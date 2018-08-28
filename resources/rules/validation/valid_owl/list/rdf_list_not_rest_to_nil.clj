`{:name        "validation_rdf-list-not-rest-to-nil-EXPECT-0"
  :description "Tests RDF list nodes to ensure that lists end using rdf:rest and then rdf:nil"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                ?list ?p rdf:nil .
                filter (?p != rdf:rest)
                }"
  }
