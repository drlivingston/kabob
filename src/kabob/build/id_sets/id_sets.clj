(ns kabob.build.id-sets.id-sets
  (use kabob.build.util.neo4j-util
       clojure.pprint)
  )


;;;-------------------------------------------------------------------
;;; globals
;;;-------------------------------------------------------------------

(def ^:dynamic *graph-db-path* "/temp/kabob/neo4j/idsets/")

;;;-------------------------------------------------------------------
;;; index
;;;-------------------------------------------------------------------

(defn index-id [graph index node id]
  (node-index-add (neo4j-node-index graph :ids)
                  node
                  id
                  nil))

(defn lookup-nodes [graph id]
  (node-index-get (neo4j-node-index graph :ids)
                  id
                  nil))

;;;-------------------------------------------------------------------
;;; initalize
;;;-------------------------------------------------------------------

(defn db [& [path]]
  (neo4j-open (or path *graph-db-path*)))

;;;-------------------------------------------------------------------
;;; remove
;;;-------------------------------------------------------------------

(defn purge-node [graph node]
  (let [index (neo4j-node-index graph :ids)]
    (dorun (map (fn [id]
                  (node-index-remove index node id nil))
                (iterator-seq
                 (node-property node :id)))))
  (neo4j-delete-node node)
  nil)

;;;-------------------------------------------------------------------
;;; node update
;;;-------------------------------------------------------------------

(defn create-node [graph ids]
  (neo4j-node graph {:id ids}))

