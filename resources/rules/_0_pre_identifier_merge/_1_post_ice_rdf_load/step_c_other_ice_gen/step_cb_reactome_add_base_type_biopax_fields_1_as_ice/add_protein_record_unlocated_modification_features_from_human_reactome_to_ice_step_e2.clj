{:description "This rule finds any previously reified protein record from Reactome and traces out its sequence modifications (not cleavages, which are described separately) via a unification xref to extract the PSI-MOD modification type ID assuming it is not precisely located.",
 :name "add_protein_record_unlocated_modification_features_from_human_reactome_to_ice_step_e2",
 :reify ([?/modification_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome modification feature record" ?/mod_feature), :prefix "R_"}]
         [?/modification_type_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence modification vocabulary record" ?/mod_type), :prefix "R_"}]
         [?/modification_xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record" ?/mod_xref), :prefix "R_"}]
         [?/modification_xref_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record db field" ?/psi_mod_db), :prefix "F_"}]
         [?/modification_xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record id field" ?/psi_mod_id), :prefix "F_"}]),
 :head ((?/prot_record obo/BFO_0000051 ?/modification_record)
        (?/modification_record rdf/type ccp/IAO_EXT_0001527)
        (?/modification_record rdf/type ccp/IAO_EXT_0001586)
        (?/modification_record obo/BFO_0000051 ?/modification_type_record)
        (?/modification_type_record rdf/type ccp/IAO_EXT_0001547)
        (?/modification_type_record rdf/type ccp/IAO_EXT_0001583)
        (?/modification_type_record obo/BFO_0000051 ?/modification_xref_record)
        (?/modification_xref_record rdf/type ccp/IAO_EXT_0001588)
        (?/modification_xref_record rdf/type ccp/IAO_EXT_0001572)
        (?/modification_xref_record obo/BFO_0000051 ?/modification_xref_db_field)
        (?/modification_xref_db_field rdf/type ccp/IAO_EXT_0001519)
        (?/modification_xref_db_field rdfs/label "MOD")
        (?/modification_xref_record obo/BFO_0000051 ?/modification_xref_id_field)
        (?/modification_xref_id_field rdf/type ccp/IAO_EXT_0001520)
        (?/modification_xref_id_field rdfs/label ?/clean_psi_mod_id)
        (?/modification_xref_id_field rdf/type ccp/IAO_EXT_0001592)),
 :sparql-string "#add_protein_record_unlocated_modification_features_from_human_reactome_to_ice_step_e2.clj
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
SELECT ?prot_record ?psi_mod_id ?psi_mod_db ?clean_psi_mod_id ?mod_feature ?mod_type ?mod_xref
WHERE {
GRAPH <file://add_protein_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?prot obo:IAO_0000142 ?prot_record .
}
?prot rdf:type bp:Protein .
?prot bp:feature ?mod_feature .
?mod_feature rdf:type bp:ModificationFeature .
?mod_feature bp:modificationType ?mod_type .
?mod_type rdf:type bp:SequenceModificationVocabulary .
?mod_type bp:xref ?mod_xref .
?mod_xref rdf:type bp:UnificationXref .
?mod_xref bp:db ?psi_mod_db .
filter (regex (str (?psi_mod_db), \"^MOD$\")) .
?mod_xref bp:id ?psi_mod_id .
bind (str (?psi_mod_id) as ?clean_psi_mod_id) .
OPTIONAL {
?mod_feature bp:featureLocation ?seq_site .
?seq_site rdf:type bp:SequenceSite .
?seq_site bp:positionStatus ?pos_stat .
?seq_site bp:sequencePosition ?mod_aa_position .
} 
filter (! bound (?pos_stat)) .
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
