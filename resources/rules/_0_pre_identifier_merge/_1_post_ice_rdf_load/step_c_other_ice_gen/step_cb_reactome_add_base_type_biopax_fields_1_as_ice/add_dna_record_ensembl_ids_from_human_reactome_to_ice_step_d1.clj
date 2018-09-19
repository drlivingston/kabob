{:description "This rule finds any previously reified DNA record from Reactome and traces out its parent entity via a unification xref to extract the DNA's ENSEMBL ID.",
 :name "add_dna_record_ensembl_ids_from_human_reactome_to_ice_step_d1",
 :reify ([?/entity_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome dna reference record" ?/entity_ref), :prefix "R_"}]
         [?/entity_xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record" ?/entity_xref), :prefix "R_"}]
         [?/entity_xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record id field" ?/up_id), :prefix "F_"}]
         [?/entity_xref_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record db field" ?/up_db), :prefix "F_"}]),
 :head ((?/dna_record obo/BFO_0000051 ?/entity_record)
        (?/entity_record rdf/type ccp/IAO_EXT_0001518)
        (?/entity_record rdf/type ccp/IAO_EXT_0001560)
        (?/entity_record obo/BFO_0000051 ?/entity_xref_record)
        (?/entity_xref_record rdf/type ccp/IAO_EXT_0001572)
        (?/entity_xref_record rdf/type ccp/IAO_EXT_0001588)
        (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_db_field)
        (?/entity_xref_db_field rdf/type ccp/IAO_EXT_0001519)
        (?/entity_xref_db_field rdfs/label "ENSEMBL")
        (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_id_field)
        (?/entity_xref_id_field rdf/type ccp/IAO_EXT_0001520)
        (?/entity_xref_id_field rdfs/label ?/clean_up_id)
        (?/entity_xref_id_field rdf/type ccp/IAO_EXT_0001523)),
 :sparql-string "#add_dna_record_ensembl_ids_from_human_reactome_to_ice_step_d1.clj
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
SELECT ?dna_record ?entity_ref ?entity_xref ?up_db ?up_id ?clean_up_id
WHERE {
?dna rdf:type bp:Dna .
?dna bp:entityReference ?entity_ref .
?entity_ref bp:xref ?entity_xref .
?entity_xref rdf:type bp:UnificationXref .
?entity_xref bp:db ?up_db .
filter (regex (str (?up_db), \"^ENSEMBL$\")) .
?entity_xref bp:id ?up_id .
bind (str (?up_id) as ?clean_up_id) .
GRAPH <file://add_dna_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?dna obo:IAO_0000142 ?dna_record .
}
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
