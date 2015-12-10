
(ns edu.ucdenver.ccp.kabob.build.input-kb
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sesame.kb
       edu.ucdenver.ccp.kabob.namespace)
  (import org.openrdf.rio.RDFFormat
          org.openrdf.query.resultio.TupleQueryResultFormat))

;;; --------------------------------------------------------
;;; kabob source kbs
;;; --------------------------------------------------------

(defn binary-kb []
  (let [kb (kb org.openrdf.repository.http.HTTPRepository)]
;    (.setPreferredRDFFormat (:server kb) RDFFormat/BINARY)
;    (.setPreferredTupleQueryResultFormat (:server kb)
;                                         TupleQueryResultFormat/BINARY)
    (register-namespaces (synch-ns-mappings (connection kb))
                         *namespaces*)))

;; ;;potential problem with AG4.13 not making namespaces queryable
;; (defn binary-kb []
;;   (let [kb (kb org.openrdf.repository.http.HTTPRepository)]
;;     (update-namespaces
;;      (initialize-ns-mappings (connection kb))
;;      *namespaces*)))


(defn open-kb [args]
  ;; Init source KB connection
  (binding [edu.ucdenver.ccp.kr.sesame.kb/*default-server* (:server-url args)
            edu.ucdenver.ccp.kr.sesame.kb/*repository-name* (:repo-name args)
            edu.ucdenver.ccp.kr.sesame.kb/*username* (:username args)
            edu.ucdenver.ccp.kr.sesame.kb/*password* (:password args)]
    (binary-kb)))

(def source-kb open-kb)

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
