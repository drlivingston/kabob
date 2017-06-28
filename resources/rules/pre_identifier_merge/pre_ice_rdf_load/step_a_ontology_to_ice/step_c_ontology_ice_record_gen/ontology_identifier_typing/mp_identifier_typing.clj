;; -----------------------------------------------
;; --------- mp identifier typing ---------
;; -----------------------------------------------
`{:name "mp-identifier-typing"
  :description "This rule specifically the mammalian phentype ontology identifier"
  :head ((?/id rdf/type ccp/IAO_EXT_0000206)) ; CCP:mammalian_phenotype_ontology_identifier
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id {
                  ?id rdf:type ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  ?id obo:IAO_0000219 ?c . # IAO:denotes
                  ?c oboInOwl:hasOBONamespace 'MPheno.ontology'
                  }"
  }