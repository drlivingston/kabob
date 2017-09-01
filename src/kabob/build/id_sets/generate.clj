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

(defn generate-combined-concept-id-sets [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "combined-concepts")]
    (try
      (println "initializing id graph")
      (let [uf (time
                 (combined-pairs-concepts-fn source-kb))]
        (println "generating set triples")
        (time
          (produce-generic-id-set-triples uf output-kb)))
      (println "gc")
      (System/gc)
      (System/gc)
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))

(defn generate-combined-property-id-sets [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "combined-properties")]
    (try
      (println "initializing id graph")
      (let [uf (time
                 (combined-pairs-property-fn source-kb))]
        (println "generating set triples")
        (time
          (produce-generic-id-set-triples uf output-kb)))
      (println "gc")
      (System/gc)
      (System/gc)
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))

;;; orphan id sets are generated based on the identifier types linked via skos:exactMatch.
;;; In order to simplify queries (and avoid the union operator) the orphan id set generation is
;;; split into three queries. The first two look for identifier types used in skos:exactMatch links
;;; and generate orphan id sets based on those types. Note, there is potential for overlap in the output
;;; between orphans-1 and orphans-2. The third rule generates orphan sets for every ontology identifier that is not
;;; in a skos:exactMatch triple.
(defn generate-orphan-id-sets [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "orphans")]
    (try
      (println "generating orphan set triples")
      (time
        (let [types (query-for-id-types-in-skos-exact-match-relations source-kb)]
          (produce-orphan-triples source-kb output-kb types)))
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))

;(defn generate-orphan-id-sets-2 [source-kb output-dir]
;  (let [output-kb (open-output-kb output-dir "orphans-2")]
;    (try
;      (println "generating orphan set triples -- 2")
;      (time
;        (produce-orphan-triples-2 source-kb output-kb))
;      (catch Exception e (print-cause-trace e))
;      (finally (close output-kb)))))

(defn generate-orphan-id-sets-obo [source-kb output-dir]
  (let [output-kb (open-output-kb output-dir "orphans-obo")]
    (try
      (println "generating single set triples -- ontology concepts")
      (time
        (produce-orphan-triples-obo source-kb output-kb))
      (catch Exception e (print-cause-trace e))
      (finally (close output-kb)))))

(defn generate-all-id-sets [source-kb output-base-dir]
  (binding [kr.core.sparql/*force-prefixes*
            [;["franzOption_queryEngine" "franz:sparql-1.0"]
             ["franzOption_memoryLimit" "franz:85g"]
             ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
             ["franzOption_chunkProcessingAllowed" "franz:yes"]
             ;;["franzOption_chunkProcessingSize" "franz:500000"]
             ["franzOption_logQuery" "franz:yes"]
             ]]
    (println "producing orphan sets")
    (generate-orphan-id-sets source-kb output-base-dir)
    (println "producing orphan sets - obo")
    (generate-orphan-id-sets-obo source-kb output-base-dir)
    (println "producing sets of merged concept identifiers")
    (generate-combined-concept-id-sets source-kb output-base-dir)
    (println "producing sets of merged object property identifiers")
    (generate-combined-property-id-sets source-kb output-base-dir)
    ))

;;; --------------------------------------------------------
;;; command line arguments and helpers
;;; --------------------------------------------------------

(defn command-line-args [original-args]
  {;; The URL to the Rdf4j server to query
   :server-url       (nth original-args 0)
   ;; The name of the repository to connect to
   :repo-name        (nth original-args 1)
   ;; The username to use when connecting
   :username         (nth original-args 2)
   ;; The password to use when connecting
   :password         (nth original-args 3)

   ;; The output directory where generated triple files will be placed. 
   ;; IMPORTANT: The output directory path must end with a forward slash.
   :output-directory (nth original-args 4)

   ;; Names of the rule sets to use (must point to files available on the 
   ;; classpath; NOTE that it should not start with a forward slash)
   ;;e.g. edu/ucdenver/ccp/kabob/build/id_typing/entrez_gene_id_typing_rules.clj
   :graph-db-dir     (nth original-args 5)

   :is-virtuoso      (nth original-args 6)
   })


;;;-------------------------------------------------------------------
;;; process
;;;-------------------------------------------------------------------

(defn -main [& args]
  (pprint args)
  (let [{out-dir :output-directory :as args}
        (command-line-args args)]
    (make-parents (str/join File/separator [out-dir "file"]))
    (generate-all-id-sets (source-kb args) out-dir))
  (System/exit 0))

;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------

