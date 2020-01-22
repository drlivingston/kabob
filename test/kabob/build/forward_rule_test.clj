(ns kabob.build.forward-rule-test
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
             ;;[kabob.core.kabob]
             [clojure.pprint :refer [pprint]]))


(def sample-rule
  `{:name "sample-rule-1"
    :description "For every rdfs/subClassOf relation make a corresponding ex/sc relation."
    :head ((?/s ex/sc ?/o))
    :reify ()
    :body (?/s rdfs/subClassOf ?/o)
    :options {}
    })

(def sample-kb-triples
  `((ex/a rdfs/subClassOf ex/b)
    (ex/b rdfs/subClassOf ex/c)
    (ex/c rdfs/subClassOf ex/d)
    (ex/d rdfs/subClassOf ex/e)))                         
                                  
(def new-triples '())

(def result-triples '())

(defn test-kb [triples]
  "initializes an empty kb"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))

(deftest test-run-forward-rule
 (let [rule sample-rule
       source-kb (test-kb sample-kb-triples)
       target-kb (test-kb '())] ;; source kb contains sample triples
    
    (run-forward-rule source-kb target-kb rule)

    ;; there should be 4 instances of ex/sc
    (is (= 4 (count (query target-kb '((?/s ex/sc ?/o))))))

    ;; there should be 8 triples total
    (is (= 8 (count (query target-kb '((?/s ?/p ?/o))))))

    ;; one of the 8 triples should contain the triple count
    (is (= 1 (count (query target-kb '((?/s ccp/IAO_EXT_0000328 ?/o)))))) ;; ccp:query hit count
    
;; The code fragment below is useful for debugging as it writes
    ;; triples to a local file.
    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  ;; add sample triples to the log kb
    ;  ;(dorun (map (partial add! log-kb) sample-kb-triples))
    ;  (run-forward-rule source-kb log-kb rule)
    ;  (close log-kb))
    
    ))
