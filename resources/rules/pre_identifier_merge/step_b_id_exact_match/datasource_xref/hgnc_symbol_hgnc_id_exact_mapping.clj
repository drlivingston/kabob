;; ----------------------------------------------------
;; --------- HGNC Symbol HGNC ID Exact Mapping --------
;; ----------------------------------------------------
`{:name "hgnc-symbol-hgnc-id-exact-mapping"
  :description "This rule asserts an exact match between a hgnc symbol and an hgnc id"
  :head ((?/hgncSymbolIce skos/exactMatch ?/hgncIntegerIdIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_hgncIDDataField1  ?/hgncIntegerIdIce))
  }