
;; TRANSFAC Gene ICE skos:exactMatch EG ICE
`{:name "transfac-eg-id-exact-mapping-assertion"
  :head ((?/tfIce skos/exactMatch ?/egIce))
  :body
  (;;(_/record kiao/hasTemplate
   ;;          iaotransfac/transfacTransfacGeneDatFileDataSchema1)
   ~@(kabob/rtv _/record
        iaotransfac/TransfacGeneDatFileData_transfacGeneIDDataField1 ?/tfIce
     iaotransfac/TransfacGeneDatFileData_entrezGeneDatabaseReferenceIDDataField1
                                                                  ?/egIce))}


;; TRANSFAC Gene ICE skos:exactMatch MGI ICE
`{:name "transfac-mgi-id-exact-mapping-assertion"
  :head ((?/transfacGeneIce skos/exactMatch ?/mgiIce))
  :body
  (;;(_/record kiao/hasTemplate
   ;;          iaotransfac/transfacTransfacGeneDatFileDataSchema1)
   ~@(kabob/rtv _/record
        iaotransfac/TransfacGeneDatFileData_transfacGeneIDDataField1
                                                               ?/transfacGeneIce
        iaotransfac/TransfacGeneDatFileData_mgiDatabaseReferenceIDDataField1
                                                               ?/mgiIce))}






