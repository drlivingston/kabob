;; -----------------------------------------------
;; --------- go-bp identifier typing ---------
;; -----------------------------------------------
`{:name "go-bp-identifier-typing"
  :description "This rule specifically the GO biological process identifier"
  :head ((?/id rdf/type ccp/IAO_EXT_0000103)) ; CCP:go_biological_process_ontology_identifier
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id {
                  ?id rdf:type ccp:IAO_EXT_0000088 .
                  ?id obo:IAO_0000219 ?c .
                  ?c oboInOwl:hasOBONamespace 'biological_process'
                  }"
  }