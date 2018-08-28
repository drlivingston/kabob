;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Molecular Function Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "step-hdb1_goa-molecular-function-labels"
  :description   "This rule creates a subclass of every molecular function and types it as a gene ontology molecular function concept identifier  (IAO_EXT_0000103)"
  :head          ((?/mf_sc rdfs/label ?/molecular_function_label)
                   (?/participating_bioentity_sc rdfs/label ?/participating_bioentity_label)
                   (?/process_sc rdfs/label ?/process_root_label))
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                select  ?mf_sc ?participating_bioentity_sc ?process_sc ?participating_bioentity_label ?molecular_function_label ?process_root_label {

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
                           ?mf_sc ccp:temp_name ?mf_name .
                           ?realizes_r owl:someValuesFrom ?mf_sc .
                           ?realizes_r owl:onProperty ?realizes .
                           ?process_sc rdfs:subClassOf ?realizes_r .
                           ?process_sc rdfs:subClassOf ?participant_r .
                           ?participant_r owl:onProperty ?has_participant .
                           ?participant_r owl:someValuesFrom ?participating_bioentity_sc .
                           ?participating_bioentity_sc rdfs:subClassOf ?participating_bioentity .

                           optional {?participating_bioentity rdfs:label ?bl}
                           bind(coalesce(?bl, \"Unnamed molecular function realizer\") as ?bioentity_name)

                           bind(concat(str(?bioentity_name), \"; realizes function: \", str(?mf_name)) as ?participating_bioentity_label)
                           bind(concat(str(?mf_name), \"; realized by: \", str(?bioentity_name)) as ?molecular_function_label)
                           bind(concat(\"Realization process of \", str(?mf_name), \" by \", str(?bioentity_name)) as ?process_root_label)




                    }"

  :options       {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }