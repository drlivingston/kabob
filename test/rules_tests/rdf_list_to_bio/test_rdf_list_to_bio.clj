(ns rules-tests.rdf-list-to-bio.test-rdf-list-to-bio
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query ask sparql-query]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf *graph*]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [run-build-rule run-build-rules test-kb build-rules-step-a
                                                            build-rules-step-b build-rules-step-ca build-rules-step-cb
                                                            build-rules-step-cc build-rules-step-da build-rules-step-db
                                                            build-rules-step-dc build-rules-step-fa build-rules-step-fb
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gca
                                                            build-rules-step-gcb build-rules-step-gcc

                                                            ]]
            [rules-tests.build-test.ccp-ext-ontology-lite :refer [ccp-ext-ontology-triples]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))

(def ro-triples '((obo/RO_0001025 rdf/type owl/ObjectProperty)
                   (obo/RO_0001025 rdf/type owl/TransitiveProperty)
                   (obo/RO_0001025 obo/IAO_0000115 ["a relation between two independent continuants, the target and the location, in which the target is entirely within the location" "en"])
                   (obo/RO_0001025 rdfs/label ["located in" "en"])

                   (obo/RO_0002379 rdf/type owl/ObjectProperty)
                   (obo/RO_0002379 rdfs/subPropertyOf obo/RO_0002131)
                   (obo/RO_0002379 owl/propertyChainAxiom bnode/BN_4a04e50456eba3289575a72ed00dd981568abd5cc2ee87c91fb071eed178ef15)
                   (bnode/BN_4a04e50456eba3289575a72ed00dd981568abd5cc2ee87c91fb071eed178ef15 rdf/type rdf/List)
                   (bnode/BN_4a04e50456eba3289575a72ed00dd981568abd5cc2ee87c91fb071eed178ef15 rdf/first obo/RO_0001025)
                   (bnode/BN_4a04e50456eba3289575a72ed00dd981568abd5cc2ee87c91fb071eed178ef15 rdf/rest bnode/BN_cef3a1fcf6869771b8404c040e994c6e05c8ca2171a0fd0eb8f3185439586ff3)
                   (bnode/BN_cef3a1fcf6869771b8404c040e994c6e05c8ca2171a0fd0eb8f3185439586ff3 rdf/type rdf/List)
                   (bnode/BN_cef3a1fcf6869771b8404c040e994c6e05c8ca2171a0fd0eb8f3185439586ff3 rdf/first bnode/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb)
                   (bnode/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb owl/inverseOf obo/RO_0001025)
                   (bnode/BN_cef3a1fcf6869771b8404c040e994c6e05c8ca2171a0fd0eb8f3185439586ff3 rdf/rest rdf/nil)
                   (obo/RO_0002379 obo/IAO_0000115 ["x spatially_coextensive_with y if and inly if x and y have the same location"])
                   (obo/RO_0002379 rdfs/label ["spatially coextensive with" "en"])))

(def input-triples (concat ro-triples))

(deftest test-step-ab
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rule source-kb target-kb build-rules-step-a 0)

    ;; 2 rules are run, so 8 metadata triples plus 2*2 output triples from the ontology_id_denotes_object_property rule
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((kice/RO_0002379 obo/IAO_0000219 obo/RO_0002379)
                          (kice/RO_0002379 rdfs/subClassOf ccp/IAO_EXT_0000306))))

    (is (ask target-kb '((kice/RO_0001025 obo/IAO_0000219 obo/RO_0001025)
                          (kice/RO_0001025 rdfs/subClassOf ccp/IAO_EXT_0000306))))

    (let [log-kb (output-kb "/tmp/triples.nt")
          src-kb (test-kb input-triples)]
      (run-build-rule source-kb log-kb build-rules-step-a 0)
      (close log-kb))

    ))

