(ns edu.ucdenver.ccp.kabob.query.labels
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util
       edu.ucdenver.ccp.kabob.query.ids
       edu.ucdenver.ccp.kabob.query.abstractions
       edu.ucdenver.ccp.kabob.query.connection-queries

       clojure.pprint
       ))

;;; --------------------------------------------------------
;;; get shortest label
;;; --------------------------------------------------------

(defn shortest-label [strs]
  (first
   (remove nil?
           (sort-by count strs))))

;;; --------------------------------------------------------
;;; hgnc name
;;; --------------------------------------------------------



(defn hgnc-id [kb entity]
  (mapcat (fn [sub]
            (bio-to-ids kb sub "iaohgnc"))
          (:subs entity)))

(defn hgnc-symbol [kb entity]
  (first
   (remove nil?
           (map (fn [n]
                  (first ;;this is second or nil
                   (rest
                    ;;the capture needs to start with a non digit
                    ;; (this is to prevent hgnc ids)
                    ;; can symbols start with a number?
                    (re-matches #"HGNC_(\D.*)_ICE" n))))
                (map name (hgnc-id kb entity))))))


;; (defn hgnc-symbol [kb bio-entity]
;;   (with-magic-ns
;;     (query-template 
;;      kb
;;      '?/drugname
;;      `((?/id obo/IAO_0000219 ~bio-entity)
;;        (?/idfv obo/IAO_0000219 ?/id)
;;        (?/idfv kiao/hasTemplate
;;                iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
;;        (?/rec obo/has_part ?/idfv)
;;        (?/rec obo/has_part ?/namefv)
;;        (?/namefv kiao/hasTemplate
;;                  iaodrugbank/DrugBankDrugRecord_drugNameDataField1)
;;        (?/namefv obo/IAO_0000219 ?/drugname)))))


;;; --------------------------------------------------------
;;; trembl name
;;; --------------------------------------------------------


(defn uniprot-primary-name [kb bio-entity]
  (with-magic-ns
    (query-template 
     kb
     '?/drugname
     `((?/id obo/IAO_0000219 ~bio-entity)
       (?/idfv obo/IAO_0000219 ?/id)
       (?/idfv kiao/hasTemplate
               iaouniprot/UniProtFileRecord_primaryAccessionDataField1)
               ;;iaouniprot/SparseUniProtFileRecord_primaryAccessionDataField1)
       (?/rec obo/has_part ?/idfv)
       (?/rec obo/has_part ?/namefv)
       (?/namefv kiao/hasTemplate
                 iaouniprot/UniProtFileRecord_nameDataField1)
                 ;;iaouniprot/SparseUniProtFileRecord_nameDataField1)
       (?/namefv obo/IAO_0000219 ?/drugname)))))


(defn uniprot-secondary-name [kb bio-entity]
  (with-magic-ns
    (query-template 
     kb
     '?/drugname
     `((?/id obo/IAO_0000219 ~bio-entity)
       (?/idfv obo/IAO_0000219 ?/id)
       (?/idfv kiao/hasTemplate
               iaouniprot/UniProtFileRecord_accessionDataField1)
               ;;iaouniprot/UniProtFileRecord_primaryAccessionDataField1)
               ;;iaouniprot/SparseUniProtFileRecord_primaryAccessionDataField1)
       (?/rec obo/has_part ?/idfv)
       (?/rec obo/has_part ?/namefv)
       (?/namefv kiao/hasTemplate
                 iaouniprot/UniProtFileRecord_nameDataField1)
                 ;;iaouniprot/SparseUniProtFileRecord_nameDataField1)
       (?/namefv obo/IAO_0000219 ?/drugname)))))

(defn trembl-name [kb bio-entity]
  (with-magic-ns
    (query-template 
     kb
     '?/drugname
     `((?/id obo/IAO_0000219 ~bio-entity)
       (?/idfv obo/IAO_0000219 ?/id)
       (?/idfv kiao/hasTemplate
               iaouniprot/SparseUniProtFileRecord_primaryAccessionDataField1)
       (?/rec obo/has_part ?/idfv)
       (?/rec obo/has_part ?/namefv)
       (?/namefv kiao/hasTemplate
                 iaouniprot/SparseUniProtFileRecord_nameDataField1)
       (?/namefv obo/IAO_0000219 ?/drugname)))))

(defn protein-name [kb bio-entity]
  (or (uniprot-primary-name kb bio-entity)
      (uniprot-secondary-name kb bio-entity)
      (trembl-name kb bio-entity)))


;;; --------------------------------------------------------
;;; drug name
;;; --------------------------------------------------------

(defn drug-name [kb bio-entity]
  (with-magic-ns
    (query-template 
     kb
     '?/drugname
     `((?/id obo/IAO_0000219 ~bio-entity)
       (?/idfv obo/IAO_0000219 ?/id)
       (?/idfv kiao/hasTemplate
               iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
       (?/rec obo/has_part ?/idfv)
       (?/rec obo/has_part ?/namefv)
       (?/namefv kiao/hasTemplate
                 iaodrugbank/DrugBankDrugRecord_drugNameDataField1)
       (?/namefv obo/IAO_0000219 ?/drugname)))))
       

;;; --------------------------------------------------------
;;; label
;;; --------------------------------------------------------

(defn rdfs-label [kb entity]
  (with-magic-ns
    (query-template 
     kb
     '?/label
     `((~entity rdfs/label ?/label)))))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
