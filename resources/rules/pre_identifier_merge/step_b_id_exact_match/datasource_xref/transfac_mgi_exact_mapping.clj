;; -----------------------------------------
;; --------- Transfac MGI Mapping ----------
;; -----------------------------------------
`{:name "transfac-mgi-exact-mapping"
  :description "This rule asserts an exact match between transfec and mgi gene"
  :head ((?/transfacGeneIce skos/exactMatch ?/mgiIce))
  :body ((?/record iaotransfac/TransfacGeneDatFileData_transfacGeneIDDataField1 ?/transfacGeneIce)
         (?/record iaotransfac/TransfacGeneDatFileData_mgiDatabaseReferenceIDDataField1 ?/mgiIce))
  }