(deftest test-step-ac
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rule source-kb source-kb build-rules-step-a 0)
    (run-build-rule source-kb target-kb build-rules-step-a 1)

    ;; 24 rules are run, so 96 metadata triples
    ;; there should be no new triples generated
    (is (= 96 (count (query target-kb '((?/s ?/p ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rule source-kb source-kb build-rules-step-a 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-a 1)
    ;  (close log-kb))

    ))


(deftest test-step-ad
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rule source-kb source-kb build-rules-step-a 0)
    (run-build-rule source-kb source-kb build-rules-step-a 1)
    (run-build-rule source-kb target-kb build-rules-step-a 2)

    ;; 2 rules are run, so 8 metadata triples
    ;; there should be output frmo the object_property_record_gen rule: 2* 11 triples
    (is (= 30 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/record rdf/type ccp/IAO_EXT_0000310) ; CCP:object_property_record
                          (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000314) ; CCP:object_property_id_field_value
                          (?/id_field_value rdf/type kice/RO_0002379)
                          (?/id_field_value rdfs/label ?/bound_id_string)
                          (?/record obo/BFO_0000051 ?/definition_field_value) ; BFO:has_part
                          (?/definition_field_value rdf/type ccp/IAO_EXT_0000313) ; CCP:object_property_record_definition_field_value
                          (?/definition_field_value rdfs/label [ "x spatially_coextensive_with y if and inly if x and y have the same location" "en"])
                          (?/record obo/BFO_0000051 ?/label_field_value) ; BFO:has_part
                          (?/label_field_value rdf/type ccp/IAO_EXT_0000315) ; CCP:object_property_record_label_field_value
                          (?/label_field_value rdfs/label ["spatially coextensive with" "en"]))))

    (is (ask target-kb '((?/record rdf/type ccp/IAO_EXT_0000310) ; CCP:object_property_record
                          (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000314) ; CCP:object_property_id_field_value
                          (?/id_field_value rdf/type kice/RO_0001025)
                          (?/id_field_value rdfs/label ?/bound_id_string)
                          (?/record obo/BFO_0000051 ?/definition_field_value) ; BFO:has_part
                          (?/definition_field_value rdf/type ccp/IAO_EXT_0000313) ; CCP:object_property_record_definition_field_value
                          (?/definition_field_value rdfs/label [ "a relation between two independent continuants, the target and the location, in which the target is entirely within the location" "en"])
                          (?/record obo/BFO_0000051 ?/label_field_value) ; BFO:has_part
                          (?/label_field_value rdf/type ccp/IAO_EXT_0000315) ; CCP:object_property_record_label_field_value
                          (?/label_field_value rdfs/label ["located in" "en"]))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rule source-kb source-kb build-rules-step-a 0)
    ;  (run-build-rule source-kb source-kb build-rules-step-a 1)
    ;  (run-build-rule source-kb log-kb build-rules-step-a 2)
    ;  (close log-kb))

    ))


;; no new triples expected from step b
(deftest test-step-b
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rule source-kb target-kb build-rules-step-b 0)
    (run-build-rule source-kb target-kb build-rules-step-b 1)


    ;; 3 rules are run, so 12 metadata triples
    ;; there should be no output triples from any of the rules
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rules source-kb build-rules-step-a)
    ;  (run-build-rule source-kb log-kb build-rules-step-b 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-b 1)
    ;  (run-build-rule source-kb log-kb build-rules-step-a 2)
    ;  (close log-kb))

    ))


