`{:description "This rule finds any physical entity field described in Reactome; it's not to be confused with the physical entity class in Reactome.",
 :name "add_reactome_physical_entities_to_ice_2",
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/physical_entity_record)
        (?/physical_entity_record rdf/type ccp/IAO_EXT_0001539) ;; physical entity field
        ),
 :body "#add_reactome_physical_entities_to_ice_2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?physical_entity_record WHERE {
 ?reactome_thing bp:physicalEntity ?physical_entity .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
 ?physical_entity ccp:ekws_temp_biopax_connector_relation ?physical_entity_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
