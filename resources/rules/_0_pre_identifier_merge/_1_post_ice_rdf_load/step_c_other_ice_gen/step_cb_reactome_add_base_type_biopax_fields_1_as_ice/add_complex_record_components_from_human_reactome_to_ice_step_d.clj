{:description "This rule finds any previously reified complex record from Reactome and sets its component fields.",
 :name "add_complex_record_components_from_human_reactome_to_ice_step_d",
 :head ((?/compl_record obo/BFO_0000051 ?/component_record)
        (?/component_record rdf/type ccp/IAO_EXT_0001544) ;; Reactome component field type
        ),
 :sparql-string "#add_complex_record_components_from_human_reactome_to_ice_step_d.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?compl_record ?component ?component_record 
WHERE { 
?compl rdf:type bp:Complex .
?compl bp:component ?component .
GRAPH <file://add_complex_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?compl obo:IAO_0000142 ?compl_record .
}
?component obo:IAO_0000142 ?component_record .
}"
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

