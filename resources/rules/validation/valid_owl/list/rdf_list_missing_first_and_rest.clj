`{:name        "validation_rdf-list-missing-rdf-first-and-rest-EXPECT-0"
  :description "Tests RDF list nodes to ensure each has an rdf:first and an rdf:rest"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                minus {?list rdf:first ?first}
                minus {?list rdf:rest ?rest}
                }"
  }
