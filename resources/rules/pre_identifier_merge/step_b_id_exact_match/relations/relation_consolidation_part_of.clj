;; --------------------------------------------------
;; --------- part_of relation consolidation ---------
;; --------------------------------------------------
`{:name          "relation_consolidation_part_of"
  :title         "creates exactMatch mappings between part_of relations"
  :head          ((?/p1 skos/exactMatch ?/p2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select distinct ?p1 ?p2 {
?p1 rdf:type owl:ObjectProperty .
{{?p1 rdfs:label 'part of'} UNION {?p1 rdfs:label 'part_of'} UNION {?p1 rdfs:label 'part of'@en} UNION {?p1 rdfs:label 'part_of'@en}} .
?p2 rdf:type owl:ObjectProperty .
FILTER (?p1 != ?p2 && STR(IRI(?p1)) < STR(IRI(?p2)))
{{?p2 rdfs:label 'part of'} UNION {?p2 rdfs:label 'part_of'} UNION {?p2 rdfs:label 'part of'@en} UNION {?p2 rdfs:label 'part_of'@en}} .
}"
  }
