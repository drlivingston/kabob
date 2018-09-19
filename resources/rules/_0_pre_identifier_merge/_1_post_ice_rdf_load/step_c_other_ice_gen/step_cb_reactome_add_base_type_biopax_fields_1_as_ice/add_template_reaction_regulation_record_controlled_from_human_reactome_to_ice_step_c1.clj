{:description "This rule finds any previously reified template reaction regulation record from Reactome and sets its controlled field.",
 :name "add_template_reaction_regulation_record_controlled_from_human_reactome_to_ice_step_c1",
 :head ((?/trreg_record obo/BFO_0000051 ?/controlled_record)
        (?/controlled_record rdf/type ccp/IAO_EXT_0001567) ;; controlled field value
        ),
 :sparql-string "#add_template_reaction_regulation_record_controlled_from_human_reactome_to_ice_step_c1.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?trreg_record ?controlled ?controlled_record 
WHERE {
?trreg rdf:type bp:TemplateReactionRegulation .
?trreg bp:controlled ?controlled .
GRAPH <file://add_template_reaction_regulation_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?trreg obo:IAO_0000142 ?trreg_record .
}
?controlled obo:IAO_0000142 ?controlled_record .
}"
}
