{:description "This rule finds any previously reified biochemical reaction record from Reactome and sets its participant fields, where the input and output are the same entity.",
 :name "add_biochemical_reaction_record_participants_from_human_reactome_to_ice_step_e",
 :head ((?/bcr_record obo/BFO_0000051 ?/left_participant_record)
        (?/left_participant_record rdf/type ccp/IAO_EXT_0001941) ;; Reactome BCR participant field type
        (?/left_participant ccp/ekws_temp_connector_relation ?/left_participant_record ) ;; connector relation
        (?/bcr_record obo/BFO_0000051 ?/right_participant_record)
        (?/right_participant_record rdf/type ccp/IAO_EXT_0001941) ;; Reactome BCR participant field type
        (?/right_participant ccp/ekws_temp_connector_relation ?/right_participant_record ) ;; connector relation
        ),
 :sparql-string "#add_biochemical_reaction_record_participants_from_human_reactome_to_ice_step_e.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT  ?bcr_record ?left_participant ?right_participant ?left_participant_record ?right_participant_record
WHERE {
?bcr rdf:type bp:BiochemicalReaction .    # a biochemical reaction
?bcr bp:left ?left_participant .       # with a left participant 
?bcr bp:right ?right_participant .       # with a right participant 
filter (?left_participant = ?right_participant) .   # same input and output
GRAPH <file://add_biochemical_reaction_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?bcr obo:IAO_0000142 ?bcr_record .
}
?left_participant obo:IAO_0000142 ?left_participant_record .
?right_participant obo:IAO_0000142 ?right_participant_record .
}"
}
