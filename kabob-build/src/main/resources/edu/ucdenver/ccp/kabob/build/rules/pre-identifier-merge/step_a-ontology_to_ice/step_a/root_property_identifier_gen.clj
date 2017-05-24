;; --------------------------------------------------------
;; --------- Top-Level Object Property Assignment ---------
;; --------------------------------------------------------
`{:name "top-level-property-identifier-gen"
  :description "This rule creates an identifier for each top-level object property and types it as a top-level object property identifier (IAO_EXT_0000308)"
  :head ((?/id rdf/type ccp/IAO_EXT_0000308) ;; ccp:top-level object property identifer
         (?/id obo/IAO_0000219 ?/top_level_property)) ;; obo:denotes
  :reify ([?/id {:ln (:localname ?/top_level_property)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "select ?top_level_property {
		  ?top_level_property rdf:type owl:ObjectProperty .
		  minus{?top_level_property rdfs:subPropertyOf ?class}
		  minus{?top_level_property owl:deprecated true} 
		}"
}