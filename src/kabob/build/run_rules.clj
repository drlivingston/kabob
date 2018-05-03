
;; This script will execute rules on the specified KB.
;;
;; Generated triples are output to files in the specified output directory.
;; This script is designed to be run by the clojure:run goal of the maven
;; clojure plugin.
;;
;; Extract rules from files on the classpath.
;;
;; The rule-set-names variable holds the names of rule files available in
;; src/main/resources. Below we map over each rule set name, opening an
;; InputStreamReader to read the rule set file, and pass the open reader to
;; kr.core.rule/load-rules-from-file

(ns kabob.build.run-rules
  (:gen-class)
  (use kr.core.kb
       kr.core.rdf
       kabob.core.rule
       [kr.core.forward-rule :exclude (run-forward-rule)]
       kr.core.sparql
       kr.core.variable
       kr.core.reify
       kr.core.unify
    ;kabob.core.staged-rule
       kabob.core.parallel-utils
       clojure.pprint
       [kabob.build.input-kb :refer [source-kb]]
       kabob.build.output-kb)
  (require [clojure.set :as set]
    ;;kabob.core.kabob
           )
  (:import (org.openrdf.query MalformedQueryException)))

;;; --------------------------------------------------------
;;; arguments and helpers
;;; --------------------------------------------------------

;; NOTE why didn't I just use destructuring here?

(defn command-line-args [original-args]
  {;; The URL to the Rdf4j server to query
   :server-url (nth original-args 0)
   ;; The name of the repository to connect to
   :repo-name (nth original-args 1)
   ;; The username to use when connecting
   :username (nth original-args 2)
   ;; The password to use when connecting
   :password (nth original-args 3) 

   ;; The output directory where generated triple files will be placed. 
   ;; IMPORTANT: The output directory path must end with a forward slash.
   :output-directory (nth original-args 4)

   ;; name of the server implementation, e.g. stardog, blazegraph. Defaults to 'http'
   :server-impl (nth original-args 5)
   
   ;; Names of the rule sets to use (must point to files available on the 
   ;; classpath; NOTE that it should not start with a forward slash)
   ;;e.g. edu/ucdenver/ccp/kabob/build/id_typing/entrez_gene_id_typing_rules.clj
   :rule-set-names (drop 6 original-args)})

(defn logging-header [args]
  (prn "Serializing rule output for: " (:rule-set-names args))
  (prn (str "Clojure version: " *clojure-version*))
  (prn (str "Server URL: " (:server-url args)))
  (prn (str "Repo name: " (:repo-name args)))
  (prn (str "Passed in select limit: " (:sparql-limit args)))
  (prn (str "Output directory: " (:output-directory args)))
  (prn (str "Server implementation: " (:server-impl args)))
  )

(defn logging-rules [rules]
  (println "RULES:")
  ;;(pprint rules)
  (pprint (map (fn [rule]
                 [(meta rule) rule])
               rules))
  (println "------"))

;;; --------------------------------------------------------
;;; source and target kb connections
;;; --------------------------------------------------------

;;TODO change this to default to gzip files
(defn rule-output-kb [base-dir rule]
  (let [output-file (str base-dir (:name rule) ".nt")]
    (output-kb output-file)))
  ;(let [output-file (str base-dir (:name rule) ".nt.gz")]
  ;  (zipped-output-kb output-file)))

;;; --------------------------------------------------------
;;; rules
;;; --------------------------------------------------------

(defn read-rules [args]
  (mapcat kabob.core.rule/kabob-load-rules-from-classpath
          (:rule-set-names args)))

;;; --------------------------------------------------------
;;; modified rule running
;;; --------------------------------------------------------

(defn query-variables [{head :head body :body reify :reify :as rule}]
  (let [needed-variables (set (concat (variables head) (variables reify)))
        body-variables (set (variables body))]
    (set/intersection needed-variables body-variables)))

