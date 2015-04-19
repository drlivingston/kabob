(ns edu.ucdenver.ccp.kabob.util.union-find
  (use [clojure.string :only (split)])
  (import java.util.Arrays))

;;;-------------------------------------------------------------------
;;; key hash
;;;-------------------------------------------------------------------

(defmacro hash-of [key-hash]
  `(first @~key-hash))

(defmacro lookup-of [key-hash]
  `(second @~key-hash))

(defn init-keyhash []
  (atom (list {} [] -1)))


(defn find-key [key-hash key]
  (get (hash-of key-hash) key nil))

;; this needs to see if somehow the key got in there from another thread
;;   and then abort the assignment
(defn set-key [key-hash key]
  (swap! key-hash
         (fn [[hash lookup val :as kh] key]
           (if (get hash key nil)
             kh
             (let [new-val (inc val)]
               (list (assoc hash key new-val)
                     (assoc lookup new-val key)
                     new-val))))
         key)
  (find-key key-hash key))

(defn key-val [key-hash key]
  (or (find-key key-hash key)
      (set-key key-hash key)))

(defn val-key [key-hash val]
  (let [[_ _ max-val] @key-hash]
    (if (> val max-val)
      nil
      ((lookup-of key-hash) val))))


(deftype ByteContainer [^bytes bytes]
  Object
  (equals [a b] (Arrays/equals ^bytes (.bytes a) ^bytes (.bytes b)))
  (hashCode [this] (Arrays/hashCode ^bytes (.bytes this))))
;;(toString [this] (.name this)))

(defn better-string-key-val [key-hash key]
  (let [new-key (ByteContainer. ^bytes (.getBytes (str key) "UTF-8"))]
    (or (find-key key-hash new-key)
        (set-key key-hash new-key))))

(defn better-string-val-key [key-hash val]
  (let [[_ _ max-val] @key-hash]
    (if (> val max-val)
      nil
      (String. ^bytes (.bytes ((lookup-of key-hash) val))
                "UTF-8"))))

(defn better-symbol-val-key [key-hash val]
  (let [[_ _ max-val] @key-hash]
    (if (> val max-val)
      nil
      (apply symbol
             (split (String. ^bytes (.bytes ((lookup-of key-hash) val))
                             "UTF-8")
                    #"/"
                    2)))))

;;;-------------------------------------------------------------------
;;; union find
;;;-------------------------------------------------------------------

;;; helpers
;;;-------------------------------------------------------------------

(defn array-of [union-find]
  (first union-find))

(defn key-fn-of [union-find]
  (second union-find))

(defn val-fn-of [[_ _ fn :as union-find]]
  fn)

;;if the keys are numeric use identity
;; otherwise use something that will make them numeric e.g.:
;; (partial key-val (init-keyhash))
(defn make-union-find [max-size key-to-val-fn val-to-key-fn ]
  (list (int-array max-size -1)
        key-to-val-fn
        val-to-key-fn))

;;; union find
;;;-------------------------------------------------------------------

;; find with path compression
(defn find-compress [union-find n]
  (let [array (array-of union-find)
        parent (aget array n)]
    (if (< parent 0)
      n
      (aset array n (find-compress union-find parent)))))

;; find with path compression
(defn uf-find [union-find key]
  (let [key-fn (key-fn-of union-find)
        n (key-fn key)
        array (array-of union-find)]
    (if (< (aget array n) 0)
      n
      (find-compress union-find n))))

(defn merge-roots [union-find parent-root child-root]
  ;;(println "merge " parent-root " " child-root)
  (let [array (array-of union-find)]
    (aset array parent-root (+ (aget array parent-root)
                               (aget array child-root)))
    (aset array child-root parent-root)))

(defn uf-union [union-find k1 k2]
  (let [key-fn (key-fn-of union-find)
        root1 (uf-find union-find k1)
        root2 (uf-find union-find k2)
        array (array-of union-find)]
    ;;(println "union " root1 " " root2)
    (cond
     (= root1 root2) nil
     (< (aget array root1)
        (aget array root2)) ;;root1 has more
           (merge-roots union-find root1 root2)
     :else (merge-roots union-find root2 root1))))


;;; union find
;;;-------------------------------------------------------------------

(defn root? [union-find key]
  (< (aget (array-of union-find) ((key-fn-of union-find) key))
     0))

(defn n-root? [union-find n]
  (< (aget (array-of union-find) n)
     0))

(defn roots [union-find]
  (let [array (array-of union-find)]
    (mapcat (fn [n]
              (if (n-root? union-find n)
                (list n)
                nil))
            (range (alength (array-of union-find))))))

;;ineffecient
(defn num-roots [union-find]
  (count (roots union-find)))

(defn uf-flatten [union-find]
  (doseq [n (range (alength (array-of union-find)))]
    (find-compress union-find n))
  union-find)

(defn n-equivalence-set-seq [union-find]
  (uf-flatten union-find)
  (let [array (array-of union-find)
        root-to-set (transient {})]
;;    (doall
     (map distinct
          (vals
           (persistent!
            (reduce (fn [r-to-s n]
                      (let [parent (aget array n)]
                        (if (< parent 0)
                          (assoc! r-to-s n
                                  (conj (get r-to-s n '()) n))
                          (assoc! r-to-s parent
                                  (conj (get r-to-s parent '()) n)))))
                    root-to-set
                    (range (alength (array-of union-find)))))))));;)

(defn translate-equivalence-set [union-find n-set]
  (map (val-fn-of union-find) n-set))

;;could return (nil) sets
;; e.g., ((f b a) (nil) (nil) ...
(defn equivalence-set-seq [union-find]
  (map (fn [keys]
         (map (val-fn-of union-find) keys))
       (n-equivalence-set-seq union-find)))

    
;;;-------------------------------------------------------------------
;;; normal uses
;;;-------------------------------------------------------------------

(defn numeric-union-find [max-size]
  (make-union-find max-size identity identity))

(defn universal-union-find [max-size]
  (let [kh (init-keyhash)]
    (make-union-find  max-size (partial key-val kh) (partial val-key kh))))

;;;-------------------------------------------------------------------
;;; end
;;;-------------------------------------------------------------------
