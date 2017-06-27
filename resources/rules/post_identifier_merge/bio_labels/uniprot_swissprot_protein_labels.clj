;; --------------------------------------------------------
;; ------ Uniprot SwissProt Protein Label Assignment ------
;; --------------------------------------------------------
`{:name "uniprot-swissprot-protein-labels"
  :description "This rule creates a label for every swissprot protein record and types it as (IAO_EXT_0000932)"
  :head (
         ;; create a label for protein
         (?/protein rdfs/label ?/protein_name))

  :body ((?/record rdf/type ccp/IAO_EXT_0000234) ; ccp:UniProt_protein_record
         (?/record obo/BFO_0000051 ?/protein_field_value) ; BFO:has_part
         (?/protein_field_value rdf/type ccp/IAO_EXT_0000931) ; ccp:UniProt_protein_record__accession_field_value
         (?/protein_field_value rdf/type ?/protein_ice_id)
         (?/protein_ice_id obo/IAO_0000219 ?/protein) ; IAO:denotes

         ;; retrieve the protein name
         (?/record obo/BFO_0000051 ?/protein_symbol_field_value) ; BFO:has_part
         (?/protein_symbol_field_value rdf/type ccp/IAO_EXT_0000932) ; ccp:UniProt_protein_record__name_field_value
         (?/protein_symbol_field_value rdfs/label ?/protein_symbol))

   ;; In this case no :reify entry is required, since all of the data required to form the new triples already exist

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }