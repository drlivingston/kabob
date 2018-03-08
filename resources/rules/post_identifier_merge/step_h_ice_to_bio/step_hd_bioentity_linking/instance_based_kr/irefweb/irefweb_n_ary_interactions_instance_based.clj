;; ----------------------------------------------
;; --------- IRefWeb N-Ary Interactions ---------
;; ----------------------------------------------
`{:name "irefweb-n-ary-interactions-instance-based-kr"
  :description "This rule generates bio-representations for all n-ary protein interactions cataloged by IRefWeb, where n>2"
  :head (
         ;; create instance of interaction type and id
         (?/interaction rdf/type ?/interaction_type) ;interaction

         ;; create instance of the protein
         (?/bioentity_instance rdf/type ?/bioentity)

         (?/interaction ?/has_participant ?/bioentity_instance)

          (?/super_record obo/IAO_0000219 ?/interaction))
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "B_"}]
           [?/bioentity_instance {:ln (:sha-1 ?/interaction ?/bioentity)
                           :ns "kbio" :prefix "B_"}])

  :sparql-string
  "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  SELECT ?bioentity ?interaction_irig_identifier ?interactor_a_identifier ?interaction_type ?interaction_type_label ?super_record ?has_participant
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
         #?interactor_record_a rdf:type ccp:IAO_EXT_0000788 . # ccp:irefweb_record___interactor_a_field_value
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
                                           ?inter_type rdfs:label ?inter_type_label .
                   }

         {
          select ?bio_interaction {
                                   ccp:INO_0000002 obo:IAO_0000219 ?bio_interaction .
                                   filter (?bio_interaction != obo:INO_0000002) .
                                   }
          }

         # if no interaction type was specified then bind to INO_0000002 (interaction)
         bind(coalesce(?inter_type, ?bio_interaction) as ?updated_inter_type)
         # if the specified interaction type is MI_0000, then change it to INO_0000002
         bind(if(?updated_inter_type = ccp:MI_0000,?bio_interaction,?updated_inter_type) as ?interaction_type)

         {
          select ?has_participant {
                                   ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
                                   filter (?has_participant != obo:RO_0000057) .
                                   }
          }
         }"

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }