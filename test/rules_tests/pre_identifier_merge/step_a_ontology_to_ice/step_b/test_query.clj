(ns rules-tests.pre-identifier-merge.step-a-ontology-to-ice.step-b.test-query
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule-sparql-string]]
             [kr.core.forward-rule :refer [add-reify-fns]]
             [kr.core.sparql :refer [sparql-select-query sparql-query]]
             [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
             [kr.core.kb :refer [kb open close]]
             [kabob.core.namespace :refer [*namespaces*]]
             [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
             [kabob.build.output-kb :refer [output-kb]]
             [kabob.build.input-kb :refer [open-kb]]
             [clojure.pprint :refer [pprint]]))

;(deftest test-query
;  (let [source-kb (open-kb {:server-url "http://amc-tantor:10034"
;                             :repo-name "kabobhuman"
;                             :username "test"
;                             :password "xyzzy"
;                             :is-virtuoso "false"})]
;
;    (prn (str "QUERY FOR ?a..."))
;    (prn-str (str "RESULT COUNT: " (count (sparql-query source-kb "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
;                                select ?a
;                                {
;                                   ?a rdfs:subClassOf* ccp:IAO_EXT_0000088 .
;                                } limit 10"))))))

