(ns kabob.build.id-sets.kabob-id-sets
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       kabob.core.namespace

       kabob.core.util.union-find
       kabob.core.parallel-utils
       kabob.build.id-sets.id-set-triples
       
       clojure.stacktrace
       clojure.pprint))

;;;-------------------------------------------------------------------
;;; generate id finding functions
;;;-------------------------------------------------------------------

;; (def magic-prefixes
;;   (str "PREFIX franzOption_memoryLimit: <franz:85g> \n"
;;        "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> \n"
;;        "PREFIX franzOption_logQuery: <franz:yes> \n"))


;; (defn mem-merge-ids [graph ids]
;;   ;; (println "graph count and max")
;;   ;; (pprint (count graph)) (println)
;;   ;; (pprint (reduce max 0 (map (fn [[_ id-set]] (count id-set)) graph)))
;;   (let [existing-ids (mapcat (fn [id]
;;                                (get graph id nil))
;;                              ids)
;;         combined-ids (set (concat existing-ids ids))]
;;      (reduce (fn [g id]
;;                (assoc! g id combined-ids))
;;                ;;(assoc g id combined-ids))
;;              graph
;;              ;(transient graph)
;;              (seq combined-ids))))


(defn mem-merge-ids [graph ids]
  (let [first-set (get graph (first ids) nil)]
    (if (and (not (empty? first-set))
             (every? first-set ids))  ; if the first set covers every input id
      graph                           ; nothing to do
      (let [existing-ids (concat first-set
                                 (mapcat (fn [id]
                                           (get graph id nil))
                                         (rest ids)))
            combined-ids (set (concat existing-ids ids))]
        (reduce (fn [g id]
                  (assoc! g id combined-ids))
                graph
                (seq combined-ids))))))

;;;-------------------------------------------------------------------
;;; generate id finding functions
;;;-------------------------------------------------------------------

;; (defn do-merge [seq-of-id-lists]
;;   (let [graph {}]
;;     (persistent!
;;      (reduce mem-merge-ids
;;              (transient graph)
;;              seq-of-id-lists))))

;;this is more like query reduce with logging
;; which should be built on query visit with logging
(defn query-reduce-with-logging [kb query-pat initial-results
                                fn-of-result-and-bindings]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))
        result (atom initial-results)]
    (try
      (println "expected count:")
      (time
       (println (query-count kb query-pat)))
      (query-visit kb
                   (fn [bindings]
                     (swap! count inc)
                     (when (= 0 (mod @count 10000))
                       (System/gc))
                     (when (= 0 (mod @count 250000))
                       ;;(pprint (first @result))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @count " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (swap! result fn-of-result-and-bindings bindings))
                   ;;(fn-of-bindings result bindings))
                   query-pat)
      (catch Exception e (print-cause-trace e))
      (finally (println "final count: " @count)))
    @result))


;;this is more like query reduce with logging
;; which should be built on query visit with logging
(defn query-reduce-with-logging-sparql [kb query-sparql initial-results
                                        fn-of-result-and-bindings]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))
        result (atom initial-results)]
    (try
      (visit-sparql kb
                   (fn [bindings]
                     (swap! count inc)
                     (when (= 0 (mod @count 10000))
                       (System/gc))
                     (when (= 0 (mod @count 250000))
                       ;;(pprint (first @result))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @count " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (swap! result fn-of-result-and-bindings bindings))
                   ;;(fn-of-bindings result bindings))
                   query-sparql)
      (catch Exception e (print-cause-trace e))
      (finally (println "final count: " @count)))
    @result))


