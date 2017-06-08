;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept
`{:name "ontology-id-denotes-concept-gen-obo"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept with the OBO namespace.
  The Protein Ontology references some classes that use non-OBO namespaces. Source-specific rules have been written for those
  namespaces and these rules can be found in the same directory as this more general OBO rule."
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; denotes
         (?/id rdf/type ccp/IAO_EXT_0000088)) ; ontology concept identifier
  :reify ([?/id {:ln (:localname ?/ontology_concept)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?ontology_concept {
                  ?root_id rdf:type ccp:IAO_EXT_0000190 . # ccp:ontology root concept identifier
                  ?root_id obo:IAO_0000219 ?root_class . # obo:denotes
                  ?ontology_concept rdfs:subClassOf* ?root_class .
                  minus {?ontology_concept owl:deprecated true} .
                  minus {?ontology_concept rdf:type ccp:IAO_EXT_0000190} . # ccp:ontology root concept identifier
                  # include only concepts with the OBO namespace
                  filter (contains (str(?ontology_concept), 'http://purl.obolibrary.org/obo/'))
                  # exclude ICE world concepts (IAO, CCP extension ontology, OA ontology)
                  filter (!contains (str(?ontology_concept), 'obo/IAO_'))
                  }"
  }