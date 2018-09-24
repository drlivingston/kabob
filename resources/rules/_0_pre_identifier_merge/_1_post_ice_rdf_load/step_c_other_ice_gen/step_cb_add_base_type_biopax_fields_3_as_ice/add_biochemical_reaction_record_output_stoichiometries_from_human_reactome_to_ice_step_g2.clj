`{:description "This rule finds any previously reified biochemical reaction record from Reactome and sets its right stoichiometry fields.",
 :name "add_biochemical_reaction_record_output_stoichiometries_from_human_reactome_to_ice_step_g2",
 :reify ([?/stoichiometry_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometry record" ?/stoi), :prefix "R_"}]
         [?/coefficient_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometric coefficient field" ?/stoi_coeff), :prefix "F_"}])
 :head ((?/bcr_record obo/BFO_0000051 ?/stoichiometry_record)
        (?/stoichiometry_record rdf/type ccp/IAO_EKW_002007) ;; Reactome participant stoichiometry field type
        (?/stoichiometry_record rdf/type ccp/IAO_EXT_0001545) ;; Reactome stoichiometry record
        (?/stoichiometry_record obo/BFO_0000051 ?/right_record)
        (?/right_record rdf/type ccp/IAO_EXT_0001539) ;; physical entity field value
        (?/stoichiometry_record obo/BFO_0000051 ?/coefficient_field)
        (?/coefficient_field rdfs/label ?/stoi_coeff)
        (?/coefficient_field rdf/type ccp/IAO_EXT_0001540) ;; stoichiometric coefficient field type
        ),
 :body "#add_biochemical_reaction_record_output_stoichiometries_from_human_reactome_to_ice_step_g2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?bcr_record ?stoi_coeff ?stoi ?right_record 
WHERE {
?bcr rdf:type bp:BiochemicalReaction .
?bcr bp:participantStoichiometry ?stoi .
?stoi bp:physicalEntity ?right .
?bcr bp:right ?right .
?stoi bp:stoichiometricCoefficient ?stoi_coeff .
GRAPH <file://add_biochemical_reaction_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?bcr obo:IAO_0000142 ?bcr_record .
}
?right obo:IAO_0000142 ?right_record .
GRAPH <file://add_biochemical_reaction_record_outputs_from_human_reactome_to_ice_step_f2.nt> {
?bcr_record obo:BFO_0000051 ?right_record .
?right_record rdf:type ccp:IAO_EXT_0001550 .
}
}"
}
