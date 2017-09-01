;; ------------------------------------------------------------
;; --------- gene abstraction generation ---------
;; ------------------------------------------------------------

;;                     GGPV
;;                    / |  \
;;                   /  |   \
;;                 GGV GGP  GPGPV
;;                / |  / \  /   \
;;               VG | /   GP    VGP
;;                  |/
;;                  G
`{:name          "gene-abstraction-gen"
  :description   "For each gene in the KB, this rule generates an abstraction hierarchy that groups gene, gene products, and variants."
  :head          ((?/g rdfs/subClassOf ?/ggp_abstraction)
                   (?/ggp_abstraction rdf/type ccp/IAO_EXT_0001715) ;; ccp:gene_or_gene_product_abstraction
                   (?/g rdfs/subClassOf ?/ggv_abstraction)
                   (?/ggv_abstraction rdf/type ccp/IAO_EXT_1720) ;; ccp:gene_or_gene_variant_abstraction
                   (?/ggv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                   (?/ggpv_abstraction rdf/type ccp/IAO_EXT_0001718) ;; ccp:gene_or_gene_product_or_variant_abstraction
                   (?/ggp_abstraction rdf/subClassOf ?/ggpv_abstraction)
                   (?/vg_abstraction rdfs/subClassOf ?/ggv_abstraction)
                   (?/vg_abstraction rdf/type ccp/IAO_EXT_0001717) ;; ccp:variant_gene_abstraction
                   (?/gp_abstraction rdfs/subClassOf ?/ggp_abstraction)
                   (?/gp_abstraction rdf/type ccp/IAO_EXT_0001716) ;; ccp:gene_product_abstraction
                   (?/gp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                   (?/gpgpv_abstraction rdf/type ccp/IAO_EXT_0001721) ;; ccp:gene_product_or_gene_product_variant_abstraction
                   (?/gpgpv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                   (?/vgp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                   (?/vgp_abstraction rdf/type ccp/IAO_EXT_0001719)) ;; ccp:variant_gene_product_abstraction

  :reify         ([?/ggp_abstraction {:ln (:localname ?/g)
                          :ns "ccp" :prefix "GGP_" :suffix ""}]
                   [?/gp_abstraction {:ln (:localname ?/g)
                          :ns "ccp" :prefix "GP_" :suffix ""}]
                   [?/vgp_abstraction {:ln (:localname ?/g)
                           :ns "ccp" :prefix "VGP_" :suffix ""}]
                   [?/vg_abstraction {:ln (:localname ?/g)
                          :ns "ccp" :prefix "VG_" :suffix ""}]
                   [?/ggpv_abstraction {:ln (:localname ?/g)
                            :ns "ccp" :prefix "GGPV_" :suffix ""}]
                   [?/ggv_abstraction {:ln (:localname ?/g)
                            :ns "ccp" :prefix "GGV_" :suffix ""}]
                   [?/gpgpv_abstraction {:ln (:localname ?/g)
                            :ns "ccp" :prefix "GPGPV_" :suffix ""}])
  :sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                    select distinct ?g {
                         ?g rdfs:subClassOf* ?gene .
                         # to keep from climbing the gene hierarchy we require the genes to have a taxon
                         ?g rdfs:subClassOf ?taxon_r .
                         ?taxon_r rdf:type owl:Restriction .
                         ?taxon_r owl:onProperty ?only_in_taxon .

                           {
                            select ?gene {
                                             ccp:SO_0000704 obo:IAO_0000219 ?gene .
                                             filter (?gene != obo:SO_0000704) .
                                             }
                            }

                    }"
  }

