;; --------------------------------------------------
;; --------- Gensat Expression in Cell Type ---------
;; --------------------------------------------------
`{:name "gensat-expression-in-cell-type"
  :description "This rule asserts thats gene expression occurs in a cell type"
  :head (
         (?/cell_type_expression rdfs/subClassOf obo/GO_0010467) ; GO:gene_expression
         (?/record obo/IAO_0000142 ?/cell_type_expression) ; IAO:mentions
         (?/cell_type rdfs/subClassOf ?/cell)
         
         ;; create a occurs in restriction
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; BFO:occurs_in
         (?/r1 owl/someValuesFrom ?/cell_type)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; RO:transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         (?/r3 rdf/type owl/Restriction)
         (?/r3 owl/onProperty obo/BFO_0000050) ; BFO:part_of
         (?/r3 owl/someValuesFrom ?/uberon)
         
         (?/cell_type rdfs/subClassOf ?/r3)

         (?/cell_type_expression rdfs/subClassOf ?/r1)
         (?/cell_type_expression rdfs/subClassOf ?/r2)
         (?/cell_type_expression rdfs/subClassOf ?/uberon_expression))

  :body ((_/fv3 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellTypeNameDataField1)
         (_/fv3 skos/exactMatch _/cell_ice)
         (_/cell_ice obo/IAO_0000219 ?/cell) ; IAO:denotes
         (?/cell [rdfs/subClassOf *] ?/parent)
         (= ?/parent  obo/CL_0000000) ; CL:cell
   
         (?/record obo/BFO_0000051 _/fv3) ; BFO:has_part
         (?/record obo/BFO_0000051 _/fv2) ; BFO:has_part
    
         (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
         (_/fv2 obo/IAO_0000219 _/uberon_name) ; IAO:denotes
   
         (_/fv2 skos/exactMatch _/uberon_ice)
         (_/uberon_ice obo/IAO_0000219 ?/uberon) ; IAO:denotes
         (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) ; UBERON:anatomical_entity
   
         (?/record obo/BFO_0000051 _/fv1) ; BFO:has_part

         (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
         (_/fv1 obo/IAO_0000219 _/gene_ice) ; IAO:denotes
         (_/gene_ice obo/IAO_0000219 ?/gene)) ; IAO:denotes
  
  :reify ([?/uberon_expression {:ln (:sha-1 "expression" ?/gene ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/cell_type_expression {:ln (:sha-1 "expression" ?/gene ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/cell_type {:ln (:sha-1 "celltype" ?/cell ?/uberon)
                           :ns "kbio" :prefix "CT_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r3 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }