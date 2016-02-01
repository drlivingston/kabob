;;;; Rules to generate bio constructs from pharm gkb

;; obo/INO_0000002 ;interaction
;; obo/MI_0000 ;psi-mi "molecular interaction"

;;TODO the restrictions need to be properly reified

`{:name "pharmgkb-gene-drug-relation"
  ;; this need to be hashed and have proper classes
  :head ((?/gene kiao/STANDIN_gene_drug ?/drug);)
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
  
  ;; :body (~@(kabob/rtv _/record
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity1TypeDataField1 "Gene"
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity2TypeDataField1 "Drug"
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1 _/geneid
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1 _/drugid)
  ;;        (_/drugid obo/IAO_0000219 ?/drug)
  ;;        (_/geneid obo/IAO_0000219 ?/gene)
  ;;        (?/gene [rdfs/subClassOf *] ?/gorgporv) 
  ;;        (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))

  :body
  ((_/fv0 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1TypeDataField1)
   (_/fv0 obo/IAO_0000219 "Gene")
   (_/record obo/BFO_0000051 _/fv0)

   (_/record obo/BFO_0000051 _/fv1)
   (_/fv1 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2TypeDataField1)
   (_/fv1 obo/IAO_0000219 "Drug")

   (_/record obo/BFO_0000051 _/fv2)
   (_/fv2 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1)
   (_/fv2 obo/IAO_0000219 _/geneid)

   (_/record obo/BFO_0000051 _/fv3)
   (_/fv3 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1)
   (_/fv3 obo/IAO_0000219 _/drugid)

   (_/drugid obo/IAO_0000219 ?/drug)

   (_/geneid obo/IAO_0000219 ?/gene)
   (?/gene [rdfs/subClassOf clojure.core/*] ?/gorgporv)
   (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))



  :reify (?/interaction
          ?/r1
          ?/r2
          ?/r3
          ?/r4

          ?/inheres)

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}

          
  }


`{:name "pharmgkb-drug-gene-relation"
  ;; this need to be hashed and have proper classes
  :head ((?/gene kiao/STANDIN_gene_drug ?/drug);)
         ;;(?/geneid kiao/STANDIN_gene_drug ?/drugid))
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
  
  ;; :body (~@(kabob/rtv _/record
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity1TypeDataField1 "Drug"
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity2TypeDataField1 "Gene"
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1 ?/drugid
  ;;             iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1 ?/geneid)
  ;;        (?/drugid obo/IAO_0000219 ?/drug)
  ;;        (?/geneid obo/IAO_0000219 ?/gene)
  ;;        (?/gene [rdfs/subClassOf *] ?/gorgporv) ;;?/gorgp)
  ;;        ;;(?/gorgp rdfs/subClassOf ?/gorgporv)
  ;;        (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass)
  ;;        )

  :body
  ((_/fv0 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2TypeDataField1)
   (_/fv0 obo/IAO_0000219 "Gene")
   (_/record obo/BFO_0000051 _/fv0)

   (_/record obo/BFO_0000051 _/fv1)
   (_/fv1 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1TypeDataField1)
   (_/fv1 obo/IAO_0000219 "Drug")

   (_/record obo/BFO_0000051 _/fv2)
   (_/fv2 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1)
   (_/fv2 obo/IAO_0000219 _/geneid)

   (_/record obo/BFO_0000051 _/fv3)
   (_/fv3 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1)
   (_/fv3 obo/IAO_0000219 _/drugid)

   (_/drugid obo/IAO_0000219 ?/drug)

   (_/geneid obo/IAO_0000219 ?/gene)
   (?/gene [rdfs/subClassOf clojure.core/*] ?/gorgporv)
   (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))


  :reify (?/interaction
          ?/r1
          ?/r2
          ?/r3
          ?/r4
          ?/inheres)
  }






;; `{:name "pharmgkb-gene-disease-relation"
;;   ;; this need to be hashed and have proper classes
;;   :head ((?/gene kiao/STANDIN_gene_drug ?/drug);)
;;          (?/interaction rdfs/subClassOf obo/MI_0000) ;interaction

;;          ;;these two triples are redundant rdf macros
;;          (?/interaction kbio/rsv_has_participant ?/gorgporv)
;;          (?/interaction kbio/rsv_has_participant ?/drug)
         
;;          (?/r1 rdf/type owl/Restriction)
;;          (?/r1 owl/onProperty obo/has_participant)
;;          (?/r1 owl/someValuesFrom ?/drug)

;;          (?/r2 rdf/type owl/Restriction)
;;          (?/r2 owl/onProperty obo/has_participant)
;;          (?/r2 owl/someValuesFrom ?/gorgporv)

;;          (?/r3 rdf/type owl/Restriction)
;;          (?/r3 owl/onProperty obo/realizes)
;;          (?/r3 owl/someValuesFrom ?/inheres)

;;          ;;create a new anonymous class that is CHEBI drug role
;;          ;;   that inheres in this specific drug
;;          ;;   so that it can be realized by the restriction above
;;          (?/r4 rdf/type owl/Restriction)
;;          (?/r4 owl/onProperty obo/inheres_in)
;;          (?/r4 owl/someValuesFrom ?/drug)
         
;;          (?/inheres rdfs/subClassOf obo/CHEBI_23888) ;drug role
;;          (?/inheres rdfs/subClassOf ?/r4)

;;          ;;make the interaction be necessarily part of these 3 restrictions
;;          ;;still need to add a sufficient definition
;;          (?/interaction rdfs/subClassOf ?/r1)
;;          (?/interaction rdfs/subClassOf ?/r2)
;;          (?/interaction rdfs/subClassOf ?/r3))

;;   :body
;;   ((_/fv0 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1TypeDataField1)
;;    (_/fv0 obo/IAO_0000219 "Gene")
;;    (_/record obo/BFO_0000051 _/fv0)

;;    (_/record obo/BFO_0000051 _/fv1)
;;    (_/fv1 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2TypeDataField1)
;;    (_/fv1 obo/IAO_0000219 "Disease")

;;    (_/record obo/BFO_0000051 _/fv2)
;;    (_/fv2 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1)
;;    (_/fv2 obo/IAO_0000219 _/geneid)

;;    (_/record obo/BFO_0000051 _/fv3)
;;    (_/fv3 kiao/hasTemplate iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1)
;;    (_/fv3 obo/IAO_0000219 _/diseaseid)

;;    (_/diseaseid obo/IAO_0000219 ?/disease)

;;    (_/geneid obo/IAO_0000219 ?/gene)
;;    (?/gene [rdfs/subClassOf clojure.core/*] ?/gorgporv)
;;    (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))



;;   :reify (?/interaction
;;           ?/r1
;;           ?/r2
;;           ?/r3
;;           ?/r4

;;           ?/inheres)

;;   :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}

          
;;   }



