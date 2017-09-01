
;; This script will evaluate a SPARQL command using a boolean query on a specified KB.
;; Queries are extracted from files on the classpath, similar to the KaBOB rule machinery.

(ns kabob.build.eval-sparql
  (:gen-class)
  (use kr.core.kb
       kr.core.rdf
       kabob.core.rule
       kr.core.sparql
       clojure.pprint
       [kabob.build.input-kb :refer [source-kb]])
  (require [clojure.set :as set]))

;;; --------------------------------------------------------
;;; arguments and helpers
;;; --------------------------------------------------------

(defn command-line-args [original-args]
  {;; The URL to the Rdf4j server to query
   :server-url (nth original-args 0)
   ;; The name of the repository to connect to
   :repo-name (nth original-args 1)
   ;; The username to use when connecting
   :username (nth original-args 2)
   ;; The password to use when connecting
   :password (nth original-args 3)
   ;; if set to 'true' then the query will be run using Virtuoso-specific tooling
   :is-virtuoso (nth original-args 4)

   ;; Name of the queries to run (must point to files available on the
   ;; classpath; NOTE that it should not start with a forward slash)
   :query-names (drop 5 original-args)})

(defn logging-header [args]
  (prn "Evaluating SPARQL for: " (:query-names args))
  (prn (str "Clojure version: " *clojure-version*))
  (prn (str "Server URL: " (:server-url args)))
  (prn (str "Repo name: " (:repo-name args)))
  (prn (str "Passed in select limit: " (:sparql-limit args)))
  (prn (str "Is Virtuoso?: " (:is-virtuoso args))))

(defn logging-rules [rules]
  (println "SPARQL TO EVAL:")
  ;;(pprint rules)
  (pprint (map (fn [rule]
                 [(meta rule) rule])
               rules))
  (println "------"))

(defn read-rules [args]
  (mapcat kabob.core.rule/kabob-load-rules-from-classpath
          (:query-names args)))


(defn eval-sparql [source-kb query]
  (let [sparql-string (:sparql-string query)]
    (println (str "sparql to evaluate:" sparql-string))
    (binding [*kb* source-kb]
      (println "running:")
      (boolean-sparql source-kb sparql-string))))


(defn primary-eval-sparql [args query]
  (binding [kr.core.rdf/*use-inference* false]
    (prn (str "Evaluating SPARQL: " (:name query)))
    (let [source-connection (source-kb args)]
      (try (time (eval-sparql source-connection query))
           true
           (finally (close source-connection))))))

(defn process-boolean-queries [args queries]
  (dorun
    (map (fn [query] (time (primary-eval-sparql args query)))
         queries)))

(defn commandline-process-boolean-queries [args]
  (logging-header args)
  (let [queries (read-rules args)]
    ;(logging-rules queries)
    (process-boolean-queries args queries)))

(defn -main [& args]
  (println "global:")
  (pprint *command-line-args*)
  (println "local:")
  (pprint args)
  (commandline-process-boolean-queries (command-line-args args))
  (System/exit 0))
