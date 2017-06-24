;; ------------------------------------------------
;; --------- Localization Infers Location ---------
;; ------------------------------------------------
`{:name "localization-infers-location_bio_to_bio"
  :description "This rule asserts that a protein is located in a cellular component if a localization process results in the localization of a protein and has target end location of a cellular component"
  :head (
         ;; create a located in restriction
         (?/located_in_restriction rdf/type owl/Restriction)
         (?/located_in_restriction owl/onProperty obo/RO_0001025) ; RO:located_in
         (?/located_in_restriction owl/someValuesFrom ?/go_cellular_component)
         (?/protein rdfs/subClassOf ?/located_in_restriction))
  
  :body (
         ;; create a transports or maintains localization of restriction
         (?/located_in_restriction rdfs/subClassOf obo/GO_0051179) ; GO:localization
         (?/located_in_restriction rdfs/subClassOf ?/trans_main_loc_restriction)
         (?/trans_main_loc_restriction rdf/type owl/Restriction)
         (?/trans_main_loc_restriction owl/onProperty obo/RO_0002313) ; RO:transports_or_maintains_localization_of
         (?/trans_main_loc_restriction owl/someValuesFrom ?/protein) 
          
         ;; ;; create a has_target_end_location restriction
         (?/located_in_restriction rdfs/subClassOf ?/target_end_restriction)
         (?/target_end_restriction rdf/type owl/Restriction)
         (?/target_end_restriction owl/onProperty obo/RO_0002339) ; RO:has_target_end_location
         (?/target_end_restriction owl/someValuesFrom ?/go_cellular_component))
  
  :reify ([?/located_in_restriction {:ln (:restriction)
                :ns "ccp" :prefix "R_"}])
  
  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }