`{:description "This rule finds any sequence modification vocabulary record described in Reactome with an xref.",
 :name "add_reactome_sequence_modification_vocabularies_to_ice",
  :reify ([?/sm_vocab_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence modification vocabulary record" ?/seq_mod_vocab), :prefix "R_"}]
          [?/sm_vocab_term_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome term field" ?/react_term), :prefix "F_"}]
          ),
  :head ((?/sm_vocab_record rdf/type ccp/IAO_EXT_0001583) ;; sequence modification vocabulary record
         (?/sm_vocab_record obo/BFO_0000051 ?/sm_vocab_xref_record)
         (?/sm_vocab_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/sm_vocab_record obo/BFO_0000051 ?/sm_vocab_term_field)
         (?/sm_vocab_term_field rdf/type ccp/IAO_EXT_0001948) ;; term field
         (?/sm_vocab_term_field rdfs/label ?/react_term)
         (?/seq_mod_vocab ccp/ekws_temp_biopax_connector_relation ?/sm_vocab_record)),
  :body "#add_reactome_sequence_modification_vocabularies_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?seq_mod_vocab ?sm_vocab_xref_record ?react_term WHERE {
?seq_mod_vocab rdf:type bp:SequenceModificationVocabulary .
?seq_mod_vocab bp:term ?react_term .
?seq_mod_vocab bp:xref ?react_xref .
?react_xref bp:db \"MOD\" .
?react_xref ccp:ekws_temp_biopax_connector_relation ?sm_vocab_xref_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

