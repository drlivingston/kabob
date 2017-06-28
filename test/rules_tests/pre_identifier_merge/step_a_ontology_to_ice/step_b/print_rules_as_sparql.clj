(ns rules-tests.pre-identifier-merge.step-a-ontology-to-ice.step-b.print-rules-as-sparql
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require  [kabob.build.run-rules :refer [query-variables run-forward-rule run-forward-rule-sparql-string]]
             [kr.core.forward-rule :refer [add-reify-fns]]
             [kr.core.sparql :refer [sparql-select-query query]]
             [kr.core.rdf :refer [register-namespaces synch-ns-mappings add!]]
             [kr.core.kb :refer [kb open close *kb*]]
             [kabob.core.namespace :refer [*namespaces*]]
             [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
             [kabob.build.output-kb :refer [output-kb]]
             [clojure.pprint :refer [pprint]]))


(defn test-kb [triples]
  "initializes an empty kb"
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))

;(defn print-rule [rule]
;  (let [{head :head body :body reify :reify :as rule} rule
;        query-vars (query-variables rule)
;        source-kb (test-kb `())]
;    (prn (str "RULE: " rule))
;    (prn (str "QVARS: " query-vars))
;    (prn (str "BODY: " body))
;    (binding [*kb* source-kb]
;      (prn (str (sparql-query-body body))))))
;    ;(println (sparql-select-query body
;    ;                              {:select-vars query-vars})))))

(defn rr [rule]
  (let [kb (test-kb `())]
    (prn (str (:name rule)))
    (if (:sparql-string rule) (run-forward-rule-sparql-string kb kb rule)
  (run-forward-rule kb kb rule))
  ))

(deftest test-rule
  (let [rules (kabob-load-rules-from-classpath
                "rules/post_identifier_merge/step_c_ice_to_bio/step_b_bioentity_linking/irefweb")]

    (doall (map rr rules))

    ))
