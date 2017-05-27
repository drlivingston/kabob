(ns kabob.core.staged-query
  (use edu.ucdenver.ccp.utils
       edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.rule
       edu.ucdenver.ccp.kr.forward-rule

       edu.ucdenver.ccp.kr.rdf
       [edu.ucdenver.ccp.kr.sparql :exclude (query-visit)]

       kabob.core.parallel-utils

       clojure.set
       clojure.pprint
       [clojure.java.io :only (reader writer)]))

;;; --------------------------------------------------------
;;; globals
;;; --------------------------------------------------------

(def ^:dynamic *visual-logging* false)

;;; --------------------------------------------------------
;;; helpers
;;; --------------------------------------------------------

(defn var-like? [x]
  (or (variable? x) (anon? x)))


;;; --------------------------------------------------------
;;; helpers for file-based staging
;;; --------------------------------------------------------

(defn temp-output-file [temp-dir name part-num]
  (str temp-dir name "-query-part-" part-num ".clj"))

(defn initialize-starting-file [output-file data]
  (with-open [wrtr (writer output-file)]
    (binding [*out* wrtr]
      (prn "init: " data)
      (prn {}))))

(defn cleanup-file [file]
  (when *visual-logging*
    (println "cleaning up file: " file))
  (println "\"deleting\" file: " file))
  

;;; --------------------------------------------------------
;;; cleanup / saftey of stages
;;; --------------------------------------------------------

;;TODO what if there is an intentionally re-used bnode name that shouldn't
;;  actually be shared across stages??

;; TODO this is messy but it works, there has to be a reduced form possible
(defn problem-bnodes [sequenced-parts]
  (loop [problem-bnodes #{}
         remaining-sets (map set
                             (map (fn [part]
                                    (remove (complement anon?) (flatten part)))
                                  sequenced-parts))]
    (if (empty? remaining-sets)
      problem-bnodes
      (let [cur-set (first remaining-sets)
            rest-syms (set (flatten (mapcat seq (rest remaining-sets))))]
        (recur  (union problem-bnodes
                        (remove (complement rest-syms) cur-set))
                (rest remaining-sets))))))


;; if there is a bnode that somehow made it into two groups
;;   it needs to be turned into a variable so that it's bindings can be
;;   preserved across query groups.
(defn fix-split-bnodes [sequenced-parts]
  (let [bnodes (problem-bnodes sequenced-parts)]
    (if (empty? bnodes)
      sequenced-parts
      (let [changes (reduce conj
                            {}
                            (map (fn [bnode]
                                   [bnode (variable (name bnode))])
                                 bnodes))]
        (vec (subst-bindings sequenced-parts changes))))))


;;; --------------------------------------------------------
;;; sequencing - helpers to order stages
;;; --------------------------------------------------------

(defn in-and-out [syms other]
  (let [sym-set (set syms)]
    (reduce (fn [[in out] triple]
              (if (some sym-set triple)
                [(conj in triple) out]
                [in (conj out triple)]))
            [[][]]
            other)))

(defn linked? [prev-syms part]
  (some prev-syms part))

;; from the list picks the first linked group
(defn pick-linked [previous parts]
  (let [syms (set (remove (complement var-like?) (flatten previous)))
        [link other-parts] (reduce (fn [[l other] part]
                                     (if (and (not l)
                                              (linked? syms part))
                                       [part other]
                                       [l (conj other part)]))
                                   [nil []]
                                   parts)]
    (if link
      [link other-parts]
      [(first parts) (rest parts)])))
    
;;put the parts in order so that the likelihood of an explosion in bindings
;;  is minimized by linking variables from group to group
(defn sequence-query
  ([parts] (sequence-query parts [] []))
  ([parts other] (sequence-query parts other []))
  ([parts other ordered]
     (if (empty? parts)         ; done looping
       (if (empty? other)       ; test for what to return
         ordered                ; return
         (conj ordered other))  ; return
       (let [[[k triples] rest-parts] (pick-linked ordered parts)
             vars (remove (complement var-like?)
                          (flatten triples))
             [include left-other] (in-and-out vars other)]
         (recur rest-parts
                left-other
                (conj ordered (concat triples include)))))))

;;; --------------------------------------------------------
;;; auto-sequencing of groups - needs a grouping function
;;; --------------------------------------------------------

;;example grouping-fn: separate-fv-queries
(defn query-sequence [query-pat grouping-fn ordering-fn]
  (let [parts (grouping-fn query-pat)
        ordered-parts (ordering-fn (dissoc parts :other))
        other (:other parts)]
    
        ;; [constrained unconstrained] (constrained-and-not-fvs (dissoc parts
        ;;                                                              :other))
        ;; ordered-parts (order-parts constrained unconstrained)]
    (fix-split-bnodes
     (sequence-query ordered-parts other))))


(defn query-visit [kb binding-fn pat other]
  (let [visit-counter (atom 0)
        t (atom (.getTime (java.util.Date.)))]
    (edu.ucdenver.ccp.kr.sparql/query-visit
     kb
     (fn [bindings]
       (swap! visit-counter inc)
       (when (= 0 (mod @visit-counter 10000))
         (System/gc))
       (when (= 0 (mod @visit-counter 250000))
         (let [new-t (.getTime (java.util.Date.))]
           (println @visit-counter " in " (- new-t @t) "ms")
           (reset! t new-t)))
       (binding-fn bindings))
     pat
     other)))

;;; --------------------------------------------------------
;;; run single stage
;;; --------------------------------------------------------

(defn run-intermediate-stage-disk [source-kb
                                   select-vars
                                   query
                                   input-file
                                   output-file]
  (when *visual-logging*
    (println "intermediate step")
    (println "input: " input-file)
    (println "output: " output-file)
    (println "query: ") (pprint (doall query)))
  (with-open [rdr (reader input-file)]
    (with-open [wrtr (writer output-file)]
      (binding [*out* wrtr]
        (println (doall query)))
      (doseq [line (rest (line-seq rdr))]
        (let [bindings (read-string line)]
          (query-visit source-kb
                       (fn [result-bindings]
                         (binding [*out* wrtr]
                           (prn (merge bindings
                                       result-bindings))))
                       (subst-bindings query bindings)
                       {:select-vars select-vars}))))))

(defn run-final-stage-disk [source-kb
                            select-vars
                            query
                            visitor-fn
                            input-file
                            output-file]
  (when *visual-logging*
    (println "final step")
    (println "input: " input-file)
    (println "query: ") (pprint (doall query)))
  (with-open [wrtr (writer output-file)]  ;; write log file
    (binding [*out* wrtr]
      (prn (doall query))))
  (with-open [rdr (reader input-file)]
    (doseq [line (rest (line-seq rdr))]
      (let [bindings (read-string line)]
        (query-visit source-kb
                     visitor-fn
                     (subst-bindings query bindings)
                     {:select-vars select-vars})))))

;;; --------------------------------------------------------

(defn run-staged-query-mem [source-kb
                            select-vars
                            query-parts
                            visitor-fn
                            & [bindings]]
  (when *visual-logging*
    (println "whole query: ") (pprint (doall query-parts)))
  (let [[cur-part & rest-parts] query-parts]
    (when *visual-logging*
      (println "cur query: ") (pprint (doall cur-part))
      (println "bindings: ") (pprint (doall bindings)))
    (if (empty? rest-parts)
      (query-visit source-kb
                   (fn [new-bindings]
                     (visitor-fn (merge bindings new-bindings)))
                   (subst-bindings cur-part bindings)
                   {:select-vars select-vars})
      (query-visit source-kb
                   (fn [new-bindings]
                     (run-staged-query-mem source-kb
                                           select-vars
                                           rest-parts
                                           visitor-fn
                                           (merge bindings new-bindings)))
                   (subst-bindings cur-part bindings)
                   {:select-vars select-vars}))))

(defn run-staged-query-parallel [source-kb
                                 select-vars
                                 query-parts
                                 visitor-fn
                                 & [bindings]]
  (println "running with work queue")
  (println "query parts: ") (pprint query-parts)
  (when *visual-logging*
    (println "whole query: ") (pprint (doall query-parts)))
  (let [[cur-part & rest-parts] query-parts]
    (when *visual-logging*
      (println "cur query: ") (pprint (doall cur-part))
      (println "bindings: ") (pprint (doall bindings)))
    (with-work-queue work
      (if (empty? rest-parts)
        (query-visit source-kb
                     (fn [new-bindings]
                       (work
                        (fn []
                          (visitor-fn (merge bindings new-bindings)))))
                     (subst-bindings cur-part bindings)
                     {:select-vars select-vars})
        (query-visit source-kb
                     (fn [new-bindings]
                       (work
                        (fn []
                          (run-staged-query-mem source-kb
                                                select-vars
                                                rest-parts
                                                visitor-fn
                                                (merge bindings
                                                       new-bindings)))))
                     (subst-bindings cur-part bindings)
                     {:select-vars select-vars})))))

;;; --------------------------------------------------------
;;; query
;;; --------------------------------------------------------

;; temp dir is location of intermediate output files
;; cleanup - nil        - nothing deleted
;;           :immediate - removed temp files as soon as possible
;;           :success   - remove files at end of query
;;           :finally   - remove files no matter what at end
(defn staged-query-disk [source-kb query-stages visitor-fn
                         temp-dir
                         &{ :keys [cleanup-strategy file-part-name]}]
  (when *visual-logging* (pprint query-stages))
  (let [part-name (or file-part-name (uuid))
        select-vars (seq (set (variables query-stages)))
        start-file (temp-output-file temp-dir part-name 0)]
    (initialize-starting-file start-file query-stages)
    (loop [[cur-part & rest-parts] query-stages
           cur-count 1
           temp-files [start-file]]
      (let [old-file (temp-output-file temp-dir part-name (dec cur-count))
            new-file (temp-output-file temp-dir part-name cur-count)]
        (and
         (try
           (when *visual-logging*
             (println "cur part:") (pprint (doall cur-part)))
           (if (empty? rest-parts)
             (do
               (run-final-stage-disk source-kb
                                     select-vars
                                     cur-part
                                     visitor-fn
                                     old-file
                                     new-file)
               (if (not (nil? cleanup-strategy))
                 (dorun (map cleanup-file (conj temp-files new-file))))
               nil) ;stop the recursion
             (do
               (run-intermediate-stage-disk source-kb
                                            select-vars
                                            cur-part
                                            old-file
                                            new-file)
               (when (= :immediate cleanup-strategy)
                 (cleanup-file old-file))
               true)) ;continue the recursion
           (catch Exception e (when (= :finally cleanup-strategy)
                                (dorun (map cleanup-file
                                            (conj temp-files new-file))))
                  (throw e))) ;re-throw after cleanup
         (recur rest-parts (inc cur-count) (conj temp-files new-file))))))
  nil)


;;performs query in stanges using visitors and sub querys the whole way
;;  effectively depth first traversal through each stage
;;does so in-memory - no intermediate files kept
(defn staged-query [source-kb query-stages visitor-fn]
  (when *visual-logging* (pprint query-stages))
  (let [select-vars (seq (set (variables query-stages)))]
    (run-staged-query-mem source-kb
                          select-vars
                          query-stages
                          visitor-fn
                          {})))

(defn staged-query-parallel [source-kb query-stages visitor-fn]
  (when *visual-logging* (pprint query-stages))
  (let [select-vars (seq (set (variables query-stages)))]
    (run-staged-query-parallel source-kb
                               select-vars
                               query-stages
                               visitor-fn
                               {})))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
