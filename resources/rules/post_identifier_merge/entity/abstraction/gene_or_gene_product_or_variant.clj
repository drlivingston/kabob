;; ---------------------------------------------------
;; --------- Gene or Gene Product or Variant ---------
;; ---------------------------------------------------
`{:name "gene-or-gene-product-or-variant"
  :description "This rule asserts genes, gene products, or variants"
  :head ((?/gene rdfs/subClassOf ?/gorgp)
         (?/gorgp rdfs/subClassOf kbio/GorGP)
         (?/gorgp rdf/type kbio/GeneSpecificGorGPClass)

         (?/gorgp rdfs/subClassOf ?/gorgporv)
         (?/gorgporv rdfs/subClassOf kbio/GorGPorV)
         (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

         ;;this is entailed but short-circuiting it might be valuable
         ;;(?/gene rdfs/subClassOf ?/gorgporv)

  ;; the first triple of the body should be redundant with the second
  :body ((?/gene rdfs/subClassOf obo/SO_0000704) ; SO:gene
         (?/gene rdf/type kbio/GeneSpecificGClass))

  :reify ([?/gorgp    {:ln (:localname ?/gene)
                       :ns "kbio" :prefix "GorGP_" :suffix ""}]
          [?/gorgporv {:ln (:localname ?/gene)
                       :ns "kbio" :prefix "GorGPorV_" :suffix ""}])
  }
