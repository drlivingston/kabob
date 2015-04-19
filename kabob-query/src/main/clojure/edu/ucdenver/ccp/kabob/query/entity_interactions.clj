(ns edu.ucdenver.ccp.kabob.query.entity-interactions
  (use ;;edu.ucdenver.ccp.kr.kb
       ;;edu.ucdenver.ccp.kr.rdf
       ;;edu.ucdenver.ccp.kr.sparql
       ;;edu.ucdenver.ccp.kr.unify
       ;;edu.ucdenver.ccp.utils
       
       clojure.set
       
       edu.ucdenver.ccp.kabob.query.entities
       ;;edu.ucdenver.ccp.kabob.query.util
       ;;edu.ucdenver.ccp.kabob.query.ids
       ;;edu.ucdenver.ccp.kabob.query.abstractions
       ;;edu.ucdenver.ccp.kabob.query.connection-queries

       clojure.pprint
       )
  (require [edu.ucdenver.ccp.kabob.query.entity-cache :as ecache]
           [edu.ucdenver.ccp.kabob.query.entities :as entities]
           [edu.ucdenver.ccp.kabob.query.species      :as species]))


;;; --------------------------------------------------------
;;; 
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; invert indexes
;;; --------------------------------------------------------

(defn invert-entity-indexes [elist]
  (let [entity-keys (map first entity-expansion-fns)]
    (reduce (fn [i-index key]
              (reduce (fn [entity-index entity]
                        (reduce (fn [value-index val]
                                  (update-in value-index [key val]
                                             conj
                                             entity))
                                             ;; this could conj on the entity
                                             ;; but it makes pprinting a mess
                                             ;; below for better pprinting
                                             ;;(:entity entity)))
                                entity-index
                                (get entity key)))
                      i-index
                      elist))
            {}
            entity-keys)))


;;; --------------------------------------------------------
;;; direct interactions
;;; --------------------------------------------------------

;;(defn direct-interactions 

;;; --------------------------------------------------------
;;; common binding partner
;;; --------------------------------------------------------

;; (count
;;  (doall 
;;   (distinct
;;    (map edu.ucdenver.ccp.kabob.query.entity-cache/get-entity
;;         (:bindings (:entity (first bindings-with-backgrounds))))))))

(defn distinct-entities [entity-list]
  (doall 
   (distinct
    (map ecache/get-entity entity-list))))
  

;; for all binding partners inferred by list1 and list2 compute
;;  how many in list1 they align with and how many in list two
;;  and how many they bind with overall
(defn associated-key-partners [kb key list1 list2]
  (let [c1 (count list1)
        c2 (count list2)
        inv1 (get (invert-entity-indexes list1) key {})
        inv2 (get (invert-entity-indexes list2) key {})
        combined-neighbors (union (set (keys inv1)) (set (keys inv2)))]
    (map
     (fn [neighbor]
       (let [bio-entity (try
                          ;;(bio-entity-to-neighbor-entity kb neighbor)
                          (ecache/get-entity neighbor)
                          (catch Exception e {:entity neighbor}))
             entity-count-1 (count (get inv1 neighbor))
             entity-count-2 (count (get inv2 neighbor))
             global-count (count (distinct-entities (get bio-entity key)))]
         ;; global (get bio-entity key)
         ;; global-count (count (get bio-entity key))]
         (if (not (<=  entity-count-1  entity-count-2 global-count))
           (pprint [neighbor
                    (species/species-query kb neighbor)
                    [entity-count-1  entity-count-2 global-count]
                    bio-entity]))
         {:key neighbor
          :entity bio-entity
          :count-list1 entity-count-1
          :count-list2 entity-count-2
          :count-global global-count
          :ratio-list1 (if (= c1 0) 0 (/ entity-count-1 c1))
          :ratio-list2 (if (= c1 0) 0 (/ entity-count-2 c2))
          :ratio-list1-global (if (= global-count 0)
                                0
                                (/ entity-count-1 global-count))
          :ratio-list2-global (if (= global-count 0)
                                0
                                (/ entity-count-2 global-count))
          :unique (= entity-count-1 entity-count-2 global-count)
          :list1-entities (get inv1 neighbor)
          }))
     (seq combined-neighbors))))

;; (defn associated-key-partners [kb key list1 list2]
;;   (let [c1 (count list1)
;;         c2 (count list2)
;;         inv1 (get (invert-entity-indexes list1) key {})
;;         inv2 (get (invert-entity-indexes list2) key {})
;;         combined-keys (union (set (keys inv1)) (set (keys inv2)))]
;;     (map (fn [key]
;;            (let [;;bio-entity (time (bio-entity-to-hgnc-neighbor-entity kb key))
;;                  bio-entity (try
;;                               (bio-entity-to-hgnc-neighbor-entity kb key)
;;                               (catch Exception e key))
;;                  entity-count-1 (count (get inv1 key))
;;                  entity-count-2 (count (get inv2 key))
;;                  global-count (count (get bio-entity key))]
;;              {:key key
;;               :entity bio-entity
;;               :count-list1 entity-count-1
;;               :count-list2 entity-count-2
;;               :entity-global global-count
;;               :ratio-list1 (if (= c1 0) 0 (/ entity-count-1 c1))
;;               :ratio-list2 (if (= c1 0) 0 (/ entity-count-2 c2))
;;               :list1-global (if (= global-count 0) 0 (/ entity-count-1 global-count))
;;               :list2-global (if (= global-count 0) 0 (/ entity-count-2 global-count))
;;               }))
;;          (seq combined-keys))))

(defn associated-binding-partners [kb list1 list2]
  (associated-key-partners kb :bindings list1 list2))
  

;;; --------------------------------------------------------
;;; shared in key (e.g. processes)
;;; --------------------------------------------------------

(defn shared-with-key [key e1 e2]
  (let [e1-parts (get e1 key)
        e2-parts (get e2 key)]
    (intersection e1-parts e2-parts)))

(defn shared-processes [e1 e2]
  (shared-with-key :processes e1 e2))

;;; --------------------------------------------------------
;;; inlist bindings
;;; --------------------------------------------------------

(defn entity-hash [entities]
  (reduce (fn [m-outer entity]
            (reduce (fn [m-inner key]
                      (assoc m-inner key entity))
                    m-outer
                    (entities/entity-index-keys entity)))
          {}
          entities))
  
(defn inlist-bindings [entities]
  (let [ehash (entity-hash entities)]
    (mapcat (fn [entity]
              (remove nil?
                      (map (fn [neighbor]
                             (let [neighbor-entity (ehash neighbor)]
                               (if neighbor-entity
                                 [entity neighbor-entity]
                                 nil)))
                           (:bindings entity))))
            entities)))

;;; --------------------------------------------------------
;;; drug bindings
;;; --------------------------------------------------------

(defn entities-drugs [neighbor-cache entities]
  (ecache/with-ecache neighbor-cache
    (doall
     (remove nil?
             (mapcat (fn [e]
                       (map (fn [d]
                              (let [drug (ecache/get-entity d)]
                                (if drug
                                  (do (pprint (count (:bindings drug)))
                                    [e drug])
                                  (do
                                    (pprint ["bad drug" d])
                                    nil))))
                            (:drugs e)))
                     entities)))))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
