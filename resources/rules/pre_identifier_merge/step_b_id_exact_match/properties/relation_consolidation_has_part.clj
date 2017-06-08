;; --------------------------------------------------
;; --------- has_part relation consolidation ---------
;; --------------------------------------------------
`{:name          "relation_consolidation_has_part"
  :title         "creates exactMatch mappings between has_part relations"
  :head          ((?/id1 skos/exactMatch ?/id2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select distinct ?id1 ?id2 {
?p1 rdf:type owl:ObjectProperty .
{{?p1 rdfs:label 'has part'} UNION {?p1 rdfs:label 'has_part'} UNION {?p1 rdfs:label 'has part'@en} UNION {?p1 rdfs:label 'has_part'@en}} .
?p2 rdf:type owl:ObjectProperty .
FILTER (?p1 != ?p2 && STR(IRI(?p1)) < STR(IRI(?p2)))
{{?p2 rdfs:label 'has part'} UNION {?p2 rdfs:label 'has_part'} UNION {?p2 rdfs:label 'has part'@en} UNION {?p2 rdfs:label 'has_part'@en}} .
?id1 obo:IAO_0000219 ?p1 .
?id2 obo:IAO_0000219 ?p2 .
}"
  }
