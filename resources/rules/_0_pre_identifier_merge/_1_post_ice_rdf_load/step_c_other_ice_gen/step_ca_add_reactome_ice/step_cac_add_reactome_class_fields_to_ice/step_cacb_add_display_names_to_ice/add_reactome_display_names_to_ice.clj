`{:description "This rule finds any display name field described in Reactome.",
 :name "add_reactome_display_names_to_ice",
 :reify ([?/display_name_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome display name field" ?/display_name), :prefix "F_"}])
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/display_name_field)
        (?/display_name_field rdf/type ccp/IAO_EXT_0001526) ;; display name field
        (?/display_name_field rdfs/label ?/display_name)
        ),
 :body "#add_reactome_display_names_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?display_name WHERE {
 ?reactome_thing bp:displayName ?display_name .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
