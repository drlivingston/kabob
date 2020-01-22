;; -----------------------------------------------
;; --------- Gensat Expression in Uberon ---------
;; -----------------------------------------------
`{:name "gensat-expression-in-uberon"
  :description "This rule asserts thats gene expression occurs in uberon"
  :head ((?/expression rdfs/subClassOf obo/GO_0010467) ; GO:gene_expression
         (?/record obo/IAO_0000142 ?/expression) ; IAO:mentions

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; BFO:occurs_in
         (?/r1 owl/someValuesFrom ?/uberon)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; RO:transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         ;; make the interaction be necessarily part of these 3 restrictions
         (?/expression rdfs/subClassOf ?/r1)
         (?/expression rdfs/subClassOf ?/r2))
  
  :body ((_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
        (_/fv2 obo/IAO_0000219 _/uberon_name) ; IAO:denotes
   
        (_/fv2 skos/exactMatch _/uberonice)
        (_/uberonice obo/IAO_0000219 ?/uberon) ; IAO:denotes
        (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) 
   
        (?/record obo/BFO_0000051 _/fv2) ; BFO:has_part
        (?/record obo/BFO_0000051 _/fv1) ; BFO:has_part

        (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
        (_/fv1 obo/IAO_0000219 _/geneice) ; IAO:denotes
        (_/geneice obo/IAO_0000219 ?/gene)) ; IAO:denotes
  
  :reify ([?/expression {:ln (:sha-1 "expression" ?/gene ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }