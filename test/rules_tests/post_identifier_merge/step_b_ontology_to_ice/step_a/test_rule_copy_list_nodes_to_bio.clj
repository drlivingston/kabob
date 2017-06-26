(ns rules-tests.post-identifier-merge.step-b-ontology-to-ice.step-a.test-rule-copy-list-nodes-to-bio
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
             [kr.core.forward-rule :refer [add-reify-fns]]
             [kr.core.sparql :refer [sparql-select-query query]]
             [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
             [kr.core.kb :refer [kb open close]]
             [kabob.core.namespace :refer [*namespaces*]]
             [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
             [kabob.build.output-kb :refer [output-kb]]
             [clojure.pprint :refer [pprint]]))


(def sample-kb-triples '((bnode/cl_genid20404 rdf/type owl/AllDisjointClasses)
                          (bnode/cl_genid20404 owl/members bnode/cl_genid20409)
                          (bnode/cl_genid20409 rdf/type rdf/List)
                          (bnode/cl_genid20409 rdf/first bnode/cl_genid20410)
                          (bnode/cl_genid20410 rdf/type owl/Restriction)
                          (bnode/cl_genid20410 owl/onProperty obo/BFO_0000050)
                          (bnode/cl_genid20410 owl/someValuesFrom obo/UBERON_0001885)
                          (bnode/cl_genid20409 rdf/rest bnode/cl_genid20407)
                          (bnode/cl_genid20407 rdf/type rdf/List)
                          (bnode/cl_genid20407 rdf/first bnode/cl_genid20408)
                          (bnode/cl_genid20408 rdf/type owl/Restriction)
                          (bnode/cl_genid20408 owl/onProperty obo/BFO_0000050)
                          (bnode/cl_genid20408 owl/someValuesFrom obo/UBERON_0001954)
                          (bnode/cl_genid20407 rdf/rest bnode/cl_genid20405)
                          (bnode/cl_genid20405 rdf/type rdf/List)
                          (bnode/cl_genid20405 rdf/first bnode/cl_genid20406)
                          (bnode/cl_genid20406 rdf/type owl/Restriction)
                          (bnode/cl_genid20406 owl/onProperty obo/BFO_0000050)
                          (bnode/cl_genid20406 owl/someValuesFrom obo/UBERON_0002191)
                          (bnode/cl_genid20405 rdf/rest rdf/nil)))

(def new-triples '())

(def result-triples '())

(defn test-kb [triples]
  "initializes an empty kb"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))



(deftest test-rule
  (let [rule1 (first (filter #(= (:name %) "copy-list-nodes-to-bio")
                              (kabob-load-rules-from-classpath "rules/post_identifier_merge/step_b_ontology_to_bio/step_a")))
        rule2 (first (filter #(= (:name %) "copy-owl-restriction-to-bio")
                             (kabob-load-rules-from-classpath "rules/post_identifier_merge/step_b_ontology_to_bio/step_a")))
        rule3 (first (filter #(= (:name %) "copy-owl-alldisjointclasses-to-bio")
                             (kabob-load-rules-from-classpath "rules/post_identifier_merge/step_b_ontology_to_bio/step_a")))
        source-kb (test-kb sample-kb-triples)] ;; source kb contains sample triples

    (run-forward-rule-sparql-string source-kb source-kb rule1)
    (run-forward-rule-sparql-string source-kb source-kb rule2)
    (run-forward-rule-sparql-string source-kb source-kb rule3)

    ;; there should be 3 list_recored instances
    (is (= 3 (count (query source-kb '((?/list_record rdf/type ccp/IAO_EXT_0000317)))))) ;; ccp:RDF list record

    ;; test the complete existence of one of the expected list_records
    ;; return 2 instead of 1 b/c the iao:denotes
    (is (= 1 (count (query source-kb '((?/list_record rdf/type ccp/IAO_EXT_0000317)
                                        (?/list_record obo/BFO_0000051 ccp/F_6M_Y8o9Ro7Q4xt6jKcLSUsZ-D8g)
                                        (ccp/F_6M_Y8o9Ro7Q4xt6jKcLSUsZ-D8g rdf/type ccp/IAO_EXT_0000346) ;; ccp:RDF list identifier field value
                                        (ccp/F_6M_Y8o9Ro7Q4xt6jKcLSUsZ-D8g rdf/type ccp/ID_aPvFdJzcE7zuZolQo-0bfXOLsnA)
                                        (ccp/ID_aPvFdJzcE7zuZolQo-0bfXOLsnA rdf/type ccp/IAO_EXT_0000354)
                                        (ccp/ID_aPvFdJzcE7zuZolQo-0bfXOLsnA obo/IAO_0000219 bnode/cl_genid20409)
                                        (ccp/ID_aPvFdJzcE7zuZolQo-0bfXOLsnA obo/IAO_0000219 ccp/L_aPvFdJzcE7zuZolQo-0bfXOLsnA) ;; obo:denotes
                                        (ccp/L_aPvFdJzcE7zuZolQo-0bfXOLsnA rdf/type rdf/List))))))


    (is (= 1 (count (query source-kb '((?/list_record rdf/type ccp/IAO_EXT_0000347)))))) ;; ccp:AllDisjointClasses record
    (is (= 3 (count (query source-kb '((?/list_record rdf/type ccp/IAO_EXT_0000305)))))) ;; ccp:OWL restriction record



    ;;; The code fragment below is useful for debugging as it writes
    ;;; triples to a local file.
    (let [log-kb (output-kb "/tmp/triples.nt")]
      ;; add sample triples to the log kb
      ;;(dorun (map (partial add! log-kb) sample-kb-triples))

      (run-forward-rule-sparql-string source-kb log-kb rule1)
      (run-forward-rule-sparql-string source-kb log-kb rule2)
      (run-forward-rule-sparql-string source-kb log-kb rule3)
      (close log-kb))
    ))