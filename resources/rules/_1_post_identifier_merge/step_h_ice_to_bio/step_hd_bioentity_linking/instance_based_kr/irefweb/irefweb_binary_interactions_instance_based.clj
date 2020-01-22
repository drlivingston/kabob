;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "step-hd_irefweb-binary-interactions-instance-based-kr"
  :description   "This rule generates bio-representations for all binary protein interactions cataloged by IRefWeb"
  :head          (;; create instances of interaction type and id
                   (?/interaction rdf/type ?/interaction_type) ;interaction

                   ;; create instances of the proteins
                   (?/bioentity1_instance rdf/type ?/bioentity1)

                   (?/bioentity2_instance rdf/type ?/bioentity2)

                   (?/interaction ?/has_participant ?/bioentity1_instance)
                   (?/interaction ?/has_participant ?/bioentity2_instance)

                   (?/super_record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/interaction_irig_identifier)
                                  :ns "kbio" :prefix "B_"}]
                   [?/bioentity1_instance {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "kbio" :prefix "B_"}]
                   [?/bioentity2_instance {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "kbio" :prefix "B_"}])

  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?bioentity1 ?bioentity2 ?interaction_irig_identifier ?interaction_type ?interaction_type_label ?super_record ?has_participant
  WHERE {
         # there is a 'super' record in IRefWeb that contains references to the interaction record and interactor record(s)
                    ?super_record rdf:type ccp:IAO_EXT_0000722 . # ccp:IRefWeb PSI-MITAB 2_6 record
         ?super_record obo:BFO_0000051 ?interaction_record .
         ?interaction_record rdf:type ccp:IAO_EXT_0000064 . # CCP:IRefWeb_interaction_record
    ?interaction_record obo:BFO_0000051 ?edge_type_field_value .
         ?edge_type_field_value rdf:type ccp:IAO_EXT_0000741 .
         ?edge_type_field_value rdfs:label ?edge_type .
         FILTER (( ?edge_type = \"X\"@en )) # 'X' signifies binary interaction

         ?super_record obo:BFO_0000051 ?interactor_record_a .
         #?interactor_record_a rdf:type ccp:IAO_EXT_0000788 . # ccp:irefweb_record___interactor_field_value
                        ?interactor_record_a rdf:type ccp:IAO_EXT_0000065 . # CCP:IRefWeb_interactor_record
                        ?interactor_record_a obo:BFO_0000051 ?final_ref_field_a .
         ?final_ref_field_a rdf:type ccp:IAO_EXT_0000769 . # ccp:irefweb_interactor_record___final_reference_field_value
                        ?final_ref_field_a rdf:type ?interactor_a_identifier .
         filter (?interactor_a_identifier != ccp:IAO_EXT_0000769)
         ?interactor_a_identifier obo:IAO_0000219 ?bioentity1 .

         ?super_record obo:BFO_0000051 ?interactor_record_b .
         #?interactor_record_b rdf:type ccp:IAO_EXT_0000788 . # ccp:irefweb_record___interactor_field_value
    ?interactor_record_b rdf:type ccp:IAO_EXT_0000065 . # CCP:IRefWeb_interactor_record
    filter (?interactor_record_b != ?interactor_record_a && STR(IRI(?interactor_record_b)) < STR(IRI(?interactor_record_a)))
         ?interactor_record_b obo:BFO_0000051 ?final_ref_field_b .
         ?final_ref_field_b rdf:type ccp:IAO_EXT_0000769 . # ccp:irefweb_interactor_record___final_reference_field_value
    ?final_ref_field_b rdf:type ?interactor_b_identifier .
         filter (?interactor_b_identifier != ccp:IAO_EXT_0000769)
         ?interactor_b_identifier obo:IAO_0000219 ?bioentity2 .

         filter (?bioentity1 != ?bioentity2)

         # get the unique interaction identifier
         ?interaction_record obo:BFO_0000051 ?irig_identifier_field_value .
         ?irig_identifier_field_value rdf:type ccp:IAO_EXT_0000737 . # ccp:IRefWeb_interaction_record__integer_RID_identifier_field_value
    ?irig_identifier_field_value rdf:type ?interaction_irig_identifier .

         filter (?interaction_irig_identifier != ccp:IAO_EXT_0000737)
         ?interaction_irig_identifier rdfs:subClassOf ccp:IAO_EXT_0001376 . # IRefWeb_interaction_RIG_identifier

    # get the interaction type ID (set to MI_0000 if there is no interaction id)
         optional {
                   ?interaction_record obo:BFO_0000051 ?interaction_type_record .
                   ?interaction_type_record rdf:type ccp:IAO_EXT_0000716 . # ccp:IRefWeb_interaction_type_record
    ?interaction_type_record obo:BFO_0000051 ?interaction_type_identifier_field_value .
                   ?interaction_type_identifier_field_value rdf:type ccp:IAO_EXT_0000753 . # ccp:IRefWeb_interaction_type_record__interaction_type_identifier_field_value
    ?interaction_type_identifier_field_value rdf:type ?interaction_type_identifier .
                   filter (?interaction_type_identifier != ccp:IAO_EXT_0000753)
                   ?interaction_type_identifier obo:IAO_0000219 ?inter_type .
                   # make sure ?interaction_type is a bioentity
                   ?id_set obo:IAO_0000142 ?inter_type . # obo:mentions
                                           ?id_set rdf:type ccp:IAO_EXT_0000316 . # ccp:identifier_set
                                           }

                   {
                    select ?bio_interaction {
                                             kice:INO_0000002 obo:IAO_0000219 ?bio_interaction .
                                             filter (?bio_interaction != obo:INO_0000002) .
                                             }
                    }

      # if no interaction type was specified then bind to INO_0000002 (interaction)
        bind(coalesce(?inter_type, ?bio_interaction) as ?updated_inter_type)
      # if the specified interaction type is MI_0000, then change it to INO_0000002
        bind(if(?updated_inter_type = kice:MI_0000,?bio_interaction,?updated_inter_type) as ?interaction_type)

                   # get the unique interaction identifier
                   ?interaction_record obo:BFO_0000051 ?irig_identifier_field_value .
                   ?irig_identifier_field_value rdf:type ccp:IAO_EXT_0000737 . # ccp:IRefWeb_interaction_record__integer_RID_identifier_field_value
                                           ?irig_identifier_field_value rdf:type ?interaction_irig_identifier .
                   ?interaction_irig_identifier rdfs:subClassOf ccp:IAO_EXT_0001376 . # IRefWeb_interaction_RIG_identifier

                                           {
                                            select ?has_participant {
                                                                     kice:RO_0000057 obo:IAO_0000219 ?has_participant .
                                                                     filter (?has_participant != obo:RO_0000057) .
                                                                     }
                                            }
                   }"

  }