`{:description "This rule finds any EC number field described in Reactome.",
 :name "add_reactome_ec_numbers_to_ice",
 :reify ([?/ec_number_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome EC number field" ?/ec_number), :prefix "F_"}])
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/ec_number_field)
        (?/ec_number_field rdf/type ccp/IAO_EXT_0001952) ;; EC number field
        (?/ec_number_field rdfs/label ?/ec_number)
        ),
 :body "#add_reactome_ec_numbers_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?ec_number WHERE {
 ?reactome_thing bp:eCNumber ?ec_number .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
