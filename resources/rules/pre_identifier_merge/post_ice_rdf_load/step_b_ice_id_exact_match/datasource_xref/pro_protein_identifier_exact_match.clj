;; -------------------------------------------------------------------
;; --------- Protein Ontology Protein Identifier Exact Match ---------
;; -------------------------------------------------------------------
`{:name "pro-protein-identifier-exact-match"
  :description "This rule asserts an exact match between proteins and other protein identifier"
  :head ((?/pr_identifier skos/exactMatch ?/other_protein_identifier))
  :body ((?/record rdf/type ccp/IAO_EXT_0000115) ; CCP:protein_ontology_identifier_mapping_record
          (?/record obo/BFO_0000051 ?/mapping_type_field_value)
          (?/mapping_type_field_value rdf/type ccp/IAO_EXT_0000813) ; CCP:pro_identifier_mapping_record_mapping_type_field_value
          (?/mapping_type_field_value rdfs/label "exact")
          (?/record obo/BFO_0000051 ?/pro_identifier_field_value)
          (?/pro_identifier_field_value rdf/type ccp/IAO_EXT_0000817) ; CCP:pro_identifier_mapping_record_pro_identifier_field value
          (?/pro_identifier_field_value rdf/type ?/pr_identifier)
          (?/pr_identifier rdfs/subClassOf ccp/IAO_EXT_0000112) ; CCP:protein_ontology_concept_identifier
          (?/record obo/BFO_0000051 ?/target_record_identifier_field_value)
          (?/target_record_identifier_field_value rdf/type ccp/IAO_EXT_0001023) ; CCP:PharmGKB_gene_record_entrez_gene_field value
          (?/target_record_identifier_field_value rdf/type ?/other_protein_identifier)
          (?/other_protein_identifier [rdfs/subClassOf *] ccp/IAO_EXT_0000183)) ; CCP:gene_product_identifier
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed:" "franz:yes"]]}
 }