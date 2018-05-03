;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Biological Process Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "goa-biological-process-ice-to-bio-instance-based-kr"
  :description   "This rule creates a subclass of every biological process and types it as a gene ontology biological process concept identifier  (IAO_EXT_0000103)"
  :head          (
                   ;; create an instance of the biological process
                   (?/biological_process_instance rdf/type ?/biological_process)
                   ;; create an instance of the participating bioentity
                   (?/bioentity_instance rdf/type ?/participating_bioentity)

                   (?/biological_process_instance ?/has_participant ?/bioentity_instance)

                   ;; provenance: connect the record to the process instance
                   (?/record obo/IAO_0000219 ?/biological_process_instance)) ; IAO:denotes

  :reify         ([?/biological_process_instance {:ln (:sha-1 ?/biological_process ?/participating_bioentity "bp" "instance")
                                            :ns "kbio" :prefix "B_"}]
                   [?/bioentity_instance {:ln (:sha-1 ?/biological_process ?/participating_bioentity "instance")
                                    :ns "kbio" :prefix "B_"}])
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?participating_bioentity ?biological_process ?has_participant ?record
                WHERE {  ?go_bp_identifier rdfs:subClassOf ccp:IAO_EXT_0000103 . # CCP:GO_biological_process_identifier
                         ?go_identifier_field_value rdf:type ?go_bp_identifier .
                         ?go_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
                         ?go_bp_identifier obo:IAO_0000219 ?biological_process .
                         filter (contains(str(?biological_process),'http://ccp.ucdenver.edu/kabob/bio/'))
                         ?record obo:BFO_0000051 ?go_identifier_field_value .
                         ?record rdf:type ccp:IAO_EXT_0000007 . # ccp:GOAGAFv2.0AnnotationRecord
                         ?record obo:BFO_0000051 ?bioentity_field_value .
                         ?bioentity_field_value rdf:type ccp:IAO_EXT_0000010 . # ccp:GOA_GAF_v2.0_Annotation_record__database_object_identifier_field_value
                         ?bioentity_field_value rdf:type ?bioentity_identifier .
                         ?bioentity_identifier obo:IAO_0000219 ?participating_bioentity .
                         OPTIONAL { ?record obo:BFO_0000051 ?qualifier_field_value .
                                    ?qualifier_field_value rdf:type ccp:IAO_EXT_0000013 . # ccp:GOA_GAF_v2.0_Annotation_record__qualifier_field_value
                                    ?qualifier_field_value rdfs:label ?qualifier .
                                   }
                         # filter out the negations
                         FILTER (( ! bound(?qualifier) || ! regex(?qualifier, \"^NOT\", \"i\")))

                      {
                         select ?has_participant {
                                 kice:RO_0000057 obo:IAO_0000219 ?has_participant .
                                 filter (?has_participant != obo:RO_0000057) .
                                 }
                      }
                }"
  }