(defn extend-node [graph node ids]
  (node-property! node :id
                  (reduce conj
                          (set (node-property node :id #{}))
                          ids)))

(defn index-node [graph index node ids]
  (dorun
   (map (partial index-id graph index node) ids)))

;;;-------------------------------------------------------------------
;;; merge
;;;-------------------------------------------------------------------

;;TODO the transaction granularity might be too fine-grained
;;  resulting in these methods being unsuitable for parallelization
;;  posibly there should only be one lock around merge-ids only

(defn merge-internal [graph nodes additional-ids]
  (let [[target & others] (seq nodes)
        new-ids (with-neo4j-transaction graph
                  (reduce (fn [ids node]
                            (concat ids
                                    (node-property node :id #{})))
                          additional-ids
                          others))]
    (with-neo4j-transaction graph
      (dorun (map (partial purge-node graph) others))
      (extend-node graph target new-ids)
      (index-node graph :ids target new-ids))))

(defn merge-ids [graph ids]
  (let [[nodes new-ids]
        (with-neo4j-transaction graph
          (reduce (fn [[nodes ids] id]
                    (let [new-nodes (doall (lookup-nodes graph id))]
                      (if (not (empty? new-nodes))
                        [(reduce conj nodes new-nodes) ids]
                        [nodes (conj ids id)])))
                  [#{} #{}]
                  ids))]
    (if (empty? nodes)
      (with-neo4j-transaction graph
        (let [node (create-node graph new-ids)]
          (index-node graph :ids node new-ids)
          node))
      (merge-internal graph nodes new-ids))))

(defn mem-merge-ids [graph ids]
  (println "graph count and max")
  (pprint (count graph)) (println)
  (pprint (reduce max 0 (map (fn [[_ id-set]] (count id-set)))))
  (let [existing-ids (mapcat (fn [id]
                               (get graph id nil))
                             ids)
        combined-ids (set (concat existing-ids ids))]
     (reduce (fn [g id]
               (assoc! g id combined-ids))
             graph
             ;(transient graph)
             (seq combined-ids))))

(defn do-merge [seq-of-id-lists]
  (let [graph {}]
    (persistent!
     (reduce mem-merge-ids
             (transient graph)
             seq-of-id-lists))))

;; (defn graph-sets [graphs id]
;;   (remove nil?
;;           (map-indexed (fn [n g]
;;                          (let [ids (get g id nil)]
;;                            (and ids
;;                                 [n ids])))
;;                        graphs)))


;; (defn acrue-ids [graphs id]
;;   (reduce (fn [[id-set new-graphs] graph]
;;             (let [ids (get graph id nil)]
;;               (if (empty? ids)
;;                 [id-set (conj new-graphs graph)]
;;                 [(concat id-set ids) (conj new-graphs
;;                                            (apply dissoc graph ids))])))
;;           [[][]]
;;           graphs))

;; (defn block-mem-merge-ids [graph-set ids]
;;   (let [[old-ids new-graphs]
;;         (reduce (fn [[id-set graph-set] id]
;;                   (let [[new-ids new-graphs] (acrue-ids graph-set id)]
;;                     [(concat id-set new-ids) new-graphs]))
;;                 [[] graph-set]
;;                 ids)
;;         combined-ids (set (concat ids old-ids))
;;         last-graph (peek new-graphs)
;;         early-graphs (pop new-graphs)]
;;     (conj early-graphs
;;           (reduce (fn [graph id]
;;                     (assoc graph id combined-ids))
;;                   last-graph
;;                   combined-ids))))

;; (defn do-merge [s]
;;   (let [count (atom 0)]
;;     (reduce (fn [graph-set id-set]
;;               (swap! count inc)
;;               (let [graphs (if (= 0 (mod @count 5))
;;                              (do (println @count)
;;                                  (conj graph-set {}))
;;                              graph-set)]
;;                 (block-mem-merge-ids graphs id-set)))
;;             [{}]
;;             s)))


;;;-------------------------------------------------------------------
;;; singletons
;;;-------------------------------------------------------------------

(defn verify-id [graph id]
  (with-neo4j-transaction graph
    (if (empty? (lookup-nodes graph id))
      (index-node graph :ids
                  (create-node graph (list id))
                  (list id))
      nil)))

;;;-------------------------------------------------------------------
;;; output sets
;;;-------------------------------------------------------------------

;; should the sets be burned into the graph database
;;   so that it could be useful for other projects?

;; make this take a callback so it visits all the sets

(defn id-sets [graph]
  (with-neo4j-transaction graph
    (dorun (map (fn [node]
                  (pprint (.getId node))
                  (pprint (node-property node :id #{})))
                (node-seq graph)))))

(defn visit-nodes [graph node-visitor-fn]
  (with-neo4j-transaction graph
    (dorun (map (fn [node]
                  ;;filter out root node
                  (when (has-property node :id)
                    (node-visitor-fn node)))
                (node-seq graph)))))

(defn visit-db-nodes [db-path node-visitor-fn]
  (let [graph (db db-path)]
    (try
      (visit-nodes graph node-visitor-fn)
      nil
      (finally (neo4j-close graph)))))


(defn visit-id-sets [db-path id-seq-visitor-fn]
  (let [graph (db db-path)]
    (try
      (visit-nodes graph
                   (fn [node]
                     (let [id-strings (node-property node :id nil)
                           ids (map read-string (seq id-strings))]
                       (and (seq? ids)
                            (not (empty? ids))
                            (id-seq-visitor-fn ids)))))
      nil
      (finally (neo4j-close graph)))))


;;;-------------------------------------------------------------------
;;; perform run
;;;-------------------------------------------------------------------

;; (defn do-merge [seq-of-id-lists]
;;   (let [graph {}]
;;     (persistent!
;;      (reduce mem-merge-ids
;;              (transient graph)
;;              seq-of-id-lists))))

;; instead the run functions should take a list of functions of one arg - graph
;; they can then use the visitor call-back methods instead

(defn initialize-id-sets [group-fn]
  (let [graph (transient {})]
    (group-fn (partial mem-merge-ids graph))
    (let [id-map (persistent! graph)
          id-sets (seq (set (map (fn [[_ ids]] ids) id-map)))]
      [id-map id-sets])))

      ;; (singleton-fn (partial verify-id graph))
      ;; nil
      ;; (finally (neo4j-close graph)))))

;; (defn initialize-id-sets [db-path group-fn singleton-fn]
;;   (let [graph (db db-path)]
;;     (try
;;       (group-fn     (partial merge-ids graph))
;;       (singleton-fn (partial verify-id graph))
;;       ;;(dorun (map (partial merge-ids graph) (group-fn)))
;;       ;;(dorun (map (partial verify-id graph) (singleton-fn)))
;;       ;;(id-sets graph)
;;       nil
;;       (finally (neo4j-close graph)))))

;;;-------------------------------------------------------------------
;;; end 
;;;-------------------------------------------------------------------

