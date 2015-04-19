(ns edu.ucdenver.ccp.kabob.ice-util
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql

       edu.ucdenver.ccp.kabob.rule))

(defn records-for [v]
  (query-template 
   *kb*
   '?/r
   `((?/fv obo/IAO_0000219 ~v)
     (?/r obo/has_part ?/fv) ;should put a type constraint on here
     )))

(defn record-field-values [r]
  (query-template 
   *kb*
   '(?/t ?/v)
   `((~r obo/has_part ?/fv)
     (?/fv obo/IAO_0000219 ?/v)
     (?/fv kiao/hasTemplate ?/t))))

(defn show-record-field-values [r]
  (dorun (map prn (record-field-values r))))

     
;; (defn gene-ice []
;;   (query-template
;;    *kb*
;;    '?geneICE
;;    '((?rec rdf/type iaoeg/egEntrezGeneInfoFileDataDataRecord20110322) 
;;      (?fv  ro/part_of ?rec) 
;;      (?fv  ro/part_of iaoeg/EGrecordIDDataField1_20110322) 
;;      (?fv  iao/IAO_0000219 ?geneICE))))
