;; ------------------------------------------------------
;; --------- Entrez Gene Identifier Exact Match ---------
;; ------------------------------------------------------
`{:name "entrez-gene-identifier-exact-match"
  :description "This rule asserts an exact match between entrez gene identifiers and other gene identifiers"
  :head((?/gene_identifier skos/exactMath ?/other_identifier))
  :body((?/record rdf/type ccp/IAO_EXT_0000681) ; CCP:NCBI_gene_info_record
        (?/record obo/BFO_0000051 ?/gene_id_field_value)
        (?/gene_id_field_value rdf/type ccp/IAO_EXT_0000876) ; CCP:NCBI_gene_info_record__gene_identifier_field_value
        (?/gene_id_field_value rdf/type ?/gene_identifier)
        (?/gene_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000182) ; CCP:DNA_identifier
        (?/record obo/BFO_0000051 ?/db_xref_field_value) 
        (?/db_xref_field_value rdf/type ccp/IAO_EXT_0000880) ; CCP:NCBI_gene_info_record__database_cross_reference_field_value
        (?/db_xref_field_value rdf/type ?/other_identifier)
        (?/other_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000182)) ; ccp:DNA_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }