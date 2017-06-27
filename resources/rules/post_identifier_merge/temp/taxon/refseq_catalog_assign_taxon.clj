;; ---------------------------------------------------
;; --------- RefSeq Catalog Taxon Assignment ---------
;; ---------------------------------------------------
`{:name "refseq-catalog-assign-taxon"
  :description "This rule assigns taxons to bio-entities represented by refseq catalog identifiers"
  :head (
         ;; create a taxon restriction
         (?/catalog obo/RO_0002162 ?/taxon) ; RO:in_taxon
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty obo/RO_0002162) ; RO:in_taxon
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/bioentity rdfs/subClassOf ?/restriction))

   :body ((?/record rdf/type ccp/IAO_EXT_0000795) ; ccp:RefSeq_catalog_record
          (?/record obo/BFO_0000051 ?/catalog_field_value) ; BFO:has_part
          (?/catalog_field_value rdf/type ccp/IAO_EXT_0000800) ; ccp:RefSeq_catalog_record__RefSeq_identifier_field_value
          (?/catalog_field_value rdf/type ?/catalog_ice_id)
          (?/catalog_ice_id obo/IAO_0000219 ?/catalog) ; IAO:denotes

          ;; retrieve the taxon
          (?/record obo/BFO_0000051 ?/taxon_field_value) ; BFO:has_part
          (?/taxon_field_value rdf/type ccp/IAO_EXT_0000796) ; ccp:RefSeq_catalog_record__taxonomy_identifier_field_value
          (?/taxon_field_value rdf/type ?/taxon_ice_id) 
          (?/taxon_ice_id obo/IAO_0000219 ?/taxon)) ; IAO:denotes

    :reify ([?/restriction {:ln (:restriction)
                   :ns "ccp" :prefix "R_"}])
}