;; -------------------------------------------------------
;; --------- Uniprot Protein Entrez Gene Mapping ---------
;; -------------------------------------------------------
`{:name "uniprot-protein-entrez-gene-mapping"
  :description "This rule maps uniprot proteins to entrez genes"
  :head (
          ;; create has indirect template restriction
          (?/indirect_template_restriction rdf/type owl/Restriction)
          (?/indirect_template_restriction owl/onProperty ?/has_gene_template)
          (?/indirect_template_restriction owl/someValuesFrom ?/gene)
          (?/protein rdfs/subClassOf ?/indirect_template_restriction))
  :reify ([?/indirect_template_restriction {:ln (:restriction)
                                            :ns "ccp" :prefix "RS_"}])
  :sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  SELECT ?protein ?gene ?has_gene_template
  WHERE {
         ?record rdf:type ccp:IAO_EXT_0000235 .  # CCP:UniProt_identifier_mapping_record
         ?record obo:BFO_0000051 ?uniprot_identifier_field_value .
         ?uniprot_identifier_field_value rdf:type ccp:IAO_EXT_0000239 .  # CCP:UniProt_identifier_mapping_record__UniProt_accession_identifier_field_value
         ?uniprot_identifier_field_value rdf:type ?uniprot_identifier .
         ?uniprot_identifier obo:IAO_0000219 ?protein .
         ?record obo:BFO_0000051 ?ncbi_gene_identifier_field_value .
         ?ncbi_gene_identifier_field_value rdf:type ccp:IAO_EXT_0000242 .  # CCP:UniProt_identifier_mapping_record__Entrez_gene_identifier_field_value
         ?ncbi_gene_identifier_field_value rdf:type ?ncbi_gene_identifier .
         ?ncbi_gene_identifier obo:IAO_0000219 ?gene .
         # don't duplicate existing has_gene_template restrictions
         filter not exists {
                     ?protein rdfs:subClassOf ?r .
                     ?r rdf:type owl:Restriction .
                     ?r owl:onProperty ?has_gene_template .
                     ?r owl:someValuesFrom ?gene .
         }

         {
          select ?has_gene_template {
                                     ?has_gene_template_id obo:IAO_0000219 obo_pr:has_gene_template .
                                     ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
                                     # ensure it's a kabob bioentity (not an obo bioentity)
                                     filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/obo/ext/'))
                                     }
          }
         }"
  }

