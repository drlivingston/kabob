(ns rules-tests.validation.owl-restriction-validation-test
  (use clojure.test
        kr.sesame.kb
        kr.sesame.sparql
        kr.sesame.rdf
        )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule]]
             [kr.core.forward-rule :refer [add-reify-fns]]
             [kr.core.sparql :refer [sparql-select-query query]]
             [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
             [kr.core.kb :refer [kb open close]]
             [kabob.core.namespace :refer [*namespaces*]]
             [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
             [kabob.build.output-kb :refer [output-kb]]
             ;;[kabob.core.kabob]
             [clojure.pprint :refer [pprint]]))


(def validation-rules '("rules/validation/valid_owl/restriction/redundant_restriction_check"
                         "rules/validation/valid_owl/restriction/restriction_has_multiple_owl_onproperty"
                         "rules/validation/valid_owl/restriction/restriction_has_multiple_owl_somevaluesfrom"
                         "rules/validation/valid_owl/restriction/restriction_missing_owl_onproperty"
                         "rules/validation/valid_owl/restriction/restriction_missing_values_from"))


(defn run-rule [source-kb target-kb rule-path]
  "run a single build rule against source-kb and populate the target-kb with the rule output"
  (let [rule (kabob-load-rules-from-classpath rule-path)]
    (prn (str "******** Running rule: " rule-path))
    (pprint rule)
    (dorun (map (partial run-forward-rule source-kb target-kb) rule))))

(defn test-kb [triples]
  "initializes an empty kb"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))



(def proper-restriction-triples
  `((ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)))

(def restriction-missing-onproperty-triples
  `((ex/r rdf/type owl/Restriction)
     (ex/r owl/someValuesFrom ex/CHEBI_12345)
     (ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)))


(deftest rules-detect-missing-onproperty
  (let [source-kb (test-kb restriction-missing-onproperty-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 3))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid restriction
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-missing-onproperty-with-proper-restriction
  (let [source-kb (test-kb proper-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 3))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid restrictions
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



(def restriction-missing-svf-triples
  `((ex/r rdf/type owl/Restriction)
     (ex/r owl/onProperty ex/BFO_0000055)
     (ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)))

(deftest rules-detect-missing-svf
  (let [source-kb (test-kb restriction-missing-svf-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 4))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid restriction
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-missing-svf-with-proper-restriction
  (let [source-kb (test-kb proper-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 4))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid restrictions
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))




(def restriction-with-multiple-onproperty-triples
  `((ex/r rdf/type owl/Restriction)
     (ex/r owl/onProperty ex/BFO_0000055)
     (ex/r owl/onProperty ex/BFO_0000051)
     (ex/r owl/someValuesFrom ex/CHEBI_12345)
     (ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)))

(deftest rules-detect-multiple-onproperty
  (let [source-kb (test-kb restriction-with-multiple-onproperty-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 1))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid restriction
    ;; only one restriction is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-multiple-onproperty-with-proper-restriction
  (let [source-kb (test-kb proper-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 1))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid restrictions
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



(def restriction-with-multiple-svf-triples
  `((ex/r rdf/type owl/Restriction)
     (ex/r owl/onProperty ex/BFO_0000055)
     (ex/r owl/someValuesFrom ex/CHEBI_12345)
     (ex/r owl/someValuesFrom ex/CHEBI_7890)
     (ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)))

(deftest rules-detect-multiple-svf
  (let [source-kb (test-kb restriction-with-multiple-svf-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 2))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid restriction
    ;; only one restriction is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-multiple-svf-with-proper-restriction
  (let [source-kb (test-kb proper-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 2))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid restrictions
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))




(def redundant-restriction-triples
  `((ex/r rdf/type owl/Restriction)
     (ex/r owl/onProperty ex/BFO_0000055)
     (ex/r owl/someValuesFrom ex/CHEBI_12345)
     (ex/r2 rdf/type owl/Restriction)
     (ex/r2 owl/onProperty ex/BFO_0000055)
     (ex/r2 owl/someValuesFrom ex/CHEBI_12345)
     (ex/c rdfs/subClassOf ex/r)
     (ex/c rdfs/subClassOf ex/r2)
     (ex/r3 rdf/type owl/Restriction)
     (ex/r3 owl/onProperty ex/BFO_0000055)
     (ex/r3 owl/someValuesFrom ex/CHEBI_12345)
     (ex/c2 rdfs/subClassOf ex/r3)))

(deftest rules-detect-redundant-restriction
  (let [source-kb (test-kb redundant-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 0))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid restriction
    ;; only one restriction is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-redundant-restriction-with-proper-restriction
  (let [source-kb (test-kb proper-restriction-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 0))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid restrictions
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



;; The code fragment below is useful for debugging as it writes
    ;; triples to a local file.
    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  ;; add sample triples to the log kb
    ;  ;(dorun (map (partial add! log-kb) sample-kb-triples))
    ;  (run-forward-rule source-kb log-kb rule)
    ;  (close log-kb))
    

