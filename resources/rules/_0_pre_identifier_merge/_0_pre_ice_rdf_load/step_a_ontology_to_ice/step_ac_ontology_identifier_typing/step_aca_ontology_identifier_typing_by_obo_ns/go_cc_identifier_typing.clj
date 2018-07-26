;; -----------------------------------------------
;; --------- go-cc identifier typing ---------
;; -----------------------------------------------
`{:name "step-ac_go-cc-identifier-typing"
  :description "This rule specifically the GO cellular component identifier"
  :head ((?/id rdfs/subClassOf ccp/IAO_EXT_0000200) ; CCP:go_cellular_component_ontology_identifier
          (?/id rdfs/subClassOf ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id {
                  ?c oboInOwl:hasOBONamespace 'cellular_component' .
                  ?id obo:IAO_0000219 ?c . #IAO_denotes
                  ?id rdfs:subClassOf ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  }"
  }