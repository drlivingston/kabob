;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept
`{:name "ontology-id-denotes-concept-gen"
  :head ((?/id obo/IAO_0000219 ?/sc) ; denotes
         (?/id rdf/type ccp/IAO_EXT_0000088)) ; ontology concept identifier
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?sc {
                  ?root_id rdf:type ccp:IAO_EXT_0000190 .
                  ?root_id obo:IAO_0000219 ?root_class .
                  ?sc rdfs:subClassOf* ?root_class .
                  }"
  :reify ([?/id {:ln (:localname ?/sc)
                 :ns "ccp" :prefix "" :suffix ""}])}