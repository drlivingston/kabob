(ns rules-tests.build_test.test_build_step_hd_goa
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf *graph*]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-ca build-rules-step-cb build-rules-step-cc
                                                            build-rules-step-da build-rules-step-db build-rules-step-dc
                                                            build-rules-step-fa
                                                            build-rules-step-fb
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gca
                                                            build-rules-step-gcb build-rules-step-gcc
                                                            build-rules-step-ha build-rules-step-hb build-rules-step-hca
                                                            build-rules-step-hcb build-rules-step-hcc build-rules-step-hcd
                                                            build-rules-step-hd-goa
                                                            validation-rules-list validation-rules-restriction
                                                            expected-subpropertyof-links expected-inverseof-links
                                                            expected-subclassof-links expected-disjointwith-links
                                                            expected-restrictions expected-restrictions-in-lists
                                                            expected-equivalent-class-links expected-rdfs-domain-links
                                                            expected-rdfs-range-links]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))

(def base-kb (let [source-kb (test-kb initial-plus-ice-triples)]
               (binding [*graph* "http://ccp-extension.owl"]
                 (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
               (run-build-rules source-kb build-rules-step-a)
               (run-build-rules source-kb build-rules-step-b)
               (run-build-rules source-kb build-rules-step-ca)
               (run-build-rules source-kb build-rules-step-cb)
               (run-build-rules source-kb build-rules-step-cc)
               (run-build-rules source-kb build-rules-step-da)
               (run-build-rules source-kb build-rules-step-db)
               (run-build-rules source-kb build-rules-step-dc)

               (with-tmp-dir
                 ;; generate identifier set ntriple files and load into the source-kb
                 (generate-all-id-sets source-kb (str tmp-dir "/"))
                 (prn (str "PRINTING FILE LIST: " (count (get-only-files tmp-dir))))
                 (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                               (load-rdf source-kb (java.util.zip.GZIPInputStream.
                                                     (clojure.java.io/input-stream
                                                       f)) :ntriple))
                             (get-only-files tmp-dir))))
               (run-build-rules source-kb build-rules-step-fa)
               (run-build-rules source-kb build-rules-step-fb)
               (run-build-rules source-kb build-rules-step-ga)
               (run-build-rules source-kb build-rules-step-gb)
               (run-build-rules source-kb build-rules-step-gca)
               (run-build-rules source-kb build-rules-step-gcb)
               (run-build-rules source-kb build-rules-step-gcc)
               (run-build-rules source-kb build-rules-step-ha)
               (run-build-rules source-kb build-rules-step-hb)
               (run-build-rules source-kb build-rules-step-hca)
               (run-build-rules source-kb build-rules-step-hcb)
               (run-build-rules source-kb build-rules-step-hcc)
               (run-build-rules source-kb build-rules-step-hcd)
               source-kb))


;;; Test that all links get transferred to bioworld
(deftest step-hd-goa-bp-test
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (run-build-rule source-kb source-kb build-rules-step-hd-goa 0)

    (run-build-rule source-kb target-kb build-rules-step-hd-goa 0)

    (is (ask source-kb '((kice/GO_0019226 obo/IAO_0000219 ?/biological_process)
                          (?/biological_process_sc rdfs/subClassOf ?/biological_process)
                          ;; create a subclass of the participating bioentity
                          (kice/UNIPROT_P37173 obo/IAO_0000219 ?/participating_bioentity)
                          (?/bioentity_sc rdfs/subClassOf ?/participating_bioentity)

                          ;; create a has_participant restriction
                          (?/participation_restriction rdf/type owl/Restriction)
                          (?/participation_restriction owl/onProperty ?/has_participant_relation) ; RO:has_participant
                          (kice/RO_0000057 obo/IAO_0000219 ?/has_participant_relation)
                          (?/participation_restriction owl/someValuesFrom ?/bioentity_sc)

                          ;; connect the process subclass to the participation restriction
                          (?/biological_process_sc rdfs/subClassOf ?/participation_restriction)

                          ;; provenance: connect the record to the process subclass
                          (?/record obo/IAO_0000219 ?/biological_process_sc)
                          (?/record rdf/type ccp/IAO_EXT_0000007)

                          )))

    ;; a single restriction per go-bp annotation are created by the go-bp rule
    (is (= 1 (count (query target-kb '((?/s rdf/type owl/Restriction))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-hd-goa 0)
    ;  (close log-kb))

;    (prn (str "--------------------------------"))
;    (doall (map #(prn (str %)) (sparql-query source-kb
;                                             "prefix franzOption_chunkProcessingAllowed: <franz:yes>
;                                             prefix franzOption_clauseReorderer: <franz:identity>
;                                             PREFIX obo: <http://purl.obolibrary.org/obo/>
;                                             PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
;                                             PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
;    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
;    SELECT ?participating_bioentity ?biological_process ?has_participant ?record   ?go_bp_identifier
;WHERE {
;?go_bp_identifier rdfs:subClassOf ccp:IAO_EXT_0000103 . # CCP:GO_biological_process_identifier
;?go_bp_identifier obo:IAO_0000219 ?biological_process .
;filter (contains(str(?biological_process),'http://ccp.ucdenver.edu/obo/ext/'))
;?go_identifier_field_value rdf:type ?go_bp_identifier .
;?go_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
;?record obo:BFO_0000051 ?go_identifier_field_value .
;?record rdf:type ccp:IAO_EXT_0000007 . # ccp:GOAGAFv2.0AnnotationRecord
;?record obo:BFO_0000051 ?bioentity_field_value .
;?bioentity_field_value rdf:type ccp:IAO_EXT_0000010 . # ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
;?bioentity_field_value rdf:type ?bioentity_identifier .
;?bioentity_identifier obo:IAO_0000219 ?participating_bioentity .
;OPTIONAL { ?record obo:BFO_0000051 ?qualifier_field_value .
;           ?qualifier_field_value rdf:type ccp:IAO_EXT_0000013 . # ccp:GOA_GAF_v2.0_Annotation_record__qualifier_field_value
;           ?qualifier_field_value rdfs:label ?qualifier .
;         }
;         # filter out the negations
;         FILTER (( ! bound(?qualifier) || ! regex(?qualifier, \"^NOT\", \"i\")))
;         {
;         select ?has_participant {
;                                 ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
;                                 filter (?has_participant != obo:RO_0000057) .
;                                 }
;         }
;       }"
;                                             )))
;
;        (prn (str "--------------------------------"))

    ))




;;; Test that all links get transferred to bioworld
(deftest step-hd-goa-cc-test
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (run-build-rule source-kb source-kb build-rules-step-hd-goa 1)

    (run-build-rule source-kb target-kb build-rules-step-hd-goa 1)

    (is (ask source-kb '((kice/GO_0051179 obo/IAO_0000219 ?/localization_process)
                          (?/localization_sc rdfs/subClassOf ?/localization_process)

                          ;; create a subclass of the participating bioentity
                          (kice/UNIPROT_P37173 obo/IAO_0000219 ?/localized_bioentity)
                          (?/bioentity_sc rdfs/subClassOf ?/localized_bioentity)

                          ;; create a subclass of cellular component
                          (?/cellular_component_sc rdfs/subClassOf ?/cellular_component)
                          (kice/GO_0005623 obo/IAO_0000219 ?/cellular_component)

                          ;; create a transports_or_maintains_localization_of restriction
                          (?/trans_main_loc_restriction rdf/type owl/Restriction)
                          (?/trans_main_loc_restriction owl/onProperty ?/transports_or_maintains_localization_of)
                          (kice/RO_0002313 obo/IAO_0000219 ?/transports_or_maintains_localization_of)
                          (?/trans_main_loc_restriction owl/someValuesFrom ?/bioentity_sc)

                          ;; create a has_target_end_location restriction
                          (?/target_end_restriction rdf/type owl/Restriction)
                          (?/target_end_restriction owl/onProperty ?/has_target_end_location)
                          (kice/RO_0002339 obo/IAO_0000219 ?/has_target_end_location)
                          (?/target_end_restriction owl/someValuesFrom ?/cellular_component_sc)

                          ;; connect the localization subclass to the transports_or_maintains_localization_of and has_target_end restrictions
                          (?/localization_sc rdfs/subClassOf ?/trans_main_loc_restriction)
                          (?/localization_sc rdfs/subClassOf ?/target_end_restriction)

                          ;; provenance: connect the record to the localization subclass
                          (?/record obo/IAO_0000219 ?/localization_sc)
                          (?/record rdf/type ccp/IAO_EXT_0000007)

                          )))

    ;; 2 restrictions per go-cc annotation are created by the go-cc rule
    (is (= 2 (count (query target-kb '((?/s rdf/type owl/Restriction))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-hd-goa 1)
    ;  (close log-kb))



;    (prn (str "--------------------------------"))
;        (doall (map #(prn (str %)) (sparql-query source-kb
;                                                 "PREFIX obo: <http://purl.obolibrary.org/obo/>
;                                                 PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
;                                                 PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
;    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
;    SELECT ?localized_bioentity ?cellular_component ?record ?localization_process ?transports_or_maintains_localization_of ?has_target_end_location
;WHERE {
;       ?go_cc_identifier rdfs:subClassOf ccp:IAO_EXT_0000200 . # CCP:GO_cellular_component_concept_identifier
;    ?go_cc_identifier obo:IAO_0000219 ?cellular_component .
;       filter (contains(str(?cellular_component),'http://ccp.ucdenver.edu/obo/ext/'))
;       ?ontology_identifier_field_value rdf:type ?go_cc_identifier .
;       ?ontology_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
;    ?record obo:BFO_0000051 ?ontology_identifier_field_value .
;       ?record rdf:type ccp:IAO_EXT_0000007 . # ccp:GOA_GAF_v2.0_Annotation_record
;    ?record obo:BFO_0000051 ?bioentity_field_value .
;       ?bioentity_field_value rdf:type ccp:IAO_EXT_0000010 . # ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
;    ?bioentity_field_value rdf:type ?bioentity_identifier .
;       ?bioentity_identifier obo:IAO_0000219 ?localized_bioentity .
;       OPTIONAL {
;                 ?record obo:BFO_0000051 ?qualifier_field_value .
;                 ?qualifier_field_value rdf:type ccp:IAO_EXT_0000013 . # ccp:GOA_GAF_v2.0_Annotation_record__qualifierfieldvalue
;    ?qualifier_field_value rdfs:label ?qualifier .
;                 }
;       FILTER (( ! bound(?qualifier) || ! regex(?qualifier, \"^NOT\", \"i\")))
;
;       {
;        select ?localization_process {
;                                      ccp:GO_0051179 obo:IAO_0000219 ?localization_process .
;                                      filter (?localization_process != obo:GO_0051179) .
;                                      }
;        }
;
;       {
;        select ?transports_or_maintains_localization_of  {
;                                                          ccp:RO_0002313 obo:IAO_0000219 ?transports_or_maintains_localization_of .
;                                                          filter (?transports_or_maintains_localization_of != obo:RO_0002313) .
;                                                          }
;        }
;
;       {
;        select ?has_target_end_location {
;                                         ccp:RO_0002339 obo:IAO_0000219 ?has_target_end_location .
;                                         filter (?has_target_end_location != obo:RO_0002339) .
;                                         }
;        }
;       }"
;                                                 )))
;
;            (prn (str "--------------------------------"))







    ))



(deftest step-hd-goa-mf-test
  (let [source-kb base-kb
        target-kb (test-kb '())]
    ;(run-build-rule source-kb source-kb build-rules-step-hd-goa 2)

    (run-build-rule source-kb target-kb build-rules-step-hd-goa 2)

    ;; TODO: add sample GOA MF data to test this rule fully
    (is (= 0
           (count (query target-kb '((?/s rdf/type owl/Restriction))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-hd-goa 2)
    ;  (close log-kb))

    ))


;; validate list structures
(deftest step-hd-goa-test-with-validation-rules-lists
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-hd-goa)

    (run-build-rule source-kb target-kb validation-rules-list 0)
    ;; add 4 for the rule metadata
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 1)
    ;; add 4 for the rule metadata
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 2)
    ;; add 4 for the rule metadata
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 3)
    ;; add 4 for the rule metadata
    (is (= 16 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 4)
    ;; add 4 for the rule metadata
    (is (= 20 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 5)
    ;; add 4 for the rule metadata
    (is (= 24 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 6)
    ;; add 4 for the rule metadata
    (is (= 28 (count (query target-kb '((?/s ?/p ?/o))))))))


;; validate restriction structures
(deftest step-hd-goa-test-with-validation-rules-restrictions
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-hd-goa)

    (run-build-rule source-kb target-kb validation-rules-restriction 0)
    ;; add 4 for the rule metadata
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 1)
    ;; add 4 for the rule metadata
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 2)
    ;; add 4 for the rule metadata
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 3)
    ;; add 4 for the rule metadata
    (is (= 16 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 4)
    ;; add 4 for the rule metadata
    (is (= 20 (count (query target-kb '((?/s ?/p ?/o))))))))




