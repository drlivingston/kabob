(ns edu.ucdenver.ccp.kabob.build.kb-connection
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule

       edu.ucdenver.ccp.kabob.namespace       

       edu.ucdenver.ccp.kr.sesame.kb
       
       ;;edu.ucdenver.ccp.kr.examples.sesame.bigdata-local
       )

  (import ;;com.bigdata.rdf.sail.BigdataSail
          org.openrdf.repository.http.HTTPRepository))


;; EXAMPLE CALL:
;; (let [rootdir "/temp/kabob/kb/"]
;;   (builder-kb {:properties (str rootdir 
;;                                 "bigdata/RWStore.properties")
;;                :journal (str rootdir "bigdata/kaboblt.RW.jnl")
;;                :store-name "kaboblt"}))


;; (defn builder-kb [{prop :properties journal :journal name :store-name
;;                    :or {prop *bigdata-sail-property-file*
;;                         journal *bigdata-sail-journal-file*
;;                         name *bigdata-sail-journal-store-name*}}]
;;   (binding [*bigdata-sail-property-file* prop
;;             *bigdata-sail-journal-file* journal
;;             *bigdata-sail-journal-store-name* name]

;;     (let [conn (connection 
;;                 (kb com.bigdata.rdf.sail.BigdataSail))]
;;       (.setAutoCommit (:connection conn) false)
;;       (register-namespaces conn)
;;       conn)))



(defn server-kb [kb-name]
   (binding [*default-server* "http://localhost:8080/openrdf-sesame/"
            *repository-name* kb-name]
    ;; (alter-var-root #'*rcon* (fn [orig new-rcon] 
    ;;                            new-rcon)
    (let [conn (connection 
                (kb org.openrdf.repository.http.HTTPRepository))]
      (.setAutoCommit (:connection conn) false)
      (register-namespaces conn)
      conn)));)




;; (defn gene-ice []
;;   (query-template
;;    *kb*
;;    '?geneICE
;;    '((?rec rdf/type iaoeg/egEntrezGeneInfoFileDataDataRecord20110322) 
;;      (?fv  ro/part_of ?rec) 
;;      (?fv  ro/part_of iaoeg/EGrecordIDDataField1_20110322) 
;;      (?fv  iao/IAO_0000219 ?geneICE))))
