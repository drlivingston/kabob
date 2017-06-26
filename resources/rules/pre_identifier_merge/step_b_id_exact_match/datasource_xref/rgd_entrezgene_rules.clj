;; -------------------------------
;; --------- RGD EG Gene ---------
;; -------------------------------
`{:name "rgd-eg-id-exact-mapping-assertion"
  :description "This rule file asserts skos:exactMatch between each pair of RGD and EG"
  :head ((?/rgdIce skos/exactMatch ?/egIce))
  :body ((?/record iaorgd/RgdGeneFileRecord_geneIdDataField1 ?/rgdIce)
         (?/record iaorgd/RgdGeneFileRecord_entrezGeneIdsDataField1 ?/egIce))
  }