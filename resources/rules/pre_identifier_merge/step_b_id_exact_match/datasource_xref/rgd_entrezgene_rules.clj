;; The RGD GENES_RAT.txt file relates RGD IDs to Entrez Gene IDs (among others)
;; This rule file asserts skos:exactMatch between each pair of RGD and EG
;; IDs.


;; RGD_ID skos:exactMatch EntrezGeneID
`{:name "rgd-eg-id-exact-mapping-assertion"
  :head ((?/rgdIce skos/exactMatch ?/egIce))
  :body
  (;;(_/record kiao/hasTemplate iaomgi/mgiMGIEntrezGeneFileDataSchema1)
   ~@(kabob/rtv _/record
                iaorgd/RgdGeneFileRecord_geneIdDataField1 ?/rgdIce
                iaorgd/RgdGeneFileRecord_entrezGeneIdsDataField1 ?/egIce))}

