;; -------------------------------------------
;; --------- Missing ncRNA Assertion ---------
;; -------------------------------------------
`{:name "step-hcc_missing-snorna-gen"
  :description "This rule asserts a snoRNA entity for genes that are known to code for them."
  :head ((?/rna_bioentity rdfs/subClassOf ?/rna_type)
          (?/rna_bioentity rdfs/label ?/rna_label)
          (?/rna_bioentity rdfs/subClassOf ?/has_gene_template_r)
          (?/has_gene_template_r owl/someValuesFrom ?/gene_bioentity)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r rdf/type owl/Restriction)) ; SO:protein_coding_gene
  :reify ([?/has_gene_template_r {:ln (:restriction)
                                  :ns "kbio" :prefix "RS_"}]
           [?/rna_bioentity {:ln (:sha-1 ?/gene_bioentity ?/rna_type)
                             :ns "kbio" :prefix "B_"}])
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>

  select ?gene_bioentity ?rna_type ?has_gene_template ?rna_label {

          {
           select ?rna_type {
              kice:SO_0000275 obo:IAO_0000219 ?rna_type . # OBO:denotes
              filter (?rna_type != obo:SO_0000275) # OBO:rRNA
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
           ?gene_bioentity rdfs:subClassOf kice:temp_snorna_entity .
           optional {?gene_bioentity rdfs:label ?label}
  bind(coalesce(?label, \"Unnamed gene\") as ?gene_name)
  bind(concat(\"snoRNA encoded by gene: \", str(?gene_name)) as ?rna_label)
           # Exclude any genes that are already the template for an ncRNA
           minus {
                  ?has_gene_template_r owl:someValuesFrom ?gene_bioentity .
                  ?has_gene_template_r owl:onProperty ?has_gene_template .
                  ?ncrna rdfs:subClassOf ?has_gene_template_r .
                  ?ncrna rdfs:subClassOf ?rna_type .
                  }
           }"

  }