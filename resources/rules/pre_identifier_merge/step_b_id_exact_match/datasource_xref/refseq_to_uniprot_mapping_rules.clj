;; The UniProt idmapping_selected.tab file contains a variety of identifier mappings. Here we link
;; mappings among protein identifiers using the skos:exactMatch predicate


;; uniport to refseq
`{:name "refseq-uniprot-exact-mapping-assertion"
 :head ((?/uniprot skos/exactMatch ?/refseq))
  :body
  (~@(kabob/rtv _/record
      iaoeg/EntrezGeneRefSeqUniprotKbCollabFileData_uniprotIdDataField1
                                                              ?/uniprot
      iaoeg/EntrezGeneRefSeqUniprotKbCollabFileData_refSeqProteinIdDataField1
                                                              ?/refseq)
   (?/refseq kiao/denotesSubClassOf obo/CHEBI_36080))} ;protein
             ;;rdf/type kiao/ProteinIdentifier))} ; is this triple redundant?





;;TODO figure out what this was?

;; UniProt_ID skos:exactMatch RefSeq_Protein_ID
;; This only holds true when the NCBI taxonomy IDs match for the two entities,
;;   so there will be further links to add via subclass_of
;; `{:name "uniprot-exact-refseq-id-mapping-assertion"
;;   :head ((?/uniprotIce skos/exactMatch ?/refseqProteinIce))
;;   :body
;;   ((_/record kiao/hasTemplate
;;              iaoeg/egEntrezGeneRefSeqUniprotKbCollabFileDataSchema1)
;;    ~@(kabob/rtv _/record
;;                 iaoeg/egrefSeqProteinIdDataField1  ?/refseqProteinIce
;;                 iaoeg/eguniprotIdDataField1        ?/uniprotIce)
   
;;    (_/refseqRecord kiao/hasTemplate
;;                    iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)
;;    ~@(kabob/rtv _/refseqRecord
;;                 iaorefseq/refseqrefseqIdDataField1 ?/refseqProteinIce
;;                 iaorefseq/refseqtaxIdDataField1    _/taxId)

;;    (_/uniprotRecord kiao/hasTemplate
;;                     iaouniprot/uniprotUniProtDatFileDataSchema1)
;;    ~@(kabob/rtv _/uniprotRecord
;;                 iaouniprot/uniprotprimaryUniProtIDDataField1 ?/uniprotIce
;;                 iaouniprot/uniprotncbiTaxonomyIDDataField1   _/taxId))}

