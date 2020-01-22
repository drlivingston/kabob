(ns rules-tests.build_test.test_build_step_gb
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
                                                            build-rules-step-ga build-rules-step-gb
                                                            object-properties concepts
                                                            expected-subpropertyof-links expected-inverseof-links
                                                            ]]
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
               (run-build-rules source-kb build-rules-step-fb)
               (run-build-rules source-kb build-rules-step-ga))
               source-kb))

;;; Test that labels of object properties are properly transferred to their bio-world counterparts
(deftest step-gb-test-transfer-ontology-labels
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (prn (str "GB--------------------------------"))
    (doall (map #(prn (str %)) (sparql-query source-kb
                                             "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                              prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                              prefix obo: <http://purl.obolibrary.org/obo/>
                                              prefix owl: <http://www.w3.org/2002/07/owl#>
                                              prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                              select ?id ?s ?label ?bioentity {
                                                    ?s rdfs:label ?label .
                                                    ?id obo:IAO_0000219 ?s .
                                                    filter (?s = obo:CHEBI_10003)
                                                    ?id obo:IAO_0000219 ?bioentity .
                                                    filter (?s != ?bioentity && contains(str(?bioentity),'http://ccp.ucdenver.edu/kabob/bio/'))
                                                    }")))

        (prn (str "GB--------------------------------"))



    (run-build-rule source-kb source-kb build-rules-step-gb 0)


    (prn (str "GB2--------------------------------"))
    (doall (map #(prn (str %)) (sparql-query source-kb
                                             "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                                              prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                              prefix obo: <http://purl.obolibrary.org/obo/>
                                              prefix owl: <http://www.w3.org/2002/07/owl#>
                                              prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                                              select ?id ?s ?label ?bioentity ?label2 {
                                                    ?s rdfs:label ?label .
                                                    ?id obo:IAO_0000219 ?s .
                                                    filter (?s = obo:CHEBI_10003)
                                                    ?id obo:IAO_0000219 ?bioentity .
                                                    filter (?s != ?bioentity && contains(str(?bioentity),'http://ccp.ucdenver.edu/kabob/bio/'))
                                                    ?bioentity rdfs:label ?label2
                                                    }")))

    (prn (str "GB2--------------------------------"))


    (doall (map (fn [prop] (let [ccp-id (symbol "kice" prop)
                                 obo-id (symbol "obo" prop)]
                             (is (ask source-kb `((~ccp-id obo/IAO_0000219 ~obo-id)
                                                   (~ccp-id obo/IAO_0000219 ?/biorelation)
                                                   (:filter (!= ?/biorelation ~obo-id))
                                                   (~obo-id rdfs/label ?/label)
                                                   (?/biorelation rdfs/label ?/label)
                                                   )))))
                object-properties))


    (doall (map (fn [concept] (let [ccp-id (symbol "kice" concept)
                                    obo-id (symbol "obo" concept)]
                                (is (ask source-kb `((~ccp-id obo/IAO_0000219 ~obo-id)
                                                      (~ccp-id obo/IAO_0000219 ?/bioconcept)
                                                      (:filter (!= ?/bioconcept ~obo-id))
                                                      (~obo-id rdfs/label ?/label)
                                                      (?/bioconcept rdfs/label ?/label)
                                                      )))))
                ;; the HGNC_11773 label comes from the hgnc_pr namespace
                concepts))

    (run-build-rule source-kb target-kb build-rules-step-gb 0)
    ;; BFO_0000050 & BFO_0000051 each have two labels (one with an underscore instead of a space)
    ;; The so#part_of label overlaps with one of the BFO_0000051 labels
    ;; so we add two for the two extra labels provided by the BFO properties and subtract 1 for the so#part_of overlap
    ;; TODO: there are some ontology concepts with multiple labels and some concepts that have been merged
    ;; TODO: need to figure out the exact count
    ;(is (= (count (concat concepts object-properties))
    ;       (count (query target-kb '((?/s rdfs/label ?/o))))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-gb 0)
    ;  (close log-kb))

    ))




