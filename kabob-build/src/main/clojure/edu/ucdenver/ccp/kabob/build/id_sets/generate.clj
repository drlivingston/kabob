
(ns edu.ucdenver.ccp.kabob.build.id-sets.generate
  (:require [clojure.java.io :refer [make-parents]]
            [clojure.string :as str])
  (:import [java.io File])
  (use edu.ucdenver.ccp.kabob.build.id-sets.kabob-id-sets
       edu.ucdenver.ccp.kabob.build.id-sets.id-set-triples
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kabob.build.input-kb
       clojure.pprint
       clojure.stacktrace)
  (:gen-class))

;;;-------------------------------------------------------------------
;;; id types
;;;-------------------------------------------------------------------

(def id-types '(kiao/DrugIdentifier
                kiao/DNAIdentifier
                kiao/ProteinIdentifier
                kiao/DiseaseIdentifier
                kiao/RNAIdentifier
                kiao/mRNAIdentifier))

;;;-------------------------------------------------------------------
;;; extract ids from source kb, group in temp graph db, output to file
;;;-------------------------------------------------------------------

;(defn generate-id-set-internal [source-kb temp-db-dir output-dir id-type]
;  (let [output-kb (open-output-kb output-dir (name id-type))]
;    (try
;      (println "initializing id graph for: " id-type)
;      (let [uf ;;[id-map id-sets]
;            (time
;             (initialize-id-sets source-kb id-type))]
;        (println "generating set triples for: " id-type)
;        (time
;         (produce-id-set-triples uf output-kb id-type))
;        
;         ;; (write-id-set-triples id-sets
;         ;;                       source-kb
;         ;;                       output-kb
;        ;;                       id-type)))
;        (println "gc")
;        (System/gc)
;        (System/gc)
;
;        (time
;         (produce-single-set-triples source-kb output-kb id-type)))
;        
;        (catch Exception e (print-cause-trace e))
;        (finally (close output-kb)))))


;(defn generate-id-set [source-kb temp-db-base-dir output-base-dir id-type]
;  (let [temp-db-dir (str temp-db-base-dir id-type)]
;    (time
;     (generate-id-set-internal source-kb temp-db-dir output-base-dir id-type))))

;; (defn generate-all-id-sets [source-kb temp-db-base-dir output-base-dir]
;;   (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
;;             [;["franzOption_queryEngine" "franz:sparql-1.0"]
;;              ["franzOption_memoryLimit" "franz:85g"]
;;              ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
;;              ;["franzOption_chunkProcessingAllowed" "franz:yes"]
;;              ;["franzOption_chunkProcessingSize" "franz:500000"]
;;              ["franzOption_logQuery" "franz:yes"]
;;              ]]
;;     (dorun
;;      (map (partial generate-id-set source-kb temp-db-base-dir output-base-dir)
;;           id-types))))


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

;(defn generate-generic-id-set-type [source-kb output-dir id-type]
;  (let [output-kb (open-output-kb output-dir (str "generic-"
;                                                  (name id-type)))]
;    (try
;      (println "generating set triples for: " id-type)
;      (time
;       (produce-generic-single-set-triples source-kb output-kb id-type))
;      (catch Exception e (print-cause-trace e))
;      (finally (close output-kb)))))

        
(defn generate-all-id-sets [source-kb temp-db-base-dir output-base-dir]
  (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
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

    ;; (dorun
    ;;  (map (partial generate-generic-id-set-type source-kb output-base-dir)
    ;;       id-types))))

;;; --------------------------------------------------------
;;; --------------------------------------------------------

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

(defn logging-header [args]
  (prn "Clojure version: " *clojure-version*)
  (prn "Server URL: " (:server-url args))
  (prn "Repo name: " (:repo-name args))
  (prn "Output directory: " (:output-directory args))
  (prn "ID types: ")
  (pprint id-types))

;; (defn source-kb [args]
;;   ;; Init source KB connection
;;   (binding [*default-server* (:server-url args)
;;             *repository-name* (:repo-name args)
;;             *username* (:username args)
;;             *password* (:password args)]
;;     (binary-kb)))

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