;; no new triples expected from step c
(deftest test-step-c
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rule source-kb target-kb build-rules-step-ca 0)
    (run-build-rule source-kb target-kb build-rules-step-cb 0)
    (run-build-rule source-kb target-kb build-rules-step-cb 0)
    (run-build-rule source-kb target-kb build-rules-step-cb 1)
    (run-build-rule source-kb target-kb build-rules-step-cb 2)
    (run-build-rule source-kb target-kb build-rules-step-cb 3)
    (run-build-rule source-kb target-kb build-rules-step-cb 4)
    (run-build-rule source-kb target-kb build-rules-step-cb 5)
    (run-build-rule source-kb target-kb build-rules-step-cb 6)
    (run-build-rule source-kb target-kb build-rules-step-cb 7)
    (run-build-rule source-kb target-kb build-rules-step-cc 0)
    (run-build-rule source-kb target-kb build-rules-step-cc 1)


    ;; 12 rules are run, so 48 metadata triples
    ;; there should be no output triples from any of the rules
    (is (= 48 (count (query target-kb '((?/s ?/p ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rules source-kb build-rules-step-a)
    ;  (run-build-rules source-kb build-rules-step-b)
    ;  (run-build-rule source-kb log-kb build-rules-step-ca 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 1)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 2)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 3)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 4)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 5)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 6)
    ;  (run-build-rule source-kb log-kb build-rules-step-cb 7)
    ;  (run-build-rule source-kb log-kb build-rules-step-cc 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-cc 1)
    ;  (close log-kb))

    ))


;; no new triples expected from step d
(deftest test-step-d
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-ca)
    (run-build-rules source-kb build-rules-step-cb)
    (run-build-rules source-kb build-rules-step-cc)
    (run-build-rule source-kb target-kb build-rules-step-da 0)
    (run-build-rule source-kb target-kb build-rules-step-da 1)
    (run-build-rule source-kb target-kb build-rules-step-da 2)
    (run-build-rule source-kb target-kb build-rules-step-da 3)
    (run-build-rule source-kb target-kb build-rules-step-da 4)
    (run-build-rule source-kb target-kb build-rules-step-da 5)
    (run-build-rule source-kb target-kb build-rules-step-db 0)
    (run-build-rule source-kb target-kb build-rules-step-db 1)
    (run-build-rule source-kb target-kb build-rules-step-db 2)
    (run-build-rule source-kb target-kb build-rules-step-db 3)
    (run-build-rule source-kb target-kb build-rules-step-db 4)
    (run-build-rule source-kb target-kb build-rules-step-db 5)
    (run-build-rule source-kb target-kb build-rules-step-db 6)
    (run-build-rule source-kb target-kb build-rules-step-db 7)
    (run-build-rule source-kb target-kb build-rules-step-dc 0)


    ;; 15 rules are run, so 60 metadata triples
    ;; there should be no output triples from any of the rules
    (is (= 60 (count (query target-kb '((?/s ?/p ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rules source-kb build-rules-step-a)
    ;  (run-build-rules source-kb build-rules-step-b)
    ;  (run-build-rules source-kb build-rules-step-ca)
    ;  (run-build-rules source-kb build-rules-step-cb)
    ;  (run-build-rules source-kb build-rules-step-cc)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 1)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 2)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 3)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 4)
    ;  (run-build-rule source-kb log-kb build-rules-step-da 5)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 1)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 2)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 3)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 4)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 5)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 6)
    ;  (run-build-rule source-kb log-kb build-rules-step-db 7)
    ;  (run-build-rule source-kb log-kb build-rules-step-dc 0)
    ;  (close log-kb))

    ))


(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))


(deftest test-step-e
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
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
                    (load-rdf target-kb (java.util.zip.GZIPInputStream.
                                          (clojure.java.io/input-stream
                                            f)) :ntriple))
                  (get-only-files tmp-dir))))

    ;; 2 id sets should have been generated
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/RO_0002379)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/RO_0001025)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))


    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query target-kb
    ;                                         "PREFIX obo: obo/>
    ;                                         PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX rdf: rdf/>
    ;PREFIX rdfs: rdfs/>
    ;select ?s ?p ?o { ?s ?p ?o . }"
    ;                                         )))
    ;
    ;(prn (str "--------------------------------"))


    ))



