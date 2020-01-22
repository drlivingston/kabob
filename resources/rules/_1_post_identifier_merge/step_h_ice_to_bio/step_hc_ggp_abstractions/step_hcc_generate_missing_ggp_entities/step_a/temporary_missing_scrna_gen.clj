;; -------------------------------------------
;; --------- Missing ncRNA Assertion ---------
;; -------------------------------------------
`{:name "step-hcc_temporary_missing-scrna-gen"
  :description "This rule asserts a scRNA entity for genes that are known to code for them."
  :head ((?/gene_bioentity rdfs/subClassOf kice/temp_scrna_entity))
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>

  select ?gene_bioentity {

           ?type_field_value rdf:type ccp:IAO_EXT_0000884 . #ccp:NCBI_gene_info_record__type_of_gene_field_value
           ?type_field_value rdfs:label ?type .
           filter (?type = \"scRNA\"@en)
           ?record obo:BFO_0000051 ?type_field_value .
           ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_info_record
           ?record obo:BFO_0000051 ?gene_field_value .
           ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
           ?gene_field_value rdf:type ?gene_id .
           ?gene_id obo:IAO_0000219 ?gene_bioentity .
           }"

  }