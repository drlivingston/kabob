`{:name "gensat-expression-in-uberon"
  :head (
         (?/expression rdfs/subClassOf obo/GO_0010467) ;gene_expression
         (?/record obo/IAO_0000142 ?/expression)

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; occurs_in
         (?/r1 owl/someValuesFrom ?/uberon)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/expression rdfs/subClassOf ?/r1)
         (?/expression rdfs/subClassOf ?/r2)
)
  :body  (
  
   (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
   (_/fv2 obo/IAO_0000219 _/uberon_name)
   
   (_/fv2 skos/exactMatch _/uberonice)
   (_/uberonice obo/IAO_0000219 ?/uberon)
   (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) 
   
   (?/record obo/BFO_0000051 _/fv2)
   (?/record obo/BFO_0000051 _/fv1)

   (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
   (_/fv1 obo/IAO_0000219 _/geneice)
   (_/geneice obo/IAO_0000219 ?/gene)
   
)
  
  :reify ([?/expression {:ln (:sha-1 "expression" ?/gene ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }


`{:name "gensat-expression-in-celltype"
  :head (
         (?/ct_expression rdfs/subClassOf obo/GO_0010467) ;gene_expression
         (?/record obo/IAO_0000142 ?/ct_expression) ; mentions
         (?/celltype rdfs/subClassOf ?/cell)
         
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; occurs_in
         (?/r1 owl/someValuesFrom ?/celltype)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         (?/r3 rdf/type owl/Restriction)
         (?/r3 owl/onProperty obo/BFO_0000050) ; part_of
         (?/r3 owl/someValuesFrom ?/uberon)
         
         (?/celltype rdfs/subClassOf ?/r3)

         (?/ct_expression rdfs/subClassOf ?/r1)
         (?/ct_expression rdfs/subClassOf ?/r2)
         (?/ct_expression rdfs/subClassOf ?/uberon_expression)
)

  :body
  (

   (_/fv3 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellTypeNameDataField1)
   ;;(_/fv3 obo/IAO_0000219 _/celltype_name)
   (_/fv3 skos/exactMatch _/cellice)
   (_/cellice obo/IAO_0000219 ?/cell) ; denotes
   ;;(:not (= ?/cell  obo/CL_0000000))
   ;;(?/cell [rdfs/subClassOf *] obo/CL_0000000)
   (?/cell [rdfs/subClassOf *] ?/parent)
    (= ?/parent  obo/CL_0000000)
   
   (?/record obo/BFO_0000051 _/fv3) ; has_part
   (?/record obo/BFO_0000051 _/fv2) ; has_part
    
   (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
   (_/fv2 obo/IAO_0000219 _/uberon_name) ; denotes
   
   (_/fv2 skos/exactMatch _/uberonice)
   (_/uberonice obo/IAO_0000219 ?/uberon) ; denotes
   (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) 
   
   (?/record obo/BFO_0000051 _/fv1) ; has_part

   (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
   (_/fv1 obo/IAO_0000219 _/geneice) ; denotes
   (_/geneice obo/IAO_0000219 ?/gene) ; denotes
   
)
  
  :reify ([?/uberon_expression {:ln (:sha-1 "expression" ?/gene ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/ct_expression {:ln (:sha-1 "expression" ?/gene ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/celltype {:ln (:sha-1 "celltype" ?/cell ?/uberon)
                          :ns "kbio" :prefix "CT_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r3 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }



`{:name "gensat-expression-in-subcelltype"
  :head (
         (?/sct_expression rdfs/subClassOf obo/GO_0010467) ;gene_expression
         (?/record obo/IAO_0000142 ?/sct_expression) ; mentions
         (?/celltype rdfs/subClassOf ?/cell)
         (?/cellsubtype rdfs/subClassOf ?/cellsub)
         (?/cellsubtype rdfs/subClassOf ?/celltype)
         
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/BFO_0000066) ; occurs_in
         (?/r1 owl/someValuesFrom ?/cellsubtype)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0002510) ; transcribed_from
         (?/r2 owl/someValuesFrom ?/gene)

         (?/r3 rdf/type owl/Restriction)
         (?/r3 owl/onProperty obo/BFO_0000050) ; part_of
         (?/r3 owl/someValuesFrom ?/uberon)
         
         (?/celltype rdfs/subClassOf ?/r3)

;         ;;still need to add a sufficient definition
         (?/sct_expression rdfs/subClassOf ?/r1)
         (?/sct_expression rdfs/subClassOf ?/r2)
         (?/celltype rdfs/subClassOf ?/r3)
         (?/cellsubtype rdfs/subClassOf ?/r3)
         
         (?/sct_expression rdfs/subClassOf ?/ct_expression)
)

  :body
  (

    
   (_/fv4 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellSubTypeNameDataField1)
   ;;(_/fv4 obo/IAO_0000219 _/cellsubtype_name)
   (_/fv4 skos/exactMatch _/cellsubice)
   (_/cellsubice obo/IAO_0000219 ?/cellsub) ; denotes
   ;;(:not (= ?/cell  obo/CL_0000000))
   ;;(?/cell [rdfs/subClassOf *] obo/CL_0000000)
   (?/cellsub [rdfs/subClassOf *] ?/parentsub)
    (= ?/parentsub  obo/CL_0000000) 
    
   (?/record obo/BFO_0000051 _/fv4) ; has_part
   (?/record obo/BFO_0000051 _/fv3) ; has_part
   
   (_/fv3 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellTypeNameDataField1)
   ;;(_/fv3 obo/IAO_0000219 _/celltype_name)
   (_/fv3 skos/exactMatch _/cellice)
   (_/cellice obo/IAO_0000219 ?/cell) ; denotes
   ;;(:not (= ?/cell  obo/CL_0000000))
   ;;(?/cell [rdfs/subClassOf *] obo/CL_0000000)
   (?/cell [rdfs/subClassOf *] ?/parent)
    (= ?/parent  obo/CL_0000000)
   
   (?/record obo/BFO_0000051 _/fv2) ; has_part
    
   (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
   (_/fv2 obo/IAO_0000219 _/uberon_name) ; denotes
   
   (_/fv2 skos/exactMatch _/uberonice)
   (_/uberonice obo/IAO_0000219 ?/uberon) ; denotes
   (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) 
   
   (?/record obo/BFO_0000051 _/fv1) ; has_part

   (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
   (_/fv1 obo/IAO_0000219 _/geneice) ; denotes
   (_/geneice obo/IAO_0000219 ?/gene) ; denotes
)
  
  :reify ([?/sct_expression {:ln (:sha-1 "expression" ?/gene ?/cellsub ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/ct_expression {:ln (:sha-1 "expression" ?/gene ?/cell ?/uberon)
                          :ns "kbio" :prefix "E_"}]
          [?/celltype {:ln (:sha-1 "celltype" ?/cell ?/uberon)
                          :ns "kbio" :prefix "CT_"}]
          [?/cellsubtype {:ln (:sha-1 "celltype" ?cellsub ?/cell ?/uberon)
                          :ns "kbio" :prefix "CT_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r3 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }



;`{:name "gensat-expression-in-fibers"
;  :head (
;         (?/sct_expression rdfs/subClassOf obo/GO_0010467) ;gene_expression
;         (?/celltype rdfs/subClassOf ?/cell)
;         (?/cellpart rdfs/subClassOf )
;         
;         (?/r1 rdf/type owl/Restriction)
;         (?/r1 owl/onProperty obo/BFO_0000066)
;         (?/r1 owl/someValuesFrom ?/cellpart)
;
;         (?/r2 rdf/type owl/Restriction)
;         (?/r2 owl/onProperty obo/RO_0002510) ; transcribed_from
;         (?/r2 owl/someValuesFrom ?/gene)
;
;         (?/r3 rdf/type owl/Restriction)
;         (?/r3 owl/onProperty obo/BFO_0000050)
;         (?/r3 owl/someValuesFrom ?/uberon)
;         
;         (?/celltype rdfs/subClassOf ?/r3)

;         ;;make the interaction be necessarily part of these 3 restrictions
;         ;;still need to add a sufficient definition
;         (?/sct_expression rdfs/subClassOf ?/r1)
;         (?/sct_expression rdfs/subClassOf ?/r2)
;         (?/celltype rdfs/subClassOf ?/r3)
;         (?/cellpart rdfs/subClassOf ?/r3)
;         
;         (?/sct_expression rdfs/subClassOf ?/ct_expression)
;)
;
;  :body
;  (
;
;    
;   (_/fv4 obo/IAO_0000219 "FIBERS")
;   (_/fv4 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellSubTypeNameDataField1)
;    
;   (?/record obo/BFO_0000051 _/fv4)
;   (?/record obo/BFO_0000051 _/fv3)
;   
;   (_/fv3 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_cellTypeNameDataField1)
;   ;;(_/fv3 obo/IAO_0000219 _/celltype_name)
;   (_/fv3 skos/exactMatch _/cellice)
;   (_/cellice obo/IAO_0000219 ?/cell)
;   ;;(:not (= ?/cell  obo/CL_0000000))
;   ;;(?/cell [rdfs/subClassOf *] obo/CL_0000000)
;   (?/cell [rdfs/subClassOf *] ?/parent)
;    (= ?/parent  obo/CL_0000000)
;   
;   (?/record obo/BFO_0000051 _/fv2)
;    
;   (_/fv2 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_anatomicalSectionDataField1)
;   (_/fv2 obo/IAO_0000219 _/uberon_name)
;   
;   (_/fv2 skos/exactMatch _/uberonice)
;   (_/uberonice obo/IAO_0000219 ?/uberon)
;   (?/uberon [rdfs/subClassOf *] obo/UBERON_0001062) 
;   
;   (?/record obo/BFO_0000051 _/fv1)
;
;   (_/fv1 kiao/hasTemplate iaogensat/GensatQueryOutputFileRecord_egIdDataField1)
;   (_/fv1 obo/IAO_0000219 _/geneice)
;   (_/geneice obo/IAO_0000219 ?/gene)
;   
;)
;  
;  :reify ([?/sct_expression {:ln (:sha-1 "expression" ?/gene ?/cellsub ?/cell ?/uberon)
;                          :ns "kbio" :prefix "E_"}]
;          [?/ct_expression {:ln (:sha-1 "expression" ?/gene ?/cell ?/uberon)
;                          :ns "kbio" :prefix "E_"}]
;          [?/celltype {:ln (:sha-1 "celltype" ?/cell ?/uberon)
;                          :ns "kbio" :prefix "CT_"}]
;          [?/cellsubtype {:ln (:sha-1 "celltype" ?cellsub ?/cell ?/uberon)
;                          :ns "kbio" :prefix "CT_"}]
;          [?/r1 {:ln (:restriction)
;                 :ns "kbio" :prefix "R_"}]
;          [?/r2 {:ln (:restriction)
;                 :ns "kbio" :prefix "R_"}]
;          [?/r3 {:ln (:restriction)
;                 :ns "kbio" :prefix "R_"}]
;          )
;
;  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
;  }
