`{:description "This rule finds any dna reference record described in Reactome with an existing ENSEMBL ID.",
 :name "add_reactome_dna_references_with_existing_ensembl_ids_to_ice",
 :reify ([?/dna_ref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome dna reference record" ?/dna_ref), :prefix "R_"}]
         ),
  :head ((?/dna_ref_record rdf/type ccp/IAO_EXT_0001560) ;; dna reference
         (?/dna_ref_record obo/BFO_0000051 ?/xref_record)
         (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001523) ;; reactome ensembl id field
         (?/xref_id_field rdf/type ?/ensembl_ice)
         (?/dna_ref ccp/ekws_temp_biopax_connector_relation ?/dna_ref_record)),
  :body "#add_reactome_dna_references_with_existing_ensembl_ids_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?dna_ref ?xref_record ?xref_id_field ?ensembl_ice WHERE {
?dna_ref rdf:type bp:DnaReference .
?dna_ref bp:xref ?xref .
?xref ccp:ekws_temp_biopax_connector_relation ?xref_record .
?xref_record obo:BFO_0000051 ?xref_id_field .
?xref_id_field rdf:type ccp:IAO_EXT_0001520 .
?xref_id_field rdfs:label ?ensembl_id .
?xref_record obo:BFO_0000051 ?xref_db_field .
?xref_db_field rdf:type ccp:IAO_EXT_0001519 .
?xref_db_field rdfs:label \"ENSEMBL\"@en .
bind (uri (concat (str (\"http://ccp.ucdenver.edu/kabob/ice/ENSEMBL_\"), str (?ensembl_id))) as ?ensembl_ice) . 

}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

