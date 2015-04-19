;; This rule set applies a sequence type to RefSeq identifiers based on
;; the molecular type field in the RefSeq release catalog file records
;;
;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; Gene types and their definitions obtained from: 
;;     http://www.ncbi.nlm.nih.gov/RefSeq/key.html 
;; Possible gene types include:
;; AC_ (Genomic) - Alternate complete genomic molecule. This prefix is used for 
;;                 records that are provided to reflect an alternate assembly or
;;                 annotation. Primarily used for viral, prokaryotic records. 
;; AP_ (Protein) - Protein products; alternate protein record. This prefix is 
;;                 used for records that are provided to reflect an alternate 
;;                 assembly or annotation. The AP_ prefix was originally 
;;                 designated for bacterial proteins but this usage was changed. 
;; NC_ (Genomic) - Complete genomic molecules including genomes, chromosomes, 
;;                 organelles, plasmids. 
;; NG_ (Genomic) - Incomplete genomic region; supplied to support the NCBI 
;;                 genome annotation pipeline. Represents either non-transcribed
;;                 pseudogenes, or larger regions representing a gene cluster 
;;                 that is difficult to annotate via automatic methods. 
;; NM_ (mRNA) -    Transcript products; mature messenger RNA (mRNA) transcripts.
;; NP_ (Protein) - Protein products; primarily full-length precursor products 
;;                 but may include some partial proteins and mature peptide 
;;                 products. 
;; NR_ (RNA) -     Non-coding transcripts including structural RNAs, transcribed
;;                 pseudogenes, and others. 
;; NT_ (Genomic) - Intermediate genomic assemblies of BAC and/or Whole Genome 
;;                 Shotgun sequence data. 
;; NW_ (Genomic) - Intermediate genomic assemblies of BAC or Whole Genome 
;;                 Shotgun sequence data. 
;; NZ_ (Genomic) - A collection of whole genome shotgun sequence data for a 
;;                 project. Accessions are not tracked between releases. The 
;;                 first four characters following the underscore (e.g. 'ABCD') 
;;                 identifies a genome project. 
;; XM_ (mRNA) -    Transcript products; model mRNA provided by a genome 
;;                 annotation process; sequence corresponds to the genomic 
;;                 contig. 
;; XP_ (Protein) - Protein products; model proteins provided by a genome 
;;                 annotation process; sequence corresponds to the genomic 
;;                 contig. 
;; XR_ (RNA) -     Transcript products; model non-coding transcripts provided by
;;                 a genome annotation process; sequence corresponds to the 
;;                 genomic contig. 
;; YP_ (Protein) - Protein products; no corresponding transcript record 
;;                 provided. Primarily used for bacterial, viral, and 
;;                 mitochondrial records. 
;; ZP_ (Protein) - Protein products; annotated on NZ_ accessions (often via 
;;                 computational methods). 
;; NS_ (Genomic) - Genomic records that represent an assembly which does not 
;;                 reflect the structure of a real biological molecule. The 
;;                 assembly may represent an unordered assembly of unplaced 
;;                 scaffolds, or it may represent an assembly of DNA sequences 
;;                 generated from a biological sample that may not represent a 
;;                 single organism.

;; RefSeq genomic identifiers map to kso/???
`{:name "refseq-genomic-entity-id-type-assertion"
  :head ((?/bio-entity rdf/type kso/))
  :body ((_/record kiao/hasTemplate
                   iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaorefseq/refseqmolecularTypeDataField1 ["Genomic" "en"]
                      iaorefseq/refseqrefseqIdDataField1 _/ice)
         (_/ice iao/IAO_0000219 ?/bio-entity))}

;; RefSeq RNA identifiers map to kso/???
`{:name "refseq-rna-entity-id-type-assertion"
 :head ((?/bio-entity rdf/type kso/))
  :body ((_/record kiao/hasTemplate
                   iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaorefseq/refseqmolecularTypeDataField1 ["RNA" "en"]
                      iaorefseq/refseqrefseqIdDataField1 _/ice)
         (_/ice iao/IAO_0000219 ?/bio-entity))}

;; RefSeq mRNA identifiers map to kso/???
`{:name "refseq-mrna-entity-id-type-assertion"
 :head ((?/bio-entity rdf/type kso/))
  :body ((_/record kiao/hasTemplate
                   iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaorefseq/refseqmolecularTypeDataField1 ["mRNA" "en"]
                      iaorefseq/refseqrefseqIdDataField1 _/ice)
         (_/ice iao/IAO_0000219 ?/bio-entity))}

;; RefSeq protein identifiers map to kso/???
`{:name "refseq-protein-entity-id-type-assertion"
 :head ((?/bio-entity rdf/type kso/))
  :body ((_/record kiao/hasTemplate
                   iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         ~@(kabob/rtv _/record
                      iaorefseq/refseqmolecularTypeDataField1 ["Protein" "en"]
                      iaorefseq/refseqrefseqIdDataField1 _/ice)
         (_/ice iao/IAO_0000219 ?/bio-entity))}


