;; -----------------------------------------------------------------
;; --------- RefSeq Protein Uniprot Identifier Exact Match ---------
;; -----------------------------------------------------------------
`{:name "refseq-protein-uniprot-identifier-exact-match"
  :description "This rule links mappings among protein identifiers using the skos:exactMatch predicate"
  :head ((?/refseq_identifier skos/exactMatch ?/uniprot_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000692) ; CCP:ncbi_gene_refseq_uniprotkb_collaboration_record
          (?/record obo/BFO_0000051 ?/refseq_protein_identifier_field_value)
          (?/refseq_protein_identifier_field_value rdf/type ccp/IAO_EXT_0000927) ; CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_refseq_protein_identifier_field_value
          (?/refseq_protein_identifier_field_value rdf/type ?/refseq_identifier)
          (?/refseq_identifier rdfs/subClassOf ccp/IAO_EXT_0001638) ; CCP:refseq_protein_identifier
          (?/record obo/BFO_0000051 ?/uniprot_identifier_field_value)
          (?/uniprot_identifier_field_value rdf/type ccp/IAO_EXT_0000928) ; CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_uniprot_identifier_field_value
          (?/uniprot_identifier_field_value rdf/type ?/uniprot_identifier)
          (?/uniprot_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000184)) ; CCP:uniprot_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }





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

