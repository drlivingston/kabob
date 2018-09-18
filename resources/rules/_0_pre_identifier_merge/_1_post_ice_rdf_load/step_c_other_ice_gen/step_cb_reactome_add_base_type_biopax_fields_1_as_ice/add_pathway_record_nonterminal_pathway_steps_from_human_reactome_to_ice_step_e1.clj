{:description "This rule finds any previously reified pathway record from Reactome and sets its nonterminal pathway order step process fields.",
 :name "add_pathway_record_nonterminal_pathway_steps_from_human_reactome_to_ice_step_e1",
  :head ((?/path_record obo/BFO_0000051 ?/pathway_step_record)
        (?/pathway_step_record rdf/type ccp/IAO_EXT_0001542) ;; Reactome pathway order field type
        (?/pathway_step_record rdf/type ccp/IAO_EXT_0001573) ;; Reactome pathway step record
        (?/pathway_step_record obo/BFO_0000051 ?/step_process_record)
        (?/step_process_record rdf/type ccp/IAO_EXT_0001548)
        (?/pathway_step_record obo/BFO_0000051 ?/next_step_record)
        (?/next_step_record rdf/type ccp/IAO_EXT_0001943)        
        ),
 :sparql-string "#add_pathway_record_nonterminal_pathway_steps_from_human_reactome_to_ice_step_e1.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?path_record ?pathway_step_record ?next_step_record ?step_process_record WHERE {
?path rdf:type bp:Pathway .
?path bp:pathwayOrder ?pathway_order .
?pathway_order bp:stepProcess ?step_process .
?pathway_order bp:nextStep ?next_step .
GRAPH <file://add_pathway_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?path obo:IAO_0000142 ?path_record .
}
?step_process obo:IAO_0000142 ?step_process_record .
GRAPH <file://add_pathway_step_records_from_human_reactome_to_ice_step_a.nt> { 
?pathway_order obo:IAO_0000142 ?pathway_step_record .
}
?next_step obo:IAO_0000142 ?next_step_record .
}"
}
