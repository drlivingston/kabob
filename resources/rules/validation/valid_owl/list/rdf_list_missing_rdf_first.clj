`{:name        "validation_rdf-list-missing-rdf-first-EXPECT-0"
  :description "Tests RDF list nodes to ensure each has a rdf:first"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                ?list rdf:rest ?rest .
                minus {?list rdf:first ?first}
                }"
  }
