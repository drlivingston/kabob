;; The MGI_EntrezGene.rpt file relates MGI IDs to Entrez Gene IDs.
;; This rule file asserts skos:exactMatch between each pair of MGI and EG
;; IDs.
;;
;; The MGI_EntrezGene.rpt file also relates MGI IDs to secondary IDs. These are 
;; deprecated? (NEED TO CHECK) and are connected via skos:???


;; MGI_Accession skos:relatedMatch EntrezGeneID
`{:name "mgi-eg-id-exact-mapping-assertion"
  :head ((?/mgiIce skos/exactMatch ?/egIce))
  :body
  (;;(_/record kiao/hasTemplate iaomgi/mgiMGIEntrezGeneFileDataSchema1)
   ~@(kabob/rtv _/record
                iaomgi/MGIEntrezGeneFileData_mgiAccessionIDDataField1 ?/mgiIce
                iaomgi/MGIEntrezGeneFileData_entrezGeneIDDataField1   ?/egIce))}

;; MGI_Accession skos:???Match Secondary_MGI_Accession
;;`{:name "mgi-exact-altmgi-id-mapping-assertion"
;; :head ((?/mgiIce skos/exactMatch ?/secondaryMgiIce))
;; :body ((_/record kiao/hasTemplate iaomgi/mgiMGIEntrezGeneFileDataSchema1)
;;            (_/f ro/part_of _/record)
;;            (_/f kiao/hasTemplate iaomgi/mgimgiAccessionIDDataField1)
;;            (_/f iao/IAO_0000219 ?/mgiIce)
;;            (_/f2 ro/part_of _/record)
;;            (_/f2 kiao/hasTemplate iaomgi/mgisecondaryAccessionIDs)
;;            (_/f2 iao/IAO_0000219 ?/secondaryMgiIce))}

