;; --------------------------------------------------------------------------------
;; --------- UniProt Protein Alternative Identifier Exact Match --------
;; --------------------------------------------------------------------------------
`{:name "uniprot-protein-alternative-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and alternative identifiers"
  :head ((?/primary_uniprot_identifier skos/exactMatch ?/secondary_uniprot_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?primary_uniprot_identifier ?secondary_uniprot_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000977> . # CCP:uniprot_protein_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?primary_accession_field_value .
?primary_accession_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000240> . # CCP:uniprot_protein_record_primary_accession_field_value
?primary_accession_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?primary_uniprot_identifier .
?primary_uniprot_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000184> . # CCP:uniprot_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?accession_field_value .
?accession_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000931> . # CCP:uniprot_protein_record_accession_field_value
?accession_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?secondary_uniprot_identifier .
?secondary_uniprot_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000184> . # CCP:uniprot_identifier
}"
}