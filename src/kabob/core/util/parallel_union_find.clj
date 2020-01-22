(ns kabob.core.util.parallel-union-find
  (use [clojure.string :only (split)])
  (import java.util.Arrays
          java.util.concurrent.atomic.AtomicIntegerArray))

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

(defn compression-agent-of [[_ _ _ a :as union-find]]
  a)

;;if the keys are numeric use identity
;; otherwise use something that will make them numeric e.g.:
;; (partial key-val (init-keyhash))
(defn make-union-find [max-size key-to-val-fn val-to-key-fn ]
  (let [aia (AtomicIntegerArray. ^int (int max-size))]
    (doseq [i (range max-size)]
      (.set ^AtomicIntegerArray aia i -1))
    (list aia
          key-to-val-fn
          val-to-key-fn
          (agent nil))))

;;; union find
;;;-------------------------------------------------------------------

(defn value [uf n]
  (.get ^AtomicIntegerArray (array-of uf) n))

(defn parent [uf n]
  (let [parent (value uf n)]
    (if (< parent 0)
      n
      parent)))

(defn ancestor [uf n & [hops]]
  (let [parent (.get ^AtomicIntegerArray (array-of uf) n)]
    (if (< parent 0)
      [n hops]
      (recur uf parent (- hops 1)))))

(defn immediate-parent-max-parent [uf n]
  (let [p (parent uf n)
        [a hops] (ancestor uf p -1)]
    [p a]))

(defn compress [uf n old-parent candidate-parent]
  (or (.compareAndSet ^AtomicIntegerArray (array-of uf)
                      n
                      old-parent
                      candidate-parent)
      (let [[p a] (immediate-parent-max-parent uf n)]
        (recur uf n p a))))

;; find n with path compression
(defn find-compress [uf n]
  (let [p (value uf n)]
    (if (< p 0)
      n
      (let [ancestor (find-compress uf p)]
        (compress uf n p ancestor)
        ancestor))))

;; find with path compression
(defn uf-find [union-find key]
  (let [key-fn (key-fn-of union-find)
        n (key-fn key)]
    (find-compress union-find n)))
    

(defn merge-roots [union-find parent-root child-root]
  (let [array (array-of union-find)]
    (aset array parent-root (+ (aget array parent-root)
                               (aget array child-root)))
    (aset array child-root parent-root)))

(defn new-depth [parent-depth child-depth]
  (min parent-depth
       (+ -1 child-depth)))

(defn update-ancestor-depth [uf child child-depth]
  (let [[ancestor hops] (ancestor uf child)
        aval (value uf ancestor)]
    (if (not (< ancestor 0))
      (recur uf child child-depth)
      (or (.compareAndSet ^AtomicIntegerArray (array-of uf)
                          ancestor
                          aval
                          (min aval (+ hops child-depth)))
          (recur uf child child-depth)))))


(defn update-parent-depth [uf parent parent-val child child-depth]
  (or (.compareAndSet ^AtomicIntegerArray (array-of uf)
                       parent
                       parent-val
                       (new-depth parent-val child-depth))
      ;;the parent might not be parent any more try again
      (update-ancestor-depth uf child child-depth)))
             

  
(defn attempt-merge [uf parent parent-val child child-val]
  (and (.compareAndSet ^AtomicIntegerArray (array-of uf)
                       child
                       child-val
                       parent)
       (update-parent-depth uf parent parent-val child child-val)))


(defn safe-union [uf n1 n2]
  (let [root1 (find-compress uf n1)
        root2 (find-compress uf n2)]
    (if (= root1 root2)
      nil
      (let [val1 (value uf root1)
            val2 (value uf root2)]
        (if (or (not (< val1 0))
                (not (< val2 0)))
          (recur uf n1 n2) ; not both actually roots by now - try again
          (or (if (< val1 val2) ; root1 has more use it as root
                (attempt-merge uf root1 val1 root2 val2)
                (attempt-merge uf root2 val2 root1 val1))
              ;; it's botched - start over :(
              (recur uf n1 n2)))))))

(defn uf-union [union-find k1 k2]
  (let [key-fn (key-fn-of union-find)
        n1 (key-fn k1)
        n2 (key-fn k2)]
    (safe-union union-find n1 n2)))


;;; union find
;;;-------------------------------------------------------------------

(defn array-range [uf]
  (range (.length ^AtomicIntegerArray (array-of uf))))

(defn n-root? [union-find n]
  (< (value union-find n)
     0))

(defn root? [union-find key]
  (n-root? union-find ((key-fn-of union-find) key)))

(defn roots [union-find]
  (mapcat (fn [n]
            (if (n-root? union-find n)
              (list n)
              nil))
          (array-range union-find)))


;;ineffecient
(defn num-roots [union-find]
  (count (roots union-find)))

(defn uf-flatten [union-find]
  (doseq [n (array-range union-find)]
    (find-compress union-find n))
  union-find)

(defn n-equivalence-set-seq [union-find]
  (uf-flatten union-find)
  (let [array (array-of union-find)
        root-to-set (transient {})]
    (map distinct
         (vals
          (persistent!
           (reduce (fn [r-to-s n]
                     (let [parent (value union-find n)]
                       (if (< parent 0)
                         (assoc! r-to-s n
                                 (conj (get r-to-s n '()) n))
                         (assoc! r-to-s parent
                                 (conj (get r-to-s parent '()) n)))))
                   root-to-set
                   (array-range union-find)))))))

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
