`{:description "This rule finds any control type field described in Reactome.",
 :name "add_reactome_control_types_to_ice",
 :reify ([?/control_type_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome control type field" ?/control_type), :prefix "F_"}])
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/control_type_record)
        (?/control_type_record rdf/type ccp/IAO_EXT_0001568) ;; control type field
        (?/control_type_record rdfs/label ?/control_type) ;; entity reference field
        ),
 :body "#add_reactome_control_types_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?control_type WHERE {
 ?reactome_thing bp:controlType ?control_type .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
