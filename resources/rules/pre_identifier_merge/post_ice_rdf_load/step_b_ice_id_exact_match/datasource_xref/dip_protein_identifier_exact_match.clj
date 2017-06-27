;; ------------------------------------------------------
;; --------- DIP Protein Identifier Exact Match ---------
;; ------------------------------------------------------
;;STILL NEEDS TO BE FIXED ONCE DIP IS ADDED TO CCP EXTENSION ONTOLOGY
`{:name "dip-protein-identifier-exact-match"
  :description "This rule asserts an exact match between dip and other protein identifiers"
  :head ((?/dipInteractorIce skos/exactMatch ?/proteinIce))
  :body ((?/record iaodip/DipInteractor_interactorIDDataField1 ?/dipInteractorIce)
         (?/record iaodip/DipInteractor_dbXReferenceIDsDataField1 ?/proteinIce)
         (?/proteinIce kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein
  } 