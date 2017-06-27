;; ------------------------------------------------
;; --------- Entrez Gene Taxon Assignment ---------
;; ------------------------------------------------
`{:name "entrez-gene-assign-taxon"
  :description "This rule assigns taxons to genes represented by entrez gene identifiers"
  :head (
         ;; create a taxon restriction
         (?/gene obo/RO_0002162 ?/taxon) ; RO:in_taxon
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty obo/RO_0002162) ; RO:in_taxon
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/bioentity rdfs/subClassOf ?/restriction))

   :body ((?/record rdf/type ccp/IAO_EXT_0000866) ; ccp:NCBI_gene_record
          (?/record obo/BFO_0000051 ?/gene_field_value) ; BFO:has_part
          (?/gene_field_value rdf/type ccp/IAO_EXT_0000876) ; ccp:NCBI_gene_info_record__gene_identifier_field_value
          (?/gene_field_value rdf/type ?/gene_ice_id)
          (?/gene_ice_id obo/IAO_0000219 ?/gene) ; IAO:denotes

          ;; retrieve the taxon
          (?/record obo/BFO_0000051 ?/taxon_field_value) ; BFO:has_part
          (?/taxon_field_value rdf/type ccp/IAO_EXT_0000875) ; ccp:NCBI_gene_info_record__taxon_identifier_field_value
          (?/taxon_field_value rdf/type ?/taxon_ice_id) 
          (?/taxon_ice_id obo/IAO_0000219 ?/taxon)) ; IAO:denotes

   :reify ([?/restriction {:ln (:restriction)
                  :ns "ccp" :prefix "R_"}])
}