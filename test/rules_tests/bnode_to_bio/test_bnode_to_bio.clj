(ns rules-tests.bnode-to-bio.test-bnode-to-bio
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

(def cl-triples '(
                   ;;  # http://purl.obolibrary.org/obo/CL_0002590
                  (obo/CL_0002590 rdf/type owl/Class)
                  (obo/CL_0002590 owl/equivalentClass bnode/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19)
                  (bnode/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19 owl/intersectionOf bnode/BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc)
                  (bnode/BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/type rdf/List)
                  (bnode/BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/first obo/CL_0000359)
                  (bnode/BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/rest bnode/BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9)
                  (bnode/BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/type rdf/List)
                  (bnode/BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/first bnode/BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d)
                  (bnode/BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d rdf/type owl/Restriction)
                  (bnode/BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d owl/onProperty obo/BFO_0000050)
                  (bnode/BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d owl/someValuesFrom bnode/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109)
                  (bnode/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109 owl/intersectionOf bnode/BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e)
                  (bnode/BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/type rdf/List)
                  (bnode/BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/first obo/UBERON_0002049)
                  (bnode/BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/rest bnode/BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3)
                  (bnode/BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/type rdf/List)
                  (bnode/BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/first bnode/BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb)
                  (bnode/BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb rdf/type owl/Restriction)
                  (bnode/BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb owl/onProperty obo/BFO_0000050)
                  (bnode/BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb owl/someValuesFrom obo/UBERON_0000955)
                  (bnode/BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/rest rdf/nil)
                  (bnode/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109 rdf/type owl/Class)
                  (bnode/BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/rest rdf/nil)
                  (bnode/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19 rdf/type owl/Class)
                  (obo/CL_0002590 rdfs/subClassOf obo/CL_0000359)
                  ;(obo/CL_0002590 rdfs/subClassOf obo/CL_0002319)
                  (obo/CL_0002590 rdfs/subClassOf bnode/BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab)
                  (bnode/BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab rdf/type owl/Restriction)
                  (bnode/BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab owl/onProperty obo/BFO_0000050)
                  (bnode/BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab owl/someValuesFrom bnode/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109)
                  (obo/CL_0002590 obo/IAO_0000115 ["A vascular associated smooth muscle cell of the brain vasculature." "en"])
                  (obo/CL_0002590 oboInOwl/created_by ["tmeehan" "en"])
                  (obo/CL_0002590 oboInOwl/creation_date ["2011-03-06T05:01:20Z" "en"])
                  (obo/CL_0002590 oboInOwl/hasOBONamespace ["cell"])
                  (obo/CL_0002590 rdfs/label ["smooth muscle cell of the brain vasculature" "en"])
                  (bnode/BN_6962cbc72e5f8bdcdb99ef8a06f0b5a37c6250294ae7189778db425ec6876971 rdf/type owl/Axiom)
                  (bnode/BN_6962cbc72e5f8bdcdb99ef8a06f0b5a37c6250294ae7189778db425ec6876971 owl/annotatedSource obo/CL_0002590)
                  (bnode/BN_6962cbc72e5f8bdcdb99ef8a06f0b5a37c6250294ae7189778db425ec6876971 owl/annotatedProperty rdfs/subClassOf)
                  (bnode/BN_6962cbc72e5f8bdcdb99ef8a06f0b5a37c6250294ae7189778db425ec6876971 owl/annotatedTarget obo/CL_0000359)
                  (bnode/BN_6962cbc72e5f8bdcdb99ef8a06f0b5a37c6250294ae7189778db425ec6876971 oboInOwl/is_inferred ["true" "en"])
                  (bnode/BN_399d8fe91da7f393fc74b8d55d0e5d8fe8388e5b0704fa822a075eced96a4fee rdf/type owl/Axiom)
                  (bnode/BN_399d8fe91da7f393fc74b8d55d0e5d8fe8388e5b0704fa822a075eced96a4fee owl/annotatedSource obo/CL_0002590)
                  (bnode/BN_399d8fe91da7f393fc74b8d55d0e5d8fe8388e5b0704fa822a075eced96a4fee owl/annotatedProperty obo/IAO_0000115)
                  (bnode/BN_399d8fe91da7f393fc74b8d55d0e5d8fe8388e5b0704fa822a075eced96a4fee owl/annotatedTarget ["A vascular associated smooth muscle cell of the brain vasculature." "en"])
                  (bnode/BN_399d8fe91da7f393fc74b8d55d0e5d8fe8388e5b0704fa822a075eced96a4fee oboInOwl/hasDbXref ["GOC:tfm" "en"])


                   ;; http://purl.obolibrary.org/obo/BFO_0000050
                  (obo/BFO_0000050 rdf/type owl/ObjectProperty)
                  (obo/BFO_0000050 rdfs/subPropertyOf obo/RO_0002131)
                  (obo/BFO_0000050 owl/inverseOf obo/BFO_0000051)
                  (obo/BFO_0000050 rdf/type owl/TransitiveProperty)
                  (obo/BFO_0000050 obo/IAO_0000115 ["a core relation that holds between a part and its whole" "en"])
                  (obo/BFO_0000050 rdfs/label ["part of"])


                  ;; http://purl.obolibrary.org/obo/CL_0000359
                  (obo/CL_0000359 rdf/type owl/Class)
                  (obo/CL_0000359 rdfs/subClassOf obo/CL_0000192)
                  (obo/CL_0000359 obo/IAO_0000115 ["A smooth muscle cell assocatiated with the vasculature." "en"])
                  (obo/CL_0000359 oboInOwl/hasExactSynonym ["VSMC" "en"])
                  (obo/CL_0000359 oboInOwl/hasExactSynonym ["vascular smooth muscle cell" "en"])
                  (obo/CL_0000359 oboInOwl/hasOBONamespace ["cell"])
                  (obo/CL_0000359 rdfs/label ["vascular associated smooth muscle cell"])


                  ;; http://purl.obolibrary.org/obo/UBERON_0002049
                  (obo/UBERON_0002049 rdf/type owl/Class)
                  (obo/UBERON_0002049 rdfs/subClassOf obo/UBERON_0000477)
                  (obo/UBERON_0002049 obo/IAO_0000115 ["An interconnected tubular multi-tissue structure contains fluid that is actively transported around the organism[ZFA]. Examples: vasculature of lung, vasculature of face." "en"])
                  (obo/UBERON_0002049 oboInOwl/hasExactSynonym ["vascular network" "en"])
                   (obo/UBERON_0002049 oboInOwl/hasOBONamespace ["uberon"])
                  (obo/UBERON_0002049 rdfs/label ["vasculature" ])

                   ;; http://purl.obolibrary.org/obo/UBERON_0000955
                  (obo/UBERON_0000955 rdf/type owl/Class)
                  (obo/UBERON_0000955 rdfs/subClassOf obo/UBERON_0000062)
                  (obo/UBERON_0000955 rdfs/subClassOf obo/UBERON_0004121)
                  (obo/UBERON_0000955 obo/IAO_0000115 ["The brain is the center of the nervous system in all vertebrate, and most invertebrate, animals. Some primitive animals such as jellyfish and starfish have a decentralized nervous system without a brain, while sponges lack any nervous system at all. In vertebrates, the brain is located in the head, protected by the skull and close to the primary sensory apparatus of vision, hearing, balance, taste, and smell[WP]." "en"])
                  (obo/UBERON_0000955 oboInOwl/hasRelatedSynonym ["encephalon" "en"])
                  (obo/UBERON_0000955 oboInOwl/hasRelatedSynonym ["suprasegmental levels of nervous system" "en"])
                  (obo/UBERON_0000955 oboInOwl/hasRelatedSynonym ["suprasegmental structures" "en"])
                  (obo/UBERON_0000955 oboInOwl/hasRelatedSynonym ["synganglion" "en"])
                  (obo/UBERON_0000955 oboInOwl/hasRelatedSynonym ["the brain" "en"])
                   (obo/UBERON_0000955 oboInOwl/hasOBONamespace ["uberon"])
                  (obo/UBERON_0000955 rdfs/label ["brain"])

    ))

