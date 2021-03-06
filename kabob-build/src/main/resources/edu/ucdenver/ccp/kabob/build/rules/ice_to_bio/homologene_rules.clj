;;;; Rules to map genes together bases on homology

;;; current modeling using homologous_to but this could create an
;;;   explosion of triples which may or may not be undesirable


`{:name "homologene-eg-gene-homologous-to"
  :head ((?/gene1 obo/homologous_to ?/gene2);)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/homologous_to)
         (?/r1 owl/someValuesFrom ?/gene2)

         ;;still need to add a sufficient definition?
         (?/gene1 rdfs/subClassOf ?/r1))

  :body (
         (?/fv0 kiao/hasTemplate  iaohomologene/HomoloGeneDataFileData_homologeneGroupIDDataField1)
         ;;we don't actually care what the group is the FV is unique and shared
         ;;(?/fv0 obo/IAO_0000219 ?/group)

         (?/record1 obo/has_part ?/fv0)
         (?/record1 obo/has_part ?/fv1)
         (?/fv1 kiao/hasTemplate iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1)
         (?/fv1 obo/IAO_0000219 ?/gene1ice)

         (?/record2 obo/has_part ?/fv0)
         (?/record2 obo/has_part ?/fv2)
         (?/fv2 kiao/hasTemplate iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1)
         (?/fv2 obo/IAO_0000219 ?/gene2ice)

         (?/gene1ice obo/IAO_0000219 ?/gene1)
         (?/gene2ice obo/IAO_0000219 ?/gene2))
  :reify (?/r1)
}



;; `{:name "homologene-eg-gene-homologous-to"
;;   :head ((?/gene1 obo/homologous_to ?/gene2);)

;;          (?/r1 rdf/type owl/Restriction)
;;          (?/r1 owl/onProperty obo/homologous_to)
;;          (?/r1 owl/someValuesFrom ?/gene2)

;;          ;;still need to add a sufficient definition?
;;          (?/gene1 rdfs/subClassOf ?/r1))

;;   :body (~@(kabob/rtv _/record
;;                       iaohomologene/HomoloGeneDataFileData_homologeneGroupIDDataField1 _/group
;;                       iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1 _/gene1ice)
;;          ~@(kabob/rtv _/record2
;;                       iaohomologene/HomoloGeneDataFileData_homologeneGroupIDDataField1 _/group
;;                       iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1 _/gene2ice)
;;          (_/gene1ice obo/IAO_0000219 ?/gene1)
;;          (_/gene2ice obo/IAO_0000219 ?/gene2))
;;   :reify (?/r1)
;; }
