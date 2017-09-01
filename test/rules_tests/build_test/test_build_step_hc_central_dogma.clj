(ns rules-tests.build_test.test_build_step_hc_central_dogma
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
                  build-rules-step-hc-central-dogma
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



;;; Test that all links get transferred to bioworld
(deftest step-hc-central-dogma
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

    ;; there should be 1 has_gene_template relation in the kb (brought in via pr.owl)
    ;; 1 from pr.owl and 1 that has been copied into bio-world
    (is (= 2 (count (query source-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (ccp/pr#has_gene_template obo/IAO_0000219 ?/prop))))))

    (run-build-rule source-kb source-kb build-rules-step-hc-central-dogma 0)

    (run-build-rule source-kb target-kb build-rules-step-hc-central-dogma 0)

;; no new ones are created I think...
    ;;TODO: add data to the uniprot-id-mapping sample data to test the rule fully
    (is (= 0 (count (query target-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        ;(ccp/pr#has_gene_template obo/IAO_0000219 ?/prop)
                                        )))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-hc-central-dogma 0)
      (close log-kb))


   ; (prn (str "--------------------------------"))
   ; (doall (map #(prn (str %)) (sparql-query source-kb
   ;"PREFIX obo: <http://purl.obolibrary.org/obo/>
   ;PREFIX obo_pr: <http://purl.obolibrary.org/obo/pr#>
   ;PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   ;PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   ; PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
   ; SELECT ?uniprot_identifier ?protein  ?ncbi_gene_identifier ?gene ?has_gene_template
   ; WHERE {
   ;       ?record rdf:type ccp:IAO_EXT_0000235 .  # CCP:UniProt_identifier_mapping_record
   ;       ?record obo:BFO_0000051 ?uniprot_identifier_field_value .
   ;       ?uniprot_identifier_field_value rdf:type ccp:IAO_EXT_0000239 .  # CCP:UniProt_identifier_mapping_record__UniProt_accession_identifier_field_value
   ;       ?uniprot_identifier_field_value rdf:type ?uniprot_identifier .
   ;       ?uniprot_identifier obo:IAO_0000219 ?protein .
   ;       ?record obo:BFO_0000051 ?ncbi_gene_identifier_field_value .
   ;       ?ncbi_gene_identifier_field_value rdf:type ccp:IAO_EXT_0000242 .  # CCP:UniProt_identifier_mapping_record__Entrez_gene_identifier_field_value
   ;       ?ncbi_gene_identifier_field_value rdf:type ?ncbi_gene_identifier .
   ;       ?ncbi_gene_identifier obo:IAO_0000219 ?gene .
   ;       {
   ;        select ?has_gene_template {
   ;                                   ?has_gene_template_id obo:IAO_0000219 obo_pr:has_gene_template .
   ;                                   ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
   ;                                   # ensure it's a kabob bioentity (not an obo bioentity)
   ;               filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/obo/ext/'))
   ;                                   }
   ;        }
   ;       }"
   ;)))
   ;
   ;     (prn (str "--------------------------------"))

    ))


