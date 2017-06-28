;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied Entrez Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above

`{:name "hgnc-gene-symbol-supplied-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an supplied entrez gene"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/ncbi_gene_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?hgnc_gene_symbol_identifier ?ncbi_gene_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000055> . # CCP:HGNC_gene_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?gene_symbol_field_value .
?gene_symbol_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000042> . # CCP:HGNC_gene_record_gene_symbol_field_value
?gene_symbol_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?hgnc_gene_symbol_identifier .
?hgnc_gene_symbol_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000186> . # CCP:HGNC_gene_symbol_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?ncbi_gene_identifier_field_value .
?ncbi_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000165> . # CCP:HGNC_gene_record_supplied_entrez_gene_identifier_field_value
?ncbi_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?ncbi_gene_identifier .
?ncbi_gene_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000084> . # ccp:NCBI_gene_identifier
}"
}