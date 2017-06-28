;; -------------------------------------------------------------
;; --------- PharmGKB Gene Entrez Identifier Exact Match -------
;; -------------------------------------------------------------
`{:name "pharmgkb-gene-entrez-identifier-exact-match"
  :description "This rule asserts exact matches between pharmgkb genes and entrez genes"
  :head ((?/pharmgkb_gene_identifier skos/exactMatch ?/ncbi_gene_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?pharmgkb_gene_identifier ?ncbi_gene_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000822> . # CCP:PharmGKB_gene_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?accession_identifier_field_value .
?accession_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001022> . # CCP:PharmGKB_gene_record_accession_identifier_field value
?accession_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?pharmgkb_gene_identifier .
?pharmgkb_gene_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001429> . # CCP:PharmGKB_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?entrez_gene_identifier_field_value .
?entrez_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001023> . # CCP:PharmGKB_gene_record_entrez_gene_field value
?entrez_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?ncbi_gene_identifier .
?ncbi_gene_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000084> . # CCP:ncbi_gene_identifier
}"
}
