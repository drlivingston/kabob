
;; obo/MI_0000 ;psi-mi "molecular interaction"
;; obo/INO_0000002 ;interaction

;; obo/GO_ ; complex

`{:name "irefweb-interaction-2-proteins"
  :head ((?/interaction rdfs/subClassOf obo/MI_0000) ;interaction

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r2 owl/someValuesFrom ?/protein2)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/r2))

  :body
  (;; just look this up once we'll need it later
   (_/Ftypename kiao/hasTemplate iaoirefweb/IRefWebInteractorType_interactorTypeNameDataField1)
   (_/Ftypename obo/IAO_0000219 "protein") ; denotes
   (_/Rtype obo/BFO_0000051 _/Ftypename) ; has_part
   (_/Rtype kiao/hasTemplate iaoirefweb/IRefWebInteractorTypeSchema1)


   ;; find all interactions with 2 partners
   (_/fvnum kiao/hasTemplate iaoirefweb/IRefWebInteraction_numParticipantsDataField1)
   ;;(_/fvnum obo/IAO_0000219 ["2" xsd/integer])
   (_/fvnum obo/IAO_0000219 2) ; denotes
   (_/interactionrecord obo/BFO_0000051 _/fvnum) ; has_part

   ;; get top record
   (_/psimi obo/BFO_0000051 _/interactionrecord) ; has_part

   ;;get two distinct interactors
   (_/psimi obo/BFO_0000051 ?/Rinteractor1) ; has_part
   (?/Rinteractor1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (_/psimi obo/BFO_0000051 ?/Rinteractor2) ; has_part
   (?/Rinteractor2 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (!= ?/Rinteractor1 ?/Rinteractor2)

   ;;all two part interactions should be protein and protein but verify
   ;; reuse type from initial lookup
   (?/Rinteractor1 obo/BFO_0000051 _/Rtype) ; has_part
   (?/Rinteractor2 obo/BFO_0000051 _/Rtype) ; has_part

   ;;get the first interactor ID and then BIO entity
   (?/Rinteractor1 obo/BFO_0000051 _/FID1) ; has_part
   (_/FID1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/FID1 obo/IAO_0000219 _/ID1) ; denotes
   (_/ID1 obo/IAO_0000219 ?/protein1) ; denotes

   ;;get the second interactor ID and then BIO entity
   (?/Rinteractor2 obo/BFO_0000051 _/FID2) ; has_part
   (_/FID2 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/FID2 obo/IAO_0000219 _/ID2) ; denotes
   (_/ID2 obo/IAO_0000219 ?/protein2) ; denotes
   )


 ;;need a better way to reify / sort the restrictions to form the interaction

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/r1 ?/r2)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
 }

`{:name "irefweb-reify-complex-and-interaction"
  :head ((?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
         (?/complex rdfs/subClassOf obo/GO_0032991)  ;macromolecular complex
         (?/interaction kro/resuts_in_the_formation_of ?/complex)

         (?/complexID obo/IAO_0000219 ?/complex) ; complexID denotes complex
         ;;(?/complexID obo/IAO_0000142 ?/interaction) ;the complex id mentions the interaction? no
         )
  :body
  ((_/Ftypename kiao/hasTemplate iaoirefweb/IRefWebInteractorType_interactorTypeNameDataField1)
   (_/Ftypename obo/IAO_0000219 "protein complex") ; denotes
   (_/Rtype obo/BFO_0000051 _/Ftypename) ; has_part
   (_/Rtype kiao/hasTemplate iaoirefweb/IRefWebInteractorTypeSchema1)

   ;;get all the interactor records that are protein complex stand-ins
   (?/Rinteractor obo/BFO_0000051 _/Rtype) ; has_part

   ;;get the complex ID
   (?/Rinteractor obo/BFO_0000051 _/FID1) ; has_part
   (_/FID1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/FID1 obo/IAO_0000219 ?/complexID)) ; denotes

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/complexID)
                          :ns "kbio" :prefix "I_"}]
          [?/complex {:ln (:sha-1 "complex" ?/complexID)
                      :ns "kbio" :prefix "C_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }


`{:name "irefweb-complex-and-interaction-participant"
  :head (;(?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
         ;(?/complex rdfs/subClassOf obo/GO_0032991)  ;macromolecular complex
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/BFO_0000051) ; has_part
         (?/r2 owl/someValuesFrom ?/protein)

         (?/interaction rdfs/subClassOf ?/r1)
         (?/complex rdfs/subClassOf ?/r2))
  ;;how to possibly extend these to sufficient definitions?

  :body
  (;; just look this up once we'll need it later
   (_/FtypenameP kiao/hasTemplate iaoirefweb/IRefWebInteractorType_interactorTypeNameDataField1)
   (_/FtypenameP obo/IAO_0000219 "protein") ; denotes
   (_/RtypeP obo/BFO_0000051 _/FtypenameP) ; has_part
   (_/RtypeP kiao/hasTemplate iaoirefweb/IRefWebInteractorTypeSchema1)

   ;;get the protein complex type
   (_/FtypenamePC kiao/hasTemplate iaoirefweb/IRefWebInteractorType_interactorTypeNameDataField1)
   (_/FtypenamePC obo/IAO_0000219 "protein complex")  ; denotes
   (_/RtypePC obo/BFO_0000051 _/FtypenamePC) ; has_part
   (_/RtypePC kiao/hasTemplate iaoirefweb/IRefWebInteractorTypeSchema1)

   ;;get all the protein complex interaction records
   (?/RinteractorPC obo/BFO_0000051 _/RtypePC) ; has_part
   (_/psimi obo/BFO_0000051 ?/RinteractorPC) ; has_part

   ;;get the one protein mentioned in this record
   (_/psimi obo/BFO_0000051 ?/RinteractorP) ; has_part
   (?/RinteractorP obo/BFO_0000051 _/RtypeP) ; has_part

   ;;get the first interactor ID and then BIO entity
   (?/RinteractorPC obo/BFO_0000051 _/FID1) ; has_part
   (_/FID1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/FID1 obo/IAO_0000219 ?/complexID) ; denotes

   ;;get the second interactor ID and then BIO entity
   (?/RinteractorP obo/BFO_0000051 _/FID2) ; has_part
   (_/FID2 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/FID2 obo/IAO_0000219 _/ID2) ; denotes
   (_/ID2 obo/IAO_0000219 ?/protein)) ; denotes

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/complexID)
                          :ns "kbio" :prefix "I_"}]
          [?/complex {:ln (:sha-1 "complex" ?/complexID)
                      :ns "kbio" :prefix "C_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
 }
