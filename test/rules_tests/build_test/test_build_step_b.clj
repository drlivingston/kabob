(ns rules-tests.build_test.test_build_step_b
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! *graph*]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.input-data :refer [go-triples cl-triples]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-build-util :refer [initial-triples run-build-rule run-build-rules test-kb build-rules-step-a build-rules-step-b]]))

(def base-kb (let [source-kb (test-kb initial-triples)]
               (binding [*graph* "http://ccp-extension.owl"]
               (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
               (run-build-rules source-kb build-rules-step-a)
               source-kb))


;; ensure that the loading of the ccp-ext-ontology-triples into the specified graph worked
(deftest ccp-ext-loaded-into-graph
  (let [source-kb base-kb]
    (is (> (count (sparql-query source-kb
                                             "PREFIX obo: <http://purl.obolibrary.org/obo/>
                                             PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                             PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    select ?s ?p ?o  {graph <http://ccp-extension.owl> {?s ?p ?o}}"
                                             )) 0))))

;;;
;;; rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
;;; Test that identifiers for concepts directly related via owl:equivalentClass are linked with skos:exactMatch
;;;
(deftest step-b-equivalent-class
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rule source-kb target-kb build-rules-step-b 0)

    ;; there should be 1 skos:exactMatch triple based on the input triples.
    (is (ask target-kb '((kice/CL_0000000 skos/exactMatch kice/GO_0005623))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 1 rule output triples expected here
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))))


(deftest step-b-shared-label
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rule source-kb target-kb build-rules-step-b 1)

    ;; there should be 1 skos:exactMatch triple based on the input triples for the protein class consolidation.
    ;; since it could be either (kice/CHEBI_36080 skos/exactMatch kice/PR_000000001) or
    ;; (kice/PR_000000001 skos/exactMatch kice/CHEBI_36080) we simply query for a single skos/exactMatch triple
    (is (or (ask target-kb '((kice/PR_000000001 skos/exactMatch kice/CHEBI_36080)))
            (ask target-kb '((kice/CHEBI_36080 skos/exactMatch kice/PR_000000001)))))

    ;; there should be 1 skos:exactMatch triple based on the object property instances (part_of).
    (is (or (ask target-kb '((kice/BFO_0000050 skos/exactMatch kice/so#part_of)))
            (ask target-kb '((kice/so#part_of skos/exactMatch kice/BFO_0000050)))))

    (is (or (ask target-kb '((kice/BFO_0000050 skos/exactMatch kice/mi#part_of)))
            (ask target-kb '((kice/mi#part_of skos/exactMatch kice/BFO_0000050)))))

    (is (or (ask target-kb '((kice/mi#part_of skos/exactMatch kice/so#part_of)))
            (ask target-kb '((kice/so#part_of skos/exactMatch kice/mi#part_of)))))

    ;; there are 4 metadata triples for each rule run, so 8 metadata triples and 4 rule output triples expected here
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;            src-kb (test-kb initial-triples)]
    ;        (run-build-rule source-kb log-kb build-rules-step-b 2)
    ;        (close log-kb))


    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX obo: <http://purl.obolibrary.org/obo/>
    ;                                         PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    ;select ?prop ?label {
    ;                                      ?prop rdf:type owl:Class .
    ;                                      ?prop rdfs:label ?label
    ;                                      }"
    ;                                         )))
    ;
    ;    (prn (str "--------------------------------"))


    ))





;;;
;;; rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref
;;; Test for proper generation of skos:exactMatch links from ontology xrefs
;;;
;; THIS RULE HAS BEEN DEPRECATED -- TOO MUCH POTENTIAL FOR UNRELIABILTY
;(deftest step-b-ontology-xref
;  (let [source-kb base-kb
;        target-kb (test-kb '())]
;    (run-build-rule source-kb target-kb build-rules-step-b 0)
;
;    ;; there should be 2 skos:exactMatch triples based on the input triples.
;    (is (ask target-kb '((kice/PR_P37173 skos/exactMatch kice/UNIPROT_P37173))))
;    (is (ask target-kb '((kice/PR_P37173-1 skos/exactMatch kice/UNIPROT_P37173-1))))
;    (is (ask target-kb '((kice/CHEBI_10003 skos/exactMatch kice/DRUGBANK_DB03615))))
;
;    ;; there are 4 metadata triples for each rule run, so 8 metadata triples and 3 rule output triples expected here
;    (is (= 11 (count (query target-kb '((?/s ?/p ?/o))))))
;
;    ;(let [log-kb (output-kb "/tmp/triples.nt")
;    ;      src-kb (test-kb initial-triples)]
;    ;
;    ;  (run-build-rule source-kb log-kb build-rules-step-b 0)
;    ;  (close log-kb))
;
;    ))


;; TODO: the one rule not being tested is the chebi-drugbank skos exactMatch rule. To test, we would need to add DrugBank data and chebi concepts with drugbank xrefs.

