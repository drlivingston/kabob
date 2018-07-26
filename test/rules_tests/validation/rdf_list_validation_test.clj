(ns rules-tests.validation.rdf-list-validation-test
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


(def validation-rules '("rules/validation/valid_owl/list/rdf_list_has_multiple_rdf_first.clj"
                         "rules/validation/valid_owl/list/rdf_list_has_multiple_rdf_rest.clj"
                         "rules/validation/valid_owl/list/rdf_list_missing_first_and_rest.clj"
                         "rules/validation/valid_owl/list/rdf_list_missing_rdf_first.clj"
                         "rules/validation/valid_owl/list/rdf_list_missing_rdf_rest.clj"
                         "rules/validation/valid_owl/list/rdf_list_not_rest_to_nil.clj"
                         "rules/validation/valid_owl/list/rdf_list_does_not_end_with_nil.clj"))


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



(def proper-list-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/rest ex/l2)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest rdf/nil)))

(def list-missing-first-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/rest ex/l2)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/rest rdf/nil)))


(deftest rules-detect-missing-first
  (let [source-kb (test-kb list-missing-first-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 3))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid list
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-missing-first-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 3))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



(def list-missing-rest-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest rdf/nil)))

(deftest rules-detect-missing-rest
  (let [source-kb (test-kb list-missing-rest-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 4))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid list
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-missing-rest-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 4))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



(def list-missing-first-and-rest-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest rdf/nil)))

(deftest rules-detect-missing-first-and-rest
  (let [source-kb (test-kb list-missing-first-and-rest-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 2))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid list
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-missing-first-and-rest-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 2))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))




(def list-with-multiple-first-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/first ex/CHEBI_45678)
     (ex/l1 rdf/rest ex/l2)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest rdf/nil)))


(deftest rules-detect-multiple-first
  (let [source-kb (test-kb list-with-multiple-first-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 0))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid list
    ;; only one list is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-multiple-first-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 0))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))



(def list-with-multiple-rest-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/rest ex/l2)
     (ex/l1 rdf/rest ex/l3)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest rdf/nil)
     (ex/l3 rdf/type rdf/List)
     (ex/l3 rdf/first ex/CHEBI_45678)
     (ex/l3 rdf/rest rdf/nil)))

(deftest rules-detect-multiple-rest
  (let [source-kb (test-kb list-with-multiple-rest-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 1))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid list
    ;; only one list is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-multiple-rest-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 1))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))




(def list-connects-to-nil-with-not-rest-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/rest ex/l2)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/rest ex/CHEBI_12345)
     (ex/l2 rdf/first rdf/nil)))

(deftest rules-detect-list-connects-to-nil-without-rest
  (let [source-kb (test-kb list-connects-to-nil-with-not-rest-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 5))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 1 however, for the detected invalid list
    (is (= 1 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-list-connects-to-nil-without-rest-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 5))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))


(def list-ends-with-nil-triples
  `((ex/l1 rdf/type rdf/List)
     (ex/l1 rdf/first ex/CHEBI_7890)
     (ex/l1 rdf/rest ex/l2)
     (ex/l2 rdf/type rdf/List)
     (ex/l2 rdf/first ex/CHEBI_12345)
     (ex/l2 rdf/rest ex/CHEBI_45667)))

(deftest rules-detect-list-end-not-nil
  (let [source-kb (test-kb list-ends-with-nil-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 6))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 2 however, for the detected invalid list
    ;; only one list is invalid, however the query returns 2 hits.
    ;; We could use 'distinct' in the query to return a single hit, however that tends to slow
    ;; down the query and we are only interested in knowing if there are >0, so
    ;; we currently don't use 'distinct'
    (is (= 2 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

(deftest rules-detect-list-end-not-nil-with-proper-list
  (let [source-kb (test-kb proper-list-triples)
        target-kb (test-kb '())]

    (run-rule source-kb target-kb (nth validation-rules 6))
    ;; no new triples should be added, only the 4 rule metadata triples
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ;; the reported triple count should be 0 b/c there are no invalid lists
    (is (= 0 (get (first (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/count)))) '?/count)))))

;; The code fragment below is useful for debugging as it writes
    ;; triples to a local file.
    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  ;; add sample triples to the log kb
    ;  ;(dorun (map (partial add! log-kb) sample-kb-triples))
    ;  (run-forward-rule source-kb log-kb rule)
    ;  (close log-kb))
    

