;; ------------------------------------------------------
;; --------- Entrez Gene Identifier Exact Match ---------
;; ------------------------------------------------------
`{:name "entrez-gene-identifier-exact-match"
  :description "This rule asserts an exact match between entrez gene identifiers and other gene identifiers"
  :head((?/gene_identifier skos/exactMath ?/other_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
SELECT ?record ?gene_identifier ?other_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000086> . #CCP:NCBI_gene_info_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?gene_id_field_value .
?gene_id_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000876> . # CCP:NCBI_gene_info_record__gene_identifier_field_value
?gene_id_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?gene_identifier .
?gene_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000084> . # CCP:ncbi_gene_identifier
    ?record <http://purl.obolibrary.org/obo/BFO_0000051> ?db_xref_field_value .
?db_xref_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000880> . # CCP:NCBI_gene_info_record__database_cross_reference_field_value
?db_xref_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?other_identifier .
?other_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf>* <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000182> . # CCP:DNA_identifier
}"

}