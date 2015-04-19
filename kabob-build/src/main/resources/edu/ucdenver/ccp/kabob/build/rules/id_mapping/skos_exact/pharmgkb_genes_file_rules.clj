
;; PharmGKB Gene ICE skos:exactMatch EG ICE
`{:name "pharmgkb-eg-id-exact-mapping-assertion"
 :head ((?/pharmGkbGeneIce skos/exactMatch ?/egIce))
  :body
  (;;(_/record kiao/hasTemplate
   ;;          iaopharmgkb/pharmgkbPharmGkbGeneFileRecordSchema1)
   ~@(kabob/rtv _/record
      iaopharmgkb/PharmGkbGeneFileRecord_accessionIdDataField1 ?/pharmGkbGeneIce
      iaopharmgkb/PharmGkbGeneFileRecord_entrezGeneIdDataField1 ?/egIce))}

