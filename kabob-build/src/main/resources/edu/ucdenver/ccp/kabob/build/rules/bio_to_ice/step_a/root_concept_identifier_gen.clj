;; -------------------------------------------
;; --------- Root Concept Assignment ---------
;; -------------------------------------------
;; create ICE URI's for each term in the ontology
`{:name "root-concept-identifier-gen"
  :head ((?/id rdf/type ccp/IAO_EXT_0000190) ; ontology root concept identifer
         (?/id obo/IAO_0000219 ?/root)) ; denotes
  :sparql-string "select ?root {
								  ?root rdf:type owl:Class .
								  minus{?root rdfs:subClassOf ?class}
								  minus{?root owl:deprecated true}  
								         }"
  :reify ([?/id {:ln (:localname ?/root)
                 :ns "ccp" :prefix "" :suffix ""}])}