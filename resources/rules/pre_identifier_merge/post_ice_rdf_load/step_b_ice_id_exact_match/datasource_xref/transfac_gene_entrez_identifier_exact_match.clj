;; TODO: use IAO_EXT_ concepts
;; -------------------------------------------------------------
;; --------- Transfac Gene Entrez Identifier Exact Match -------
;; -------------------------------------------------------------
`{:name "transfac-gene-entrez-identifier-exact-match"
  :description "This rule asserts an exact match between transfec and entrez gene"
  :head ((?/tfIce skos/exactMatch ?/egIce))
  :body ((?/record iaotransfac/TransfacGeneDatFileData_transfacGeneIDDataField1 ?/tfIce)
         (?/record iaotransfac/TransfacGeneDatFileData_mgiDatabaseReferenceIDDataField1 ?/egIce))
  }






