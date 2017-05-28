(ns kabob.build.id-sets.id-set-triples
  (use kabob.build.id-sets.id-sets
       kabob.core.util.union-find
       
       clojure.pprint
       clojure.stacktrace

       kr.core.kb
       kr.core.rdf
       kr.core.sparql
       kabob.core.util.hashing
       kabob.core.namespace
       kabob.build.output-kb

       kabob.core.parallel-utils))

;;;-------------------------------------------------------------------
;;; globals
;;;-------------------------------------------------------------------

;;(def ^:dynamic *graph-db-path* "/temp/kabob/neo4j/idsets/")

(def ^:dynamic *id-set-ns* "ccp")

(def magic-prefixes
  (str "PREFIX franzOption_memoryLimit: <franz:85g> \n"
       "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> \n"
       "PREFIX franzOption_logQuery: <franz:yes> \n "
       "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
       "PREFIX obo: <http://purl.obolibrary.org/obo/> \n"
       "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/> \n"
       ;; "PREFIX franzOption_chunkProcessingAllowed: <franz:yes> \n"
       ;; "PREFIX franzOption_chunkProcessingSize: <franz:500000> \n"
       ))

;;;-------------------------------------------------------------------
;;; ouptut
;;;-------------------------------------------------------------------

(defn open-output-kb [base-dir id-type]
  (zipped-output-kb (str base-dir id-type "-IDSet.nt.gz")))

;;;-------------------------------------------------------------------
;;; generate symbols
;;;-------------------------------------------------------------------

(defn id-set-sym [id-type hash-code]
  (symbol *id-set-ns*
          (str (name id-type) "-Set-" hash-code)))

(defn generic-id-set-sym [hash-code]
  (symbol *id-set-ns*
          (str "IDs-" hash-code)))

(defn hash-long-names [ids]
  (let [long-names (sort
                    (map (partial sym-to-long-name
                                  nil
                                  (reduce (fn [m [short long]]
                                            (assoc m short long))
                                          {}
                                          *namespaces*))
                         ids))
        hashing-string (str "<"
                            (apply str (interpose "><" long-names))
                            ">")]
    (sha-1 hashing-string)))

(defn derive-id-set-sym [id-type ids]
  (id-set-sym id-type (hash-long-names ids)))

(defn derive-generic-id-set-sym [ids]
  (generic-id-set-sym (hash-long-names ids)))

;;;-------------------------------------------------------------------
;;; generate triples
;;;-------------------------------------------------------------------

