;; ------------------------------------------------
;; --------- Localization Infers Location ---------
;; ------------------------------------------------
`{:name        "step-i_localization-infers-location-labels"
  :description "This rule asserts that a protein is located in a cellular component if a localization process results in the localization of a protein and has target end location of a cellular component"
  :head        ((?/location_sc rdfs/label ?/location_label)
                 (?/bioentity_sc rdfs/label ?/bioentity_label))
  :body
               "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                    prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                         PREFIX obo: <http://purl.obolibrary.org/obo/>
               select ?location_sc ?bioentity_sc ?bioentity_label ?location_label {

                   {
                    select ?located_in {
                                        kice:RO_0001025 obo:IAO_0000219 ?located_in .
                                        filter (?located_in != obo:RO_0001025) .
                                        }
                    }

                   ?location_sc rdfs:subClassOf ccp:temp_location .
                   ?location_sc rdfs:subClassOf ?location .
                   filter (?location != ccp:temp_location)
                   ?r owl:someValuesFrom ?location_sc .
                   ?r owl:onProperty ?located_in .
                   ?bioentity_sc rdfs:subClassOf ?r .
                   ?bioentity_sc rdfs:subClassOf ?bioentity .
                   filter (?r != ?bioentity)

                   optional {?bioentity rdfs:label ?label1}
                   bind(coalesce(?label1, \"Unnamed localized bioentity\") as ?bioentity_name)

                   optional {?location rdfs:label ?label2}
                   bind(coalesce(?label2, \"Unnamed location\") as ?location_name)


                   bind(concat(str(?bioentity_name), \"; localized to: \", str(?location_name)) as ?bioentity_label)
                   bind(concat(str(?location_name), \"; with localized bioentity: \", str(?bioentity_name)) as ?location_label)

                   }"

  :options     {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }