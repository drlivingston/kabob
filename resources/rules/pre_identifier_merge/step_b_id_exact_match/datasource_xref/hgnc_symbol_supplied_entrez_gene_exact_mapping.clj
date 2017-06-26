;; -----------------------------------------------------------------
;; --------- HGNC Symbol Supplied Entrez Gene Exact Mapping --------
;; -----------------------------------------------------------------
;;TODO what's the difference between the "supplied" version below and the above

`{:name "hgnc-symbol-supplied-entrez-exact-mapping"
  :description "This rule asserts an exact match between a hgnc symbol and an supplied entrez gene"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_suppliedEntrezGeneIdDataField1 ?/egIce))
  }