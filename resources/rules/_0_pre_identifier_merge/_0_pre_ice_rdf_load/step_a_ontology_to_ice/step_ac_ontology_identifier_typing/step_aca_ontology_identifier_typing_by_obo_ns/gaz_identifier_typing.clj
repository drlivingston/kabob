;; -----------------------------------------------
;; --------- pr identifier typing ---------
;; -----------------------------------------------
`{:name "step-ac_gaz-identifier-typing"
  :description "This rule specifically the protein ontology identifier"
  :head ((?/id rdfs/subClassOf ccp/IAO_EXT_0001704)) ; CCP:gazetteer_ontology_identifier
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id {
                  ?c obo:IAO_0000412 obo:gaz.owl .
                  ?id obo:IAO_0000219 ?c . # IAO:denotes
                  ?id rdfs:subClassOf ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  }"
  }