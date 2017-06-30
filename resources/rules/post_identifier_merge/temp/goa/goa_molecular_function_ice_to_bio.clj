;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name "goa-molecular-function-ice-to-bio"
  :description "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head (
         ;; create a subclass of the molecular function
         (?/molecular_function_sc rdfs/subClassOf ?/molecular_function)
         ;; create a subclass of the participating bioentity
         (?/bioentity_sc rdfs/subClassOf ?/bioentity)

         ;; create a has_participant restriction
         (?/participation_restriction rdf/type owl/Restriction)
         (?/participation_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/participation_restriction  owl/someValuesFrom ?/bioentity_sc)

         ;; connect the process subclass to the participation restriction
         (?/molecular_function_sc rdfs/subClassOf ?/participation_restriction)

         ;; provenance: connect the record to the process subclass
         (?/record obo/IAO_0000219 ?/molecular_function_sc)) ; IAO:denotes
    
  :body ((?/go_ice_id rdfs/subClassOf ccp/IAO_EXT_0000199) ; ccp:Gene_Ontology_Concept_Identifier__GO_molecular_function_concept_identifier
         (?/go_ice_id obo/IAO_0000219 ?/molecular_function) ; IAO:denotes
         (?/id_field_value rdf/type ?/go_ice_id)
         (?/id_field_value rdf/type ccp/IAO_EXT_0000014) ; ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
         (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
         (?/record rdf/type ccp/IAO_EXT_0000007) ; ccp:GOAGAFv2.0AnnotationRecord

         ;; retrieve the process participant identifier
         (?/record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000010) ; ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/participating_bioentity) ; IAO:denotes

         ;; filter out the negations
          (:optional ((?/record obo/BFO_0000051 ?/qualifier_field_value) ; BFO:has_part
                      (?/qualifier_field_value rdf/type ccp/IAO_EXT_0000013) ; ccp:GOA_GAF_v2.0_Annotation_record__qualifier_field_value
                      (?/qualifier_field_value rdfs/label ?/qualifier)))
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))
         )
  
  :reify ([?/molecular_function_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity)
                 :ns "ccp" :prefix "MF_"}]
          [?/bioentity_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity)
                    :ns "ccp" :prefix "B_"}]
          [?/participation_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }