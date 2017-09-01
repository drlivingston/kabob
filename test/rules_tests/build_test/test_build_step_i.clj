(ns rules-tests.build_test.test_build_step_i
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-c build-rules-step-da build-rules-step-db build-rules-step-fa
                                                            build-rules-step-fb
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gc
                                                            build-rules-step-ha build-rules-step-hb
                                                            build-rules-step-hc-central-dogma build-rules-step-hc-goa build-rules-step-ia
                                                            build-rules-step-ib build-rules-step-ic
                                                            expected-subpropertyof-links expected-inverseof-links
                                                            expected-subclassof-links expected-disjointwith-links
                                                            expected-restrictions expected-restrictions-in-lists
                                                            expected-equivalent-class-links expected-rdfs-domain-links
                                                            expected-rdfs-range-links]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))



;;; Test that proteins not involved in a has_gene_template relation get a corresponding gene
(deftest step-ia-add-missing-genes
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)

    (with-tmp-dir
      ;; generate identifier set ntriple files and load into the source-kb
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (dorun (map (fn [f] (load-rdf source-kb (java.util.zip.GZIPInputStream.
                                                (clojure.java.io/input-stream
                                                  f)) :ntriple))
                  (get-only-files tmp-dir))))
    (run-build-rules source-kb build-rules-step-fa)
    (run-build-rules source-kb build-rules-step-fb)
    (run-build-rules source-kb build-rules-step-ga)
    (run-build-rules source-kb build-rules-step-gb)
    (run-build-rules source-kb build-rules-step-gc)
    (run-build-rules source-kb build-rules-step-ha)
    (run-build-rules source-kb build-rules-step-hb)
    (run-build-rules source-kb build-rules-step-hc-central-dogma)
    (run-build-rules source-kb build-rules-step-hc-goa)

    ;; confirm that there are only 2 has_gene_template relations in the KB
    (is (= 2 (count (query source-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (ccp/pr#has_gene_template obo/IAO_0000219 ?/prop))))))

    (run-build-rule source-kb source-kb build-rules-step-ia 0)

    ;(run-build-rule source-kb target-kb build-rules-step-ia 0)

    (is (ask source-kb '(
                          (?/protein_coding_gene rdfs/subClassOf ?/protein_coding_gene_bioentity)
                          (?/taxon_r rdf/type owl/Restriction)
                          (?/taxon_r owl/onProperty ?/only_in_taxon)
                          (ccp/RO_0002160 obo/IAO_0000219 ?/only_in_taxon) ; RO:only_in_taxon
                          (?/taxon_r owl/someValuesFrom ?/taxon)
                          (ccp/NCBITaxon_9606 obo/IAO_0000219 ?/taxon)
                          (?/protein_coding_gene rdfs/subClassOf ?/taxon_r)
                          (?/has_gene_template_r rdf/type owl/Restriction)
                          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
                          (?/id_set obo/IAO_0000142 ?/has_gene_template)
                          (ccp/pr#has_gene_template obo/IAO_0000219 ?/has_gene_template)
                          (?/has_gene_template_r owl/someValuesFrom ?/protein_coding_gene)
                          (?/protein_missing_gene rdfs/subClassOf ?/has_gene_template_r)
                          (ccp/UNIPROT_P37173-1 obo/IAO_0000219 ?/protein_missing_gene)
                          )))

    ;; there should now be just one more has_gene_template relation in the KB (so 3 total; see query above)
    (is (= 3 (count (query source-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (ccp/pr#has_gene_template obo/IAO_0000219 ?/prop))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ia 0)
    ;  (close log-kb))

    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX obo: <http://purl.obolibrary.org/obo/>
    ;                                         PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
    ;                                         PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;select distinct ?protein_id ?protein_missing_gene ?protein ?has_gene_template ?protein_coding_gene_bioentity  ?hgt_id {
    ;          ?protein_missing_gene rdfs:subClassOf* ?protein .
    ;          # to keep from climbing the protein hierarchy too high we require the protein to have a taxon
    ;          ?protein_missing_gene rdfs:subClassOf ?taxon_r .
    ;          ?taxon_r rdf:type owl:Restriction .
    ;          ?taxon_r owl:onProperty ?in_taxon .
    ;          ?taxon_r owl:someValuesFrom ?taxon .
    ;          ?protein_id obo:IAO_0000219 ?protein_missing_gene .
    ;          # exclude proteins that already have a has_gene_template restriction (likely imported via pr.owl)
    ;          filter not exists {
    ;                             ?protein_missing_gene rdfs:subClassOf ?has_gene_template_r .
    ;                             ?has_gene_template_r rdf:type owl:Restriction .
    ;                             ?has_gene_template_r owl:onProperty ?has_gene_template .
    ;                             }
    ;
    ;
    ;          {
    ;           select ?only_in_taxon {
    ;                            ccp:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
    ;                            filter (?only_in_taxon != obo:RO_0002160) .
    ;                            }
    ;           }
    ;
    ;                       {
    ;                        select ?protein {
    ;                                         ccp:CHEBI_36080 obo:IAO_0000219 ?protein .
    ;                                         filter (?protein != obo:CHEBI_36080) .
    ;                                         }
    ;                        }
    ;
    ;                                             {
    ;                                              select ?has_gene_template ?hgt_id {
    ;                                                                         <http://ccp.ucdenver.edu/obo/ext/pr#has_gene_template> obo:IAO_0000219 ?has_gene_template .
    ;                                                                         ?hgt_id obo:IAO_0000219 ?has_gene_template .
    ;                                                                                 filter (?has_gene_template != <http://purl.obolibrary.org/obo/pr#has_gene_template>) .
    ;                                                                         }
    ;                                              }
    ;                       {
    ;                        # get the kabob bioentity that corresponds to SO:gene
    ;                                  select ?protein_coding_gene_bioentity {
    ;                                                                         ccp:SO_0001217 obo:IAO_0000219 ?protein_coding_gene_bioentity . # OBO:denotes
    ;                           filter (?protein_coding_gene_bioentity != obo:SO_0001217) # OBO:gene
    ;                           }
    ;                                                                         }
    ;                        }"
    ;                                         )))
    ;
    ;(prn (str "--------------------------------"))

    ))



;;; Test the addition of the gene abstraction layers for each gene
(deftest step-ib-add-gene-abstraction-layers
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)

    (with-tmp-dir
      ;; generate identifier set ntriple files and load into the source-kb
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (dorun (map (fn [f] (load-rdf source-kb (java.util.zip.GZIPInputStream.
                                                (clojure.java.io/input-stream
                                                  f)) :ntriple))
                  (get-only-files tmp-dir))))
    (run-build-rules source-kb build-rules-step-fa)
    (run-build-rules source-kb build-rules-step-fb)
    (run-build-rules source-kb build-rules-step-ga)
    (run-build-rules source-kb build-rules-step-gb)
    (run-build-rules source-kb build-rules-step-gc)
    (run-build-rules source-kb build-rules-step-ha)
    (run-build-rules source-kb build-rules-step-hb)
    (run-build-rules source-kb build-rules-step-hc-central-dogma)
    (run-build-rules source-kb build-rules-step-hc-goa)
    (run-build-rules source-kb build-rules-step-ia)

    ;; confirm that there are only 2 genes relations in the KB (bioworld)
    (is (= 2 (count (query source-kb '((?/g [rdfs/subClassOf *] ?/gene)
                                        (ccp/SO_0000704 obo/IAO_0000219 ?/gene)
                                        (?/g rdfs/subClassOf ?/taxon_r)
                                        (?/taxon_r owl/onProperty ?/only_in_taxon)
                                        ;; linking to id_set ensures ?/g is in bioworld
                                        (?/id_set obo/IAO_0000142 ?/only_in_taxon)
                                        )))))

    (run-build-rule source-kb source-kb build-rules-step-ib 0)

    ;(run-build-rule source-kb target-kb build-rules-step-ia 0)

    (is (ask source-kb '((ccp/HGNC_11773 obo/IAO_0000219 ?/g)
                          (?/g rdfs/subClassOf ?/ggp_abstraction)
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
             ))

  (is (ask source-kb '((ccp/UNIPROT_P37173-1 obo/IAO_0000219 ?/p)
                        (?/p rdfs/subClassOf ?/has_gene_template_r)
                        (?/has_gene_template_r owl/onProperty ?/has_gene_template)
                        (ccp/pr#has_gene_template obo/IAO_0000219 ?/has_gene_template)
                        (?/id_set obo/IAO_0000142 ?/has_gene_template)
                        (?/has_gene_template_r owl/someValuesFrom ?/g)
                        (?/g rdfs/subClassOf ?/ggp_abstraction)
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
                        (?/vgp_abstraction rdf/type ccp/IAO_EXT_0001719) ;; ccp:variant_gene_product_abstraction
                        )
           ))

  ;; there should now be two instances of GGPV (IAO_EXT_0001718)
  (is (= 2 (count (query source-kb '((?/ggpv_abstraction rdf/type ccp/IAO_EXT_0001718))))))

  ;(let [log-kb (output-kb "/tmp/triples.nt")]
  ;  (run-build-rule source-kb log-kb build-rules-step-ib 0)
  ;  (close log-kb))

  ;(prn (str "--------------------------------"))
  ;(doall (map #(prn (str %)) (sparql-query source-kb
  ;                                         "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  ;                                         PREFIX obo: <http://purl.obolibrary.org/obo/>
  ;                                         PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
  ;                                         PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  ;  select distinct ?g {
  ;                      ?g rdfs:subClassOf* ?gene .
  ;                      # to keep from climbing the gene hierarchy we require the genes to have a taxon
  ;                      ?g rdfs:subClassOf ?taxon_r .
  ;                      ?taxon_r rdf:type owl:Restriction .
  ;                      ?taxon_r owl:onProperty ?only_in_taxon .
  ;
  ;                      {
  ;                       select ?gene {
  ;                                     ccp:SO_0000704 obo:IAO_0000219 ?gene .
  ;                                     filter (?gene != obo:SO_0000704) .
  ;                                     }
  ;                       }
  ;
  ;                      }"
  ;                                         )))
  ;(prn (str "--------------------------------"))

  ) )


;;; Test the linking of proteins to the gene_product_abstraction nodes
(deftest step-ic-test-link-proteins-to-gp-abstractions
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)

    (with-tmp-dir
      ;; generate identifier set ntriple files and load into the source-kb
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (dorun (map (fn [f] (load-rdf source-kb (java.util.zip.GZIPInputStream.
                                                (clojure.java.io/input-stream
                                                  f)) :ntriple))
                  (get-only-files tmp-dir))))
    (run-build-rules source-kb build-rules-step-fa)
    (run-build-rules source-kb build-rules-step-fb)
    (run-build-rules source-kb build-rules-step-ga)
    (run-build-rules source-kb build-rules-step-gb)
    (run-build-rules source-kb build-rules-step-gc)
    (run-build-rules source-kb build-rules-step-ha)
    (run-build-rules source-kb build-rules-step-hb)
    (run-build-rules source-kb build-rules-step-hc-central-dogma)
    (run-build-rules source-kb build-rules-step-hc-goa)
    (run-build-rules source-kb build-rules-step-ia)
    (run-build-rules source-kb build-rules-step-ib)

    (run-build-rule source-kb source-kb build-rules-step-ic 0)

    (is (ask source-kb '((ccp/HGNC_11773 obo/IAO_0000219 ?/g)
                          (?/g rdfs/subClassOf ?/ggp_abstraction)
                          (?/ggp_abstraction rdf/type ccp/IAO_EXT_0001715) ;; ccp:gene_or_gene_product_abstraction
                          (?/g_abstraction rdfs/subClassOf ?/ggv_abstraction)
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
                          (?/vgp_abstraction rdf/type ccp/IAO_EXT_0001719) ;; ccp:variant_gene_product_abstraction

                          (ccp/UNIPROT_P37173 obo/IAO_0000219 ?/p)
                          (?/p rdfs/subClassOf ?/gp_abstraction)

                          )
             ))

    (is (ask source-kb '((ccp/UNIPROT_P37173-1 obo/IAO_0000219 ?/p)
                          (?/p rdfs/subClassOf ?/has_gene_template_r)
                          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
                          (ccp/pr#has_gene_template obo/IAO_0000219 ?/has_gene_template)
                          (?/id_set obo/IAO_0000142 ?/has_gene_template)
                          (?/has_gene_template_r owl/someValuesFrom ?/g)
                          (?/g rdfs/subClassOf ?/ggp_abstraction)
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
                          (?/vgp_abstraction rdf/type ccp/IAO_EXT_0001719) ;; ccp:variant_gene_product_abstraction

                          (ccp/UNIPROT_P37173-1 obo/IAO_0000219 ?/p)
                          (?/p rdfs/subClassOf ?/gp_abstraction)


                          )
             ))

    (run-build-rule source-kb target-kb build-rules-step-ic 0)

    ;; there should be 2 subclassOf links generated from running this rule
    (is (= 2 (count (query target-kb '((?/p rdfs/subClassOf ?/abstraction))))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-ib 0)
      (close log-kb))

    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX obo: <http://purl.obolibrary.org/obo/>
    ;                                         PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
    ;                                         PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;  select distinct ?g {
    ;                      ?g rdfs:subClassOf* ?gene .
    ;                      # to keep from climbing the gene hierarchy we require the genes to have a taxon
    ;                      ?g rdfs:subClassOf ?taxon_r .
    ;                      ?taxon_r rdf:type owl:Restriction .
    ;                      ?taxon_r owl:onProperty ?only_in_taxon .
    ;
    ;                      {
    ;                       select ?gene {
    ;                                     ccp:SO_0000704 obo:IAO_0000219 ?gene .
    ;                                     filter (?gene != obo:SO_0000704) .
    ;                                     }
    ;                       }
    ;
    ;                      }"
    ;                                         )))
    ;(prn (str "--------------------------------"))

    ) )








