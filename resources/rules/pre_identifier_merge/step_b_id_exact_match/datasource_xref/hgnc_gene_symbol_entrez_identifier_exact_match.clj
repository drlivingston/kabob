;; -----------------------------------------------------------------
;; --------- HGNC Gene Symbol Entrez Identifier Exact Match --------
;; -----------------------------------------------------------------
`{:name "hgnc-gene-symbol-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between a hgnc symbol and an entrez gene"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body ((?/record iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce)
         (?/record iaohgnc/HgncDownloadFileData_entrezGeneIDDataField1 ?/egIce))
  }