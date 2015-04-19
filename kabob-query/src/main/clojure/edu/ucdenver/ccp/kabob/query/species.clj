(ns edu.ucdenver.ccp.kabob.query.species
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util

       clojure.pprint
       ))

;;; --------------------------------------------------------
;;; species query
;;; --------------------------------------------------------

(defn species-query [kb bio-entity]
  (with-magic-ns
    (query-template 
     kb
     '?/species
     `((~bio-entity obo/RO_0002162 ?/species)))))

;;; --------------------------------------------------------
;;; species for entity
;;; --------------------------------------------------------

(defn entity-species [kb entity]
  (let [species (mapcat (partial species-query kb)
                        (remove nil?
                                (conj (:subs entity)
                                      (:entity entity))))]
    (if (> (count species) 1)
      (throw (Exception. (str "Too many species "
                              (count species)
                              " " (:entity entity))))
      (assoc entity
        :species (first species)))))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
