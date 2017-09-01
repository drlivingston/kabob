;; This rule set applies a sequence type to UniProt identifiers. All UniProt
;; entries are assumed to be proteins, so each is associated with pr:????
;;

;; All UniProt identifiers map to pr:???? or is it kso:????
`{:name "uniprot-entity-type-assertion"
  :head ((?/bio-entity rdf/type pr/????))
  :body ((_/record kiao/hasTemplate iaouniprot/uniprotUniProtDatFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaouniprot/uniprotprimaryUniProtIDDataField1 _/ice)
         (_/ice iao/IAO_0000219 ?/bio-entity))} ; denotes
