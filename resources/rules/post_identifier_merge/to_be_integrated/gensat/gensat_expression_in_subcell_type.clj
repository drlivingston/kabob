;; ------------------------------------------------------
;; --------- Gensat Expression in Sub-Cell Type ---------
;; ------------------------------------------------------
`{:name "gensat-expression-in-subcell-type"
  :description "This rule asserts thats gene expression occurs in a sub-cell type"
  :head ((?/sub_cell_type_expression rdfs/subClassOf obo/GO_0010467) ; GO:gene_expression
         (?/record obo/IAO_0000142 ?/sct_expression) ; IAO:mentions
         (?/cell_type rdfs/subClassOf ?/cell)
         (?/sub_cell_type rdfs/subClassOf ?/sub_cell)
         (?/sub_cell_type rdfs/subClassOf ?/cell_type)
         
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; BFO:occurs_in
         (?/r1 owl/someValuesFrom ?/sub_cell_type)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; RO:transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         (?/r3 rdf/type owl/Restriction)
         (?/r3 owl/onProperty obo/BFO_0000050) ; BFO:part_of
         (?/r3 owl/someValuesFrom ?/uberon)
         
         (?/cell_type rdfs/subClassOf ?/r3)

         ;; still need to add a sufficient definition
         (?/sub_cell_type_expression rdfs/subClassOf ?/r1)
         (?/sub_cell_type_expression rdfs/subClassOf ?/r2)
         (?/cell_type rdfs/subClassOf ?/r3)
         (?/sub_cell_type rdfs/subClassOf ?/r3)
         
         (?/sub_cell_type_expression rdfs/subClassOf ?/cell_type_expression))

  :body ((_/fv4 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellSubTypeNameDataField1)
         (_/fv4 skos/exactMatch _/cell_sub_ice)
         (_/sub_cell_ice obo/IAO_0000219 ?/sub_cell) ; IAO:denotes
         (?/sub_cell [rdfs/subClassOf *] ?/parent_sub)
          (= ?/parent_sub  obo/CL_0000000) ; CL:cell
    
         (?/record obo/BFO_0000051 _/fv4) ; BFO:has_part
         (?/record obo/BFO_0000051 _/fv3) ; BFO:has_part
   
         (_/fv3 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellTypeNameDataField1)
         (_/fv3 skos/exactMatch _/cell_ice)
         (_/cell_ice obo/IAO_0000219 ?/cell) ; IAO:denotes
         (?/cell [rdfs/subClassOf *] ?/parent)
         (= ?/parent  obo/CL_0000000) ; CL:cell
   
         (?/record obo/BFO_0000051 _/fv2) ; BFO:has_part
    
         (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
         (_/fv2 obo/IAO_0000219 _/uberon_name) ; IAO:denotes
   
         (_/fv2 skos/exactMatch _/uberonice)
         (_/uberonice obo/IAO_0000219 ?/uberon) ; IAO:denotes
         (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) ; UBERON:anatomical_entity
   
         (?/record obo/BFO_0000051 _/fv1) ; BFO:has_part

         (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
         (_/fv1 obo/IAO_0000219 _/gene_ice) ; IAO:denotes
         (_/gene_ice obo/IAO_0000219 ?/gene)) ; IAO:denotes
  
  :reify ([?/sub_cell_type_expression {:ln (:sha-1 "expression" ?/gene ?/sub_cell ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/cell_type_expression {:ln (:sha-1 "expression" ?/gene ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/cell_type {:ln (:sha-1 "celltype" ?/cell ?/uberon)
                           :ns "kbio" :prefix "CT_"}]
          [?/sub_cell_type {:ln (:sha-1 "celltype" ?sub_cell ?/cell ?/uberon)
                          :ns "kbio" :prefix "CT_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r3 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }