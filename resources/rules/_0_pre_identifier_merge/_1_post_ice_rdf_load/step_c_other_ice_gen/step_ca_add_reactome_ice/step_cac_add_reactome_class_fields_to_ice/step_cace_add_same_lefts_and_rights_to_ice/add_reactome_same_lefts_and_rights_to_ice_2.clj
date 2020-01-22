`{:description "This rule finds any biochemical reaction described in Reactome with the same left and right participants with no stoichiometric coefficients given for either.",
 :name "add_reactome_same_lefts_and_rights_to_ice_2",
 :head ((?/right_thing_record ccp/ekws_temp_biochemical_reaction_participant_relation ?/left_thing_record) ;; 
        (?/left_thing_record ccp/ekws_temp_biochemical_reaction_participant_relation ?/right_thing_record)
        (?/reactome_thing_record ccp/ekws_temp_biochemical_reaction_participant_relation ?/left_thing_record)
        (?/reactome_thing_record ccp/ekws_temp_biochemical_reaction_participant_relation ?/right_thing_record)
        ),
 :body "#add_reactome_same_lefts_and_rights_to_ice_2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?left_thing_record ?right_thing_record { 
 ?reactome_thing bp:left ?left_thing .
 ?reactome_thing bp:right ?right_thing .
OPTIONAL {
 ?reactome_thing bp:participantStoichiometry ?stoi_1 .
 ?reactome_thing bp:participantStoichiometry ?stoi_2 .
 ?stoi_1 bp:physicalEntity ?left_thing .
 ?stoi_2 bp:physicalEntity ?right_thing .
}
 filter (!bound (?stoi_1) && !bound (?stoi_2)) .
 filter (?left_thing = ?right_thing) .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
 ?left_thing ccp:ekws_temp_biopax_connector_relation ?left_thing_record .
 ?right_thing ccp:ekws_temp_biopax_connector_relation ?right_thing_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
