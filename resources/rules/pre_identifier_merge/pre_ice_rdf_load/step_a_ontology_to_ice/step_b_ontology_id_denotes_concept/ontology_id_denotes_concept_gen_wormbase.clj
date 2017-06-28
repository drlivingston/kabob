;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
`{:name "ontology-id-denotes-concept-gen-wormbase"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept with the WormBase namespace."
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; IAO:denotes
         (?/id rdf/type ccp/IAO_EXT_0000088) ; CCP:WormBase_concept_identifier
         (?/id rdf/type ccp/IAO_EXT_0000337)) ; CCP:CGNC_gene_identifier
  :reify ([?/id {:ln (:regex "WormBase:" "WORMBASE_" ?/concept_id)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept ?concept_id {
                  ?ontology_concept oboInOwl:id ?concept_id .
                  # include only concepts with the WormBase namespace
                  filter (contains (str(?ontology_concept), 'http://www.wormbase.org/species/c_elegans/gene'))
                  minus {?ontology_concept owl:deprecated true} .
                  minus {?ontology_concept rdf:type ccp:IAO_EXT_0000190} . #ccp:ontology root concept identifier
                  ?ontology_concept rdfs:subClassOf* ?root_class .
                  ?root_id obo:IAO_0000219 ?root_class . # IAO:denotes
                  ?root_id rdf:type ccp:IAO_EXT_0000190 . # ccp:ontology_root_concept_identifier
  }"
  }