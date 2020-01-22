;; ---------------------------------------------------
;; --------- Homologene Entrez Gene Homology ---------
;; ---------------------------------------------------
`{:name "homologene-entrez-gene-homology"
  :description ""
  :head (;(?/gene1 obo/RO_0002158 ?/gene2);)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0002158) ; homologous_to
         (?/r1 owl/someValuesFrom ?/gene2)

         ;;still need to add a sufficient definition?
         (?/gene1 rdfs/subClassOf ?/r1))

  :body (
         (?/fv0 kiao/hasTemplate  iaohomologene/HomoloGeneDataFileData_homologeneGroupIDDataField1)
         (?/record1 obo/BFO_0000051 ?/fv0) ; has_part
         (?/record1 obo/BFO_0000051 ?/fv1) ; has_part
         (?/fv1 kiao/hasTemplate iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1)
         (?/fv1 obo/IAO_0000219 ?/gene1ice) ; denotes

         (?/record2 obo/BFO_0000051 ?/fv0) ; has_part
         (?/record2 obo/BFO_0000051 ?/fv2) ; has_part
         (?/fv2 kiao/hasTemplate iaohomologene/HomoloGeneDataFileData_entrezGeneIDDataField1)
         (?/fv2 obo/IAO_0000219 ?/gene2ice)

         (?/gene1ice obo/IAO_0000219 ?/gene1) ; denotes
         (?/gene2ice obo/IAO_0000219 ?/gene2)) ; denotes
  
  :reify (?/r1)
}
