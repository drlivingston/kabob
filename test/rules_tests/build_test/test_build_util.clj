(ns rules-tests.build-test.test-build-util
  (:require [kabob.build.run-rules :refer [run-forward-rule]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! *graph*]]
            [kr.core.kb :refer [kb open]]
            [kabob.core.namespace :refer [*namespaces*]]
            [rules-tests.build-test.input-data :refer [go-triples cl-triples chebi-triples ncbitaxon-triples so-triples
                                                       pr-triples ro-triples uberon-triples mi-triples uniprot-id-mapping-record-triples
                                                       ncbi-uniprot-collab-triples hgnc-triples ncbi-gene-info-triples
                                                       pro-mapping-record-triples refseq-release-catalog-triples reactome-biopax-triples]]
            [rules-tests.build-test.input-data-uniprot :refer [uniprot-record-triples]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-triples.goa-triples :refer [goa-triples]]))


(def initial-triples (concat go-triples cl-triples chebi-triples ncbitaxon-triples so-triples pr-triples
                             ro-triples uberon-triples mi-triples))
(def ice-triples (concat uniprot-record-triples uniprot-id-mapping-record-triples ncbi-gene-info-triples
                         ncbi-uniprot-collab-triples hgnc-triples pro-mapping-record-triples refseq-release-catalog-triples goa-triples
                         reactome-biopax-triples))
(def initial-plus-ice-triples (concat initial-triples ice-triples))

;(def root-concepts #{"BFO_0000001" "SO_0000400" "SO_0000110" "NCBITaxon_1" "CHEBI_24431" "GO_0005575" "GO_0008150"
;                     "UBERON_0008307" "MI_0000" "MI_0001"})
;(def root-object-properties #{"RO_0000056" "RO_0000057" "RO_0000058" "RO_0000059" "RO_0001000"
;                              "RO_0001001" "RO_0001015" "RO_0001025" "RO_0002000" "RO_0002002"
;                              "RO_0000053" "RO_0000052" "BFO_0000067" "BFO_0000066" "BFO_0000054" "BFO_0000055"
;                              "so#part_of" "RO_0002320" "RO_0002323" "RO_0002328" "RO_0002609" "RO_0002324"
;                              "pr#has_gene_template" "RO_0002339" "mi#part_of"})


