(ns rules-tests.pre-identifier-merge.step-a-ontology-to-ice.step-b.test-ontology-id-denotes-concept-gen
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
             [kr.core.forward-rule :refer [add-reify-fns]]
             [kr.core.sparql :refer [sparql-select-query query]]
             [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
             [kr.core.kb :refer [kb open close]]
             [kabob.core.namespace :refer [*namespaces*]]
             [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
             [kabob.build.output-kb :refer [output-kb]]
             [clojure.pprint :refer [pprint]]))


(def sample-kb-triples '((ccp/CHEBI_1234 rdf/type ccp/IAO_EXT_0000190)
                          (ccp/CHEBI_1234 obo/IAO_0000219 obo/CHEBI_1234)
                          (obo/CHEBI_7890 rdfs/subClassOf obo/CHEBI_1234)
                          (hgnc_pr/gene_symbol_report?hgnc_id=26222 rdfs/subClassOf obo/CHEBI_1234)
                          (hgnc_pr/gene_symbol_report?hgnc_id=26222 oboInOwl/id "HGNC:26222")
                          (pombase_pr/SPMIT.11 rdfs/subClassOf obo/CHEBI_1234)
                          (pombase_pr/SPMIT.11 oboInOwl/id "PomBase:SPMIT.11")
                          (cgnc_pr/GeneReport?id=10114 rdfs/subClassOf obo/CHEBI_1234)
                          (cgnc_pr/GeneReport?id=10114 oboInOwl/id "CGNC:10114")
                          (rgd_pr/main.html?id=7676737 rdfs/subClassOf obo/CHEBI_1234)
                          (rgd_pr/main.html?id=7676737 oboInOwl/id "RGD:7676737")
                          (tair_pr/TairObject?type=locus&name=locus:6530298248 rdfs/subClassOf obo/CHEBI_1234)
                          (tair_pr/TairObject?type=locus&name=locus:6530298248 oboInOwl/id "TAIR:locus:6530298248")
                          (sgd_pr/locus.fpl?dbid=S000122558 rdfs/subClassOf obo/CHEBI_1234)
                          (sgd_pr/locus.fpl?dbid=S000122558 oboInOwl/id "SGD:S000122558")

                          (flybase_pr/FBgn0000014 rdfs/subClassOf obo/CHEBI_1234)
                          (flybase_pr/FBgn0000014 oboInOwl/id "FlyBase:FBgn0000014")

                          (zfin_pr/ZDB-GENE-991213-3 rdfs/subClassOf obo/CHEBI_1234)
                          (zfin_pr/ZDB-GENE-991213-3 oboInOwl/id "ZFIN:ZDB-GENE-991213-3")

                          (wormbase_pr/WBGene00185048 rdfs/subClassOf obo/CHEBI_1234)
                          (wormbase_pr/WBGene00185048 oboInOwl/id "WormBase:WBGene00185048")

                          (eco_pr/EG10001 rdfs/subClassOf obo/CHEBI_1234)
                          (eco_pr/EG10001 oboInOwl/id "EcoGene:EG10001")

                          (eco_pr/EG10001 rdfs/subClassOf obo/CHEBI_1234)
                          (eco_pr/EG10001 oboInOwl/id "EcoGene:EG10001")

                          (mgi_pr/MGI:101757 rdfs/subClassOf obo/CHEBI_1234)
                          (mgi_pr/MGI:101757 oboInOwl/id "MGI:101757")

                          (obo/dictyBase#_DDB_G0349043 rdfs/subClassOf obo/CHEBI_1234)
                          (obo/dictyBase#_DDB_G0349043 oboInOwl/id "dictyBase:DDB_G0349043")

                          ;; not sure how to test ncbi gene id gen b/c symbols can't start with numbers in clojure
                          ;(~http://www.ncbi.nlm.nih.gov/gene/380481 rdfs/subClassOf obo/CHEBI_1234)
                          ;(~http://www.ncbi.nlm.nih.gov/gene/380481 oboInOwl/id "NCBIGene:380481")

                          ))


(def new-triples '())

(def result-triples '())

(defn test-kb [triples]
  "initializes an empty kb"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))



(deftest test-rule
  (let [rule1 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-obo")
                            (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule2 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-hgnc")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule3 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-pombase")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule4 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-cgnc")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule5 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-rgd")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule6 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-tair")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule7 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-sgd")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule8 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-flybase")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule9 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-zfin")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule10 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-wormbase")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule11 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-eco")
                             (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule12 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-mgi")
                              (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule13 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-ncbi")
                              (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        rule14 (first (filter #(= (:name %) "ontology-id-denotes-concept-gen-dictybase")
                              (kabob-load-rules-from-classpath "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept")))
        source-kb (test-kb sample-kb-triples)]

    (run-forward-rule-sparql-string source-kb source-kb rule1)
    (run-forward-rule-sparql-string source-kb source-kb rule2)
    (run-forward-rule-sparql-string source-kb source-kb rule3)
    (run-forward-rule-sparql-string source-kb source-kb rule4)
    (run-forward-rule-sparql-string source-kb source-kb rule5)
    (run-forward-rule-sparql-string source-kb source-kb rule6)
    (run-forward-rule-sparql-string source-kb source-kb rule7)
    (run-forward-rule-sparql-string source-kb source-kb rule8)
    (run-forward-rule-sparql-string source-kb source-kb rule9)
    (run-forward-rule-sparql-string source-kb source-kb rule10)
    (run-forward-rule-sparql-string source-kb source-kb rule11)
    (run-forward-rule-sparql-string source-kb source-kb rule12)
    (run-forward-rule-sparql-string source-kb source-kb rule13)
    (run-forward-rule-sparql-string source-kb source-kb rule14)

    ;; there should be 2 new triples list_recored instances
    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 obo/CHEBI_7890)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/CHEBI_7890)))))) ;; ccp:ontology concept identifier

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 hgnc_pr/gene_symbol_report?hgnc_id=26222)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/HGNC_26222)))))) ;; ccp:ontology concept identifier

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 pombase_pr/SPMIT.11)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/POMBASE_SPMIT.11))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 cgnc_pr/GeneReport?id=10114)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/CGNC_10114))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 rgd_pr/main.html?id=7676737)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/RGD_7676737))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 tair_pr/TairObject?type=locus&name=locus:6530298248)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/TAIR_LOCUS_6530298248))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 sgd_pr/locus.fpl?dbid=S000122558)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/SGD_S000122558))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 flybase_pr/FBgn0000014)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/FLYBASE_FBgn0000014))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 zfin_pr/ZDB-GENE-991213-3)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/ZFIN_ZDB-GENE-991213-3))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 wormbase_pr/WBGene00185048)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/WORMBASE_WBGene00185048))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 eco_pr/EG10001)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/ECOGENE_EG10001))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 eco_pr/EG10001)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/ECOGENE_EG10001))))))

    ;; not sure how to test ncbi gene id gen b/c symbols can't start with numbers in clojure
    ;(is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 ~http://www.ncbi.nlm.nih.gov/gene/380481)
    ;                                    (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
    ;                                    (= ?/id ccp/NCBI_GENE_380481)))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 mgi_pr/MGI:101757)
                                            (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                            (= ?/id ccp/MGI_101757))))))

    (is (= 1 (count (query source-kb '((?/id obo/IAO_0000219 obo/dictyBase#_DDB_G0349043)
                                        (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)
                                        (= ?/id ccp/DICTYBASE_DDB_G0349043))))))


        ;; The code fragment below is useful for debugging as it writes
    ;; triples to a local file.
    (let [log-kb (output-kb "/tmp/triples.nt")]
      ;; add sample triples to the log kb
      (dorun (map (partial add! log-kb) sample-kb-triples))

      (run-forward-rule-sparql-string source-kb log-kb rule1)
      (run-forward-rule-sparql-string source-kb log-kb rule2)
      (run-forward-rule-sparql-string source-kb log-kb rule3)
      (run-forward-rule-sparql-string source-kb log-kb rule4)
      (run-forward-rule-sparql-string source-kb log-kb rule5)
      (run-forward-rule-sparql-string source-kb log-kb rule6)
      (run-forward-rule-sparql-string source-kb log-kb rule7)
      (run-forward-rule-sparql-string source-kb log-kb rule8)
      (run-forward-rule-sparql-string source-kb log-kb rule9)
      (run-forward-rule-sparql-string source-kb log-kb rule10)
      (run-forward-rule-sparql-string source-kb log-kb rule11)
      (run-forward-rule-sparql-string source-kb log-kb rule12)
      (run-forward-rule-sparql-string source-kb log-kb rule13)
      (run-forward-rule-sparql-string source-kb log-kb rule14)
      (close log-kb))
    ))
