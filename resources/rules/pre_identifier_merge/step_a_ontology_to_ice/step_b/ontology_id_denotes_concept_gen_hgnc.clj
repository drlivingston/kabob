;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept; this rule handles the hgnc URIs used by pr.owl
`{:name "ontology-id-denotes-concept-gen-hgnc"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept with the HGNC namespace."
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; denotes
         (?/id rdf/type ccp/IAO_EXT_0000088)) ; ontology concept identifier
  :reify ([?/id {:ln (:regex ":" "_" ?/concept_id)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept ?concept_id {
                  ?root_id rdf:type ccp:IAO_EXT_0000190 . # ccp:ontology root concept identifier
                  ?root_id obo:IAO_0000219 ?root_class . # obo:denotes
                  ?ontology_concept rdfs:subClassOf* ?root_class .
                  ?ontology_concept oboInOwl:id ?concept_id .
                  minus {?ontology_concept owl:deprecated true} .
                  minus {?ontology_concept rdf:type ccp:IAO_EXT_0000190} . # ccp:ontology root concept identifier
                  # include only concepts with the HGNC namespace
                  filter (contains (str(?ontology_concept), 'http://www.genenames.org/cgi-bin/gene_symbol_report'))
                  }"
  }