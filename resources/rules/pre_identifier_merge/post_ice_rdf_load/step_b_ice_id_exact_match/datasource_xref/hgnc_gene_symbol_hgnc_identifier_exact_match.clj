;; ----------------------------------------------------
;; --------- HGNC Gene Symbol HGNC Identifier Exact Match --------
;; ----------------------------------------------------
`{:name "hgnc-gene-symbol-hgnc-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an hgnc identifiers"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/hgnc_gene_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000055) ; CCP:HGNC_gene_record
          (?/record obo/BFO_0000051 ?/gene_symbol_field_value)
          (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000042) ; CCP:HGNC_gene_record_gene_symbol_field_value
          (?/gene_symbol_field_value rdf/type ?/hgnc_gene_symbol_identifier)
          (?/hgnc_gene_symbol_identifier rdfs/subClassOf ccp/IAO_EXT_0000186) ; CCP:HGNC_gene_symbol_identifier
          (?/record obo/BFO_0000051 ?/hgnc_gene_identifier_field_value)
          (?/hgnc_gene_identifier_field_value rdf/type ccp/IAO_EXT_0000041) ; CCP:HGNC_gene_record_hgnc_identifier_field_value
          (?/hgnc_gene_identifier_field_value rdf/type ?/hgnc_gene_identifier)
          (?/hgnc_gene_identifier rdfs/subClassOf ccp/IAO_EXT_0000185)) ; ccp:HGNC_gene_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }