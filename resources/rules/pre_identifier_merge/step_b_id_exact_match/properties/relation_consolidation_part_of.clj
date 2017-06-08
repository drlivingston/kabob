;; --------------------------------------------------
;; --------- part_of relation consolidation ---------
;; --------------------------------------------------
`{:name          "relation_consolidation_part_of"
  :title         "creates exactMatch mappings between part_of relations"
  :head          ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select distinct ?id1 ?id2 {
?p1 rdf:type owl:ObjectProperty .
{{?p1 rdfs:label 'part of'} UNION {?p1 rdfs:label 'part_of'} UNION {?p1 rdfs:label 'part of'@en} UNION {?p1 rdfs:label 'part_of'@en}} .
?p2 rdf:type owl:ObjectProperty .
FILTER (?p1 != ?p2 && STR(IRI(?p1)) < STR(IRI(?p2)))
{{?p2 rdfs:label 'part of'} UNION {?p2 rdfs:label 'part_of'} UNION {?p2 rdfs:label 'part of'@en} UNION {?p2 rdfs:label 'part_of'@en}} .
?id1 obo:IAO_0000219 ?p1 .
?id2 obo:IAO_0000219 ?p2 .
}"
  }
