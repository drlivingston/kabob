(ns rules-tests.build_test.test_build_step_d
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-c build-rules-step-da build-rules-step-db]]))




;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/drugbank_drug_identifier_exact_match
;;; Test the generation of skos:exactMatch links to DrugBank identifiers based on the DrugBank data source
;;;
;;; TODO: add DrugBank ICE triples to the input_data to test this rule.
(deftest step-da-drugbank-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 0)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))))

;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/hgnc_gene_symbol_entrez_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
(deftest step-da-hgnc-symbol-to-ncbi-gene-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 1)

    (is (ask target-kb '((ccp/HGNC_TGFBR2 skos/exactMatch ccp/NCBI_GENE_7048))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))))


;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/hgnc_gene_symbol_hgnc_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
(deftest step-da-hgnc-symbol-to-hgnc-gene-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 2)

    (is (ask target-kb '((ccp/HGNC_TGFBR2 skos/exactMatch ccp/HGNC_11773))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))
    ))

;;;;
;;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/hgnc_gene_symbol_refseq_identifier_exact_match
;;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;;
;(deftest step-d-hgnc-symbol-to-refseq-identifier-exact-match
;  (let [source-kb (test-kb initial-plus-ice-triples)
;        target-kb (test-kb '())]
;    (run-build-rules source-kb build-rules-step-a)
;    (run-build-rules source-kb build-rules-step-b)
;    (run-build-rules source-kb build-rules-step-c)
;    (run-build-rule source-kb target-kb build-rules-step-da 3)
;
;    ;;(is (ask target-kb '((ccp/HGNC_TGFBR2 skos/exactMatch ccp/HGNC_11773))))
;
;    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
;    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
;    ))



;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/hgnc_gene_symbol_supplied_entrez_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
(deftest step-da-hgnc-symbol-to-supplied-ncbi-gene-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 3)

    (is (ask target-kb '((ccp/HGNC_TGFBR2 skos/exactMatch ccp/NCBI_GENE_7048))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))
    ))


;;;;
;;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/hgnc_gene_symbol_supplied_refseq_identifier_exact_match
;;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;;
;(deftest step-d-hgnc-symbol-to-supplied-refseq-identifier-exact-match
;  (let [source-kb (test-kb initial-plus-ice-triples)
;        target-kb (test-kb '())]
;    (run-build-rules source-kb build-rules-step-a)
;    (run-build-rules source-kb build-rules-step-b)
;    (run-build-rules source-kb build-rules-step-c)
;    (run-build-rule source-kb target-kb build-rules-step-da 6)
;
;    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
;    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
;    ))


;;;;
;;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/mgi_gene_entrez_identifier_exact_match
;;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;;
;;;; TODO: convert this rule to use sparql-string
;(deftest step-d-mgi-to-ncbi-gene-identifier-exact-match
;  (let [source-kb (test-kb initial-plus-ice-triples)
;        target-kb (test-kb '())]
;    ;(run-build-rules source-kb build-rules-step-a 3)
;    ;(run-build-rules source-kb build-rules-step-b 2)
;    ;(run-build-rules source-kb build-rules-step-c 3)
;    ;(run-build-rule source-kb target-kb build-rules-step-da 10)
;    ;
;    ;;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
;    ;(is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
;    ))


;;;;
;;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/ncbi_gene_identifier_exact_match
;;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;;
;(deftest step-d-ncbi-gene-to-other-identifier-exact-match
;  (let [source-kb (test-kb initial-plus-ice-triples)
;        target-kb (test-kb '())]
;    (run-build-rules source-kb build-rules-step-a)
;    (run-build-rules source-kb build-rules-step-b)
;    (run-build-rules source-kb build-rules-step-c)
;    (run-build-rule source-kb target-kb build-rules-step-da 11)
;
;    (is (ask target-kb '((ccp/NCBI_GENE_7048 skos/exactMatch ccp/HGNC_11773))))
;
;    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 1 rule output triples expected here
;    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))
;
;
;    ;(let [log-kb (output-kb "/tmp/triples.nt")
;    ;      src-kb (test-kb initial-plus-ice-triples)]
;    ;
;    ;  ;; add sample triples to the log kb
;    ;  (run-build-rules src-kb build-rules-step-a)
;    ;  (run-build-rules src-kb build-rules-step-b)
;    ;  (run-build-rules src-kb build-rules-step-c)
;    ;  (run-build-rule src-kb log-kb build-rules-step-da 11)
;    ;  (close log-kb))
;
;    ))



;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/pharmgkb_drug_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
;;; TODO: add test triples to input_data to test this rule
(deftest step-da-pharmgkb-drug-to-other-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 4)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ))




;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/pharmgkb_gene_entrez_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
;;; TODO: add test triples to input_data to test this rule
(deftest step-da-pharmgkb-gene-to-ncbi-gene-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 5)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ))



;;;
;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/pro_protein_identifier_exact_match
;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;
;;; TODO: add 'exact' info to the record in order to test this rule
(deftest step-da-pr-to-other-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 6)

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
    ))



;
;;;;
;;;; rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/rgd_gene_entrez_identifier_exact_match
;;;; Test the generation of skos:exactMatch links between HGNC symbols and NCBI gene identifiers based on the HGNC data source
;;;;
;;;; TODO: convert rule to use sparql-string
;(deftest step-d-rgd-to-ncbi-gene-identifier-exact-match
;  (let [source-kb (test-kb initial-plus-ice-triples)
;        target-kb (test-kb '())]
;    ;(run-build-rules source-kb build-rules-step-a 3)
;    ;(run-build-rules source-kb build-rules-step-b 2)
;    ;(run-build-rules source-kb build-rules-step-c 3)
;    ;(run-build-rule source-kb target-kb build-rules-step-da 16)
;    ;
;    ;;; there are 4 metadata triples for each rule run, so 4 metadata triples and 0 rule output triples expected here
;    ;(is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))
;    ))



(deftest step-da-uniprot-isoform-to-refseq-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 7)

    (is (ask target-kb '((ccp/UNIPROT_P37173-2 skos/exactMatch ccp/REFSEQ_NP_001020018))))
    (is (ask target-kb '((ccp/UNIPROT_P37173-1 skos/exactMatch ccp/REFSEQ_NP_003233))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 2 rule output triples expected here
    (is (= 6 (count (query target-kb '((?/s ?/p ?/o))))))))


(deftest step-da-uniprot-primary-accession-to-entry-name-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rule source-kb target-kb build-rules-step-da 8)

    (is (ask target-kb '((ccp/UNIPROT_P37173 skos/exactMatch ccp/UNIPROTENTRYNAME_TGFR2_HUMAN))))
    (is (ask target-kb '((ccp/UNIPROT_P62258 skos/exactMatch ccp/UNIPROTENTRYNAME_1433E_HUMAN))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 2 rule output triples expected here
    (is (= 6 (count (query target-kb '((?/s ?/p ?/o))))))





    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;                                          prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                          prefix obo: <http://purl.obolibrary.org/obo/>
    ;                                          prefix owl: <http://www.w3.org/2002/07/owl#>
    ;                                          prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    ;                                          select * {
    ;                                            ?db_ref_record rdf:type ccp:IAO_EXT_0000955 . # CCP:uniprot_database_reference_record
    ;                                            ?db_ref_record rdf:type ccp:IAO_EXT_0000940 . # CCP:uniprot_kb_record_database_reference_field_value
    ;                                            ?db_ref_record obo:BFO_0000051 ?id_field_value .
    ;                                            ?id_field_value rdf:type ?refseq_id .
    ;                                            ?refseq_id rdfs:subClassOf ccp:IAO_EXT_0001638 . # ccp:refseq_protein_identifier
    ;                                            ?db_ref_record obo:BFO_0000051 ?molecule_record .
    ;                                            ?molecule_record rdf:type ccp:IAO_EXT_0001712 . # CCP:uniprot_database_reference_record___molecule_field_value
    ;                                            ?molecule_record rdf:type ccp:IAO_EXT_0000961 . # CCP:uniprot_molecule_type_record
    ;                                            ?molecule_record obo:BFO_0000051 ?id_field .
    ;                                            ?id_field rdf:type ccp:IAO_EXT_0001147 . # CCP:uniprot_molecule_type_record___identifier_field_value
    ;                                            ?id_field rdf:type ?uniprot_id .
    ;                                            ?uniprot_id rdfs:subClassOf ccp:IAO_EXT_0001495 . # CCP:uniprot_identifier
    ;      }"
    ;                                         )))
    ;
    ;    (prn (str "--------------------------------"))


    ;;; The code fragment below is useful for debugging as it writes
    ;;; triples to a local file.
    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb initial-plus-ice-triples)]
    ;
    ;  ;; add sample triples to the log kb
    ;  (dorun (map (partial add! log-kb) initial-plus-ice-triples))
    ;
    ;  (run-build-rule src-kb log-kb build-rules-step-a 0)
    ;  (run-build-rule src-kb log-kb build-rules-step-a 1)
    ;  (run-build-rule src-kb log-kb build-rules-step-a 2)
    ;  (run-build-rule src-kb log-kb build-rules-step-a 3)
    ;  (run-build-rules src-kb build-rules-step-a 3)
    ;
    ;  (run-build-rule src-kb log-kb build-rules-step-b 0)
    ;  (run-build-rule src-kb log-kb build-rules-step-b 1)
    ;  (run-build-rule src-kb log-kb build-rules-step-b 2)
    ;  (run-build-rules src-kb build-rules-step-b 2)
    ;
    ;  (run-build-rule src-kb log-kb build-rules-step-c 0)
    ;  (run-build-rule src-kb log-kb build-rules-step-c 1)
    ;  (run-build-rule src-kb log-kb build-rules-step-c 2)
    ;  (run-build-rule src-kb log-kb build-rules-step-c 3)
    ;  (run-build-rules src-kb build-rules-step-c 3)
    ;
    ;  ;
    ;  ;(run-build-rule src-kb log-kb 0)
    ;  ;(run-build-rule src-kb src-kb 0)
    ;  ;
    ;  ;(run-build-rule src-kb log-kb 1)
    ;  ;(run-build-rule src-kb src-kb 1)
    ;  ;(run-build-rule source-kb log-kb build-rules-step-a 3)
    ;  (close log-kb))

    ))



(deftest step-db-uniprot-to-refseq-identifier-exact-match
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rule source-kb target-kb build-rules-step-db 0)

    (is (ask target-kb '((ccp/REFSEQ_NP_006752 skos/exactMatch ccp/UNIPROT_P62258))))

    ;; there are 4 metadata triples for each rule run, so 4 metadata triples and 2 rule output triples expected here
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-db 0)
      (close log-kb))


    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;                                          prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                          prefix obo: <http://purl.obolibrary.org/obo/>
    ;                                          prefix owl: <http://www.w3.org/2002/07/owl#>
    ;                                          prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    ;                                          prefix skos: <http://www.w3.org/2004/02/skos/core#>
    ;                                          select * {
    ;                                            ?record rdf:type ccp:IAO_EXT_0000692 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record
    ;                                            ?record obo:BFO_0000051 ?refseq_protein_identifier_field_value .
    ;                                            ?refseq_protein_identifier_field_value rdf:type ccp:IAO_EXT_0000927 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_refseq_protein_identifier_field_value
    ;                                            ?refseq_protein_identifier_field_value rdf:type ?refseq_identifier .
    ;                                            ?refseq_identifier rdfs:subClassOf ccp:IAO_EXT_0001638 . # CCP:refseq_protein_identifier
    ;                                            ?record obo:BFO_0000051 ?uniprot_identifier_field_value .
    ;                                            ?uniprot_identifier_field_value rdf:type ccp:IAO_EXT_0000928 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_uniprot_identifier_field_value
    ;                                            ?uniprot_identifier_field_value rdf:type ?uniprot_identifier .
    ;                                            ?uniprot_identifier rdfs:subClassOf ccp:IAO_EXT_0000184 . # CCP:uniprot_identifier
    ;
    ;                                        # only include matches if the refseq identifier is not already in a skos:exactMatch relation
    ;                                                     # b/c if it is, then it is already directly linked to a uniprot isoform id
    ;                                                     minus {?refseq_identifier skos:exactMatch ?other_id}
    ;                                                     minus {?other_id skos:exactMatch ?refseq_identifier}
    ;                                         }"
    ;                                         )))
    ;
    ;    (prn (str "--------------------------------"))

    ))


