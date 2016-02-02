(ns edu.ucdenver.ccp.kabob.query.connection-queries
  (use ;;edu.ucdenver.ccp.kabob

       edu.ucdenver.ccp.kr.kb
       ;;edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kabob.query.util
       edu.ucdenver.ccp.kabob.query.abstractions

       clojure.pprint
       ))

;;; --------------------------------------------------------
;;; 
;;; --------------------------------------------------------

;;; --------------------------------------------------------
;;; locations
;;; --------------------------------------------------------

(def location-query
   '((?/of1 owl/someValuesFrom ?/entity)
     (?/of1 owl/onProperty obo/RO_0002313) ;transports or maintains localization of

     (?/loc rdfs/subClassOf ?/of1)
     (?/loc rdfs/subClassOf obo/GO_0051179) ;localization
     (?/loc rdfs/subClassOf ?/to1)

     (?/to1 owl/onProperty kro/results_in_localization_to)
     (?/to1 owl/someValuesFrom ?/loc)
     (?/loc [rdfs/subClassOf *] ?/location)))

(def simple-location-query
   '((?/of1 owl/someValuesFrom ?/entity)
     (?/of1 owl/onProperty obo/RO_0002313) ;transports or maintains localization of

     (?/loc rdfs/subClassOf ?/of1)
     (?/loc rdfs/subClassOf obo/GO_0051179) ;localization
     (?/loc rdfs/subClassOf ?/to1)

     (?/to1 owl/onProperty kro/results_in_localization_to)
     (?/to1 owl/someValuesFrom ?/location)))


