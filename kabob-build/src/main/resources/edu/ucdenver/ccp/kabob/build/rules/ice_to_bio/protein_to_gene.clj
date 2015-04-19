;;;; Rules to map from protein identifiers to gene identifiers

`{:name "uniprot-protein-eg-gene-mapping"
  :head ((?/protein kso/hasIndirectTemplate ?/dna);)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/hasIndirectTemplate)
         (?/r1 owl/someValuesFrom ?/dna)

         ;;still need to add a sufficient definition?
         (?/protein rdfs/subClassOf ?/r1))

  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
         ;; ~@(kabob/rtv _/record1
         ;;              iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1 _/proteinice
         ;;              iaouniprot/UniProtIDMappingFileData_entrezGeneIDsDataField1 _/geneice)

         (?/fv0 kiao/hasTemplate  iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1)
         (?/fv0 obo/IAO_0000219 _/proteinice)

         (?/record1 obo/has_part ?/fv0)
         (?/record1 obo/has_part ?/fv1)
         (?/fv1 kiao/hasTemplate iaouniprot/UniProtIDMappingFileData_entrezGeneIDsDataField1)
         (?/fv1 obo/IAO_0000219 _/geneice)

         (_/proteinice obo/IAO_0000219 ?/protein)
         (_/geneice obo/IAO_0000219 ?/dna))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}


`{:name "rgd-gene-to-protein-mapping"
  :head ((?/protein kso/hasIndirectTemplate ?/dna);)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/hasIndirectTemplate)
         (?/r1 owl/someValuesFrom ?/dna)

         ;;still need to add a sufficient definition?
         (?/protein rdfs/subClassOf ?/r1))

  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
         ;; ~@(kabob/rtv _/record1
         ;;              iaorgd/RgdGeneFileRecord_uniprotIdsDataField1 _/proteinice
         ;;              iaorgd/RgdGeneFileRecord_geneIdDataField1 _/geneice)

         (?/fv0 kiao/hasTemplate  iaorgd/RgdGeneFileRecord_uniprotIdsDataField1)
         (?/fv0 obo/IAO_0000219 _/proteinice)

         (?/record1 obo/has_part ?/fv0)
         (?/record1 obo/has_part ?/fv1)
         (?/fv1 kiao/hasTemplate iaorgd/RgdGeneFileRecord_geneIdDataField1)
         (?/fv1 obo/IAO_0000219 _/geneice)

         (_/proteinice obo/IAO_0000219 ?/protein)
         (_/geneice obo/IAO_0000219 ?/dna))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}
