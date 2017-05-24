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
		  minus{?root rdfs:subClassOf ?class}
		  minus{?root owl:deprecated true} 
		  filter(!contains(str(?root), '/bnode/')) 
		}"
}