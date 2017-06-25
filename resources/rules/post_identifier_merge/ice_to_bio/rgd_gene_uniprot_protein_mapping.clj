;; ----------------------------------------------------
;; --------- RGD Gene Uniprot Protein Mapping ---------
;; ----------------------------------------------------
`{:name "rgd-gene-uniprot-protein-mapping"
   :description "This rule maps rgd genes to uniprot proteins"
   :head (
          ;; create has indirect template restriction
          (?/indirect_template_restriction rdf/type owl/Restriction)
          (?/indirect_template_restriction owl/onProperty obo/pr#has_gene_template) ; PR:has_gene_template
          (?/indirect_template_restriction owl/someValuesFrom ?/gene)
          (?/protein rdfs/subClassOf ?/indirect_template_restriction))

    :body ((?/record rdf/type ccp/IAO_EXT_0000XXX) ; ccp:RGD_gene_record
           (?/record obo/BFO_0000051 ?/field_1_field_value) ; BFO:has_part
           (?/field_1_field_value rdf/type ccp/IAO_EXT_0000XXX) ; ccp:RGD_gene_record__UniProt_identifier_field_value
           (?/field_1_field_value rdf/type ?/field_1_ice_id)
           (?/field_1_ice_id obo/IAO_0000219 ?/protein) ; IAO:denotes

           ;; retrieve the external exact match identifier
           (?/record obo/BFO_0000051 ?/field_2_field_value) ; BFO:has_part
           (?/field_2_field_value rdf/type ccp/IAO_EXT_0000XXX) ; ccp:RGD_gene_record__gene_identifier_field_value
           (?/field_2_field_value rdf/type ?/field_2_ice_id)
           (?/field_2_ice_id obo/IAO_0000219 ?/gene)) ; IAO:denotes

    :reify ([?/indirect_template_restriction1 {:ln (:restriction)
                   :ns "ccp" :prefix "R_"}])
   
    :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}