;; this function adds triples to the target kb describing metadata of
;; processing a rule by creating a KabobRuleMetadata instance and
;; populating it with the time the rule was run, the number of triples
;; that were generated, the time it took to run the rule, and the name
;; of the rule.
(defn add-rule-metadata [target-kb rule-name time-at-run triple-count elapsed-time-in-ms]
  (let [metadata-uri (symbol (binding [*reify-ns* "ccp"
                                *reify-prefix* "RULEMETA_"
                                *reify-suffix* ""]
                               (reify-sha-1 rule-name time-at-run)))
        dateformatter  (doto (java.text.SimpleDateFormat. "yyyy-MM-dd'T'HH:mm:ss'Z'")
                         (.setTimeZone (java.util.TimeZone/getTimeZone "GMT")))
        timestamp (.format dateformatter (java.util.Date. time-at-run))
        date-at-run `[~timestamp xsd/dateTime]]
    (add! target-kb `(~metadata-uri rdf/type ccp/IAO_EXT_0000329)) ;; data about a forward-chaining rule
    (add! target-kb `(~metadata-uri dc/title ~rule-name))
    (add! target-kb `(~metadata-uri ccp/IAO_EXT_0000328 ~triple-count)) ;; ccp:query hit count
    (add! target-kb `(~metadata-uri dc/date ~date-at-run))
    ))

(defn run-forward-rule [source-kb target-kb rule]
  (let [{rule-name :name head :head body :body reify :reify :as rule} (add-reify-fns rule)
        visit-counter (atom 0)
        t (atom (.getTime (java.util.Date.)))
        query-vars (query-variables rule)
        bindings-fn (fn [bindings]
                      (swap! visit-counter inc)
                      (when (= 0 (mod @visit-counter 10000))
                        (System/gc))
                      (when (= 0 (mod @visit-counter 250000))
                        (let [new-t (.getTime (java.util.Date.))]
                          (println @visit-counter " in " (- new-t @t) "ms")
                          (reset! t new-t)))
                      (dorun
                        (map (partial add! target-kb)
                             (doall
                               (subst-bindings head
                                               bindings
                                               (reify-bindings reify ;;-with-fns
                                                               bindings))))))]

    (println "running:")
    (try
      (cond (string? body) (visit-sparql source-kb bindings-fn body)
            :else (query-visit source-kb bindings-fn body {:select-vars query-vars}))
      (catch MalformedQueryException e (str "Malformed query in rule: " rule-name " " (.getMessage e))
                                       (prn (str "Malformed query in rule: " rule-name " " (.getMessage e)))
                                       (throw e)))
    (println "final count: " @visit-counter)
    (let [new-t (.getTime (java.util.Date.))]
      (add-rule-metadata target-kb (:name rule) new-t @visit-counter (- new-t @t)))))


;(defn run-forward-rule-sparql-string [source-kb target-kb rule]
;  "This function is very similar to run-forward-rule, however it takes
;  as input a rule that has an explicitly defined SPARQL string. This
;  can be used for complex SPARQL queryies that are difficult to
;  represent using the DSL defined by the forward rule machinery in
;  this project."
;  (let [{head :head sparql-string :sparql-string reify :reify :as rule} (add-reify-fns rule)
;        visit-counter (atom 0)
;        t (atom (.getTime (java.util.Date.)))]
;    (println "rule query:")
;    (binding [*kb* source-kb]
;      (println sparql-string)
;      (println "running:")
;      (visit-sparql source-kb
;                    (fn [bindings]
;                      (swap! visit-counter inc)
;                      (when (= 0 (mod @visit-counter 10000))
;                        (System/gc))
;                      (when (= 0 (mod @visit-counter 250000))
;                        (let [new-t (.getTime (java.util.Date.))]
;                          (println @visit-counter " in " (- new-t @t) "ms")
;                          (reset! t new-t)))
;                      (dorun
;                       (map (partial add! target-kb)
;                            (doall
;                             (subst-bindings head
;                                             bindings
;                                             (reify-bindings reify
;                                                             bindings))))))
;                    sparql-string)
;      (println "final count: " @visit-counter)
;      (let [new-t (.getTime (java.util.Date.))]
;        (add-rule-metadata target-kb (:name rule) new-t @visit-counter (- new-t @t))))))



;; This needs to check for duplicates and prefer one, but which?
;; This should probably take multiple lists, then defaults and hard-codes can
;; also be given in addition to what's in the rule.
(defn add-magic-prefixes [current-prefixes rule]
  (let [new-prefixes (get (get rule :options {}) :magic-prefixes [])]
    ;; concat returns lazy list, into [] will make this a vector again
    (into [] (concat current-prefixes new-prefixes))))

;;add a flag to options to control inference
(defn requested-rule-inference [rule]
  (let [requested-inference (get (get rule :options {})
                                 :inference
                                 kr.core.rdf/*use-inference*)]
    ;; This probably needs to be a more interesting cond, but for now this
    ;; works
    (if requested-inference
      true
      false)))

;; OTHER MAGIC OPTIONS
;; ["franzOption_queryEngine" "franz:sparql-1.0"]
;; ["franzOption_chunkProcessingAllowed" "franz:no"]
;; ["franzOption_chunkProcessingSize" "franz:500000"]
;; ["franzOption_chunkProcessingSize" "franz:50000"]
;; ["franzOption_chunkProcessingAllowed" "franz:yes"]
;; ["franzOption_chunkProcessingSize" "franz:100000000"]
;; ["franzOption_usePropertyPathCache" "franz:no"]
;; ["franzOption_clauseReorderer" "franz:identity"]
;; ["franzOption_reorderDuringExecution" "franz:yes"]
;; ["franzOption_warmupStringTable" "franz:yes"]
;; ["franzOption_cacheStringsCutoff" "franz:1000"]
;; ["franzOption_logQuery" "franz:yes"]
;; ["franzOption_clauseReorderer" "franz:identity"]

(defn primary-process-rule [args rule]
  (binding [kr.core.sparql/*force-prefixes*
            (add-magic-prefixes
              [["franzOption_memoryLimit" "franz:85g"]
               ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]]
              rule)
            kr.core.rdf/*use-inference* false]
    (binding [kr.core.rdf/*use-inference*
              (requested-rule-inference rule)]
      (prn (str "Processing rule: " (:name rule)))
      (let [source-connection (source-kb args)
            target-connection (rule-output-kb (:output-directory args) rule)]
        (try (time (run-forward-rule source-connection target-connection rule))
             true
             (finally (close target-connection)
                      (close source-connection)))))))

(defn process-forward-rules [args rules]
  (println "select limit (before bound): " kr.core.sparql/*select-limit* )
  (binding [kr.core.sparql/*select-limit* (:sparql-limit args)]
    (dorun ;; Switch to a parallel work queue?
     (map (fn [rule] (time (primary-process-rule args rule)))
          rules))))

(defn commandline-process-forward-rules [args]
  (logging-header args)
  (let [rules (read-rules args)]
    (logging-rules rules)
    (process-forward-rules args rules)))

(defn -main [& args]
  (println "global:")
  (pprint *command-line-args*)
  (println "local:")
  (pprint args)
  (commandline-process-forward-rules (command-line-args args))
  (System/exit 0))
