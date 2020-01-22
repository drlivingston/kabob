`{:name        "validation_restriction-missing-owl-valuefrom-EXPECT-0"
  :description "tests that each owl restriction with an owl:onProperty property has at least one other property that is not on:Property"
  :head        ()
  :body "select ?r
               WHERE {
                      ?r rdf:type owl:Restriction .
                         minus {?r owl:someValuesFrom ?v}
                         minus {?r owl:allValuesFrom ?v}
                         minus {?r owl:qualifiedCardinality ?v}
                         minus {?r owl:minQualifiedCardinality ?v}
                         minus {?r owl:maxQualifiedCardinality ?v}
                         minus {?r owl:minCardinality ?v}
                         minus {?r owl:maxCardinality ?v}
                         minus {?r owl:cardinality ?v}
                         minus {?r owl:onClass ?v}
                         minus {?r owl:hasSelf ?v}
                         minus {?r owl:hasValue ?v}
                      }"

  }
