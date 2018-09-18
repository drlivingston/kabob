{:description "This rule finds any previously reified RNA record from Reactome and records its name field(s).",
 :name "add_rna_record_names_from_human_reactome_to_ice_step_b1",
 :reify ([?/name_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome rna record name field" ?/name), :prefix "F_"}]),
 :head ((?/rna_record obo/BFO_0000051 ?/name_field)
        (?/name_field rdf/type ccp/IAO_EXT_0001525)
        (?/name_field rdfs/label ?/name)),
 :sparql-string "#add_rna_record_names_from_human_reactome_to_ice_step_b1.clj
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
SELECT ?rna_record ?name
WHERE {
?rna rdf:type bp:Rna .
?rna bp:name ?name .
GRAPH <file://add_rna_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?rna obo:IAO_0000142 ?rna_record .
}
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
