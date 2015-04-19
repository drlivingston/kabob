;;;; Rules to generate bio constructs from drugbank targets

;; obo/INO_0000002 ;interaction
;; obo/MI_0000 ;psi-mi "molecular interaction"

;;TODO the restrictions need to be properly reified

`{:name "drugbank-gene-drug-relation"
  ;; this need to be hashed and have proper classes
  :head ((?/gorgporv kiao/STANDIN_gene_drug ?/drug);)
         (?/gorgporv kiao/STANDIN_drugbank_gene_drug ?/drug);)
         (?/interaction rdfs/subClassOf obo/MI_0000) ;interaction

         ;;these two triples are redundant rdf macros
         (?/interaction kbio/rsv_has_participant ?/gorgporv)
         (?/interaction kbio/rsv_has_participant ?/drug)
         
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/has_participant)
         (?/r1 owl/someValuesFrom ?/drug)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/has_participant)
         (?/r2 owl/someValuesFrom ?/gorgporv)

         (?/r3 rdf/type owl/Restriction)
         (?/r3 owl/onProperty obo/realizes)
         (?/r3 owl/someValuesFrom ?/inheres)

         ;;create a new anonymous class that is CHEBI drug role
         ;;   that inheres in this specific drug
         ;;   so that it can be realized by the restriction above
         (?/r4 rdf/type owl/Restriction)
         (?/r4 owl/onProperty obo/inheres_in)
         (?/r4 owl/someValuesFrom ?/drug)
         
         (?/inheres rdfs/subClassOf obo/CHEBI_23888) ;drug role
         (?/inheres rdfs/subClassOf ?/r4)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/r2)
         (?/interaction rdfs/subClassOf ?/r3))
  

;;iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1
;;iaodrugbank/DrugBankPartnerRecord_externalIdentifiersDataField1
  ;;(?/partnerRec obo/has_part ?/entityFV)
  
  :body
  (
   ;; (_/fv0 kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
   ;; (_/fv0 obo/IAO_0000219 _/drugid)
   ;; (_/record obo/has_part _/fv0)

   ;; (_/record obo/has_part _/subrecord1)
   ;; (_/subrecord1 obo/has_part _/fv1)
   ;; (_/fv1 kiao/hasTemplate iaodrugbank/DrugBankPartnerRecord_externalIdentifiersDataField1)
   ;; (_/fv1 obo/IAO_0000219 _/externalid)
   
   (_/fv1 kiao/hasTemplate iaodrugbank/DrugBankPartnerRecord_externalIdentifiersDataField1)
   (_/fv1 obo/IAO_0000219 _/externalid)
   (_/partnerRecord1 obo/has_part _/fv1)
   (_/targetRecord1 obo/has_part _/partnerRecord1)
   (_/record obo/has_part _/targetRecord1)

   (_/record obo/has_part _/fv0)
   (_/fv0 kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
   (_/fv0 obo/IAO_0000219 _/drugid)

   (_/drugid obo/IAO_0000219 ?/drug)

   (_/externalid obo/IAO_0000219 ?/bio)
   (?/bio [rdfs/subClassOf clojure.core/*] ?/gorgporv)
   (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

  
  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/bio ?/drug)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r3 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r4 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/inheres {:ln (:restriction)
                      :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  ;; :reify (?/interaction
  ;;         ?/r1
  ;;         ?/r2
  ;;         ?/r3
  ;;         ?/r4
  ;;         ?/inheres)
  }
