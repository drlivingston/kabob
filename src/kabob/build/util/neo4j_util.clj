(ns kabob.build.util.neo4j-util
  (import org.neo4j.tooling.GlobalGraphOperations
          org.neo4j.graphdb.factory.GraphDatabaseFactory
          (org.neo4j.graphdb GraphDatabaseService
                             Node)
          (org.neo4j.graphdb.index Index
                                   ReadableIndex)))

;;;-------------------------------------------------------------------
;;; open close
;;;-------------------------------------------------------------------

(defn neo4j-open [path]
  (.newEmbeddedDatabase (GraphDatabaseFactory.) path))

(defn neo4j-close [graph]
  (.shutdown graph)
  nil)

;;;-------------------------------------------------------------------
;;; transactions
;;;-------------------------------------------------------------------

;;TODO add a way to add a hook or sentinal value on fail
(defmacro with-neo4j-transaction [graph & body]
  `(let [transaction# (.beginTx ~graph)]
     (try
       (let [results# (do ~@body)]
         (.success transaction#)
         results#)
       (finally (.finish transaction#)))))

;;;-------------------------------------------------------------------
;;; global iterators
;;;-------------------------------------------------------------------

(defn node-seq [graph]
  (seq
   (.getAllNodes
    (GlobalGraphOperations/at graph))))

(defn relationship-seq [graph]
  (seq
   (.getAllRelationships
    (GlobalGraphOperations/at graph))))

;;;-------------------------------------------------------------------
;;; key value index helpers
;;;-------------------------------------------------------------------

(defn neo4j-key [k]
  (str k))

;;extend to handle all the following types
;;  plug into the xsd type system used in the KR package?
;; boolean or boolean[]
;; byte or byte[]
;; short or short[]
;; int or int[]
;; long or long[]
;; float or float[]
;; double or double[]
;; char or char[]
;; java.lang.String or String[]

;; note null is not allowed - need a sentinal?
(defn neo4j-value [v]
  (cond
   (string? v) (str v)
   (coll? v)    (into-array String (map str v))
   true (str v)))


(defn neo4j-index [i]
  (str i))

;;;-------------------------------------------------------------------
;;; nodes
;;;-------------------------------------------------------------------

(defn has-property
  ([node key] (.hasProperty node (neo4j-key key))))

(defn node-property
  ([node key] (.getProperty node (neo4j-key key)))
  ([node key default] (.getProperty node (neo4j-key key) default)))

(defn node-property! [node key val]
  (.setProperty node (neo4j-key key) (neo4j-value val))
  node)

(defn neo4j-node [graph & [properties-map]]
  (let [node (.createNode graph)]
    (doseq [[k v] properties-map]
      (node-property! node k v))
    node))

;;this only works if there are no relationships connected to the node
(defn neo4j-delete-node [node]
  (.delete node))
  
;;;-------------------------------------------------------------------
;;; indicies
;;;-------------------------------------------------------------------

(defn neo4j-node-index [graph index]
  (.forNodes (.index graph)
             (neo4j-index index)))

(defn node-index-add [index entity key value]
  (.add index entity (neo4j-key key) (neo4j-value value))
  index)

(defn node-index-remove
  ([index entity]
     (.remove index entity)
     index)
  ([index entity key]
     (.remove index entity (neo4j-key key))
     index)
  ([index entity key value]
     (.remove index entity (neo4j-key key) (neo4j-value value))
     index))

(defn node-index-get [index key value]
  (iterator-seq
   (.get index (neo4j-key key) (neo4j-value value))))


;;;-------------------------------------------------------------------
;;; end
;;;-------------------------------------------------------------------


