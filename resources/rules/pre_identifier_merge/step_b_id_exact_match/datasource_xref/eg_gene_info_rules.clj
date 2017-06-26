;; --------------------------------
;; --------- EG Gene Info ---------
;; --------------------------------
`{:name "eg-gene-exact-mapping-assertion"
  :description "This rule asserts an exact match between eg genes and other gene ids"
  :head ((?/geneid skos/exactMatch ?/otherice))
  :body ((?/record iaoeg/EntrezGeneInfoFileData_geneIDDataField1 ?/geneid)
         (?/record iaoeg/EntrezGeneInfoFileData_dbXrefsDataField1 ?/otherice)
         (?/otherice kiao/denotesSubClassOf obo/SO_0000352)) ; SO:dna
  }