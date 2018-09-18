{:description "This rule finds any previously reified physical entity record from Reactome and traces out its location via a unification xref to extract the physical entity's cellular location (a Gene Ontology cellular compartment).",
 :name "add_physical_entity_record_cellular_locations_from_human_reactome_to_ice_step_c",
 :reify ([?/cellular_location_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome cellular location vocabulary record" ?/go_cc), :prefix "R_"}]
         [?/go_xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record" ?/go_cc_xref), :prefix "R_"}]
         [?/go_xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record id field" ?/go_cc_id), :prefix "F_"}]
         [?/go_xref_db_field {:suffix "", :ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record db field" ?/go_db), :prefix "F_"}]),
 :head ((?/phent_record obo/BFO_0000051 ?/cellular_location_record)
        (?/cellular_location_record rdf/type ccp/IAO_EXT_0001521)
        (?/cellular_location_record rdf/type ccp/IAO_EXT_0001584)
        (?/cellular_location_record obo/BFO_0000051 ?/go_xref_record)
        (?/go_xref_record rdf/type ccp/IAO_EXT_0001572)
        (?/go_xref_record rdf/type ccp/IAO_EXT_0001588)
        (?/go_xref_record obo/BFO_0000051 ?/go_xref_db_field)
        (?/go_xref_db_field rdf/type ccp/IAO_EXT_0001519)
        (?/go_xref_db_field rdfs/label "GENE ONTOLOGY")
        (?/go_xref_record obo/BFO_0000051 ?/go_xref_id_field)
        (?/go_xref_id_field rdf/type ccp/IAO_EXT_0001520)
        (?/go_xref_id_field rdfs/label ?/clean_go_cc_id)
        (?/go_xref_id_field rdf/type ccp/IAO_EXT_0001596)),
 :sparql-string "#add_physical_entity_record_cellular_locations_from_human_reactome_to_ice_step_c.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?phent_record ?go_cc_id ?go_db ?go_cc ?go_cc_xref ?clean_go_cc_id 
WHERE {
?phent rdf:type bp:PhysicalEntity .
?phent bp:cellularLocation ?go_cc .
?go_cc bp:xref ?go_cc_xref .
?go_cc_xref rdf:type bp:UnificationXref .
?go_cc_xref bp:db ?go_db .
filter (regex (str (?go_db), \"^GENE ONTOLOGY$\")) .
?go_cc_xref bp:id ?go_cc_id .
bind (str (?go_cc_id) as ?clean_go_cc_id) .
GRAPH <file://add_physical_entity_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?phent obo:IAO_0000142 ?phent_record .
}
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
