;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "goa-molecular-function-ice-to-bio"
  :description   "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head          (
                   ;; create a subclass of the molecular function
                   (?/molecular_function_sc rdfs/subClassOf ?/molecular_function)
                   ;; create a subclass of the participating bioentity
                   (?/bioentity_sc rdfs/subClassOf ?/participating_bioentity)

                   ;; create a has_participant restriction
                   (?/participation_restriction rdf/type owl/Restriction)
                   (?/participation_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/participation_restriction owl/someValuesFrom ?/bioentity_sc)

                   ;; connect the process subclass to the participation restriction
                   (?/molecular_function_sc rdfs/subClassOf ?/participation_restriction)

                   ;; provenance: connect the record to the process subclass
                   (?/record obo/IAO_0000219 ?/molecular_function_sc)) ; IAO:denotes


  :reify         ([?/molecular_function_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity "mf")
                                            :ns "ccp" :prefix "B_"}]
                   [?/bioentity_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity)
                                    :ns "ccp" :prefix "B_"}]
                   [?/participation_restriction {:ln (:restriction)
                                                 :ns "ccp" :prefix "RS_"}])

  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?participating_bioentity ?molecular_function ?has_participant ?record
                WHERE {  ?go_mf_identifier rdfs:subClassOf ccp:IAO_EXT_0000199 . # CCP:GO_molecular_function_identifier
                         ?go_identifier_field_value rdf:type ?go_mf_identifier .
                         ?go_identifier_field_value rdf:type ccp:IAO_EXT_0000014 . # ccp:GOA_GAF_v2.0_Annotation_record__ontology_term_identifier_field_value
                         ?go_mf_identifier obo:IAO_0000219 ?molecular_function .
                         filter (contains(str(?molecular_function),'http://ccp.ucdenver.edu/obo/ext/'))
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
                                 ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
                                 filter (?has_participant != obo:RO_0000057) .
                                 }
                      }
                }"

  :options       {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }