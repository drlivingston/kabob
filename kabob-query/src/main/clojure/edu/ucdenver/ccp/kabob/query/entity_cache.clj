(ns edu.ucdenver.ccp.kabob.query.entity-cache
  (use ;;edu.ucdenver.ccp.utils

       clojure.pprint
       ;;clojure.set
       )
  (require [clojure.java.io :as io]
           [edu.ucdenver.ccp.utils :as krutil]
           [edu.ucdenver.ccp.kabob.query.entities :as entities])
  (import java.lang.IllegalArgumentException))


;;; --------------------------------------------------------
;;; constants
;;; --------------------------------------------------------

;; (defonce ^:dynamic entity-cache (ref {}))
;; (defonce ^:dynamic entity-cache-file (ref nil))
;; (defonce ^:dynamic entity-cache (atom {}))
;; (defonce ^:dynamic entity-cache-file (atom nil))

(defonce ^:dynamic entity-cache (atom {}))
(defonce ^:dynamic entity-cache-file (atom nil))

;; (defonce ^:dynamic entity-cache {})
;; (defonce ^:dynamic entity-cache-file nil)

;;(defonce ^:dynamic entity-write-agent (agent nil))
(defonce ^:dynamic entity-write-agent (agent nil))

(def append-entity-cache)

;;; --------------------------------------------------------
;;; cache binding
;;; --------------------------------------------------------

;;(defmacro with-ecache [[cache cache-file write-agent :as entitycache] & body]
(defmacro with-ecache [entitycache & body]
  `(let [[cache# cache-file# write-agent#] ~entitycache]
     (binding [edu.ucdenver.ccp.kabob.query.entity-cache/entity-cache
               cache#
               edu.ucdenver.ccp.kabob.query.entity-cache/entity-cache-file
               cache-file#
               edu.ucdenver.ccp.kabob.query.entity-cache/entity-write-agent
               write-agent#]
       ~@body)))

;;; --------------------------------------------------------
;;; cache api
;;; --------------------------------------------------------

;; should this return an empty map or nil?
(defn get-entity
  ([key]    (get-entity entity-cache key))
  ([ec key] (get @ec key nil)))

;; should the cache have a seperate table for non-primary names
(defn add-entity [curr-ec entity]
  (let [entity-keys (entities/entity-index-keys entity)]
    (reduce (fn [ec key]
              (assoc ec key entity))
            curr-ec
            entity-keys)))

(defn cache-entity
  ([entity] (cache-entity entity-cache entity))
  
  ([ec entity]
   ;;(send entity-write-agent (fn [_] (append-entity-cache @entity-cache-file entity)))
   (send entity-write-agent (fn [cache-file]
                              (append-entity-cache cache-file entity)
                              cache-file))
   (swap! ec add-entity entity)))

(defn get-or-cache-entity
  ([primary-id entity-creation-fn]
   (get-or-cache-entity entity-cache primary-id entity-creation-fn))

  ([ec primary-id entity-creation-fn]
   (let [entity (get-entity ec primary-id)]
     (if (or entity (not entity-creation-fn))
       entity
       (let [new-entity (entity-creation-fn primary-id)]
         (cache-entity ec new-entity)
         new-entity)))))
  
;;; --------------------------------------------------------
;;; cache io
;;; --------------------------------------------------------

(defn write-entity [output entity]
  (binding [*out* output]
    (prn entity)))

(defn append-entity-cache [file entity]
  (try 
    (with-open [output (io/writer file :append true)]
      (write-entity output entity))
    (catch java.lang.IllegalArgumentException e
      (with-open [output (io/writer file)]
        (write-entity output entity)))))
      

(defn overwrite-entity-cache [file ec]
  (with-open [output (io/writer file :append false)]
    (doall
     (map (fn [[key entity]]
            (write-entity output entity))
          ec))))

(defn read-entity-cache [file]
  (reduce (fn [ec entity]
            (add-entity ec entity))
          {}
          (try
            (krutil/read-all-input file)
            (catch Exception e nil))))

(defn load-entity-cache [file]
  (let [new-cache (read-entity-cache file)]
    (send entity-write-agent (fn [_] file))
    (swap! entity-cache-file (fn [_] file))
    (swap! entity-cache (fn [_] new-cache))
    (await entity-write-agent)))


(defn initialize-entity-cache [[cache cache-file write-agent :as entity-cache]]
  (binding [entity-cache       cache
            entity-cache-file  cache-file
            entity-write-agent write-agent]
    (load-entity-cache @cache-file)))


;; (defn load-entity-cache [file]
;;   (let [new-cache (read-entity-cache file)]
;;     (dosync (ref-set entity-cache-file file))))
;; ;;            (ref-set entity-cache new-cache))))

;;; --------------------------------------------------------
;;; 
;;; --------------------------------------------------------


;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
