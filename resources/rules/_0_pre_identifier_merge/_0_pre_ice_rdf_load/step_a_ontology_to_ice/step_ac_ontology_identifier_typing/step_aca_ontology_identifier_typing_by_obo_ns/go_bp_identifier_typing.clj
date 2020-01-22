;; -----------------------------------------------
;; --------- go-bp identifier typing ---------
;; -----------------------------------------------
`{:name "step-ac_go-bp-identifier-typing"
  :description "This rule specifically the GO biological process identifier"
  :head ((?/id rdfs/subClassOf ccp/IAO_EXT_0000103) ; CCP:go_biological_process_ontology_identifier
          (?/id rdfs/subClassOf ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id {
                  ?c oboInOwl:hasOBONamespace 'biological_process' .
                  ?id obo:IAO_0000219 ?c .
                  ?id rdfs:subClassOf ccp:IAO_EXT_0000088 .
                  }"
  }