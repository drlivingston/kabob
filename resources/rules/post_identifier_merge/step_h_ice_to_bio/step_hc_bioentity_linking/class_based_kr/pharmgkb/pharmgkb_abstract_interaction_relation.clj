;; --------------------------------------------------------------------
;; --------- PharmGKB abstract bioentity interaction Relation ---------
;; --------------------------------------------------------------------
`{:name "pharmgkb-abstract-interaction-relation"
  :description "This rule assigns an abstract interaction relation between pharmgkb entities"
  :head (
          (?/interaction rdfs/subClassOf ?/abstract_interaction) ;interaction
          (?/interaction rdfs/label ?/abstract_interaction_label) ; transfer label to the subclass

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

          ;; join interaction restrictions
          (?/interaction rdfs/subClassOf ?/bioentity1_sc_restriction)
          (?/interaction rdfs/subClassOf ?/bioentity2_sc_restriction)

          (?/record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/bioentity1 ?/bioentity2 "pharmgkb-relation")
                                  :ns "ccp" :prefix "B_"}]
                   [?/bioentity1_sc_restriction {:ln (:restriction)
                                                 :ns "ccp" :prefix "RS_"}]
                   [?/bioentity2_sc_restriction {:ln (:restriction)
                                                 :ns "ccp" :prefix "RS_"}]
                   [?/bioentity1_sc {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "ccp" :prefix "B_"}]
                   [?/bioentity2_sc {:ln (:sha-1 ?/interaction ?/bioentity2)
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