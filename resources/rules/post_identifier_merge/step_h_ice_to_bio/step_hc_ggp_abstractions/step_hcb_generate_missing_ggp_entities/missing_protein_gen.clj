;; --------------------------------------------------
;; --------- Missing protein entity generation ---------
;; --------------------------------------------------
`{:name "missing-protein-gen"
  :description "This rule generates a protein bioentity for any protein-coding gene that is not connected to a protein via the has_gene_template restriction"
  :head ((?/has_gene_template_r rdf/type owl/Restriction)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r owl/someValuesFrom ?/gene_missing_protein)
          (?/protein_bioentity rdfs/subClassOf ?/has_gene_template_r)
          (?/protein_bioentity rdfs/subClassOf ?/protein))
  :reify ([?/has_gene_template_r {:ln (:restriction)
                                   :ns "kbio" :prefix "RS_"}]
           [?/protein_bioentity {:ln (:sha-1 ?/gene_missing_protein "missing")
                                   :ns "kbio" :prefix "B_"}])
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
  PREFIX franzOption_clauseReorderer: <franz:identity>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select ?gene_missing_protein ?has_gene_template ?protein {

    {
     select ?has_gene_template {
                                <http://ccp.ucdenver.edu/obo/ext/pr#has_gene_template> obo:IAO_0000219 ?has_gene_template .
                                        filter (?has_gene_template != <http://purl.obolibrary.org/obo/pr#has_gene_template>) .
                                }
     }
   {
    select ?protein_coding_gene {
                                 ccp:SO_0001217 obo:IAO_0000219 ?protein_coding_gene .
                                 filter (?protein_coding_gene != obo:SO_0001217)
                                 }
    }
    {
     select ?protein {
                      ccp:CHEBI_36080 obo:IAO_0000219 ?protein .
                      filter (?protein != obo:CHEBI_36080) .
                      }
     }

    ?gene_missing_protein rdfs:subClassOf ?protein_coding_gene .
    # exclude genes that already participate in a has_gene_template restriction
    minus {
           ?has_gene_template_r owl:someValuesFrom ?gene_missing_protein .
           ?has_gene_template_r owl:onProperty ?has_gene_template .
           }

    }"
  }

