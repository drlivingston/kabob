;; -------------------------------------------------------------
;; --------- PharmGKB Gene Entrez Identifier Exact Match -------
;; -------------------------------------------------------------
`{:name "pharmgkb-gene-entrez-identifier-exact-match"
  :description "This rule asserts exact matches between pharmgkb genes and entrez genes"
  :head ((?/pharmgkb_gene_identifier skos/exactMatch ?/ncbi_gene_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000822) ; CCP:PharmGKB_gene_record
          (?/record obo/BFO_0000051 ?/accession_identifier_field_value)
          (?/accession_identifier_field_value rdf/type ccp/IAO_EXT_0001022) ; CCP:PharmGKB_gene_record_accession_identifier_field value
          (?/accession_identifier_field_value rdf/type ?/pharmgkb_gene_identifier)
          (?/pharmgkb_gene_identifier rdfs/subClassOf ccp/IAO_EXT_0001429) ; CCP:PharmGKB_identifier
          (?/record obo/BFO_0000051 ?/entrez_gene_identifier_field_value)
          (?/entrez_gene_identifier_field_value rdf/type ccp/IAO_EXT_0001023) ; CCP:PharmGKB_gene_record_entrez_gene_field value
          (?/entrez_gene_identifier_field_value rdf/type ?/ncbi_gene_identifier)
          (?/ncbi_gene_identifier rdfs/subClassOf ccp/IAO_EXT_0000084)) ; CCP:ncbi_gene_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }