;; -------------------------------------------------------
;; --------- Uniprot Protein Entrez Gene Mapping ---------
;; -------------------------------------------------------
`{:name "uniprot-protein-entrez-gene-mapping"
   :description "This rule maps uniprot proteins to entrez genes"
   :head (
          ;; map proteins to genes
          (?/protein kso/has_indirect_template ?/gene)
         
          ;; create has indirect template restriction
          (?/indirect_template_restriction rdf/type owl/Restriction)
          (?/indirect_template_restriction owl/onProperty kso/has_indirect_template)
          (?/indirect_template_restriction owl/someValuesFrom ?/gene)
          (?/protein rdfs/subClassOf ?/indirect_template_restriction))

    :body ((?/record rdf/type ccp/IAO_EXT_0000235) ; ccp:UniProt_identifier_mapping_record
           (?/record obo/BFO_0000051 ?/field_1_field_value) ; BFO:has_part
           (?/field_1_field_value rdf/type ccp/IAO_EXT_0000239) ; ccp:UniProt_identifier_mapping_record__UniProt_accession_identifier_field_value
           (?/field_1_field_value rdf/type ?/field_1_ice_id)
           (?/field_1_ice_id obo/IAO_0000219 ?/protein) ; IAO:denotes

           ;; retrieve the external exact match identifier
           (?/record obo/BFO_0000051 ?/field_2_field_value) ; BFO:has_part
           (?/field_2_field_value rdf/type ccp/IAO_EXT_0000242) ; ccp:UniProt_identifier_mapping_record__Entrez_gene_identifier_field_value
           (?/field_2_field_value rdf/type ?/field_2_ice_id)
           (?/field_2_ice_id obo/IAO_0000219 ?/gene)) ; IAO:denotes

    :reify ([?/indirect_template_restriction1 {:ln (:restriction)
                   :ns "ccp" :prefix "R_"}])
   
    :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}