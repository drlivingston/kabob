;; -------------------------------------------------------------
;; --------- Protein Entity Direct SubClass Assertion ----------
;; -------------------------------------------------------------
`{:name "step-ha_protein-entity-direct-subclass-assertions"
  :description "This rule asserts a direct subClassOf relationship for every protein to the protein root class. Doing so avoids the use of *'s in downstream queries."
  :head ((?/protein_sc rdfs/subClassOf ?/protein))
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
                  PREFIX franzOption_clauseReorderer: <franz:identity>
                  select ?protein_sc ?protein {

                    {
                     select ?protein {
                                      kice:CHEBI_36080 obo:IAO_0000219 ?protein .
                                      filter (?protein != obo:CHEBI_36080) .
                                      }
                     }

                    ?protein_sc rdfs:subClassOf+ ?protein .
                    minus { ?protein_sc rdfs:subClassOf ?protein . }

                    }"
  }