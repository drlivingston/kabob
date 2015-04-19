(ns edu.ucdenver.ccp.kabob.query.entities
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util
       edu.ucdenver.ccp.kabob.query.labels
       edu.ucdenver.ccp.kabob.query.ids
       edu.ucdenver.ccp.kabob.query.abstractions
       edu.ucdenver.ccp.kabob.query.connection-queries
       
       clojure.pprint
       ))
;;  (require [edu.ucdenver.ccp.kabob.query.entity-cache :as ecache]))


;;; --------------------------------------------------------
;;; entity index
;;; --------------------------------------------------------

(defn entity-index-keys [entity]
  (remove nil?
          (conj (get entity :subs nil)
                (get entity :entity nil)
                (get entity :source-id nil))))
  
;;; --------------------------------------------------------
;;; create list
;;; --------------------------------------------------------

(defn create-entity [source]
  {:source-str source})


(defn prep-entity 
  ([entity] {:entity entity})
  ([entity label] {:entity entity :label label}))
  

;; (defn prep-list [l]
;;   (map (fn [entity]
;;          {:entity entity})
;;        l))

;;; --------------------------------------------------------
;;; expand subs
;;; --------------------------------------------------------

(defn expand-subs [kb entity]
  (let [ent (:entity entity)]
    (assoc entity
      :subs (and ent
                 (get-subentities kb ent)))))

;;; --------------------------------------------------------
;;; expand entities
;;; --------------------------------------------------------

(defn expand-entity-with-fn [key expansion-fn kb entity]
  (assoc entity
    key (doall (distinct
                (mapcat (partial expansion-fn kb)
                        (:subs entity))))))

(defn expand-entity-with-fn-and-supers [key expansion-fn kb entity]
  (assoc entity
    key (doall 
         (distinct
          (get-ancestors kb
                         (distinct
                          (expansion-fn kb (:subs entity))))))))

(def entity-expansion-fns
  (list [:bindings  expand-entity-with-fn            interactions]
        [:drugs     expand-entity-with-fn            drug-interactions]
        [:processes expand-entity-with-fn-and-supers direct-processes]
        [:locations expand-entity-with-fn-and-supers direct-locations]
        ))

(def neighbor-entity-expansion-fns
  (list [:bindings  expand-entity-with-fn            interactions]
        [:drugs     expand-entity-with-fn            drug-interactions]
        ))

(def drug-neighbor-entity-expansion-fns
  (list [:bindings  expand-entity-with-fn            interactions]
        ;;as defined, for drugs this finds all the bindigns too
        ;;[:drugs     expand-entity-with-fn            drug-interactions]
        ))

