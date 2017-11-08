;; -----------------------------------------------------------------------
;; --------- Gene Ontology Cellular Component Concept Assignment ---------
;; -----------------------------------------------------------------------
`{:name "goa-cellular-component-ice-to-bio-instance-based-kr"
  :description "This rule creates a subclass of every cellular component and types it as a gene ontology cellular component concept identifier (IAO_EXT_0000200)"
  :head (
         ;; create an instance of GO localization
         (?/localization_instance rdf/type ?/localization_process)
         
         ;; create an instance of the participating bioentity
         (?/bioentity_instance rdf/type ?/localized_bioentity)
         
         ;; create an instance of cellular component
         (?/cellular_component_instance rdf/type ?/cellular_component)

         (?/localization_instance ?/transports_or_maintains_localization_of ?/bioentity_instance)
         (?/localization_instance ?/has_target_end_location ?/cellular_component_instance)

         ;; provenance: connect the record to the localization instance
         (?/record obo/IAO_0000219 ?/localization_instance)) ; IAO:denotes

  :reify ([?/localization_instance {:ln (:sha-1 obo/GO_0051179 ?/trans_main_loc_restriction ?/target_end_restriction "instance")
                               :ns "ccp" :prefix "B_"}]
           [?/bioentity_instance {:ln (:sha-1 ?/cellular_component ?/localized_bioentity "instance")
                            :ns "ccp" :prefix "B_"}]
           [?/cellular_component_instance {:ln (:sha-1 ?/cellular_component ?/localized_bioentity "cc" "instance")
                                     :ns "ccp" :prefix "B_"}])

  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  SELECT ?localized_bioentity ?cellular_component ?record ?localization_process ?transports_or_maintains_localization_of ?has_target_end_location
                  WHERE {
                    ?go_cc_identifier rdfs:subClassOf ccp:IAO_EXT_0000200 . # CCP:GO_cellular_component_concept_identifier
                    ?go_cc_identifier obo:IAO_0000219 ?cellular_component .
                    filter (contains(str(?cellular_component),'http://ccp.ucdenver.edu/obo/ext/'))
                    ?ontology_identifier_field_value rdf:type ?go_cc_identifier .
                    ?ontology_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
                    ?record obo:BFO_0000051 ?ontology_identifier_field_value .
                    ?record rdf:type ccp:IAO_EXT_0000007 . # ccp:GOA_GAF_v2.0_Annotation_record
                    ?record obo:BFO_0000051 ?bioentity_field_value .
                    ?bioentity_field_value rdf:type ccp:IAO_EXT_0000010 . # ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
                    ?bioentity_field_value rdf:type ?bioentity_identifier .
                    ?bioentity_identifier obo:IAO_0000219 ?localized_bioentity .
                    OPTIONAL {
                         ?record obo:BFO_0000051 ?qualifier_field_value .
                         ?qualifier_field_value rdf:type ccp:IAO_EXT_0000013 . # ccp:GOA_GAF_v2.0_Annotation_record__qualifierfieldvalue
                         ?qualifier_field_value rdfs:label ?qualifier .
                     }
                    FILTER (( ! bound(?qualifier) || ! regex(?qualifier, \"^NOT\", \"i\")))

                  {
                   select ?localization_process {
                               ccp:GO_0051179 obo:IAO_0000219 ?localization_process .
                               filter (?localization_process != obo:GO_0051179) .
                               }
                   }

                  {
                   select ?transports_or_maintains_localization_of  {
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
  }"

}