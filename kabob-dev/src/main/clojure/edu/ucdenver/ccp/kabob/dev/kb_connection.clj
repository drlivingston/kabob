(ns edu.ucdenver.ccp.kabob.dev.kb-connection
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kabob.namespace
       edu.ucdenver.ccp.kr.sesame.kb)
  (import org.openrdf.rio.RDFFormat
          org.openrdf.query.resultio.TupleQueryResultFormat))

;;; --------------------------------------------------------
;;; kabob connection functions
;;; --------------------------------------------------------

;; PREFER THE BINARY CONNECTION BELOW
(defn kabob-kb [kb-name]
  (binding [*username* "xx_user_xx"
            *password* "xx_pass_xx"
            *default-server* "http://localhost:10035/"
            *repository-name* kb-name]
    (let [kb (connection
              (kb org.openrdf.repository.http.HTTPRepository))]
      (register-namespaces (synch-ns-mappings (connection kb))
                           *namespaces*))))

(defn binary-kb []
  (let [kb (kb org.openrdf.repository.http.HTTPRepository)]
    (.setPreferredRDFFormat (:server kb) RDFFormat/BINARY)
    (.setPreferredTupleQueryResultFormat (:server kb)
                                         TupleQueryResultFormat/BINARY)
    (register-namespaces (synch-ns-mappings (connection kb))
                         *namespaces*)))

(defn kabob-binary-kb [kb-name]
  (binding [*username* "xx_user_xx"
            *password* "xx_pass_xx"
            *default-server* "http://localhost:10035/"
            *repository-name* kb-name]
    (binary-kb)))

;;; --------------------------------------------------------
;;; kabob kbs
;;; --------------------------------------------------------

(defn old-test-kb []
  (kabob-binary-kb "2011"))

(defn new-test-kb []
  (kabob-binary-kb "2012"))


(defn new-ice-kb []
  ;;(kabob-kb "ice20121201"))
  (kabob-binary-kb "ice20121201"))


;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------