(defn locations [kb entity]
  (doall
   (distinct
    ;;(mapcat (partial get-ancestors kb)
    (get-ancestors 
     kb
     (with-magic-ns
       (doall
        (query-template kb '?/location
                        (subst-bindings simple-location-query
                                        {'?/entity entity}))))))))

(defn direct-locations [kb entities]
  (with-magic-ns
    (doall
     (mapcat (fn [entity]
               (query-template kb '?/location
                               (subst-bindings simple-location-query
                                               {'?/entity entity})))
             entities))))

;; (defn locations [kb entity]
;;   (with-magic-ns
;;     (doall
;;      (query-template kb '?/entity
;;                      (subst-bindings location-query
;;                                      {'?/entity entity})))))

(defn location? [kb entity location]
  (with-magic-ns
    (ask kb (subst-bindings location-query
                            {'?/entity entity
                             '?/location location}))))

;;; --------------------------------------------------------
;;; pathways / processes
;;; --------------------------------------------------------

(def process-query
   '((?/subentity [rdfs/subClassOf *] ?/entity)
     (?/of1 owl/someValuesFrom ?/subentity)
     (?/of1 owl/onProperty  obo/RO_0000057)

     (?/event rdfs/subClassOf ?/of1)
     (?/event rdfs/subClassOf ?/proc)
     (!= ?/of1 ?/proc)
     ;;should probably filter bank-nodes / restrictions?
     (?/proc [rdfs/subClassOf *] ?/process)))
;;this needs to be filtered to just process, 
;; although just about everything is process

;; (def simple-process-query
;;    '(;;(?/subentity rdfs/subClassOf ?/entity)
;;      ;;(?/of1 owl/someValuesFrom ?/subentity)
;;      (?/of1 owl/someValuesFrom ?/entity)
;;      (?/of1 owl/onProperty  obo/has_participant)

;;      (?/event rdfs/subClassOf ?/of1)
;;      ;; this is getting all the has-participant restrctions too
;;      ;; from sibling participants in interactions
;;      (?/event rdfs/subClassOf ?/process)
;;      (!= ?/of1 ?/process)))
;;      ;;should probably filter bank-nodes / restrictions?

(def simple-process-query
   '(;;(?/subentity rdfs/subClassOf ?/entity)
     ;;(?/of1 owl/someValuesFrom ?/subentity)
     (?/of1 owl/someValuesFrom ?/entity)
     (?/of1 owl/onProperty  obo/RO_0000057)

     (?/event rdfs/subClassOf ?/of1)
     (?/event rdfs/subClassOf ?/process)
     (!= ?/of1 ?/process)
     ;;(:minus ((?/process rdf/type owl/Restriction)))
     ))

(defn simple-process-sparql-str [kb entity]
  (let [entity-str (binding  [edu.ucdenver.ccp.kr.kb/*kb* kb]
                     (edu.ucdenver.ccp.kr.rdf/sym-to-long-name entity))]
    ;;(println entity-str)
(str
"
PREFIX franzOption_logQuery: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX owl: <http://www.w3.org/2002/07/owl#> 
PREFIX obo: <http://purl.obolibrary.org/obo/> 
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> 
SELECT ?entity ?process ?event ?of1 
WHERE {  ?of1 <http://www.w3.org/2002/07/owl#someValuesFrom> <"
entity-str

"> .  
 ?of1 <http://www.w3.org/2002/07/owl#onProperty> <http://purl.obolibrary.org/obo/RO_0000057> .  
 ?event <http://www.w3.org/2000/01/rdf-schema#subClassOf> ?of1 .  
 ?event <http://www.w3.org/2000/01/rdf-schema#subClassOf> ?process .  
 FILTER (  ( ?of1 != ?process ) )  
 MINUS { ?process <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Restriction> . }
}")))


;; (defn processes [kb entity]
;;   (doall
;;    (distinct
;;     (mapcat (partial get-ancestors kb)
;;             (with-magic-ns
;;               (doall
;;                (query-template kb '?/process
;;                                (subst-bindings simple-process-query
;;                                                {'?/entity entity}))))))))

;;(defn filter-interaction [kb process-list]


(defn processes [kb entity]
  (doall
   (distinct
    (get-ancestors kb
                   (with-magic-ns
                     (doall
                      (sparql-query-template kb '?/process
                                             (simple-process-sparql-str
                                              kb
                                              entity))))))))

(defn direct-processes [kb entities]
  (with-magic-ns
    (doall
     (mapcat (fn [entity]
               (sparql-query-template kb '?/process
                                      (simple-process-sparql-str
                                       kb
                                       entity)))
             entities))))

;; (defn processes [kb entity]
;;   (doall
;;    (distinct
;;     (get-ancestors kb
;;                    (with-magic-ns
;;                      (doall
;;                       (query-template kb '?/process
;;                                       (subst-bindings simple-process-query
;;                                                       {'?/entity entity}))))))))
                   

;; (defn processes [kb entity]
;;   (with-magic-ns
;;     (doall
;;      (query-template kb '?/process
;;                      (subst-bindings process-query
;;                                      {'?/entity entity})))))

(defn process? [kb entity process]
  (with-magic-ns
    (ask kb (subst-bindings process-query
                            {'?/entity entity
                             '?/process process}))))

;;; --------------------------------------------------------
;;; interactions
;;; --------------------------------------------------------

(def interaction-query
  ;;get the processes they participate in
  '((?/rparticipant1 owl/someValuesFrom ?/participant1)
    (?/rparticipant1 owl/onProperty obo/RO_0000057)

    ;;look for interactions
    (?/interaction rdfs/subClassOf ?/rparticipant1)
    (?/interaction rdfs/subClassOf obo/MI_0000) ;; generic interaction
    
    ;;get all the other participants
    (?/interaction rdfs/subClassOf ?/rparticipant2)
    (!= ?/rparticipant1 ?/rparticipant2)
    (?/rparticipant2 owl/onProperty obo/RO_0000057)
    (?/rparticipant2 owl/someValuesFrom ?/participant2)))

(defn interactions [kb participant1]
  (with-magic-ns
    (distinct
     (doall
      (query-template kb '?/participant2
                      (subst-bindings interaction-query
                                      {'?/participant1 participant1}))))))

(defn interaction? [kb participant1 participant2]
  (with-magic-ns
    (ask kb (subst-bindings interaction-query
                            {'?/participant1 participant1
                             '?/participant2 participant2}))))

;;; --------------------------------------------------------
;;; drugs
;;; --------------------------------------------------------

(def drug-query
  ;;get the processes they participate in
  '((?/rparticipant2 owl/someValuesFrom ?/entity)
    (?/rparticipant2 owl/onProperty obo/RO_0000057)

    ;;look for interactions
    (?/interaction rdfs/subClassOf ?/rparticipant2)
    (?/interaction rdfs/subClassOf obo/MI_0000)
    
    ;;get all the other participants
    (?/interaction rdfs/subClassOf ?/rdrugparticipant)
    (!= ?/rparticipant2 ?/rdrugparticipant)   ;; redundant for effeciency
    (?/rdrugparticipant owl/onProperty obo/RO_0000057)
    (?/rdrugparticipant owl/someValuesFrom ?/drug)
    
    ;;verify that the other participant is playing a drug role in the interaction
    (?/interaction rdfs/subClassOf ?/rx)
    (?/rx owl/onProperty obo/BFO_0000055)))
    ;;need a few more triples here
    ;;?rx owl:someValuesFrom ?realized .

(defn drug-interactions [kb entity]
  (with-magic-ns
    (distinct
     (doall
      (query-template kb '?/drug
                      (subst-bindings drug-query
                                      {'?/entity entity}))))))

(defn drug-interaction? [kb entity drug]
  (with-magic-ns
    (ask kb (subst-bindings drug-query
                            {'?/entity entity
                             '?/drug drug}))))



(def target-query
  ;;get the processes they participate in
  '((?/rdrugparticipant owl/someValuesFrom ?/drug)
    (?/rdrugparticipant owl/onProperty obo/RO_0000057)
    (?/interaction rdfs/subClassOf ?/rdrugparticipant)
    (?/interaction rdfs/subClassOf obo/MI_0000)

    ;;verify that the other participant is playing a drug role in the interaction
    (?/interaction rdfs/subClassOf ?/rx)
    (?/rx owl/onProperty obo/BFO_0000055)

    (?/interaction rdfs/subClassOf ?/rparticipant2)
    (!= ?/rparticipant2 ?/rdrugparticipant)   ;; redundant for effeciency
    
    (?/rparticipant2 owl/onProperty obo/RO_0000057)
    (?/rparticipant2 owl/someValuesFrom ?/entity)))

(defn drug-targets [kb drug]
  (with-magic-ns
    (distinct 
     (doall
      (query-template kb '?/entity
                      (subst-bindings target-query
                                      {'?/drug drug}))))))

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
