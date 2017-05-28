(ns kabob.core.staged-rule
  (use kr.core.variable
       kr.core.kb
       kr.core.rdf
       kr.core.unify
       kr.core.rule
       kr.core.forward-rule
       kabob.core.staged-query
       kabob.core.parallel-utils
       clojure.pprint))

;;; --------------------------------------------------------
;;; modified rule running
;;; --------------------------------------------------------

(defn are-fv-triples? [triples]
  (and (= 2 (count triples))
       (every? (set '(kiao/hasTemplate obo/IAO_0000219))
               (map second triples))))

(defn objects-constrained? [triples]
  (every? (complement variable?) (map (fn [[s p o]] o) triples)))

;; breaks apart triples into groups of triples that look like they
;;  are retrieving field values
(defn separate-fv-queries [query-pat]
  (let [first-pass (reduce (fn [tmap [s p o :as triple]]
                             (let [key (if (or (= p 'kiao/hasTemplate)
                                               (= p 'obo/IAO_0000219))
                                         s
                                         :other)]
                               (assoc tmap key (conj (get tmap key [])
                                                     triple))))
                           {}
                           query-pat)]
    (reduce (fn [results [k triples :as group]]
              (if (are-fv-triples? triples)
                (assoc results k triples)
                (assoc results :other (concat (:other results) triples))))
            {:other (:other first-pass)}
            (dissoc first-pass :other))))
        
;; separates groups so that the first list is maximally constrained
(defn constrained-and-not-fvs [map-of-fvs]
  (reduce (fn [[constrained unconst] [k triples :as group]]
            (if (objects-constrained? triples)
              [(conj constrained group) unconst]
              [constrained (conj unconst group)]))
          [[][]]
          map-of-fvs))

(defn order-parts [constrained not-constrained]
  (concat constrained not-constrained))

(defn ordering-function [groups]
  (apply order-parts (constrained-and-not-fvs groups)))

;;; --------------------------------------------------------
;;; modified rule running
;;; --------------------------------------------------------

(defn kabob-run-forward-ice-rule-parts-disk [source-kb target-kb rule temp-dir]
  (let [{head :head
         body :body
         reify :reify
         :as rule}    (add-reify-fns rule)
         query-parts (query-sequence body
                                     separate-fv-queries
                                     ordering-function)
         select-vars (seq (set (concat (variables head)
                                       (variables reify)
                                       (variables query-parts))))]
    (pprint query-parts)
    (staged-query-disk source-kb
                       query-parts
                       (fn [bindings]
                         (dorun
                          (map (partial add! target-kb)
                               (doall
                                (subst-bindings head
                                                bindings
                                                (reify-bindings reify
                                                                bindings))))))
                       temp-dir
                       {:file-part-name (:name rule)})))

(defn kabob-run-forward-ice-rule-parts [source-kb target-kb rule]
  (let [{head :head
         body :body
         reify :reify
         :as rule}    (add-reify-fns rule)
         query-parts (query-sequence body
                                     separate-fv-queries
                                     ordering-function)
         select-vars (seq (set (concat (variables head)
                                       (variables reify)
                                       (variables query-parts))))
         write-agent (agent target-kb)]
    (pprint query-parts)
    (staged-query-parallel
     source-kb
     query-parts
     (fn [bindings]
       (blocking-write-triples write-agent
                               (doall
                                (subst-bindings head
                                                bindings
                                                (reify-bindings reify
                                                                bindings))))))
    (await write-agent)))

;; (defn kabob-run-forward-ice-rule-parts [source-kb target-kb rule]
;;   (let [{head :head
;;          body :body
;;          reify :reify
;;          :as rule}    (add-reify-fns rule)
;;          query-parts (query-sequence body
;;                                      separate-fv-queries
;;                                      ordering-function)
;;          select-vars (seq (set (concat (variables head)
;;                                        (variables reify)
;;                                        (variables query-parts))))]
;;     (pprint query-parts)
;;     (staged-query source-kb
;;                   query-parts
;;                   (fn [bindings]
;;                     (dorun
;;                      (map (partial add! target-kb)
;;                           (doall
;;                            (subst-bindings head
;;                                            bindings
;;                                            (reify-bindings reify
;;                                                            bindings)))))))))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
