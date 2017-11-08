;; --------------------------------------------------------------------
;; --------- PharmGKB abstract bioentity interaction Relation ---------
;; --------------------------------------------------------------------
`{:name "pharmgkb-abstract-interaction-relation-instance-based-kr"
  :description "This rule assigns an abstract interaction relation between pharmgkb entities"
  :head (
          (?/interaction rdf/type ?/abstract_interaction) ;interaction

          ;; create subclasses of the proteins
          (?/bioentity1_instance rdf/type ?/bioentity1)

          (?/bioentity2_instance rdf/type ?/bioentity2)

          (?/interaction ?/has_participant ?/bioentity1_instance)
          (?/interaction ?/has_participant ?/bioentity2_instance)

          (?/record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/bioentity1 ?/bioentity2 "pharmgkb-relation")
                                  :ns "ccp" :prefix "B_"}]
                   [?/bioentity1_instance {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "ccp" :prefix "B_"}]
                   [?/bioentity2_instance {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "ccp" :prefix "B_"}])

  :sparql-string
     "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
      PREFIX obo: <http://purl.obolibrary.org/obo/>
      PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      SELECT ?bioentity1 ?bioentity2 ?has_participant ?abstract_interaction ?abstract_interaction_label ?record
      WHERE {  ?record rdf:type ccp:IAO_EXT_0000823 . # ccp:PharmGKB_relation_record
               ?record obo:BFO_0000051 ?entity1_identifier_field_value . # BFO:has_part
               ?entity1_identifier_field_value rdf:type ccp:IAO_EXT_0001036 . # ccp:PharmGKB_relation_record__entity_1_identifier_field_value
               ?entity1_identifier_field_value rdf:type ?entity1_id .
               ?entity1_id obo:IAO_0000219 ?bioentity1 . # IAO:denotes

               ?record obo:BFO_0000051 ?entity2_identifier_field_value . # BFO:has_part
               ?entity2_identifier_field_value rdf:type ccp:IAO_EXT_0001041 . # ccp:PharmGKB_relation_record__entity_2_identifier_field_value
               ?entity2_identifier_field_value rdf:type ?entity2_id .
               ?entity2_id obo:IAO_0000219 ?bioentity2 . # IAO:denotes

            {
              select ?has_participant {
                              ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
                              filter (?has_participant != obo:RO_0000057) .
                              }
            }

            # TODO: Perhaps there is a better class to use other than MI_0000?
            {
             select ?abstract_interaction ?abstract_interaction_label {
                              ccp:MI_0000 obo:IAO_0000219 ?abstract_interaction .
                              ?abstract_interaction rdfs:label ?abstract_interaction_label .
                              filter (?abstract_interaction != obo:MI_0000) .
             }
          }

         }"


  }