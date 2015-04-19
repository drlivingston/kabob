(ns edu.ucdenver.ccp.kabob.build.build
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule
       
       ;edu.ucdenver.ccp.kabob.build.kb-connection
       edu.ucdenver.ccp.kabob.build.de-ice
       edu.ucdenver.ccp.kabob.build.post-process
       )
  (require edu.ucdenver.ccp.kabob.build.kb-connection)

  ;;what of the following is actaully needed?
  (import (java.io FileOutputStream)
                   ;BufferedWriter
                   ;OutputStreamWriter)
          java.nio.charset.Charset
          (org.openrdf.rio Rio
                           RDFFormat
                           RDFWriter
                           RDFWriterFactory)
          org.openrdf.rio.ntriples.NTriplesWriterFactory)
)


(def ^:dynamic *output-dir* (str "/temp/"))

;; EXAMPLE CALL to get KB connection:
;; (let [rootdir "/temp/kabob/kb/"]
;;   (edu.ucdenver.ccp.kabob.build.kb-connection/builder-kb
;;    {:properties (str rootdir "bigdata/RWStore.properties")
;;     :journal (str rootdir "bigdata/kaboblt.RW.jnl")
;;     :store-name "kaboblt"}))


(defn writer-wrapper [writer statement-processing-fn]
  ;;creates a new function that does what the old fn does and writes it
  (fn [input]
    ;; the let is just here to return the statements so
    ;;   the side effects won't hurt things expecting return values
    (let [stmts (statement-processing-fn input)]
      (doall
       (map (fn [stmt]
              (try 
               ;;KML 2011-06-076 this let block now dead?
               ;;(let [s (statement stmt)])
               (.handleStatement writer (statement stmt))
               (catch java.lang.IllegalArgumentException iae
                 (prn (str (.getMessage iae) " FOR STATEMENT: " stmt)))))
            stmts))
      stmts)))
   


(defn new-writer [file-name]
  (let [writer (Rio/createWriter RDFFormat/NTRIPLES 
                                 (FileOutputStream. file-name))]
    (.startRDF writer) ;side effect function doesn't return itself
    writer))

;;this was in the java what is all this??
;;  doesn't it just need a file output stream?
;; original java writer code:
;; new BufferedWriter(
;;    new OutputStreamWriter(getOutputStream(rdfFile), 
;;                          Charset.forName(rdfFile.getCharacterEncoding())
;;                                           .newEncoder()));     


(defn serialize-rule-output [kb ice-pp-rules]
  (binding [*kb* kb]
    (dorun
     ;;changing this map to a pmap will run the rules in parallel
     (map (fn [rule]
            (let [writer (new-writer (str *output-dir* (:name rule) ".nt"))]
              (try
                (apply-post-processing-rule 
                  kb
                  ;;change the rule being applied to serialize statements too
                  (assoc rule 
                         :post-process 
                         (writer-wrapper writer (:post-process rule))))
                (:finally (.endRDF writer)))))
            ice-pp-rules))))


;; prints the input set of triples to a file
;;   using the specified filename prefix (suffix is .nt)
(defn serialize-triples [triples filename-prefix]
  (let [writer (new-writer (str *output-dir* filename-prefix ".nt"))]
    (try
      (doall
       (map (fn [stmt]
              (try
               ;;again dead let block
               ;;(let [s (statement stmt)])
                (.handleStatement writer (statement stmt))
                (catch java.lang.IllegalArgumentException iae 
                  (prn (str (.getMessage iae) " FOR STATEMENT: " stmt)))))
            triples))
      (:finally (.endRDF writer)))))



;; Defines a rule that will output all triples from a KB
(defn output-all-triples-rule [output-file-name]
  {:name output-file-name
   :query `((?s ?p ?o))
   :post-process 
   (fn [bindings]
  ;;   (prn "outputting all triples to file")
     (let [sub (get bindings '?s) 
           pred (get bindings '?p)
           obj (get bindings '?o)] 
       (and sub pred obj  
           ;; (prn `(~sub ~pred ~obj))
            `((~sub ~pred ~obj))))) })

;; Serializes all triples in the input KB to a file
;;  named with the specified output-file-prefix
(defn serialize-all-triples [kb output-file-prefix]
  (let [output-rule (list
                      (output-all-triples-rule output-file-prefix))]
    (serialize-rule-output kb output-rule)))
    
   

