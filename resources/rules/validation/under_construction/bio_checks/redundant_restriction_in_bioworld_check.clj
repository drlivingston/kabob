`{:name        "validation_redundant-restriction-check-EXPECT-0"
  :description "tests that a classes that inherits from a restriction comprised of bioentities does not inherit from any other identical restrictions"
  :head        '()
  :body "SELECT ?c { ?c rdfs:subClassOf ?r1 .
                   ?r1 rdf:type owl:Restriction .
                   ?r1 owl:onProperty ?p1 .
                   ?r1 owl:someValuesFrom ?v1 .
                   ?v1 rdfs:subClassOf ?b1 .
                   ?ice1 obo:IAO_0000219 ?b1 .
                   ?c rdfs:subClassOf ?r2 .
                   ?r2 rdf:type owl:Restriction .
                   filter (?r1 != ?r2)
                   ?r2 owl:onProperty ?p2 .
                   ?r2 owl:someValuesFrom ?v2 .
                   ?v2 rdfs:subClassOf ?b2 .
                   ?ice2 obo:IAO_0000219 ?b2 .
                   filter (?p1 = ?p2)
                   filter (?b1 = ?b2)
                   }"




  }
