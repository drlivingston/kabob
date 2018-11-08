(def rule-directory "../step_heah_add_entity_families_to_bio_2/")
(def rule {:name "add_reactome_entity_families_to_bio_part_1"})

;; take initial results from rule results
(defn grab-initial-results [rule]
    (let [fname (str rule-directory (:name rule) ".nt")]
        (with-open [rdr (reader fname)]
            (doall (line-seq rdr)))))

(defn println-str [& more]
  (with-out-str (apply println more)))

;; map bio-nodes to Reactome ICE ids
;; map component lists to Reactome ICE ids
(defn map-ice-to-list [lines ice-to-set-map ice-to-bio-ids-map current-list current-set current-ice]
    (cond (empty? lines) (do 
                            [ice-to-set-map ice-to-bio-ids-map])
          :else
          (let [s (first lines)
                t (str/split s #" ")
                ]
              (cond
                  ;; if we see the reactome id, remember it
                  (str/index-of (second t) "IAO_0000219")
                  (do ;(println "ice guy found" (first t) "denoting" (nth t 2))
                      (recur (rest lines) ice-to-set-map ice-to-bio-ids-map
                             current-list (nth t 2) (first t)))
                  ;; if we see the connector, remember it
                  (str/index-of (second t) "ekws_temp_list_connector_relation")
                  (do 
                  (recur (rest lines) ice-to-set-map ice-to-bio-ids-map (nth t 2) current-set current-ice)) 
                  ;; if we see the extra BIO node, remember it and make sure we have an empty list of its list nodes
                  (str/index-of (second t) "#equivalentClass")
                  (do 
                  (if (nil? (get ice-to-bio-ids-map current-ice))
                      ;; add a new empty list to the REACTOME ID key
                      (do 
                      (recur (rest lines) (assoc ice-to-set-map current-ice {:union (first t) :other (nth t 2)}) (assoc ice-to-bio-ids-map current-ice '()) 
                             current-list (nth t 2) current-ice))
                      (do 
                      (recur (rest lines) (assoc ice-to-set-map current-ice {:union (first t) :other (nth t 2)}) ice-to-bio-ids-map 
                             current-list (nth t 2) current-ice))))
                  ;; if we see a list member connected to a bio node, add it
                  (str/index-of (second t) "#first")
                  (do 
                      (recur (rest lines) ice-to-set-map (assoc ice-to-bio-ids-map current-ice 
                                          (conj (get ice-to-bio-ids-map current-ice) {:lst (first t) :bio (nth t 2)}))
                             current-list current-set current-ice))
                  ;; else just keep reading more lines
                  :else (recur (rest lines) ice-to-set-map ice-to-bio-ids-map current-list current-set current-ice)))))
                
;; connect the list nodes for a reactome ID denoting a set 
(defn process-list-lines [rlist lines]
    (cond (empty? rlist) lines
          (= (count rlist) 1)
          (let [s1 (str (:lst (first rlist)) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> " (:bio (first rlist)) " .")
                s2 (str (:lst (first rlist)) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> <http://www.w3.org/1999/02/22-rdf-syntax-ns#nil> . ")
                ]
              (do 
                  (recur (rest rlist) (conj (conj lines s1) s2))))
          :else
          (let [s1 (str (:lst (first rlist)) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#first> " (:bio (first rlist)) " .")
                s2 (str (:lst (first rlist)) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#rest> " (:lst (second rlist)) " .")
                ]
              (do 
                  (recur (rest rlist) (conj (conj lines s1) s2))))))

(defn write-lines-to-file [lines fname]
    (with-open [wtr (writer fname)]
        (doseq [line lines] 
            (.write wtr (println-str line)))))

(defn write-list-back [bio-sets bio-ids lines counter]
    (cond (empty? (keys bio-sets)) 
          (do ;; write remaining list lines to file
              (write-lines-to-file 
                  ;; take out the redundant lines already output by the rule
                  (remove #(or (str/index-of % "#first")
                               (str/index-of % "IAO_0000219") 
                               (str/index-of % "equivalentClass")) lines)
                  (str rule-directory (:name rule) 
                       "_list_elements_" (str counter) ".nt")))
          :else
          ;; connect the remaining nodes for the owl representation of lists
          (let [k (first (keys bio-sets))
                b (get bio-ids k)
                c (get bio-sets k)
                d (reverse (process-list-lines b nil))
                l1 (str k " <http://purl.obolibrary.org/obo/IAO_0000219> " (:union c) " .")
                l2 (str (:union c) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> .")
                l3 (str (:other c) " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://www.w3.org/2002/07/owl#Class> ." )
                l4 (str (:union c) " <http://www.w3.org/2002/07/owl#equivalentClass> " (:other c) " ." )
                l5 (str (:other c) " <http://www.w3.org/2002/07/owl#unionOf> " (:lst (first b)) " ." )
                l (list l1 l2 l3 l4 l5)
                ]
                  ;; files get too big for memory; write them out every 1000 Reactome id sets
                  (if (= 0 (mod counter 1000))
                  (do 
                      ;; need to write to file periodically or list of lines gets too big
                      (write-lines-to-file 
                          ;; remove redundant lines already output by the rule
                          (remove #(or (str/index-of % "#first")
                                   (str/index-of % "IAO_0000219") 
                                   (str/index-of % "equivalentClass")) lines)
                          (str rule-directory (:name rule) "_list_elements_" (str counter) ".nt"))
                            (recur (dissoc bio-sets k) (dissoc bio-ids k) nil (inc counter)))
                      ;; else just keep working through Reactome IDs
                      (do (recur (dissoc bio-sets k) (dissoc bio-ids k) (concat (concat lines l) d) (inc counter))
                  )))))

(let [q (grab-initial-results rule)
      ;; you can probably remove this if the missing / is not arising in your rules
      s (map #(str/replace % #"http:/ccp" "http://ccp") q)
      [sets ids] (map-ice-to-list s {} {} nil nil nil)
      v (write-list-back sets ids nil 1)
      ]
    )

