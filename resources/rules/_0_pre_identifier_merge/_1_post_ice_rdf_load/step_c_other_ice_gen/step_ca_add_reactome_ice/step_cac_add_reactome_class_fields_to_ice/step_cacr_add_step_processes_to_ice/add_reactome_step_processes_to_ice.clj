`{:description "This rule finds any step process field described in Reactome.",
 :name "add_reactome_step_processes_to_ice",
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/step_process_record)
        (?/step_process_record rdf/type ccp/IAO_EXT_0001548) ;; step process field
        ),
 :body "#add_reactome_step_processes_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?step_process_record WHERE {
 ?reactome_thing bp:stepProcess ?step_process .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
 ?step_process ccp:ekws_temp_biopax_connector_relation ?step_process_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
