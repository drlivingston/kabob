
(ns kabob.build.input-kb
  (:require [kr.core.kb
             :refer [connection kb]]
            [kr.core.rdf
             :refer [register-namespaces synch-ns-mappings]]
            [kr.sesame.kb
             :refer [*default-server* *repository-name* *username* *password*]]
            [kabob.core.namespace
             :refer [*namespaces*]])
  (:import [org.openrdf.rio RDFFormat]
           [org.openrdf.query.resultio TupleQueryResultFormat]
           [org.openrdf.repository.http HTTPRepository]
           [virtuoso.sesame2.driver VirtuosoRepository]))

(defn initialize-kb [kb]
  (register-namespaces (synch-ns-mappings (connection kb)) *namespaces*))

(defn virtuoso-kb [args]
  ;;Init source KB connection
  (println "forcing a virtuoso connection")
  (let [kb (kb (VirtuosoRepository. "jdbc:virtuoso://localhost:1111","dba","dba"))]
    (initialize-kb kb)))

(defn open-kb [args]
  (binding [*default-server* (:server-url args)
            *repository-name* (:repo-name args)
            *username* (:username args)
            *password* (:password args)]
    (if (= "true" (:is-virtuoso args))
      (virtuoso-kb args)
      (initialize-kb (kb HTTPRepository)))))

(def source-kb open-kb)

