;;;; Rules to generate bio constructs from GOA

`{:name "goa-bp"
  :head (
         (?/bp rdfs/subClassOf ?/go) ;interaction

         (?/protein rdfs/subClassOf ?/bioentity)
         
         (?/hr1 rdf/type owl/Restriction)
         (?/hr1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/hr1 owl/someValuesFrom ?/protein)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/bp rdfs/subClassOf ?/hr1))
    
  :body ((?/go [rdfs/subClassOf *] obo/GO_0008150)
         (?/goid obo/IAO_0000219 ?/go) ; denotes
         (?/fv0 obo/IAO_0000219 ?/goid) ; denotes
         (?/fv0 kiao/hasTemplate  iaogoa/GoaGaf2FileRecord_ontologyTermIdDataField1)

         (?/record obo/BFO_0000051 ?/fv0) ; has_part        
         (?/record obo/BFO_0000051 ?/fv1) ; has_part

         (?/fv1 kiao/hasTemplate iaogoa/GoaGaf2FileRecord_dbObjectIdDataField1)
         (?/fv1 obo/IAO_0000219 ?/gp) ; denotes
         (?/gp obo/IAO_0000219 ?/bioentity) ; denotes

         
         ;; ;;filter out the negations
          (:optional
           ((?/record obo/BFO_0000051 ?/qualfv) ; has_part
            (?/qualfv kiao/hasTemplate iaogoa/GoaGaf2FileRecord_qualifierDataField)
            (?/qualfv obo/IAO_0000219 ?/qualifier))) ; denotes
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))

         ;;is it always a protein, or do we need to go up to GorGP?  
         ;;(_/gp obo/IAO_0000219 ?/bioentity))

         ;; (_/geneid obo/IAO_0000219 ?/gene)
         ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
         ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

         )
  :reify ([?/bp {:ln (:sha-1 ?/go ?/hr1)
                 :ns "kbio" :prefix "BP_"}]
          [?/protein {:ln (:sha-1 ?/go ?/bioentity)
                    :ns "kbio" :prefix "P_"}]
          [?/hr1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }


`{:name "goa-cc"
  :head (
         (?/loc rdfs/subClassOf obo/GO_0051179) ;localization

         (?/protein rdfs/subClassOf ?/bioentity)
         (?/gocc rdfs/subClassOf ?/go)
         
         (?/of1 rdf/type owl/Restriction)
         (?/of1 owl/onProperty obo/RO_0002313) ;transports or maintains localization of
         (?/of1 owl/someValuesFrom ?/protein)

         (?/to1 rdf/type owl/Restriction)
         (?/to1 owl/onProperty obo/RO_0002339) ; has target end location
         (?/to1 owl/someValuesFrom ?/gocc)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/loc rdfs/subClassOf ?/of1)
         (?/loc rdfs/subClassOf ?/to1))
    
  :body ((?/go [rdfs/subClassOf *] obo/GO_0005575) ;cellular_component
         (?/goid obo/IAO_0000219 ?/go) ; denotes
         (?/fv0 obo/IAO_0000219 ?/goid) ; denotes
         (?/fv0 kiao/hasTemplate  iaogoa/GoaGaf2FileRecord_ontologyTermIdDataField1)

         (?/record obo/BFO_0000051 ?/fv0) ; has_part 
         (?/record obo/BFO_0000051 ?/fv1) ; has_part

         (?/fv1 kiao/hasTemplate iaogoa/GoaGaf2FileRecord_dbObjectIdDataField1)
         (?/fv1 obo/IAO_0000219 ?/gp) ; denotes
         (?/gp obo/IAO_0000219 ?/bioentity) ; denotes

         
         ;; ;;filter out the negations
          (:optional
           ((?/record obo/BFO_0000051 ?/qualfv) ; has_part
            (?/qualfv kiao/hasTemplate iaogoa/GoaGaf2FileRecord_qualifierDataField1)
            (?/qualfv obo/IAO_0000219 ?/qualifier)))
          (:or (:not (:bound ?/qualifier))
               (:not (:regex ?/qualifier "^NOT" "i")))

         ;;is it always a protein, or do we need to go up to GorGP?  
         ;;(_/gp obo/IAO_0000219 ?/bioentity))

         ;; (_/geneid obo/IAO_0000219 ?/gene)
         ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
         ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

         )
  :reify ([?/loc {:ln (:sha-1 obo/GO_0051179 ?/of1 ?/to1)
                 :ns "kbio" :prefix "LOC_"}]
          [?/of1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/to1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/protein {:ln (:sha-1 ?/go ?/bioentity)
                     :ns "kbio" :prefix "P_"}]
          [?/gocc {:ln (:sha-1 ?/go ?/bioentity)
                   :ns "kbio" :prefix "CC_"}]
          )

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }






