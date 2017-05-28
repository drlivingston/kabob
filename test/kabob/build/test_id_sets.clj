(ns kabob.build.test-id-sets
  (use clojure.test
       kabob.build.id-sets.id-sets))
       ;;arguably we should test the neo4j bindings too
       ;;kabob.build.util.neo4j-util

;;;-------------------------------------------------------------------
;;; globals
;;;-------------------------------------------------------------------

;;NOTE TEST WILL NOT EXECUTE UNLESS THIS IS BOUND
;; e.g. (binding [*db-dir* "/temp/kabob/neo4j/test/"]

(def ^:dynamic *db-dir* nil)

;;;-------------------------------------------------------------------
;;; test input functions
;;;-------------------------------------------------------------------

(defn pairs-fn [id-list-callback-fn]
  (dorun
   (map id-list-callback-fn '((ex/a1 ex/b1)
                              (ex/a1 ex/c1)

                              (ex/a2 ex/b2)
                              (ex/b2 ex/c2)

                              (ex/a3 ex/b3)

                              (ex/a4 ex/b4)
                              (ex/c4 ex/b4)

                              (ex/a5 ex/b5)))))

(defn singles-fn [id-callback-fn]
  (dorun
   (map id-callback-fn '(ex/a5
                         ex/b5
                         ex/a6
                         exa7))))


(defn seperate-pairs [n]
  (repeatedly n (fn [] [(gensym) (gensym)])))

(defn massive-pair [n]
  (let [count (atom 0)
        a (gensym)]
    (repeatedly n
                (fn []
                  (let [b (gensym)]
                    (swap! count inc)
                    (if (even? @count)
                      [a b]
                      [b a]))))))

(defn k-pairs [k n]
  (let [count (atom 0)
        a (atom (gensym))
        t (atom (.getTime (java.util.Date.)))]
    (repeatedly n
                (fn []
                  (let [b (gensym)]
                    (swap! count inc)
                    (when (= 0 (mod @count 10000))
                      (let [new-time (.getTime (java.util.Date.))]
                        (println @count " in " (- new-time @t) "ms")
                        (reset! t new-time)))
                    (if (= 0 (mod @count k)) (reset! a (gensym)))
                    (if (even? @count)
                      [@a b]
                      [b @a]))))))

;;;-------------------------------------------------------------------
;;; test sets
;;;-------------------------------------------------------------------

(deftest id-grouping
  (when *db-dir*
    (initialize-id-sets *db-dir* pairs-fn singles-fn)
    (with-local-vars [cnt 0]
      (visit-db-nodes *db-dir* (fn [_] (var-set cnt (inc @cnt))))
      (is (= 7 @cnt)))))


(defn seperate-test [n]
  (let [graph {}]
    (reduce mem-merge-ids
            graph
            (seperate-pairs n))))

(defn k-pair-test [k n]
  (do-merge (k-pairs k n)))
;; (let [graph {}]
;;     (persistent!
;;      (reduce mem-merge-ids
;;              (transient graph)
;;              (k-pairs k n)))))

(defn block-k-pair-test [k n]
  (do-merge (k-pairs k n)))

(defn massive-pair-test [n]
  (let [graph {}]
    (reduce mem-merge-ids
            graph
            (massive-pair n))))


;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------
