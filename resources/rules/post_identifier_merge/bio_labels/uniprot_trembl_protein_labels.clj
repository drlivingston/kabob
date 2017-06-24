;; -----------------------------------------------------------
;; --------- Uniprot Trembl Protein Label Assignment ---------
;; -----------------------------------------------------------
`{:name "uniprot-trembl-protein-labels"
  :description "This rule creates a label for every trembl protein record and types it as (IAO_EXT_0000991)"
  :head (
         ;; create a label for protein
         (?/protein rdfs/label ?/protein_name))

  :body ((?/record rdf/type ccp/IAO_EXT_0000987) ; ccp:Sparse_UniProt_record
         (?/record obo/BFO_0000051 ?/protein_field_value) ; BFO:has_part
         (?/protein_field_value rdf/type ccp/IAO_EXT_0000990) ; ccp:Sparse_UniProt_record__accession_field_value
         (?/protein_field_value rdf/type ?/protein_ice_id)
         (?/protein_ice_id obo/IAO_0000219 ?/protein) ; IAO:denotes

         ;; retrieve the protein name
         (?/record obo/BFO_0000051 ?/protein_symbol_field_value) ; BFO:has_part
         (?/protein_symbol_field_value rdf/type ccp/IAO_EXT_0000991) ; ccp:Sparse_UniProt_record__name_field_value
         (?/protein_symbol_field_value rdfs/label ?/protein_symbol))

   ;; In this case no :reify entry is required, since all of the data required to form the new triples already exist

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }