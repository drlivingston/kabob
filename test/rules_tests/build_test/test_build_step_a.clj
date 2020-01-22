(ns rules-tests.build_test.test_build_step_a
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! *graph*]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-build-util :refer [initial-triples run-build-rule run-build-rules test-kb build-rules-step-a
                                                            go-bp-concepts go-cc-concepts cl-concepts pr-concepts chebi-concepts
                                                            gaz-concepts hgnc-concepts concepts object-properties so-concepts mi-concepts
                                                            ncbitaxon-concepts obi-concepts pato-concepts bfo-concepts uberon-concepts
                                                            ]]))

;;;;
;;;; pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_ontology_root_identifier
;;;; Test that the ontology root concepts are properly identified
;;;;
;(deftest pre-ice-load-step-aa
;  (let [source-kb (test-kb initial-triples)
;        target-kb (test-kb '())
;        concepts-only-hgnc (filter #(contains? hgnc-concepts %) concepts)]
;
;    (run-build-rule source-kb target-kb build-rules-step-a 0)
;
;
;    (is (ask target-kb '((ccp/HGNC_11773 rdfs/subClassOf ccp/IAO_EXT_0000088) ;; ccp:ontology_concept_identifier
;                          (ccp/HGNC_11773 rdfs/subClassOf ccp/IAO_EXT_0000185) ;; ccp:hgnc_gene_identifier
;                          (ccp/HGNC_11773 obo/IAO_0000219 obo/HGNC_11773))))
;
;    ;;; there should be two triples for each ontology concept
;    ;(doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)
;    ;                                obo-id (symbol "hgnc_pr" (str "gene_symbol_report?hgnc_id=" (clojure.string/replace concept #"HGNC_" "")))]
;    ;                            (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000088) ;; ccp:ontology_concept_identifier
;    ;                                                  (~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000185)
;    ;                                                  (~ccp-id obo/IAO_0000219 ~obo-id))))))
;    ;            concepts-only-hgnc))
;
;    ;; there are 4 metadata triples for each rule run, so 4*13 metadata triples and 3 rule output triples per non-obo concept
;    (is (= (+ (* 4 13) (* 3 (count concepts-only-hgnc)))
;           (count (query target-kb '((?/s ?/p ?/o))))))
;
;    (let [log-kb (output-kb "/tmp/triples.nt")
;          src-kb (test-kb initial-triples)]
;      (run-build-rule source-kb log-kb build-rules-step-a 0)
;      (close log-kb))
;
;    ))



;;;
;;; pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept
;;; for each non-root ontology concept, create an identifier and type it as an 'ontology concept identifier'
;;;
(deftest pre-ice-load-step-ab
  (let [source-kb (test-kb initial-triples)
        target-kb (test-kb '())]

    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))

    (run-build-rule source-kb target-kb build-rules-step-a 0)

    ;; there should be two triples for each ontology concept
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000088) ;; ccp:ontology_concept_identifier
                                                      (~ccp-id obo/IAO_0000219 ~obo-id))))))
                concepts))

    (doall (map (fn [prop] (let [ccp-id (symbol "kice" prop)
                                 obo-id (symbol "obo" prop)]
                             (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000306) ;; ccp:object_property_identifier
                                                   (~ccp-id obo/IAO_0000219 ~obo-id))))))
                object-properties))

    (prn (str "======================CONCEPT COUNT: " (count concepts)))
    (prn (str "======================PROPERTY COUNT: " (count object-properties)))

    ;; there are 4 metadata triples for each rule run so 2*4=8 metadata triples and 75+82=157 rule output triples for the
    ;; concepts and 50 rule output triples for the object properties expected here
    (is (= (+ 8 (* 2 (count concepts)) (* 2 (count object-properties)))
           (count (query target-kb '((?/s ?/p ?/o))))
           ))



    (let [log-kb (output-kb "/tmp/triples.nt")
          src-kb (test-kb initial-triples)]
      (run-build-rule source-kb log-kb build-rules-step-a 1)
      (close log-kb))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb initial-triples)]
    ;
    ;  (run-build-rule source-kb log-kb build-rules-step-a 1)
    ;
    ;  ;; add sample triples to the log kb
    ;  ;(dorun (map (partial add! log-kb) initial-triples))
    ;  ;
    ;  ;(run-build-rule src-kb log-kb 0)
    ;  ;(run-build-rule src-kb src-kb 0)
    ;  ;
    ;  ;(run-build-rule src-kb log-kb 1)
    ;  ;(run-build-rule src-kb src-kb 1)
    ;  ;(run-build-rule source-kb log-kb build-rules-step-a 3)
    ;  (close log-kb))
    ))



