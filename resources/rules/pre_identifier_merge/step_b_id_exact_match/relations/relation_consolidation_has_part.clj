;; --------------------------------------------------
;; --------- has_part relation consolidation ---------
;; --------------------------------------------------
`{:name          "relation_consolidation_has_part"
  :title         "creates exactMatch mappings between has_part relations"
  :head          ((?/p1 skos/exactMatch ?/p2))
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
select distinct ?p1 ?p2 {
?p1 rdf:type owl:ObjectProperty .
{{?p1 rdfs:label 'has part'} UNION {?p1 rdfs:label 'has_part'} UNION {?p1 rdfs:label 'has part'@en} UNION {?p1 rdfs:label 'has_part'@en}} .
?p2 rdf:type owl:ObjectProperty .
FILTER (?p1 != ?p2 && STR(IRI(?p1)) < STR(IRI(?p2)))
{{?p2 rdfs:label 'has part'} UNION {?p2 rdfs:label 'has_part'} UNION {?p2 rdfs:label 'has part'@en} UNION {?p2 rdfs:label 'has_part'@en}} .
}"
  }
