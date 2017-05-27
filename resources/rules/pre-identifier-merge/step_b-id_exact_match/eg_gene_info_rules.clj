;; EG gene id to other gene id mappings
`{:name "eg-gene-exact-mapping-assertion"
 :head ((?/geneid skos/exactMatch ?/otherice))
  :body
  (~@(kabob/rtv _/record
      iaoeg/EntrezGeneInfoFileData_geneIDDataField1 ?/geneid
      iaoeg/EntrezGeneInfoFileData_dbXrefsDataField1 ?/otherice)
   (?/otherice kiao/denotesSubClassOf obo/SO_0000352))} ;dna
    ;;rdf/type kiao/DNAIdentifier))}

