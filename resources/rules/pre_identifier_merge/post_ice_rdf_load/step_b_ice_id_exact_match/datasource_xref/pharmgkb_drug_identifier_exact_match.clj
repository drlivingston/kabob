   ;; ------------------------------------------------------
;; --------- PharmGKB Drug Identifier Exact Match -------
;; ------------------------------------------------------
`{:name "pharmgkb-drug-identifier-exact-match"
  :description "This rule creates an exact match mapping between pahrmgkb drugs and other drugs"
  :head ((?/pharmgkb_drug_identifier skos/exactMatch ?/xref_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000821) ; CCP:PharmGKB_drug_record
           (?/record obo/BFO_0000051 ?/accession_identifier_field_value)
           (?/accession_identifier_field_value rdf/type ccp/IAO_EXT_0001010) ; CCP:PharmGKB_drug_record_accession_identifier_field value
           (?/accession_identifier_field_value rdf/type ?/pharmgkb_drug_identifier)
           (?/pharmgkb_drug_identifier rdfs/subClassOf ccp/IAO_EXT_0001429) ; CCP:PharmGKB_identifier
           (?/record obo/BFO_0000051 ?/cross_references_field_value)
           (?/unique_identifier_field_value rdf/type ccp/IAO_EXT_0001016) ; CCP:PharmGKB_drug_record_cross_references_field value
           (?/unique_identifier_field_value rdf/type ?/xref_identifier)
           (?/xref_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
  }