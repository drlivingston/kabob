(ns kabob.build.id-sets.test-large-hash
  (use kabob.core.util.union-find
       clojure.pprint)
  (import java.lang.management.ManagementFactory
          java.lang.management.MemoryMXBean
          java.lang.management.MemoryUsage))

(def ^:dynamic *uri-base* "http://www.example.com/kabob/ice/foo")

(def ^:dynamic *uri-short-ns* "kfoo")
(def ^:dynamic *uri-name-prefix* "FooID")

(def ^:dynamic *parallel-query-logging*           true)
(def ^:dynamic *parallel-query-logging-frequency* 250000)
(def ^:dynamic *parallel-query-gc-frequency*      10000)



;; (def interner (Interners/newWeakInterner))

;; (def heap-intern [x]
;;   (.intern interner x))

(defn uri [i]
  (symbol *uri-short-ns*
          (str *uri-name-prefix* i)))

(defn uri-loading [i]
  (.getBytes (str (uri i))
             "UTF-8"))

;;(defn uri [i] i)

(defn uri-seq [n]
  (map uri (range n)))

(defn uri-seq-loading [n]
  (map uri-loading (range n)))


(defn test-loading [n]
  (let [kh (init-keyhash)
        count (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (dorun
     (map (fn [uri]
            (swap! count inc)
            (when (= 0 (mod @count *parallel-query-gc-frequency*))
              (System/gc))
            (when (= 0 (mod @count
                            *parallel-query-logging-frequency*))
              (let [new-t (.getTime (java.util.Date.))]
                (println @count " in " (- new-t @t) "ms")
                (reset! t new-t)))
            (key-val kh uri))
          (uri-seq-loading n)))))

(defn test-9m []
  (time
   (test-loading 9000000)))


(defn test-9m-with-uf-in-mem []
  (test-loading 9000000))

    

(defn fast-burn [n]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (dorun
     (map (fn [i]
            (swap! count inc)
            (when (= 0 (mod @count *parallel-query-gc-frequency*))
              (System/gc))
            (when (= 0 (mod @count
                            *parallel-query-logging-frequency*))
              (let [new-t (.getTime (java.util.Date.))]
                (println @count " in " (- new-t @t) "ms")
                (reset! t new-t)))
            i)
          (range n)))))

(defn n-clean-union-find [union-find]
  (remove (fn [s]
            (and (= nil (first s))
                 (empty? (rest s))))
          (n-equivalence-set-seq union-find)))

(defn clean-testing [union-find]
  (println "cleaning and equivalent-sets")
  (let [id-sets (time (n-clean-union-find union-find))]
    (println "burning iterator")
    (let [item-count (atom 0)
          t (atom (.getTime (java.util.Date.)))]
      (time
       (dorun
        (map (fn [n-ids]
               (swap! item-count inc)
               (when (= 0 (mod @item-count 10000))
                 (System/gc))
               (when (= 0 (mod @item-count 250000))
                 (let [new-t (.getTime (java.util.Date.))]
                   (println @item-count " in " (- new-t @t) "ms")
                   (reset! t new-t)))
               (dorun (translate-equivalence-set union-find n-ids)))
             id-sets)))))
  nil)

(defn clean-union-find [union-find]
  (remove (fn [s]
            (and (= nil (first s))
                 (empty? (rest s))))
          (equivalence-set-seq union-find)))

(defn clean-testing [union-find]
  (println "cleaning and equivalent-sets")
  (let [id-sets (time (clean-union-find union-find))]
    (println "burning iterator")
    (let [item-count (atom 0)
          t (atom (.getTime (java.util.Date.)))]
      (time
       (dorun
        (map (fn [ids]
               (swap! item-count inc)
               (when (= 0 (mod @item-count 10000))
                 (System/gc))
               (when (= 0 (mod @item-count 250000))
                 (let [new-t (.getTime (java.util.Date.))]
                   (println @item-count " in " (- new-t @t) "ms")
                   (reset! t new-t)))
               (dorun ids))
             id-sets)))))
  nil)


(defn union-find-test [n]
  (time
   (let [num-pairs n
         max-num (+ 4 (* 2 num-pairs))
         khash (init-keyhash)
         union-find (make-union-find max-num
                                     (partial better-string-key-val khash)
                                     (partial better-symbol-val-key khash))
         count (atom 0)
         t (atom (.getTime (java.util.Date.)))]
     (println "expected count " num-pairs)
     (time
      (loop [uris (uri-seq (* 2 num-pairs))]
        (let [[a b & rest-uris] uris]
          (when (not (empty? uris))
            (swap! count inc)
            (when (= 0 (mod @count *parallel-query-gc-frequency*))
              (System/gc))
            (when (= 0 (mod @count
                            *parallel-query-logging-frequency*))
              (let [new-t (.getTime (java.util.Date.))]
                (println @count " in " (- new-t @t) "ms")
                (reset! t new-t)))
            (uf-union union-find a b)
            (recur rest-uris)))))
     ;(clean-testing union-find)
     union-find)))

        
(defn mem-details []
  (let [mem-bean  (ManagementFactory/getMemoryMXBean)
        heap     (.getHeapMemoryUsage mem-bean)
        non-heap (.getNonHeapMemoryUsage mem-bean)]
    (pprint heap)))

(defn perm-gen []
  (let [mb (last (ManagementFactory/getMemoryPoolMXBeans))]
    (pprint (.getName mb))
    (pprint (.getType mb))
    (pprint (.getMemoryManagerNames mb))
    (pprint (.getCollectionUsage mb))))



