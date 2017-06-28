;; --------------------------------------------------------
;; --------- IRefWeb Protein Identifier Exact Match -------
;; --------------------------------------------------------
`{:name "irefweb-irogid-to-other-identifier-exact-match"
  :description "This rule asserts an exact match relation between irefweb proteins and other proteins"
  :head ((?/irefweb_irogid_identifier skos/exactMatch ?/other_gene_product_identifier))

  :body ((?/record rdf/type ccp/IAO_EXT_0000065) ; CCP:IRefWeb_interactor_record
          (?/record obo/BFO_0000051 ?/irogid_identifier_field_value)
          (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000772) ; CCP:IrefWeb_interactor_record_integer_rog_identifier_field value
          (?/gene_symbol_field_value rdf/type ?/irefweb_irogid_identifier)
          (?/irefweb_irogid_identifier rdfs/subClassOf ccp/IAO_EXT_0001377) ; CCP:IrefWeb_IROGID_identifier
          (?/record obo/BFO_0000051 ?/unique_identifier_field_value)
          (?/unique_identifier_field_value rdf/type ccp/IAO_EXT_0000755) ; CCP:IrefWeb_interactor_record_unique_identifier_field value
          (?/unique_identifier_field_value rdf/type ?/other_gene_product_identifier)
          (?/other_gene_product_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000183)) ; ccp:gene_product_identifier

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
}

;;TODO replicate the above for Complexes
