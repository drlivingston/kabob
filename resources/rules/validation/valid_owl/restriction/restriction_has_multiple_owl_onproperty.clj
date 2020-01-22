`{:name        "validation_restriction-has-multiple-owl-onproperty-EXPECT-0"
  :description "tests that each owl restriction has no more than one owl:onProperty property"
  :head        ()
  :body "select ?r
               { ?r rdf:type owl:Restriction .
                ?r owl:onProperty ?prop .
                ?r owl:onProperty ?prop2 .
                filter (?prop != ?prop2)
                }"

  }
