;; ------------------------------------------------
;; --------- Localization Infers Location ---------
;; ------------------------------------------------
`{:name        "localization-infers-location-instance-based"
  :description "This rule asserts that a protein is located in a cellular component if a localization process results in the localization of a protein and has target end location of a cellular component"
  :head        ((?/location_instance rdf/type ?/location)
                 (?/bioentity_instance rdf/type ?/bioentity)
                 (?/bioentity_instance ?/located_in ?/location_instance))

  :reify       ([?/location_instance {:ln (:sha-1 ?/location "inferred-location")
                                :ns "kbio" :prefix "B_"}]
                 [?/bioentity_instance {:ln (:sha-1 ?/bioentity ?/location "inferred-location")
                                  :ns "kbio" :prefix "B_"}])

  :body
               "prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
               PREFIX obo: <http://purl.obolibrary.org/obo/>
               SELECT ?bioentity ?location ?located_in

               WHERE {
                      ?localization_instance rdf:type ?localization_process .
                      ?localization_instance ?transports_or_maintains_localization_of ?bioentity_instance .
                      ?localization_instance ?has_target_end_location ?location_instance .
                      ?bioentity_instance rdf:type ?bioentity .
                      ?location_instance rdf:type ?location .

                      {
                       select ?localization_process {
                                                     kice:GO_0051179 obo:IAO_0000219 ?localization_process .
                                                     filter (?localization_process != obo:GO_0051179) .
                                                     }
                       }

                      {
                       select ?transports_or_maintains_localization_of {
                                                                        kice:RO_0002313 obo:IAO_0000219 ?transports_or_maintains_localization_of .
                                                                        filter (?transports_or_maintains_localization_of != obo:RO_0002313) .
                                                                        }
                       }

                      {
                       select ?has_target_end_location {
                                                        kice:RO_0002339 obo:IAO_0000219 ?has_target_end_location .
                                                        filter (?has_target_end_location != obo:RO_0002339) .
                                                        }
                       }

                      {
                       select ?located_in {
                                           kice:RO_0001025 obo:IAO_0000219 ?located_in .
                                           filter (?located_in != obo:RO_0001025) .
                                           }
                       }
                      }"

  :options     {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }