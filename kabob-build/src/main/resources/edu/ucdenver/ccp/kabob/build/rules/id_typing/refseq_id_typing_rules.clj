;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq genomic identifiers are of type kiao/DNASequenceIdentifier
;; `{:name "refseq-genomic-id-type-assertion"
;;  :head ((?/ice rdf/type kiao/DNAIdentifier))
;;  :body ((_/record kiao/hasTemplate
;;                   iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
;;          (_/f2 ro/part_of _/record)
;;          (_/f2 kiao/hasTemplate iaorefseq/refseqmoleculeTypeDataField1)
;;          (_/f2 iao/IAO_0000219 "Genomic")
;;          (_/f ro/part_of _/record)
;;          (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
;;          (_/f iao/IAO_0000219 ?/ice))}

;; old fields
;; `{:name "refseq-genomic-id-type-assertion"
;;   :head ((?/ice rdf/type kiao/DNAIdentifier))
;;   :body ((_/record kiao/hasTemplate
;;                    iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaorefseq/refseqmoleculeTypeDataField1 "Genomic"
;;                       iaorefseq/refseqrefseqIdDataField1 ?/ice))}

`{:name "refseq-genomic-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body
  (~@(kabob/rtv _/record
        iaorefseq/RefSeqReleaseCatalogFileData_moleculeTypeDataField1 "Genomic"
        iaorefseq/RefSeqReleaseCatalogFileData_refseqIdDataField1 ?/ice))}

;; ~@(edu.ucdenver.ccp.kabob.build.ice-helper/rftv
;;    '_/record '_fv2 'iaorefseq/refseqmoleculeTypeDataField1 "Genomic")
         ;; (_/f2 ro/part_of _/record)
         ;; (_/f2 kiao/hasTemplate iaorefseq/refseqmoleculeTypeDataField1)
         ;; (_/f2 iao/IAO_0000219 "Genomic")
         ;; (_/f ro/part_of _/record)
         ;; (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
         ;; (_/f iao/IAO_0000219 ?/ice))}



;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq RNA identifiers are of type kiao/RNAIdentifier
`{:name "refseq-rna-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/SO_0000356)) ;rna
  :body
  (~@(kabob/rtv _/record
        iaorefseq/RefSeqReleaseCatalogFileData_moleculeTypeDataField1 "RNA"
        iaorefseq/RefSeqReleaseCatalogFileData_refseqIdDataField1 ?/ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq mRNA identifiers are of type kiao/mRNAIdentifier
`{:name "refseq-mrna-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/SO_0000234)) ;mrna
  :body
  (~@(kabob/rtv _/record
        iaorefseq/RefSeqReleaseCatalogFileData_moleculeTypeDataField1 "mRNA"
        iaorefseq/RefSeqReleaseCatalogFileData_refseqIdDataField1 ?/ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq protein identifiers are of type kiao/ProteinIdentifier
`{:name "refseq-protein-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
  :body
  (~@(kabob/rtv _/record
        iaorefseq/RefSeqReleaseCatalogFileData_moleculeTypeDataField1 "Protein"
        iaorefseq/RefSeqReleaseCatalogFileData_refseqIdDataField1 ?/ice))}

