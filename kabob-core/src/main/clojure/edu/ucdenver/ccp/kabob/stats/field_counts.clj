(ns edu.ucdenver.ccp.kabob.stats.field-counts
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kabob.parallel-utils
       edu.ucdenver.ccp.kabob.namespace)
  (require [clojure.java.io :as io]
           [cheshire.core :refer :all]))

(defmacro with-big-query [& body]
  `(binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
             [;["franzOption_queryEngine" "franz:sparql-1.0"]
              ["franzOption_memoryLimit" "franz:85g"]
              ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
              ["franzOption_chunkProcessingAllowed" "franz:yes"]
              ["franzOption_chunkProcessingSize" "franz:500000"]
              ["franzOption_logQuery" "franz:yes"]
              ]]
     ~@body))


(defn all-fields [kb]
  (with-big-query
    (binding [*select-type* select-distinct]
      (query-template kb '?/field
                      '((?/field rdf/type kiao/Field))))))


(defn field-stat-type [val]
  (if (symbol? val)
    (let [nspace (namespace val)]
      (if (= nspace "kiao")
        (str "kiao/" (first (clojure.string/split (name val) #"/" 2)))
        nspace))
    (type val)))


(defn visit-count-ns [kb field]
  (let [counts (atom {})]
    (binding [*use-inference*                 false
              *work-queue-single-threaded*    true
              *parallel-query-expected-count* false]
      (with-big-query
        (parallel-query-visit kb `((_/fv kiao/hasTemplate ~field)
                                   (_/fv obo/IAO_0000219 ?/id))
          (fn [bindings]
            (let [id (get bindings '?/id nil)
                  key (field-stat-type id)]
              (swap! counts
                     (fn [old-counts]
                       (assoc old-counts
                         key (inc (get old-counts key 0))))))))))
        @counts))

(defn write-field-counts [out kb field]
  (let [count-data (visit-count-ns kb field)]
    (binding [*out* out]
      (prn [field  count-data])
      (flush))))

(defn write-all-field-counts [out-dir kb fields]
  (let [out-path (str out-dir "field-counts.clj")]
    (with-open [out (io/writer out-path)]
      (dorun
       (map (fn [field]
              (println "field: " field)
              (time 
               (write-field-counts out kb field)))
            fields)))))
        

;; (defn open-json [out]
;;   (binding [*out* out]
;;     (println "{")))

;; (defn close-json [out]
;;   (binding [*out* out]
;;     (println "}")))

(defn d3-children [usage-map]
  (map (fn [[type count]]
         {:name (str type ": " count)})
       usage-map))
  
(defn write-as-json [out-dir data]
  (let [out-path (str out-dir "field-counts.json")
        ns-map (reduce conj
                       {}
                       (map vec *namespaces*))]
    (binding [*ns-map-to-long* ns-map]
      (with-open [out (io/writer out-path)]
        (generate-stream (reduce conj
                                 {}
                                 (map (fn [[name usage-map]]
                                        [(sym-to-long-name name)
                                         (d3-children usage-map)])
                                      data))
                         out)))))


;; (defn all-fields [kb]
;;   (with-big-query
;;     (binding [*select-type* select-distinct]
;;       (query-template kb '?/template
;;                       '((_/fv kiao/hasTemplate ?/template))))))


(defn count-field-ns-sparql-query [field]
  (str "PREFIX franzOption_memoryLimit: <franz:85g> "
       "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> "
       "PREFIX franzOption_logQuery: <franz:yes> "
       "PREFIX franzOption_chunkProcessingAllowed: <franz:yes> "
       ;;"PREFIX franzOption_chunkProcessingSize: <franz:1000000> "
       "PREFIX franzOption_chunkProcessingSize: <franz:500000> "
       "SELECT ?namespace (count(?namespace) as ?count) WHERE { "
       "?fv kiao:hasTemplate " (namespace field) ":" (name field) " . "
       "?fv obo:IAO_0000219 ?id . "
       "BIND (REPLACE (SUBSTR(str(?id), 31), \"[^/]*$\", \"\") AS ?namespace) "
       "} GROUP BY ?namespace"))


(defn count-denotes-sparql-query []
  (str "PREFIX franzOption_memoryLimit: <franz:85g> "
       "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> "
       "PREFIX franzOption_logQuery: <franz:yes> "
       "PREFIX franzOption_chunkProcessingAllowed: <franz:yes> "
       ;;"PREFIX franzOption_chunkProcessingSize: <franz:1000000> "
       "PREFIX franzOption_chunkProcessingSize: <franz:500000> "
       "SELECT (count(?id) as ?count) WHERE { "
       ;;"?fv kiao:hasTemplate " (namespace field) ":" (name field) " . "
       "?fv obo:IAO_0000219 ?id . "
       "} "))

(defn count-both-sparql-query [field]
  (str "PREFIX franzOption_memoryLimit: <franz:85g> "
       "PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95> "
       "PREFIX franzOption_logQuery: <franz:yes> "
       "PREFIX franzOption_chunkProcessingAllowed: <franz:yes> "
       ;;"PREFIX franzOption_chunkProcessingSize: <franz:1000000> "
       "PREFIX franzOption_chunkProcessingSize: <franz:500000> "
       "SELECT (count(?id) as ?count) WHERE { "
       "?fv kiao:hasTemplate " (namespace field) ":" (name field) " . "
       "?fv obo:IAO_0000219 ?id . "
       "} "))

(defn count-parts [kb field]
  (binding [*use-inference* false]
    (with-big-query
      (println "field: ")
      (println
       (time
        (query-count kb `((?/fv kiao/hasTemplate ~field)))))
      (println "denotes: ")
      (println
       (time
        (query-sparql kb (count-denotes-sparql-query))))
      ;;(query-count kb `((_/fv obo/IAO_0000219 ?/id)))))
      (println "combined: ")
      (println
       (time
        (query-sparql kb (count-both-sparql-query field)))))))
        ;; (query-count kb `((_/fv kiao/hasTemplate ~field)
        ;;                   (_/fv obo/IAO_0000219 ?/id))))))))
      

(defn count-field-namespaces [kb field]
  (with-big-query
    (sparql-query-template kb '(?/namespace ?/count)
                           (count-field-ns-sparql-query field))))



