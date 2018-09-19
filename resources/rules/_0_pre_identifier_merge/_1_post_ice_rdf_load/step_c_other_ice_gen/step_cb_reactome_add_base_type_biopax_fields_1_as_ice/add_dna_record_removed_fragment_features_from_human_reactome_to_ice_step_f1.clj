{:description "This rule finds any previously reified DNA record from Reactome and traces out any bits of the DNA that have been cleaved off (not its sequence modifications, which are described separately).",
 :name "add_dna_record_removed_fragment_features_from_human_reactome_to_ice_step_f1",
 :reify ([?/fragment_feature_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome fragment feature record" ?/frag_feature), :prefix "R_"}]
         [?/sequence_interval_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence interval record" ?/removed_chunk), :prefix "R_"}]
         [?/start_sequence_site_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record" ?/site1), :prefix "R_"}]
         [?/end_sequence_site_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record" ?/site2), :prefix "R_"}]
         [?/start_sequence_position {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record sequence position field" ?/start ?/site1), :prefix "F_"}]
         [?/start_position_status {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record position status field" ?/pos_stat_1 ?/site1), :prefix "F_"}]
         [?/end_sequence_position {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record sequence position field" ?/end ?/site2), :prefix "F_"}]
         [?/end_position_status {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record position status field" ?/pos_stat_2 ?/site2), :prefix "F_"}]),
 :head ((?/dna_record obo/BFO_0000051 ?/fragment_feature_record)
        (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001527)
        (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001587)
        (?/fragment_feature_record obo/BFO_0000051 ?/sequence_interval_record)
        (?/sequence_interval_record rdf/type ccp/IAO_EXT_0001532)
        (?/sequence_interval_record rdf/type ccp/IAO_EXT_0001576)
        (?/sequence_interval_record obo/BFO_0000051 ?/start_sequence_site_record)
        (?/start_sequence_site_record rdf/type ccp/IAO_EXT_0001534)
        (?/start_sequence_site_record rdf/type ccp/IAO_EXT_0001575)
        (?/start_sequence_site_record obo/BFO_0000051 ?/start_position_status)
        (?/start_position_status rdf/type ccp/IAO_EXT_0001537)
        (?/start_position_status rdfs/label "EQUAL")
        (?/start_sequence_site_record obo/BFO_0000051 ?/start_sequence_position)
        (?/start_sequence_position rdf/type ccp/IAO_EXT_0001538)
        (?/start_sequence_position rdfs/label ?/start)
        (?/sequence_interval_record obo/BFO_0000051 ?/end_sequence_site_record)
        (?/end_sequence_site_record rdf/type ccp/IAO_EXT_0001535)
        (?/end_sequence_site_record rdf/type ccp/IAO_EXT_0001575)
        (?/end_sequence_site_record obo/BFO_0000051 ?/end_position_status)
        (?/end_position_status rdf/type ccp/IAO_EXT_0001537)
        (?/end_position_status rdfs/label "EQUAL")
        (?/end_sequence_site_record obo/BFO_0000051 ?/end_sequence_position)
        (?/end_sequence_position rdf/type ccp/IAO_EXT_0001538)
        (?/end_sequence_position rdfs/label ?/end)),
 :sparql-string "#add_dna_record_removed_fragment_features_from_human_reactome_to_ice_step_f1.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?dna_record ?site1 ?site2 ?pos_stat_1 ?pos_stat_2 ?start ?end ?frag_feature ?removed_chunk 
WHERE {
GRAPH <file://add_dna_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?dna obo:IAO_0000142 ?dna_record .
} 
?dna rdf:type bp:Dna .
?dna bp:feature ?frag_feature .
?frag_feature rdf:type bp:FragmentFeature . 
?frag_feature bp:featureLocation ?removed_chunk .
?removed_chunk rdf:type bp:SequenceInterval .
?removed_chunk bp:sequenceIntervalBegin ?site1 .
?site1 rdf:type bp:SequenceSite .
?site1 bp:positionStatus ?pos_stat_1 .
?site1 bp:sequencePosition ?start .
?removed_chunk bp:sequenceIntervalEnd ?site2 .
?site2 rdf:type bp:SequenceSite .
?site2 bp:positionStatus ?pos_stat_2 .
?site2 bp:sequencePosition ?end .
filter (?site1 != ?site2) .
filter (regex (str (?pos_stat_1), \"^EQUAL$\")) .
filter (regex (str (?pos_stat_2), \"^EQUAL$\")) .
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
