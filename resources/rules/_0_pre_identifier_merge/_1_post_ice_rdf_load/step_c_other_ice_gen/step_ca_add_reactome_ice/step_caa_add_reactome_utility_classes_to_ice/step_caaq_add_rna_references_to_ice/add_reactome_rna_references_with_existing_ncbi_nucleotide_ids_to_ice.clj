`{:description "This rule finds any rna reference record described in Reactome with an existing NCBI nucleotide ID via an HGNC id. It may miss names that don't match the HGNC id.",
 :name "add_reactome_rna_references_with_existing_ncbi_nucleotide_ids_to_ice",
 :reify ([?/rna_ref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome rna reference record" ?/rna_ref), :prefix "R_"}]
         ),
  :head ((?/rna_ref_record rdf/type ccp/IAO_EXT_0001561) ;; rna reference
         (?/rna_ref_record obo/BFO_0000051 ?/xref_record)
         (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/ncbi_ice rdfs/subClassOf ccp/IAO_EXT_0001397) ;; ncbi_nucleotide id field
         (?/xref_id_field rdf/type ?/ncbi_ice)
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001597) ;; reactome ncbi nucleotide identifier field
         (?/rna_ref ccp/ekws_temp_biopax_connector_relation ?/rna_ref_record)),
  :body "#add_reactome_rna_references_with_existing_ncbi_nucleotide_ids_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?rna_ref ?xref_record ?xref_id_field ?ncbi_ice WHERE {
?rna_ref rdf:type bp:RnaReference .
?rna_ref bp:name ?react_name .
?rna_ref bp:xref ?xref .
?xref ccp:ekws_temp_biopax_connector_relation ?xref_record .
?xref_record obo:BFO_0000051 ?xref_id_field .
?xref_id_field rdf:type ccp:IAO_EXT_0001520 .
?xref_id_field rdfs:label ?ncbi_nucleotide_id .
?xref_record obo:BFO_0000051 ?xref_db_field .
?xref_db_field rdf:type ccp:IAO_EXT_0001519 .
?xref_db_field rdfs:label \"NCBI Nucleotide\"@en .
# ok, we have to find these via HGNC ICE ids based on the rna's name
bind (uri (concat (str (\"http://ccp.ucdenver.edu/kabob/ice/HGNC_\"), str (?react_name))) as ?hgnc_ice) . 
# then we grab the hgnc id that matches and find an exact match with an ncbi id
?hgnc_ice skos:exactMatch ?ncbi_ice .
filter (contains (str (?ncbi_ice), \"http://ccp.ucdenver.edu/kabob/ice/NCBI_\")) .
?ncbi_ice rdfs:subClassOf ccp:IAO_EXT_0000084 .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

