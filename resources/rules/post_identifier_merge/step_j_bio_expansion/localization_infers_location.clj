;; ------------------------------------------------
;; --------- Localization Infers Location ---------
;; ------------------------------------------------
`{:name        "localization-infers-location"
  :description "This rule asserts that a protein is located in a cellular component if a localization process results in the localization of a protein and has target end location of a cellular component"
  :head        ((?/location_sc rdfs/subClassOf ?/parent_location)
                 (?/bioentity_sc rdfs/subClassOf ?/parent_bioentity)
                 ;; create a located in restriction
                 (?/located_in_restriction rdf/type owl/Restriction)
                 (?/located_in_restriction owl/onProperty ?/located_in)
                 (?/located_in_restriction owl/someValuesFrom ?/location_sc)
                 (?/bioentity_sc rdfs/subClassOf ?/located_in_restriction))

  :reify       ([?/location_sc {:ln (:sha-1 ?/parent_location "inferred-location")
                                :ns "ccp" :prefix "B_"}]
                 [?/located_in_restriction {:ln (:restriction)
                                            :ns "ccp" :prefix "RS_"}]
                 [?/bioentity_sc {:ln (:sha-1 ?/parent_bioentity ?/parent_location "inferred-location")
                                  :ns "ccp" :prefix "B_"}])

  :sparql-string
               "SELECT ?parent_bioentity ?parent_location ?located_in

               WHERE {
                      ?localization_process_sc rdfs:subClassOf ?localization_process .
                      ?localization_process_sc rdfs:subClassOf ?trans_main_loc_restriction .
                      ?trans_main_loc_restriction rdf:type owl:Restriction .
                      ?trans_main_loc_restriction owl:onProperty ?transports_or_maintains_localization_of .
                      ?trans_main_loc_restriction owl:someValuesFrom ?bioentity .
                      ?localization_process_sc rdfs:subClassOf ?target_end_restriction .
                      ?target_end_restriction rdf:type owl:Restriction .
                      ?target_end_restriction owl:onProperty ?has_target_end_location .
                      ?target_end_restriction owl:someValuesFrom ?location .

                      ?bioentity rdfs:subClassOf ?parent_bioentity .
                      ?location rdfs:subClassOf ?parent_location .



                      {
                       select ?localization_process {
                                                     ccp:GO_0051179 obo:IAO_0000219 ?localization_process .
                                                     filter (?localization_process != obo:GO_0051179) .
                                                     }
                       }

                      {
                       select ?transports_or_maintains_localization_of {
                                                                        ccp:RO_0002313 obo:IAO_0000219 ?transports_or_maintains_localization_of .
                                                                        filter (?transports_or_maintains_localization_of != obo:RO_0002313) .
                                                                        }
                       }

                      {
                       select ?has_target_end_location {
                                                        ccp:RO_0002339 obo:IAO_0000219 ?has_target_end_location .
                                                        filter (?has_target_end_location != obo:RO_0002339) .
                                                        }
                       }

                      {
                       select ?located_in {
                                           ccp:RO_0001025 obo:IAO_0000219 ?located_in .
                                           filter (?located_in != obo:RO_0001025) .
                                           }
                       }
                      }"

  :options     {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }