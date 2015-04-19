(ns edu.ucdenver.ccp.kabob.build.bio.entities.generate
  (:gen-class)
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kabob.namespace
       edu.ucdenver.ccp.kabob.build.input-kb
       edu.ucdenver.ccp.kabob.build.output-kb

       edu.ucdenver.ccp.kabob.parallel-utils
       clojure.pprint
       [clojure.string :only (replace-first)]))
  ;;(require edu.ucdenver.ccp.kabob.build.id-sets.generate))

;;;-------------------------------------------------------------------
;;; id types
;;;-------------------------------------------------------------------

(def bio-namespace "kbio")

(def types '[[kiao/DNAIdentifier-Set     "DNA"     obo/SO_0000704]
             [kiao/ProteinIdentifier-Set "Protein" obo/CHEBI_36080]
             [kiao/RNAIdentifier-Set     "RNA"     obo/SO_0000356]
             [kiao/mRNAIdentifier-Set    "mRNA"    obo/SO_0000234]
             [kiao/DiseaseIdentifier-Set "Disease" obo/DOID_4]
             [kiao/DrugIdentifier-Set    "Drug"    obo/CHEBI_23888]])


;; (def types [[(symbol "kiao" "kiao/DNAIdentifier-Set")     'obo/SO_0000704]
;;             [(symbol "kiao" "kiao/ProteinIdentifier-Set") 'obo/CHEBI_36080]
;;             [(symbol "kiao" "kiao/RNAIdentifier-Set")     'obo/SO_0000356]
;;             [(symbol "kiao" "kiao/DiseaseIdentifier-Set") 'obo/DOID_4]
;;             [(symbol "kiao" "kiao/DrugIdentifier-Set")    'obo/CHEBI_23888]
;;             [(symbol "kiao" "kiao/mRNAIdentifier-Set")    'obo/SO_0000234]])

  ;; (map (fn [s]
  ;;        (symbol (namespace s)
  ;;                (str (name s) "-Set")))
  ;;      edu.ucdenver.ccp.kabob.build.id-sets.generate/id-types))

;; '(kiao/DNAIdentifier
;;   kiao/ProteinIdentifier))

;;;-------------------------------------------------------------------
;;; kb lookup functions
;;;-------------------------------------------------------------------

(defn find-denoted-triples [kb ice]
  (query-rdf source-kb ice 'obo/IAO_0000219 nil))

(defn find-denoted [kb ice]
  (nth (first (find-denoted-triples kb ice))
       2))


(defn find-mentioned-triples [kb ice]
  (query-rdf kb ice 'obo/IAO_0000142 nil))

(defn find-mentioned [kb ice]
  (map (fn [[_ _ mentioned]] mentioned)
       (find-mentioned-triples kb ice)))

(defn find-first-mentioned [kb ice]
  (first (find-mentioned kb ice)))


(defn ids-in-set [kb id-set]
  (query-template kb
                  '?/id
                  `((~id-set kro/hasMember ?/id))))
  ;; (prn id-set)
  ;; (let [ids (query-template kb
  ;;                           '?/id
  ;;                           `((~id-set kro/hasMember ?/id)))]
  ;;   (prn ids)
  ;;   ids))

;;;-------------------------------------------------------------------
;;; gen entity
;;;-------------------------------------------------------------------

;;TODO QUESTION should we even be using counters or just UUIDs?

(def ^:dynamic *entity-counters* (atom {}))

(defn initialize-entity-counter [prefix]
  ;;TODO lookup current highest type count in the kb
  ;;  need to print this triple to the end of file too to save for next time
  (let [current-max-count 1
        counter (atom current-max-count)]
    (swap! *entity-counters* assoc prefix counter)))

(defn next-entity-count [prefix]
  (let [counter (get @*entity-counters* prefix nil)]
    (swap! counter inc)))

(defn gen-entity [prefix]
  (symbol "kbio" (str prefix "_" (next-entity-count prefix))))

;;;-------------------------------------------------------------------
;;; find entity
;;;-------------------------------------------------------------------

;; verify there is only one possible denoted entity
(defn find-candidate-entity [source-kb ids]
  (let [denoted-entities (map (partial find-denoted source-kb) ids)
        denoted-set (distinct (remove nil? denoted-entities))]
    (if (= 1 (count denoted-set))
      (first denoted-set)
      nil)))

;; find the id set for the old id
;;  get it's other members
;;  make sure there isn't one that isn't in the new set
(defn check-for-missing-id [seen ids id entity]
  (let [new-ids (query-template source-kb
                                '?/id
                                `((_/set obo/IAO_0000142 ~entity)
                                  (_/set kro/hasMember ~id)
                                  (_/set kro/hasMember ?/id)))]
    (reduce (fn [seen new-id]
              (cond
               (nil? seen)   nil
               (seen new-id) seen
               (ids new-id)  (conj seen new-id)
               true          nil)) ;we saw one we didn't know about
            seen
            new-ids)))

;;TODO need a way to restrict the sets to only those active?
;; or only those that mention the specific entity - that will work 
(defn verify-old-id-sets [source-kb ids entity]
  ;(let [ids (set ids)]
  (reduce (fn [seen id]
            (cond
             (nil? seen) nil
             (seen id)   seen
             true (check-for-missing-id seen ids id entity)))
          #{}
          (seq ids)))

(defn verify-entity [source-kb ids entity]
  (if (verify-old-id-sets source-kb (set ids) entity)
    entity
    nil))

(defn find-entity [source-kb id-set ids]
  (or (find-first-mentioned source-kb id-set)
      (let [entity (find-candidate-entity source-kb ids)]
        (and entity
             (verify-entity source-kb ids entity)))))

;;;-------------------------------------------------------------------

(defn find-or-gen-entity [source-kb prefix id-set ids]
  (or (and source-kb
           (find-entity source-kb id-set ids))
        (gen-entity prefix)))

;;;-------------------------------------------------------------------
;;; triples
;;;-------------------------------------------------------------------

(defn create-triples [id-set ids entity entity-type]
  (concat `((~entity rdfs/subClassOf ~entity-type)
            (~id-set obo/IAO_0000142 ~entity))
          (map (fn [id]
                 `(~id obo/IAO_0000219 ~entity))
               ids)))

;;;-------------------------------------------------------------------
;;; writing
;;;-------------------------------------------------------------------

(defn serialize-entities [old-kb new-kb output-kb
                          id-set-type prefix entity-type]
  (let [write-agent (agent output-kb)
        visit-counter (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (with-work-queue work
      (query-visit new-kb
                   (fn [bindings]
                     ;;(pprint bindings)
                     (swap! visit-counter inc)
                     (when (= 0 (mod @visit-counter 10000))
                       ;;(close output-kb)
                       (System/gc))
                     (when (= 0 (mod @visit-counter 250000))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @visit-counter " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (work (fn [] ;parallelize here
                             (let [id-set (get bindings '?/idset)
                                   ;;garbage (prn "id set: " id-set)
                                   ids (ids-in-set new-kb id-set)
                                   entity (find-or-gen-entity old-kb
                                                              prefix
                                                              id-set
                                                              ids)
                                   triples (create-triples id-set
                                                           ids
                                                           entity
                                                           entity-type)]
                               ;;(prn triples)
                               (blocking-write-triples write-agent triples)))))
                   `((?/idset rdf/type ~id-set-type))))
    (await write-agent)))

;;;-------------------------------------------------------------------
;;;-------------------------------------------------------------------
;;; output
;;;-------------------------------------------------------------------

(defn open-output-kb [base-dir entity-type]
  (zipped-output-kb (str base-dir (name entity-type) "-Entities.nt.gz")))


;;; --------------------------------------------------------
;;; --------------------------------------------------------

(defn generate-type-entities [old-kb new-kb output-dir
                              id-set-type prefix entity-type]
  (let [output-kb (open-output-kb output-dir prefix)]
    (try
      (serialize-entities old-kb new-kb output-kb
                          id-set-type prefix entity-type)
      (finally (close output-kb)))))

(defn generate-all-entities [old-kb new-kb output-dir]
  (dorun
   (map (fn [[id-set-type prefix entity-type]]
          ;;TODO add retrive for existing count
          (initialize-entity-counter prefix)
          (generate-type-entities old-kb
                                  new-kb
                                  output-dir
                                  id-set-type
                                  prefix
                                  entity-type))
        types)))

;;; --------------------------------------------------------
;;; --------------------------------------------------------


(defn set-to-entity [set set-type prefix]
  (symbol bio-namespace
          (replace-first (name set)
                         (str (name set-type) "-")
                         (str prefix "_"))))

(defn single-set-triples [id-set [set-type prefix entity-type]]
  (let [entity (set-to-entity id-set set-type prefix)]
    `((~entity rdfs/subClassOf ~entity-type)
      (~id-set obo/IAO_0000142 ~entity))))



(defn serialize-sets [kb output-kb
                          [id-set-type prefix entity-type :as type]]
  (let [write-agent (agent output-kb)
        visit-counter (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (with-work-queue work
      (query-visit kb
                   (fn [bindings]
                     (swap! visit-counter inc)
                     (when (= 0 (mod @visit-counter 10000))
                       (System/gc))
                     (when (= 0 (mod @visit-counter 250000))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @visit-counter " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (work (fn [] ;parallelize here
                             (let [id-set (get bindings '?/idset)
                                   triples (single-set-triples id-set type)]
                               (blocking-write-triples write-agent triples)))))
                   `((?/idset rdf/type ~id-set-type))))
    (await write-agent)))

(defn generate-set-triples [kb output-dir
                            [id-set-type prefix entity-type :as type ]]
  (let [output-kb (open-output-kb output-dir prefix)]
    (try
      (println "mentions and type:")
      (pprint type)
      (time
       (serialize-sets kb output-kb type))
      (finally (close output-kb)))))

(defn generate-type-and-mentions [kb output-dir]
  (dorun
   (map (fn [type]
          (generate-set-triples kb output-dir type))
        types)))




(defn denotes-triples [id id-set [set-type prefix entity-type]]
  (let [entity (set-to-entity id-set set-type prefix)]
    `((~id obo/IAO_0000219 ~entity))))

(defn serialize-denotes [kb output-kb
                         [id-set-type prefix entity-type :as type]]
  (let [write-agent (agent output-kb)
        visit-counter (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (with-work-queue work
      (query-visit kb
                   (fn [bindings]
                     (swap! visit-counter inc)
                     (when (= 0 (mod @visit-counter 10000))
                       (System/gc))
                     (when (= 0 (mod @visit-counter 250000))
                       (let [new-t (.getTime (java.util.Date.))]
                         (println @visit-counter " in " (- new-t @t) "ms")
                         (reset! t new-t)))
                     (work (fn [] ;parallelize here
                             (let [id (get bindings '?/id)
                                   id-set (get bindings '?/idset)
                                   triples (denotes-triples id id-set type)]
                               (blocking-write-triples write-agent triples)))))
                   `((?/idset rdf/type ~id-set-type)
                     (?/idset kro/hasMember ?/id))))
    (await write-agent)))

(defn generate-denotes-triples [kb output-dir
                            [id-set-type prefix entity-type :as type ]]
  (let [output-kb (open-output-kb output-dir (str prefix "-ids"))]
    (try
      (println "id denotes:")
      (pprint type)
      (time
       (serialize-denotes kb output-kb type))
      (finally (close output-kb)))))

(defn generate-id-denotes [kb output-dir]
  (dorun
   (map (fn [type]
          (generate-denotes-triples kb output-dir type))
        types)))


;;; --------------------------------------------------------
;;; command line arguments and helpers
;;; --------------------------------------------------------

(defn command-line-args [original-args]
  {;;this is the source / old kb
   :original-kb-details
   {;; The URL to the Sesame server to query
    :server-url (nth original-args 0)
    ;; The name of the repository to connect to
    :repo-name (nth original-args 1)
    ;; The username to use when connecting
    :username (nth original-args 2)
    ;; The password to use when connecting
    :password (nth original-args 3)}

   :new-kb-details
   {;;this is the being built kb
    ;; The URL to the Sesame server to query
    :server-url (nth original-args 4)
    ;; The name of the repository to connect to
    :repo-name (nth original-args 5)
    ;; The username to use when connecting
    :username (nth original-args 6)
    ;; The password to use when connecting
    :password (nth original-args 7)}

   ;; The output directory where generated triple files will be placed. 
   ;; IMPORTANT: The output directory path must end with a forward slash.
   :output-directory (nth original-args 8)})

(defn logging-header [args]
  (prn "Clojure version: " *clojure-version*)
  (pprint (list :original-kb-details (:original-kb-details args)))
  (pprint (list :new-kb-details (:new-kb-details args)))
  (prn "Output directory: " (:output-directory args))
  (prn "ID types: ")
  (pprint types))

;;;-------------------------------------------------------------------
;;; process
;;;-------------------------------------------------------------------

(defn -main [& original-args]
  (pprint original-args)
  (let [args (command-line-args original-args)]
    (pprint args)
    (logging-header args)
    (binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
              [;;["franzOption_queryEngine" "franz:sparql-1.0"]
               ["franzOption_memoryLimit" "franz:85g"]
               ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
               ;;["franzOption_chunkProcessingAllowed" "franz:yes"]
               ;;["franzOption_chunkProcessingSize" "franz:500000"]
               ["franzOption_logQuery" "franz:yes"]
               ]]
      ;;(binding [*work-queue-single-threaded* true]
      (let [kb (open-kb (:new-kb-details args))]
        (generate-type-and-mentions kb (:output-directory args))
        (generate-id-denotes kb (:output-directory args)))))
  (System/exit 0))



;;original:
;; (defn -main [& original-args]
;;   (pprint original-args)
;;   (let [args (command-line-args original-args)]
;;     (pprint args)
;;     (logging-header args)
;;     ;;(binding [*work-queue-single-threaded* true]
;;       (generate-all-entities (and (:original-kb-details args)
;;                                   (not (= "nil"
;;                                           (:server-url
;;                                            (:original-kb-details args))))
;;                                   (open-kb (:original-kb-details args)))
;;                              (open-kb (:new-kb-details args))
;;                              (:output-directory args)));;)
;;   (System/exit 0))

;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------
