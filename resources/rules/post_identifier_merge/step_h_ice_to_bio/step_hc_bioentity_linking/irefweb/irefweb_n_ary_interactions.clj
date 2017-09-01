;; ----------------------------------------------
;; --------- IRefWeb N-Ary Interactions ---------
;; ----------------------------------------------
`{:name "irefweb-n-ary-interactions"
  :description "This rule generates bio-representations for all n-ary protein interactions cataloged by IRefWeb, where n>2"
  :head (
         ;; create subclasses of interaction type and id
         (?/interaction rdfs/subClassOf ?/interaction_type) ;interaction
         (?/interaction rdfs/label ?/interaction_type_label) ; transfer label to the subclass

         ;; create subclasses of the proteins
         (?/bioentity_sc rdfs/subClassOf ?/bioentity)
         ;(?/bioentity_sc rdfs/label ?/bioentity_label)
         
         ;; create restriction for protein interator
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty ?/has_participant) ; RO:has_participant
         (?/restriction owl/someValuesFrom ?/bioentity_sc)

         ;; create a cardinality restriction of >2 on the has_participant property
         ;(?/card_restriction rdf/type owl/Restriction)
         ;(?/card_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         ;(?/card_restriction owl/minCardinality 3) ; n-ary interactions must have >2 participant
         
         ;; join interaction restrictions
         (?/interaction rdfs/subClassOf ?/restriction)
         ;(?/interaction rdfs/subClassOf ?/card_restriction)
          (?/super_record obo/IAO_0000219 ?/interaction))
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "ccp" :prefix "B_"}]
           [?/restriction {:ln (:restriction)
                           :ns "ccp" :prefix "RS_"}]
           ;[?/card_restriction {:ln (:restriction)
           ;                     :ns "ccp" :prefix "R_"}]
           [?/bioentity_sc {:ln (:sha-1 ?/interaction ?/bioentity)
                           :ns "ccp" :prefix "B_"}])

  :sparql-string
  "SELECT ?bioentity ?interaction_irig_identifier ?interactor_a_identifier ?interaction_type ?interaction_type_label ?super_record ?has_participant
  WHERE {
         # get binary interaction records
                      ?interaction_record rdf:type ccp:IAO_EXT_0000064 . # CCP:IRefWeb_interaction_record
                          ?interaction_record obo:BFO_0000051 ?edge_type_field_value .
         ?edge_type_field_value rdf:type ccp:IAO_EXT_0000741 .
         ?edge_type_field_value rdfs:label ?edge_type .
         FILTER (( ?edge_type = \"C\"@en )) # 'C' signifies 'complex' in IRefWeb, but really mean n-ary interaction where n>2

         # there is a 'super' record in IRefWeb that contains references to the interaction record and interactor record(s)
         ?super_record obo:BFO_0000051 ?interaction_record .
         ?super_record rdf:type ccp:IAO_EXT_0000722 . # ccp:IRefWeb PSI-MITAB 2_6 record
         ?super_record obo:BFO_0000051 ?interactor_record_a .
         ?interactor_record_a rdf:type ccp:IAO_EXT_0000788 . # ccp:irefweb_record___interactor_a_field_value
                          ?interactor_record_a rdf:type ccp:IAO_EXT_0000065 . # CCP:IRefWeb_interactor_record
                          ?interactor_record_a obo:BFO_0000051 ?final_ref_field_a .
         ?final_ref_field_a rdf:type ccp:IAO_EXT_0000769 . # ccp:irefweb_interactor_record___final_reference_field_value
                          ?final_ref_field_a rdf:type ?interactor_a_identifier .
         ?interactor_a_identifier obo:IAO_0000219 ?bioentity .

         # get the unique interaction identifier
         ?interaction_record obo:BFO_0000051 ?irig_identifier_field_value .
         ?irig_identifier_field_value rdf:type ccp:IAO_EXT_0000737 . # ccp:IRefWeb_interaction_record__integer_RID_identifier_field_value
                          ?irig_identifier_field_value rdf:type ?interaction_irig_identifier .
         ?interaction_irig_identifier rdfs:subClassOf ccp:IAO_EXT_0001376 . # IRefWeb_interaction_RIG_identifier

                          # get the interaction type name and ID
         ?interaction_record obo:BFO_0000051 ?interaction_type_record .
         ?interaction_type_record rdf:type ccp:IAO_EXT_0000716 . # ccp:IRefWeb_interaction_type_record
                          ?interaction_type_record obo:BFO_0000051 ?interaction_type_identifier_field_value .
         ?interaction_type_identifier_field_value rdf:type ccp:IAO_EXT_0000753 . # ccp:IRefWeb_interaction_type_record__interaction_type_identifier_field_value
                          ?interaction_type_identifier_field_value rdf:type ?interaction_type_identifier .
         ?interaction_type_identifier obo:IAO_0000219 ?interaction_type .
         # make sure ?interaction_type is a bioentity
         ?id_set obo:IAO_0000142 ?interaction_type . # obo:mentions
    ?id_set rdf:type ccp:IAO_EXT_0000316 . # ccp:identifier_set
    ?interaction_type rdfs:label ?interaction_type_label .

         {
          select ?has_participant {
                                   ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
                                   filter (?has_participant != obo:RO_0000057) .
                                   }
          }
         }"

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }