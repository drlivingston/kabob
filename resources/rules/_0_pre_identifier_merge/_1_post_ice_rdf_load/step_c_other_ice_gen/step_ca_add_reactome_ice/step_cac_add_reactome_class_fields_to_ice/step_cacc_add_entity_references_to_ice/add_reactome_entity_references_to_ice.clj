`{:description "This rule finds any entity reference field described in Reactome.",
 :name "add_reactome_entity_references_to_ice",
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/entity_reference_record)
        (?/entity_reference_record rdf/type ccp/IAO_EXT_0001518) ;; entity reference field
        ),
 :body "#add_reactome_entity_references_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?entity_reference_record WHERE {
 ?reactome_thing bp:entityReference ?entity_reference .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
 ?entity_reference ccp:ekws_temp_biopax_connector_relation ?entity_reference_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