(deftest test-step-f
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
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

    (run-build-rule source-kb target-kb build-rules-step-fa 0)
    (run-build-rule source-kb target-kb build-rules-step-fa 1)
    (run-build-rule source-kb target-kb build-rules-step-fb 0)

    ;; 3 rules are run, so 12 metadata triples
    ;; there should be two triple from identifier_denotes_bioentity
    ;; there should be two triple from idset_mentions_bioentity
    (is (= 16 (count (query target-kb '((?/s ?/p ?/o))))))


    (is (ask target-kb '((kice/RO_0002379 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))

    (is (ask target-kb '((kice/RO_0001025 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))



    ;(let [log-kb (output-kb "/tmp/triples.nt")
    ;      src-kb (test-kb input-triples)]
    ;  (run-build-rules source-kb build-rules-step-a)
    ;  (run-build-rules source-kb build-rules-step-b)
    ;  (run-build-rules source-kb build-rules-step-ca)
    ;  (run-build-rules source-kb build-rules-step-cb)
    ;  (run-build-rules source-kb build-rules-step-cc)
    ;  (run-build-rules source-kb build-rules-step-da)
    ;  (run-build-rules source-kb build-rules-step-db)
    ;  (run-build-rules source-kb build-rules-step-dc)
    ;
    ;  (close log-kb))

    ))



(deftest test-step-ga
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
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

    (run-build-rule source-kb target-kb build-rules-step-ga 1)
    ;; 1 rule run, so 4 metadata triples
    ;; there should be no output triples from the copy_owl_alldisjointclasses_to_bio rule
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb build-rules-step-ga 2)
    ;; 2 rules run, so 8 metadata triples
    ;; there should be no output triples from the copy_owl_restriction_to_bio rule
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    ;; copy_anonymous_nodes_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 3)
    ;; 8 from above + 4 metadata triples + 0
    ;; there should be 0output triples from the this rule
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb build-rules-step-ga 4)
    ;; 12 from above + 4  metadata triples + 7
    ;; there should be 7 output triples from the copy_anonymous_nodes_to_bio rule; 1 anon node copied
    (is (= 23 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
                          (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
                          (?/id obo/IAO_0000219 bnode/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb)
                          (?/id obo/IAO_0000219 ?/bio_blank_node))))

    ;; copy_list_nodes_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 0)
    ;; 23 from above + 4 metadata triples + 16
    ;; there should be 2 * 8 = 16 triples from the copy_list_nodes_to_bio rule
    (is (= 43 (count (query target-kb '((?/s ?/p ?/o))))))


    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_4a04e50456eba3289575a72ed00dd981568abd5cc2ee87c91fb071eed178ef15)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))

    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_cef3a1fcf6869771b8404c040e994c6e05c8ca2171a0fd0eb8f3185439586ff3)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))



    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-ga 2)
      (close log-kb))

    ))

;; todo -- write step gc tests
;; todo -- make sure all kabob tests work with new rule
;; todo -- try it out in the may build


(deftest test-step-gc
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
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


    (run-build-rule source-kb target-kb build-rules-step-gca 0)
    ;; 1 rule run, so 4 metadata triples
    ;; there should be one link to nil
    (is (= 5 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rules source-kb build-rules-step-gcb)
    (run-build-rule source-kb target-kb build-rules-step-gcc 0)
    ;; 5 from above + 4 metadata triples + 5
    ;; there should be 5 new links
    (is (= 14 (count (query target-kb '((?/s ?/p ?/o))))))



    (is (ask target-kb '((?/bio_prop owl/propertyChainAxiom ?/list1)
                          ;;(?/list1 rdf/type rdf/List)
                          (?/list1 rdf/first ?/relation)
                          (?/list1 rdf/rest ?/list2)
                          ;;(?/list2 rdf/type rdf/List)
                          (?/list2 rdf/first kbio/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb)
                          (kbio/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb owl/inverseOf ?/relation)
                          (?/list2 rdf/rest rdf/nil)
                          )))



    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-gc 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-gc 1)
    ;  (close log-kb))

    ))