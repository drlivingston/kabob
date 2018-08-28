;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "step-hd_goa-molecular-function-ice-to-bio-instance-based-kr"
  :description   "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head          ((?/molecular_function_instance rdf/type ?/molecular_function)
                   (?/bioentity_instance rdf/type ?/participating_bioentity)
                   (?/process_instance rdf/type ?/process_root)

                   (?/process_instance ?/has_participant ?/bioentity_instance)
                   (?/process_instance ?/realizes ?/molecular_function_instance)

                   ;; provenance: connect the record to the process subclass
                   (?/record obo/IAO_0000219 ?/molecular_function_instance)
                   (?/record obo/IAO_0000219 ?/process_instance)
                   (?/record obo/IAO_0000219 ?/bioentity_instance)) ; IAO:denotes


  :reify         ([?/molecular_function_instance {:ln (:sha-1 ?/molecular_function ?/participating_bioentity "mf")
                                            :ns "kbio" :prefix "B_"}]
                   [?/process_instance {:ln (:sha-1 ?/molecular_function ?/participating_bioentity "process")
                                  :ns "kbio" :prefix "B_"}]
                   [?/bioentity_instance {:ln (:sha-1 ?/molecular_function ?/participating_bioentity)
                                    :ns "kbio" :prefix "B_"}])

  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?participating_bioentity ?molecular_function ?has_participant ?record
                WHERE {  ?go_mf_identifier rdfs:subClassOf ccp:IAO_EXT_0000199 . # CCP:GO_molecular_function_identifier
                         ?go_identifier_field_value rdf:type ?go_mf_identifier .
                         ?go_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
                         ?go_mf_identifier obo:IAO_0000219 ?molecular_function .
                         filter (contains(str(?molecular_function),'http://ccp.ucdenver.edu/kabob/bio/'))
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
                      {
                                select ?realizes {
                                                  kice:BFO_0000055 obo:IAO_0000219 ?realizes .
                                                  filter (?realizes != obo:BFO_0000055) .
                                                  }
                                }

                 {
                  select ?process_root {
                                        kice:GO_0008150 obo:IAO_0000219 ?process_root .
                                        filter (?process_root != obo:GO_0008150) .
                                        }
                  }
                }"

  :options       {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }