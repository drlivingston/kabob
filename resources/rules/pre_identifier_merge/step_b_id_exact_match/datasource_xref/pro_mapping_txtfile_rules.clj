;; ---------------------------------------
;; --------- PRO Mapping TxtFile ---------
;; ---------------------------------------
`{:name "pro-other-id-exact-mapping-assertion"
  :description "This rule asserts an exact match between proteins and other protein ids"
  :head ((?/proIce skos/exactMatch ?/otherIce))
  :body ((?/record iaopr/ProMappingRecord_mappingTypeDataField1 "exact")
         (?/record iaopr/ProMappingRecord_proteinOntologyIdDataField1 ?/proIce)
         (?/record iaopr/ProMappingRecord_targetRecordIdDataField1 ?/otherIce)
         (?/otherIce kiao/denotesSubClassOf obo/CHEBI_36080)) ; CHEBI:protein
 }