(defn expand-entity
  ([kb entity] (expand-entity kb entity entity-expansion-fns))
  ([kb entity expansion-fns]
   ;;(time
    (reduce (fn [growing-entity [key expansion-type-fn expansion-fn]]
              (expansion-type-fn key expansion-fn kb growing-entity))
            (expand-subs kb entity)
            expansion-fns)))

(defn expand-list [kb l]
  (map (partial expand-entity kb) l))

;; (defn new-expand-list [kb l]
;;   (map (fn [e]
;;          (expand-entity-with-fn kb e :processes processes))
;;        (map (fn [e]
;;               (expand-entity-with-fn kb e :bidings interactions))
;;             (map (partial expand-subs kb)
;;                  (map prep-entity l)))))


;;; --------------------------------------------------------
;;; expand drug
;;; --------------------------------------------------------

(defn create-drug-entity [kb drug]
  {:entity drug
   :label (drug-name kb drug)
   :targets (drug-targets kb drug)})

;;; --------------------------------------------------------
;;; convert to hgnc entity
;;; --------------------------------------------------------

;; (defn convert-to-hgnc-entity [kb bio]
;;   (let [entity-sym (first (gorgporv kb bio))]
;;     (if (nil? entity-sym)
;;       {:source-id bio
;;        :label (name bio)}
;;       ;;else
;;       (let[entity (expand-subs kb {:source-id bio
;;                                    :entity entity-sym})]
;;         (assoc entity
;;           :label (hgnc-symbol kb entity))))))

(defn convert-to-hgnc-entity [kb bio]
  (let [entity-sym (first (gorgporv kb bio))]
    (if (nil? entity-sym)
      {:source-id bio
       :label (name bio)}
      ;;else
      (let[entity (expand-subs kb {:source-id bio
                                   :entity entity-sym})]
        (assoc entity
          :label (hgnc-symbol kb entity))))))

(defn add-hgnc-label [kb entity]
  (assoc entity
    :label (hgnc-symbol kb entity)))

(defn lift-entity-to-gorgporv [kb entity]
  (let [gorgporv (gorgporv kb (or (:entity entity)
                                  (:source-entity entity)))]
    ;;(if (> (count gorgporv) 1)
    (if false ;(> (count gorgporv) 1)
      (throw (Exception. (str "Too many GorGPorV "
                              (count gorgporv)
                              " " (:entity entity))))
      (assoc entity
        :entity (first gorgporv)))))


(defn memo-lift-entity-to-gorgporv [kb entity]
  (let [gorgporv (memo-gorgporv kb (or (:entity entity)
                                       (:source-entity entity)))]
    (if (> (count gorgporv) 1)
      (throw (Exception. (str "Too many GorGPorV "
                              (count gorgporv)
                              " " (or (:entity entity)
                                       (:source-entity entity)))))
      (assoc entity
        :entity (first gorgporv)))))




(defn bio-entity-to-hgnc-neighbor-entity [kb bio-entity]
  (let [new-entity (lift-entity-to-gorgporv kb
                                            {:entity bio-entity
                                             :source-entity bio-entity})]
    (if (nil? (:entity new-entity))
      new-entity
      (add-hgnc-label kb
                      (expand-entity kb 
                                     new-entity
                                     neighbor-entity-expansion-fns)))))


;;switch this to sort and get the shortest label?
(defn coerce-to-protein-entity [kb bio-entity]
  (let [;;bio-entity (:source-entity entity)
        protein-label (shortest-label (protein-name kb bio-entity))]
    (if protein-label
      (expand-entity kb
                     ;;(assoc entity
                     {:source-entity bio-entity
                       :entity bio-entity
                       :tags '(:protein)
                       :label protein-label} ;)
                     neighbor-entity-expansion-fns)
      {:entity nil
       :source-entity bio-entity})))
      ;;bio-entity)))

;;switch this to sort and get the shortest label?
;; or the preferred name or ... ?
(defn coerce-to-drug-entity [kb bio-entity]
  (let [;;bio-entity (:source-entity entity)
        drug-label (shortest-label (drug-name kb bio-entity))]
    (if drug-label
      (expand-entity kb
                     ;;(assoc entity
                     {:source-entity bio-entity
                      :entity bio-entity
                      :tags '(:drug)
                       :label drug-label } ;)
                     drug-neighbor-entity-expansion-fns)
      {:entity nil
       :source-entity bio-entity})))
      ;;entity)))


(defn default-entity [kb bio-entity]
  (expand-entity kb
                 {:entity bio-entity
                  :source-entity bio-entity}
                 ;; (assoc bio-entity
                 ;;   :entity (:source-entity bio-entity))
                 neighbor-entity-expansion-fns))



;;; --------------------------------------------------------
;;; create initial entities
;;; --------------------------------------------------------


;; this need to return a single entity
;; and complain if there is more than one gorgporv
;; (defn convert-entity-to-gorgporv [kb e]
;;   (map (fn [abstraction]
;;          (assoc e
;;                :entity abstraction))
;;        (mapcat (partial gorgporv kb)
;;                (id-to-bio kb (:source-id e)))))

(defn convert-entity-to-gorgporv [kb e]
  (let [bios (id-to-bio kb (:source-id e))]
    (if (> (count bios) 1)
      (throw (Exception. (str "Too many GorGPorV "
                              (count bios)
                              " " (:source-id e))))
      (lift-entity-to-gorgporv kb (assoc e
                                    :entity (first bios))))))

(defn initialize-hgnc-entity [e]
  (let [hgnc (:source-str e)]
    (assoc e
          :source-id (hgnc-id-from-string-name hgnc)
          :label     hgnc)))

(defn initialize-hgnc-entities [kb l]
  (map initialize-hgnc-entity
       (map create-entity l)))


;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
