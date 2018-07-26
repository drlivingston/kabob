`{:name        "delete-temporary-biological-region-gene-type-links"
  :description "Deletes temporary links created in step haa."
  :head        ()
  :body        "prefix obo: <http://purl.obolibrary.org/obo/>
                prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                 delete { graph ?g {?s rdfs:subClassOf ?o}} where {
                    select ?s (kice:temp_protein_coding_gene as ?o) ?g {
                      graph ?g {
                        ?s rdfs:subClassOf kice:temp_protein_coding_gene .
                      }
                    }
                  }"
  }
