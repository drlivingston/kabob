`{:name "hprd-swissprot-exact-mapping"
  :head ((?/hprd skos/exactMatch ?/swiss))
  :body
  (~@(kabob/rtv _/record
        iaohprd/HprdIdMappingsTxtFileData_hprdIDDataField1 ?/hprd
        iaohprd/HprdIdMappingsTxtFileData_swissProtIDsDataField1 ?/swiss))}

`{:name "hprd-refseq-exact-mapping"
  :head ((?/hprd skos/exactMatch ?/refseq))
  :body
  (~@(kabob/rtv _/record
        iaohprd/HprdIdMappingsTxtFileData_hprdIDDataField1 ?/hprd
        iaohprd/HprdIdMappingsTxtFileData_proteinAccessionDataField1 ?/refseq))}
;;TODO: do we need to verify it's a protein refseq?
;;   (?/refseq rdf/type kiao/DNAIdentifier))}