
(ns kabob.build.id-sets.generate
  (:require [clojure.java.io :refer [make-parents]]
            [clojure.string :as str])
  (:import [java.io File])
  (use kabob.build.id-sets.id-set-triples
       kr.core.kb
       kabob.build.input-kb
       clojure.pprint
       clojure.stacktrace)
  (:gen-class))

(defn generate-combined-id-sets [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "combined")]
    (try
      (println "initializing id graph")
      (let [uf (time
                (combined-pairs-fn source-kb))]
        (println "generating set triples")
        (time
         (produce-generic-id-set-triples uf output-kb)))
      (println "gc")
      (System/gc)
      (System/gc)
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))

(defn generate-generic-id-set-singles [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "singles")]
    (try
      (println "generating single set triples")
      (time
       (produce-generic-singles-triples source-kb output-kb))
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))
        
(defn generate-all-id-sets [source-kb temp-db-base-dir output-base-dir]
  (binding [kr.core.sparql/*force-prefixes*
            [;["franzOption_queryEngine" "franz:sparql-1.0"]
             ["franzOption_memoryLimit" "franz:85g"]
             ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
             ;;["franzOption_chunkProcessingAllowed" "franz:yes"]
             ;;["franzOption_chunkProcessingSize" "franz:500000"]
             ["franzOption_logQuery" "franz:yes"]
             ]]
    (println "producing singles")
    (generate-generic-id-set-singles source-kb output-base-dir)
    (println "producing sets")
    (generate-combined-id-sets       source-kb output-base-dir)
    ))

;;; --------------------------------------------------------
;;; command line arguments and helpers
;;; --------------------------------------------------------

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
   :graph-db-dir (nth original-args 5)})


;;;-------------------------------------------------------------------
;;; process
;;;-------------------------------------------------------------------

(defn -main [& args]
  (pprint args)
  (let [{out-dir :output-directory gdb-dir :graph-db-dir :as args}
        (command-line-args args)]
    (make-parents (str/join File/separator [out-dir "file"]))
    (generate-all-id-sets (source-kb args) gdb-dir out-dir))
  (System/exit 0))

;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------

