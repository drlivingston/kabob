;; ----------------------------------------------------
;; --------- Gene or Gene Product Abstraction ---------
;; ----------------------------------------------------
;;TODO the restrictions need to be properly reified

`{:name "gene-or-gene-product-abstraction"
  :description "This rule creates an absraction for referring to genes or gene products"
  ;; this needs to be hashed and have proper classes
  :head ((?/gene_product rdfs/subClassOf ?/gene_or_gene_product))
  
  :body ((?/gene rdf/type kbio/GeneSpecificGClass)
         (?/gene rdfs/subClassOf ?/gene_or_gene_product)
         (?/gene_or_gene_product rdf/type kbio/GeneSpecificGorGPClass)

         (?/has_indriect_template_restriction owl/someValuesFrom ?/gene)
         (?/has_indriect_template_restriction owl/onProperty kso/has_indirect_template)
         (?/gene_product rdfs/subClassOf?/has_indriect_template_restriction))

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }