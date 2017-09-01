;; 06/25/17 -- when updating this rule need to ensure formatting changes are added (1 file per rule; heading; :description; and labeing classes and properties)

;; Connects KABOB_DNA entities with Entrez Gene IDs to KABOB_DNA with
;; genomic RefSeq identifiers via the ro:proper_part_of predicate.
;; The KABOB_DNA entities with EG identifiers are subclassed under
;; kso:DNA_subsequence and are given the two data properties:
;;                   kso:has_start_position and kso:has_end_position.
;; The KABOB_DNA enities with RefSeq Genomic identifiers
;;   are subclassed under kso:DNA_sequence
;;
;; Records that don't have start and end positions don't seem to have
;; a genomic refseq associated with them.
;; `{:name "i2b0-kabobdna-partof-genomic-refseq-assertions"
;;   :head ((?/subSeqInstance ro/proper_part_of ?/refseqKabobDna)
;;           (?/subSeqInstance kso/has_start_position ?/startPosition)
;;           (?/subSeqInstance kso/has_end_position ?/endPosition)
;;           (?/subSeqInstance rdfs/subClassOf ?/egKabobDna)
;;           (?/egKabobDna rdfs/subClassOf kso/DNA_subsequence) ;; will result in duplicate triples
;;           (?/refseqKabobDna rdfs/subClassOf kso/DNA_sequence)) ;; will result in duplicate triples
;;   :body
;;   ((_/record kiao/hasTemplate iaoeg/egEntrezGene2RefseqFileDataSchema1)
;;    ~@(kabob/rtv _/record
;;        iaoeg/eggeneIDDataField1 _/egIce
;;        iaoeg/eggenomic_nucleotide_accession_dot_versionDataField1 _/refseqIce
;;        iaoeg/egstart_position_on_the_genomic_accessionDataField1 ?/startPosition
;;        iaoeg/egend_position_on_the_genomic_accessionDataField1   ?/endPosition)
;;    (_/refseqIce obo/IAO_0000219 ?/refseqKabobDna)
;;    (_/egIce     obo/IAO_0000219 ?/egKabobDna))
;;   :reify ([?/subSeqInstance
;;            {:ln (:md5 ?/refseqKabobDna ?/startPosition ?/endPosition)
;;             :ns "kabob" :prefix "DNASubSeq_" :suffix ""}])}


;;:body ((_/record kiao/hasTemplate iaoeg/egEntrezGene2RefseqFileDataSchema1)
;;         (_/f ro/part_of _/record)
;;         (_/f kiao/hasTemplate iaoeg/eggeneIDDataField1)
;;         (_/f obo/IAO_0000219 _/egIce)
;;         (_/egIce obo/denotes ?/egKabobDna)
;;         (_/f2 ro/part_of _/record)
;;         (_/f2 kiao/hasTemplate iaoeg/eggenomic_nucleotide_accession_dot_versionDataField1)
;;         (_/f2 obo/IAO_0000219 _/refseqIce)
;;         (_/refseqIce obo/denotes ?/refseqKabobDna))}
;;         (_/f3 ro/part_of _/record)
;;         (_/f3 kiao/hasTemplate iaoeg/egstart_position_on_the_genomic_accessionDataField1)
;;         (_/f3 obo/IAO_0000219 ?/startPosition)
;;         (_/f4 ro/part_of _/record)
;;         (_/f4 kiao/hasTemplate iaoeg/egend_position_on_the_genomic_accessionDataField1)
;;        (_/f4 obo/IAO_0000219 ?/endPosition))}


;; Adds obo/pr#has_gene_template links between KABOB_RNA/mRNA and KABOB_DNA 
;; entities based on the gene2refseq data
;;`{:name "i2b1-kabobRna-has_gene_template-kabobDna-basedon-gene2refseq-assertions"
;;  :head ((?/refseqKabobRna obo/pr#has_gene_template ?/egKabobDna)) ; PR:has_gene_template
;;  :body ((_/record kiao/hasTemplate iaoeg/egEntrezGene2RefseqFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;              iaoeg/eggeneIDDataField1 _/egIce
;;              iaoeg/egRNA_nucleotide_accession_dot_versionDataField1 _/refseqRnaIce)
;;          (_/refseqRnaIce obo/denotes ?/refseqKabobRna)
;;          (_/egIce obo/denotes ?/egKabobDna))}
   
;; Adds obo/pr#has_gene_template links between KABOB_PROTEIN and KABOB_mRNA 
;; entites based on the gene2refseq data
;;`{:name "i2b2-kabobProtein-has_gene_template-kabobMrna-basedon-gene2refseq-assertions"
;; :head ((?/refseqKabobProtein obo/pr#has_gene_template ?/refseqKabobRna)) ; PR:has_gene_template
;; :body ((_/record kiao/hasTemplate iaoeg/egEntrezGene2RefseqFileDataSchema1)
;;         ~@(kabob/rtv _/record
;;                      iaoeg/egRNA_nucleotide_accession_dot_versionDataField1 _/refseqRnaIce
;;                      iaoeg/egprotein_accession_dot_versionDataField1 _/refseqProteinIce)
;;         (_/refseqRnaIce obo/denotes ?/refseqKabobRna)
;;         (_/refseqProteinIce obo/denotes ?/refseqKabobProtein))}
        
;; Adds obo/pr#has_gene_template links between KABOB_PROTEIN and KABOB_DNA if 
;; there is a KABOB_mRNA entity that connects them based on the gene2refseq data
;;`{:name "i2b3-kabobProtein-has_gene_template-kabobDna-basedon-gene2refseq-assertions"
;;  :head ((?/refseqKabobProtein obo/pr#has_gene_template ?/egKabobDna)) ; PR:has_gene_template
;;  :body ((_/record kiao/hasTemplate iaoeg/egEntrezGene2RefseqFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaoeg/eggeneIDDataField1 _/egIce
;;                       iaoeg/egRNA_nucleotide_accession_dot_versionDataField1 _/refseqRnaIce
;;                       iaoeg/egprotein_accession_dot_versionDataField1 _/refseqProteinIce)
;;          (_/egIce obo/denotes ?/egKabobDna)
;;          (_/refseqProteinIce obo/denotes ?/refseqKabobProtein))}

;; Adds obo/pr#has_gene_template links between KABOB_PROTEIN and KABOB_DNA 
;; based on the UniProt ID Mapping data. No check for an intervening mRNA is made.
;;`{:name "i2b4-kabobProtein-has_gene_template-kabobDna-basedon-uniprot-id-mapping-assertions"
;;  :head ((?/kabobProtein obo/pr#has_gene_template ?/kabobDna)) ; PR:has_gene_template
;;  :body ((_/record kiao/hasTemplate iaouniprot/uniprotUniProtIDMappingFileDataSchema1)
;;          ~@(kabob/rtv _/record
;;                       iaouniprot/uniprotuniProtAccessionIDDataField1 _/uniprotIce
;;                       iaouniprot/uniprotentrezGeneIDsDataField1 _/egIce)
;;          (_/egIce obo/denotes ?/kabobDna)
;;          (_/uniprotIce obo/denotes ?/kabobProtein))}



;; NEED RULE RELATING REFSEQ AND UNIPROT PROTEINS WITH SIMILAR BUT NOT EXACT TAXONOMY IDs




       