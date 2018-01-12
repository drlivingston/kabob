
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
          ; [virtuoso.rdf4j.driver VirtuosoRepository]
           [com.complexible.stardog.api ConnectionConfiguration]
           [com.complexible.stardog.sesame StardogRepository]
           [com.bigdata.rdf.sail.webapp.client RemoteRepositoryManager]))

(defn initialize-kb [kb]
  (register-namespaces (synch-ns-mappings (connection kb)) *namespaces*))


(defn stardog-kb [args]
    (println "opening a stardog connection")

    (let [server-url (:server-url args)
          repository-name (:repo-name args)
          user (:username args)
          password (:password args)
          kb (kb (StardogRepository. (.credentials (.server (ConnectionConfiguration/to repository-name) server-url) user password)))]
      (initialize-kb kb)))


(defn blazegraph-kb [args]
  (println "opening a blazegraph connection")

  (let [server-url (:server-url args)
        repository-name (:repo-name args)
        user (:username args)
        password (:password args)
        kb (kb (.getBigdataSailRemoteRepository (.getRepositoryForNamespace (RemoteRepositoryManager. server-url) repository-name)))]
    (initialize-kb kb)))

;(defn virtuoso-kb [args]
;  ;;Init source KB connection
;  (println "forcing a virtuoso connection")
;
;  (let [server-url (:server-url args)
;        server-url-nohttp (if (.startsWith server-url "http://")
;                            (.substring server-url 7)
;                            server-url)
;        virtuoso-connection-str (str "jdbc:virtuoso://" server-url-nohttp)
;        user (:username args)
;        password (:password args)
;        kb (kb (VirtuosoRepository. virtuoso-connection-str, user, password))]
;    ;kb (kb (VirtuosoRepository. "jdbc:virtuoso://virtuoso-prod:1111","dba","dba"))]
;    (initialize-kb kb)))

(defn open-kb [args]
  (binding [*default-server* (:server-url args)
            *repository-name* (:repo-name args)
            *username* (:username args)
            *password* (:password args)]

    (case (:server-impl args)
      "stardog" (stardog-kb args)
      "blazegraph" (blazegraph-kb args)
      ;"virtuoso" (virtuoso-kb args)
      ;; default is to open a connection to an HTTPRepository
      (initialize-kb (kb HTTPRepository)))))

(def source-kb open-kb)

