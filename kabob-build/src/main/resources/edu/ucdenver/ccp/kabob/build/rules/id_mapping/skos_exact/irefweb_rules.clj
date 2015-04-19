
`{:name "irefweb-protein-exact-match"
 :head ((?/irefice skos/exactMatch ?/otherice))

 :body ((_/idfv kiao/hasTemplate iaoirefweb/IRefWebInteractor_irogidDataField1)
        (_/idfv obo/IAO_0000219 ?/irefice)
        (?/irefice kiao/denotesSubClassOf obo/CHEBI_36080) ;protein)

        (_/interactorrec obo/has_part _/idfv)
        (_/interactorrec obo/has_part _/otheridfv)

        (_/otheridfv kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
        (_/otheridfv obo/IAO_0000219 ?/otherice)
        ;;this should be redundant but just to be sure
        (?/otherice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}

;;TODO replicate the above and create denotesSubClassOf for Complexes



;; IREFWEB ids are of type kiao/ProteinIdentifier
;; `{:name "irefweb-A-id-type-assertion"
;;  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
;;  :body ((_/f kiao/hasTemplate iaoirefweb/irefwebirefwebInteractorID_ADataField1)
;;         (_/f obo/IAO_0000219 ?/ice))}

;; `{:name "irefweb-B-id-type-assertion"
;;  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
;;  :body ((_/f kiao/hasTemplate iaoirefweb/irefwebirefwebInteractorID_BDataField1)
;;         (_/f obo/IAO_0000219 ?/ice))}


