`{:name        "validation_redundant-restriction-check-EXPECT-0"
  :description "tests that a class that inherits from a restriction does not inherit from any other identical restrictions"
  :head        ()
  :body "select ?c { ?r1 rdf:type owl:Restriction .
               ?r1 owl:onProperty ?p1 .
               ?r1 owl:someValuesFrom ?v1 .
               ?c rdfs:subClassOf ?r1 .
               ?r2 owl:onProperty ?p1 .
               filter (?r1 != ?r2)
               ?r2 rdf:type owl:Restriction .
               ?r2 owl:someValuesFrom ?v1 .
               ?c rdfs:subClassOf ?r2 .
               }"

  }
