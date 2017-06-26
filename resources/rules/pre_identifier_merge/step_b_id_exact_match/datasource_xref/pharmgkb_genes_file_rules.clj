;; --------------------------------
;; --------- PharmGKB Genes -------
;; --------------------------------
`{:name "pharmgkb-eg-id-exact-mapping-assertion"
  :description "This rule asserts exact matches between pharmgkb genes and entrez genes"
  :head ((?/pharmGkbGeneIce skos/exactMatch ?/egIce))
  :body ((?/record iaopharmgkb/PharmGkbGeneFileRecord_accessionIdDataField1 ?/pharmGkbGeneIce)
         (?/record iaopharmgkb/PharmGkbGeneFileRecord_entrezGeneIdDataField1 ?/egIce))
  }