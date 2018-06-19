;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "step-hdb0_goa-molecular-function-go-concept-temp-labels"
  :description   "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head          ((?/mf_sc ccp/temp_name ?/mf_name))

  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                select  ?mf_sc ?mf_name  {

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


                      ?mf_sc rdfs:subClassOf ccp:temp_molecular_function .
                      ?mf_sc rdfs:subClassOf ?mf .
                      filter (?mf != ccp:temp_molecular_function) .
                             ?realizes_r owl:someValuesFrom ?mf_sc .
                             ?realizes_r owl:onProperty ?realizes .
                             ?process_sc rdfs:subClassOf ?realizes_r .
                             ?process_sc rdfs:subClassOf ?participant_r .
                             ?participant_r owl:onProperty ?has_participant .
                             ?participant_r owl:someValuesFrom ?participating_bioentity_sc .
                             ?participating_bioentity_sc rdfs:subClassOf ?participating_bioentity .


                             optional {?mf rdfs:label ?mfl}
                             bind(coalesce(?mfl, \"Unnamed molecular function\") as ?mf_name)

                      }"

  :options       {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }