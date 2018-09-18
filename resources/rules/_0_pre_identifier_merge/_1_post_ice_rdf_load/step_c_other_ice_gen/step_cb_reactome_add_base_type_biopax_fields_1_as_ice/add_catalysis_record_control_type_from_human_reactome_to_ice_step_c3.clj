{:description "This rule finds any previously reified catalysis record from Reactome and sets its control type fields.",
 :name "add_catalysis_record_control_type_from_human_reactome_to_ice_step_c3",
 :reify ([?/control_type_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome catalysis record control type field" ?/control_type), :prefix "F_"}]
         ),
 :head ((?/modul_record obo/BFO_0000051 ?/control_type_field)
        (?/control_type_field rdf/type ccp/IAO_EXT_0001568) ;; control type field value
        (?/control_type_field rdfs/label ?/control_type)
        ),
 :sparql-string "#add_catalysis_record_control_type_from_human_reactome_to_ice_step_c3.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?modul_record ?control_type WHERE {
?modul rdf:type bp:Catalysis .
?modul bp:controlType ?control_type .
GRAPH <file://add_catalysis_records_from_human_reactome_to_ice_step_a.nt> {
?modul obo:IAO_0000142 ?modul_record .
}
}"
}
