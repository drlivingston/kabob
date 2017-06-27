;; ---------------------------------------------------------------------------
;; --------- UniProt Protein RefSeq Identifier Exact Match --------
;; ---------------------------------------------------------------------------
`{:name "uniprot-protein-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and refseq identifiers"
  :head ((?/uniprot skos/exactMatch ?/refseq))
  :body ((?/record iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1 ?/uniprot)
         (?/record iaouniprot/UniProtIDMappingFileData_refseqIdsDataField1 ?/refseq)
         (?/refseq kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein
  }