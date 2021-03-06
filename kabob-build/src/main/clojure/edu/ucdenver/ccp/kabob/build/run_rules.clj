;; This script will execute rules on the specified KB. 
;; Generated triples are output to files in the specified output directory.
;; This script is designed to be run
;;    by the clojure:run goal of the maven clojure plugin.

;; Extract rules from files on the classpath
;; The rule-set-names variable holds the names of rule files available 
;; in src/main/resources. Below we map over each rule set name, openning an 
;; InputStreamReader to read the rule set file, and pass the open reader to 
;; edu.ucdenver.ccp.kr.rule/load-rules-from-file


(ns edu.ucdenver.ccp.kabob.build.run-rules
  (:gen-class)
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       ;edu.ucdenver.ccp.kr.sesame.kb
       ;edu.ucdenver.ccp.kabob.namespace
       
       edu.ucdenver.ccp.kabob.rule

       ;;edu.ucdenver.ccp.kr.forward-rule
       [edu.ucdenver.ccp.kr.forward-rule :exclude (run-forward-rule)]
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.reify
       edu.ucdenver.ccp.kr.unify

       edu.ucdenver.ccp.kabob.staged-rule

       edu.ucdenver.ccp.kabob.parallel-utils

       ;; edu.ucdenver.ccp.kr.variable
       ;; edu.ucdenver.ccp.kr.unify
       ;; edu.ucdenver.ccp.kr.rule
       ;; edu.ucdenver.ccp.kr.sparql
       ;clojure.set
       clojure.pprint
       ;[clojure.java.io :only (output-stream)];reader writer)]
       edu.ucdenver.ccp.kabob.build.input-kb
       edu.ucdenver.ccp.kabob.build.output-kb)
  (require ;edu.ucdenver.ccp.kr.sesame.writer-kb
           ;edu.ucdenver.ccp.kabob.build.kb-connection
           [clojure.set :as set]
           kabob)
  ;; (import org.openrdf.rio.RDFFormat
  ;;         org.openrdf.query.resultio.TupleQueryResultFormat
  ;;         (java.io InputStreamReader)
          ;;(edu.ucdenver.ccp.common.file CharacterEncoding)
          ;java.util.zip.GZIPOutputStream
                                        ;)
  )

;;; --------------------------------------------------------
;;; globals
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; arguments and helpers
;;; --------------------------------------------------------

;; NOTE why didn't I just use destructuring here?


(defn command-line-args [original-args]
  {;; The URL to the Sesame server to query
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
   
   ;; Names of the rule sets to use (must point to files available on the 
   ;; classpath; NOTE that it should not start with a forward slash)
   ;;e.g. edu/ucdenver/ccp/kabob/build/id_typing/entrez_gene_id_typing_rules.clj
   :rule-set-names (drop 5 original-args)})

;; the version below allows an argument after the server password
;;   for a forced limit
;;
;; (defn command-line-args [original-args]
;;   {;; The URL to the Sesame server to query
;;    :server-url (nth original-args 0)
;;    ;; The name of the repository to connect to
;;    :repo-name (nth original-args 1)
;;    ;; The username to use when connecting
;;    :username (nth original-args 2)
;;    ;; The password to use when connecting
;;    :password (nth original-args 3) 

;;    :sparql-limit (read-string (nth original-args 4))


;;    ;; The output directory where generated triple files will be placed. 
;;    ;; IMPORTANT: The output directory path must end with a forward slash.
;;    :output-directory (nth original-args 5)
   
;;    ;; Names of the rule sets to use (must point to files available on the 
;;    ;; classpath; NOTE that it should not start with a forward slash)
;; ;;e.g. edu/ucdenver/ccp/kabob/build/id_typing/entrez_gene_id_typing_rules.clj
;;    :rule-set-names (drop 6 original-args)})

(defn logging-header [args]
  (prn "Serializing rule output for: " (:rule-set-names args))
  (prn (str "Clojure version: " *clojure-version*))
  (prn (str "Server URL: " (:server-url args)))
  (prn (str "Repo name: " (:repo-name args)))
  (prn (str "Passed in select limit: " (:sparql-limit args)))
  (prn (str "Output directory: " (:output-directory args))))

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

;; (defn binary-kb []
;;   (let [kb (kb org.openrdf.repository.http.HTTPRepository)]
;;     (.setPreferredRDFFormat (:server kb) RDFFormat/BINARY)
;;     (.setPreferredTupleQueryResultFormat (:server kb)
;;                                          TupleQueryResultFormat/BINARY)
;;     (register-namespaces (synch-ns-mappings (connection kb))
;;                          *namespaces*)))

;;these are sesame only options - get a better pass-through function
;;  that avoids the dependency
;; (defn source-kb [args]
;;   ;; Init source KB connection
;;   (binding [*default-server* (:server-url args)
;;             *repository-name* (:repo-name args)
;;             *username* (:username args)
;;             *password* (:password args)]
;;     (binary-kb)))

;;TODO change this to default to gzip files
(defn rule-output-kb [base-dir rule]
  (let [output-file (str base-dir (:name rule) ".nt.gz")]
    (zipped-output-kb output-file)))


;;; --------------------------------------------------------
;;; rules
;;; --------------------------------------------------------

(defn read-rules [args]
  (mapcat edu.ucdenver.ccp.kabob.rule/kabob-load-rules-from-classpath
          (:rule-set-names args)))

;;; --------------------------------------------------------
;;; modified rule running
;;; --------------------------------------------------------

(defn query-variables [{head :head
                        body :body
                        reify :reify
                        :as rule}]
  (let [needed-variables (set (concat (variables head)
                                      (variables reify)))
        body-variables (set (variables body))]
    (set/intersection needed-variables body-variables)))


(defn run-forward-rule [source-kb target-kb rule]
  (let [{head :head
         body :body
         reify :reify
         :as rule}    (add-reify-fns rule)
         visit-counter (atom 0)
         t (atom (.getTime (java.util.Date.)))
         ;;write-agent (agent target-kb)
         query-vars (query-variables rule)
         ]
    (println "rule query:")
    (binding [*kb* source-kb]
      (println (sparql-select-query body
                                    {:select-vars query-vars
                                     ;; (concat (variables head)
                                     ;;         (variables reify))
                                     })))
    (println "running:")
    (query-visit source-kb
                 (fn [bindings]
                   ;;(pprint bindings)
                   (swap! visit-counter inc)
                   ;;(println @visit-counter)
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
                                                          bindings))))))
                 body ;need to add reify find clauses on optionally
                 {:select-vars query-vars
                  ;; (concat (variables head)
                  ;;         (variables reify))
                  })
    (println "final count: " @visit-counter)
    ;;(await write-agent)
    ))


;; print only version

;; (defn run-forward-rule [source-kb target-kb rule]
;;   (let [{head :head
;;          body :body
;;          reify :reify
;;          :as rule}    (add-reify-fns rule)
;;          visit-counter (atom 0)
;;          t (atom (.getTime (java.util.Date.)))
;;          ;;write-agent (agent target-kb)
;;          ]
;;     (println "rule query:")
;;     (binding [*kb* source-kb]
;;       (println (sparql-select-query body
;;                                     {:select-vars (concat (variables head)
;;                                                           (variables reify))})))
;;     (println "running:")
;;     (query-visit source-kb
;;                  (fn [bindings]
;;                    (pprint bindings)
;;                    (swap! visit-counter inc)
;;                    (println @visit-counter)
;;                    (when (= 0 (mod @visit-counter 10000))
;;                      (System/gc))
;;                    (when (= 0 (mod @visit-counter 250000))
;;                      (let [new-t (.getTime (java.util.Date.))]
;;                        (println @visit-counter " in " (- new-t @t) "ms")
;;                        (reset! t new-t)))
;;                    ;; (blocking-write-triples write-agent
;;                    ;; ;; (dorun
;;                    ;; ;;  (map (partial add! target-kb)
;;                    ;;       (doall
;;                    ;;        (subst-bindings head
;;                    ;;                        bindings
;;                    ;;                        (reify-bindings reify ;;-with-fns
;;                    ;;                                        bindings))))
;;                    ););)
;;                  body ;need to add reify find clauses on optionally
;;                  {:select-vars (concat (variables head)
;;                                        (variables reify))})
;;     ;;(await write-agent)
;;     ))


;; TODO factor out constants below so they can be shared across binding forms

(defn failover-process-rule [args rule]
  (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
            [;["franzOption_queryEngine" "franz:sparql-1.0"]
             ["franzOption_memoryLimit" "franz:85g"]
             ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
             ["franzOption_chunkProcessingAllowed" "franz:yes"]
             ["franzOption_chunkProcessingSize" "franz:500000"]
             ;; the parts logging will be too much, turn it off
             ;["franzOption_logQuery" "franz:yes"]
             ]]
    (prn (str "Failover Processing rule: " (:name rule)))
    (let [source-connection (source-kb args)
          target-connection (rule-output-kb (:output-directory args)
                                            rule)]
      (try (time
            (kabob-run-forward-ice-rule-parts source-connection
                                              target-connection
                                              rule))
           true
           (catch Exception e
             (println "query failed.")
             (prn e)
             (.printStackTrace e)
             nil)
           (finally (close target-connection)
                    (close source-connection))))))

