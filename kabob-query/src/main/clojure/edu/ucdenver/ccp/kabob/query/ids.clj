(ns edu.ucdenver.ccp.kabob.query.ids
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util

       clojure.pprint
       )
  (require [edu.ucdenver.ccp.kabob.query.abstractions :as abstractions]))


;;; --------------------------------------------------------
;;; ids to entities
;;; --------------------------------------------------------

(defn exact-match-in [kb id]
  (with-magic-ns
    (doall
     (query-template kb '?/id
                     `((?/id skos/exactMatch ~id))))))

(defn exact-match-out [kb id]
  (with-magic-ns
    (doall
     (query-template kb '?/id
                     `((~id skos/exactMatch ?/id))))))


;;; --------------------------------------------------------
;;; ids to entities
;;; --------------------------------------------------------

(defn id-to-bio [kb id]
  (with-magic-ns
    (doall
     (query-template kb '?/entity
                     `((~id obo/IAO_0000219 ?/entity))))))

;;; --------------------------------------------------------
;;; get ids
;;; --------------------------------------------------------

(defn bio-to-ids 
  ([kb bio]
     (with-magic-ns
       (doall
        (query-template kb '?/id
                        `((?/subs [rdfs/subClassOf *] ~bio)
                          (?/id obo/IAO_0000219 ?/subs))))))
  ([kb bio & namespaces]
     (let [ids (bio-to-ids kb bio)]           
       (remove (fn [id]
                 (let [id-ns (namespace id)]
                   (not 
                    (some (fn [keep-ns]
                            (= id-ns keep-ns))
                          namespaces))))
               ids))))

;;; --------------------------------------------------------
;;; lift bio to gorgp and then back to all ids
;;; --------------------------------------------------------

(defn ids-for-gorgporv [kb bio]
  (let [gorgporv-list (abstractions/gorgporv kb bio)]
    (distinct 
     (mapcat (fn [gorgporv]
               (mapcat (partial bio-to-ids kb)
                       (abstractions/get-subentities
                        kb
                        gorgporv)))
             gorgporv-list))))


               
;;;; --------------------------------------------------------
;;;; CREATING IDS:
;;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; HGNC
;;; --------------------------------------------------------

(defn hgnc-id-from-string-name [s]
  (symbol "iaohgnc"
          (str "HGNC_" s "_ICE")))

(defn hgnc-id-from-string-id [s]
  (symbol "iaohgnc"
          (str "HGNC_" s "_ICE")))

;;; --------------------------------------------------------
;;; EG
;;; --------------------------------------------------------

(defn eg-id-from-string-id [s]
  (symbol "iaoeg"
          (str "EG_" s "_ICE")))

;;; --------------------------------------------------------
;;; UniProt
;;; --------------------------------------------------------

(defn uniprot-id-from-string-id [s]
  (symbol "iaouniprot"
          (str "UNIPROT_" s "_ICE")))

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
