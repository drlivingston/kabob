(ns kabob.build.test-triple-load-with-unknown-ns
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule]]
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



(deftest test-triple-load
  "There is a bug when loading triples that use a namespace that is not enumerated in kabob.core.namespaces. For cases
  when the URI with unknown namespace is the object in the triple being loaded, the loaded URI is missing a slash, i.e.
  http:/ is used instead of http://."
  (let [source-kb (test-kb sample-kb-triples)] ;; source kb contains sample triples

    ;; there are two instances b/c there are two possible combinations of ?/han_node_1 and ?/han_node_2. You could fix this using FILTER( STR(IRI(?node1)) < STR(IRI(?node2)) but I'm not sure if those have been implemented in this branch of the code. They are in Elizabeth's branch of kabob somewhereI believe.
    ;(is (= 1 (count (query source-kb
    ;                       '((?/bnode owl/members bnode/cl_genid20409))))))

    ;; the following auery demonstrates that the URI is well formed within the triple store
    (is (= 1 (count (query source-kb
                           '((?/bnode owl/members bnode/cl_genid20409))))))

    ;; TODO: come up with a better way to test this
    ;; looking at the output file specified below confirms that use of the bnode namespace serves as a workaround
    (let [log-kb (output-kb "/tmp/triples.nt")]
      ;; add sample triples to the log kb
      (dorun (map (partial add! log-kb) sample-kb-triples))
      (close log-kb))
    ))