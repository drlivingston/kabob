{:description "This rule finds any previously reified pathway record from Reactome and annotates its GO BP identifiers if they exist.",
 :name "add_pathway_record_go_bp_ids_from_human_reactome_to_ice_step_d",
 :reify ([?/go_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record database field" "GENE ONTOLOGY"), :prefix "F_"}]
         [?/go_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record db identifier field" ?/go_bp_id), :prefix "F_"}]
         [?/go_xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record" "GENE ONTOLOGY" ?/go_bp_id), :prefix "F_"}]
         ),
 :head ((?/pw_record obo/BFO_0000051 ?/go_xref_record)
        (?/go_xref_record rdf/type ccp/IAO_EXT_0001588)
        (?/go_xref_record rdf/type ccp/IAO_EXT_0001570)
        (?/go_xref_record obo/BFO_0000051 ?/go_id_field)
        (?/go_xref_record obo/BFO_0000051 ?/go_db_field)
        (?/go_id_field rdf/type ccp/IAO_EXT_0001520)
        (?/go_id_field rdf/type ccp/IAO_EXT_0001596)
        (?/go_id_field rdfs/label ?/go_bp_id)
        (?/go_db_field rdf/type ccp/IAO_EXT_0001519)        
        ),
 :sparql-string "#add_pathway_record_go_bp_ids_from_human_reactome_to_ice_step_d.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT  ?pw_record ?go_bp_id ?go_db_field ?go_id_field 
WHERE {
?pw rdf:type bp:Pathway .    # a pathway
?pw bp:xref ?xref .       # with an xref 
?xref bp:db \"GENE ONTOLOGY\" .
?xref bp:id ?go_bp_id .
GRAPH <file://add_pathway_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?pw obo:IAO_0000142 ?pw_record .
}
}"
}