;;;
;;; rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing
;;; test the explicit subclassing of the ontology concept identifiers
;;;
(deftest pre-ice-load-step-ac
  (let [source-kb (test-kb initial-triples)
        target-kb (test-kb '())]

    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))

    (run-build-rules source-kb build-rules-step-a 0)
    (run-build-rule source-kb target-kb build-rules-step-a 1)

    ;; there should be one triples for each ontology concept
    ;; go-bp
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000103)))))) ;; ccp:go_bp_ontology_identifier
                go-bp-concepts))

    ;; go-cc
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000200)))))) ;; ccp:go_cc_ontology_identifier
                go-cc-concepts))

    ;; cl
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000202)))))) ;; ccp:cell_ontology_identifier
                cl-concepts))

    ;; ncbitaxon
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0001832)))))) ;; ccp:ncbitaxon_ontology_taxon_identifier
                ncbitaxon-concepts))

    ;; chebi
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000198)))))) ;; ccp:chebi_ontology_identifier
                chebi-concepts))

    ;; pr
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000112)))))) ;; ccp:protein_ontology_identifier
                pr-concepts))

    ;; so
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000203)))))) ;; ccp:sequence_ontology_identifier
                so-concepts))

    ;; bfo
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000205)))))) ;; ccp:bfo_ontology_identifier
                bfo-concepts))

    ;; obi
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0001847)))))) ;; ccp:obi_ontology_identifier
                obi-concepts))

    ;; pato
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000211)))))) ;; ccp:pato_ontology_identifier
                pato-concepts))

    ;;; gaz
    ;(doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
    ;                                obo-id (symbol "obo" concept)]
    ;                            (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0001704)))))) ;; ccp:gazetteer_ontology_identifier
    ;            gaz-concepts))

    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0000201)))))) ;; ccp:uberon_ontology_identifier
                uberon-concepts))


    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((~ccp-id rdfs/subClassOf ccp/IAO_EXT_0001262)))))) ;; ccp:mi_ontology_identifier
                mi-concepts))


    ;; there are 4 metadata triples for each rule run so 24*4=92 metadata triples and 36 rule output triples for the
    ;; concepts and 0 rule output triples for the object properties expected here
    (is (= (+ 96 (count (distinct (concat go-bp-concepts go-cc-concepts cl-concepts chebi-concepts pr-concepts hgnc-concepts
                                          so-concepts bfo-concepts obi-concepts
                                          pato-concepts ;gaz-concepts
                                          ncbitaxon-concepts uberon-concepts mi-concepts))))
           (count (query target-kb '((?/s ?/p ?/o))))))

    (let [log-kb (output-kb "/tmp/triples.nt")
          src-kb (test-kb initial-triples)]

      (run-build-rule source-kb log-kb build-rules-step-a 1)
      (close log-kb))
    ))



;;;
;;; rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen
;;; test creation of the ontology concept and property ICE records
;;;
(deftest pre-ice-load-step-ad
  (let [source-kb (test-kb initial-triples)
        target-kb (test-kb '())]

    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))

    (run-build-rules source-kb build-rules-step-a 1)
    (run-build-rule source-kb target-kb build-rules-step-a 2)

    ;; there should be one record for each ontology concept
    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask target-kb `((?/record rdf/type ccp/IAO_EXT_0000191)
                                                      (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
                                                      (?/id_field_value rdf/type ccp/IAO_EXT_0000034) ; CCP:identifier_field_value
                                                      (?/id_field_value rdf/type ~ccp-id))))))
                concepts))


    (doall (map (fn [prop] (let [ccp-id (symbol "kice" prop)
                                 obo-id (symbol "obo" prop)]
                             (is (ask target-kb `((?/record rdf/type ccp/IAO_EXT_0000310)
                                                   (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
                                                   (?/id_field_value rdf/type ccp/IAO_EXT_0000314) ; CCP:identifier_field_value
                                                   (?/id_field_value rdf/type ~ccp-id))))))
                object-properties))



    ;; there are 4 metadata triples for each rule run so 2*4=8 metadata triples and 407 rule output triples for the
    ;; 37 concepts and 253 rule output triples for the 23 object properties expected here. The expected number ends up
    ;; being 656 instead of 660 b/c the object properties don't have definitions (so they all reference a field value
    ;; with value 'no definition' -- 2 triples instead of more if their definitions were unique) and b/c there are two
    ;; concepts with the label 'cell' (again, 2 triples that are 'shared' instead of 4 if they were unique)
    ;; and 2 concepts with the label "protein".

    (prn (str "concepts+properties: " (+ (count concepts) (count object-properties))))

    ;(is (= (+ 8 (* 11 (count concepts)) (* 11 (count object-properties)))
    ;       (count (query target-kb '((?/s ?/p ?/o))))))

    ;;TODO: come back to this one. the expected count 1141 doesn't equal the observed cound 1139.

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb initial-triples)]
    ;
    ;  (run-build-rule source-kb log-kb build-rules-step-a 3)
    ;  (close log-kb))
    ))


