;; --------------------------------------------------------
;; --------- HGNC Symbol Entrez Gene Exact Mapping --------
;; --------------------------------------------------------
`{:name "hgnc-symbol-entrez-exact-mapping"
  :description "This rule asserts an exact match between a hgnc symbol and an entrez gene"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_entrezGeneIDDataField1 ?/egIce))
  }