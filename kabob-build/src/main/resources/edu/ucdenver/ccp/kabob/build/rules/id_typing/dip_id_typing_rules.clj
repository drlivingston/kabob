;; DIP ids are of type kiao/ProteinIdentifier
`{:name "dip-id-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
  :body ((_/f kiao/hasTemplate iaodip/DipInteractor_interactorIDDataField1)
         ;;dipinteractorIDDataField1)
        (_/f obo/IAO_0000219 ?/ice))} ; denotes


