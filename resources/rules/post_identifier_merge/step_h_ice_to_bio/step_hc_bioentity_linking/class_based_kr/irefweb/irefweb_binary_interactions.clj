;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "irefweb-binary-interactions"
  :description   "This rule generates bio-representations for all binary protein interactions cataloged by IRefWeb"
  :head          (;; create subclasses of interaction type and id
                   (?/interaction rdfs/subClassOf ?/interaction_type) ;interaction
                   (?/interaction rdfs/label ?/interaction_type_label) ; transfer label to the subclass

                   ;; create subclasses of the proteins
                   (?/bioentity1_sc rdfs/subClassOf ?/bioentity1)
                   ;(?/bioentity1_sc rdfs/label ?/bioentity1_label) ; transfer label to the subclass
                   (?/bioentity2_sc rdfs/subClassOf ?/bioentity2)
                   ;(?/bioentity2_sc rdfs/label ?/bioentity2_label) ; transfer label to the subclass

                   ;; create restriction for protein interators
                   (?/bioentity1_sc_restriction rdf/type owl/Restriction)
                   (?/bioentity1_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/bioentity1_sc_restriction owl/someValuesFrom ?/bioentity1_sc)
                   (?/bioentity2_sc_restriction rdf/type owl/Restriction)
                   (?/bioentity2_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/bioentity2_sc_restriction owl/someValuesFrom ?/bioentity2_sc)

                   ;; create a cardinality restriction of 2 on the has_participant property
                   ;(?/card_restriction rdf/type owl/Restriction)
                   ;(?/card_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
                   ;(?/card_restriction owl/cardinality 2)   ; binary interactions must have 2 participants.

                   ;; join interaction restrictions
                   (?/interaction rdfs/subClassOf ?/bioentity1_sc_restriction)
                   (?/interaction rdfs/subClassOf ?/bioentity2_sc_restriction)
                   ;(?/interaction rdfs/subClassOf ?/card_restriction)

                   (?/super_record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/interaction_irig_identifier)
                                  :ns "ccp" :prefix "B_"}]
                   [?/bioentity1_sc_restriction {:ln (:restriction)
                                                 :ns "ccp" :prefix "RS_"}]
                   [?/bioentity2_sc_restriction {:ln (:restriction)
                                                 :ns "ccp" :prefix "RS_"}]
                   ;[?/card_restriction {:ln (:restriction)
                   ;                     :ns "ccp" :prefix "R_"}]
                   [?/bioentity1_sc {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "ccp" :prefix "B_"}]
                   [?/bioentity2_sc {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "ccp" :prefix "B_"}])

  :sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?bioentity1 ?bioentity2 ?interaction_irig_identifier ?interaction_type ?interaction_type_label ?super_record ?has_participant
    WHERE {
         # get binary interaction records
         ?interaction_record rdf:type ccp:IAO_EXT_0000064 . # CCP:IRefWeb_interaction_record
         ?interaction_record obo:BFO_0000051 ?edge_type_field_value .
         ?edge_type_field_value rdf:type ccp:IAO_EXT_0000741 .
         ?edge_type_field_value rdfs:label ?edge_type .
         FILTER (( ?edge_type = \"X\"@en )) # 'X' signifies binary interaction

         # there is a 'super' record in IRefWeb that contains references to the interaction record and interactor record(s)
         ?super_record obo:BFO_0000051 ?interaction_record .
         ?super_record rdf:type ccp:IAO_EXT_0000722 . # ccp:IRefWeb PSI-MITAB 2_6 record
         ?super_record obo:BFO_0000051 ?interactor_record_a .
         ?interactor_record_a rdf:type ccp:IAO_EXT_0000788 . # ccp:irefweb_record___interactor_a_field_value
         ?interactor_record_a rdf:type ccp:IAO_EXT_0000065 . # CCP:IRefWeb_interactor_record
         ?interactor_record_a obo:BFO_0000051 ?final_ref_field_a .
         ?final_ref_field_a rdf:type ccp:IAO_EXT_0000769 . # ccp:irefweb_interactor_record___final_reference_field_value
         ?final_ref_field_a rdf:type ?interactor_a_identifier .
         ?interactor_a_identifier obo:IAO_0000219 ?bioentity1 .

         ?super_record obo:BFO_0000051 ?interactor_record_b .
         ?interactor_record_b rdf:type ccp:IAO_EXT_0000789 . # ccp:irefweb_record___interactor_a_field_value
         ?interactor_record_b rdf:type ccp:IAO_EXT_0000065 . # CCP:IRefWeb_interactor_record
         ?interactor_record_b obo:BFO_0000051 ?final_ref_field_b .
         ?final_ref_field_b rdf:type ccp:IAO_EXT_0000769 . # ccp:irefweb_interactor_record___final_reference_field_value
         ?final_ref_field_b rdf:type ?interactor_b_identifier .
         ?interactor_b_identifier obo:IAO_0000219 ?bioentity2 .

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

  }