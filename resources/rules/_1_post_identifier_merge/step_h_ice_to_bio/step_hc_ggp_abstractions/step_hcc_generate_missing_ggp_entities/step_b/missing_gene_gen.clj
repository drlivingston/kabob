;; --------------------------------------------------
;; --------- Missing gene entity generation ---------
;; --------------------------------------------------
`{:name "step-hcc_missing-gene-gen"
  :description "This rule generates a gene bioentity for any protein that is not connected to a gene via the has_gene_template restriction"
  :head ((?/protein_coding_gene rdfs/subClassOf ?/protein_coding_gene_bioentity)
          (?/taxon_r rdf/type owl/Restriction)
          (?/taxon_r owl/onProperty ?/only_in_taxon) ; RO:only_in_taxon
          (?/taxon_r owl/someValuesFrom ?/taxon)
          (?/protein_coding_gene rdfs/subClassOf ?/taxon_r)
          (?/protein_coding_gene rdfs/label ?/protein_coding_gene_label)
          (?/has_gene_template_r rdf/type owl/Restriction)
          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
          (?/has_gene_template_r owl/someValuesFrom ?/protein_coding_gene)
          (?/protein_missing_gene rdfs/subClassOf ?/has_gene_template_r))
  :reify ([?/taxon_r {:ln (:restriction)
                          :ns "kbio" :prefix "RS_"}]
           [?/has_gene_template_r {:ln (:restriction)
                                            :ns "kbio" :prefix "RS_"}]
           [?/protein_coding_gene {:ln (:sha-1 ?/protein_missing_gene "missing")
                             :ns "kbio" :prefix "B_"}])
  :body "prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    select distinct ?protein_missing_gene ?has_gene_template ?protein_coding_gene_bioentity ?only_in_taxon ?taxon ?protein_coding_gene_label {
       {
        select ?only_in_taxon {
                               kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                               filter (?only_in_taxon != obo:RO_0002160) .
                               }
        }

       {
        select ?protein {
                         kice:CHEBI_36080 obo:IAO_0000219 ?protein .
                         filter (?protein != obo:CHEBI_36080) .
                         }
        }

       {
        select ?has_gene_template {
                                   ?has_gene_template_id obo:IAO_0000219 <http://purl.obolibrary.org/obo/pr#has_gene_template> .
                                                         ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
                                                         # ensure it's a kabob bioentity (not an obo bioentity)
                                   filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/kabob/bio/'))
                                   }
        }

       {
        # get the kabob bioentity that corresponds to SO:gene
                  select ?protein_coding_gene_bioentity {
                                                         kice:SO_0001217 obo:IAO_0000219 ?protein_coding_gene_bioentity . # OBO:denotes
           filter (?protein_coding_gene_bioentity != obo:SO_0001217) # OBO:gene
           }
                                                         }

        ?protein_missing_gene rdfs:subClassOf+ ?protein .
        ## to keep from climbing the protein hierarchy too high we require the protein to have a taxon
        ?protein_missing_gene rdfs:subClassOf ?taxon_r .
        ?taxon_r owl:onProperty ?only_in_taxon .
        ?taxon_r owl:someValuesFrom ?taxon .
        ?protein_id obo:IAO_0000219 ?protein_missing_gene .
        optional {?protein_missing_gene rdfs:label ?label}

        bind(coalesce(?label, \"Unnamed protein\") as ?protein_name)
        bind(concat(\"Gene for protein: \", str(?protein_name)) as ?protein_coding_gene_label)
        ## exclude proteins that already have a has_gene_template restriction (likely imported via pr.owl)
        minus {
               ?protein_missing_gene rdfs:subClassOf ?has_gene_template_r .
               ?has_gene_template_r owl:onProperty ?has_gene_template .
               }
        }"
  }

