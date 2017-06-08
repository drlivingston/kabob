;; ------------------------------------------------------------
;; ---------  relation consolidation via shared label ---------
;; ------------------------------------------------------------
`{:name          "class-consolidation-by-shared_label"
  :title         "creates exactMatch mappings between Class identifiers whose classes share the same exact label"
  :head          ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id1 ?id2 {
                    ?c1 oboInOwl:hasOBONamespace ?ns1 .
                    ?c1 rdf:type owl:Class .
                    ?c1 rdfs:label ?label .
                    ?c2 rdfs:label ?label .
                    ?c2 oboInOwl:hasOBONamespace ?ns2 .
                    ?c2 rdf:type owl:Class .
                    FILTER (?c1 != ?c2 && STR(IRI(?c1)) < STR(IRI(?c2)))
                    ?id1 obo:IAO_0000219 ?c1 .
                    ?id2 obo:IAO_0000219 ?c2 .
                  }"
  }
