;; -----------------------------------------------
;; --------- cell-ontology identifier typing ---------
;; -----------------------------------------------
`{:name "step-ac_cell-ontology-identifier-typing-ns-cell"
  :description "This rule specifically the cell ontology identifier"
  :head ((?/id rdfs/subClassOf ccp/IAO_EXT_0000202) ; CCP:cell_ontology_identifier
          (?/id rdfs/subClassOf ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id {
                  {{?c oboInOwl:hasOBONamespace 'cell' .} union {?c oboInOwl:hasOBONamespace 'cl' .}}
                  ?id obo:IAO_0000219 ?c . # IAO:denotes
                  ?id rdfs:subClassOf ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  }"
  }