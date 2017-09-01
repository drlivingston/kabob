;; ----------------------------------------------
;; --------- IRefWeb Unary Interactions ---------
;; ----------------------------------------------
`{:name "irefweb-unary-interactions"
  :description "This rule generates bio-representations for all unary (intra-molecular) protein interactions cataloged by IRefWeb"
  :head (
         ;; create subclasses of interaction type and id
         (?/interaction rdfs/subClassOf ?/interaction_type_id) ;interaction
         (?/interaction rdfs/label ?/interaction_type_label) ; transfer label to the subclass

         ;; create subclasses of the proteins
         (?/protein1_sc rdfs/subClassOf ?/protein1)
         (?/protein1_sc rdfs/label ?/protein1_label)
         
         ;; create restriction for protein interator
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/restriction owl/someValuesFrom ?/protein1_sc)

         ;; create a cardinality restriction of 2 on the has_participant property
         (?/card_restriction rdf/type owl/Restriction)
         (?/card_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/card_restriction owl/cardinality 1) ;; unary interactions must have 1 participant.

         ;; join interaction restrictions
         (?/interaction rdfs/subClassOf ?/restriction)
         (?/interaction rdfs/subClassOf ?/card_restriction))

  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "ccp" :prefix "I_"}]
           [?/restriction {:ln (:restriction)
                           :ns "ccp" :prefix "R_"}]
           [?/card_restriction {:ln (:restriction)
                                :ns "ccp" :prefix "R_"}]
           [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                           :ns "ccp" :prefix "P_"}])

  :body (
         ;; get n-ary interaction records
         (?/interaction_record rdf/type ccp/IAO_EXT_0000064) ; ccp:IRefWeb_interaction_record
         (?/interaction_record obo/BFO_0000051 ?/edge_type_field_value) ; BFO:has_part
        
         ;; get interaction edge type
         (?/edge_type_field_value rdf/type ccp/IAO_EXT_0000741) ; ccp:IRefWeb_interaction_record__edge_type_field_value
         (?/edge_type_field_value rdf/type ?/edge_type_ice_id)
         (?/edge_type_ice_id obo/IAO_0000219 ?/edge_type) ; IAO:denotes
         (= ?/edge_type "Y") ; "Y" signifies intra-molecular interaction
         
         ;; get the unique interaction identifier
         (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field_value) ; BFO:has_part
         (?/interaction_checksum_field_value rdf/type ccp/IAO_EXT_0000735) ; ccp:IRefWeb_interaction_record__check_sum_interaction_field_value
         (?/interaction_checksum_field_value rdf/type ?/edge_type_ice_id)
         (?/edge_type_ice_id obo/IAO_0000219 ?/unique_interaction_id) ; IAO:denotes
   
         ;; get the interaction type name and ID
         (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ; BFO:has_part
         (?/interaction_type_record rdf/type ccp/IAO_EXT_0000716) ; ccp:IRefWeb_interaction_type_record
         (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ; BFO:has_part
         (?/int_type_id_field rdf/type ccp/IAO_EXT_0000753) ; ccp:IRefWeb_interaction_type_record__interaction_type_identifier_field_value
         (?/int_type_id_field rdf/type ?/int_type_id_ice)
         (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ; IAO:denotes
         (?/int_type_id rdfs/label ?/int_type_label)
         ;; retrieve the interaction type name
         (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ; BFO:has_part
         (?/int_type_name_field rdf/type ccp/IAO_EXT_0000754) ; ccp:IRefWeb_interaction_type_record__interaction_type_name_field_value
         (?/int_type_name_field rdfs/label ?/int_type_name)
         
         ;; get the detection method
         (?/interaction_record rdf/type ccp/IAO_EXT_0000715) ; ccp:IRefWeb_interaction_detection_method_record
         (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ; BFO:has_part
         (?/interaction_detection_method_record rdf/type ccp/IAO_EXT_0000748) ; ccp:IRefWeb_interaction_detection_method_record__detection_method_identifier_field_value
         (?/interaction_detection_method_record rdf/type ?/detection_method_id_field)
         (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ; IAO:denotes

         ;; there is a "super" record in IRefWeb that contains references to the interaction record and interactor record(s)
         (?/super_record obo/BFO_0000051 ?/interaction_record) ; BFO:has_part

         ;; select the other interactor
         (?/super_record obo/BFO_0000051 ?/interactor_record1) ; BFO:has_part
         
         ;; get the bioentity (protein) corresponding to interactor 1
         (?/interaction_record1 rdf/type ccp/IAO_EXT_0000715) ; ccp:IRefWeb_interaction_detection_method_record
         (?/interaction_record1 obo/BFO_0000051 ?/interaction_detection_method_record) ; BFO:has_part
         (?/interaction_detection_method_record rdf/type ccp/IAO_EXT_0000748) ; ccp:IRefWeb_interaction_detection_method_record__detection_method_identifier_field_value
         (?/interaction_detection_method_record rdf/type ?/detection_method_id_field)
         (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ; IAO:denotes

         ;; get the bioentity (protein) corresponding to interactor 1
         (?/interaction_record1 rdf/type ccp/IAO_EXT_0000065) ; ccp:IRefWeb_interactor_record
         (?/interaction_record1 obo/BFO_0000051 ?/id_field1) ; BFO:has_part
         (?/id_field1 rdf/type ccp/IAO_EXT_0000755) ; ccp:IRefWeb_interactor_record__unique_identifier_field_value
         (?/id_field1 rdf/type ?/id1)
         (?/id1 obo/IAO_0000219 ?/protein1) ; IAO:denotes
         (!= ?/id1 ?/unique_interaction_id)
         
         ;; get the protein label
         (?/protein1 rdfs/label ?/protein1_label))
  


  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }