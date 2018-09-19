{:description "This rule finds any previously reified pathway record from Reactome and sets its pathway component fields.",
 :name "add_pathway_record_components_from_human_reactome_to_ice_step_c1",
 :head ((?/path_record obo/BFO_0000051 ?/pathway_component_record)
        (?/pathway_component_record rdf/type ccp/IAO_EXT_0001541) ;; Reactome payhway component field type
        ),
 :sparql-string "#add_pathway_record_components_from_human_reactome_to_ice_step_c1.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?path_record ?pathway_component ?pathway_component_record 
WHERE {
?path rdf:type bp:Pathway .
?path bp:pathwayComponent ?pathway_component .
GRAPH <file://add_pathway_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?path obo:IAO_0000142 ?path_record .
}
?pathway_component obo:IAO_0000142 ?pathway_component_record .
}"
}
