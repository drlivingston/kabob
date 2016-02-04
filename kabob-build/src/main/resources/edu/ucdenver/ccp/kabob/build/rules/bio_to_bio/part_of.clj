
;; DEFINITION: If a localization-process results-in-the-localization-of object-X and 
;;             has-target-end-location object-Y, then X part-of Y
`{:name "localization-infers-part_of"
  :head (
          (?/r rdf/type owl/Restriction)
          (?/r owl/onProperty obo/BFO_0000050) ; part_of
          (?/r owl/someValuesFrom ?/gocc)

          (?/protein rdfs/subClassOf ?/r)
          )
  :body (
          (?/loc rdfs/subClassOf obo/GO_0051179) ;localization
          (?/loc rdfs/subClassOf ?/of1)
          (?/of1 rdf/type owl/Restriction)
          (?/of1 owl/onProperty obo/RO_0002313) ;transports or maintains localization of
          (?/of1 owl/someValuesFrom ?/protein) 
          
          (?/loc rdfs/subClassOf ?/to1)
          (?/to1 rdf/type owl/Restriction)
          (?/to1 owl/onProperty obo/RO_0002339) ; has target end location
          (?/to1 owl/someValuesFrom ?/gocc)
          )
  :reify ([?/r {:ln (:restriction)
                :ns "kbio" :prefix "R_"}]
           )
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }