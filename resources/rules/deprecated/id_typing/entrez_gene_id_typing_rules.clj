;; All Entrez Gene identifiers are of type kiao/DNAIdentifier
`{:name "eg-id-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body ((_/f kiao/hasTemplate iaoeg/EntrezGeneInfoFileData_geneIDDataField1)
        (_/f obo/IAO_0000219 ?/ice)) ; denotes

  ;;:options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}

  ;; :options {:magic-prefixes [
  ;;                            ["franzOption_chunkProcessingAllowed" "franz:yes"]
  ;;                            ["franzOption_chunkProcessingSize" "franz:1000000"]
  ;;                            ["franzOption_clauseReorderer" "franz:identity"]
  ;;                            ]}

}
