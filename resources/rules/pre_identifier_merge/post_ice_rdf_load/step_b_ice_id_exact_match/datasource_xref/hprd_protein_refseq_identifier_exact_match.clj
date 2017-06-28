;; ------------------------------------------------------------
;; --------- HPRD Protein RefSeq Identifier Exact Match -------
;; ------------------------------------------------------------
`{:name "hprd-protein-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between refseq proteins and swissprot proteins"
  :head ((?/hprd skos/exactMatch ?/refseq))
  :body ((?/record ccp/HprdIdMappingsTxtFileData_hprdIDDataField1 ?/hprd)
         (?/record ccp/HprdIdMappingsTxtFileData_swissProtIDsDataField1 ?/refseq))
  }