(ns rules-tests.build_test.test_build_step_f
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
                                                            concepts object-properties ice-identifiers obsolete-ice-identifiers]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))



;;; This tests that the individual identifiers each denote a bioentity
(deftest step-fa-bioentity-generation-test-id-denotes-bioentity
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

    ;; run the identifier-denotes-bioentity rule
    (run-build-rule source-kb target-kb build-rules-step-fa 0)


    ;; each concept identifier should denote a bioentity
    (doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)]
                                (is (ask target-kb `((~ccp-id obo/IAO_0000219 ?/bioentity)))))) ; obo:denotes
                concepts))

    ;; each concept identifier should denote a bioentity
    (doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)]
                                (is (ask target-kb `((~ccp-id obo/IAO_0000219 ?/bioentity)))))) ; obo:denotes
                ice-identifiers))

    ;; each property identifier should denote a bioentity
    (doall (map (fn [prop] (let [ccp-id (symbol "ccp" prop)]
                             (is (ask target-kb `((~ccp-id obo/IAO_0000219 ?/bioentity)))))) ; obo:denotes
                object-properties))


    ;; include the 7 uniprot identifiers brought in by the uniprot ice rdf (referenced as interactors with P37173)
    (is (= (+ 7 (count (distinct (concat concepts object-properties ice-identifiers))))
           (count (query target-kb '((?/x obo/IAO_0000219 ?/y)))))) ; obo:denotes

    (let [log-kb (output-kb "/tmp/triples.nt")]

      (run-build-rule source-kb log-kb build-rules-step-fa 0)

      (close log-kb))

    ))


;;; This tests that each id-set mentions the bioentity denoted by its member ids
(deftest step-fa-bioentity-generation-test-idset-mentions-bioentity
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


    ;; run the identifier-denotes-bioentity rule
    (run-build-rule source-kb source-kb build-rules-step-fa 0)
    (run-build-rule source-kb source-kb build-rules-step-fa 1)


    ;; each concept identifier should denote a bioentity
    (doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)]
                                (is (ask source-kb `((?/id_set obo/RO_0002351 ~ccp-id)
                                                      (~ccp-id obo/IAO_0000219 ?/bioentity) ; obo:denotes
                                                      (?/id_set obo/IAO_0000142 ?/bioentity)))))) ; obo:mentions
                concepts))

    ;; each concept identifier should denote a bioentity
    (doall (map (fn [concept] (let [ccp-id (symbol "ccp" concept)]
                                (is (ask source-kb `((?/id_set obo/RO_0002351 ~ccp-id)
                                                      (~ccp-id obo/IAO_0000219 ?/bioentity) ; obo:denotes
                                                      (?/id_set obo/IAO_0000142 ?/bioentity)))))) ; obo:mentions
                ice-identifiers))

    ;; each property identifier should denote a bioentity
    (doall (map (fn [prop] (let [ccp-id (symbol "ccp" prop)]
                             (is (ask source-kb `((?/id_set obo/RO_0002351 ~ccp-id)
                                                   (~ccp-id obo/IAO_0000219 ?/bioentity) ; obo:denotes
                                                   (?/id_set obo/IAO_0000142 ?/bioentity)))))) ; obo:mentions
                object-properties))


    (run-build-rule source-kb target-kb build-rules-step-fa 0)
    (run-build-rule source-kb target-kb build-rules-step-fa 1)


    ;; there are 17 identifiers that take part in 5 combined id_sets, so 17-5=12 fewer identifier sets than if each identifier
    ;; had its own is set. See the combined sets in test_build_step_e/step-e-identifier-merge-test-generation-of-merged-concept-identifier-sets
    ;(is (= (- (count (distinct (concat concepts ice-concepts object-properties))) 12)
    ;       (count (query target-kb '((?/x obo/IAO_0000142 ?/y)))))) ; obo:denotes
    ; TODO: the above count comparison is off by one 133/132. Can't figure out why.

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;
    ;  (run-build-rule source-kb log-kb build-rules-step-f 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-f 1)
    ;
    ;  (close log-kb))

    ))


;;; This tests that each id-set mentions the bioentity denoted by its member ids
(deftest step-fb-test-obsolete-identifier-bioentity-linking
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
    (run-build-rule source-kb source-kb build-rules-step-fb 0)

    ;; the 4 secondary uniprot accessions should point to the bioentity denoted by P37173

    (is (ask source-kb `((ccp/UNIPROT_P37173 obo/IAO_0000219 ?/bioentity) ; obo:denotes
                          (?/id_set obo/IAO_0000142 ?/bioentity) ; obo:mentions
                          ccp/UNIPROT_B4DTV5 obo/IAO_0000219 ?/bioentity)))  ; obo:denotes

    (is (ask source-kb `((ccp/UNIPROT_P37173 obo/IAO_0000219 ?/bioentity) ; obo:denotes
                          (?/id_set obo/IAO_0000142 ?/bioentity) ; obo:mentions
                          ccp/UNIPROT_Q15580 obo/IAO_0000219 ?/bioentity)))  ; obo:denotes

    (is (ask source-kb `((ccp/UNIPROT_P37173 obo/IAO_0000219 ?/bioentity) ; obo:denotes
                          (?/id_set obo/IAO_0000142 ?/bioentity) ; obo:mentions
                          ccp/UNIPROT_Q6DKT6 obo/IAO_0000219 ?/bioentity)))  ; obo:denotes

    (is (ask source-kb `((ccp/UNIPROT_P37173 obo/IAO_0000219 ?/bioentity) ; obo:denotes
                          (?/id_set obo/IAO_0000142 ?/bioentity) ; obo:mentions
                          ccp/UNIPROT_Q99474 obo/IAO_0000219 ?/bioentity)))  ; obo:denotes


    (run-build-rule source-kb target-kb build-rules-step-fb 0)
    (is (= (count obsolete-ice-identifiers)
           (count (query target-kb '((?/x obo/IAO_0000219 ?/y)))))) ; obo:denotes

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;
    ;  (run-build-rule source-kb log-kb build-rules-step-f 0)
    ;  (run-build-rule source-kb log-kb build-rules-step-f 1)
    ;
    ;  (close log-kb))

    ))





