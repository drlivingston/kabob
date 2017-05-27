
(ns kabob.build.input-kb
  (:require [edu.ucdenver.ccp.kr.kb
             :refer [connection kb]]
            [edu.ucdenver.ccp.kr.rdf
             :refer [register-namespaces synch-ns-mappings]]
            [edu.ucdenver.ccp.kr.sesame.kb
             :refer [*default-server* *repository-name* *username* *password*]]
            [kabob.core.namespace
             :refer [*namespaces*]])
  (:import [org.openrdf.rio RDFFormat]
           [org.openrdf.query.resultio TupleQueryResultFormat]
           [org.openrdf.repository.http HTTPRepository]))

(defn initialize-kb [kb]
  (register-namespaces (synch-ns-mappings (connection kb)) *namespaces*))

(defn open-kb [args]
  (binding [*default-server* (:server-url args)
            *repository-name* (:repo-name args)
            *username* (:username args)
            *password* (:password args)]
    (initialize-kb (kb HTTPRepository))))

(def source-kb open-kb)
