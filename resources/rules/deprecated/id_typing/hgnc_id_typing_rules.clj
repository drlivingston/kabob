;; HGNC ids are of type kiao/DNAIdentifier
`{:name "hgnc-id-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body ((_/f kiao/hasTemplate iaohgnc/HgncDownloadFileData_hgncIDDataField1)
         (_/f obo/IAO_0000219 ?/ice))} ; denotes

`{:name "hgnc-symbol-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body ((_/f kiao/hasTemplate
              iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1)
         (_/f obo/IAO_0000219 ?/ice))} ; denotes