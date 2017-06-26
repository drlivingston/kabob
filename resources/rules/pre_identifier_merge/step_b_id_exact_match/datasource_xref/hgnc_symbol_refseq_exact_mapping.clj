;; ---------------------------------------------------
;; --------- HGNC Symbol RefSeq Exact Mapping --------
;; ---------------------------------------------------
`{:name "hgnc-symbol-refseq-exact-mapping"
  :description "This rule asserts an exact match between a hgnc symbol and a refseq protein"
  :head ((?/hgncSymbolIce skos/exactMatch ?/refseq))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_refseqIDsDataField1 ?/refseq)
         (?/refseq kiao/denotesSubClassOf obo/SO_0000352)) ; SO:dna
  }