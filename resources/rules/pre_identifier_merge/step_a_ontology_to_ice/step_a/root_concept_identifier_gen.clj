;; -------------------------------------------
;; --------- Root Concept Assignment ---------
;; -------------------------------------------
`{:name "root-concept-identifier-gen"
  :description "This rule creates an identifier for each root ontology class and types it as a root concept identifier (IAO_EXT_0000190)"
  :head ((?/id rdf/type ccp/IAO_EXT_0000190) ;; ccp:ontology root concept identifer
         (?/id obo/IAO_0000219 ?/root)) ;; obo:denotes
  :reify ([?/id {:ln (:localname ?/root)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "select ?root {
  ?root rdf:type owl:Class .
  # return classes that are subClassOf owl:Thing or classes that are not subClassOf anything
  filter ((exists {?root rdfs:subClassOf owl:Thing}) || (not exists {?root rdfs:subClassOf ?class}))
  # exclude all deprecated classes
  filter (not exists {?root owl:deprecated true})
  # exclude any blank nodes
  filter (!contains(str(?root), '/bnode/'))
  # exclude the oboInOwl:ObsoleteClass class
  minus {?root rdfs:label 'Obsolete Class'}
  }"
}