;; (defn pairs-fn [kb map-of-sets id-type]
;;   (let [query-pat-a `((?/ice1 rdf/type ~id-type)
;;                       (?/ice1 skos/exactMatch ?/ice2))
;;         query-pat-b `((?/ice1 rdf/type ~id-type)
;;                       (?/ice2 skos/exactMatch ?/ice1))]
;;     (binding [*use-inference* false]
;;       (println "running pairs query a.")
;;       (let [a-results 
;;             (query-reduce-with-logging kb query-pat-a {}
;;               (fn [graph bindings]
;;                 (let [ice1 (get bindings '?/ice1 nil)
;;                       ice2 (get bindings '?/ice2 nil)]
;;                   (and ice1
;;                        ice2
;;                        (mem-merge-ids graph (list ice1 ice2))))))]
;;         (println "running pairs query b.")
;;             (query-reduce-with-logging kb query-pat-b a-results
;;               (fn [graph bindings]
;;                 (let [ice1 (get bindings '?/ice1 nil)
;;                       ice2 (get bindings '?/ice2 nil)]
;;                   (and ice1
;;                        ice2
;;                        (mem-merge-ids graph (list ice1 ice2))))))))))


;; (defn pairs-fn [kb map-of-sets id-type]
;;   (let [query-pat `((?/ice1 rdf/type ~id-type)
;;                     (:union ((?/ice1 skos/exactMatch ?/ice2))
;;                             ((?/ice2 skos/exactMatch ?/ice1))))]
;;     (binding [*use-inference* false]
;;       (println "running pairs query.")
;;       (persistent!
;;        (query-reduce-with-logging kb query-pat (transient {})
;;          (fn [graph bindings]
;;            (let [ice1 (get bindings '?/ice1 nil)
;;                  ice2 (get bindings '?/ice2 nil)]
;;              (and ice1
;;                   ice2
;;                   (mem-merge-ids graph (list ice1 ice2))))))))))

;; (defn clean-union-find [union-find]
;;   (remove (fn [s]
;;             (and (= nil (first s))
;;                  (empty? (rest s))))
;;           (equivalence-set-seq union-find)))

(defn pairs-fn [kb map-of-sets id-type]
  (let [query-pat `((?/ice1 rdf/type ~id-type)
                    (:union ((?/ice1 skos/exactMatch ?/ice2))
                            ((?/ice2 skos/exactMatch ?/ice1))))
        query-body
        (str "where {
               {?ice1 rdf:type " (namespace id-type) ":" (name id-type) " .
                ?ice1 skos:exactMatch ?ice2} 
               UNION
               {?ice2 rdf:type " (namespace id-type) ":" (name id-type) " .
                ?ice1 skos:exactMatch ?ice2 .
                FILTER NOT EXISTS {?ice1 rdf:type "
                                     (namespace id-type) ":" (name id-type) "}} 
               }")
        count-query (str magic-prefixes
                         " select (count(*) as ?count) " query-body)
        pair-query (str magic-prefixes
                        " select ?ice1 ?ice2 " query-body)
        ]
    (binding [*use-inference* false
              *work-queue-single-threaded* true]
      (println "running pairs query.")
      (println "timing")
      (let [num-pairs (time
                       (second (first (first (query-sparql kb count-query)))))
            max-num (* 2 num-pairs)
            khash (init-keyhash)
            union-find (make-union-find max-num
                                        (partial better-string-key-val khash)
                                        (partial better-symbol-val-key khash))]
        (println "expected count " num-pairs)
        (with-work-queue work
          (query-reduce-with-logging-sparql kb pair-query union-find
            (fn [_ bindings]
              (work (fn []
                      (let [ice1 (get bindings '?/ice1 nil)
                            ice2 (get bindings '?/ice2 nil)]
                        (and ice1
                             ice2
                             (uf-union union-find ice1 ice2))))))))
        union-find))))
        ;; (println "cleaning")
        ;; (clean-union-find union-find)))))

        ;; [(hash-of khash)
        ;;  (clean-union-find union-find)]))))

        
(defn combined-pairs-fn [kb]
  (let [query-pat `((?/id skos/exactMatch ?/id2)
                     (?/id rdf/type ?/id_type)
                     (?/id_type [rdfs/subClassOf *] obo/IAO_0000578 ) ;centrally registered identifier
                     (?/id2 rdf/type ?id_type2)
                     (?/id_type2 [rdfs/subClassOf *] obo/IAO_0000578) ;centrally registered identifier
                     (!= ?/id  ?/id2))]
    (binding [*use-inference* false
              *work-queue-single-threaded* true]
      (println "running pairs query.")
      (println "timing")
      (let [num-pairs (time (query-count kb query-pat))
            max-num (* 2 num-pairs)
            khash (init-keyhash)
            union-find (make-union-find max-num
                                        (partial better-string-key-val khash)
                                        (partial better-symbol-val-key khash))]
        (println "expected count: " num-pairs)
        ;(with-work-queue work
          (query-reduce-with-logging kb query-pat union-find
            (fn [_ bindings]
              ;(work (fn []
                      (let [id (get bindings '?/id nil)
                            id2 (get bindings '?/id2 nil)]
                        (and id
                             id2
                             (uf-union union-find id id2)))
                      nil));)))
        union-find))))



;; (defn pairs-fn [kb map-of-sets id-type]
;;   (let [query-pat `((?/ice1 rdf/type ~id-type)
;;                     (:union ((?/ice1 skos/exactMatch ?/ice2))
;;                             ((?/ice2 skos/exactMatch ?/ice1))))]
;;     (binding [*use-inference* false]
;;       (println "running pairs query.")
;;       (let [max-num (* 2 (query-count kb query-pat))
;;             khash (init-keyhash)
;;             union-find (make-union-find max-num
;;                                         (partial key-val khash)
;;                                         (partial val-key khash))]
;;         (query-reduce-with-logging kb query-pat union-find
;;           (fn [graph bindings]
;;             (let [ice1 (get bindings '?/ice1 nil)
;;                   ice2 (get bindings '?/ice2 nil)]
;;               (and ice1
;;                    ice2
;;                    (uf-union union-find ice1 ice2)))))
;;         [(hash-of khash)
;;          (clean-union-find union-find)]))))


;; (defn pairs-fn [id-type]
;;   (fn [kb callback-fn]
;;     (let [query-pat `((?/ice1 rdf/type ~id-type)
;;                       (:union ((?/ice1 skos/exactMatch ?/ice2))
;;                               ((?/ice2 skos/exactMatch ?/ice1))))]
;;       (binding [*use-inference* false]
;;         (println "running pairs query.")
;;         (query-visit-with-logging kb query-pat callback-fn)))))

;; could this somehow be combined with the above?
;; huge wastefully slow query?
(defn singles-fn [id-type]
  (fn [kb callback-fn]
    (let [query-pat `((?/ice1 rdf/type ~id-type))]
      (binding [*use-inference* false]
        (println "running singles query.")
        (query-reduce-with-logging kb query-pat nil
          (fn [_ bindings]
            (let [ice1 (get bindings '?/ice1 nil)]
              (and ice1
                   (callback-fn ice1)))))))))


;; (defn singles-fn [id-type]
;;   (fn [kb callback-fn]
;;     (let [query-pat `((?/ice1 rdf/type ~id-type))]
;;       (binding [*use-inference* false]
;;         (println "running singles query.")
;;         (query-visit-with-logging kb query-pat callback-fn)))))


;;;-------------------------------------------------------------------
;;; generate sets
;;;-------------------------------------------------------------------

(defn initialize-id-sets [source-kb id-type]
  (let [uf (pairs-fn source-kb {} id-type)]
    ;;(println "basic stats (count and first)")
    ;; (pprint (count id-sets))
    ;; (pprint (first id-sets))
    uf))
;;[id-hash id-sets]))




;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------