(def input-triples (concat cl-triples))

(deftest test-step-ab
  (let [source-kb (test-kb input-triples)
        target-kb (test-kb '())]
    (binding [*graph* "http://ccp-extension.owl"]
      (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
    (run-build-rule source-kb target-kb build-rules-step-a 0)

    ;; 2 rules are run, so 8 metadata triples plus 5*2 output triples from the ontology_id_denotes_object_property rule
    (is (= 18 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((kice/CL_0002590 obo/IAO_0000219 obo/CL_0002590)
                          (kice/CL_0002590 rdfs/subClassOf ccp/IAO_EXT_0000088))))


    (is (ask target-kb '((kice/BFO_0000050 obo/IAO_0000219 obo/BFO_0000050)
                          (kice/BFO_0000050 rdfs/subClassOf ccp/IAO_EXT_0000306))))

    (is (ask target-kb '((kice/CL_0000359 obo/IAO_0000219 obo/CL_0000359)
                          (kice/CL_0000359 rdfs/subClassOf ccp/IAO_EXT_0000088))))

    (is (ask target-kb '((kice/UBERON_0002049 obo/IAO_0000219 obo/UBERON_0002049)
                          (kice/UBERON_0002049 rdfs/subClassOf ccp/IAO_EXT_0000088))))

    (is (ask target-kb '((kice/UBERON_0000955 obo/IAO_0000219 obo/UBERON_0000955)
                          (kice/UBERON_0000955 rdfs/subClassOf ccp/IAO_EXT_0000088))))


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
    ;; there should be 4 new triple generated
    (is (= 100 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((kice/CL_0002590 rdfs/subClassOf ccp/IAO_EXT_0000202))))
    (is (ask target-kb '((kice/CL_0000359 rdfs/subClassOf ccp/IAO_EXT_0000202))))
    (is (ask target-kb '((kice/UBERON_0002049 rdfs/subClassOf ccp/IAO_EXT_0000201))))
    (is (ask target-kb '((kice/UBERON_0000955 rdfs/subClassOf ccp/IAO_EXT_0000201))))


    (let [log-kb (output-kb "/tmp/triples.nt")
          src-kb (test-kb input-triples)]
      (run-build-rule source-kb source-kb build-rules-step-a 0)
      (run-build-rule source-kb log-kb build-rules-step-a 1)
      (close log-kb))

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
    ;; there should be output from the object_concept_record_gen rule: 4*11 triples & from the object_property_record: 1*11
    (is (= 63 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/record rdf/type ccp/IAO_EXT_0000191) ; CCP:ontology_concept_record
                          (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000034) ; CCP:identifier_field_value
                          (?/id_field_value rdf/type kice/CL_0002590) ; CCP:ontology_concept_identifier
                          (?/id_field_value rdfs/label ?/bound_id_string)
                          (?/record obo/BFO_0000051 ?/definition_field_value) ; BFO:has_part
                          (?/definition_field_value rdf/type ccp/IAO_EXT_0000195) ; CCP:ontology_concept_record_definition_field_value
                          (?/definition_field_value rdfs/label [ "A vascular associated smooth muscle cell of the brain vasculature." "en"])
                          (?/record obo/BFO_0000051 ?/label_field_value); BFO:has_part
                          (?/label_field_value rdf/type ccp/IAO_EXT_0000196) ; CCP:ontology_concept_record_label_field_value
                          (?/label_field_value rdfs/label ["smooth muscle cell of the brain vasculature" "en"]))))


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
    ;; there should be 4 output triple
    (is (= 64 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((kice/CL_0002590 rdfs/subClassOf ccp/IAO_EXT_0000342))))
    (is (ask target-kb '((kice/CL_0000359 rdfs/subClassOf ccp/IAO_EXT_0000342))))
    (is (ask target-kb '((kice/UBERON_0002049 rdfs/subClassOf ccp/IAO_EXT_0000342))))
    (is (ask target-kb '((kice/UBERON_0000955 rdfs/subClassOf ccp/IAO_EXT_0000342))))


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

    ;; 5 id sets should have been generated
    (is (= 10 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/CL_0002590)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/BFO_0000050)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/CL_0000359)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/UBERON_0002049)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))

    (is (ask target-kb '((?/idset obo/RO_0002351 kice/UBERON_0000955)
                          (?/idset rdf/type ccp/IAO_EXT_0000316))))


    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query target-kb
    ;                                         "PREFIX obo: obo/>
    ;                                         PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX rdf: rdf/>
    ;PREFIX rdfs: rdfs/>
    ;select ?s ?p ?o { ?s ?p ?o) }"
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
    ;; there should be five triple from identifier_denotes_bioentity
    ;; there should be five triple from idset_mentions_bioentity
    (is (= 22 (count (query target-kb '((?/s ?/p ?/o))))))


    (is (ask target-kb '((kice/CL_0002590 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))

    (is (ask target-kb '((kice/BFO_0000050 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))

    (is (ask target-kb '((kice/CL_0000359 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))

    (is (ask target-kb '((kice/UBERON_0002049 obo/IAO_0000219 ?/bio)
                          (?/idset obo/IAO_0000142 ?/bio))))

    (is (ask target-kb '((kice/UBERON_0000955 obo/IAO_0000219 ?/bio)
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

    ;; copy_owl_alldisjointclasses_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 1)
    ;; 1 rule run, so 4 metadata triples
    ;; there should be no output triples from the copy_owl_alldisjointclasses_to_bio rule
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    ;; copy_owl_restriction_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 2)
    ;; 4 from above + 4 more metadata triples + 24
    ;; there are 3 restrictions, so 3*8 triples = 24
    (is (= 32 (count (query target-kb '((?/s ?/p ?/o))))))

    ;; copy_anonymous_nodes_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 3)
    ;; 32 from above + 4 more metadata triples + 14
    ;; there should be 2 blank nodes, so 2*8=16 triples
    (is (= 52 (count (query target-kb '((?/s ?/p ?/o))))))

    (is (ask target-kb '((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
                          (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
                          (?/id obo/IAO_0000219 bnode/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19)
                          (?/id obo/IAO_0000219 ?/bio_blank_node))))

    (is (ask target-kb '((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
                          (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
                          (?/id obo/IAO_0000219 bnode/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109)
                          (?/id obo/IAO_0000219 ?/bio_blank_node))))


    ;; copy_anonymous_nodes_in_lists_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 4)
    ;; 52 from above + 4 metadata triples + 0
    ;; there should be 0 output triples from t
    (is (= 56 (count (query target-kb '((?/s ?/p ?/o))))))
    ;
    ;(is (ask target-kb '((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
    ;                      (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
    ;                      (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
    ;                      (?/id_field_value rdf/type ?/id)
    ;                      (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
    ;                      (?/id obo/IAO_0000219 bnode/BN_0605b7be27965a4e35d0cfa66604d11b4108c6fee811db19245f2024408dd6bb)
    ;                      (?/id obo/IAO_0000219 ?/bio_blank_node))))
    ;

    ;; copy_list_nodes_to_bio rule
    (run-build-rule source-kb target-kb build-rules-step-ga 0)
    ;; 56 from above + 4 more metadata triples + 32
    ;; there should be 4 * 8 = 32 triples for the 4 list nodes
    (is (= 92 (count (query target-kb '((?/s ?/p ?/o))))))


    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))

    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))

    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))

    (is (ask target-kb '( (?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
                          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
                          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
                          (?/id_field_value rdf/type ?/id)
                          (?/id rdf/type ccp/IAO_EXT_0000354)
                          (?/id obo/IAO_0000219 bnode/BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3)
                          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
                          (?/bio_listmember rdf/type rdf/List))))



    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-ga 1)
      (run-build-rule source-kb log-kb build-rules-step-ga 2)
      (run-build-rule source-kb log-kb build-rules-step-ga 3)
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
    ;; there should be two link to nil
    (is (= 6 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rules source-kb build-rules-step-gca)
    (run-build-rules source-kb build-rules-step-gcb)
    (run-build-rule source-kb target-kb build-rules-step-gcc 0)
    ;; 6 from above + 4 metadata triples + 17
    ;; there should be 17 new links
    (is (= 27 (count (query target-kb '((?/s ?/p ?/o))))))


    (run-build-rules source-kb build-rules-step-gcc)
    (is (ask source-kb '((kice/CL_0002590 obo/IAO_0000219 ?/cls_cl_0002590)
                          (kice/BFO_0000050 obo/IAO_0000219 ?/cls_bfo_0000050)
                          (kice/CL_0000359 obo/IAO_0000219 ?/cls_cl_0000359)
                          (kice/UBERON_0000955 obo/IAO_0000219 ?/cls_uberon_0000955)
                          (kice/UBERON_0002049 obo/IAO_0000219 ?/cls_uberon_0002049)
                          (?/cls_cl_0002590 rdfs/subClassOf ?/cls_cl_0000359)
                          (?/cls_cl_0002590 owl/equivalentClass kbio/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19)
                          (kbio/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19 owl/intersectionOf kbio/L_BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc)
                          (kbio/L_BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/type rdf/List)
                          (kbio/L_BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/first ?/cls_cl_0000359)
                          (kbio/L_BN_590d92728ddd6f01f3ab38af77ce00f0c86627643cc89a26342aef84db8b65dc rdf/rest kbio/L_BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9)
                          (kbio/L_BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/type rdf/List)
                          (kbio/L_BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/first kbio/RS_BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d)
                          (kbio/RS_BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d rdf/type owl/Restriction)
                          (kbio/RS_BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d owl/onProperty ?/cls_bfo_0000050)
                          (kbio/RS_BN_fbea5c90d924668c49bd33e8d716028a227ac161fddba68971be89a91cb2463d owl/someValuesFrom kbio/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109)
                          (kbio/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109 owl/intersectionOf kbio/L_BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e)
                          (kbio/L_BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/type rdf/List)
                          (kbio/L_BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/first ?/cls_uberon_0002049)
                          (kbio/L_BN_4ccf91af55010564cabe5bf87a13f3b673413194c5b695e2075b80866d7eeb4e rdf/rest kbio/L_BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3)
                          (kbio/L_BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/type rdf/List)
                          (kbio/L_BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/first kbio/RS_BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb)
                          (kbio/RS_BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb rdf/type owl/Restriction)
                          (kbio/RS_BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb owl/onProperty ?/cls_bfo_0000050)
                          (kbio/RS_BN_7a72a134849d484a3026251198e49140f2dddfec151f22aa2c3a8299faf4b0cb owl/someValuesFrom ?/cls_uberon_0000955)
                          (kbio/L_BN_a3ad4626e77b14621d70861682ebc5572100798df2cefaee61e6799ee43c6cb3 rdf/rest rdf/nil)
                          (kbio/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109 rdf/type owl/Class)
                          (kbio/L_BN_a7a4f3c790e78c05889e509afc51c0e83f255343c040803309575d48eb6f16b9 rdf/rest rdf/nil)
                          (kbio/BN_6ef34dfdbcdb01aae6603d3f9695cd4500b1e3a3275631babcf4d2ddeebafe19 rdf/type owl/Class)
                          (?/cls_cl_0002590 rdfs/subClassOf kbio/RS_BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab)
                          (kbio/RS_BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab rdf/type owl/Restriction)
                          (kbio/RS_BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab owl/onProperty ?/cls_bfo_0000050)
                          (kbio/RS_BN_97c06ccf683963be06f7fc8e2f9110a8b0baed4db95055d2765ece08d20652ab owl/someValuesFrom kbio/BN_b4d54ee3980fdbaeeac0e004449e2f5aa3583e424e8d47687eacad6315073109))))


    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-gcc 0)
      (close log-kb))

    ))