;;this needs to check for duplicates and prefer one, but which
;; this should probably take multiple lists
;; then defaults and hard-codes can also be given
;; in addition to what's in the rule
(defn add-magic-prefixes [current-prefixes rule]
  (let [new-prefixes (get (get rule :options {})
                          :magic-prefixes
                          [])]
    ;;concat returns lazy list, into [] will make this a vector again
    (into [] (concat current-prefixes new-prefixes))))

;;add a flag to options to control inference
(defn requested-rule-inference [rule]
  (let [requested-inference (get (get rule :options {})
                                 :inference
                                 edu.ucdenver.ccp.kr.rdf/*use-inference*)]
    ;;this probably needs to be a more interesting cond, but for now this works
    (if requested-inference
      true
      false)))

;; (println "select limit (outside query): "
;;          edu.ucdenver.ccp.kr.sparql/*select-limit* )

;;OTHER MAGIC OPTIONS
;;["franzOption_queryEngine" "franz:sparql-1.0"]
;;["franzOption_chunkProcessingAllowed" "franz:no"]
;;["franzOption_chunkProcessingSize" "franz:500000"]
;;["franzOption_chunkProcessingSize" "franz:50000"]
             
;; ["franzOption_chunkProcessingAllowed" "franz:yes"]
;; ["franzOption_chunkProcessingSize" "franz:100000000"]

;;["franzOption_usePropertyPathCache" "franz:no"]
;;["franzOption_clauseReorderer" "franz:identity"]

;;["franzOption_reorderDuringExecution" "franz:yes"]
;;["franzOption_warmupStringTable" "franz:yes"]

 
(defn primary-process-rule [args rule]
  (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
            (add-magic-prefixes
             [
              ["franzOption_memoryLimit" "franz:85g"]
              ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
              ;; ["franzOption_cacheStringsCutoff" "franz:1000"]
              ;;["franzOption_logQuery" "franz:yes"]
              ;;["franzOption_clauseReorderer" "franz:identity"]
              ]
             rule)
            ;;default inference
            edu.ucdenver.ccp.kr.rdf/*use-inference* false
            ;;edu.ucdenver.ccp.kr.rdf/*use-inference* true
            ;edu.ucdenver.ccp.kr.sparql/*select-limit* 1000
            ]
    (binding [edu.ucdenver.ccp.kr.rdf/*use-inference*
              (requested-rule-inference rule)]
      (prn (str "Processing rule: " (:name rule)))
      (let [source-connection (source-kb args)
            target-connection (rule-output-kb (:output-directory args)
                                              rule)]
        (try (time
              (run-forward-rule source-connection
                                target-connection
                                rule))
             true
             (catch Exception e
               (println "query failed.")
               (prn e)
               (.printStackTrace e)
               nil)
             (finally (close target-connection)
                      (close source-connection)))))))


(defn warmup-kb [args]
  (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
            (add-magic-prefixes
             [;["franzOption_memoryLimit" "franz:85g"]
              ;["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
              ["franzOption_warmupStringTable" "franz:yes"]
              ["franzOption_logQuery" "franz:yes"]]
             {})
            edu.ucdenver.ccp.kr.rdf/*use-inference* false
            edu.ucdenver.ccp.kr.sparql/*select-limit* 1
            ]
    (let [query '((?/s ?/p ?/o))]
      (prn (str "warming up kb: "))
      (let [source-connection (source-kb args)]
        (binding [*kb* source-connection]
          (println "query:")
          (prn query)
          (println (sparql-select-query query))
          (try (println "running:")
               (time
                (query-visit source-connection
                             (fn [bindings]
                               (pprint bindings))
                             query))
               true
               (catch Exception e
                 (println "query failed.")
                 (prn e)
                 nil)
               (finally (close source-connection))))))))

(defn try-process [args rule]
  (try (primary-process-rule args rule)
       true
       (catch Exception e
         nil)))

(defn try-process-failover [args rule]
  (try (primary-process-rule args rule)
       (catch Exception e
         (failover-process-rule args rule))))

(defn process-forward-rules [args rules]
  ;;(warmup-kb args)
  (println "select limit (before bound): " edu.ucdenver.ccp.kr.sparql/*select-limit* )
  (binding [edu.ucdenver.ccp.kr.sparql/*select-limit* (:sparql-limit args)]
    (dorun ;switch to a parallel work queue?
     (map (fn [rule]
            ;;(println "select limit: " edu.ucdenver.ccp.kr.sparql/*select-limit* )
            (time (primary-process-rule args rule)))
            ;; (dorun
            ;;  (for [i (range 3)]
            ;;    (do
            ;;      (println (str "iteration: " i))
            ;;      (time (primary-process-rule args rule))))))
          ;; (or (time (primary-process-rule args rule))
          ;;     (time (failover-process-rule args rule))))
          rules
          ;; (let [[r1 r2 & rest] rules]
          ;;   (cons r2
          ;;         (cons r1 rules)))
          ))))

          ;;rules))))

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

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
