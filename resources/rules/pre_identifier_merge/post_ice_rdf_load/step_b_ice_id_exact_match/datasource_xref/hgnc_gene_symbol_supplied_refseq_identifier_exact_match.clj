;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied RefSeq Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above
`{:name "hgnc-gene-symbol-supplied-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and a supplied refseq protein"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/refseq_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT ?hgnc_gene_symbol_identifier ?refseq_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000055> . # CCP:HGNC_gene_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?gene_symbol_field_value .
?gene_symbol_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000042> . # CCP:HGNC_gene_record_gene_symbol_field_value
?gene_symbol_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?hgnc_gene_symbol_identifier .
?hgnc_gene_symbol_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000186> . # CCP:HGNC_gene_symbol_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?refseq_gene_identifier_field_value .
?refseq_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000167> . # CCP:HGNC_gene_record_supplied_refseq_identifier_field_value
?refseq_gene_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?refseq_identifier .
?refseq_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001640> . # ccp:REFSEQ_genomic_identifier
}"
}