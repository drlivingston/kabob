;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied RefSeq Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above
`{:name "hgnc-gene-symbol-supplied-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and a supplied refseq protein"
  :head ((?/hgncSymbolIce skos/exactMatch ?/refseq))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_suppliedRefseqIdDataField1 ?/refseq)
         (?/refseq kiao/denotesSubClassOf obo/SO_0000352)) ; SO:dna
  }