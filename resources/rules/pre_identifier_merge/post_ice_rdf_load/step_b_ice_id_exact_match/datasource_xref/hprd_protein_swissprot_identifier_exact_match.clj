;; ---------------------------------------------------------------
;; --------- HPRD Protein SwissProt Identifier Exact Match -------
;; ---------------------------------------------------------------
`{:name "hprd-protein-swissprot-identifier-exact-match"
  :description "This rule asserts an exact match between refseq proteins and swissprot proteins"
  :head ((?/hprd skos/exactMatch ?/swiss))
  :body ((?/record iaohprd/HprdIdMappingsTxtFileData_hprdIDDataField1 ?/hprd)
         (?/record iaohprd/HprdIdMappingsTxtFileData_swissProtIDsDataField1 ?/swiss))
  }