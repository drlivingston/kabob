`{:name        "validation_restriction-missing-owl-onproperty-EXPECT-0"
  :description "tests that each owl restriction has at least one owl:onProperty property"
  :head ()
  :body "select ?r
               { ?r rdf:type owl:Restriction .
                minus {?r owl:onProperty ?prop}
                }"

  }
