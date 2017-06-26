;; -------------------------------------------
;; --------- drugbank_drugs_rules ---------
;; -------------------------------------------
;; create drugbank  id to other chemical id mappings



;; drugbank id to other chemical id mappings
;; it would be nice if this rule just worked - unfortunately there are errors
;;   in the drugbank mappings - they are NOT UNIQUE
;; `{:name "drugbank-drug-exact-mapping-assertion"
;;  :head ((?/dbice skos/exactMatch ?/otherice))
;;   :body
;;   (~@(kabob/rtv _/record
;;       iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1 ?/dbice
;;       iaodrugbank/DrugBankDrugRecord_externalIdentifiersDataField1 ?/otherice))}


;; CORRECTING FOR NON-UNIQUENESS
`{:name "drugbank-drug-exact-mapping-assertion"
  :description "This rule asserts an exact match between drugbank drugs and other drug ids"
  :head ((?/dbice skos/exactMatch ?/otherice))
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