(ns rules-tests.build_test.test_build_step_e
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
                                                            build-rules-step-c build-rules-step-da build-rules-step-db
                                                            concepts object-properties]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))



;;; This tests the ID merging queries/code for concept identifiers that are linked with skos:exactMatch
(deftest step-e-identifier-merge-test-generation-of-merged-concept-identifier-sets
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)
    (with-tmp-dir
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (prn (str "PRINTING FILE LIST: " (count (get-only-files tmp-dir))))
      (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                    (load-rdf target-kb (java.util.zip.GZIPInputStream.
                                          (clojure.java.io/input-stream
                                            f)) :ntriple))
                  (filter #(.contains (.getName %) "combined-concepts") (get-only-files tmp-dir))))


      ;;; there should be X identifier sets. One for each ontology concept except for any that have been merged, so
      ;;; CHEBI:protein/PR:protein and CL:cell/GO:cell will have shared identifier sets. As will BFO_0000050/so#part_of.

      ;;; the identifier for every concept should be a member of an identifier set
      ;(doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)
      ;                                obo-id (symbol "obo" concept)]
      ;                            (is (ask target-kb `((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                                                  (?/id_set obo/RO_0002351 ~ccp-id)))))) ; obo:has_member
      ;            concepts))
      ;
      ;;; the identifier for every object property should be a member of an identifier set
      ;(doall (map (fn [prop] (let [ccp-id (symbol "ccp" prop)
      ;                                obo-id (symbol "obo" prop)]
      ;                            (is (ask target-kb `((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                                                  (?/id_set obo/RO_0002351 ~ccp-id)))))) ; obo:has_member
      ;            object-properties))


      ;; most identifier sets consist of a single identifier, however there are a few that contain multiple identifiers
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/GO_0005623) ; obo:has_member
                            (?/id_set obo/RO_0002351 ccp/CL_0000000)))) ; obo:has_member


      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/PR_000000001)
                            (?/id_set obo/RO_0002351 ccp/CHEBI_36080)))) ; obo:has_member


      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/HGNC_11773)
                            (?/id_set obo/RO_0002351 ccp/HGNC_TGFBR2)
                            (?/id_set obo/RO_0002351 ccp/NCBI_GENE_7048)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/PR_P37173) ; obo:has_member
                            (?/id_set obo/RO_0002351 ccp/UNIPROTENTRYNAME_TGFR2_HUMAN) ; obo:has_member
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P37173)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            ;(?/id_set obo/RO_0002351 ccp/PR_P37173-2) ;; this mapping isn't in the test data
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P37173-2)
                            (?/id_set obo/RO_0002351 ccp/REFSEQ_NP_001020018)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/PR_P37173-1)
                            (?/id_set obo/RO_0002351 ccp/REFSEQ_NP_003233)
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P37173-1)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROTENTRYNAME_1433E_HUMAN) ; obo:has_member
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P62258)))) ; obo:has_member

      (is (= 7 (count (query target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316))))))))) ; ccp:identifier_set




;;; This tests the ID merging queries/code for property identifiers that have been linked with skos:exactMatch
(deftest step-e-identifier-merge-test-generation-of-merged-property-identifier-sets
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)
    (with-tmp-dir
      (generate-all-id-sets source-kb (str tmp-dir "/"))

      (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                    (load-rdf target-kb (java.util.zip.GZIPInputStream.
                                          (clojure.java.io/input-stream
                                            f)) :ntriple))
                  (filter #(.contains (.getName %) "combined-properties") (get-only-files tmp-dir))))

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/mi#part_of)
                            (?/id_set obo/RO_0002351 ccp/so#part_of)
                            (?/id_set obo/RO_0002351 ccp/BFO_0000050)))) ; obo:has_member

      (is (= 1 (count (query target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316))))))))) ; ccp:identifier_set


