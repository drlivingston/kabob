
`{:name "irefweb-binary-interactions"
  :description ""
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction

         ;; create subclasses of the proteins
         (?/protein1_sc rdfs/subClassOf ?/protein1)
         (?/protein2_sc rdfs/subClassOf ?/protein2)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r2 owl/someValuesFrom ?/protein2_sc)

         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/r2))

  :body
  ;; get binary interaction records
  ((?/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (?/edge_field obo/IAO_0000219 "X"@en)   ;; "X" signifies binary interaction
   (?/interaction_record obo/BFO_0000051 ?/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field) ;; bfo/has_part
   (?/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (?/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ;; bfo/has_part
   (?/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
   (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
   (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ;; bfo/has_part
   (?/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (?/int_type_id_field obo/IAO_0000219 ?/int_type_id_ice) ;; iao/denotes
   (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes

   ;; get the detection method
   (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ;; bfo/has_part
   (?/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (?/interaction_detection_method_record obo/BFO_0000051 ?/detection_method_id_field) ;; bfo/has_part
   (?/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (?/super_record obo/BFO_0000051 ?/interaction_record) ;; bfo/has_part

   ;; get the two distinct interactors for this binary interaction
   (?/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (?/super_record obo/BFO_0000051 ?/interactor_record2) ;; bfo/has_part
   (?/interactor_record2 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (!= ?/interactor_record1 ?/interactor_record2)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 ?/id_field1) ;; bfo/has_part
   (?/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field1 obo/IAO_0000219 ?/id1) ;; iao/denotes
   (?/id1 obo/IAO_0000219 ?/protein1) ;; iao/denotes

   ;; get the bioentity (protein) corresponding to interactor 2
   (?/interactor_record2 obo/BFO_0000051 ?/id_field2) ;; bfo/has_part
   (?/id_field2 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field2 obo/IAO_0000219 ?/id2) ;; iao/denotes
   (?/id2 obo/IAO_0000219 ?/protein2)) ;; iao/denotes

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}]
          [?/protein2_sc {:ln (:sha-1 ?/interaction ?/protein2)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }



`{:name "irefweb-unary-interactions"
  :description ""
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction

         ;; create subclasses of the single protein involved
         (?/protein1_sc rdfs/subClassOf ?/protein1)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         (?/interaction rdfs/subClassOf ?/r1))

  :body
  ;; get binary interaction records
  ((?/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (?/edge_field obo/IAO_0000219 "Y"@en)   ;; "Y" signifies intra-molecular interaction
   (?/interaction_record obo/BFO_0000051 ?/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field) ;; bfo/has_part
   (?/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (?/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ;; bfo/has_part
   (?/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
   (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
   (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ;; bfo/has_part
   (?/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (?/int_type_id_field obo/IAO_0000219 ?/int_type_id_ice) ;; iao/denotes
   (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes

   ;; get the detection method
   (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ;; bfo/has_part
   (?/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (?/interaction_detection_method_record obo/BFO_0000051 ?/detection_method_id_field) ;; bfo/has_part
   (?/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (?/super_record obo/BFO_0000051 ?/interaction_record) ;; bfo/has_part

   ;; there is only one interactor for the unary interaction
   (?/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 ?/id_field1) ;; bfo/has_part
   (?/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field1 obo/IAO_0000219 ?/id1) ;; iao/denotes
   (?/id1 obo/IAO_0000219 ?/protein1)) ;; iao/denotes

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }



`{:name "irefweb-n-ary-interactions"
  :description "interactions with >2 participants. IRefWeb refers to them as complexes,
  but these interactions don't necessarily form complexes."
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction

         ;; create subclasses of the proteins
         (?/protein1_sc rdfs/subClassOf ?/protein1)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         (?/interaction rdfs/subClassOf ?/r1))

  :body
  ;; get binary interaction records
  ((?/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (?/edge_field obo/IAO_0000219 "C"@en)   ;; "C" signifies "complex" in IRefWeb, but really mean n-ary interaction where n>2
   (?/interaction_record obo/BFO_0000051 ?/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field) ;; bfo/has_part
   (?/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (?/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ;; bfo/has_part
   (?/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
   (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
   (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ;; bfo/has_part
   (?/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (?/int_type_id_field obo/IAO_0000219 ?/int_type_id_ice) ;; iao/denotes
   (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes

   ;; get the detection method
   (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ;; bfo/has_part
   (?/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (?/interaction_detection_method_record obo/BFO_0000051 ?/detection_method_id_field) ;; bfo/has_part
   (?/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (?/super_record obo/BFO_0000051 ?/interaction_record) ;; bfo/has_part

   ;; there are two interactors, but one is a place holder that is used to bind
   ;; multiple records together to form n-ary interactions. The placeholder is
   ;; identical to the unique interaction ID, so we select the other interactor
   ;; (which will be linked to a bioentity (protein))
   (?/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 ?/id_field1) ;; bfo/has_part
   (?/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field1 obo/IAO_0000219 ?/id1) ;; iao/denotes
   (!= ?/id1 ?/unique_interaction_id)
   (?/id1 obo/IAO_0000219 ?/protein1)) ;; iao/denotes

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }
