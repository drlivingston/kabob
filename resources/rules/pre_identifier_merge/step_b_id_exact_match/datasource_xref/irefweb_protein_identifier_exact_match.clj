;; --------------------------------------------------------
;; --------- IRefWeb Protein Identifier Exact Match -------
;; --------------------------------------------------------
`{:name "irefweb-protein-identifier-exact-match"
  :description "This rule asserts an exact match relation between irefweb proteins and other proteins"
  :head ((?/irefice skos/exactMatch ?/otherice))

  :body ((?/idfv kiao/hasTemplate iaoirefweb/IRefWebInteractor_irogidDataField1)
         (?/idfv obo/IAO_0000219 ?/irefice) ; IAO:denotes
         (?/irefice kiao/denotesSubClassOf obo/CHEBI_36080) ; CHEBI:protein)

         (?/interactorrec obo/BFO_0000051 ?/idfv) ; BFO:has_part
         (?/interactorrec obo/BFO_0000051 ?/otheridfv) ; BFO:has_part

         (?/otheridfv kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
         (?/otheridfv obo/IAO_0000219 ?/otherice) ; IAO:denotes
         ;;this should be redundant but just to be sure
         (?/otherice kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein

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


