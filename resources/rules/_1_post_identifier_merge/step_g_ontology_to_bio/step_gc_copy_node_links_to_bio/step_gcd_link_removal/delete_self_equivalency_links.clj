`{:name        "delete-self-equivalency-links"
  :description "The combination of merging identifiers using owl:equivalentClass links (see step b) and then transferring the ontology links to bioworld results in some classes that are owl:equivalentClass themselves. This rule deletes those relations. "
  :head        ()
  :body        "prefix obo: <http://purl.obolibrary.org/obo/>
                 delete { graph ?g {?c ?p ?c}} where {
                   select ?c (owl:equivalentClass as ?p) ?g {
                       graph ?g {
                          ?c owl:equivalentClass ?c .
                       }
                   }
                 }"
  }
