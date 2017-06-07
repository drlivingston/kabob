;; ------------------------------------------------------------
;; ---------  relation consolidation via shared label ---------
;; ------------------------------------------------------------
`{:name          "relation_consolidation_by_shared_label"
  :title         "creates exactMatch mappings between relations that share the same exact label"
  :head          ((?/p1 skos/exactMatch ?/p2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select distinct ?p1 ?p2 {
                             ?p1 rdf:type owl:ObjectProperty .
                             ?p1 rdfs:label ?label1 .
                             ?p2 rdf:type owl:ObjectProperty .
                             ?p2 rdfs:label ?label2 .
                             FILTER (?p1 != ?p2 && str(?label1) = str(?label2) && STR(IRI(?p1)) < STR(IRI(?p2)))
                             }"
  }
