;; --------------------------------------------------
;; --------- Entrez Gene Label Assignment -----------
;; --------------------------------------------------
`{:name "entrez-gene-labels"
  :description "This rule creates a label for every gene record and types it as (IAO_EXT_0000877)"
  :head (
         ;; create a symbol label for gene
         (?/gene rdfs/label ?/gene_symbol))

  :body ((?/record rdf/type ccp/IAO_EXT_0000866) ; ccp:NCBI_gene_record
         (?/record obo/BFO_0000051 ?/gene_field_value) ; BFO:has_part
         (?/gene_field_value rdf/type ccp/IAO_EXT_0000876) ; ccp:NCBI_gene_info_record__gene_identifier_field_value
         (?/gene_field_value rdf/type ?/gene_ice_id)
         (?/gene_ice_id obo/IAO_0000219 ?/gene) ; IAO:denotes

         ;; retrieve the gene symbol
         (?/record obo/BFO_0000051 ?/gene_symbol_field_value) ; BFO:has_part
         (?/gene_symbol_field_value rdf/type ccp/IAO_EXT_0000877) ; ccp:NCBI_gene_info_record__symbol_field_value
         (?/gene_symbol_field_value rdfs/label ?/gene_symbol))

   ;; In this case no `:reify` entry is required, since all of the data required to form the new triples already exist

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }