;; ----------------------------------------
;; --------- HPRD SwissProt Mapping -------
;; ----------------------------------------
`{:name "hprd-swissprot-exact-mapping"
  :description "This rule asserts an exact match between hprd proteins and swissprot proteins"
  :head ((?/hprd skos/exactMatch ?/swiss))
  :body ((?/record iaohprd/HprdIdMappingsTxtFileData_hprdIDDataField1 ?/hprd)
         (?/record iaohprd/HprdIdMappingsTxtFileData_swissProtIDsDataField1 ?/swiss))
  }