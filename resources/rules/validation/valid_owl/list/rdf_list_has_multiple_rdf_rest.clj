`{:name        "validation_rdf-list-has-multiple-rest-EXPECT-0"
  :description "Tests RDF list nodes for multiple rdf:rest properties"
  :head        ()
  :body "select ?list
               { ?list rdf:type rdf:List .
                ?list rdf:first ?first .
                ?list rdf:rest ?rest .
                ?list rdf:rest ?rest2 .
                filter (?rest != ?rest2)
                }"
  }
