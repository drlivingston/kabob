(ns rules-tests.build_test.test_build_step_hcd
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
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
                                                            build-rules-step-ca build-rules-step-cb build-rules-step-cc
                                                            build-rules-step-da build-rules-step-db build-rules-step-dc
                                                            build-rules-step-fa
                                                            build-rules-step-fb
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gc
                                                            build-rules-step-ha build-rules-step-hb
                                                            build-rules-step-hca build-rules-step-hcb build-rules-step-hcc
                                                            build-rules-step-hcd build-rules-step-hce
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

(def base-kb (let [source-kb (test-kb initial-plus-ice-triples)]
               (run-build-rules source-kb build-rules-step-a)
               (run-build-rules source-kb build-rules-step-b)
               (run-build-rules source-kb build-rules-step-ca)
               (run-build-rules source-kb build-rules-step-cb)
               (run-build-rules source-kb build-rules-step-cc)
               (run-build-rules source-kb build-rules-step-da)
               (run-build-rules source-kb build-rules-step-db)
               (run-build-rules source-kb build-rules-step-dc)

               (with-tmp-dir
                 ;; generate identifier set ntriple files and load into the source-kb
                 (generate-all-id-sets source-kb (str tmp-dir "/"))
                 (prn (str "PRINTING FILE LIST: " (count (get-only-files tmp-dir))))
                 (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                               (load-rdf source-kb (java.util.zip.GZIPInputStream.
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
               (run-build-rules source-kb build-rules-step-hca)
               (run-build-rules source-kb build-rules-step-hcb)
               (run-build-rules source-kb build-rules-step-hcc)
               source-kb))


;;; Test that proteins not involved in a has_gene_template relation get a corresponding gene
(deftest step-hcd-gene-abstraction-gen

    (let [source-kb base-kb
          target-kb (test-kb '())]



      (prn (str "--------------------------------"))
      (doall (map #(prn (str %)) (sparql-query source-kb
                                               "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                               PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
                                               PREFIX obo: <http://purl.obolibrary.org/obo/>
                                               PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                                               PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        select distinct ?id {
                            ?g rdfs:subClassOf* ?gene .
                            # to keep from climbing the gene hierarchy we require the genes to have a taxon
                            #?g rdfs:subClassOf ?taxon_r .
                            #?taxon_r rdf:type owl:Restriction .
                            #?taxon_r owl:onProperty ?only_in_taxon .
                            ?id obo:IAO_0000219 ?g .

                            {
                             select ?gene {
                                           kice:SO_0000704 obo:IAO_0000219 ?gene .
                                           filter (?gene != obo:SO_0000704) .
                                           }
                             }

                            }"
                                               )))
      (prn (str "--------------------------------"))


      (prn (str "--------------------------------"))
      (doall (map #(prn (str %)) (sparql-query source-kb
                                               "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                               PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
                                               PREFIX obo: <http://purl.obolibrary.org/obo/>
                                               PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
                                               PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
        select * {
                            ?g rdfs:subClassOf* ?gene .
                            kice:SO_0000704 obo:IAO_0000219 ?gene .
                            ?g rdfs:subClassOf ?taxon_r .
                            ?taxon_r owl:onProperty ?only_in_taxon .
                            ?id_set obo:IAO_0000142 ?only_in_taxon .

                            }"
                                               )))
      (prn (str "--------------------------------"))


      ;; confirm that there are only 2 genes relations in the KB (bioworld)
      (is (= 2 (count (distinct (query source-kb '((?/g [rdfs/subClassOf *] ?/gene)
                                          (kice/SO_0000704 obo/IAO_0000219 ?/gene)
                                          (?/g rdfs/subClassOf ?/taxon_r)
                                          (?/taxon_r owl/onProperty ?/only_in_taxon)
                                          ;; linking to id_set ensures ?/g is in bioworld
                                          (?/id_set obo/IAO_0000142 ?/only_in_taxon)
                                          ))))))

      (run-build-rules source-kb build-rules-step-hcd 0)
      ;(run-build-rule source-kb source-kb build-rules-step-ib 0)

      ;(run-build-rule source-kb target-kb build-rules-step-ia 0)

      (is (ask source-kb '((kice/HGNC_11773 obo/IAO_0000219 ?/g)
                            (?/g rdfs/subClassOf ?/ggp_abstraction)
                            (?/ggp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001715) ;; ccp:gene_or_gene_product_abstraction
                            (?/g rdfs/subClassOf ?/ggv_abstraction)
                            (?/ggv_abstraction rdfs/subClassOf ccp/IAO_EXT_1720) ;; ccp:gene_or_gene_variant_abstraction
                            (?/ggv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                            (?/ggpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001718) ;; ccp:gene_or_gene_product_or_variant_abstraction
                            (?/ggp_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                            (?/vg_abstraction rdfs/subClassOf ?/ggv_abstraction)
                            (?/vg_abstraction rdfs/subClassOf ccp/IAO_EXT_0001717) ;; ccp:variant_gene_abstraction
                            (?/gp_abstraction rdfs/subClassOf ?/ggp_abstraction)
                            (?/gp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001716) ;; ccp:gene_product_abstraction
                            (?/gp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                            (?/gpgpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001721) ;; ccp:gene_product_or_gene_product_variant_abstraction
                            (?/gpgpv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                            (?/vgp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                            (?/vgp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001719)) ;; ccp:variant_gene_product_abstraction
               ))

    (is (ask source-kb '((kice/UNIPROT_P37173-1 obo/IAO_0000219 ?/p)
                          (?/p rdfs/subClassOf ?/has_gene_template_r)
                          (?/has_gene_template_r owl/onProperty ?/has_gene_template)
                          (kice/pr#has_gene_template obo/IAO_0000219 ?/has_gene_template)
                          (?/id_set obo/IAO_0000142 ?/has_gene_template)
                          (?/has_gene_template_r owl/someValuesFrom ?/g)
                          (?/g rdfs/subClassOf ?/ggp_abstraction)
                          (?/ggp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001715) ;; ccp:gene_or_gene_product_abstraction
                          (?/g rdfs/subClassOf ?/ggv_abstraction)
                          (?/ggv_abstraction rdfs/subClassOf ccp/IAO_EXT_1720) ;; ccp:gene_or_gene_variant_abstraction
                          (?/ggv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                          (?/ggpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001718) ;; ccp:gene_or_gene_product_or_variant_abstraction
                          (?/ggp_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                          (?/vg_abstraction rdfs/subClassOf ?/ggv_abstraction)
                          (?/vg_abstraction rdfs/subClassOf ccp/IAO_EXT_0001717) ;; ccp:variant_gene_abstraction
                          (?/gp_abstraction rdfs/subClassOf ?/ggp_abstraction)
                          (?/gp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001716) ;; ccp:gene_product_abstraction
                          (?/gp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                          (?/gpgpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001721) ;; ccp:gene_product_or_gene_product_variant_abstraction
                          (?/gpgpv_abstraction rdfs/subClassOf ?/ggpv_abstraction)
                          (?/vgp_abstraction rdfs/subClassOf ?/gpgpv_abstraction)
                          (?/vgp_abstraction rdfs/subClassOf ccp/IAO_EXT_0001719) ;; ccp:variant_gene_product_abstraction
                          )
             ))

    ;; there should now be two instances of GGPV (IAO_EXT_0001718)
    (is (= 2 (count (query source-kb '((?/ggpv_abstraction rdfs/subClassOf ccp/IAO_EXT_0001718))))))

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


    ))




