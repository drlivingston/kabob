{:description "This rule finds any previously reified RNA record from Reactome and traces out any bits of the RNA that have been cleaved off (not its sequence modifications, which are described separately) when those are annoyingly described as modifications.",
 :name "add_rna_record_located_modification_features_from_human_reactome_to_ice_step_e1",
 :reify ([?/fragment_feature_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome modification feature record" ?/frag_feature), :prefix "R_"}]
         [?/comment_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome record - comment field" ?/c), :prefix "F_"}]
         [?/end_index {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome record - end range from comment field" ?/end ?/c), :prefix "F_"}],
         [?/start_index {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome record - start range from comment field" ?/start ?/c), :prefix "F_"}]),
 :head ((?/rna_record obo/BFO_0000051 ?/fragment_feature_record)
        (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001527)
        (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001586)
        (?/fragment_feature_record obo/BFO_0000051 ?/comment_field)
        (?/comment_field rdfs/label ?/c)
        (?/comment_field obo/BFO_0000051 ?/start_index)
        (?/comment_field obo/BFO_0000051 ?/end_index)
        (?/start_index rdfs/label ?/start)
        (?/end_index rdfs/label ?/end)),
 :sparql-string "#add_rna_record_located_modification_features_from_human_reactome_to_ice_step_e1.clj
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
SELECT ?rna_record ?start ?end ?c ?frag_feature 
WHERE { 
GRAPH <file://add_rna_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?rna obo:IAO_0000142 ?rna_record .
}
?rna rdf:type bp:Rna .
?rna bp:feature ?frag_feature .
?frag_feature rdf:type bp:ModificationFeature .
?frag_feature bp:comment ?c .
bind (strafter (str(?c), (\"Deletion of residues \")) AS ?range) .
bind (strbefore (str(?range), (\" to \")) AS ?start) .
bind (strafter (str(?range), (\" to \")) AS ?end) .
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
