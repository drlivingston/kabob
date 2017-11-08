(ns rules-tests.build_test.test_build_step_c
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-ca build-rules-step-cb build-rules-step-cc]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))


(deftest biopax2ice-step-ca
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rule source-kb target-kb build-rules-step-ca 0)

    ;; a single protein record should have been created
    (is (= 1 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001513))))))

    (is (ask target-kb '((?/prot_record obo/IAO_0000219 ?/prot) ;; obo:denotes
             (?/prot_record rdf/type ccp/IAO_EXT_0001513)  ;; Reactome protein record
             (?/prot_record obo/BFO_0000051 ?/xref_record)  ;; has_part
             (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; Reactome xref field value
             (?/xref_record rdf/type ccp/IAO_EXT_0001572) ;; Reactome unification xref
             (?/xref_record obo/BFO_0000051 ?/xref_db_field)
             (?/xref_db_field rdf/type ccp/IAO_EXT_0001519)  ;; Reactome unification xref db field value
             (?/xref_record obo/BFO_0000051 ccp/REACTOME_R-HSA-379666)
             (ccp/REACTOME_R-HSA-379666 rdf/type ccp/IAO_EXT_0001520)  ;; Reactome unification xref database id field value
             (ccp/REACTOME_R-HSA-379666 rdf/type ccp/IAO_EXT_0001517)  ;; Reactome identifier field value
             (ccp/REACTOME_R-HSA-379666 rdfs/label ["R-HSA-379666" "en"]))))
  ))


(deftest biopax2ice-step-b-add-cellular-location
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 0)

    ;; a single cellular location field value record should have been created
    (is (= 1 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001521))))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/cellular_location_record)  ;; has_part
                          (?/cellular_location_record rdf/type ccp/IAO_EXT_0001521) ;; Reactome cellular location field value
                          (?/cellular_location_record rdf/type ccp/IAO_EXT_0001584) ;; Reactome cellular location vocabulary record
                          (?/cellular_location_record obo/BFO_0000051 ?/go_xref_record)  ;; has_part
                          (?/go_xref_record rdf/type ccp/IAO_EXT_0001572) ;; unification xref record
                          (?/go_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field value
                          (?/go_xref_record obo/BFO_0000051 ?/go_xref_db_field)  ;; has_part
                          (?/go_xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; xref database field
                          (?/go_xref_record obo/BFO_0000051 ccp/GO_0005829)  ;; has_part
                          (ccp/GO_0005829 rdf/type ccp/IAO_EXT_0001520) ;; xref database id field
                          (ccp/GO_0005829 rdfs/label ["GO:0005829" "en"])
                          (ccp/GO_0005829 rdf/type ccp/IAO_EXT_0000200))))
    ))


(deftest biopax2ice-step-b-add-display-name
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 1)

    ;; a single display name field value record should have been created
    (is (= 1 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001526))))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/name_field)  ;; has_part
                          (?/name_field rdf/type ccp/IAO_EXT_0001526) ;; Reactome display name field value
                          (?/name_field rdfs/label ["AARS" "en"]))))
    ))

;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-b-add-located-modification-feature
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 2)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001527))))))

    ))


;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-b-add-member-physical-entity-reference-feature
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 3)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001528))))))

    ))

(deftest biopax2ice-step-b-add-name
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 4)

    ;; a single display name field value record should have been created
    (is (= 1 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001525))))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/name_field)  ;; has_part
                          (?/name_field rdf/type ccp/IAO_EXT_0001525) ;; Reactome display name field value
                          (?/name_field rdfs/label ["Alanyl-tRNA synthetase" "en"]))))
    ))


;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-b-add-removed-fragment-feature-reference-feature
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 5)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001528))))))

    ))


(deftest biopax2ice-step-b-add-uniprot-identifier-field
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 6)

    ;; a single display name field value record should have been created
    (is (= 1 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001518))))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/entity_record)  ;; has_part
                          ;; TODO: the dc/source line below should be replaced with a proper field value
                          (?/entity_record dc/source ?/entity_ref)
                          (?/entity_record rdf/type ccp/IAO_EXT_0001518) ;; Reactome entity reference field value
                          (?/entity_record rdf/type ccp/IAO_EXT_0001551) ;; Reactome protein reference record

                          (?/entity_record obo/BFO_0000051 ?/entity_xref_record)  ;; has_part
                          (?/entity_xref_record rdf/type ccp/IAO_EXT_0001572) ;; unification xref record
                          (?/entity_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field value
                          (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_db_field)
                          (?/entity_xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; xref database field

                          (?/entity_xref_record obo/BFO_0000051 ccp/UNIPROT_P49588)
                          (ccp/UNIPROT_P49588 rdf/type ccp/IAO_EXT_0001520) ;; xref database id field
                          (ccp/UNIPROT_P49588 rdfs/label ["P49588" "en"])
                          (ccp/UNIPROT_P49588 rdf/type ccp/IAO_EXT_0000184))))
    ))


;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-b-add-uniprot-isoform-identifier-field
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 7)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001518))))))
    ))

;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-b-add-unlocated-modification-feature
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rule source-kb target-kb build-rules-step-cb 8)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001527))))))

    ))

;; TODO: add appropriate data to the input-data so the below rule will fire
(deftest biopax2ice-step-c-add-display-name-from-protein-reference
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rules source-kb build-rules-step-cb)
    (run-build-rule source-kb target-kb build-rules-step-cc 0)

    ;; a single display name field value record should have been created
    (is (= 0 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001526))))))

    ))

(deftest biopax2ice-step-c-add-name-from-protein-reference
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rules source-kb build-rules-step-cb)
    (run-build-rule source-kb target-kb build-rules-step-cc 1)

    ;; a single display name field value record should have been created
    (is (= 2 (count (query target-kb '((?/s rdf/type ccp/IAO_EXT_0001525))))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/name_field)  ;; has_part
                          (?/name_field rdf/type ccp/IAO_EXT_0001525) ;; Reactome display name field value
                          (?/name_field rdfs/label ["AARS" "en"]))))

    (is (ask target-kb '((?/prot_record obo/BFO_0000051 ?/name_field)  ;; has_part
                          (?/name_field rdf/type ccp/IAO_EXT_0001525) ;; Reactome display name field value
                          (?/name_field rdfs/label ["UniProt:P49588 AARS" "en"]))))

    ))
