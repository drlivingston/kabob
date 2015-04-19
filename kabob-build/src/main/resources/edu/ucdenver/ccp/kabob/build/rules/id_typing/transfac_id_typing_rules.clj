;; TRANSFAC ids are of type kiao/DNAIdentifier
`{:name "transfac-id-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body
  ((_/f kiao/hasTemplate
        iaotransfac/TransfacGeneDatFileData_transfacGeneIDDataField1)
        ;;transfactransfacGeneIDDataField1)
   (_/f obo/IAO_0000219 ?/ice))}
