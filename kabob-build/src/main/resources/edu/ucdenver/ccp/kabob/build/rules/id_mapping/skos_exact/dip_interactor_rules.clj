
;; DIP ID ICE skos:exactMatch other protein ICE
`{:name "dip-id-exact-mapping-assertions"
  :head ((?/dipInteractorIce skos/exactMatch ?/proteinIce))
  :body
  (;;(_/record kiao/hasTemplate iaodip/dipDipInteractorSchema1)
   ~@(kabob/rtv _/record
                iaodip/DipInteractor_interactorIDDataField1 ?/dipInteractorIce
                iaodip/DipInteractor_dbXReferenceIDsDataField1 ?/proteinIce)
   (?/proteinIce kiao/denotesSubClassOf obo/CHEBI_36080))} ;protein




       