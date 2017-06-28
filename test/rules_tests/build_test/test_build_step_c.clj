(ns rules-tests.build_test.test_build_step_c
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules test-kb build-rules-step-a build-rules-step-b build-rules-step-c]]))

;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_protein_identifier_typing
;;; Test that refseq protein identifiers get appropriately typed (or subclassed in this case).
;;;
(deftest step-c-refseq-protein
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-c 2)

    (is (ask target-kb '((ccp/REFSEQ_NP_001020018 rdfs/subClassOf ccp/IAO_EXT_0001638)))) ;; ccp:refseq_protein_identifier
    (is (ask target-kb '((ccp/REFSEQ_NP_003233 rdfs/subClassOf ccp/IAO_EXT_0001638)))) ;; ccp:refseq_protein_identifier
    (is (ask target-kb '((ccp/REFSEQ_NP_006752 rdfs/subClassOf ccp/IAO_EXT_0001638)))) ;; ccp:refseq_protein_identifier

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 3 rule output triples expected here
    (is (= 7 (count (query target-kb '((?/s ?/p ?/o))))))))


;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_genomic_identifier_typing
;;; Test that refseq genomic identifiers get appropriately typed (or subclassed in this case).
;;;
(deftest step-c-refseq-genomic
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-c 0)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))))


;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_mrna_identifier_typing
;;; Test that refseq genomic identifiers get appropriately typed (or subclassed in this case).
;;;
(deftest step-c-refseq-mrna
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-c 1)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))))


;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_rna_identifier_typing
;;; Test that refseq genomic identifiers get appropriately typed (or subclassed in this case).
;;;
(deftest step-c-refseq-rna
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-c 3)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))))


;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/uniprot_protein_alternative_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
(deftest step-c-obsolete-uniprot-identifier-typing
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-c 4)

    (is (ask target-kb '((ccp/UNIPROT_B4DTV5 rdf/type ccp/IAO_EXT_0001711)))) ;; ccp:obsolete_identifier
    (is (ask target-kb '((ccp/UNIPROT_Q15580 rdf/type ccp/IAO_EXT_0001711)))) ;; ccp:obsolete_identifier
    (is (ask target-kb '((ccp/UNIPROT_Q6DKT6 rdf/type ccp/IAO_EXT_0001711)))) ;; ccp:obsolete_identifier
    (is (ask target-kb '((ccp/UNIPROT_Q99474 rdf/type ccp/IAO_EXT_0001711)))) ;; ccp:obsolete_identifier

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 4 rule output triples expected here
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))
    ))