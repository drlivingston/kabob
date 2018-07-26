;; -----------------------------------------------------------------------
;; --------- Gene Ontology Cellular Component Concept Assignment ---------
;; -----------------------------------------------------------------------
`{:name "step-hdb_goa-cellular-component-ice-to-bio-labels"
  :description "This rule creates a subclass of every cellular component and types it as a gene ontology cellular component concept identifier (IAO_EXT_0000200)"
  :head ((?/loc_process_sc rdfs/label ?/localization_process_label)
          (?/localized_bioentity_sc rdfs/label ?/localized_bioentity_label)
          (?/cc_sc rdfs/label ?/cellular_component_label))
  :body "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select  ?loc_process_sc ?localized_bioentity_sc ?cc_sc ?localized_bioentity_label ?cellular_component_label ?localization_process_label {

                        {
                         select ?transports_or_maintains_localization_of  {
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

                        ?loc_process_sc rdfs:subClassOf ccp:temp_localization_process .
                        ?loc_process_sc rdfs:subClassOf ?r1 .
                        ?r1 owl:onProperty ?has_target_end_location .
                        ?r1 owl:someValuesFrom ?cc_sc .
                        ?cc_sc rdfs:subClassOf ?cc .
                        ?loc_process_sc rdfs:subClassOf ?r2 .
                        ?r2 owl:onProperty ?transports_or_maintains_localization_of .
                        ?r2 owl:someValuesFrom ?localized_bioentity_sc .
                        ?localized_bioentity_sc rdfs:subClassOf ?localized_bioentity .


                        optional {?cc rdfs:label ?ccl}
                        bind(coalesce(?ccl, \"Unnamed cellular component\") as ?cc_name)

                        optional {?localized_bioentity rdfs:label ?bl}
                        bind(coalesce(?bl, \"Unnamed localized bioentity\") as ?bioentity_name)

                        bind(concat(str(?bioentity_name), \"; localized to: \", str(?cc_name)) as ?localized_bioentity_label)
                        bind(concat(str(?cc_name), \"; with localized bioentity: \", str(?bioentity_name)) as ?cellular_component_label)
                        bind(concat(\"Localization of \", str(?bioentity_name), \" to \", str(?cc_name)) as ?localization_process_label)

                        }"
}