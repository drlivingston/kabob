;; --------------------------------------------
;; --------- Delete duplicate triples ---------
;; --------------------------------------------
`{:name "delete-duplicate-triples"
  :description "This directive creates no new triples, but deletes all duplicate triples in the repository"
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix franzOption_memoryLimit: <franz:85g>
                  delete { graph ?g1 {?s ?p ?o}} where {
                               select ?g1 ?s ?p ?o  {
                                   graph ?g1 {
                                        ?s ?p ?o
                                   }
                                   graph ?g2 {
                                        ?s ?p ?o
                                   }
                                   filter (?g1 != ?g2)
                               }
                  }"
  }