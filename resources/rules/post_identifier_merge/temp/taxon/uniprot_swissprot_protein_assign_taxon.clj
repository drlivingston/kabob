;; --------------------------------------------------------------
;; --------- Uniprot SwissProt Protein Taxon Assignment ---------
;; --------------------------------------------------------------
`{:name "uniprot-swissprot-protein-assign-taxon"
  :description "This rule assigns a taxon to proteins represented by uniprot swissprot protein identifiers"
  :head (
         ;; create a taxon restriction
         (?/protein obo/RO_0002162 ?/taxon) ; RO:in_taxon
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty obo/RO_0002162) ; RO:in_taxon
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/protein rdfs/subClassOf ?/restriction))

   :body ((?/record rdf/type ccp/IAO_EXT_0000234) ; ccp:UniProt_protein_record
          (?/record obo/BFO_0000051 ?/protein_field_value) ; BFO:has_part
          (?/protein_field_value rdf/type ccp/IAO_EXT_0000931) ; ccp:UniProt_protein_record__accession_field_value
          (?/protein_field_value rdf/type ?/protein_ice_id)
          (?/protein_ice_id obo/IAO_0000219 ?/protein) ; IAO:denotes

          ;; retrieve the taxon
          (?/record obo/BFO_0000051 ?/taxon_field_value) ; BFO:has_part
          (?/taxon_field_value rdf/type ccp/IAO_EXT_0000935) ; ccp:UniProt_protein_record__organism_field_value
          (?/taxon_field_value rdf/type ?/taxon_ice_id) 
          (?/taxon_ice_id obo/IAO_0000219 ?/taxon)) ; IAO:denotes

   :reify ([?/restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}])
   }