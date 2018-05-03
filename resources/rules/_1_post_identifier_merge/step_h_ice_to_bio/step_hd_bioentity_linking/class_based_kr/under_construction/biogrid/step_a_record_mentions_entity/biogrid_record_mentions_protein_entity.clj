;; ----------------------------------------------------------
;; --------- BioGRID record mentions protein entity ---------
;; ----------------------------------------------------------
`{:name          "biogrid-record-mentions-protein-entity"
  :description   "This rule connects biogrid records to the protein entities the indirectly refer to using IAO:mentions."
  :head          ((?/record obo/IAO_0000142  ?/bioentity)) ;; obo:mentions
  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    select ?bioentity ?record {

     {
                        select ?has_gene_template {
                                                   ?has_gene_template_id obo:IAO_0000219 obo_pr:has_gene_template .
                                                   ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
                                                   # ensure it's a kabob bioentity (not an obo bioentity)
                                                                         filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/kabob/bio/'))
                                                   }
                        }
      {
       select ?protein_coding_gene {
                                    kice:SO_0001217 obo:IAO_0000219 ?protein_coding_gene .
                                    filter (?protein_coding_gene != obo:SO_0001217) # OBO:protein_coding_gene
          }
                                    }
              {
               select ?protein {
                                kice:CHEBI_36080 obo:IAO_0000219 ?protein .
                                filter (?protein != obo:CHEBI_36080) .
                                }
               }

       ?record rdf:type ccp:IAO_EXT_0001777 . # ccp:BIOGRID_PROTEIN_INTERACTION_RECORD
       ?record obo:BFO_0000051 ?ncbi_gene_identifier_field_value .
       {?ncbi_gene_identifier_field_value rdf:type ccp:IAO_EXT_0001807}
       union
       {?ncbi_gene_identifier_field_value rdf:type ccp:IAO_EXT_0001808}
       ?ncbi_gene_identifier_field_value rdf:type ?ncbi_gene_identifier .
       ?ncbi_gene_identifier obo:IAO_0000219 ?gene .
       ?gene rdfs:subClassOf ?protein_coding_gene .
       ?has_gene_template_r owl:someValuesFrom ?gene .
       ?has_gene_template_r owl:onProperty ?has_gene_template .
       ?bioentity rdfs:subClassOf ?has_gene_template_r .
       ?bioentity rdfs:subClassOf ?protein .
       }"

  }