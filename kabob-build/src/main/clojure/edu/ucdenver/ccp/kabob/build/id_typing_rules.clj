;; All Entrez Gene identifiers are of type kiao/GeneIdentifier
{:name "eg-id-type-assertion"
 :head ((?ice rdf/type kiao/GeneIdentifier))
 :body ((_/record kiao/hasTemplate iaoeg/egEntrezGeneInfoFileDataSchema1)
            (_/f ro/part_of _/record)
            (_/f kiao/hasTemplate iaoeg/eggeneIDDataField1)
            (_/f iao/IAO_0000219 ?ice))}


;; All UniProt identifiers are of type kiao/ProteinIdentifier
{:name "uniprot-id-type-assertion"
 :head ((?ice rdf/type kiao/ProteinIdentifier))
 :body ((_/record kiao/hasTemplate iaouniprot/uniprotUniProtDatFileDataSchema1)
         (_/f ro/part_of _/record)
            (_/f kiao/hasTemplate iaouniprot/uniprotprimaryUniProtIDDataField1)
            (_/f iao/IAO_0000219 ?ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq genomic identifiers are of type kiao/DNASequenceIdentifier
{:name "refseq-genomic-id-type-assertion"
 :head ((?ice rdf/type kiao/DnaSequenceIdentifier))
 :body ((_/record kiao/hasTemplate iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         (_/f2 ro/part_of _/record)
         (_/f2 kiao/hasTemplate iaorefseq/refseqmolecularTypeDataField1)
         (_/f2 iao/IAO_0000219 "Genomic"@en)
         (_/f ro/part_of _/record)
         (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
         (_/f iao/IAO_0000219 ?ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq RNA identifiers are of type kiao/RNAIdentifier
{:name "refseq-rna-id-type-assertion"
 :head ((?ice rdf/type kiao/DnaSequenceIdentifier))
 :body ((_/record kiao/hasTemplate iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         (_/f2 ro/part_of _/record)
         (_/f2 kiao/hasTemplate iaorefseq/refseqmolecularTypeDataField1)
         (_/f2 iao/IAO_0000219 "RNA"@en)
         (_/f ro/part_of _/record)
         (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
         (_/f iao/IAO_0000219 ?ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq mRNA identifiers are of type kiao/mRNAIdentifier
{:name "refseq-mrna-id-type-assertion"
 :head ((?ice rdf/type kiao/DnaSequenceIdentifier))
 :body ((_/record kiao/hasTemplate iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         (_/f2 ro/part_of _/record)
         (_/f2 kiao/hasTemplate iaorefseq/refseqmolecularTypeDataField1)
         (_/f2 iao/IAO_0000219 "mRNA"@en)
         (_/f ro/part_of _/record)
         (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
         (_/f iao/IAO_0000219 ?ice))}

;; RefSeq Ids can represent Genomic, RNA, mRNA, or Protein sequences
;; RefSeq protein identifiers are of type kiao/ProteinIdentifier
{:name "refseq-protein-id-type-assertion"
 :head ((?ice rdf/type kiao/DnaSequenceIdentifier))
 :body ((_/record kiao/hasTemplate iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
         (_/f2 ro/part_of _/record)
         (_/f2 kiao/hasTemplate iaorefseq/refseqmolecularTypeDataField1)
         (_/f2 iao/IAO_0000219 "Protein"@en)
         (_/f ro/part_of _/record)
         (_/f kiao/hasTemplate iaorefseq/refseqrefseqIdDataField1)
         (_/f iao/IAO_0000219 ?ice))}

