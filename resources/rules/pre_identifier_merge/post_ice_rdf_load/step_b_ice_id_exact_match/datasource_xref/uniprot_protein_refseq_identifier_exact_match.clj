;; ---------------------------------------------------------------------------
;; --------- UniProt Protein RefSeq Identifier Exact Match --------
;; ---------------------------------------------------------------------------
`{:name "uniprot-protein-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and refseq identifiers"
  :head ((?/uniprot_identifier skos/exactMatch ?/refseq_identifier))
:sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?refseq_identifier ?uniprot_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000235> . # CCP:uniprot_identifier_mapping_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?uniprot_identifier_field_value .
?uniprot_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000239> . # CCP:uniprot_identifier_mapping_record_uniprot_accession_identifier_field_value
?uniprot_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?uniprot_identifier .
?uniprot_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000184> . # CCP:uniprot_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?refseq_identifier_field_value .
?refseq_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000243> . # CCP:uniprot_identifier_mapping_record_refseq_identifier_field_value
?refseq_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?refseq_identifier .
?refseq_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001638> . # CCP:refseq_protein_identifier
}"
}
