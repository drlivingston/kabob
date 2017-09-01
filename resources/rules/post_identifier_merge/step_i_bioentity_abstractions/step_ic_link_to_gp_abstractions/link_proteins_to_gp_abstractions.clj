;; ------------------------------------------------------------
;; --------- link proteins to gp abstractions ---------
;; ------------------------------------------------------------

;;                     GGPV
;;                    / |  \
;;                   /  |   \
;;                 GGV GGP  GPGPV
;;                / |  / \  /   \
;;               VG | /   GP    VGP
;;                  |/
;;                  G
`{:name          "link-proteins-to-gp-abstractions"
  :description   "For each protein in the KB, find its gene and then link to its corresponding gene_product_abstraction."
  :head          ((?/p rdfs/subClassOf ?/gp_abstraction))
  :sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                    select distinct ?p ?gp_abstraction {
                         ?p rdfs:subClassOf* ?protein .
                         ?p rdfs:subClassOf ?has_gene_template_r .
                         ?has_gene_template_r rdf:type owl:Restriction .
                         ?has_gene_template_r owl:onProperty ?has_gene_template .
                         ?has_gene_template_r owl:someValuesFrom ?g .

                         # link from the gene (?g) to its GP abstraction node
                         ?g rdfs:subClassOf ?ggp_abstraction .
                         ?ggp_abstraction rdf:type ccp:IAO_EXT_0001715 . # ccp:gene_or_gene_product_abstraction
                         ?gp_abstraction rdfs:subClassOf ?ggp_abstraction .
                         ?gp_abstraction rdf:type ccp:IAO_EXT_0001716 . # ccp:gene_product_abstraction

                         {
                          select ?protein {
                                           ccp:CHEBI_36080 obo:IAO_0000219 ?protein .
                                           filter (?protein != obo:CHEBI_36080) .
                                           }
                          }

                         {
                          select ?has_gene_template {
                             <http://ccp.ucdenver.edu/obo/ext/pr#has_gene_template> obo:IAO_0000219 ?has_gene_template .
                             filter (?has_gene_template != <http://purl.obolibrary.org/obo/pr#has_gene_template>) .
                             }
                          }
                  }"
  }

