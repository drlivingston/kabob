;; -----------------------------------------------------
;; --------- DrugBank SKOS Exact Match Mapping ---------
;; -----------------------------------------------------
`{:name "drugbank-drug-exact-mapping-assertion"
  :description "This rule identifies drug concept exact matches for every drug record and types them as (IAO_EXT_0000404)"
  :head (
          ;; create an exact match to drugbank identifier
          (?/drug_ice_id skos/exactMatch ?/other_drug_ice_id))

  :body ((?/record rdf/type ccp/IAO_EXT_0000426) ; ccp:DrugBank_drug_record
         (?/record obo/BFO_0000051 ?/drug_field_value) ; BFO:has_part
         (?/drug_field_value rdf/type ccp/IAO_EXT_0000360) ; ccp:Drugbank_drug_record__drugbank_identifier_field_value 
         (?/drug_field_value rdf/type ?/drug_ice_id)

         ;; retrieve the external exact match identifier
         (?/record obo/BFO_0000051 ?/other_drug_field_value) ; BFO:has_part
         (?/other_drug_field_value rdf/type ccp/IAO_EXT_0000404) ; ccp:Drugbank_drug_record__data_source_identifier_field_value
         (?/other_drug_field_value obo/IAO_0000219 ?/other_drug_ice_id)) ; IAO:denotes

  ;; In this case no :reify entry is required, since all of the data required to form the new triples already exist

  :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
  }