;; --------------------------------------------------------
;; --------- DrugBank Drug Identifier Exact Match ---------
;; --------------------------------------------------------
`{:name "drugbank-drug-identifier-exact-match"
  :description "This rule asserts an exact match between drugbank drugs and other drug identifiers"
  :head ((?/drugbank_identifier skos/exactMath ?/other_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000426) ; CCP:Drugbank_drug_record
          (?/record obo/BFO_0000051 ?/drugbank_id_field_value)
          (?/drugbank_id_field_value rdf/type ccp/IAO_EXT_0000360) ; CCP:Drugbank_drug_record_drugbank_identifier_field_value
          (?/drugbank_id_field_value rdf/type ?/drugbank_identifier)
          ; when the ccp identifier hierarchy is more mature, add more specific constraints such that the identifiers must be of similar types
          (?/drugbank_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000342) ; CCP:identifier_of_a_biological_entity
          (?/record obo/BFO_0000051 ?/external_identifiers_field_value)
          (?/external_identifiers_field_value rdf/type ccp/IAO_EXT_0000405) ; CCP:Drugbank_drug_record_external_link_field_value
          (?/external_identifiers_field_value rdf/type ?/other_identifier)
          ; when the ccp identifier hierarchy is more mature, add more specific constraints such that the identifiers must be of similar types
          (?/other_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
    :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                               ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
}