;; --------------------------------------------------
;; --------- Missing protein entity generation ---------
;; --------------------------------------------------
`{:name "step-hcc_missing-protein-gen"
  :description "This rule generates a protein bioentity for any protein-coding gene that is not connected to a protein via the has_gene_template restriction"
  :head ((?/has_gene_template_r rdf/type owl/Restriction)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r owl/someValuesFrom ?/gene_missing_protein)
          (?/protein_bioentity rdfs/subClassOf ?/has_gene_template_r)
          (?/protein_bioentity rdfs/subClassOf ?/protein)
          (?/protein_bioentity rdfs/label ?/protein_label))
  :reify ([?/has_gene_template_r {:ln (:restriction)
                                   :ns "kbio" :prefix "RS_"}]
           [?/protein_bioentity {:ln (:sha-1 ?/gene_missing_protein "missing")
                                   :ns "kbio" :prefix "B_"}])
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select ?gene_missing_protein ?has_gene_template ?protein ?protein_label {

        {
         select ?has_gene_template {
                                    ?has_gene_template_id obo:IAO_0000219 <http://purl.obolibrary.org/obo/pr#has_gene_template> .
                                                          ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
                                                          # ensure it's a kabob bioentity (not an obo bioentity)
                                    filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/kabob/bio/'))
                                    }
         }
        {
         select ?protein_coding_gene {
                                      kice:SO_0001217 obo:IAO_0000219 ?protein_coding_gene .
                                      filter (?protein_coding_gene != obo:SO_0001217)
                                      }
         }
        {
         select ?protein {
                          kice:CHEBI_36080 obo:IAO_0000219 ?protein .
                          filter (?protein != obo:CHEBI_36080) .
                          }
         }

        ?gene_missing_protein rdfs:subClassOf ?protein_coding_gene .
        optional {?gene_missing_protein rdfs:label ?label}
        bind(coalesce(?label, \"Unnamed gene\") as ?gene_name)
        bind(concat(\"Protein encoded by gene: \", str(?gene_name)) as ?protein_label)

        # exclude genes that already participate in a has_gene_template restriction
        minus {
               ?has_gene_template_r owl:someValuesFrom ?gene_missing_protein .
               ?has_gene_template_r owl:onProperty ?has_gene_template .
               }

        }"
  }

