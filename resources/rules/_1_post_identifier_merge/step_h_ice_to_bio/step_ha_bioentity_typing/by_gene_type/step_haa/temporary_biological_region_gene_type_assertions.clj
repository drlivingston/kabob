;; ------------------------------------------------------
;; --------- Protein Coding Gene Type Assertion ---------
;; ------------------------------------------------------
`{:name "step-haa_temporary-biological-region-ncbi-gene-type-assertions"
  :description "This rule asserts the protein coding gene type to NCBI genes marked as 'protein-coding', excluding those
                genes that are already subClassOF SO:protein_coding_gene (as likely asserted by the protein ontology)."
  :head ((?/gene_bioentity rdfs/subClassOf kice/temp_bio_region))
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
                  PREFIX franzOption_clauseReorderer: <franz:identity>

                  select ?gene_bioentity {

                           ?type_field_value rdf:type ccp:IAO_EXT_0000884 . #ccp:NCBI_gene_info_record__type_of_gene_field_value
                           ?type_field_value rdfs:label ?type .
                           filter (?type = \"biological-region\"@en)
                           ?record obo:BFO_0000051 ?type_field_value .
                           ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_info_record
                           ?record obo:BFO_0000051 ?gene_field_value .
                           ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
                           ?gene_field_value rdf:type ?gene_id .
                           ?gene_id obo:IAO_0000219 ?gene_bioentity .

                           }"

  }