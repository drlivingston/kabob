(ns edu.ucdenver.ccp.kabob.query.cached-entities
  (use edu.ucdenver.ccp.kr.kb
       ;; edu.ucdenver.ccp.kr.rdf
       ;; edu.ucdenver.ccp.kr.sparql
       ;; edu.ucdenver.ccp.kr.unify
       ;; edu.ucdenver.ccp.utils

       ;;edu.ucdenver.ccp.kabob.query.util
       ;;edu.ucdenver.ccp.kabob.query.labels
       ;;edu.ucdenver.ccp.kabob.query.ids
       ;;edu.ucdenver.ccp.kabob.query.abstractions
       ;;edu.ucdenver.ccp.kabob.query.connection-queries

       edu.ucdenver.ccp.kabob.query.ids
       edu.ucdenver.ccp.kabob.query.entities)
  (require [edu.ucdenver.ccp.kabob.query.entity-cache :as ecache]))


;;; --------------------------------------------------------
;;; entity index
;;; --------------------------------------------------------

(defn bio-entity-to-neighbor-entity [kb bio-entity]
  (ecache/get-or-cache-entity
   bio-entity
   (fn [primary-id]
     (let [new-entity
           (let [hgnc-entity (bio-entity-to-hgnc-neighbor-entity kb bio-entity)]
             (if (:entity hgnc-entity)
               hgnc-entity
               (let [protein-entity (coerce-to-protein-entity kb bio-entity)]
                 (if (:entity protein-entity)
                   protein-entity
                   (let [drug-entity (coerce-to-drug-entity kb bio-entity)]
                     (if (:entity drug-entity)
                       drug-entity
                       (default-entity kb bio-entity)))))))]
       (assoc new-entity
         :tags (conj (:tags new-entity) :neighbor))))))
     
(defn hgnc-name-to-entity [kb hgnc-name]
  (ecache/get-or-cache-entity
   (hgnc-id-from-string-name hgnc-name)
   (fn [primary-id]
     (expand-entity kb
       (convert-entity-to-gorgporv kb
         (initialize-hgnc-entity
          (create-entity hgnc-name)))))))  

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
