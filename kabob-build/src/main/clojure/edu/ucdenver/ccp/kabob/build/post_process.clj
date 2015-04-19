(ns edu.ucdenver.ccp.kabob.build.post-process
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))


;; I can't for the life of me figure out why I can't get this to work as
;;   a macro do you don't have the quote all the arguments - ugh
(defn RFV 
  ([field value] (let [fv (symbol "_" (str (gensym)))]
                   `((~fv kiao/hasTemplate ~field)
                     (~fv iao/IAO_0000219 ~value))))
  ([record field value] (let [fv (symbol "_" (str (gensym)))]
                          ;; (list (list fv 'kiao/hasTemplate field)
                          ;;       (list fv 'ro/part_of record)
                          ;;       (list fv 'iao/IAO_0000219 value))))
                          `((~fv kiao/hasTemplate ~field)
                            (~fv ro/part_of ~record)
                            (~fv iao/IAO_0000219 ~value))))
  ([record fv field value] `((~fv kiao/hasTemplate ~field)
                             (~fv ro/part_of ~record)
                             (~fv iao/IAO_0000219 ~value))))



(def ^:dynamic *post-processing-rules*
     (list
      {:name 'uniprot-to-eg
       :query 
       '((fv1 kiao/hasTemplate iaouniprot/uniprotuniProtAccessionIDDataField1)
         (fv1 ro/part_of r)
         (fv2 ro/part_of r)
         (fv2 kiao/hasTemplate iaouniprot/uniprotentrezGeneIDsDataField1) 
         (fv1 iao/IAO_0000219 protICE)
         (protICE iao/IAO_0000219 ?prot)
         (fv2 iao/IAO_0000219 geneICE)
         (geneICE iao/IAO_0000219 ?gene))
       :post-process 
       (fn [bindings]
         (subst-bindings
          '((?gene kro/indirectTemplateOf ?prot))
          bindings)) }


      ;; example of RFV function to facilitate repetitive triple patterns
      ;; {:name 'irefweb-ppi
      ;;  :query 
      ;;  `(~@(RFV '_/r 'iaoirefweb/irefwebinteractorIDsDataField1 '?p1ice)
      ;;    ~@(RFV '_/r 'iaoirefweb/irefwebinteractorIDsDataField1 '?p2ice)
      ;;    ;;this doesn't prevent ?p1 == ?p2
      ;;    ;(?p1ice iao/IAO_0000219 ?p1)  ;get the actual proteins from the ice
      ;;    ;(?p2ice iao/IAO_0000219 ?p2)
      ;;    )
      ;;  :post-process 
      ;;  (fn [bindings] bindings) }


      ))