;;; This tests the ID merging queries/code for non-ontology identifiers that have not been linked with skos:exactMatch
(deftest step-e-identifier-merge-test-generation-of-orphan-identifier-sets
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)

    (with-tmp-dir
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                    (load-rdf target-kb (java.util.zip.GZIPInputStream.
                                          (clojure.java.io/input-stream
                                            f)) :ntriple))
                  (filter #(.contains (.getName %) "orphans-IDSet") (get-only-files tmp-dir))))

      ;; there are two uniprot identifiers that are not involved in any skos:exactMatch relations
      ;; one is its own uniprot record (P62258) and the other is related to P37173 via an interaction sub-record (Q9UER7)

      ; this one is now merged with its entry name
      ;(is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                      (?/id_set obo/RO_0002351 ccp/UNIPROT_P62258)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_Q9UER7)))) ; obo:has_member

      ;; this one is now exactmatch with a refseq id
      ;(is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                      (?/id_set obo/RO_0002351 ccp/UNIPROT_P37173-2)))) ; obo:has_member

      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P62258-1)))) ; obo:has_member


      ;; the following 7 are brought in via interactions with P37173 in the uniprot ice rdf
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P10600)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_Q93074)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_Q62312)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P01137)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_P07200)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_A2AGH6)))) ; obo:has_member
      (is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                            (?/id_set obo/RO_0002351 ccp/UNIPROT_Q8IX30)))) ; obo:has_member


      (is (= 9 (count (query target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316)))))) ; ccp:identifier_set




      ;(prn (str "--------------------------------"))
      ;(doall (map #(prn (str %)) (sparql-query target-kb
      ;                                         "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      ;                                          prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
      ;                                          prefix obo: <http://purl.obolibrary.org/obo/>
      ;                                          prefix owl: <http://www.w3.org/2002/07/owl#>
      ;                                          prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      ;                                          select * {
      ;                                            ?id_set rdf:type ccp:IAO_EXT_0000316 .
      ;                                            ?id_set obo:RO_0002351 ?id .
      ;      }"
      ;                                         )))
      ;
      ;    (prn (str "--------------------------------"))


      ;; throw an exeception to prevent the tmp directory from being deleted so the generated files can be viewed
      ;;(throw (Exception. "my exception message"))
      )))


;;; This tests the ID merging queries/code for ontology identifiers that have not been linked with skos:exactMatch
(deftest step-e-identifier-merge-test-generation-of-obo-orphan-identifier-sets
  (let [source-kb (test-kb initial-plus-ice-triples)
        target-kb (test-kb '())]
    (run-build-rules source-kb build-rules-step-a)
    (run-build-rules source-kb build-rules-step-b)
    (run-build-rules source-kb build-rules-step-c)
    (run-build-rules source-kb build-rules-step-da)
    (run-build-rules source-kb build-rules-step-db)

    (with-tmp-dir
      (generate-all-id-sets source-kb (str tmp-dir "/"))
      (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                    (load-rdf target-kb (java.util.zip.GZIPInputStream.
                                          (clojure.java.io/input-stream
                                            f)) :ntriple))
                  (filter #(.contains (.getName %) "orphans-obo-IDSet") (get-only-files tmp-dir))))

      ;; there are two uniprot identifiers that are not involved in any skos:exactMatch relations
      ;; one is its own uniprot record (P62258) and the other is related to P37173 via an interaction sub-record (Q9UER7)
      ;(is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                      (?/id_set obo/RO_0002351 ccp/UNIPROT_P62258)))) ; obo:has_member
      ;
      ;(is (ask target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
      ;                      (?/id_set obo/RO_0002351 ccp/UNIPROT_Q9UER7)))) ; obo:has_member

      ;; remove properties and concepts that participate in skos:exactMatch relations
      (let [orphan-bioentity-concepts
            (remove #{"CL_0000000" "GO_0005623" "PR_000000001" "CHEBI_36080"
                      "BFO_0000050" "so#part_of" "PR_P37173" "PR_P37173-1" "PR_P37173-2" "HGNC_11773"
                      "mi#part_of"}
                    (concat concepts object-properties))
            ]

        (doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)]
                                    (is (ask target-kb `((?/id_set rdf/type ccp/IAO_EXT_0000316) ; ccp:identifier_set
                                                          (?/id_set obo/RO_0002351 ~ccp-id)))))) ; obo:has_member
                    orphan-bioentity-concepts))

        (is (= (count orphan-bioentity-concepts)
               (count (query target-kb '((?/id_set rdf/type ccp/IAO_EXT_0000316)))))) ; ccp:identifier_set
        )


      ;(prn (str "--------------------------------"))
      ;(doall (map #(prn (str %)) (sparql-query target-kb
      ;                                         "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      ;                                          prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
      ;                                          prefix obo: <http://purl.obolibrary.org/obo/>
      ;                                          prefix owl: <http://www.w3.org/2002/07/owl#>
      ;                                          prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      ;                                          select * {
      ;                                            ?id_set rdf:type ccp:IAO_EXT_0000316 .
      ;                                            ?id_set obo:RO_0002351 ?id .
      ;      }"
      ;                                         )))
      ;
      ;(prn (str "--------------------------------"))

      ;; throw an exeception to prevent the tmp directory from being deleted so the generated files can be viewed
      ;(throw (Exception. "my exception message"))
      )))





