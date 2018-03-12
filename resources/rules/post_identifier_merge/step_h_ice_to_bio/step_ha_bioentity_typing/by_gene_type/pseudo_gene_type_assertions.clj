;; ----------------------------------------------
;; --------- Pseudo Gene Type Assertion ---------
;; ----------------------------------------------
`{:name "pseudogene-ncbi-gene-type-assertion"
  :description "This rule asserts the pseudogene type to NCBI genes marked as 'pseudo'."
  :head ((?/gene_bioentity rdfs/subClassOf ?/gene_type)) ; SO:pseudogene
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>

  select ?gene_bioentity ?gene_type {

                                     {
                                      select ?gene_type {
                                                         ccp:SO_0000336 obo:IAO_0000219 ?gene_type . # OBO:denotes
                                         filter (?gene_type != obo:SO_0000336) # OBO:pseudogene
                                         }
                                                         }
                                             ?type_field_value rdf:type ccp:IAO_EXT_0000884 . #ccp:NCBI_gene_info_record__type_of_gene_field_value
                                                 ?type_field_value rdfs:label ?type .
                                      filter (?type = \"pseudo\"@en)
                                      ?record obo:BFO_0000051 ?type_field_value .
                                      ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_info_record
                                                 ?record obo:BFO_0000051 ?gene_field_value .
                                      ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
                                                 ?gene_field_value rdf:type ?gene_id .
                                      ?gene_id obo:IAO_0000219 ?gene_bioentity .
                                      # Exclude any genes that are already subClassOf the gene_type
                                      # These relations are imported via the Protein Ontology
                                      minus {
                                             ?gene_bioentity rdfs:subClassOf ?gene_type .
                                             }
                                      }"

   }