;; --------------------------------------------------------------------------
;; --------- HGNC Gene Symbol Supplied Entrez Identifier Exact Match --------
;; --------------------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above

`{:name "hgnc-gene-symbol-supplied-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an supplied entrez gene"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_suppliedEntrezGeneIdDataField1 ?/egIce))
  }