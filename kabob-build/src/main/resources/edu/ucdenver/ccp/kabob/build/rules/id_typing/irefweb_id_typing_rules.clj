
`{:name "irefweb-protein-interactor-types"
 :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
 :body ((_/typefv kiao/hasTemplate iaoirefweb/IRefWebInteractorType_interactorTypeNameDataField1)
        (_/typefv obo/IAO_0000219 "protein") ; denotes
        (_/typerec obo/BFO_0000051 _/typefv) ; has_part
        (_/interactorrec obo/BFO_0000051 _/typerec) ; has_part

        (_/interactorrec obo/BFO_0000051 _/idfv) ; has_part
        (_/idfv kiao/hasTemplate iaoirefweb/IRefWebInteractor_irogidDataField1)
        (_/idfv obo/IAO_0000219 ?/ice)) ; denotes

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}