(defn id-set-triples [id-type ids]
  (let [set-sym (derive-id-set-sym id-type ids)
        type-sym (symbol *id-set-ns* (str (name id-type) "-Set"))]
    (conj (map (fn [id]
                 `(~set-sym obo/RO_0002351 ~id))
               ids)
          `(~set-sym rdf/type ~type-sym))))


(defn generic-id-set-triples [ids]
  (let [set-sym (derive-generic-id-set-sym ids)
        type-sym (symbol *id-set-ns* "IAO_EXT_0000316")] ;; ccp:identifier set
    (conj (map (fn [id]
                 `(~set-sym obo/RO_0002351 ~id))
               ids)
          `(~set-sym rdf/type ~type-sym))))

;;;-------------------------------------------------------------------
;;; produce all triples
;;;-------------------------------------------------------------------

;; (defn produce-id-set-triples [graph-db-path output-kb id-type]
;;   (visit-id-sets graph-db-path
;;                  (fn [ids]
;;                    (dorun
;;                     (map (partial add! output-kb)
;;                          (id-set-triples id-type ids))))))

(defn n-clean-union-find [union-find]
  (pprint "cleaning uf n-sets")
  (time
   (remove (fn [s]
             (and (= nil (first s))
                  (empty? (rest s))))
           (n-equivalence-set-seq union-find))))

(defn good-set? [set]
  (not (and (= nil (first set))
            (empty? (rest set)))))
  

;(defn produce-id-set-triples [uf output-kb id-type]
;  (let [count (atom 0)
;        t (atom (.getTime (java.util.Date.)))]
;    (println "building set triples")
;    (time
;     (dorun
;      (map (fn [n-ids]
;             (swap! count inc)
;             (when (= 0 (mod @count 10000))
;               (System/gc))
;             (when (= 0 (mod @count 250000))
;               (let [new-t (.getTime (java.util.Date.))]
;                 (println @count " in " (- new-t @t) "ms")
;                 (reset! t new-t)))
;             (let [eset (translate-equivalence-set uf n-ids)]
;               (when (good-set? eset)
;                 (dorun
;                  (map (partial add! output-kb)
;                       (id-set-triples id-type eset)))))
;             nil)
;           (n-clean-union-find uf))))
;    nil))

(defn produce-generic-id-set-triples [uf output-kb]
  (let [count (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (println "building set triples")
    (time
     (dorun
      (map (fn [n-ids]
             (swap! count inc)
             (when (= 0 (mod @count 10000))
               (System/gc))
             (when (= 0 (mod @count 250000))
               (let [new-t (.getTime (java.util.Date.))]
                 (println @count " in " (- new-t @t) "ms")
                 (reset! t new-t)))
             (let [eset (translate-equivalence-set uf n-ids)]
               (when (good-set? eset)
                 (dorun
                  (map (partial add! output-kb)
                       (generic-id-set-triples eset)))))
             nil)
           (n-clean-union-find uf))))
    nil))

;; (defn produce-id-set-triples [id-sets output-kb id-type]
;;   (let [count (atom 0)
;;         t (atom (.getTime (java.util.Date.)))]
;;     (time
;;      (dorun
;;       (map (fn [ids]
;;              (swap! count inc)
;;              (when (= 0 (mod @count 10000))
;;                (System/gc))
;;              (when (= 0 (mod @count 250000))
;;                (let [new-t (.getTime (java.util.Date.))]
;;                  (println @count " in " (- new-t @t) "ms")
;;                  (reset! t new-t)))
;;              (dorun
;;               (map (partial add! output-kb)
;;                    (id-set-triples id-type ids)))
;;              nil)
;;            id-sets)))
;;     nil))

;; (defn produce-single-set-triples [id-graph source-kb output-kb id-type]
;;   (let [query-pat `((?/ice1 rdf/type ~id-type))]
;;     (binding [*use-inference* false]
;;       (println "running singles query.")
;;       (parallel-triple-creation source-kb
;;                                 output-kb
;;                                 query-pat
;;                                 (fn [bindings]
;;                                   (let [id (get bindings '?/ice1 nil)]
;;                                     (when (and id
;;                                                (not (get id-graph id nil)))
;;                                       (id-set-triples id-type (list id)))))))))

;;(defn run-forward-rule [source-kb target-kb rule]
(defn produce-generic-singles-triples [source-kb output-kb]
  (binding [*use-inference* false]
    (println "running singles query.")
    (println "timing count:")
    (let [query-body
          (str "where {
                ?id_type rdfs:subClassOf* obo:IAO_0000578 .
  				?id rdf:type ?id_type .
                MINUS { {?id2 skos:exactMatch ?id . }
                        UNION
                        { ?id skos:exactMatch ?id2 . } }}")
          count-query (str magic-prefixes
                           " select (count(?id) as ?count) " query-body)
          singles-query (str magic-prefixes " select ?id " query-body)
          num-pairs (time
                     (second (first (first (query-sparql source-kb
                                                         count-query)))))
          visit-counter (atom 0)
          t (atom (.getTime (java.util.Date.)))]
      (println "expected count:" num-pairs)

      (println "running full query")
      (sparql-visit source-kb
                   (fn [bindings]
                     (when (= 0 @visit-counter)
                       (pprint bindings))
                     ;;(pprint bindings)
                     (swap! visit-counter inc)
                     ;;(println @visit-counter)
                     (when (= 0 (mod @visit-counter 10000))
                       (System/gc))
                     (when (= 0 (mod @visit-counter 250000))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @visit-counter " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (dorun
                      (map (partial add! output-kb)
                           (doall
                            (let [id (get bindings '?/id nil)]
                              (generic-id-set-triples (list id)))))))
                   singles-query ;need to add reify find clauses on optionally
                   ))))


;; (defn produce-generic-singles-triples [source-kb output-kb]
;;   (binding [*use-inference* false]
;;     (println "running singles query.")
;;     (println "timing count:")
;;     (let [query-body
;;           (str " where {
;;                 ?ice kiao:denotesSubClassOf ?class .  
;;                 MINUS { {?ice2 skos:exactMatch ?ice . }
;;                         UNION
;;                         { ?ice skos:exactMatch ?ice2 . } }}")
;;           count-query (str magic-prefixes
;;                            " select (count(?ice) as ?count) " query-body)
;;           singles-query (str magic-prefixes " select ?ice " query-body)
;;           num-pairs (time
;;                      (second (first (first (query-sparql source-kb
;;                                                          count-query)))))]
;;       (println "expected count:" num-pairs)
;;       (nonparallel-triple-creation-sparql source-kb
;;                                           output-kb
;;                                           singles-query
;;                                           (fn [bindings]
;;                                             (let [id (get bindings '?/ice nil)]
;;                                               (generic-id-set-triples
;;                                                (list id))))))))


;(defn produce-generic-single-set-triples [source-kb output-kb id-type]
;  (binding [*use-inference* false]
;    (println "running singles query.")
;    (println "timing count:")
;    (let [query-pat `((?/ice1 rdf/type ~id-type))
;          query-body
;          (str " where {
;                ?ice rdf:type " (namespace id-type) ":" (name id-type) " .
;                MINUS { {?ice2 skos:exactMatch ?ice . }
;                        UNION
;                        { ?ice skos:exactMatch ?ice2 . } }}")
;          count-query (str magic-prefixes
;                           " select (count(*) as ?count) " query-body)
;          singles-query (str magic-prefixes " select ?ice " query-body)
;          num-pairs (time
;                     (second (first (first (query-sparql source-kb
;                                                         count-query)))))]
;      (println "expected count:" num-pairs)
;      (parallel-triple-creation-sparql source-kb
;                                       output-kb
;                                       singles-query
;                                       (fn [bindings]
;                                         (let [id (get bindings '?/ice nil)]
;                                           (generic-id-set-triples
;                                            (list id))))))))

;
;(defn produce-single-set-triples [source-kb output-kb id-type]
;  (binding [*use-inference* false]
;    (println "running singles query.")
;    (println "timing count:")
;    (let [query-pat `((?/ice1 rdf/type ~id-type))
;          query-body
;          (str " where {
;                ?ice rdf:type " (namespace id-type) ":" (name id-type) " .
;                MINUS { {?ice2 skos:exactMatch ?ice . }
;                        UNION
;                        { ?ice skos:exactMatch ?ice2 . } }}")
;          count-query (str magic-prefixes
;                           " select (count(*) as ?count) " query-body)
;          singles-query (str magic-prefixes " select ?ice " query-body)
;          num-pairs (time
;                     (second (first (first (query-sparql source-kb
;                                                         count-query)))))]
;      (println "expected count:" num-pairs)
;      (parallel-triple-creation-sparql source-kb
;                                       output-kb
;                                       singles-query
;                                       (fn [bindings]
;                                         (let [id (get bindings '?/ice nil)]
;                                           (id-set-triples id-type
;                                                           (list id))))))))


;(defn write-id-set-triples [id-graph id-sets source-kb output-kb id-type]
;;  (binding [*parallel-query-logging-frequency* 25000]
;  ;;(let [output-kb (open-output-kb output-dir (name id-type))]
;      (try
;        (println "first graph:")
;        (pprint (first id-graph))
;        (println "first set:")
;        (pprint (first id-sets))
;        
;        (produce-id-set-triples id-sets output-kb id-type)
;        (produce-single-set-triples id-graph source-kb output-kb id-type)
;        
;        (catch Exception e (print-cause-trace e))))
    ;;    (finally (close output-kb)))));;)

;; (defn write-id-set-triples [id-graph id-sets single-fn output-dir id-type]
;;   (let [output-kb (open-output-kb output-dir (name id-type))]
;;     (try
;;       (println "first graph:")
;;       (pprint (first id-graph))
;;       (println "first set:")
;;       (pprint (first id-sets))

;;       (produce-id-set-triples id-sets output-kb id-type)
;;       (produce-single-set-triples id-graph single-fn output-kb id-type)
;;       (finally (close output-kb)))))

;; (defn write-id-set-triples [graph-db-path output-dir id-type]
;;   (let [output-kb (open-output-kb output-dir id-type)]
;;     (try
;;       (produce-id-set-triples graph-db-path output-kb id-type)
;;       (finally (close output-kb)))))
                       
;; need an input-dir (path) and
;; an output-file (for a writer kb) for group triples
;; an output-file (for a writer kb) for diffs?
;; should diffs be computed here? -- do we even need explict diffs?
;; NO but old un-used groups need to be preserved.

;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------

