;; ------------------------------------------------------
;; --------- Protein Coding Gene Type Assertion ---------
;; ------------------------------------------------------
`{:name "step-hab_biological-region-ncbi-gene-type-assertions"
  :description "This rule asserts the protein coding gene type to NCBI genes marked as 'protein-coding', excluding those
                genes that are already subClassOF SO:protein_coding_gene (as likely asserted by the protein ontology)."
  :head ((?/gene_bioentity rdfs/subClassOf ?/gene_type))
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
                  PREFIX franzOption_clauseReorderer: <franz:identity>

                  select ?gene_bioentity ?gene_type {

                          {
                            select ?gene_type {
                              kice:SO_0001411 obo:IAO_0000219 ?gene_type . # OBO:denotes
                              filter (?gene_type != obo:SO_0001411) # OBO:biological_region
                              }
                          }

                          ?gene_bioentity rdfs:subClassOf kice:temp_bio_region .
                        minus {
                           ?gene_bioentity rdfs:subClassOf ?gene_type .
                        }
                           }"

  }