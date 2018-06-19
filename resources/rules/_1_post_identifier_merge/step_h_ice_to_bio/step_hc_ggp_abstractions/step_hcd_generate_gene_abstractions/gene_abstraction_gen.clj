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
`{:name          "step-hcd_gene-abstraction-gen"
  :description   "For each gene in the KB, this rule generates an abstraction hierarchy that groups gene, gene products, and variants."
  :head          ((?/g rdfs/subClassOf ?/ggp_abstraction)
                   (?/ggp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001715) ;; ccp:gene_or_gene_product_abstraction
                   (?/ggp_abstraction rdfs/label ?/ggp_label)
                   (?/g rdfs/subClassOf ?/ggv_abstraction)
                   (?/ggv_abstraction rdfs/subClassOf ccp/IAO_EXT_1720) ;; ccp:gene_or_gene_variant_abstraction
                   (?/ggv_abstraction rdfs/label ?/ggv_label)
                   (?/ggv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                   (?/ggpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001718) ;; ccp:gene_or_gene_product_or_variant_abstraction
                   (?/ggpv_abstraction rdfs/label ?/ggpv_label)
                   (?/ggp_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                   (?/vg_abstraction rdfs/subClassOf ?/ggv_abstraction)
                   (?/vg_abstraction rdfs/subClassOf ccp/IAO_EXT_0001717) ;; ccp:variant_gene_abstraction
                   (?/vg_abstraction rdfs/label ?/vg_label)
                   (?/gp_abstraction rdfs/subClassOf ?/ggp_abstraction)
                   (?/gp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001716) ;; ccp:gene_product_abstraction
                   (?/gp_abstraction rdfs/label ?/gp_label)
                   (?/gp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                   (?/gpgpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001721) ;; ccp:gene_product_or_gene_product_variant_abstraction
                   (?/gpgpv_abstraction rdfs/label ?/gpgpv_label)
                   (?/gpgpv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                   (?/vgp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                   (?/vgp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001719) ;; ccp:variant_gene_product_abstraction
                   (?/vgp_abstraction rdfs/label ?/vgp_label))

  :reify         ([?/ggp_abstraction {:ln (:localname ?/g)
                          :ns "kbio" :prefix "GGP_" :suffix ""}]
                   [?/gp_abstraction {:ln (:localname ?/g)
                          :ns "kbio" :prefix "GP_" :suffix ""}]
                   [?/vgp_abstraction {:ln (:localname ?/g)
                           :ns "kbio" :prefix "VGP_" :suffix ""}]
                   [?/vg_abstraction {:ln (:localname ?/g)
                          :ns "kbio" :prefix "VG_" :suffix ""}]
                   [?/ggpv_abstraction {:ln (:localname ?/g)
                            :ns "kbio" :prefix "GGPV_" :suffix ""}]
                   [?/ggv_abstraction {:ln (:localname ?/g)
                            :ns "kbio" :prefix "GGV_" :suffix ""}]
                   [?/gpgpv_abstraction {:ln (:localname ?/g)
                            :ns "kbio" :prefix "GPGPV_" :suffix ""}])
  :body "prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                    select distinct ?g ?ggp_label ?gp_label ?vgp_label ?vg_label ?ggpv_label ?ggv_label ?gpgpv_label {

                    {
                              select ?gene {
                                            kice:SO_0000704 obo:IAO_0000219 ?gene .
                                            filter (?gene != obo:SO_0000704) .
                                            }
                              }

                         ?g rdfs:subClassOf* ?gene .
                         optional {?g rdfs:label ?label}
                         bind(coalesce(?label, \"Unnamed gene\") as ?gene_name)
                         bind(concat(\"Gene or gene product abstraction for gene: \", str(?gene_name)) as ?ggp_label)
                         bind(concat(\"Gene product abstraction for gene: \", str(?gene_name)) as ?gp_label)
                         bind(concat(\"Variant gene product abstraction for gene: \", str(?gene_name)) as ?vgp_label)
                         bind(concat(\"Variant gene abstraction for gene: \", str(?gene_name)) as ?vg_label)
                         bind(concat(\"Gene or gene product or variant abstraction for gene: \", str(?gene_name)) as ?ggpv_label)
                         bind(concat(\"Gene or gene variant abstraction for gene: \", str(?gene_name)) as ?ggv_label)
                         bind(concat(\"Gene product or gene product variant abstraction for gene: \", str(?gene_name)) as ?gpgpv_label)
                         # to keep from climbing the gene hierarchy we require the genes to have a taxon
                         ?g rdfs:subClassOf ?taxon_r .
                         ?taxon_r rdf:type owl:Restriction .
                         ?taxon_r owl:onProperty ?only_in_taxon .
                    }"
  }

