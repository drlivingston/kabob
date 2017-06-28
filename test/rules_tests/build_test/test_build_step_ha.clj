(ns rules-tests.build_test.test_build_step_ha
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
                                                            build-rules-step-ha
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


;;; Test that protein nodes that are not already part of a hierarchy are provided with a subClassOf relation to chebi:protein
(deftest step-ha-test-ophan-protein-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 4)

    ;; there are 10 protein bioentities that are isolated, most brought in as interaction partners of P37173
    (is (= 10
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-ha 4)
      (close log-kb))


    ;(prn (str "--------------------------------"))
    ;(doall (map #(prn (str %)) (sparql-query source-kb
    ;                                         "PREFIX obo: <http://purl.obolibrary.org/obo/>
    ;                                         PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    ;                                         PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    ;PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    ;select ?id ?bioentity ?protein_bioentity {
    ;                                      ?id rdfs:subClassOf* ccp:IAO_EXT_0000188 . # CCP:protein identifier
    ;                                          ?id obo:IAO_0000219 ?bioentity . # OBO:denotes
    ;                                              # ensure it's a kabob bioentity (not an obo bioentity)
    ;                                      filter (contains (str(?bioentity), 'http://ccp.ucdenver.edu/obo/ext/'))
    ;                                      # if it already has a subClassOf relation, then it's already part of a hierarchy so we exclude it
    ;                                      minus {?bioentity rdfs:subClassOf ?x}
    ;                                      {
    ;                                       # get the kabob bioentity that corresponds to CHEBI:protein
    ;                                                 select ?protein_bioentity {
    ;                                                                            ccp:CHEBI_36080 obo:IAO_0000219 ?protein_bioentity . # OBO:denotes
    ;                                          filter (?protein_bioentity != obo:CHEBI_36080)  # OBO:protein
    ;                                          }
    ;                                                                            }
    ;                                       }"
    ;                                         )))
    ;
    ;    (prn (str "--------------------------------"))


    ))



;; TODO: revisit this rule when sample drugbank rdf has been added
(deftest step-ha-test-ophan-drugbank-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 0)

    ;; there are 11 proteins that are isolated, most brought in as interaction partners of P37173
    (is (= 0
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ha 4)
    ;  (close log-kb))

    ))

;; this rule results in zero hits b/c the only gene in the sample data is HGNC:11773 and it
;; is already rdfs:subClassOf SO:protein_coding_gene
(deftest step-ha-test-ophan-gene-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 1)

    ;; there are 11 proteins that are isolated, most brought in as interaction partners of P37173
    (is (= 0
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ha 4)
    ;  (close log-kb))

    ))

;; TODO: revisit this rule if mrna identifiers are added to the sample data
(deftest step-ha-test-ophan-mrna-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 2)

    ;; there are 11 proteins that are isolated, most brought in as interaction partners of P37173
    (is (= 0
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ha 4)
    ;  (close log-kb))

    ))

;; TODO: revisit this rule when sample pharmgkb data is added to the sample input
(deftest step-ha-test-ophan-pharmgkb-drug-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 3)

    ;; there are 11 proteins that are isolated, most brought in as interaction partners of P37173
    (is (= 0
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ha 4)
    ;  (close log-kb))

    ))

;; TODO: revisit this rule when rna identifiers are added to the sample data (perhaps through a gene2refseq record)
(deftest step-ha-test-ophan-rna-node-typing
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

    (run-build-rule source-kb target-kb build-rules-step-ha 5)

    ;; there are 11 proteins that are isolated, most brought in as interaction partners of P37173
    (is (= 0
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ha 4)
    ;  (close log-kb))

    ))