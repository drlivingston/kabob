(ns kabob.core.kb
  (use kr.core.kb
       kr.core.rdf
       kr.core.rdf4j.kb
       kabob.core.namespace)
  (import org.eclipse.rdf4j.rio.RDFFormat
          org.eclipse.rdf4j.query.resultio.TupleQueryResultFormat))

;;; --------------------------------------------------------
;;; kabob source kbs
;;; --------------------------------------------------------

(defn binary-kb []
  (let [kb (kb org.eclipse.rdf4j.repository.http.HTTPRepository)]
    (.setPreferredRDFFormat (:server kb) RDFFormat/BINARY)
    (.setPreferredTupleQueryResultFormat (:server kb)
                                         TupleQueryResultFormat/BINARY)
    (register-namespaces (synch-ns-mappings (connection kb))
                         *namespaces*)))

(defn open-kb [args]
  ;; Init source KB connection
  (binding [*default-server* (:server-url args)
            *repository-name* (:repo-name args)
            *username* (:username args)
            *password* (:password args)]
    (binary-kb)))


