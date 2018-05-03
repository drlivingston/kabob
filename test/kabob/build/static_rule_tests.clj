(ns kabob.build.static-rule-tests
  (use clojure.test
       clojure.pprint

       kr.core.utils
       kr.core.rule
       kr.core.forward-rule
       kabob.core.rule

       kabob.core.namespace
       )
  (:require [kabob.core.rule :refer [kabob-load-rules-from-classpath kabob-load-rules-from-directory]]
            [kabob.core.namespace :refer [*namespaces*]])
  (:import (org.apache.commons.io FileUtils)
           (java.io File)))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

;;(defonce ^:dynamic *rcon* nil)

(def ^:dynamic *known-ns* (set (concat (map first *namespaces*)
                                       (map second *namespaces*))))

;;; --------------------------------------------------------
;;; functions
;;; --------------------------------------------------------


(defn find-all-rule-paths []
  "returns a list of directories that contain rule files"
  (distinct
    (map (fn [file] (.getParentFile file))
         (FileUtils/listFiles (File. "resources/rules/") (into-array String ["clj"]) true))))

(def ^:dynamic *rule-paths* (find-all-rule-paths))


(defn whitespace-padded-names [rules]
  (bad-rules (fn [rule]
               (let [n (:name rule)]
                 (= n (clojure.string/trim n))))
             rules
             "Failure due to leading and/or trailing whitespace in the rule :name field."))

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
             rules
             "Failure due to unknown namespace symbol."))

(defn rules-have-meta [rules]
  (bad-rules (fn [r] (not (nil? (meta r))))
             rules
             "Failure due to rule missing meta data."))

(defn rules-forward-safe [rules]
  (bad-rules forward-safe-with-reification?
             rules
             "Failure due to mismatch between variables in :head, :reify, and :body."))

(defn rule-heads-use-proper-namespaces [rules]
  (bad-rules head-property-ns-correct?
             rules
             "Failure due to observed property with unexpected namespace in rule head."))

(defn rules-with-missing-slashes [rules]
  (bad-rules variables-with-proper-slashes?
             rules
             "Failure due to observed variable with a missing slash after the '?'."))









;; (defn report-bad-rules [rules]
;;   (doseq [bad-rule rules]
;;     (pprint (meta bad-rule))
;;     (pprint bad-rule)))
  

(defn ask-rules [kb rules]
  ;; (doseq [rule-path *rule-paths*]
  ;;   (pprint rule-path)
  ;;   (let [rules (kabob-load-rules-from-classpath rule-path)]
  (bad-rules (partial ask-forward-rule kb)
             rules
             "Failure message"))


;;; --------------------------------------------------------
;;; manual / live tests
;;; --------------------------------------------------------

;; helpers to not use the classpath
(defn dir-files [dir]
  (directory-seq dir #"\.clj$"))
;;; --------------------------------------------------------




;(defn ask-dir [kb dir]
;  (doseq [rule-path (dir-files dir)]
;    (pprint rule-path)
;    (let [rules (kabob-load-rules-from-file rule-path)]
;      (ask-rules kb rules))))
;
;(defn quick-shakedown [dir]
;  (doseq [rule-path (dir-files dir)]
;    (pprint rule-path)
;    (let [rules (kabob-load-rules-from-file rule-path)]
;      (map (fn [f] (f rules)) (list whitespace-padded-names
;                                    rules-known-syms
;                                    rules-have-meta
;                                    rules-forward-safe)))))
;
;(defn slow-shakedown [kb dir]
;  (doseq [rule-path (dir-files dir)]
;    (pprint rule-path)
;    (let [rules (kabob-load-rules-from-file rule-path)]
;      (println "ask rules:")
;      (time (ask-rules kb rules)))))
;
;
;(defn shakedown [kb dir]
;  (quick-shakedown dir)
;  (slow-shakedown kb dir))






;;; --------------------------------------------------------
;;; automated tests
;;; --------------------------------------------------------



(deftest test-rule-structure
  "Tests rules for proper structure. If this test fails,
  then a rule likely does not have the proper structure, e.g. :head, :body, etc."
  (doseq [rule-path *rule-paths*]
    (let [rules (kabob-load-rules-from-directory rule-path)]
      (is (<= 0 (count rules)))
      (doseq [r rules]
        (prn (str (:name r) "\n"))))))


(deftest test-whitespace-padded-names
  "Test for leading/trailing whitespace in the rule names. It is important to not have and
  whitespace padding in the rule names because the rule names are used as part of the file
  names for storing rule output (and spaces in file names are inconvenient at best."
  (is (= 0
         (count
          (whitespace-padded-names
           (mapcat kabob-load-rules-from-classpath
                   (map (fn [file] (.getAbsolutePath file))
                        *rule-paths*)))))))


(deftest test-rules-known-syms
  "Tests that the namespaces used in the rules are known, i.e. are enumerated in the namespaces.clj file."
  (is (= 0
         (count
          (rules-known-syms
           (mapcat kabob-load-rules-from-classpath
                   (map (fn [file] (.getAbsolutePath file))
                        *rule-paths*)))))))


(deftest test-rules-have-meta
  "Tests that each rule has metadata."
  (is (= 0
         (count
          (rules-have-meta
           (mapcat kabob-load-rules-from-classpath
                   (map (fn [file] (.getAbsolutePath file))
                        *rule-paths*)))))))

(deftest test-duplicate-names
  "Tests that each rule has a unique name."
  (is (empty?
        (duplicates
          (map :name (mapcat kabob-load-rules-from-classpath
                             (map (fn [file] (.getAbsolutePath file))
                                  *rule-paths*)))))))


(deftest test-rules-forward-safe
  "Tests that each rule is forward-safe, e.g. the variables in the :head
  are accounted for by variables in the :body and :reify clauses."
  (is (= 0
         (count
          (rules-forward-safe
           (mapcat kabob-load-rules-from-classpath
                   (map (fn [file] (.getAbsolutePath file))
                        *rule-paths*)))))))


(deftest test-rule-heads-for-expected-property-namespace
  "Tests the namespaces of the properties in the rule heads. Ensures correct
  namespaces are used for commonly used properties, e.g. rdfs/subClassOf"
  (is (= 0
         (count
           (rule-heads-use-proper-namespaces
             (mapcat kabob-load-rules-from-classpath
                     (map (fn [file] (.getAbsolutePath file))
                          *rule-paths*)))))))


(deftest test-rules-for-missing-slashes-in-variables
  "Tests the head, reify, and body (if relevant) for variables missing
  the forward slash between the '?' and the variable name."
  (is (= 0
         (count
           (rules-with-missing-slashes
             (mapcat kabob-load-rules-from-classpath
                     (map (fn [file] (.getAbsolutePath file))
                          *rule-paths*)))))))



;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
