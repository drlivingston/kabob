
;; PRO ICE skos:exactMatch [OTHER PROTEIN ID]
`{:name "pro-other-id-exact-mapping-assertion"
  :head ((?/proIce skos/exactMatch ?/otherIce))
  :body
  (;;(_/record kiao/hasTemplate iaopr/prProMappingRecordSchema1)
   ~@(kabob/rtv _/record
                iaopr/ProMappingRecord_mappingTypeDataField1       "exact"
                iaopr/ProMappingRecord_proteinOntologyIdDataField1 ?/proIce
                iaopr/ProMappingRecord_targetRecordIdDataField1    ?/otherIce)
   (?/otherIce kiao/denotesSubClassOf obo/CHEBI_36080))} ;protein
               ;;rdf/type kiao/ProteinIdentifier))}
