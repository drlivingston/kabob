;; -------------------------------------------------------------------------
;; --------- Human Phenotype Ontology Phenotype Concept Assignment ---------
;; -------------------------------------------------------------------------
`{:name "hp-phenotype-ice-to-bio"
  :description "This rule creates a subclass of every human phenotype and types it as a human phenotype concept identifier (IAO_EXT_0000208)"
  :head (
         ;; create a subclass of the human phenotype 
         (?/phenotype_sc rdfs/subClassOf ?/human_phenotype)
         ;; create a label for the human phenotype subclass
         (?/phenotype_sc rdfs/label ?/human_phenotype_label)
         
         ;; create a sublcass of the participating bioentity
         (?/bioentity_sc rdfs/subClassOf ?/participating_bioentity)
         ;; create a label for the bioentity subclass
         (?/bioentity_sc rdfs/label ?/bioentity_sc_label)
         
         ;; create a has_participant restriction
         (?/participant_restriction rdf/type owl/Restriction)
         (?/participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/participant_restriction owl/someValuesFrom ?/bioentity_sc)
         
         ;; connect the human phenotype subclass to the participation restriction
         (?/phenotype_sc rdfs/subClassOf ?/participant_restriction)
  
         ;; provenance: connect the record to the human phenotype subclass
         (?/record obo/IAO_0000219 ?/phenotype_sc)) ; IAO_denotes

  :body ((?/human_phenotype_ice_id rdfs/subClassOf ccp/IAO_EXT_0000208) ; ccp:Human_Phenotype_Ontology_concept_identifier
         (?/human_phenotype_ice_id obo/IAO_0000219 ?/human_phenotype) ; IAO:denotes
         (?/human_phenotype_field_value rdf/type ?/human_phenotype_ice_id)
         (?/human_phenotype_field_value rdf/type ccp/IAO_EXT_0000701) ; ccp:HPO_annotation_record__HPO_term_field_value
         (?/record obo/BFO_0000051 ?/human_phenotype_field_value) ; BFO:has_part
         (?/record rdf/type ccp/IAO_EXT_0000029) ; ccp:HPO_annotation_record

         ;; retrieve the process participant identifier
         (?/record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000698) ; ccp:HPO_annotation_record__gene_identifier_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/participating_bioentity)) ; IAO:denotes

  :reify ([?/phenotype_sc {:ln (:sha-1 ?/human_phenotype ?/participating_bioentity) 
                 :ns "ccp" :prefix "PHENO_"}]
          [?/bioentity_sc {:ln (:sha-1 ?/human_phenotype ?/participating_bioentity)
                    :ns "ccp" :prefix "B_"}]
          [?/participant_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }