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
  (let [rule (first (filter #(= (:name %) "copy-list-nodes-to-bio")
                              (kabob-load-rules-from-classpath "rules/post_identifier_merge/step_b_ontology_to_ice/step_a")))
        source-kb (test-kb sample-kb-triples)] ;; source kb contains sample triples

    (run-forward-rule-sparql-string source-kb source-kb rule)

    ;; there should be 3 list_recored instances
    (is (= 3 (count (query source-kb '((?/list_record rdf/type ccp/IAO_EXT_0000317)))))) ;; ccp:RDF list record

    ;; test the complete existence of one of the expected list_records
    (is (= 1 (count (query source-kb '((?/list_record obo/IAO_0000219 bnode/cl_genid20409)
                                        (?/list_record rdf/type ccp/IAO_EXT_0000317) ;; ccp:RDF list record
                                        (?/list_record obo/IAO_0000219 ?/bio_listmember) ;; obo:denotes
                                        (?/bio_listmember rdf/Type rdf/List))))))

    ;;; The code fragment below is useful for debugging as it writes
    ;;; triples to a local file.
    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  ;; add sample triples to the log kb
    ;  ;;(dorun (map (partial add! log-kb) sample-kb-triples))
    ;
    ;  (run-forward-rule-sparql-string source-kb log-kb rule)
    ;  (close log-kb))
    ))