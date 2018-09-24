`{:description "This rule finds any previously reified biochemical reaction record from Reactome and sets its output fields, where the input and output are not the same entity.",
 :name "add_biochemical_reaction_record_outputs_from_human_reactome_to_ice_step_f2",
 :head ((?/bcr_record obo/BFO_0000051 ?/right_participant_record)
        (?/right_participant_record rdf/type ccp/IAO_EXT_0001550)),
 :body "#add_biochemical_reaction_record_outputs_from_human_reactome_to_ice_step_f2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT  ?bcr_record ?right_participant ?right_participant_record 
WHERE {
?bcr rdf:type bp:BiochemicalReaction .    # a biochemical reaction
?bcr bp:right ?right_participant .       # with a right participant 
OPTIONAL {
?right_participant ccp:ekws_temp_connector_relation ?right_participant_field .
}
FILTER (! bound (?right_participant_field)).
GRAPH <file://add_biochemical_reaction_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?bcr obo:IAO_0000142 ?bcr_record .
}
?right_participant obo:IAO_0000142 ?right_participant_record .
GRAPH <file://add_biochemical_reaction_record_conversion_directions_from_human_reactome_to_ice_step_c.nt> {
?bcr_record obo:BFO_0000051 ?direction_field .
?direction_field rdf:type ccp:IAO_EXT_0001546 .
?direction_field rdfs:label \"LEFT-TO-RIGHT\"@en .
}
}"
}
