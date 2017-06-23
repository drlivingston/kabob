;; PharmGkb Gene ids are of type kiao/DNAIdentifier
`{:name "pharmgkb-gene-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body (~@(kabob/tv iaopharmgkb/PharmGkbGeneFileRecord_accessionIdDataField1 
                     ?/ice))}

`{:name "pharmgkb-disease-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/DOID_4)) ;(human) disease
  :body (~@(kabob/tv iaopharmgkb/PharmGkbDiseaseFileRecord_accessionIdDataField1
                     ?/ice))}

`{:name "pharmgkb-drug-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_23888)) ;drug
  :body (~@(kabob/tv iaopharmgkb/PharmGkbDrugFileRecord_accessionIdDataField1
                     ?/ice))}


