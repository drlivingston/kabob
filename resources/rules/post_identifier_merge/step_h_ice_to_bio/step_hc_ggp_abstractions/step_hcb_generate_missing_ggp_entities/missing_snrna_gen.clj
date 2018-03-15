;; -------------------------------------------
;; --------- Missing ncRNA Assertion ---------
;; -------------------------------------------
`{:name "missing-snrna-gen"
  :description "This rule asserts a snRNA entity for genes that are known to code for them."
  :head ((?/rna_bioentity rdfs/subClassOf ?/rna_type)
          (?/rna_bioentity rdfs/subClassOf ?/has_gene_template_r)
          (?/has_gene_template_r owl/someValuesFrom ?/gene_bioentity)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r rdf/type owl/Restriction)) ; SO:protein_coding_gene
  :reify ([?/has_gene_template_r {:ln (:restriction)
                                  :ns "kbio" :prefix "RS_"}]
           [?/rna_bioentity {:ln (:sha-1 ?/gene_bioentity ?/rna_type)
                             :ns "kbio" :prefix "B_"}])
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>

  select ?gene_bioentity ?rna_type ?has_gene_template {

          {
           select ?rna_type {
              ccp:SO_0000274 obo:IAO_0000219 ?rna_type . # OBO:denotes
              filter (?rna_type != obo:SO_0000274) # OBO:rRNA
              }
                             }
           {
                              select ?has_gene_template {
                                                         ?has_gene_template_id obo:IAO_0000219 obo_pr:has_gene_template .
                                                         ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
                                                         # ensure it's a kabob bioentity (not an obo bioentity)
                                                                               filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/kabob/bio/'))
                                                         }
                              }
           ?type_field_value rdf:type ccp:IAO_EXT_0000884 . #ccp:NCBI_gene_info_record__type_of_gene_field_value
           ?type_field_value rdfs:label ?type .
           filter (?type = \"snRNA\"@en)
           ?record obo:BFO_0000051 ?type_field_value .
           ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_info_record
           ?record obo:BFO_0000051 ?gene_field_value .
           ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
           ?gene_field_value rdf:type ?gene_id .
           ?gene_id obo:IAO_0000219 ?gene_bioentity .
           # Exclude any genes that are already the template for an ncRNA
           minus {
                  ?has_gene_template_r owl:someValuesFrom ?gene_bioentity .
                  ?has_gene_template_r owl:onProperty ?has_gene_template .
                  ?ncrna rdfs:subClassOf ?has_gene_template_r .
                  ?ncrna rdfs:subClassOf ?rna_type .
                  }
           }"

  }