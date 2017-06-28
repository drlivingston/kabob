;; -----------------------------------------------
;; --------- bto identifier typing ---------
;; -----------------------------------------------
`{:name "bto-identifier-typing"
  :description "This rule specifically the brenda tissue ontology identifier"
  :head ((?/id rdf/type ccp/IAO_EXT_0000207)) ; CCP:brenda_ontology_identifier
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id {
                  ?id rdf:type ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  ?id obo:IAO_0000219 ?c . # IAO:denotes
                  ?c oboInOwl:hasOBONamespace 'BrendaTissueOBO'
                  }"
  }