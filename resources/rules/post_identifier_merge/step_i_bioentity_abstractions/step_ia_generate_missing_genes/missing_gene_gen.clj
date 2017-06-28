;; --------------------------------------------------
;; --------- Missing gene entity generation ---------
;; --------------------------------------------------
`{:name "missing-gene-gen"
  :description "This rule generates a gene bioentity for any protein that is not connected to a gene via the has_gene_template restriction"
  :head ((?/protein_coding_gene rdfs/subClassOf ?/protein_coding_gene_bioentity)
          (?/taxon_r rdf/type owl/Restriction)
          (?/taxon_r owl/onProperty ?/only_in_taxon) ; RO:only_in_taxon
          (?/taxon_r owl/someValuesFrom ?/taxon)
          (?/protein_coding_gene rdfs/subClassOf ?/taxon_r)
          (?/has_gene_template_r rdf/type owl/Restriction)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r owl/someValuesFrom ?/protein_coding_gene)
          (?/protein_missing_gene rdfs/subClassOf ?/has_gene_template_r))
  :reify ([?/taxon_r {:ln (:restriction)
                          :ns "ccp" :prefix "RS_"}]
           [?/has_gene_template_r {:ln (:restriction)
                                            :ns "ccp" :prefix "RS_"}]
           [?/protein_coding_gene {:ln (:sha-1 ?/protein_missing_gene "missing")
                             :ns "ccp" :prefix "B_"}])
  :sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select distinct ?protein_missing_gene ?has_gene_template ?protein_coding_gene_bioentity ?only_in_taxon ?taxon {
         ?protein_missing_gene rdfs:subClassOf* ?protein .
         # to keep from climbing the protein hierarchy too high we require the protein to have a taxon
         ?protein_missing_gene rdfs:subClassOf ?taxon_r .
         ?taxon_r rdf:type owl:Restriction .
         ?taxon_r owl:onProperty ?only_in_taxon .
         ?taxon_r owl:someValuesFrom ?taxon .
         ?protein_id obo:IAO_0000219 ?protein_missing_gene .
         # exclude proteins that already have a has_gene_template restriction (likely imported via pr.owl)
         filter not exists {
                            ?protein_missing_gene rdfs:subClassOf ?has_gene_template_r .
                            ?has_gene_template_r rdf:type owl:Restriction .
                            ?has_gene_template_r owl:onProperty ?has_gene_template .
                            }


         {
          select ?only_in_taxon {
                                 ccp:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                                 filter (?only_in_taxon != obo:RO_0002160) .
                                 }
          }

                               {
                                select ?protein {
                                                 ccp:CHEBI_36080 obo:IAO_0000219 ?protein .
                                                 filter (?protein != obo:CHEBI_36080) .
                                                 }
                                }

         {
          select ?has_gene_template ?hgt_id {
                                             <http://ccp.ucdenver.edu/obo/ext/pr#has_gene_template> obo:IAO_0000219 ?has_gene_template .
                                                     ?hgt_id obo:IAO_0000219 ?has_gene_template .
                                                     filter (?has_gene_template != <http://purl.obolibrary.org/obo/pr#has_gene_template>) .
                                             }
          }
                               {
                                # get the kabob bioentity that corresponds to SO:gene
                                          select ?protein_coding_gene_bioentity {
                                                                                 ccp:SO_0001217 obo:IAO_0000219 ?protein_coding_gene_bioentity . # OBO:denotes
                                   filter (?protein_coding_gene_bioentity != obo:SO_0001217) # OBO:gene
                                   }
                                                                                 }
                                }"
  }

