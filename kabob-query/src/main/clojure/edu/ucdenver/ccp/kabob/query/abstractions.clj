(ns edu.ucdenver.ccp.kabob.query.abstractions
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util

       clojure.pprint
       )
  (require [clojure.core.memoize :as memo]))

;;; --------------------------------------------------------
;;; expand up / down
;;; --------------------------------------------------------

;; would this be faster / better with the RDF API?
(defn parent-classes-no-memo [kb concept]
  (if (anon? concept)
    '()
    (query-template kb '?/parent `((~concept rdfs/subClassOf ?/parent)))))

(def parent-classes
  (memo/lu parent-classes-no-memo
           :lu/threshold 100000))


(defn get-ancestors
  ([kb concept-or-concepts] 
     (remove anon?
             (get-ancestors kb 
                            '() 
                            (if (symbol? concept-or-concepts)
                              (list concept-or-concepts)
                              (seq concept-or-concepts)))))
  ([kb seen-list [concept & rest-to-visit :as to-visit]]
     (if (not (seq  to-visit))
       seen-list
       (let [already-seen? (some (partial = concept) seen-list)
             new-seen-list (if already-seen?
                             seen-list
                             (conj seen-list concept))
             new-visit (if already-seen?
                         '()
                         (parent-classes kb concept))]
         (recur kb new-seen-list (concat new-visit rest-to-visit))))))


(defn subclasses [kb concept]
  (if (anon? concept)
    '()
    (query-template kb '?/child `((?/child rdfs/subClassOf ~concept)))))

(defn get-subentities 
  ([kb concept]
     (remove anon?
             (get-subentities kb '() (list concept))))
  ([kb seen-list [concept & rest-to-visit :as to-visit]]
     (if (not (seq  to-visit))
       seen-list
       (let [already-seen? (some (partial = concept) seen-list)
             new-seen-list (if already-seen?
                             seen-list
                             (conj seen-list concept))
             new-visit (if already-seen?
                         '()
                         (subclasses kb concept))]
         (recur kb new-seen-list (concat new-visit rest-to-visit))))))

;;; --------------------------------------------------------
;;; G or GP or V
;;; --------------------------------------------------------

(defn gorgporv? [kb concept]
  (ask-rdf kb `(~concept rdf/type kbio/GeneSpecificGorGPorVClass)))

(defn gorgporv [kb concept]
  (and concept
       (with-magic-ns
         (doall
          (distinct
           (query-template
            kb '?/gorgporv
            `((~concept [rdfs/subClassOf *] ?/gorgporv)
              (?/gorgporv rdf/type kbio/GeneSpecificGorGPorVClass))))))))

(def memo-gorgporv
  (memo/lu gorgporv
           :lu/threshold 100000))

;;; --------------------------------------------------------
;;; G or GP
;;; --------------------------------------------------------

(defn gorgp? [kb concept]
  (ask-rdf kb `(~concept rdf/type kbio/GeneSpecificGorGPClass)))

;;lifting should always be safe, shouldn't need to transfer over
;; to the gene if this is a protein or another product
(defn gorgp [kb concept]
  (with-magic-ns
    (doall
     (query-template kb '?/gorgp
                     `((~concept [rdfs/subClassOf *] ?/gorgp)
                       (?/gorgp rdf/type kbio/GeneSpecificGorGPClass))))))


;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
