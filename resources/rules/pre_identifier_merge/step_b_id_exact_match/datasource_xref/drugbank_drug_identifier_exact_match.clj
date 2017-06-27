;; --------------------------------------------------------
;; --------- DrugBank Drug Identifier Exact Match ---------
;; --------------------------------------------------------
`{:name "drugbank-drug-identifier-exact-match"
  :description "This rule asserts an exact match between drugbank drugs and other drug identifiers"
  :head ((?/drug_identifier skos/exactMath ?/other_identifier))
  :body ((?/fv0 kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
         (?/fv0 obo/IAO_0000219  ?/dbice)  ; IAO:denotes
         (?/record obo/BFO_0000051  ?/fv0) ; BFO:has_part

         (?/record obo/BFO_0000051  ?/externalfv) ; BFO:has_part
         (?/externalfv kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_externalIdentifiersDataField1)
         (?/externalfv obo/IAO_0000219  ?/otherice) ; IAO:denotes

         ;;check to see if that fv is in another record
         (:optional ((?/record2 obo/BFO_0000051  ?/externalfv) ; BFO:has_part
                     (:not (= ?/record2 ?/record))))
         (:not (:bound ?/record2)))

    :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
}