;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied Entrez Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above

`{:name "hgnc-gene-symbol-supplied-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an supplied entrez gene"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/ncbi_gene_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000055) ; CCP:HGNC_gene_record
          (?/record obo/BFO_0000051 ?/gene_symbol_field_value)
          (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000042) ; CCP:HGNC_gene_record_gene_symbol_field_value
          (?/gene_symbol_field_value rdf/type ?/hgnc_gene_symbol_identifier)
          (?/hgnc_gene_symbol_identifier rdfs/subClassOf ccp/IAO_EXT_0000186) ; CCP:HGNC_gene_symbol_identifier
          (?/record obo/BFO_0000051 ?/ncbi_gene_identifier_field_value)
          (?/ncbi_gene_identifier_field_value rdf/type ccp/IAO_EXT_0000165) ; CCP:HGNC_gene_record_supplied_entrez_gene_identifier_field_value
          (?/ncbi_gene_identifier_field_value rdf/type ?/ncbi_gene_identifier)
          (?/ncbi_gene_identifier rdfs/subClassOf ccp/IAO_EXT_0000084)) ; ccp:NCBI_gene_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }