;; -------------------------------------------
;; --------- Entity Type Propogation ---------
;; -------------------------------------------
`{:name "entity-type-propogation"
  :description "this rule the propigates ice identifier relation types onto the actualy entities"
  :head ((?/entity rdfs/subClassOf ?/sc))
  :body ((_/ice kiao/denotesSubClassOf ?/sc)
         (_/ice obo/IAO_0000219 ?/entity))} ; IAO:denotes
