;; ----------------------------------
;; --------- DIP Interactor ---------
;; ----------------------------------
`{:name "dip-id-exact-mapping-assertions"
  :description "This rule asserts an exact match between dip and other protein ids"
  :head ((?/dipInteractorIce skos/exactMatch ?/proteinIce))
  :body ((?/record iaodip/DipInteractor_interactorIDDataField1 ?/dipInteractorIce)
         (?/record iaodip/DipInteractor_dbXReferenceIDsDataField1 ?/proteinIce)
         (?/proteinIce kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein
  } 