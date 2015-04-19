(ns edu.ucdenver.ccp.kabob.query.views
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util
       edu.ucdenver.ccp.kabob.query.entities
       clojure.pprint
       ;;clojure.set
       )
  (require [edu.ucdenver.ccp.kabob.query.ids :as ids]
           loom.graph
           loom.attr
           loom.io)
  )


;;; --------------------------------------------------------
;;; skos exact match graph
;;; --------------------------------------------------------

(defn skos-exact-match-graph [kb ids]
  (apply
   loom.graph/digraph
   (map (fn [[x y]]
          [(name x) (name y)])
        (mapcat (fn [id]
                  (concat (map (fn [source-id]
                                 [source-id id])
                               (ids/exact-match-in kb id))
                          (map (fn [target-id]
                                 [id target-id])
                               (ids/exact-match-out kb id))
                          (map (fn [denot]
                                 [id denot])
                               (ids/id-to-bio kb id))

                          ))
                ids))))

(defn view-skos-graph [kb ids]
  (loom.io/view
   (skos-exact-match-graph kb ids)))

;;; --------------------------------------------------------
;;; data processing for views
;;; --------------------------------------------------------


;; given an inverted binding index expand the keys into entities

(defn bio-keys-to-gorgporv-entities [kb map-with-bio-keys]
  (reduce (fn [results [key val]]
            (assoc results
              (convert-to-hgnc-entity kb key) val))
          {}
          map-with-bio-keys))


;; given an inverted binding index down select for things that
;;  bind to more than one entity

(defn remove-singles [inv-bindings]
  (remove (fn [[key val]]
            ;;(nil? (rest val))) ;why doesn't this work?
            (< (count val) 2))
          inv-bindings))


;;; --------------------------------------------------------
;;; creating loom views
;;; --------------------------------------------------------

(defn all-source-nodes [binding-map]
  (distinct
   (mapcat (fn [[k v]]
             (map (fn [entity]
                    (:label entity))
                  v))
           binding-map)))

(defn inv-binding-map-to-loom [binding-map]
  (let [source-nodes (set (all-source-nodes binding-map))
        loom-graph
        (loom.graph/graph
         (reduce (fn [adj-map [node adj]]
                   (assoc adj-map
                     (:label node) (map (fn [m]
                                          (:label m))
                                        adj)))
                 {}
                 binding-map))]
    (reduce (fn [g n]
              (pprint n)
              (loom.attr/hilite g n))
            loom-graph
            (seq source-nodes))))

;;; --------------------------------------------------------
;;; add drugs to graph
;;; --------------------------------------------------------




;;; --------------------------------------------------------
;;; primary (sl) graph
;;; --------------------------------------------------------

(defn primary-graph [entities]
  
  )

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
