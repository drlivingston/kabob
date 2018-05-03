;; ------------------------------------------------------
;; --------- Protein Coding Gene Type Assertion ---------
;; ------------------------------------------------------
`{:name "step-hab_protein-coding-ncbi-gene-type-assertions"
  :description "This rule asserts the protein coding gene type to NCBI genes marked as 'protein-coding', excluding those
                genes that are already subClassOF SO:protein_coding_gene (as likely asserted by the protein ontology)."
  :head ((?/gene_bioentity rdfs/subClassOf ?/protein_coding_gene)) ; SO:protein_coding_gene
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
                  PREFIX franzOption_clauseReorderer: <franz:identity>

                  select ?gene_bioentity ?protein_coding_gene {

                          {
                           # get the kabob bioentity that corresponds to SO:protein_coding_gene
                                     select ?protein_coding_gene {
                                        kice:SO_0001217 obo:IAO_0000219 ?protein_coding_gene . # OBO:denotes
                                        filter (?protein_coding_gene != obo:SO_0001217) # OBO:protein_coding_gene
                              }
                          }
                          ?gene_bioentity rdfs:subClassOf kice:temp_protein_coding_gene .
                           # Exclude any genes that are already subClassOf SO:protein_coding_gene
                           # These relations are imported via the Protein Ontology
                           minus {
                                  ?gene_bioentity rdfs:subClassOf ?protein_coding_gene .
                                  }
                           }"

  }