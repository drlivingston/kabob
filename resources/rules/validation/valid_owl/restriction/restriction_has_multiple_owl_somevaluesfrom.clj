`{:name        "validation_restriction-has-multiple-owl-somevaluesfrom-EXPECT-0"
  :description "tests that each owl restriction has no more than one owl:somevaluesfrom property"
  :head        ()
  :body "select ?r
               { ?r rdf:type owl:Restriction .
                ?r owl:someValuesFrom ?value .
                ?r owl:someValuesFrom ?value2 .
                filter (?value != ?value2)
                }"

  }
