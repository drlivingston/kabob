
;; ;;in_taxon
;; obo/RO_0002162

;; canonical is in the records not the mapping file
;;  run this one anyway and look for mismatch??
;; `{:name "uniprot-in-taxon"
;;   :head ((?/protein obo/RO_0002162 ?/taxon)

;;          (?/r1 rdf/type owl/Restriction)
;;          (?/r1 owl/onProperty obo/RO_0002162)
;;          (?/r1 owl/someValuesFrom ?/taxon)

;;          (?/protein rdfs/subClassOf ?/r1))

         
;;   :body 
;;   (~@(kabob/rtv _/record
;;         iaouniprot/UniProtIDMappingFileData_taxonomyIDDataField1 _/taxonIce
;;         iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1
;;                                                                  _/uniprotIce)
;;    (_/uniprotIce obo/IAO_0000219 ?/protein)
;;    (_/taxonIce obo/IAO_0000219 ?/taxon))

;;   :reify (?/r1)
;; }

`{:name "uniprot-in-taxon"
  :head (
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0002162)
         (?/r1 owl/someValuesFrom ?/taxon)

         (?/protein rdfs/subClassOf ?/r1))
  :body 
  ((_/fv0 kiao/hasTemplate iaouniprot/UniProtFileRecord_primaryAccessionDataField1)
   (_/fv0 obo/IAO_0000219 _/uniprotIce)
   (_/record obo/has_part _/fv0)

   (_/record obo/has_part _/taxrecord)
   (_/taxrecord obo/has_part _/dbrecord)

   (_/dbrecord obo/has_part _/fv1)
   (_/fv1 kiao/hasTemplate iaouniprot/DbReference_idDataField1)
   (_/fv1 obo/IAO_0000219 _/taxonIce)

   (_/uniprotIce obo/IAO_0000219 ?/protein)
   (_/taxonIce obo/IAO_0000219 ?/taxon))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}

`{:name "uniprot-sparse-in-taxon"
  :head ((?/protein obo/RO_0002162 ?/taxon)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0002162)
         (?/r1 owl/someValuesFrom ?/taxon)

         (?/protein rdfs/subClassOf ?/r1))
  :body 
  ((_/fv0 kiao/hasTemplate iaouniprot/SparseUniProtFileRecord_primaryAccessionDataField1)
   (_/fv0 obo/IAO_0000219 _/uniprotIce)
   (_/record obo/has_part _/fv0)

   (_/record obo/has_part _/taxrecord)
   (_/taxrecord obo/has_part _/dbrecord)

   (_/dbrecord obo/has_part _/fv1)
   (_/fv1 kiao/hasTemplate iaouniprot/DbReference_idDataField1)
   (_/fv1 obo/IAO_0000219 _/taxonIce)

   (_/uniprotIce obo/IAO_0000219 ?/protein)
   (_/taxonIce obo/IAO_0000219 ?/taxon))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}



`{:name "eg-in-taxon"
  :head ((?/gene obo/RO_0002162 ?/taxon)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0002162)
         (?/r1 owl/someValuesFrom ?/taxon)

         (?/gene rdfs/subClassOf ?/r1))

         
  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
                      iaoeg/EntrezGeneInfoFileData_taxonIDDataField1 _/taxonIce)
         (_/egIce obo/IAO_0000219 ?/gene)
         (_/taxonIce obo/IAO_0000219 ?/taxon))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
}


;; This rule assigns taxons to bio-entities represented by RefSeq IDs (so genes, proteins, RNAs...)
`{:name "refseq-in-taxon"
  :head ((?/bioentity obo/RO_0002162 ?/taxon)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0002162)
         (?/r1 owl/someValuesFrom ?/taxon)

         (?/bioentity rdfs/subClassOf ?/r1))

         
  :body (;;(_/record kiao/hasTemplate iaorefseq/RefSeqReleaseCatalogFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaorefseq/RefSeqReleaseCatalogFileData_refseqIdDataField1 _/refseqIce
                      iaorefseq/RefSeqReleaseCatalogFileData_taxIdDataField1 _/taxonIce)
         (_/refseqIce obo/IAO_0000219 ?/bioentity)
         (_/taxonIce obo/IAO_0000219 ?/taxon))

  :reify ([?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])
}



;; `{:name "uniprot-in-taxon"
;;   :head ((?/protein obo/inTaxon ?/taxon)

;;          (?/r1 rdf/type owl/Restriction)
;;          (?/r1 owl/onProperty obo/inTaxon)
;;          (?/r1 owl/someValuesFrom ?/taxon)

;;          (?/protein rdfs/subClassOf ?/r1))

         
;;   :body 
;;   (~@(kabob/rtv _/record
;;         iaouniprot/UniProtIDMappingFileData_taxonomyIDDataField1 _/taxonIce
;;         iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1
;;                                                                  _/uniprotIce)
;;    (_/uniprotIce obo/IAO_0000219 ?/protein)
;;    (_/taxonIce obo/IAO_0000219 ?/taxon))

;;   :reify (?/r1)
;; }

;; `{:name "eg-in-taxon"
;;   :head ((?/gene obo/inTaxon ?/taxon)

;;          (?/r1 rdf/type owl/Restriction)
;;          (?/r1 owl/onProperty obo/inTaxon)
;;          (?/r1 owl/someValuesFrom ?/taxon)

;;          (?/gene rdfs/subClassOf ?/r1))

         
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_taxonIDDataField1 _/taxonIce)
;;          (_/egIce obo/IAO_0000219 ?/gene)
;;          (_/taxonIce obo/IAO_0000219 ?/taxon))

;;   :reify (?/r1)
;; }

