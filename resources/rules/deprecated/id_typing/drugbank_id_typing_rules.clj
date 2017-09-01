`{:name "drugbank-drug-id-type-assertion"
  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_23888)) ;drug
  :body (~@(kabob/tv iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1
                     ?/ice))}


