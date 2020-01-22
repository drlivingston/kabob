`{:description "This rule finds any left and right participant that are unchanged in a Reactome biochemical reaction.",
 :name "add_reactome_biochemical_reaction_participants_to_ice",
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/left_thing_record)
        (?/left_thing_record rdf/type ccp/IAO_EXT_0001944) ;; participant field
        (?/right_thing_record rdf/type ccp/IAO_EXT_0001944) ;; participant field
        ),
 :body "#add_reactome_biochemical_reaction_participants_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?left_thing_record ?right_thing_record WHERE {
 ?reactome_thing bp:left ?left_thing .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
 ?left_thing ccp:ekws_temp_biopax_connector_relation ?left_thing_record .
# Look for inputs and outputs that are the same thing; these are participants
 ?left_thing_record ccp:ekws_temp_biochemical_reaction_participant_relation ?right_thing_record .
 ?reactome_thing_record ccp:ekws_temp_biochemical_reaction_participant_relation ?right_thing_record .
 ?reactome_thing_record ccp:ekws_temp_biochemical_reaction_participant_relation ?left_thing_record .  
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
