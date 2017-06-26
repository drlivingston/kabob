;; -----------------------------------------------
;; --------- Uniprot RefSeq Exact Mapping --------
;; -----------------------------------------------
`{:name "uniprot-refseq-exact-mapping"
  :description "This rule asserts an exact match between a refseq catalog and a uniprot protein"
  :head ((?/uniprot skos/exactMatch ?/refseq))
  :body ((?/record iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1 ?/uniprot)
         (?/record iaouniprot/UniProtIDMappingFileData_refseqIdsDataField1 ?/refseq)
         (?/refseq kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein
  }