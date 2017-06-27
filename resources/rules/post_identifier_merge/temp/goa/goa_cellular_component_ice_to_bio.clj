;; -----------------------------------------------------------------------
;; --------- Gene Ontology Cellular Component Concept Assignment ---------
;; -----------------------------------------------------------------------
`{:name "goa-cellular-component-ice-to-bio"
  :description "This rule creates a subclass of every cellular component and types it as a gene ontology cellular component concept identifier (IAO_EXT_0000200)"
  :head (
         ;; create a subclass of GO localization
         (?/localization_sc rdfs/subClassOf obo/GO_0051179) ; GO:localization
         
         ;; create a subclass of the participating bioentity 
         (?/bioentity_sc rdfs/subClassOf ?/bioentity)
         
         ;; create a subclass of cellular component
         (?/cellular_component_sc rdfs/subClassOf ?/go_cellular_component)
 
         ;; create a transports_or_maintains_localization_of restriction
         (?/trans_main_loc_restriction rdf/type owl/Restriction)
         (?/trans_main_loc_restriction owl/onProperty obo/RO_0002313) ; RO:transports_or_maintains_localization_of
         (?/trans_main_loc_restriction owl/someValuesFrom ?/bioentity_sc)

         ;; create a has_target_end_location restriction
         (?/target_end_restriction rdf/type owl/Restriction)
         (?/target_end_restriction owl/onProperty obo/RO_0002339) ; RO:has_target_end_location
         (?/target_end_restriction owl/someValuesFrom ?/cellular_component_sc)

         ;; connect the localization subclass to the transports_or_maintains_localization_of and has_target_end restrictions
         (?/localization_sc rdfs/subClassOf ?/trans_main_loc_restriction)
         (?/localization_sc rdfs/subClassOf ?/target_end_restriction)
  
         ;; provenance: connect the record to the localization subclass
         (?/record obo/IAO_0000219 ?/localization_sc)) ; IAO:denotes
    
  :body ((?/go_ice_id [rdfs/subClassOf *] ccp/IAO_EXT_0000200) ; ccp:Gene_Ontology_Concept_Identifier__GO_cellular_component_concept_identifier
         (?/go_ice_id obo/IAO_0000219 ?/go_cellular_component) ; IAO:denotes
         (?/id_field_value rdf/type ?/go_ice_id)
         (?/id_field_value rdf/type ccp/IAO_EXT_0000014) ; ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
         (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
         (?/record rdf/type ccp/IAO_EXT_0000007) ; ccp:GOA_GAF_v2.0_Annotation_record

         ;; retrieve the process participant identifier
         (?/record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000010) ; ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/participating_bioentity) ; IAO:denotes

         ;; filter out the negations
          (:optional ((?/record obo/BFO_0000051 ?/qualifier_field_value) ; BFO:has_part
                      (?/qualifier_field_value rdf/type ccp/IAO_EXT_0000013) ; ccp:GOA_GAF_v2.0_Annotation_record__qualifierfieldvalue
                      (?/qualifier_field_value rdfs/label ?/qualifier)))
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))
         )
  
  :reify ([?/localization_sc {:ln (:sha-1 obo/GO_0051179 ?/trans_main_loc_restriction ?/target_end_restriction)
                             :ns "ccp" :prefix "LOC_"}]
          [?/trans_main_loc_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/target_end_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/bioentity_sc {:ln (:sha-1 ?/go_cellular_component ?/bioentity)
                     :ns "kbio" :prefix "B_"}]
          [?/cellular_component_sc {:ln (:sha-1 ?/go_cellular_component ?/bioentity)
                    :ns "kbio" :prefix "CC_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }