(ns rules-tests.build_test.test_build_step_ga
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf *graph*]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.ccp-ext-ontology :refer [ccp-ext-ontology-triples]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-ca build-rules-step-cb build-rules-step-cc
                                                            build-rules-step-da build-rules-step-db build-rules-step-dc
                                                            build-rules-step-fa
                                                            build-rules-step-fb
                                                            build-rules-step-ga
                                                            expected-subpropertyof-links expected-inverseof-links
                                                            expected-subclassof-links expected-disjointwith-links
                                                            expected-restrictions expected-restrictions-in-lists
                                                            expected-equivalent-class-links]]
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
               (binding [*graph* "http://ccp-extension.owl"]
                 (dorun (map (partial add! source-kb) ccp-ext-ontology-triples)))
               (binding [*graph* "http://default-graph"]
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
               (run-build-rules source-kb build-rules-step-fb))
               source-kb))


;;; Test that list members get reproduced in bioworld where appropriate
(deftest step-ga-test-copy-list-nodes-to-bio
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (run-build-rule source-kb target-kb build-rules-step-ga 0)

    ;; There are 2 list members associated with GO_0022402
    ;; There are 2 list members associated with PR_P37173
    ;; There are 2 list members associated with RO_0002216
    ;; There are 4 list members associated with BFO_0000066; but these are ignored
    ;; b/c they are part of the ccp-extension graph in these tests
    (is (= 6 (count (query target-kb '((?/x rdf/type rdf/List)))))) ; obo:denotes

    (let [log-kb (output-kb "/tmp/triples.nt")]

      (run-build-rule source-kb log-kb build-rules-step-ga 0)

      (close log-kb))

    ))


;;; Test that owl restrictions get reproduced in bioworld where appropriate
(deftest step-ga-test-copy-owl-restrictions-to-bio
  (let [source-kb base-kb
        target-kb (test-kb '())]



    (prn (str "--------------------------------"))
    (doall (map #(prn (str "QUERY === " %)) (sparql-query source-kb
                                             "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                              prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                              prefix obo: <http://purl.obolibrary.org/obo/>
                                              prefix owl: <http://www.w3.org/2002/07/owl#>
                                              prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                              select ?obo_restriction {
                                               graph ?g {
                                                         ?obo_restriction rdf:type owl:Restriction .
                                                         }
                                                     # exclude restrictions from the IAO
                                                     filter (!contains (str (?g), \"ccp-extension\"))
                                               }")))

        (prn (str "--------------------------------"))





    (run-build-rule source-kb target-kb build-rules-step-ga 2)
    (run-build-rule source-kb source-kb build-rules-step-ga 2)


    ;; testing the existence of a single sample bio restriction generated by this rule
    (is (ask source-kb '((obo/GO_0022402 rdfs/subClassOf ?/r)
                          (?/r rdf/type owl/Restriction)
                          (?/r owl/onProperty obo/BFO_0000050)
                          (?/r owl/someValuesFrom obo/GO_0007049)
                          (?/r_id obo/IAO_0000219 ?/r)
                          (?/r_id obo/IAO_0000219 ?/bio_r)
                          (?/bio_r rdf/type owl/Restriction)
                          (:filter (!= ?/r ?/bio_r))
                          )))


    (is (ask source-kb '((obo/MI_0001 rdfs/subClassOf ?/r)
                          (?/r rdf/type owl/Restriction)
                          (?/r owl/onProperty obo/mi#part_of)
                          (?/r owl/someValuesFrom obo/MI_0000)
                          (?/r_id obo/IAO_0000219 ?/r)
                          (?/r_id obo/IAO_0000219 ?/bio_r)
                          (?/bio_r rdf/type owl/Restriction)
                          (:filter (!= ?/r ?/bio_r))
                          )))

    ;; there are 15 restriction instances in the input_data, however only 10 of them
    ;; are unique given the way the RDF generation code uses hashes to limit the number
    ;; of unique URIs.
    (is (= 10 (count (query target-kb '((?/record rdf/type ccp/IAO_EXT_0000305))))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-ga 2)
      (close log-kb))

    ))


;;; Test that blank nodes get transferred to bioworld (nodes that connect owl:equivalentClass to owl:intersectionOf)
(deftest step-ga-test-copy-anonymous-nodes-to-bio
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rule source-kb source-kb build-rules-step-ga 3)

    ;; testing the existence of anonymous node records links to blank nodes
    (is (ask source-kb '((obo/PR_P37173 owl/equivalentClass ?/blank_node)
                          (?/id obo/IAO_0000219 ?/blank_node)
                          (?/id obo/IAO_0000219 ?/bio_blank_node)
                          (:filter (!= ?/blank_node ?/bio_blank_node))
                          (?/id rdf/type ccp/IAO_EXT_0001710)
                          )))

    (is (ask source-kb '((obo/GO_0022402 owl/equivalentClass ?/blank_node)
                          (?/id obo/IAO_0000219 ?/blank_node)
                          (?/id obo/IAO_0000219 ?/bio_blank_node)
                          (:filter (!= ?/blank_node ?/bio_blank_node))
                          (?/id rdf/type ccp/IAO_EXT_0001710)
                          )))


    (run-build-rule source-kb target-kb build-rules-step-ga 3)
    ;; there are only 2 equivalentClass links to blank nodes in the test data
    (is (= 2 (count (query target-kb '((?/r rdf/type ccp/IAO_EXT_0001707))))))


    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ga 3)
    ;  (close log-kb))

    ))



(deftest step-ga-test-copy-anonymous-nodes-in-lists-to-bio
  (let [source-kb base-kb
        target-kb (test-kb '())]


    (prn (str "111--------------------------------"))
    (doall (map #(prn (str "QUERY === " %)) (sparql-query source-kb
                                                         "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                          prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                          prefix obo: <http://purl.obolibrary.org/obo/>
                                                          prefix owl: <http://www.w3.org/2002/07/owl#>
                                                          prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                          select ?blank_node ?g ?type{
                                                          graph ?g {
                                                               ?listmember rdf:type rdf:List .
                                                               ?listmember rdf:first ?blank_node .
                                                               filter (contains(str(?blank_node), '/bnode/'))
                                                               minus {?blank_node rdf:type ?t}
                                                               minus {?blank_node rdfs:subClassOf ?c}
                                                               }
                                                          filter (!contains (str (?g), \"ccp-extension\"))
                                                     }")))

    (prn (str "111--------------------------------"))


    (run-build-rule source-kb target-kb build-rules-step-ga 4)


    (prn (str "222--------------------------------"))
    (doall (map #(prn (str "ANON === " %)) (sparql-query target-kb
                                                          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                           prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                           prefix obo: <http://purl.obolibrary.org/obo/>
                                                           prefix owl: <http://www.w3.org/2002/07/owl#>
                                                           prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                           select ?s ?p ?o {?s ?p ?o}")))

    (prn (str "222--------------------------------"))

    ;; this rule should produce zero hits for the test data set; but 4 triples for the rule metadata
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    ))

;; TODO: write a test for the copy AllDisjointClasses rule -- will need to add appropriate constructs to the input data






