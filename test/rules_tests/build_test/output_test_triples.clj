(ns rules-tests.build_test.output_test_triples
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
           [java.util.Arrays]
           (org.openrdf.rio Rio RDFFormat)
           (org.openrdf.rio.helpers StatementCollector)
           (java.util ArrayList)))









(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))

;; output triples to a local file
;(deftest output-triples
;  (let [log-kb (output-kb "/tmp/triples.nt")
;        src-kb (test-kb initial-plus-ice-triples)]
;
;    ;; add sample triples to the log kb
;    (dorun (map (partial add! log-kb) initial-plus-ice-triples))
;
;    ;(run-build-rule src-kb log-kb build-rules-step-a 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-a 1)
;    ;(run-build-rule src-kb log-kb build-rules-step-a 2)
;    ;(run-build-rules src-kb build-rules-step-a)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-b 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-b 1)
;    ;(run-build-rule src-kb log-kb build-rules-step-b 2)
;    ;(run-build-rules src-kb build-rules-step-b)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-ca 0)
;    ;(run-build-rules src-kb build-rules-step-ca)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 1)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 2)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 3)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 4)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 5)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 6)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 7)
;    ;(run-build-rule src-kb log-kb build-rules-step-cb 8)
;    ;(run-build-rules src-kb build-rules-step-cb)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-cc 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-cc 1)
;    ;(run-build-rules src-kb build-rules-step-cc)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-da 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-da 1)
;    ;(run-build-rule src-kb log-kb build-rules-step-da 2)
;    ;(run-build-rule src-kb log-kb build-rules-step-da 3)
;    ;(run-build-rule src-kb log-kb build-rules-step-da 4)
;    ;(run-build-rule src-kb log-kb build-rules-step-da 5)
;    ;(run-build-rules src-kb build-rules-step-da)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-db 0)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 1)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 2)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 3)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 4)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 5)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 6)
;    ;(run-build-rule src-kb log-kb build-rules-step-db 7)
;    ;(run-build-rules src-kb build-rules-step-db)
;    ;
;    ;(run-build-rule src-kb log-kb build-rules-step-dc 0)
;    ;(run-build-rules src-kb build-rules-step-dc)
;
;    (with-tmp-dir
;      ;; generate identifier set ntriple files and load into the source-kb
;      (generate-all-id-sets src-kb (str tmp-dir "/"))
;      (prn (str "PRINTING FILE LIST: " (count (get-only-files tmp-dir))))
;      (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
;                    (load-rdf src-kb (java.util.zip.GZIPInputStream.
;                                          (clojure.java.io/input-stream
;                                            f)) :ntriple))
;                  (get-only-files tmp-dir)))
;      (dorun (map (fn [f] (let [inputstream (java.util.zip.GZIPInputStream.
;                                              (clojure.java.io/input-stream
;                                                f))
;                                rdf-parser (Rio/createParser RDFFormat/NTRIPLES)
;                                stmts (ArrayList.)
;                                collector (StatementCollector. stmts)]
;                            (prn (str "FILE TO LOAD:" f))
;                            (.setRDFHandler rdf-parser collector)
;                            (.parse rdf-parser inputstream "http://ex/")
;                            (dorun (map (partial add! log-kb) stmts))))
;                  (get-only-files tmp-dir))))
;
;    (run-build-rule src-kb log-kb build-rules-step-fa 0)
;    (run-build-rule src-kb log-kb build-rules-step-fa 1)
;    (run-build-rules src-kb build-rules-step-fa)
;
;    (run-build-rule src-kb log-kb build-rules-step-fb 0)
;    (run-build-rules src-kb build-rules-step-fb)
;
;    (run-build-rule src-kb log-kb build-rules-step-ga 0)
;    (run-build-rule src-kb log-kb build-rules-step-ga 1)
;    (run-build-rule src-kb log-kb build-rules-step-ga 2)
;    (run-build-rule src-kb log-kb build-rules-step-ga 3)
;    (run-build-rules src-kb build-rules-step-ga)
;
;    (run-build-rule src-kb log-kb build-rules-step-gb 0)
;    (run-build-rules src-kb build-rules-step-gb)
;
;    (run-build-rule src-kb log-kb build-rules-step-gc 0)
;    (run-build-rule src-kb log-kb build-rules-step-gc 1)
;    (run-build-rules src-kb build-rules-step-gc)
;
;    (run-build-rule src-kb log-kb build-rules-step-ha 0)
;    (run-build-rule src-kb log-kb build-rules-step-ha 1)
;    (run-build-rule src-kb log-kb build-rules-step-ha 2)
;    (run-build-rule src-kb log-kb build-rules-step-ha 3)
;    (run-build-rule src-kb log-kb build-rules-step-ha 4)
;    (run-build-rule src-kb log-kb build-rules-step-ha 5)
;    (run-build-rule src-kb log-kb build-rules-step-ha 6)
;    (run-build-rule src-kb log-kb build-rules-step-ha 7)
;    (run-build-rule src-kb log-kb build-rules-step-ha 8)
;    (run-build-rule src-kb log-kb build-rules-step-ha 9)
;    (run-build-rules src-kb build-rules-step-ha)
;
;    (run-build-rule src-kb log-kb build-rules-step-hb 0)
;    (run-build-rule src-kb log-kb build-rules-step-hb 1)
;    (run-build-rule src-kb log-kb build-rules-step-hb 2)
;    (run-build-rules src-kb build-rules-step-hb)
;
;    (run-build-rule src-kb log-kb build-rules-step-hca 0)
;    (run-build-rules src-kb build-rules-step-hca)
;
;    (run-build-rule src-kb log-kb build-rules-step-hcb 0)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 1)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 2)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 3)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 4)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 5)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 6)
;    (run-build-rule src-kb log-kb build-rules-step-hcb 7)
;    (run-build-rules src-kb build-rules-step-hcb)
;
;    (close log-kb)))





