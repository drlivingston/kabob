;; ---------------------------------------------------
;; --------- Uniprot AltUniprot Exact Mapping --------
;; ---------------------------------------------------
`{:name "uniprot-altuniprot-exact-mapping"
  :description "This rule asserts an exact match between a refseq catalog and a uniprot protein"
  :head ((?/uniprotIce skos/exactMatch ?/secondaryUniprotIce))
  :body ((?/record kiao/hasTemplate iaouniprot/uniprotUniProtDatFileDataSchema1)
         (?/f ro/part_of ?/record)
         (?/f kiao/hasTemplate iaouniprot/uniprotprimaryUniProtIDDataField1)
         (?/f iao/IAO_0000219 ?/uniprotIce) ; IAO:denotes
         (?/f2 ro/part_of ?/record)
         (?/f2 kiao/hasTemplate iaouniprot/uniprotsecondaryUniprotIDsDataField1)
         (?/f2 iao/IAO_0000219 ?/secondaryUniprotIce)) ; IAO:denotes
  }