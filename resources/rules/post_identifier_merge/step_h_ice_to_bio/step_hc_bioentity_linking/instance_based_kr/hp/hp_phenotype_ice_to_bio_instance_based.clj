;; -------------------------------------------------------------------------
;; --------- Human Phenotype Ontology Phenotype Concept Assignment ---------
;; -------------------------------------------------------------------------
`{:name          "hp-phenotype-ice-to-bio-instance-based-kr"
  :description   "This rule creates a subclass of every human phenotype and types it as a human phenotype concept identifier (IAO_EXT_0000208)"
  :head          (
                   ;; create an instance of the human phenotype
                   (?/phenotype_instance rdf/type ?/human_phenotype)

                   ;; create an instance of the participating bioentity
                   (?/bioentity_instance rdf/type ?/causal_bioentity)

                   (?/bioentity_instance ?/cause_or_contributes_to_condition ?/phenotype_instance)

                   ;; provenance: connect the record to the human phenotype instance
                   (?/record obo/IAO_0000219 ?/bioentity_instance)) ; IAO_denotes

  :reify         ([?/phenotype_instance {:ln (:sha-1 ?/human_phenotype ?/causal_bioentity "pheno")
                                   :ns "ccp" :prefix "B_"}]
                   [?/bioentity_instance {:ln (:sha-1 ?/human_phenotype ?/causal_bioentity)
                                    :ns "ccp" :prefix "B_"}])

  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  SELECT ?causal_bioentity ?human_phenotype ?record ?cause_or_contributes_to_condition ?human_phenotype_label
                  WHERE {  ?human_phenotype_identifier rdfs:subClassOf ccp:IAO_EXT_0000208 . # CCP:Human_Phenotype_Ontology_concept_identifier
                           ?human_phenotype_identifier obo:IAO_0000219 ?human_phenotype .
                           # ensure it is a kabob bioentity
                           ?id_set obo:IAO_0000142 ?human_phenotype . # obo:mentions
                           ?id_set rdf:type ccp:IAO_EXT_0000316 . # ccp:identifier_set
                           # get the hp label
                           ?human_phenotype rdfs:label ?human_phenotype_label .

                           ?hp_identifier_field_value rdf:type ?human_phenotype_identifier .
                           ?hp_identifier_field_value rdf:type ccp:IAO_EXT_0000701 .  # CCP:HPO_annotation_record__HPO_term_field_value
                           ?record obo:BFO_0000051 ?hp_identifier_field_value .
                           ?record rdf:type ccp:IAO_EXT_0000029 . # CCP:HPO_annotation_record
                           ?record obo:BFO_0000051 ?bioentity_identifier_field_value .
                           ?bioentity_identifier_field_value rdf:type ccp:IAO_EXT_0000698 . # CCP:HPO_annotation_record__gene_identifier_field_value
                           ?bioentity_identifier_field_value rdf:type ?bioentity_identifier .
                           ?bioentity_identifier obo:IAO_0000219 ?causal_bioentity .

                           {
                             select ?cause_or_contributes_to_condition {
                                    ccp:RO_0003302 obo:IAO_0000219 ?cause_or_contributes_to_condition .
                                    filter (?cause_or_contributes_to_condition != obo:RO_0003302) .
                                   }
                           }
                        }"

  }