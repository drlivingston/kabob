;; ----------------------------------------------
;; --------- Pseudo Gene Type Assertion ---------
;; ----------------------------------------------
`{:name "step-hab_pseudogene-ncbi-gene-type-assertion"
  :description "This rule asserts the pseudogene type to NCBI genes marked as 'pseudo'."
  :head ((?/gene_bioentity rdfs/subClassOf ?/gene_type)) ; SO:pseudogene
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>

  select ?gene_bioentity ?gene_type {

                                     {
                                      select ?gene_type {
                                                         kice:SO_0000336 obo:IAO_0000219 ?gene_type . # OBO:denotes
                                         filter (?gene_type != obo:SO_0000336) # OBO:pseudogene
                                         }
                                                         }
                                      ?gene_bioentity rdfs:subClassOf kice:temp_pseudogene .
                                      # Exclude any genes that are already subClassOf the gene_type
                                      # These relations are imported via the Protein Ontology
                                      minus {
                                             ?gene_bioentity rdfs:subClassOf ?gene_type .
                                             }
                                      }"

   }