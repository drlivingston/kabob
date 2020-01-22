`{:name        "validation_rdf-list-has-multiple-first-EXPECT-0"
  :description "Tests RDF list nodes for multiple rdf:first properties"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                ?list rdf:first ?first .
                ?list rdf:rest ?rest .
                ?list rdf:first ?first2 .
                filter (?first != ?first2)
                }"
  }
