;; ---------------------------------------------------
;; --------- owl:equivalentClass id mappings ---------
;; ---------------------------------------------------
`{:name "step-b_equivalent-class-idmappings"
  :description "create skos/exactMatch links between identifiers that denote named classes that are linked via owl:equivalentClass, e.g. CL_0000000 (cell) & GO_0005623 (cell)"
  :head ((?/id1 skos/exactMatch ?/id2))
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id1 ?id2 {
                    ?c1 owl:equivalentClass ?c2 .
                    ?id1 obo:IAO_0000219 ?c1 . # IAO:denotes
                    ?id2 obo:IAO_0000219 ?c2 . # IAO:denotes
                  }"
  }