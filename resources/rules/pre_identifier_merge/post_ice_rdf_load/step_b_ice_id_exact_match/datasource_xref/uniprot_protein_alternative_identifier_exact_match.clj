;; --------------------------------------------------------------------------------
;; --------- UniProt Protein Alternative Identifier Exact Match --------
;; --------------------------------------------------------------------------------
`{:name "uniprot-protein-alternative-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and alternative identifiers"
  :head ((?/primary_uniprot_identifier skos/exactMatch ?/secondaryUniprotIce))
  :body ((?/record rdf/type ccp/IAO_EXT_0000977) ; CCP:uniprot_protein_record
          (?/record obo/BFO_0000051 ?/primary_accession_field_value)
          (?/primary_accession_field_value rdf/type ccp/IAO_EXT_0000240) ; CCP:uniprot_protein_record_primary_accession_field_value
          (?/primary_accession_field_value rdf/type ?/primary_uniprot_identifier)
          (?/primary_uniprot_identifier rdfs/subClassOf ccp/IAO_EXT_0000184) ; CCP:uniprot_identifier
          (?/record obo/BFO_0000051 ?/accession_field_value)
          (?/accession_field_value rdf/type ccp/IAO_EXT_0000931) ; CCP:uniprot_protein_record_accession_field_value
          (?/accession_field_value rdf/type ?/secondary_uniprot_identifier)
          (?/secondary_uniprot_identifier rdfs/subClassOf ccp/IAO_EXT_0000184)) ; CCP:uniprot_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }