(ns rules-tests.build-test.test-build-util
  (:require [kabob.build.run-rules :refer [run-forward-rule-sparql-string]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
            [kr.core.kb :refer [kb open]]
            [kabob.core.namespace :refer [*namespaces*]]
            [rules-tests.build-test.input-data :refer [go-triples cl-triples chebi-triples ncbitaxon-triples so-triples
                                                       pr-triples ro-triples uberon-triples mi-triples uniprot-id-mapping-record-triples
                                                       ncbi-uniprot-collab-triples hgnc-triples ncbi-gene-info-triples
                                                       pro-mapping-record-triples refseq-release-catalog-triples]]
            [rules-tests.build-test.input-data-uniprot :refer [uniprot-record-triples]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-triples.goa-triples :refer [goa-triples]]))


(def initial-triples (concat go-triples cl-triples chebi-triples ncbitaxon-triples so-triples pr-triples
                             ro-triples ccp-ext-ontology-triples uberon-triples mi-triples))
(def ice-triples (concat uniprot-record-triples uniprot-id-mapping-record-triples ncbi-gene-info-triples
                         ncbi-uniprot-collab-triples hgnc-triples pro-mapping-record-triples refseq-release-catalog-triples goa-triples))
(def initial-plus-ice-triples (concat initial-triples ice-triples))

(def root-concepts #{"BFO_0000001" "SO_0000400" "SO_0000110" "NCBITaxon_1" "CHEBI_24431" "GO_0005575" "GO_0008150"
                     "UBERON_0008307" "MI_0000" "MI_0001"})
(def root-object-properties #{"RO_0000056" "RO_0000057" "RO_0000058" "RO_0000059" "RO_0001000"
                              "RO_0001001" "RO_0001015" "RO_0001025" "RO_0002000" "RO_0002002"
                              "RO_0000053" "RO_0000052" "BFO_0000067" "BFO_0000066" "BFO_0000054" "BFO_0000055"
                              "so#part_of" "RO_0002320" "RO_0002323" "RO_0002328" "RO_0002609" "RO_0002324"
                              "pr#has_gene_template" "RO_0002339" "mi#part_of"})


(def go-bp-concepts #{"GO_0008150" "GO_0009987" "GO_0044699" "GO_0044763" "GO_0007049" "GO_0019226" "GO_0022402" "GO_0051179"})
(def go-cc-concepts #{"GO_0005623" "GO_0005575"})
(def go-concepts (concat go-bp-concepts go-cc-concepts))
(def cl-concepts #{"CL_0000000" "CL_0000540" "CL_0000031" "CL_0000047"})
(def chebi-concepts #{"CHEBI_24431" "CHEBI_50860" "CHEBI_50047" "CHEBI_33694" "CHEBI_33695" "CHEBI_36080"})
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
                      obi-concepts pato-concepts gaz-concepts hgnc-concepts uberon-concepts mi-concepts))


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
                       "UNIPROTENTRYNAME_1433E_HUMAN"})

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
                                  ("BFO_0000142" "BFO_0000140") ("BFO_0000146" "BFO_0000140") ("GAZ_00000448" "BFO_0000029")
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
(def expected-restrictions `#{("ccp/GO_0022402" "obo/GO_0022402" "BFO_0000050" "owl/someValuesFrom" "ccp/GO_0007049")
                              ("ccp/CL_0000540" "obo/CL_0000540" "RO_0002202" "owl/someValuesFrom" "ccp/CL_0000031")
                              ("ccp/CL_0000540" "obo/CL_0000540" "RO_0002215" "owl/someValuesFrom" "ccp/GO_0019226")
                              ("ccp/PR_P37173" "obo/PR_P37173" "RO_0002160" "owl/someValuesFrom" "ccp/NCBITaxon_9606")
                              ("ccp/PR_P37173-1" "obo/PR_P37173-1" "RO_0002160" "owl/someValuesFrom" "ccp/NCBITaxon_9606")
                              ("ccp/HGNC_11773" "hgnc_pr/gene_symbol_report?hgnc_id=11773" "RO_0002160" "owl/someValuesFrom" "ccp/NCBITaxon_9606")
                              ("ccp/PR_P37173" "obo/PR_P37173" "pr#has_gene_template" "owl/someValuesFrom" "ccp/HGNC_11773")
                              ("ccp/MI_0001" "obo/MI_0001" "mi#part_of" "owl/someValuesFrom" "ccp/MI_0000")
                              })

(def expected-restrictions-in-lists `#{("ccp/GO_0022402" "obo/GO_0022402" "BFO_0000050" "owl/someValuesFrom" "ccp/GO_0007049" "owl/equivalentClass owl/intersectionOf rdf/rest rdf/first")
                                       ("ccp/PR_P37173" "obo/PR_P37173" "RO_0002160" "owl/someValuesFrom" "ccp/NCBITaxon_9606" "owl/equivalentClass owl/intersectionOf rdf/rest rdf/first")})


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