(def go-bp-concepts #{"GO_0008150" "GO_0009987" "GO_0044699" "GO_0044763" "GO_0007049" "GO_0019226" "GO_0022402" "GO_0051179"})
(def go-cc-concepts #{"GO_0005623" "GO_0005575"})
(def go-concepts (concat go-bp-concepts go-cc-concepts))
(def cl-concepts #{"CL_0000000" "CL_0000540" "CL_0000031" "CL_0000047"})
(def chebi-concepts #{"CHEBI_24431" "CHEBI_50860" "CHEBI_50047" "CHEBI_33694" "CHEBI_33695" "CHEBI_36080" "CHEBI_10003"})
(def ncbitaxon-concepts #{"NCBITaxon_9606" "NCBITaxon_1"})
(def so-concepts #{"SO_0001217" "SO_0000704" "SO_0000010" "SO_0001411" "SO_0000001"
                   "SO_0000110" "SO_0005855" "SO_0000401" "SO_0000733" "SO_0000400"})
(def pr-concepts #{"PR_000018263" "PR_000000001" "PR_000000005" "PR_P37173" "PR_P37173-1"})
(def bfo-concepts #{"BFO_0000001" "BFO_0000002" "BFO_0000031" "BFO_0000004" "BFO_0000040" "BFO_0000024"
                    "BFO_0000030" "BFO_0000141" "BFO_0000027" "BFO_0000140" "BFO_0000147" "BFO_0000142"
                    "BFO_0000146" "BFO_0000029" "BFO_0000006" "BFO_0000018" "BFO_0000026" "BFO_0000009"
                    "BFO_0000028" "BFO_0000020" "BFO_0000019" "BFO_0000017" "BFO_0000145" "BFO_0000016"
                    "BFO_0000023" "BFO_0000034" "BFO_0000003" "BFO_0000015" "BFO_0000182" "BFO_0000144"
                    "BFO_0000035" "BFO_0000011" "BFO_0000008" "BFO_0000148" "BFO_0000038"})
(def obi-concepts #{"OBI_0000011" "OBI_0000066" "OBI_0000471"})
(def pato-concepts #{"PATO_0000122" "PATO_0000125"})
(def gaz-concepts #{"GAZ_00000448"})
(def hgnc-concepts #{"HGNC_11773"})
(def uberon-concepts #{"UBERON_0008307"})                   ;; brought in via the GOA ICE RDF
(def mi-concepts #{"MI_0000" "MI_0001"})

(def concepts (concat go-concepts cl-concepts chebi-concepts ncbitaxon-concepts so-concepts pr-concepts bfo-concepts
                      obi-concepts
                      pato-concepts
                      ;gaz-concepts
                      hgnc-concepts uberon-concepts mi-concepts))


(def object-properties #{"BFO_0000050" "BFO_0000051" "RO_0002324" "RO_0002384" "RO_0002387" "RO_0002286"
                         "RO_0002388" "RO_0002202" "RO_0002203" "RO_0002258" "RO_0002609" "RO_0002410"
                         "RO_0002595" "RO_0002500" "RO_0002328" "RO_0002216" "RO_0002215" "RO_0002323"
                         "RO_0002131" "RO_0002320" "RO_0002162" "RO_0002160" "so#part_of" "RO_0002350"
                         "RO_0002351" "RO_0000056" "RO_0000057" "RO_0000058" "RO_0000059" "RO_0001000"
                         "RO_0001001" "RO_0001015" "RO_0001025" "RO_0002000" "RO_0002002"
                         "RO_0000053" "RO_0000052" "BFO_0000067" "BFO_0000066" "BFO_0000054" "BFO_0000055"
                         "OBI_0000293" "OBI_0000295" "OBI_0000299" "OBI_0000312" "RO_0000079" "RO_0000080"
                         "RO_0000081" "RO_0000085" "RO_0000086" "RO_0000087" "RO_0000091" "RO_0000092"
                         "pr#has_gene_template" "RO_0002339" "RO_0002313" "mi#part_of"})

(def ice-identifiers #{"UNIPROT_P37173" "UNIPROT_P37173-1" "UNIPROT_P37173-2"
                       "UNIPROT_Q9UER7" "UNIPROT_P62258" "UNIPROT_P62258-1" "HGNC_11773" "HGNC_TGFBR2" "NCBI_GENE_7048"
                       "REFSEQ_NP_001020018" "REFSEQ_NP_003233" "REFSEQ_NP_006752" "UNIPROTENTRYNAME_TGFR2_HUMAN"
                       "UNIPROTENTRYNAME_1433E_HUMAN" "NCBI_GENE_1" "HGNC_5" "HGNC_A1BG"})

(def other-identifiers-mentioned-in-records #{"CCDS_CCDS2648"
                                              "CCDS_CCDS33727"
                                              "CCDS_CCDS12976"
                                              "COSMIC_A1BG"
                                              "EMBL_AAT70724"
                                              "EMBL_AAA61164"
                                              "EMBL_BAA05673"
                                              "EMBL_AAB40916"
                                              "EMBL_U20972"
                                              "ENSEMBL_ENSG00000163513"
                                              "ENSEMBL_ENSP00000351905"
                                              "ENSEMBL_ENSG00000121410"
                                              "ENZYMECOMMISSION_"
                                              "MGI_98729"
                                              "NCBI_GI_290560370"
                                              "NCBI_GI_290560376"
                                              "NCBI_GI_1026943654"
                                              "NCBI_GI_67782326"
                                              "NCBI_GI_67782324"
                                              "NCBI_GI_5803225"
                                              "OMIM_610168" "OMIM_133239" "OMIM_614331" "OMIM_190182"
                                              "ORDO_120069"
                                              "PROTEIN_DATA_BANK_4XJJ_A" "PROTEIN_DATA_BANK_1M9Z_A" "PROTEIN_DATA_BANK_3KFD_G"
                                              "RGD_69651"
                                              "UCSCGENOMEBROWSER_uc003ceo.4"
                                              "UNIGENE_Hs.82028"
                                              "UNIPARC_UPI000011DD7E"
                                              "UNIPROT_A2AGH6"
                                              "UNIPROT_B4DTV5"
                                              "UNIPROT_P01137"
                                              "UNIPROT_P07200"
                                              "UNIPROT_P10600"
                                              "UNIPROT_Q15580"
                                              "UNIPROT_Q62312"
                                              "UNIPROT_Q6DKT6"
                                              "UNIPROT_Q8IX30"
                                              "UNIPROT_Q93074"
                                              "UNIPROT_Q99474"
                                              "UNIREF_UniRef100_P37173"
                                              "VEGA_OTTHUMG00000130569"})

(def obsolete-ice-identifiers #{"UNIPROT_B4DTV5" "UNIPROT_Q15580" "UNIPROT_Q6DKT6" "UNIPROT_Q99474"})



(def expected-subpropertyof-links `#{("RO_0002215" "RO_0002216") ("RO_0002216" "RO_0002328") ("RO_0002216" "RO_0002500")
                                     ("RO_0002500" "RO_0002595") ("RO_0002595" "RO_0002410") ("RO_0002410" "RO_0002609")
                                     ("BFO_0000050" "RO_0002131") ("BFO_0000051" "RO_0002131") ("RO_0002131" "RO_0002323")
                                     ("RO_0002160" "RO_0002162") ("RO_0002162" "RO_0002320") ("RO_0002203" "RO_0002388")
                                     ("RO_0002203" "RO_0002387") ("RO_0002203" "RO_0002286") ("RO_0002286" "RO_0002384") ("RO_0002388" "RO_0002387")
                                     ("RO_0002387" "RO_0002384") ("RO_0002384" "RO_0002324") ("RO_0002202" "RO_0002258")
                                     ("RO_0002258" "RO_0002324") ("OBI_0000293" "RO_0000057") ("OBI_0000295" "RO_0000056")
                                     ("OBI_0000299" "RO_0000057") ("OBI_0000312" "RO_0000056") ("RO_0000079" "RO_0000052")
                                     ("RO_0000080" "RO_0000052") ("RO_0000081" "RO_0000052") ("RO_0000085" "RO_0000053")
                                     ("RO_0000086" "RO_0000053") ("RO_0000087" "RO_0000053") ("RO_0000091" "RO_0000053")
                                     ("RO_0000092" "RO_0000052") ("RO_0002350" "BFO_0000050") ("RO_0002351" "BFO_0000051")
                                     ("RO_0002313" "RO_0000057")})

(def expected-inverseof-links `#{("RO_0002258" "RO_0002286") ("RO_0002202" "RO_0002203") ("BFO_0000050" "BFO_0000051")
                                 ("BFO_0000054" "BFO_0000055") ("BFO_0000066" "BFO_0000067") ("OBI_0000293" "OBI_0000295")
                                 ("OBI_0000299" "OBI_0000312") ("RO_0000052" "RO_0000053") ("RO_0000056" "RO_0000057")
                                 ("RO_0000058" "RO_0000059") ("RO_0000079" "RO_0000085") ("RO_0000080" "RO_0000086")
                                 ("RO_0000081" "RO_0000087") ("RO_0000091" "RO_0000092") ("RO_0001000" "RO_0001001")
                                 ("RO_0001015" "RO_0001025") ("RO_0002000" "RO_0002002") ("RO_0002350" "RO_0002351")
                                 })


(def expected-subclassof-links `#{("GO_0022402" "GO_0009987") ("GO_0007049" "GO_0044763") ("GO_0044763" "GO_0044699")
                                  ("GO_0044763" "GO_0009987") ("GO_0009987" "GO_0008150") ("GO_0044699" "GO_0008150")
                                  ("GO_0019226" "GO_0008150") ("GO_0005623" "GO_0005575") ; b/c cl_0000000 & go_0005623 get merged, we only need to account for one of their subclass relations ("CL_0000000" "GO_0005575")
                                  ("CL_0000540" "CL_0000000") ("CL_0000031" "CL_0000047") ("CL_0000047" "CL_0000000")
                                  ("NCBITaxon_9606" "NCBITaxon_1") ("HGNC_11773" "SO_0001217") ("SO_0001217" "SO_0000704")
                                  ("SO_0000704" "SO_0001411") ("SO_0005855" "SO_0001411") ("SO_0001411" "SO_0000001")
                                  ("SO_0000001" "SO_0000110") ("SO_0000010" "SO_0000401") ("SO_0000401" "SO_0000733") ("SO_0000733" "SO_0000400")
                                  ("PR_P37173-1" "PR_P37173") ("PR_P37173" "PR_000000005") ("PR_000000005" "PR_000000001") ("PR_000000001" "PR_000018263")
                                  ("PR_000018263" "CHEBI_50047") ("CHEBI_50047" "CHEBI_50860") ("CHEBI_50860" "CHEBI_24431")
                                  ("CHEBI_36080" "CHEBI_33695") ("CHEBI_33695" "CHEBI_33694") ("CHEBI_33694" "CHEBI_50860")
                                  ("BFO_0000147" "BFO_0000140") ("BFO_0000002" "BFO_0000001") ("BFO_0000003" "BFO_0000001")
                                  ("BFO_0000020" "BFO_0000002")
                                  ("BFO_0000142" "BFO_0000140") ("BFO_0000146" "BFO_0000140") ;("GAZ_00000448" "BFO_0000029")
                                  ("BFO_0000140" "BFO_0000141") ("BFO_0000029" "BFO_0000141") ("BFO_0000006" "BFO_0000141")
                                  ("BFO_0000018" "BFO_0000006") ("BFO_0000026" "BFO_0000006") ("BFO_0000009" "BFO_0000006")
                                  ("BFO_0000028" "BFO_0000006") ("BFO_0000141" "BFO_0000004") ("BFO_0000004" "BFO_0000002")
                                  ("BFO_0000031" "BFO_0000002") ("BFO_0000040" "BFO_0000004") ("BFO_0000024" "BFO_0000040")
                                  ("BFO_0000027" "BFO_0000040") ("BFO_0000030" "BFO_0000040") ("BFO_0000145" "BFO_0000019")
                                  ("PATO_0000122" "BFO_0000019") ("PATO_0000125" "BFO_0000019") ("BFO_0000019" "BFO_0000020")
                                  ("BFO_0000017" "BFO_0000020") ("BFO_0000016" "BFO_0000017") ("BFO_0000023" "BFO_0000017")
                                  ("BFO_0000034" "BFO_0000016") ("OBI_0000066" "OBI_0000011") ("OBI_0000471" "OBI_0000011")
                                  ("OBI_0000011" "BFO_0000015") ("BFO_0000182" "BFO_0000015") ("BFO_0000144" "BFO_0000015")
                                  ("BFO_0000015" "BFO_0000003") ("BFO_0000148" "BFO_0000008") ("BFO_0000038" "BFO_0000008")
                                  ("BFO_0000008" "BFO_0000003") ("BFO_0000035" "BFO_0000003") ("BFO_0000011" "BFO_0000003")
                                  ("GO_0051179" "GO_0008150")})

(def expected-equivalent-class-links `#{("GO_0005623" "CL_0000000")})

(def expected-disjointwith-links `#{("SO_0000110" "SO_0000400") ("BFO_0000002" "BFO_0000003") ("BFO_0000004" "BFO_0000020")
                                    ("BFO_0000004" "BFO_0000031") ("BFO_0000006" "BFO_0000029") ("BFO_0000006" "BFO_0000140")
                                    ("BFO_0000008" "BFO_0000011") ("BFO_0000008" "BFO_0000015") ("BFO_0000008" "BFO_0000035")
                                    ("BFO_0000009" "BFO_0000028") ("BFO_0000016" "BFO_0000023") ("BFO_0000017" "BFO_0000019")
                                    ("BFO_0000018" "BFO_0000028") ("BFO_0000020" "BFO_0000031") ("BFO_0000026" "BFO_0000028")
                                    ("BFO_0000038" "BFO_0000148") ("BFO_0000040" "BFO_0000141") ("BFO_0000142" "BFO_0000146")
                                    ("BFO_0000142" "BFO_0000147") ("BFO_0000144" "BFO_0000182")})



;; TODO: GO_0022402, PR_P37173 restriction is part of a list structure
;; expected restrictions are listed as quads below.
;; 1) subclass of the restriction
;; 2) property used in the restriction (via owl:onProperty)
;; 3) values-from property (e.g. owl:someValuesFrom)
;; 4) object of the values-from property
(def expected-restrictions `#{("kice/GO_0022402" "obo/GO_0022402" "BFO_0000050" "owl/someValuesFrom" "kice/GO_0007049")
                              ("kice/CL_0000540" "obo/CL_0000540" "RO_0002202" "owl/someValuesFrom" "kice/CL_0000031")
                              ("kice/CL_0000540" "obo/CL_0000540" "RO_0002215" "owl/someValuesFrom" "kice/GO_0019226")
                              ("kice/PR_P37173" "obo/PR_P37173" "RO_0002160" "owl/someValuesFrom" "kice/NCBITaxon_9606")
                              ("kice/PR_P37173-1" "obo/PR_P37173-1" "RO_0002160" "owl/someValuesFrom" "kice/NCBITaxon_9606")
                              ("kice/HGNC_11773" "obo/HGNC_11773" "RO_0002160" "owl/someValuesFrom" "kice/NCBITaxon_9606")
                              ("kice/PR_P37173" "obo/PR_P37173" "pr#has_gene_template" "owl/someValuesFrom" "kice/HGNC_11773")
                              ("kice/MI_0001" "obo/MI_0001" "mi#part_of" "owl/someValuesFrom" "kice/MI_0000")
                              })

(def expected-restrictions-in-lists `#{("kice/GO_0022402" "obo/GO_0022402" "BFO_0000050" "owl/someValuesFrom" "kice/GO_0007049" "owl/equivalentClass owl/intersectionOf rdf/rest rdf/first")
                                       ("kice/PR_P37173" "obo/PR_P37173" "RO_0002160" "owl/someValuesFrom" "kice/NCBITaxon_9606" "owl/equivalentClass owl/intersectionOf rdf/rest rdf/first")})


(def list-blank-nodes #{"bnode/go_genid60570" "bnode/go_genid60568" "bnode/pr_genid469417" "bnode/pr_genid469415"
                        "bnode/ro_genid142" "bnode/ro_genid141"}); "bnode/ccp-extensions_genid8" "node/ccp-extensions_genid7"
                        ;"bnode/ccp-extensions_genid10" "bnode/ccp-extensions_genid9"})
(def restriction-blank-nodes #{"bnode/go_genid226412" "bnode/go_genid226413" "bnode/cl_genid1536" "bnode/cl_genid1537"
                               "bnode/pr_genid1418675" "bnode/pr_genid1800188" "bnode/pr_genid1800189" "bnode/pr_genid469418"
                               "bnode/pr_genid1800190" "bnode/mi_genid7"})
(def intersection-of-blank-nodes #{"bnode/go_genid60567" "pr_genid469414"})


(def expected-rdfs-domain-links `#{("RO_0002258" "BFO_0000002") ("RO_0002202" "BFO_0000004") ("RO_0002595" "BFO_0000040")
                                   ("RO_0002215" "BFO_0000004") ("BFO_0000054" "BFO_0000017") ("BFO_0000055" "BFO_0000015")
                                   ("BFO_0000066" "BFO_0000003") ("RO_0000056" "BFO_0000002") ("RO_0000057" "BFO_0000003")
                                   ("RO_0000058" "BFO_0000031") ("RO_0000059" "BFO_0000020") ("RO_0000085" "BFO_0000004")
                                   ("RO_0000087" "BFO_0000004") ("RO_0000091" "BFO_0000004") ("RO_0002002" "BFO_0000040")
                                   ("OBI_0000293" "OBI_0000011") ("OBI_0000299" "OBI_0000011") ("RO_0000079" "BFO_0000034")})

(def expected-rdfs-range-links `#{("RO_0002258" "BFO_0000002") ("RO_0002202" "BFO_0000004") ("RO_0002595" "BFO_0000015")
                                  ("RO_0002215" "BFO_0000015") ("RO_0002162" "BFO_0000004") ("BFO_0000054" "BFO_0000015")
                                  ("BFO_0000055" "BFO_0000017") ("BFO_0000066" "BFO_0000004") ("RO_0000053" "BFO_0000020")
                                  ("RO_0000056" "BFO_0000003") ("RO_0000057" "BFO_0000002") ("RO_0000058" "BFO_0000020")
                                  ("RO_0000059" "BFO_0000031") ("RO_0000085" "BFO_0000034") ("RO_0000086" "BFO_0000019")
                                  ("RO_0000087" "BFO_0000023") ("RO_0000091" "BFO_0000016") ("RO_0002002" "BFO_0000141")
                                  ("OBI_0000312" "OBI_0000011")})


(def build-rules-step-a '("rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept_obo_ns"
                           "rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing"
                           "rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen"))

(def build-rules-step-b '(;; DEPRECATED "rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref"
                           "rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class"
                           "rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label"))



(def build-rules-step-ca '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_ca_reactome_biopax2ice/reactome_protein_record_gen_create_base_records"))

(def build-rules-step-cb '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_cellular_location_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_displayname_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_located_modification_feature_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_member_physical_entity_reference_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_name_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_removed_fragment_feature_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_uniprot_identifier_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_uniprot_isoform_identifier_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice/reactome_protein_record_gen_add_unlocated_protein_modification_feature_field"))

(def build-rules-step-cc '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cc_reactome_biopax2ice/reactome_protein_record_gen_add_display_names_from_protein_references_field"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cc_reactome_biopax2ice/reactome_protein_record_gen_add_names_from_protein_references_field"))


(def build-rules-step-da '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/refseq_genomic_identifier_typing"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/refseq_mrna_identifier_typing"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/refseq_protein_identifier_typing"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/refseq_rna_identifier_typing"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/obsolete_uniprot_identifier_typing"
                            "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing/bio_identifier_typing"))

(def build-rules-step-db '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/drugbank_drug_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/hgnc_gene_symbol_entrez_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/hgnc_gene_symbol_hgnc_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/pharmgkb_drug_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/pharmgkb_gene_entrez_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/pro_protein_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/uniprot_isoform_to_refseq_identifier_exact_match"
                           "rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match/uniprot_primary_accession_to_uniprot_entry_name_exact_match"))


(def build-rules-step-dc '("rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_dc_more_identifier_exact_match/refseq_protein_uniprot_identifier_exact_match"))

(def build-rules-step-fa '("rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links/identifier_denotes_bioentity"
                            "rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links/idset_mentions_bioentity"))

(def build-rules-step-fb '("rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links/obsolete_uniprot_identifier_denotes_bioentity"))

(def build-rules-step-ga '("rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_list_nodes_to_bio"
                            "rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_owl_alldisjointclasses_to_bio"
                            "rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_owl_restriction_to_bio"
                            "rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_anonymous_nodes_to_bio"
                            "rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_anonymous_nodes_in_lists_to_bio"))

(def build-rules-step-gb '("rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/transfer_ontology_labels"))

(def build-rules-step-gca '("rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gca_links_to_nil/transfer_links_to_nil"))
(def build-rules-step-gcb '("rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcb_temp_link_ont_to_bio_concepts/temp_link_ont_to_bio_concepts"))
(def build-rules-step-gcc '("rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcc_transfer_ontology_links_to_bio/transfer_ontology_links"))


(def build-rules-step-ha '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_drugbank"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_gene"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_mrna"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_pharmgkb_drug"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_protein"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier/orphan_bioentity_typing_rna"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/biological_region_gene_type_assertions"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/protein_coding_gene_type_assertions"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/pseudo_gene_type_assertions"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_parent_class/protein_entity_direct_subclass_assertions"
                            ))


(def build-rules-step-hb '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/drugbank_drug_labels"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/ncbi_gene_labels"
                            "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/uniprot_protein_labels"))

(def build-rules-step-hca '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hca_central_dogma/uniprot_protein_entrez_gene_mapping"))


(def build-rules-step-hcb '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_gene_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_ncrna_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_protein_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_rrna_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_scrna_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_snorna_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_snrna_gen"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_generate_missing_ggp_entities/missing_trna_gen"))

(def build-rules-step-hcc '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_assign_taxon/ncbi/entrez_gene_assign_taxon"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_assign_taxon/refseq/refseq_catalog_assign_taxon"
                             "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_assign_taxon/uniprot/uniprot_swissprot_protein_assign_taxon"))

(def build-rules-step-hcd '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcd_generate_gene_abstractions/gene_abstraction_gen"))

(def build-rules-step-hce '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hce_link_to_gp_abstractions/link_proteins_to_gp_abstractions"))

(def build-rules-step-hd-goa '("rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/goa_biological_process_ice_to_bio"
                                "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/goa_cellular_component_ice_to_bio"
                                "rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/goa_molecular_function_ice_to_bio"))


(def validation-rules-list '("rules/validation/valid_owl/list/rdf_list_does_not_end_with_nil"
                              "rules/validation/valid_owl/list/rdf_list_has_multiple_rdf_first"
                              "rules/validation/valid_owl/list/rdf_list_has_multiple_rdf_rest"
                              "rules/validation/valid_owl/list/rdf_list_missing_first_and_rest"
                              "rules/validation/valid_owl/list/rdf_list_missing_rdf_first"
                              "rules/validation/valid_owl/list/rdf_list_missing_rdf_rest"
                              "rules/validation/valid_owl/list/rdf_list_not_rest_to_nil"))

(def validation-rules-restriction '("rules/validation/valid_owl/restriction/redundant_restriction_check"
                                     "rules/validation/valid_owl/restriction/restriction_has_multiple_owl_onproperty"
                                     "rules/validation/valid_owl/restriction/restriction_has_multiple_owl_somevaluesfrom"
                                     "rules/validation/valid_owl/restriction/restriction_missing_owl_onproperty"
                                     "rules/validation/valid_owl/restriction/restriction_missing_values_from"))

(defn run-build-rules
  ([source-kb build-rules thru-rule-index]
   "run all build rules up to and including the thru-rule-index (zero-based) rule and populate the results in source-kb"
   (let [rule-paths (take (inc thru-rule-index) build-rules)]
     (doall (map (fn [rule-path]
                   (let [rules (kabob-load-rules-from-classpath rule-path)]
                     (prn (str "^^^^^ Running rule: " rule-path))
                     (dorun (map (partial run-forward-rule source-kb source-kb) rules)))) rule-paths))))

  ([source-kb build-rules]
   (doall (map (fn [rule-path]
                 (let [rules (kabob-load-rules-from-classpath rule-path)]
                   (prn (str "^^^^^ Running rule: " rule-path))
                   (dorun (map (partial run-forward-rule source-kb source-kb) rules)))) build-rules))))

(defn run-build-rule [source-kb target-kb build-rules rule-index]
  "run a single build rule against source-kb and populate the target-kb with the rule output"
  (let [rule-path (nth build-rules rule-index)
        rules (kabob-load-rules-from-classpath rule-path)]
    (prn (str "******** Running rule: " rule-path))
    (dorun (map (partial run-forward-rule source-kb target-kb) rules))))

(defn test-kb [triples]
  "initializes a kb and loads the initial ontology file triples"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (binding [*graph* "http://default-graph"]
    (dorun (map (partial add! kb) triples)))
    kb))

