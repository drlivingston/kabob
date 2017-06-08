;; ------------------------------------------------------------
;; ---------  relation consolidation via shared label ---------
;; ------------------------------------------------------------
`{:name          "object-property-consolidation-by-shared_label"
  :title         "creates exactMatch mappings between ObjectProperty identifiers whose properties share the same exact label"
  :head          ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select distinct ?id1 ?id2 {
                             ?p1 rdf:type owl:ObjectProperty .
                             ?p1 rdfs:label ?label1 .
                             ?p2 rdf:type owl:ObjectProperty .
                             ?p2 rdfs:label ?label2 .
                             FILTER (?p1 != ?p2 && replace(str(?label1),'_',' ') = replace(str(?label2),'_',' ') && STR(IRI(?p1)) < STR(IRI(?p2)))
                             ?id1 obo:IAO_0000219 ?p1 .
                             ?id2 obo:IAO_0000219 ?p2 .
                             }"
  }
