;; ice identifiers have relations that attempt to encode type
;;   this rule propigates those types onto the actualy entities
`{:name "propigate-denotesSubClassOf"
  :head ((?/entity rdfs/subClassOf ?/sc))
  :body ((_/ice kiao/denotesSubClassOf ?/sc)
         (_/ice obo/IAO_0000219 ?/entity))}
