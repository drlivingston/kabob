;; TODO: use IAO_EXT_ concepts
;; ----------------------------------------------------------
;; --------- RGD Gene Entrez identifier Exact Match ---------
;; ----------------------------------------------------------
`{:name "rgd-gene-entrez-identifier-exact-match"
  :description "This rule file asserts skos:exactMatch between each pair of RGD and EG gene identifiers"
  :head ((?/rgdIce skos/exactMatch ?/egIce))
  :body ((?/record ccp/RgdGeneFileRecord_geneIdDataField1 ?/rgdIce)
         (?/record ccp/RgdGeneFileRecord_entrezGeneIdsDataField1 ?/egIce))
  }