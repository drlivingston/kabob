;; ---------------------------------------------------------------------------
;; --------- UniProt Protein RefSeq Identifier Exact Match --------
;; ---------------------------------------------------------------------------
`{:name "uniprot-protein-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and refseq identifiers"
  :head ((?/uniprot skos/exactMatch ?/refseq))
  :body ((?/record rdf/type ccp/IAO_EXT_0000235) ; CCP:uniprot_identifier_mapping_record
          (?/record obo/BFO_0000051 ?/uniprot_identifier_field_value)
          (?/uniprot_identifier_field_value rdf/type ccp/IAO_EXT_0000239) ; CCP:uniprot_identifier_mapping_record_uniprot_accession_identifier_field_value
          (?/uniprot_identifier_field_value rdf/type ?/uniprot_identifier)
          (?/uniprot_identifier rdfs/subClassOf ccp/IAO_EXT_0000184) ; CCP:uniprot_identifier
          (?/record obo/BFO_0000051 ?/uniprot_identifier_field_value)
          (?/uniprot_identifier_field_value rdf/type ccp/IAO_EXT_0000243) ; CCP:uniprot_identifier_mapping_record_uniprot_accession_identifier_field_value
          (?/uniprot_identifier_field_value rdf/type ?/uniprot_identifier)
          (?/uniprot_identifier rdfs/subClassOf ccp/IAO_EXT_0001638)) ; CCP:refseq_protein_identifier
  }