
;; PharmGKB drug cross references 
`{:name "pharmgkb-crossref-exact-mapping-assertion"
 :head ((?/drug skos/exactMatch ?/other))
  :body
  (~@(kabob/rtv _/record
      iaopharmgkb/PharmGkbDrugFileRecord_accessionIdDataField1 ?/drug
      iaopharmgkb/PharmGkbDrugFileRecord_crossReferencesDataField1 ?/other))}

