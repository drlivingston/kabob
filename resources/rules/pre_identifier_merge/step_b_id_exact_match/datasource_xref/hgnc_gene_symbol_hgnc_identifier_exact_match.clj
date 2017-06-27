;; ----------------------------------------------------
;; --------- HGNC Gene Symbol HGNC Identifier Exact Match --------
;; ----------------------------------------------------
`{:name "hgnc-gene-symbol-hgnc-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an hgnc identifiers"
  :head ((?/hgncSymbolIce skos/exactMatch ?/hgncIntegerIdIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_hgncIDDataField1  ?/hgncIntegerIdIce))
  }