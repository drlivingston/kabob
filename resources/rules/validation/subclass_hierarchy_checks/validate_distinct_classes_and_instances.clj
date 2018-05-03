;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "validation_distinct-classes-and-instances-EXPECT-0"
  :description   "This rule produces no triples, but instead validates that there are no things in kabob that are both
                  a class and an instance. If this rule reports >0 hits, then there is an issue."
  :head          ()

  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    SELECT distinct ?instance
   WHERE {
          ?instance rdf:type ?cls .
          ?instance rdfs:subClassOf ?super .
   }"

  }