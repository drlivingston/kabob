;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied RefSeq Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above
`{:name "hgnc-gene-symbol-supplied-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and a supplied refseq protein"
  :head ((?/hgnc_gene_symbol_identifier skos/exactMatch ?/refseq_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000055) ; CCP:HGNC_gene_record
          (?/record obo/BFO_0000051 ?/gene_symbol_field_value)
          (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000042) ; CCP:HGNC_gene_record_gene_symbol_field_value
          (?/gene_symbol_field_value rdf/type ?/hgnc_gene_symbol_identifier)
          (?/hgnc_gene_symbol_identifier rdfs/subClassOf ccp/IAO_EXT_0000186) ; CCP:HGNC_gene_symbol_identifier
          (?/record obo/BFO_0000051 ?/refseq_gene_identifier_field_value)
          (?/refseq_gene_identifier_field_value rdf/type ccp/IAO_EXT_0000167) ; CCP:HGNC_gene_record_supplied_refseq_identifier_field_value
          (?/refseq_gene_identifier_field_value rdf/type ?/refseq_identifier)
          (?/refseq_identifier rdfs/subClassOf ccp/IAO_EXT_0001640)) ; ccp:REFSEQ_genomic_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }