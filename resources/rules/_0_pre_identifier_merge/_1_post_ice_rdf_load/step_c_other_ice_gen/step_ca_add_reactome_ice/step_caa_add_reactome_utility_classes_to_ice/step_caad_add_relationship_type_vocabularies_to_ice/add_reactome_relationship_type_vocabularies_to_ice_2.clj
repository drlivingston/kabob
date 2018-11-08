`{:description "This rule finds any relationship type vocabulary record described in Reactome with an xref.",
 :name "add_reactome_relationship_type_vocabularies_to_ice_2",
  :reify ([?/rt_vocab_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome relationship type vocabulary record" ?/rel_type_vocab), :prefix "R_"}]
          [?/rt_vocab_term_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome term field" ?/react_term), :prefix "F_"}]
          ),
  :head ((?/rt_vocab_record rdf/type ccp/IAO_EXT_0001579) ;; relationship type vocabulary record
         (?/rt_vocab_record obo/BFO_0000051 ?/rt_vocab_term_field)
         (?/rt_vocab_term_field rdf/type ccp/IAO_EXT_0001948) ;; term field
         (?/rt_vocab_term_field rdfs/label ?/react_term)
         (?/rel_type_vocab ccp/ekws_temp_biopax_connector_relation ?/rt_vocab_record)),
  :body "#add_reactome_relationship_type_vocabularies_to_ice_2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?rel_type_vocab ?react_term WHERE {
?rel_type_vocab rdf:type bp:RelationshipTypeVocabulary .
?rel_type_vocab bp:term ?react_term .
optional {
?rel_type_vocab bp:xref ?react_xref .
}
filter (!bound (?react_xref)) .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

