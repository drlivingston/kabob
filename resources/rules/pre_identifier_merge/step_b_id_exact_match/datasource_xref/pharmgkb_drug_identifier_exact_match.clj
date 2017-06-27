;; ------------------------------------------------------
;; --------- PharmGKB Drug Identifier Exact Match -------
;; ------------------------------------------------------
`{:name "pharmgkb-drug-identifier-exact-match"
  :description "This rule creates an exact match mapping between pahrmgkb drugs and other drugs"
  :head ((?/drug skos/exactMatch ?/other))
  :body
  ((?/record iaopharmgkb/PharmGkbDrugFileRecord_accessionIdDataField1 ?/drug)
   (?/record iaopharmgkb/PharmGkbDrugFileRecord_crossReferencesDataField1 ?/other))
  }