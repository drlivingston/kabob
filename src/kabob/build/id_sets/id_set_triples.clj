(ns kabob.build.id-sets.id-set-triples
  (use kabob.core.util.union-find

       clojure.pprint
       clojure.stacktrace
       clojure.set

       kr.core.kb
       kr.core.rdf
       kr.core.sparql
       kabob.core.util.hashing
       kabob.core.namespace
       kabob.build.output-kb

       kabob.core.parallel-utils)
  (:require [clojure.set :as cljset]))

;;;-------------------------------------------------------------------
;;; globals
;;;-------------------------------------------------------------------

;;(def ^:dynamic *graph-db-path* "/temp/kabob/neo4j/idsets/")

(def ^:dynamic *id-set-ns* "ccp")

(def magic-prefixes
  (str "prefix franzOption_clauseReorderer: <franz:identity> \n"
       "prefix franzOption_chunkProcessingAllowed: <franz:yes> \n"
       "PREFIX franzOption_memoryLimit: <franz:85g> \n"
       "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> \n"
       "PREFIX franzOption_logQuery: <franz:yes> \n "
       "PREFIX skos: <http://www.w3.org/2004/02/skos/core#> \n"
       "PREFIX obo: <http://purl.obolibrary.org/obo/> \n"
       "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/> \n"
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

(defn generic-id-set-triples [ids]
  (let [set-sym (derive-generic-id-set-sym ids)
        type-sym (symbol *id-set-ns* "IAO_EXT_0000316")]    ;; ccp:identifier set
    (conj (map (fn [id]
                 `(~set-sym obo/RO_0002351 ~id))
               ids)
          `(~set-sym rdf/type ~type-sym))))


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


(defn query-for-id-types-in-skos-exact-match-relations [source-kb]
  (binding [*use-inference* false]
    (println "querying for identifier types used in skos:exactMatch links...")
    (let [query (str magic-prefixes "select distinct ?idtype1 ?idtype2 {
                                                             ?id1 skos:exactMatch ?id2 .
                                                             ?id1 rdfs:subClassOf ?idtype1 .
                                                             ?id2 rdfs:subClassOf ?idtype2 .
                                                             minus {?idtype1 rdfs:subClassOf* ccp:IAO_EXT_0000307}
                                                             minus {?idtype2 rdfs:subClassOf* ccp:IAO_EXT_0000307}
                                                             }")]
      (reduce cljset/union #{} (map (fn [bindings] (let [idtype1 (get bindings '?/idtype1 nil)
                                                              idtype2 (get bindings '?/idtype2 nil)]
                                                          (set (list idtype1 idtype2))))
                                         (sparql-query source-kb query))))))

;(sparql-visit source-kb
;              (fn [bindings]
;                (pprint bindings)
;                (dorun
;                  (map (partial reduce conj #{})
;                       (doall
;                         (let [idtype1 (get bindings '?/idtype1 nil)
;                               idtype2 (get bindings '?/idtype2 nil)]
;                           (prn (str "TYPES: " idtype1 " " idtype2))
;                           (list idtype1 idtype2)
;                           )))))
;              query)
;)))

(defn produce-orphan-triples [source-kb output-kb types]
  (binding [*use-inference* false]
    (println "querying for solo bioentities...")
    (let [query (str magic-prefixes " select distinct ?id where {
                                      values ?id_type {\n"
                     (clojure.string/replace (apply str (interpose "\n" types)) #"/" ":")
                     "\n}
                                      ?id rdfs:subClassOf ?id_type .
                                      # exclude any identifiers that participate in a skos:exactMatch relationship
                                      minus {?x skos:exactMatch ?id}
                                      minus {?id skos:exactMatch ?y}
                                      # exclude any identifiers that are obsolete
                                      minus {?id rdf:type ccp:IAO_EXT_0001711}
                                      # exclude any classes of identifiers. this filter is an argument for
                                      #using rdf:type instead of rdfs:subClassOf in the identifier hierarchy
                                      filter (!contains (str(?id), 'ext/IAO_'))
                                      }")
          visit-counter (atom 0)
          t (atom (.getTime (java.util.Date.)))]
      (pprint query)
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
                    query
                    ))))


;(defn produce-orphan-triples-2 [source-kb output-kb]
;  (binding [*use-inference* false]
;    (println "running singles query.")
;    (println "timing count:")
;    (let [query-body
;          (str "where {
;  				      ?id rdfs:subClassOf ?id_type2 .
;                MINUS { {?id2 skos:exactMatch ?id . }
;                        UNION
;                        { ?id skos:exactMatch ?id2 . } }
;                {
;                   select distinct ?id_type2 {
;                      ?id1 skos:exactMatch ?id2 .
;                      ?id1 rdfs:subClassOf ?id_type1 .
;                      ?id2 rdfs:subClassOf ?id_type2 .
;                   }
;                }
;                        }")
;          count-query (str magic-prefixes
;                           " select (count( distinct ?id) as ?count) " query-body)
;          singles-query (str magic-prefixes " select distinct ?id " query-body)
;          num-pairs (time
;                      (second (first (first (query-sparql source-kb
;                                                          count-query)))))
;          visit-counter (atom 0)
;          t (atom (.getTime (java.util.Date.)))]
;      (println "expected count:" num-pairs)
;
;      (println "running full query")
;      (sparql-visit source-kb
;                    (fn [bindings]
;                      (when (= 0 @visit-counter)
;                        (pprint bindings))
;                      ;;(pprint bindings)
;                      (swap! visit-counter inc)
;                      ;;(println @visit-counter)
;                      (when (= 0 (mod @visit-counter 10000))
;                        (System/gc))
;                      (when (= 0 (mod @visit-counter 250000))
;                        (let [new-t (.getTime (java.util.Date.))]
;                          (println @visit-counter " in " (- new-t @t) "ms")
;                          (reset! t new-t)))
;                      (dorun
;                        (map (partial add! output-kb)
;                             (doall
;                               (let [id (get bindings '?/id nil)]
;                                 (generic-id-set-triples (list id)))))))
;                    singles-query ;need to add reify find clauses on optionally
;                    ))))

(defn produce-orphan-triples-obo [source-kb output-kb]
  (binding [*use-inference* false]
    (println "running singles query.")
    (println "timing count:")
    (let [query-body
          "where {
                     ?id rdfs:subClassOf* ccp:IAO_EXT_0000307 . # ontology identifier
                         # exclude any identifiers that participate in a skos:exactMatch relationship
                         minus {?x skos:exactMatch ?id}
                         minus {?id skos:exactMatch ?y}
                         # exclude any classes of identifiers. this filter is an argument for
                         #using rdf:type instead of rdfs:subClassOf in the identifier hierarchy
                         filter (!contains (str(?id), 'ext/IAO_'))
                     }"
          count-query (str magic-prefixes
                           " select (count( distinct ?id) as ?count) " query-body)
          singles-query (str magic-prefixes " select distinct ?id " query-body)
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
                    singles-query                           ;need to add reify find clauses on optionally
                    ))))



; ------
; --- combined sets
; ------

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

(defn combined-pairs-concepts-fn [kb]
  (let [query-pat `((?/id skos/exactMatch ?/id2)
                     (?/id [rdfs/subClassOf *] ccp/IAO_EXT_0000342) ; ccp:identifier of a biological entity
                     (?/id2 [rdfs/subClassOf *] ccp/IAO_EXT_0000342) ; ccp:identifier of a biological entity
                     (!= ?/id ?/id2))]
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
                                     nil))                  ;)))
        union-find))))

(defn combined-pairs-property-fn [kb]
  (let [query-pat `((?/id skos/exactMatch ?/id2)
                     (?/id [rdfs/subClassOf *] ccp/IAO_EXT_0000306) ; ccp:identifier of a biological entity
                     (?/id2 [rdfs/subClassOf *] ccp/IAO_EXT_0000306) ; ccp:identifier of a biological entity
                     (!= ?/id ?/id2))]
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
                                     nil))                  ;)))
        union-find))))