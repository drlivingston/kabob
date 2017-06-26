
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
           [com.complexible.stardog.sesame StardogRepository]))

(defn initialize-kb [kb]
  (register-namespaces (synch-ns-mappings (connection kb)) *namespaces*))


(defn stardog-kb [args]
    ;;Init source KB connection
    (println "forcing a stardog connection")

    (let [server-url (:server-url args)
          repository-name (:repo-name args)
          user (:username args)
          password (:password args)
          kb (kb (StardogRepository. (.credentials user password (.server server-url (ConnectionConfiguration/to repository-name)))))]
      ;kb (kb (VirtuosoRepository. "jdbc:virtuoso://virtuoso-prod:1111","dba","dba"))]
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
    (if (= "true" (:is-virtuoso args))
      (stardog-kb args)
      ;(virtuoso-kb args)
      (initialize-kb (kb HTTPRepository)))))

(def source-kb open-kb)

