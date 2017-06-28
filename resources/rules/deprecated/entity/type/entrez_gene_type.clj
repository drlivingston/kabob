;; ------------------------------------
;; --------- Entrez Gene Type ---------
;; ------------------------------------
`{:name "entrez-gene-type"
  :description "This rule identifies entrez gene type"
  :head ((?/dna rdfs/subClassOf obo/SO_0000704) ; SO:gene
         (?/dna rdf/type kbio/GeneSpecificGClass))

  :body ((?/record rdf/type ccp/IAO_EXT_0000681) ; ccp:NCBI_gene_info_record
         (?/record obo/BFO_0000051 ?/dna_field_value) ; BFO:has_part
         (?/dna_field_value rdf/type ccp/IAO_EXT_0000876) ; ccp:NCBI_gene_info_record__gene_identifier_field_value
         (?/dna_field_value rdf/type ?/dna_ice_id)
         (?/dna_ice_id obo/IAO_0000219 ?/dna) ; IAO:denotes

         ;; retrieve gene type
         (?/record obo/BFO_0000051 ?/type_field_value) ; BFO:has_part
         (?/type_field_value rdf/type ccp/IAO_EXT_0000884) ; ccp:NCBI_gene_info_record__type_of_gene_field_value
         (?/type_field_value rdf/type ?/type_ice_id)
         (?/type_ice_id obo/IAO_0000219 ?/type) ; IAO:denotes
         
         (!= ?/type "pseudo")
         (!= ?/type "unknown")
         (!= ?/type "other"))
  }


           
