`{:description "This rule finds any cellular location vocabulary record described in Reactome with an xref.",
 :name "add_reactome_cellular_location_vocabularies_to_ice",
  :reify ([?/cl_vocab_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome cellular location vocabulary record" ?/cell_loc_vocab), :prefix "R_"}]
          [?/cl_vocab_term_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome term field" ?/react_term), :prefix "F_"}]
          ),
  :head ((?/cl_vocab_record rdf/type ccp/IAO_EXT_0001584) ;; cellular location vocabulary record
         (?/cl_vocab_record obo/BFO_0000051 ?/cl_vocab_xref_record)
         (?/cl_vocab_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/cl_vocab_record obo/BFO_0000051 ?/cl_vocab_term_field)
         (?/cl_vocab_term_field rdf/type ccp/IAO_EXT_0001948) ;; term field
         (?/cl_vocab_term_field rdfs/label ?/react_term)
         (?/cell_loc_vocab ccp/ekws_temp_biopax_connector_relation ?/cl_vocab_record)
         (?/cl_vocab_id_field rdf/type ?/go_uri)),
  :body "#add_reactome_cellular_location_vocabularies_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?cell_loc_vocab ?cl_vocab_xref_record ?react_term ?cl_vocab_id_field ?go_uri WHERE {
?cell_loc_vocab rdf:type bp:CellularLocationVocabulary .
?cell_loc_vocab bp:term ?react_term .
?cell_loc_vocab bp:xref ?react_xref .
?react_xref ccp:ekws_temp_biopax_connector_relation ?cl_vocab_xref_record .
?cl_vocab_xref_record obo:BFO_0000051 ?cl_vocab_id_field .
?cl_vocab_id_field rdf:type ccp:IAO_EXT_0001520 .
?cl_vocab_id_field rdfs:label ?go_id .
bind (strafter (str (?go_id), \":\") as ?clean_go_id).
bind (uri (concat (str (\"http://ccp.ucdenver.edu/kabob/ice/GO_\"), ?clean_go_id)) AS ?go_uri).
?cl_vocab_xref_record obo:BFO_0000051 ?cl_vocab_db_field .
?cl_vocab_db_field rdf:type ccp:IAO_EXT_0001519 .
?cl_vocab_db_field rdfs:label \"GENE ONTOLOGY\"@en .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

