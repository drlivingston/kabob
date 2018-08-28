;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "step-hda_goa-molecular-function-ice-to-bio"
  :description   "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head          (
                   ;; create a subclass of the molecular function
                   (?/molecular_function_sc rdfs/subClassOf ccp/temp_molecular_function)
                   (?/molecular_function_sc rdfs/subClassOf ?/molecular_function)

                   ;; create a subclass of the participating bioentity
                   (?/bioentity_sc rdfs/subClassOf ?/participating_bioentity)

                   (?/process_sc rdfs/subClassOf ?/process_root)

                   ;; create a has_participant restriction
                   (?/participation_restriction rdf/type owl/Restriction)
                   (?/participation_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/participation_restriction owl/someValuesFrom ?/bioentity_sc)

                   ;; connect the process subclass to the participation restriction
                   (?/process_sc rdfs/subClassOf ?/participation_restriction)

                   (?/realizes_restriction rdf/type owl/Restriction)
                   (?/realizes_restriction owl/onProperty ?/realizes)
                   (?/realizes_restriction owl/someValuesFrom ?/molecular_function_sc)

                   (?/process_sc rdfs/subClassOf ?/realizes_restriction)

                   ;; provenance
                   (?/record obo/IAO_0000219 ?/molecular_function_sc) ; IAO:denotes
                   (?/record obo/IAO_0000219 ?/bioentity_sc) ; IAO:denotes
                   (?/record obo/IAO_0000219 ?/process_sc)
                   (?/record obo/IAO_0000219 ?/participation_restriction)
                   (?/record obo/IAO_0000219 ?/realizes_restriction)) ; IAO:denotes


  :reify         ([?/molecular_function_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity "mf")
                                            :ns "kbio" :prefix "B_"}]
                   [?/bioentity_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity)
                                    :ns "kbio" :prefix "B_"}]
                   [?/process_sc {:ln (:sha-1 ?/molecular_function ?/participating_bioentity "process")
                                    :ns "kbio" :prefix "B_"}]
                   [?/participation_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}]
                   [?/realizes_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}])

  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?participating_bioentity ?molecular_function ?has_participant ?realizes ?process_root ?record
                WHERE {

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

                ?go_mf_identifier rdfs:subClassOf ccp:IAO_EXT_0000199 . # CCP:GO_molecular_function_identifier
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



                }"

  :options       {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }