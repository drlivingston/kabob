(ns rules-tests.build_test.test_build_step_gc
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
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gca
                                                            build-rules-step-gcb build-rules-step-gcc
                                                            validation-rules-list validation-rules-restriction
                                                            concepts object-properties list-blank-nodes restriction-blank-nodes
                                                            intersection-of-blank-nodes
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

               (prn (str "fa--AS OBJ------------------------------"))
               (doall (map #(prn (str "AS OBJ === " %)) (sparql-query source-kb
                                                                      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                                       prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                                       prefix obo: <http://purl.obolibrary.org/obo/>
                                                                       prefix owl: <http://www.w3.org/2002/07/owl#>
                                                                       prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                                       prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                                       select ?s ?p {
                                                                        ?s ?p kbio:go_genid226412 .
                                                                        }")))

               (prn (str "fa---AS OBJ-----------------------------"))

               (run-build-rules source-kb build-rules-step-fb)

               (prn (str "fb---AS OBJ-----------------------------"))
               (doall (map #(prn (str "AS OBJ === " %)) (sparql-query source-kb
                                                                      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                                       prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                                       prefix obo: <http://purl.obolibrary.org/obo/>
                                                                       prefix owl: <http://www.w3.org/2002/07/owl#>
                                                                       prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                                       prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                                       select ?s ?p {
                                                                        ?s ?p kbio:go_genid226412 .
                                                                        }")))

               (prn (str "fb----AS OBJ----------------------------"))

               (run-build-rules source-kb build-rules-step-ga)

               (prn (str "ga----AS OBJ----------------------------"))
               (doall (map #(prn (str "AS OBJ === " %)) (sparql-query source-kb
                                                                      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                                       prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                                       prefix obo: <http://purl.obolibrary.org/obo/>
                                                                       prefix owl: <http://www.w3.org/2002/07/owl#>
                                                                       prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                                       prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                                       select ?s ?p {
                                                                        ?s ?p kbio:go_genid226412 .
                                                                        }")))

               (prn (str "ga-----AS OBJ---------------------------"))



               (prn (str "ga----AS OBJ-----ID_qTuLIhdRcJWr74qcpEFOzSBD3FQ-----------------------"))
               (doall (map #(prn (str "AS OBJ === " %)) (sparql-query source-kb
                                                                      "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                                       prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                                       prefix obo: <http://purl.obolibrary.org/obo/>
                                                                       prefix owl: <http://www.w3.org/2002/07/owl#>
                                                                       prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                                       prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                                       prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                                                                       select ?p ?o {
                                                                        kice:ID_qTuLIhdRcJWr74qcpEFOzSBD3FQ ?p ?o .
                                                                        }")))

               (prn (str "ga-----AS OBJ---ID_qTuLIhdRcJWr74qcpEFOzSBD3FQ------------------------"))


               (run-build-rules source-kb build-rules-step-gb))
               source-kb))




; tests that links with nil get transferred to bioworld
(deftest step-gca-test-transfer-links-with-nil
  (let [source-kb base-kb
        target-kb (test-kb '())]
    ;(run-build-rule source-kb source-kb build-rules-step-gc 1)

    (run-build-rule source-kb target-kb build-rules-step-gca 0)

    ;; there are 5 links to rdf/nil in the test data, but 2 of them are in the ccp-extension graph
    (is (= 3 (count (query target-kb '((?/s ?/p rdf/nil))))))


    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-gca 0)
      (close log-kb))

    ))

; tests that links with nil get transferred to bioworld
(deftest step-gcb-test-temporary-link-ont-to-bio-concepts
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rule source-kb target-kb build-rules-step-gcb 0)

    ;; 4 for the rule meta
    (is (= (+ 4 (count (concat concepts object-properties list-blank-nodes restriction-blank-nodes intersection-of-blank-nodes)))
           (count (query target-kb '((?/s ?/p ?/o))))))


    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-gcb 0)
      (close log-kb))

    ))

;;; Test that all links get transferred to bioworld
(deftest step-gc-test-transfer-links-between-nodes
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rule source-kb source-kb build-rules-step-gcb 0)
    (run-build-rule source-kb source-kb build-rules-step-gcc 0)

    ;; test existence of expected subclass relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "kice" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "kice" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (:filter (!= ?/bioentity1 ~child-concept-id-obo))
                                                   (?/bioentity1 rdfs/subClassOf ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                (filter #(not= % '("SO_0001217")) expected-subclassof-links)))

    ;;; separate out the expected HGNC_11773 subclass relation b/c of hgnc_pr namespace usage
    ;(is (ask source-kb `((kice/HGNC_11773 obo/IAO_0000219 hgnc_pr/gene_symbol_report?hgnc_id=11773)
    ;                      (kice/HGNC_11773 obo/IAO_0000219 ?/bioentity1)
    ;                      (:filter (!= ?/bioentity1 hgnc_pr/gene_symbol_report?hgnc_id=11773))
    ;                      (?/bioentity1 rdfs/subClassOf ?/bioentity2)
    ;                      (kice/SO_0001217 obo/IAO_0000219 ?/bioentity2))))

    ;; test existence of expected disjointWith relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "kice" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "kice" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (:filter (!= ?/bioentity1 ~child-concept-id-obo))
                                                   (?/bioentity1 owl/disjointWith ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                expected-disjointwith-links))

    ;; test existence of expected owl:equivalentClass relations
    (doall (map (fn [link] (let [child-concept-id-ccp (symbol "kice" (first link))
                                 child-concept-id-obo (symbol "obo" (first link))
                                 parent-concept-id (symbol "kice" (last link))]
                             (is (ask source-kb `((~child-concept-id-ccp obo/IAO_0000219 ~child-concept-id-obo)
                                                   (~child-concept-id-ccp obo/IAO_0000219 ?/bioentity1)
                                                   (:filter (!= ?/bioentity1 ~child-concept-id-obo))
                                                   (?/bioentity1 owl/equivalentClass ?/bioentity2)
                                                   (~parent-concept-id obo/IAO_0000219 ?/bioentity2))))))
                expected-equivalent-class-links))


    ;; test rdfs:domain links
    (doall (map (fn [link] (let [prop-id-ccp (symbol "kice" (first link))
                                 prop-id-obo (symbol "obo" (first link))
                                 cls-id (symbol "kice" (last link))]
                             (is (ask source-kb `((~prop-id-ccp obo/IAO_0000219 ~prop-id-obo)
                                                   (~prop-id-ccp obo/IAO_0000219 ?/bioprop)
                                                   (:filter (!= ?/bioprop ~prop-id-obo))


                                                   (?/bioprop rdfs/domain ?/biodomain)
                                                   (~cls-id obo/IAO_0000219 ?/biodomain))))))
                expected-rdfs-domain-links))

    ;; test rdfs:range links
    (doall (map (fn [link] (let [prop-id-ccp (symbol "kice" (first link))
                                 prop-id-obo (symbol "obo" (first link))
                                 cls-id (symbol "kice" (last link))]
                             (is (ask source-kb `((~prop-id-ccp obo/IAO_0000219 ~prop-id-obo)
                                                   (~prop-id-ccp obo/IAO_0000219 ?/bioprop)
                                                   (:filter (!= ?/bioprop ~prop-id-obo))
                                                   (?/bioprop rdfs/range ?/biodomain)
                                                   (~cls-id obo/IAO_0000219 ?/biodomain))))))
                expected-rdfs-range-links))


    (doall (map (fn [props] (let [child-prop-id-ccp (symbol "kice" (first props))
                                  child-prop-id-obo (symbol "obo" (first props))
                                  parent-prop-id (symbol "kice" (last props))]
                              (is (ask source-kb `((~child-prop-id-ccp obo/IAO_0000219 ~child-prop-id-obo)
                                                    (~child-prop-id-ccp obo/IAO_0000219 ?/biorelation1)
                                                    (:filter (!= ?/biorelation1 ~child-prop-id-obo))
                                                    (?/biorelation1 rdfs/subPropertyOf ?/biorelation2)
                                                    (~parent-prop-id obo/IAO_0000219 ?/biorelation2))))))
                expected-subpropertyof-links))

    (doall (map (fn [props] (let [child-prop-id-ccp (symbol "kice" (first props))
                                  child-prop-id-obo (symbol "obo" (first props))
                                  parent-prop-id (symbol "kice" (last props))]
                              (is (ask source-kb `((~child-prop-id-ccp obo/IAO_0000219 ~child-prop-id-obo)
                                                    (~child-prop-id-ccp obo/IAO_0000219 ?/biorelation1)
                                                    (:filter (!= ?/biorelation1 ~child-prop-id-obo))
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


    (prn (str "rr--------------------------------"))
    (doall (map #(prn (str "RESTRICTIONS === " %)) (sparql-query source-kb
                                                          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                           prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                           prefix obo: <http://purl.obolibrary.org/obo/>
                                                           prefix owl: <http://www.w3.org/2002/07/owl#>
                                                           prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                           select ?r ?g {
                                                             graph ?g {
                                                                ?r rdf:type owl:Restriction .
                                                            }
                                                            } order by ?g")))

    (prn (str "rr--------------------------------"))


    (prn (str "s--------------------------------"))
    (doall (map #(prn (str "AS SUBJ === " %)) (sparql-query source-kb
                                                           "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                            prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                            prefix obo: <http://purl.obolibrary.org/obo/>
                                                            prefix owl: <http://www.w3.org/2002/07/owl#>
                                                            prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                            prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                            select ?p ?o {
                                                             kbio:go_genid226412 ?p ?o .
                                                             }")))

    (prn (str "s--------------------------------"))

    (prn (str "o--------------------------------"))
    (doall (map #(prn (str "AS OBJ === " %)) (sparql-query source-kb
                                                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                             prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                             prefix obo: <http://purl.obolibrary.org/obo/>
                                                             prefix owl: <http://www.w3.org/2002/07/owl#>
                                                             prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                             prefix kbio: <http://ccp.ucdenver.edu/kabob/bio/>
                                                             select ?s ?p {
                                                              ?s ?p kbio:go_genid226412 .
                                                              }")))

    (prn (str "o--------------------------------"))

    (run-build-rule source-kb target-kb build-rules-step-gcc 0)

    (prn (str "--------------------------------"))
    (doall (map #(prn (str "GC GEN === " %)) (sparql-query target-kb
                                                          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                           prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                           prefix obo: <http://purl.obolibrary.org/obo/>
                                                           prefix owl: <http://www.w3.org/2002/07/owl#>
                                                           prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                           select ?s ?p ?o {
                                                            ?s ?p ?o .
                                                            }")))

    (prn (str "--------------------------------"))



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

    (prn (str "--------------------------------"))
    (doall (map #(prn (str "QUERY === " %)) (sparql-query target-kb
                                                          "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                                           prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                                           prefix obo: <http://purl.obolibrary.org/obo/>
                                                           prefix owl: <http://www.w3.org/2002/07/owl#>
                                                           prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                                           select ?s ?o {
                                                            ?s owl:onProperty ?o .
                                                            }")))

    (prn (str "--------------------------------"))


    (is (= 3
           (count (query target-kb '((?/s owl/equivalentClass ?/o))))))

    (is (= 2
           (count (query target-kb '((?/s owl/intersectionOf ?/o))))))

    ;; there are 10, but 4 are in the ccp-extension graph
    (is (= 6
           (count (query target-kb '((?/s rdf/first ?/o))))))

    ;; this are rdf/rest links to non-nil nodes; there are 5, but 2 are in the ccp-extension graph
    (is (= 3
           (count (query target-kb '((?/s rdf/rest ?/o))))))

    ;; 3 total, but only 1 not in the ccp-extension graph
    (is (= 1
           (count (query target-kb '((?/s owl/propertyChainAxiom ?/o))))))


    ;; 4 triples for the rule metadata
    (is (= (+ 4 3 2 6 3 1 8
              (count (concat expected-disjointwith-links expected-subclassof-links
                             expected-inverseof-links expected-rdfs-domain-links
                             expected-rdfs-range-links expected-subpropertyof-links))
              ;; * 2 b/c for each restriction there is an asserted onProperty & someValuesFrom relation
              (* 2 (count (concat
                            expected-restrictions expected-restrictions-in-lists))))
           (count (query target-kb '((?/s ?/p ?/o))))))


    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-gcc 0)
      (close log-kb))



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






;; validate list structures
(deftest step-gc-test-with-validation-rules-lists
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-gca)
    (run-build-rules source-kb build-rules-step-gcb)
    (run-build-rules source-kb build-rules-step-gcc)

    (run-build-rule source-kb target-kb validation-rules-list 0)
    ;; add 4 for the rule metadata
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 1)
    ;; add 4 for the rule metadata
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 2)
    ;; add 4 for the rule metadata
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 3)
    ;; add 4 for the rule metadata
    (is (= 16 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 4)
    ;; add 4 for the rule metadata
    (is (= 20 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 5)
    ;; add 4 for the rule metadata
    (is (= 24 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-list 6)
    ;; add 4 for the rule metadata
    (is (= 28 (count (query target-kb '((?/s ?/p ?/o))))))))


;; validate restriction structures
(deftest step-gc-test-with-validation-rules-restrictions
  (let [source-kb base-kb
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-gca)
    (run-build-rules source-kb build-rules-step-gcb)
    (run-build-rules source-kb build-rules-step-gcc)

    (run-build-rule source-kb target-kb validation-rules-restriction 0)
    ;; add 4 for the rule metadata
    (is (= 4 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 1)
    ;; add 4 for the rule metadata
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 2)
    ;; add 4 for the rule metadata
    (is (= 12 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 3)
    ;; add 4 for the rule metadata
    (is (= 16 (count (query target-kb '((?/s ?/p ?/o))))))

    (run-build-rule source-kb target-kb validation-rules-restriction 4)
    ;; add 4 for the rule metadata
    (is (= 20 (count (query target-kb '((?/s ?/p ?/o))))))))



