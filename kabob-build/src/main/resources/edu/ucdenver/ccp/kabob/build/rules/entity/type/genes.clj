

`{:name "egid-denotes-type-gene"
  :head ((?/dna rdfs/subClassOf obo/SO_0000704) ;gene
         (?/dna rdf/type kbio/GeneSpecificGClass))

  :body (~@(kabob/rtv _/record
                      iaoeg/EntrezGeneInfoFileData_geneIDDataField1 _/egIce
                      iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1 ?/type)
         (!= ?/type "pseudo")
         (!= ?/type "unknown")
         (!= ?/type "other")
         (_/egIce obo/IAO_0000219 ?/dna))
  }