(def build-rules-step-a '("rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_ontology_root_identifier"
                           "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept"
                           "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing"
                           "rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen"))

(def build-rules-step-b '("rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref"
                           "rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class"
                           "rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label"))

(def build-rules-step-c '("rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_genomic_identifier_typing"
                           "rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_mrna_identifier_typing"
                           "rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_protein_identifier_typing"
                           "rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/refseq_rna_identifier_typing"
                           "rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing/obsolete_uniprot_identifier_typing"))

(def build-rules-step-da '(
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/drugbank_drug_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/hgnc_gene_symbol_entrez_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/hgnc_gene_symbol_hgnc_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/hgnc_gene_symbol_supplied_entrez_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/pharmgkb_drug_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/pharmgkb_gene_entrez_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/pro_protein_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/uniprot_isoform_to_refseq_identifier_exact_match"
                            "rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match/uniprot_primary_accession_to_uniprot_entry_name_exact_match"
                            ))

(def build-rules-step-db '("rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_db_more_identifier_exact_match/refseq_protein_uniprot_identifier_exact_match"))

(def build-rules-step-fa '("rules/post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links/identifier_denotes_bioentity"
                            "rules/post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links/idset_mentions_bioentity"))

(def build-rules-step-fb '("rules/post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links/obsolete_uniprot_identifier_denotes_bioentity"))

(def build-rules-step-ga '("rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_list_nodes_to_bio"
                            "rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_owl_alldisjointclasses_to_bio"
                            "rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_owl_restriction_to_bio"
                            "rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio/copy_anonymous_nodes_to_bio"))

(def build-rules-step-gb '("rules/post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/transfer_ontology_labels"))

(def build-rules-step-gc '("rules/post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/transfer_ontology_links"
                            "rules/post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/transfer_links_to_nil"))


(def build-rules-step-ha '("rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_drugbank"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_gene"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_mrna"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_pharmgkb_drug"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_protein"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/orphan_bioentity_typing_rna"))


(def build-rules-step-hb '("rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/drugbank_drug_labels"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/ncbi_gene_labels"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/uniprot_swissprot_protein_labels"
                            "rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling/uniprot_trembl_protein_labels"))

(def build-rules-step-hc-central-dogma '("rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/central_dogma/uniprot_protein_entrez_gene_mapping"))

(def build-rules-step-hc-goa '("rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/goa/goa_biological_process_ice_to_bio"
                                "rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/goa/goa_cellular_component_ice_to_bio"
                                "rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/goa/goa_molecular_function_ice_to_bio"))

(def build-rules-step-ia '("rules/post_identifier_merge/step_i_bioentity_abstractions/step_ia_generate_missing_genes/missing_gene_gen."))

(def build-rules-step-ib '("rules/post_identifier_merge/step_i_bioentity_abstractions/step_ib_generate_gene_abstractions/gene_abstraction_gen"))

(def build-rules-step-ic '("rules/post_identifier_merge/step_i_bioentity_abstractions/step_ic_link_to_gp_abstractions/link_proteins_to_gp_abstractions"))




(defn run-build-rules
  ([source-kb build-rules thru-rule-index]
   "run all build rules up to and including the thru-rule-index (zero-based) rule and populate the results in source-kb"
   (let [rule-paths (take (inc thru-rule-index) build-rules)]
     (doall (map (fn [rule-path]
                   (let [rules (kabob-load-rules-from-classpath rule-path)]
                     (prn (str "^^^^^ Running rule: " rule-path))
                     (dorun (map (partial run-forward-rule-sparql-string source-kb source-kb) rules)))) rule-paths))))

  ([source-kb build-rules]
   (doall (map (fn [rule-path]
                 (let [rules (kabob-load-rules-from-classpath rule-path)]
                   (prn (str "^^^^^ Running rule: " rule-path))
                   (dorun (map (partial run-forward-rule-sparql-string source-kb source-kb) rules)))) build-rules))))

(defn run-build-rule [source-kb target-kb build-rules rule-index]
  "run a single build rule against source-kb and populate the target-kb with the rule output"
  (let [rule-path (nth build-rules rule-index)
        rules (kabob-load-rules-from-classpath rule-path)]
    (prn (str "******** Running rule: " rule-path))
    (dorun (map (partial run-forward-rule-sparql-string source-kb target-kb) rules))))

(defn test-kb [triples]
  "initializes a kb and loads the initial ontology file triples"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))

