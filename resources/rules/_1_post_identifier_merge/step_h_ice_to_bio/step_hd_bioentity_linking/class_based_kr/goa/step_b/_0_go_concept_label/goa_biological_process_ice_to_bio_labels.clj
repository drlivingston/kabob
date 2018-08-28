;; ----------------------------------------------------------------------------------
;; --------- Gene Ontology Annotation Biological Process Concept Assignment ---------
;; ----------------------------------------------------------------------------------
`{:name          "step-hdb_goa-biological-process-ice-to-bio-labels"
  :description   "This rule creates a subclass of every biological process and types it as a gene ontology biological process concept identifier  (IAO_EXT_0000103)"
  :head          ((?/bp_sc rdfs/label ?/biological_process_label)
                   (?/participating_bioentity_sc rdfs/label ?/participating_bioentity_label))
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                prefix franzOption_clauseReorderer: <franz:identity>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                select  ?bp_sc ?participating_bioentity_sc ?participating_bioentity_label ?biological_process_label {

                    {
                     select ?has_participant {
                                              kice:RO_0000057 obo:IAO_0000219 ?has_participant .
                                              filter (?has_participant != obo:RO_0000057) .
                                              }
                     }


                                                ?bp_sc rdfs:subClassOf ccp:temp_biological_process .
                                                ?bp_sc rdfs:subClassOf ?bp .
                                                filter (?bp != ccp:temp_biological_process) .

                    ?bp_sc rdfs:subClassOf ?r1 .
                    ?r1 owl:onProperty ?has_participant .
                    ?r1 owl:someValuesFrom ?participating_bioentity_sc .
                    ?participating_bioentity_sc rdfs:subClassOf ?participating_bioentity .

                    optional {?bp rdfs:label ?bpl}
                    bind(coalesce(?bpl, \"Unnamed biological process\") as ?bp_name)

                    optional {?participating_bioentity rdfs:label ?bl}
                    bind(coalesce(?bl, \"Unnamed biological process participant\") as ?bioentity_name)

                    bind(concat(str(?bioentity_name), \"; participating in: \", str(?bp_name)) as ?participating_bioentity_label)
                    bind(concat(str(?bp_name), \"; with participant: \", str(?bioentity_name)) as ?biological_process_label)


                    }"
}