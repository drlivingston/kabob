`{:name        "validation_rdf-list-missing-rdf-rest-EXPECT-0"
  :description "Tests RDF list nodes to ensure each has a rdf:rest"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                ?list rdf:first ?first .
                minus {?list rdf:rest ?rest}
                }"
  }
