;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept
`{:name "ontology-id-denotes-concept-gen"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept."
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; denotes
         (?/id rdf/type ccp/IAO_EXT_0000088)) ; ontology concept identifier
  :reify ([?/id {:ln (:localname ?/ontology_concept)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?ontology_concept {
                  ?root_id rdf:type ccp:IAO_EXT_0000190 . # ccp:ontology root concept identifier
                  ?root_id obo:IAO_0000219 ?root_class . # obo:denotes
                  ?ontology_concept rdfs:subClassOf* ?root_class .
                  minus {?ontology_concept owl:deprecated true} .
                  minus {?ontology_concept rdf:type ccp:IAO_EXT_0000190} . # ccp:ontology root concept identifier
                  # exclude ICE world concepts (IAO, CCP extension ontology, OA ontology)
                  filter (!contains (str(?ontology_concept), 'ext/IAO_'))
                  filter (!contains (str(?ontology_concept), 'obo/IAO_'))
                  filter (!contains (str(?ontology_concept), 'http://www.w3.org/ns/oa#'))
                  }"
  }