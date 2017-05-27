(ns kabob.build.test-id-rules
  (use clojure.test
       clojure.pprint

       edu.ucdenver.ccp.utils
       edu.ucdenver.ccp.kr.rule
       edu.ucdenver.ccp.kr.forward-rule
       kabob.core.rule
       kabob.build.live-test-utils

       kabob.core.namespace
       )
  (require ;;kabob.core.kabob
           clojure.string))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

;;(defonce ^:dynamic *rcon* nil)

(def id-typing-rules-root "edu/ucdenver/ccp/kabob/build/id_typing/")
(def id-mapping-rules-root "edu/ucdenver/ccp/kabob/build/id_mapping/")
(def entity-typing-rules-root "edu/ucdenver/ccp/kabob/build/entity_typing/")


(def ^:dynamic *rule-paths* (list id-typing-rules-root 
                                  id-mapping-rules-root 
                                  ;;entity-typing-rules-root
                                  ))

(def ^:dynamic *known-ns* (set (concat (map first *namespaces*)
                                       (map second *namespaces*))))

;;; --------------------------------------------------------
;;; functions
;;; --------------------------------------------------------

(defn whitespace-padded-names [rules]
  (bad-rules (fn [rule]
               (let [n (:name rule)]
                 (= n (clojure.string/trim n))))
             rules))

(defn duplicates [coll]
  (second
   (reduce (fn [[seen dup] new]
             (if (seen new)
               [seen (conj dup new)]
               [(conj seen new) dup]))
           [#{} #{}]
           coll)))

(defn rules-known-syms [rules]
  (bad-rules (partial all-ns-known? *known-ns*)
             rules))

(defn rules-have-meta [rules]
  (bad-rules (fn [r] (not (nil? (meta r)))) rules))

(defn rules-forward-safe [rules]
  (bad-rules forward-safe-with-reification? rules))




;; (defn report-bad-rules [rules]
;;   (doseq [bad-rule rules]
;;     (pprint (meta bad-rule))
;;     (pprint bad-rule)))
  

(defn ask-rules [kb rules]
  ;; (doseq [rule-path *rule-paths*]
  ;;   (pprint rule-path)
  ;;   (let [rules (kabob-load-rules-from-classpath rule-path)]
  (bad-rules (partial ask-forward-rule kb) rules))


;;; --------------------------------------------------------
;;; manual / live tests
;;; --------------------------------------------------------

;; helpers to not use the classpath
(defn dir-files [dir]
  (directory-seq dir #"\.clj$"))
;;; --------------------------------------------------------




(defn ask-dir [kb dir]
  (doseq [rule-path (dir-files dir)]
    (pprint rule-path)
    (let [rules (kabob-load-rules-from-file rule-path)]
      (ask-rules kb rules))))
  
(defn live-tests [kb]
  (doseq [rule-path *rule-paths*]
    (rule-count-report
     (sort-rule-counts
      (count-forward-rules kb (kabob-load-rules-from-classpath rule-path))))))

(defn quick-shakedown [dir]
  (doseq [rule-path (dir-files dir)]
    (pprint rule-path)
    (let [rules (kabob-load-rules-from-file rule-path)]
      (map (fn [f] (f rules)) (list whitespace-padded-names
                                    rules-known-syms
                                    rules-have-meta
                                    rules-forward-safe)))))

(defn slow-shakedown [kb dir]
  (doseq [rule-path (dir-files dir)]
    (pprint rule-path)
    (let [rules (kabob-load-rules-from-file rule-path)]
      (println "ask rules:")
      (time (ask-rules kb rules)))))


(defn shakedown [kb dir]
  (quick-shakedown dir)
  (slow-shakedown kb dir))
  
;;; --------------------------------------------------------
;;; automated tests
;;; --------------------------------------------------------

(deftest test-whitespace-padded-names
  (is (= 0
         (count
          (whitespace-padded-names
           (mapcat kabob-load-rules-from-classpath *rule-paths*))))))
  ;; (let [names (map :name
  ;;                  (mapcat kabob-load-rules-from-classpath *rule-paths*))]
  ;;   (doseq [n names]
  ;;     (is (= n (clojure.string/trim n))))))



(deftest test-id-rules-known-syms
  (is (= 0
         (count
          (rules-known-syms
           (mapcat kabob-load-rules-from-classpath *rule-paths*))))))
  ;; (doseq [rule-path *rule-paths*]
  ;;   ;;(prn rule-path)
  ;;     (let [rules (kabob-load-rules-from-classpath rule-path)]
  ;;       (is (= 0 (count (bad-rules (partial all-ns-known? known-ns)
  ;;                                  rules)))))))


(deftest test-id-rules-have-meta
  (is (= 0
         (count
          (rules-have-meta
           (mapcat kabob-load-rules-from-classpath *rule-paths*))))))

(deftest test-id-rules-forward-safe
  (is (= 0
         (count
          (rules-forward-safe
           (mapcat kabob-load-rules-from-classpath *rule-paths*))))))

(deftest test-duplicate-names
  (is (empty?
       (duplicates
        (map :name (mapcat kabob-load-rules-from-classpath *rule-paths*))))))

;; (defn report-bad-rules [rules]
;;   (doseq [bad-rule rules]
;;     (pprint (meta bad-rule))
;;     (pprint bad-rule)))
  

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