;; `{:name "goa-bp"
;;   ;; this need to be hashed and have proper classes
;;   :head ((?/bioentity kiao/STANDIN_bio_go ?/go))

;;          ;; (?/bp rdfs/subClassOf ?/go) ;interaction

;;          ;; ;;this triple is redundant rdf macros
;;          ;; (?/bp kbio/rsv_has_participant ?/go)
         
;;          ;; (?/r1 rdf/type owl/Restriction)
;;          ;; (?/r1 owl/onProperty obo/has_participant)
;;          ;; (?/r1 owl/someValuesFrom ?/bioentity)

;;          ;; ;;make the interaction be necessarily part of these 3 restrictions
;;          ;; ;;still need to add a sufficient definition
;;          ;; (?/bp rdfs/subClassOf ?/r1))
    
;;   :body (;;~@(kabob/rtv ?/record
;;          ;;     iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1 _/goid
;;          ;;     iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1 _/gp)

         
;;          (?/fv0 kiao/hasTemplate  iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1)
;;          (?/record obo/BFO_0000051 ?/fv0)
;;          (?/fv0 obo/IAO_0000219 ?/goid)
;;          (?/goid obo/IAO_0000219 ?/go)
;;          (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          (?/record obo/BFO_0000051 ?/fv1)
;;          (?/fv1 kiao/hasTemplate iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1)
;;          (?/fv1 obo/IAO_0000219 ?/gp)
;;          (?/gp obo/IAO_0000219 ?/bioentity)

         
;;          ;; ;;filter out the negations
         
;;          ;; (:optional
;;          ;;  ~@(kabob/rtv ?/record
;;          ;;               iaogoa/GpAssociationGoaUniprotFileData_qualifierDataField1 ?/qualifier))
;;          ;; ;;(!= ?/qualifier )
;;          ;; (:not (:regex ?/qualifier "^NOT" "i"))

;;          ;;(_/goid obo/IAO_0000219 ?/go)
;; ;;         (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          ;;is it always a protein, or do we need to go up to GorGP?  
;;          ;;(_/gp obo/IAO_0000219 ?/bioentity))

;;          ;; (_/geneid obo/IAO_0000219 ?/gene)
;;          ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
;;          ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

;;          )
;;   ;; :reify (?/bp
;;   ;;         ?/r1)
;;   }



;; `{:name "goa-bp"
;;   ;; this need to be hashed and have proper classes
;;   :head ((?/bioentity kiao/STANDIN_bio_go ?/go))

;;          ;; (?/bp rdfs/subClassOf ?/go) ;interaction

;;          ;; ;;this triple is redundant rdf macros
;;          ;; (?/bp kbio/rsv_has_participant ?/go)
         
;;          ;; (?/r1 rdf/type owl/Restriction)
;;          ;; (?/r1 owl/onProperty obo/has_participant)
;;          ;; (?/r1 owl/someValuesFrom ?/bioentity)

;;          ;; ;;make the interaction be necessarily part of these 3 restrictions
;;          ;; ;;still need to add a sufficient definition
;;          ;; (?/bp rdfs/subClassOf ?/r1))
    
;;   :body (~@(kabob/rtv ?/record
;;               iaogoa/GpAssociationGoaUniprotFileData_goIDDataField1 _/goid
;;               iaogoa/GpAssociationGoaUniprotFileData_databaseObjectIDDataField1 _/gp)

;;          ;; ;;filter out the negations
;;          ;; (:optional
;;          ;;  ~@(kabob/rtv ?/record
;;          ;;               iaogoa/GpAssociationGoaUniprotFileData_qualifierDataField1 ?/qualifier))
;;          ;; ;;(!= ?/qualifier )
;;          ;; (:not (:regex ?/qualifier "^NOT" "i"))

;;          (_/goid obo/IAO_0000219 ?/go)
;; ;;         (?/go [rdfs/subClassOf *] obo/GO_0008150)

;;          ;;is it always a protein, or do we need to go up to GorGP?  
;;          (_/gp obo/IAO_0000219 ?/bioentity))

;;          ;; (_/geneid obo/IAO_0000219 ?/gene)
;;          ;; (?/gene [rdfs/subClassOf *] ?/gorgporv) 
;;          ;; (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

;;   :reify (?/bp
;;           ?/r1)
;;   }
