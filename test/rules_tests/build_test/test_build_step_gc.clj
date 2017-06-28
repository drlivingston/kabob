(ns rules-tests.build_test.test_build_step_gc
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
(deftest step-gc-test-transfer-links-between-nodes
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

    (run-build-rule source-kb source-kb build-rules-step-gc 0)

    ;; test existence of expected subclass relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "ccp" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "ccp" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (!= ?/bioentity1 ~child-concept-id-obo)
                                                   (?/bioentity1 rdfs/subClassOf ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                (filter #(not= % '("HGNC_11773" "SO_0001217")) expected-subclassof-links)))

    ;; separate out the expected HGNC_11773 subclass relation b/c of hgnc_pr namespace usage
    (is (ask source-kb `((ccp/HGNC_11773 obo/IAO_0000219 hgnc_pr/gene_symbol_report?hgnc_id=11773)
                          (ccp/HGNC_11773 obo/IAO_0000219 ?/bioentity1)
                          (!= ?/bioentity1 hgnc_pr/gene_symbol_report?hgnc_id=11773)
                          (?/bioentity1 rdfs/subClassOf ?/bioentity2)
                          (ccp/SO_0001217 obo/IAO_0000219 ?/bioentity2))))

    ;; test existence of expected disjointWith relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "ccp" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "ccp" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (!= ?/bioentity1 ~child-concept-id-obo)
                                                   (?/bioentity1 owl/disjointWith ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                expected-disjointwith-links))

    ;; test existence of expected owl:equivalentClass relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "ccp" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "ccp" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (!= ?/bioentity1 ~child-concept-id-obo)
                                                   (?/bioentity1 owl/equivalentClass ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                expected-equivalent-class-links))


    ;; test rdfs:domain links
    (doall (map (fn [link] (let [prop-id-ccp (symbol "ccp" (first link))
                                 prop-id-obo (symbol "obo" (first link))
                                 cls-id (symbol "ccp" (last link))]
                             (is (ask source-kb `((~prop-id-ccp obo/IAO_0000219 ~prop-id-obo)
                                                   (~prop-id-ccp obo/IAO_0000219 ?/bioprop)
                                                   (!= ?/bioprop ~prop-id-obo)
                                                   (?/bioprop rdfs/domain ?/biodomain)
                                                   (~cls-id obo/IAO_0000219 ?/biodomain))))))
                expected-rdfs-domain-links))

    ;; test rdfs:range links
    (doall (map (fn [link] (let [prop-id-ccp (symbol "ccp" (first link))
                                 prop-id-obo (symbol "obo" (first link))
                                 cls-id (symbol "ccp" (last link))]
                             (is (ask source-kb `((~prop-id-ccp obo/IAO_0000219 ~prop-id-obo)
                                                   (~prop-id-ccp obo/IAO_0000219 ?/bioprop)
                                                   (!= ?/bioprop ~prop-id-obo)
                                                   (?/bioprop rdfs/range ?/biodomain)
                                                   (~cls-id obo/IAO_0000219 ?/biodomain))))))
                expected-rdfs-range-links))


    (doall (map (fn [props] (let [child-prop-id-ccp (symbol "ccp" (first props))
                                  child-prop-id-obo (symbol "obo" (first props))
                                  parent-prop-id (symbol "ccp" (last props))]
                              (is (ask source-kb `((~child-prop-id-ccp obo/IAO_0000219 ~child-prop-id-obo)
                                                    (~child-prop-id-ccp obo/IAO_0000219 ?/biorelation1)
                                                    (!= ?/biorelation1 ~child-prop-id-obo)
                                                    (?/biorelation1 rdfs/subPropertyOf ?/biorelation2)
                                                    (~parent-prop-id obo/IAO_0000219 ?/biorelation2))))))
                expected-subpropertyof-links))

    (doall (map (fn [props] (let [child-prop-id-ccp (symbol "ccp" (first props))
                                  child-prop-id-obo (symbol "obo" (first props))
                                  parent-prop-id (symbol "ccp" (last props))]
                              (is (ask source-kb `((~child-prop-id-ccp obo/IAO_0000219 ~child-prop-id-obo)
                                                    (~child-prop-id-ccp obo/IAO_0000219 ?/biorelation1)
                                                    (!= ?/biorelation1 ~child-prop-id-obo)
                                                    (?/biorelation1 owl/inverseOf ?/biorelation2)
                                                    (~parent-prop-id obo/IAO_0000219 ?/biorelation2))))))
                expected-inverseof-links))



    ;; test existence of expected restrictions
    (doall (map (fn [expected_r] (let [restriction_subclass_id_ccp (symbol (nth expected_r 0))
                                       restriction_subclass_id_obo (symbol (nth expected_r 1))
                                       obo_prop (symbol "obo" (nth expected_r 2))
                                       values_from_prop (symbol (nth expected_r 3))
                                       values_from_class_ccp (symbol (nth expected_r 4))]
                                   (is (ask source-kb `((~restriction_subclass_id_obo rdfs/subClassOf ?/obo_r)
                                                         (?/r_id obo/IAO_0000219 ?/obo_r)
                                                         (?/r_id obo/IAO_0000219 ?/bio_r)
                                                         (?/bio rdfs/subClassOf ?/bio_r)
                                                         (~restriction_subclass_id_ccp obo/IAO_0000219 ?/bio)
                                                         (?/obo_r owl/onProperty ~obo_prop)
                                                         (?/prop_id obo/IAO_0000219 ~obo_prop)
                                                         (?/prop_id obo/IAO_0000219 ?/bio_prop)
                                                         (?/bio_r owl/onProperty ?/bio_prop)

                                                         (?/bio_r ~values_from_prop ?/restricted_class)
                                                         (~values_from_class_ccp obo/IAO_0000219 ?/restricted_class))))))
                expected-restrictions))


    ;;; test existence of expected restrictions that are part of rdf lists
    ;(doall (map (fn [expected_r] (let [restriction_subclass_id_ccp (symbol (nth expected_r 0))
    ;                                   restriction_subclass_id_obo (symbol (nth expected_r 1))
    ;                                   obo_prop (symbol "obo" (nth expected_r 2))
    ;                                   values_from_prop (symbol (nth expected_r 3))
    ;                                   values_from_class_ccp (symbol (nth expected_r 4))
    ;                                   prop_path (nth expected_r 5)]
    ;                               ;; TODO: figure out how to make prop_path well-formed symbols
    ;                               (is (ask source-kb `((~restriction_subclass_id_obo prop_path ?/obo_r)
    ;                                                     (?/r_id obo/IAO_0000219 ?/obo_r)
    ;                                                     (?/r_id obo/IAO_0000219 ?/bio_r)
    ;                                                     (?/bio rdfs/subClassOf ?/bio_r)
    ;                                                     (~restriction_subclass_id_ccp obo/IAO_0000219 ?/bio)
    ;                                                     (?/obo_r owl/onProperty ~obo_prop)
    ;                                                     (?/prop_id obo/IAO_0000219 ~obo_prop)
    ;                                                     (?/prop_id obo/IAO_0000219 ?/bio_prop)
    ;                                                     (?/bio_r owl/onProperty ?/bio_prop)
    ;
    ;                                                     (?/bio_r ~values_from_prop ?/restricted_class)
    ;                                                     (~values_from_class_ccp obo/IAO_0000219 ?/restricted_class))))))
    ;            expected-restrictions-in-lists))


    (run-build-rule source-kb target-kb build-rules-step-gc 0)

    ;; there are 8 concept-subclassof-restriction links that are not included in expected-subclassof-links
    (is (= (+ 8 (count expected-subclassof-links))
           (count (query target-kb '((?/s rdfs/subClassOf ?/o))))))

    (is (= (count expected-disjointwith-links)
           (count (query target-kb '((?/s owl/disjointWith ?/o))))))

    (is (= (count (concat expected-inverseof-links))
           (count (query target-kb '((?/s owl/inverseOf ?/o))))))

    (is (= (count expected-rdfs-domain-links)
           (count (query target-kb '((?/s rdfs/domain ?/o))))))

    (is (= (count expected-rdfs-range-links)
           (count (query target-kb '((?/s rdfs/range ?/o))))))

    (is (= (count expected-inverseof-links)
           (count (query target-kb '((?/s owl/inverseOf ?/o))))))

    (is (= (count expected-subpropertyof-links)
           (count (query target-kb '((?/s rdfs/subPropertyOf ?/o))))))

    ;; there are two restrictions that are part of rdf list constructs
    (is (= (count (concat expected-restrictions expected-restrictions-in-lists))
           (count (query target-kb '((?/s owl/onProperty ?/o))))))

    (is (= 3
           (count (query target-kb '((?/s owl/equivalentClass ?/o))))))

    (is (= 2
           (count (query target-kb '((?/s owl/intersectionOf ?/o))))))

    (is (= 10
           (count (query target-kb '((?/s rdf/first ?/o))))))

    ;; this are rdf/rest links to non-nil nodes
    (is (= 5
           (count (query target-kb '((?/s rdf/rest ?/o))))))

    (is (= 3
           (count (query target-kb '((?/s owl/propertyChainAxiom ?/o))))))


    ;; 4 triples for the rule metadata
    (is (= (+ 4 3 2 10 5 3 8
              (count (concat expected-disjointwith-links expected-subclassof-links
                             expected-inverseof-links expected-rdfs-domain-links
                             expected-rdfs-range-links expected-subpropertyof-links))
              ;; * 2 b/c for each restriction there is an asserted onProperty & someValuesFrom relation
              (* 2 (count (concat
                            expected-restrictions expected-restrictions-in-lists))))
           (count (query target-kb '((?/s ?/p ?/o))))))


    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-gc 0)
    ;  (close log-kb))



    (prn (str "--------------------------------"))
    (doall (map #(prn (str %)) (sparql-query source-kb
                                             "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                              prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                              prefix obo: <http://purl.obolibrary.org/obo/>
                                              prefix owl: <http://www.w3.org/2002/07/owl#>
                                              prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                              select * {
                                                    ccp:BFO_0000050 obo:IAO_0000219 ?prop .
                                                    ?id obo:IAO_0000219 ?prop .
                                              }")))

        (prn (str "--------------------------------"))

    ))



;; tests that links with nil get transferred to bioworld
(deftest step-gc-test-transfer-links-with-nil
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

    ;(run-build-rule source-kb source-kb build-rules-step-gc 1)

    (run-build-rule source-kb target-kb build-rules-step-gc 1)

    ;; there are 5 links to rdf/nil in the test data
    (is (= 5 (count (query target-kb '((?/s ?/p rdf/nil))))))


    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-gc 1)
      (close log-kb))

    ))





