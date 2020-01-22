;; --------------------------------------------------------------------
;; --------- PharmGKB abstract bioentity interaction Relation ---------
;; --------------------------------------------------------------------
`{:name "step-hda_pharmgkb-abstract-interaction-relation"
  :description "This rule assigns an abstract interaction relation between pharmgkb entities"
  :head (
          (?/interaction rdfs/subClassOf ccp/temp_pharmgkb_interaction) ;interaction
          (?/interaction rdfs/subClassOf ?/abstract_interaction) ;interaction

          ;; create subclasses of the proteins
          (?/bioentity1_sc rdfs/subClassOf ?/bioentity1)
          (?/bioentity2_sc rdfs/subClassOf ?/bioentity2)

          ;; create restriction for protein interators
          (?/bioentity1_sc_restriction rdf/type owl/Restriction)
          (?/bioentity1_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
          (?/bioentity1_sc_restriction owl/someValuesFrom ?/bioentity1_sc)
          (?/bioentity2_sc_restriction rdf/type owl/Restriction)
          (?/bioentity2_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
          (?/bioentity2_sc_restriction owl/someValuesFrom ?/bioentity2_sc)

          ;; join interaction restrictions
          (?/interaction rdfs/subClassOf ?/bioentity1_sc_restriction)
          (?/interaction rdfs/subClassOf ?/bioentity2_sc_restriction)

          (?/record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/bioentity1 ?/bioentity2 "pharmgkb-relation")
                                  :ns "kbio" :prefix "B_"}]
                   [?/bioentity1_sc_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}]
                   [?/bioentity2_sc_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}]
                   [?/bioentity1_sc {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "kbio" :prefix "B_"}]
                   [?/bioentity2_sc {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "kbio" :prefix "B_"}])

  :body
     "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
     prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
      PREFIX obo: <http://purl.obolibrary.org/obo/>
      PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      SELECT ?bioentity1 ?bioentity2 ?has_participant ?abstract_interaction ?record
      WHERE {

      {
             select ?has_participant {
                                      kice:RO_0000057 obo:IAO_0000219 ?has_participant .
                                      filter (?has_participant != obo:RO_0000057) .
                                      }
             }


  {
   select ?abstract_interaction {
                                 kice:INO_0000002 obo:IAO_0000219 ?abstract_interaction .
                                 filter (?abstract_interaction != obo:INO_0000002) .
                                 }
   }
      ?record rdf:type ccp:IAO_EXT_0000823 . # ccp:PharmGKB_relation_record
               ?record obo:BFO_0000051 ?entity1_identifier_field_value . # BFO:has_part
               ?entity1_identifier_field_value rdf:type ccp:IAO_EXT_0001036 . # ccp:PharmGKB_relation_record__entity_1_identifier_field_value
               ?entity1_identifier_field_value rdf:type ?entity1_id .
               ?entity1_id obo:IAO_0000219 ?bioentity1 . # IAO:denotes

               ?record obo:BFO_0000051 ?entity2_identifier_field_value . # BFO:has_part
               ?entity2_identifier_field_value rdf:type ccp:IAO_EXT_0001041 . # ccp:PharmGKB_relation_record__entity_2_identifier_field_value
               ?entity2_identifier_field_value rdf:type ?entity2_id .
               ?entity2_id obo:IAO_0000219 ?bioentity2 . # IAO:denotes


         }"


  }