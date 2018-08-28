`{:name        "delete-temporary-links"
  :description "Deletes temporary links created in step gcb."
  :head        ()
  :body        "prefix obo: <http://purl.obolibrary.org/obo/>
                prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                 delete { graph ?g {?s ?p ?o}} where {
                    select ?s (ccp:temporary_link as ?p) ?o ?g {
                      graph ?g {
                        ?s ccp:temporary_link ?o .
                      }
                    }
                  }"
  }
