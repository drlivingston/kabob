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
  ((_/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (_/edge_field obo/IAO_0000219 ["X" "en"])   ;; "X" signifies binary interaction
   (_/interaction_record obo/BFO_0000051 _/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (_/interaction_record obo/BFO_0000051 _/interaction_checksum_field) ;; bfo/has_part
   (_/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (_/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (_/interaction_record obo/BFO_0000051 _/interaction_type_record) ;; bfo/has_part
   (_/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
 ;;  (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
 ;;  (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
 ;;  (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (_/interaction_type_record obo/BFO_0000051 _/int_type_id_field) ;; bfo/has_part
   (_/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (_/int_type_id_field obo/IAO_0000219 _/int_type_id_ice) ;; iao/denotes
   (_/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes

   ;; get the detection method
   (_/interaction_record obo/BFO_0000051 _/interaction_detection_method_record) ;; bfo/has_part
   (_/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (_/interaction_detection_method_record obo/BFO_0000051 _/detection_method_id_field) ;; bfo/has_part
   (_/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (_/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (_/super_record obo/BFO_0000051 _/interaction_record) ;; bfo/has_part

   ;; get the two distinct interactors for this binary interaction
   (_/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (_/super_record obo/BFO_0000051 ?/interactor_record2) ;; bfo/has_part
   (?/interactor_record2 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (!= ?/interactor_record1 ?/interactor_record2)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 _/id_field1) ;; bfo/has_part
   (_/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/id_field1 obo/IAO_0000219 _/id1) ;; iao/denotes
   (_/id1 obo/IAO_0000219 ?/protein1) ;; iao/denotes

   ;; get the bioentity (protein) corresponding to interactor 2
   (?/interactor_record2 obo/BFO_0000051 _/id_field2) ;; bfo/has_part
   (_/id_field2 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/id_field2 obo/IAO_0000219 _/id2) ;; iao/denotes
   (_/id2 obo/IAO_0000219 ?/protein2)) ;; iao/denotes

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
