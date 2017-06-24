;; -----------------------------------------------------------------------
;; --------- Generate Bio Concepts for Gene Ontology Annotations ---------
;; -----------------------------------------------------------------------
`{:name "goa-bp"
  :description "This rule creates a subclass of every biological process and types it as a GO BP concept identifier (IAO_EXT_0000103)"
  :head (
         ;; create a subclass of the biological process
         (?/process_sc rdfs/subClassOf ?/process)
         ;; create a subclass of the participating bioentity
         (?/bioentity_sc rdfs/subClassOf ?/bioentity)

         ;; create a has_participant restriction
         (?/participation_restriction rdf/type owl/Restriction)
         (?/participation_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/participation_restriction  owl/someValuesFrom ?/bioentity_sc)

         ;; connect the process subclass to the participation restriction
         (?/process_sc rdfs/subClassOf ?/participation_restriction)

         ;; provenance: connect the record to the process subclass
         (?/record obo/IAO_0000219 ?/process_sc)) ; IAO:denotes
    
  :body ((?/go_ice_id rdfs/subClassOf ccp/IAO_EXT_0000103) ; ccp:Gene_Ontology_concept_identifier_GO_BP_concept_identifier
         (?/go_ice_id obo/IAO_0000219 ?/process) ; IAO:denotes
         (?/id_field_value rdf/type ?/go_ice_id)
         (?/id_field_value rdf/type ccp/IAO_EXT_0000014) ; ccp:GOA_GAF_v2.0_Annotation_record_ontology_term_identifier_field_value
         (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
         (?/record rdf/type ccp/IAO_EXT_0000007) ; ccp:GOA_GAF_v2.0_Annotation_record

         ;; retrieve the process participant identifier
         (?/record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000010) ; ccp:GOA_GAF_v2.0_Annotation_record_database_object_identifier_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/participating_bioentity) ; IAO:denotes

         ;; filter out the negations
          (:optional
           ((?/record obo/BFO_0000051 ?/qualifier_fv) ; BFO:has_part
            (?/qualifier_fv rdf/type ccp/IAO_EXT_0000013) ; ccp:GOA_GAF_v2.0_Annotation_record_qualifier_field_value
            (?/qualifier_fv rdfs/label ?/qualifier)))
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))
         )
  
  :reify ([?/process_sc {:ln (:sha-1 ?/process ?/participation_restriction)
                 :ns "ccp" :prefix "BP_"}]
          [?/bioentity_sc {:ln (:sha-1 ?/process ?/participating_bioentity)
                    :ns "ccp" :prefix "B_"}]
          [?/participation_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }


`{:name "goa-cc"
  :description "This rule creates a subclass of every cellular component and types it as a GO CC concept identifier (IAO_EXT_0000200)"
  :head (
         ;; create a subclass of GO localization
          (?/localization_sc rdfs/subClassOf obo/GO_0051179) ; GO:localization
         ;; create a subclass of the participating bioentity 
         (?/bioentity_sc rdfs/subClassOf ?/bioentity)
         ;; create a subclass of cellular component
         (?/component_sc rdfs/subClassOf ?/go_cc)
 
         ;; create a transports_or_maintains_localization_of restriction
         (?/trans_main_loc_restriction rdf/type owl/Restriction)
         (?/trans_main_loc_restriction owl/onProperty obo/RO_0002313) ; RO:transports_or_maintains_localization_of
         (?/trans_main_loc_restriction owl/someValuesFrom ?/bioentity_sc)

         ;; create a has_target_end_location restriction
         (?/target_end_restriction rdf/type owl/Restriction)
         (?/target_end_restriction owl/onProperty obo/RO_0002339) ; RO:has_target_end_location
         (?/target_end_restriction owl/someValuesFrom ?/component_sc)

         ;; connect the localization subclass to the transports_or_maintains_localization_of and has_target_end restrictions
         (?/localization_sc rdfs/subClassOf ?/trans_main_loc_restriction)
         (?/localization_sc rdfs/subClassOf ?/target_end_restriction)
  
         ;; provenance: connect the record to the localization subclass
         (?/record obo/IAO_0000219 ?/localization_sc)) ; IAO:denotes
    
  :body ((?/go_ice_id [rdfs/subClassOf *] ccp/IAO_EXT_0000200) ; ccp:Gene_Ontology_concept_identifier_GO_CC_concept_identifier
         (?/go_ice_id obo/IAO_0000219 ?/process) ; IAO:denotes
         (?/id_field_value rdf/type ?/go_ice_id)
         (?/id_field_value rdf/type ccp/IAO_EXT_0000014) ; ccp:GOA_GAF_v2.0_Annotation_record_ontology_term_identifier_field_value
         (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
         (?/record rdf/type ccp/IAO_EXT_0000007) ; ccp:GOA_GAF_v2.0_Annotation_record

         ;; retrieve the process participant identifier
         (?/record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000010) ; ccp:GOA_GAF_v2.0_Annotation_record_database_object_identifier_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/participating_bioentity) ; IAO:denotes

         ;; filter out the negations
          (:optional
           ((?/record obo/BFO_0000051 ?/qualifier_fv) ; BFO:has_part
            (?/qualifier_fv rdf/type ccp/IAO_EXT_0000013) ; ccp:GAF_qualifier_field_value
            (?/qualifier_fv rdfs/label ?/qualifier)))
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))

         )
  :reify ([?/localization_sc {:ln (:sha-1 obo/GO_0051179 ?/trans_main_loc_restriction ?/target_end_restriction)
                             :ns "ccp" :prefix "LOC_"}]
          [?/trans_main_loc_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/target_end_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/bioentity_sc {:ln (:sha-1 ?/go_cc ?/bioentity)
                     :ns "kbio" :prefix "B_"}]
          [?/component_sc {:ln (:sha-1 ?/go_cc ?/bioentity)
                    :ns "kbio" :prefix "CC_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }






;; `{:name "goa-bp"
;;   ;; this need to be hashed and have proper classes
;;   :head ((?/bioentity kiao/STANDIN_bio_go ?/go))

;;          ;; (?/bp rdfs/subClassOf ?/go) ;interaction

;;          ;; ;;this triple is redundant rdf macros
;;          ;; (?/bp kbio/rsv_has_participant ?/go)
         
;;          ;; (?/r1 rdf/type owl/Restriction)
;;          ;; (?/r1 owl/onProperty obo/has_participant)
;;          ;; (?/r1 owl/someValuesFrom ?/bioentity)

;;          ;; ;;make the interaction be necessarily part of these 3 restrictions
;;          ;; ;;still need to add a sufficient definition
;;          ;; (?/bp rdfs/subClassOf ?/r1))
    
;;   :body (;;~@(kabob/rtv ?/record
;;          ;;     iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1 _/goid
;;          ;;     iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1 _/gp)

         
;;          (?/fv0 kiao/hasTemplate  iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1)
;;          (?/record obo/BFO_0000051 ?/fv0)
;;          (?/fv0 obo/IAO_0000219 ?/goid)
;;          (?/goid obo/IAO_0000219 ?/go)
;;          (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          (?/record obo/BFO_0000051 ?/fv1)
;;          (?/fv1 kiao/hasTemplate iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1)
;;          (?/fv1 obo/IAO_0000219 ?/gp)
;;          (?/gp obo/IAO_0000219 ?/bioentity)

         
;;          ;; ;;filter out the negations
         
;;          ;; (:optional
;;          ;;  ~@(kabob/rtv ?/record
;;          ;;               iaogoa/GpAssociationGoaUniprotFileData_qualifierDataField1 ?/qualifier))
;;          ;; ;;(!= ?/qualifier )
;;          ;; (:not (:regex ?/qualifier "^NOT" "i"))

;;          ;;(_/goid obo/IAO_0000219 ?/go)
;; ;;         (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          ;;is it always a protein, or do we need to go up to GorGP?  
;;          ;;(_/gp obo/IAO_0000219 ?/bioentity))

;;          ;; (_/geneid obo/IAO_0000219 ?/gene)
;;          ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
;;          ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

;;          )
;;   ;; :reify (?/bp
;;   ;;         ?/r1)
;;   }



;; `{:name "goa-bp"
;;   ;; this need to be hashed and have proper classes
;;   :head ((?/bioentity kiao/STANDIN_bio_go ?/go))

;;          ;; (?/bp rdfs/subClassOf ?/go) ;interaction

;;          ;; ;;this triple is redundant rdf macros
;;          ;; (?/bp kbio/rsv_has_participant ?/go)
         
;;          ;; (?/r1 rdf/type owl/Restriction)
;;          ;; (?/r1 owl/onProperty obo/has_participant)
;;          ;; (?/r1 owl/someValuesFrom ?/bioentity)

;;          ;; ;;make the interaction be necessarily part of these 3 restrictions
;;          ;; ;;still need to add a sufficient definition
;;          ;; (?/bp rdfs/subClassOf ?/r1))
    
;;   :body (~@(kabob/rtv ?/record
;;               iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1 _/goid
;;               iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1 _/gp)

;;          ;; ;;filter out the negations
;;          ;; (:optional
;;          ;;  ~@(kabob/rtv ?/record
;;          ;;               iaogoa/GpAssociationGoaUniprotFileData_qualifierDataField1 ?/qualifier))
;;          ;; ;;(!= ?/qualifier )
;;          ;; (:not (:regex ?/qualifier "^NOT" "i"))

;;          (_/goid obo/IAO_0000219 ?/go)
;; ;;         (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          ;;is it always a protein, or do we need to go up to GorGP?  
;;          (_/gp obo/IAO_0000219 ?/bioentity))

;;          ;; (_/geneid obo/IAO_0000219 ?/gene)
;;          ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
;;          ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

;;   :reify (?/bp
;;           ?/r1)
;;   }
