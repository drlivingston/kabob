;; NEED A COUNT QUERY TO TEST FOR PRESENCE OF ANY OTHER GENE TYPE NAME!!!


;; Assigning a sequence type to KABOB_DNA entities denoted by Entrez Gene 
;; identifiers based on the gene type field in the Entrez Gene gene_info file 
;; records
;;
;; Gene types and their definitions obtained from: 
;;     http://www.ncbi.nlm.nih.gov/books/NBK3841/#EntrezGene.Properties 
;; Possible gene types include:
;; miscRNA - Names for the types of molecules encoded by genes follow the 
;;           conventions the collaborating sequence databases 
;;           (DDBJ/EMBL/GenBank); thus miscrna (misc_rna, miscellaneous RNA) is 
;;           assigned to any gene that encodes an RNA product not included in 
;;           the other specifics.
;; ncRNA (non-coding RNA)
;; other - applied to loci of known type, but a specific category has not yet 
;;         been applied in the Entrezgene data model (e.g., named fragile sites)
;; protein-coding
;; pseudo (pseudogene)
;; rRNA (ribosomal RNA)
;; scRNA (small cytoplasmic RNA)
;; snRNA (small nuclear RNA)
;; snoRNA (small nucleolar RNA)
;; tRNA (transfer RNA)
;; unknown - applied to probable genes for which the type is still under review.
;;           This category is frequently used when the defining sequence has 
;;           uncertain coding propensity.


;;protein-coding maps to SO:0001217 (so:protein_coding_gene)
`{:name "i2b10-eg-proteincoding-gene-type-assertions"
  :head ((?/egKabobDna rdfs/subClassOf obo/SO_0001217))
  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
                      iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "protein-coding")
         (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; ;; ncRNA maps to SO:0000655 (so:ncRNA)
;; `{:name "i2b11-eg-ncRNA-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000655))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "ncRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; pseudo maps to SO:0000336 (so:pseudogene)
`{:name "i2b12-eg-pseudo-gene-type-assertion"
  :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000336))
  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
                      iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "pseudo")
         (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; rRNA maps to SO:0000252 (so:rRNA)
;; `{:name "i2b13-eg-rrna-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000252))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "rRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; scRNA maps to SO:0000013 (so:scRNA)
;; `{:name "i2b14-eg-scrna-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000013))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "scRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; ;; snRNA maps to SO:0000274 (so:snRNA)
;; `{:name "i2b15-eg-snrna-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000274))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "snRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; ;; snoRNA maps to SO:0000275 (so:snoRNA)
;; `{:name "i2b16-eg-snoRNA-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000275))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "snoRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; ;; tRNA maps to SO:0000253 (so:tRNA)
;; `{:name "i2b17-eg-trna-gene-type-assertion"
;;   :head ((?/egKabobDna rdfs/subClassOf obo/S0_0000253))
;;   :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                        iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                        iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "tRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;;;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;; BELOW STILL NEED CLASSIFICATION
;;;; ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;


;;;;;; unknown maps to kso:DNA_Sequence 
;;`{:name "i2b18-eg-id-unknown-gene-type-assertion"
;; :head ((?/egKabobDna rdfs/subClassOf obo/))
;; :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;         ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "unknown")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}
;;
;;;; other maps to kso:DNA_Sequence
;;`{:name "i2b19-eg-id-other-gene-type-assertion"
;;  :head ((?/egKabobDna rdfs/subClassOf obo/))
;;  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "other")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}

;; miscRNA maps to SO: (so:)
;;`{:name "i2b20-eg-id-miscrna-gene-type-assertion"
;;  :head ((?/egKabobDna rdfs/subClassOf obo/))
;;  :body (;;(_/record kiao/hasTemplate iaoeg/EntrezGeneInfoFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
;;                       iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 "miscRNA")
;;          (_/egIce obo/IAO_0000219 ?/egKabobDna))}





       
