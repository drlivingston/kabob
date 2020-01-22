(ns kabob.core.test-static-rule-tests
  (use clojure.test
       clojure.pprint

       kabob.core.util.hashing
       kabob.core.rule
       kr.core.forward-rule

       kabob.core.namespace
       kr.sesame.kb

       kr.core.kb
       kr.core.rdf
       kr.core.sparql
       kr.core.utils
       )
  (:require [kabob.build.static-rule-tests :refer [rules-forward-safe rule-heads-use-proper-namespaces
                                                   rules-known-syms
                                                   rule-heads-use-proper-namespaces
                                                   rules-with-missing-slashes]]))


(def fs-rule '{:name "rule1"
               :head  ((?/hacker ex/inDept ?/dept)
                        (?/dept ex/deptID ?/deptid)
                        (?/dept rdf/type ex/Department))
               :body  ((?/hacker ex/hasBoss ?/boss)
                        (?/hacker ex/atCompany ?/co))
               :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                :ns "ex" :prefix "DEPT_"}]
                        [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                   :ns "ex" :prefix "DEPT_"}])
               })

;; missing deptid in reify clause
(def fs-rule-unsafe-1 '{:name "rule1"
                        :head  ((?/hacker ex/inDept ?/dept)
                                 (?/dept ex/deptID ?/deptid)
                                 (?/dept rdf/type ex/Department))
                        :body  ((?/hacker ex/hasBoss ?/boss)
                                 (?/hacker ex/atCompany ?/co))
                        :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                         :ns "ex" :prefix "DEPT_"}])
                        })

;; missing ?/boss variable in body
(def fs-rule-unsafe-2 '{:name "rule1"
                        :head  ((?/hacker ex/inDept ?/dept)
                                 (?/dept ex/deptID ?/deptid)
                                 (?/dept rdf/type ex/Department))
                        :body  ((?/hacker ex/atCompany ?/co))
                        :reify ([?/deptid {:ln (:sha-1 ?/dept ?/co)
                                           :ns "ex" :prefix "DEPT_"}]
                                 [?/dept {:ln (:sha-1 ?/boss ?/co)
                                          :ns "ex" :prefix "DEPT_"}])
                        })

;; unused variable in reify block
(def fs-rule-unsafe-3 '{:name "rule1"
               :head  ((?/hacker ex/inDept ?/dept)
                        (?/dept ex/deptID ?/deptid)
                        (?/dept rdf/type ex/Department))
               :body  ((?/hacker ex/hasBoss ?/boss)
                        (?/hacker ex/atCompany ?/co))
               :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                :ns "ex" :prefix "DEPT_"}]
                        [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                   :ns "ex" :prefix "DEPT_"}]
                        [?/unused {:ln (:sha-1 ?/dept ?/co)
                                   :ns "ex" :prefix "UNUSED_"}])
               })


(def fs-rule-sparql '{:name "rule1"
                      :head  ((?/hacker ex/inDept ?/dept)
                               (?/dept ex/deptID ?/deptid)
                               (?/dept rdf/type ex/Department))
                      :body  "prefix ex: <http://www.example.org/>
                                       select ?hacker ?boss ?co
                                       where {
                                          ?hacker ex:hasBoss ?boss .
                                          ?hacker ex:atCompany ?co .
                                       }"
                      :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                       :ns "ex" :prefix "DEPT_"}]
                               [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                          :ns "ex" :prefix "DEPT_"}])
                      })

;; missing deptid in reify clause
(def fs-rule-sparql-unsafe-1 '{:name "rule1"
                               :head  ((?/hacker ex/inDept ?/dept)
                                        (?/dept ex/deptID ?/deptid)
                                        (?/dept rdf/type ex/Department))
                               :body  "prefix ex: <http://www.example.org/>
                                       select ?hacker ?boss ?co
                                       where {
                                          ?hacker ex:hasBoss ?boss .
                                          ?hacker ex:atCompany ?co .
                                       }"
                               :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                                :ns "ex" :prefix "DEPT_"}])
                               })

;; missing ?/boss variable in body
(def fs-rule-sparql-unsafe-2 '{:name "rule1"
                               :head  ((?/hacker ex/inDept ?/dept)
                                        (?/dept ex/deptID ?/deptid)
                                        (?/dept rdf/type ex/Department))
                               :body  "prefix ex: <http://www.example.org/>
                                       select ?hacker ?co
                                       where {
                                          ?hacker ex:hasBoss ?boss .
                                          ?hacker ex:atCompany ?co .
                                       }"
                               :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                                :ns "ex" :prefix "DEPT_"}]
                                        [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                                   :ns "ex" :prefix "DEPT_"}])
                               })





(deftest test-forward-safe-check-on-safe-rule
  (is (= 0 (count (rules-forward-safe (list fs-rule))))))

(deftest test-forward-safe-check-on-unsafe-rule-1
  (is (= 1 (count (rules-forward-safe (list fs-rule-unsafe-1))))))

(deftest test-forward-safe-check-on-unsafe-rule-2
  (is (= 1 (count (rules-forward-safe (list fs-rule-unsafe-2))))))

(deftest test-forward-safe-check-on-unsafe-rule-3
  (is (= 1 (count (rules-forward-safe (list fs-rule-unsafe-3))))))

(deftest test-forward-safe-check-on-safe-rule-sparql
  (is (= 0 (count (rules-forward-safe (list fs-rule-sparql))))))

(deftest test-forward-safe-check-on-unsafe-rule-sparql-1
  (is (= 1 (count (rules-forward-safe (list fs-rule-sparql-unsafe-1))))))

(deftest test-forward-safe-check-on-unsafe-rule-sparql-2
  (is (= 1 (count (rules-forward-safe (list fs-rule-sparql-unsafe-2))))))


;; rdfs/type & rdfs/Class
(def fs-rule-bad-ns '{:name "rule1"
               :head  ((?/hacker ex/inDept rdfs/Class)
                        (?/dept ex/deptID ?/deptid)
                        (?/dept rdfs/type ex/Department))
               :body  ((?/hacker ex/hasBoss ?/boss)
                        (?/hacker ex/atCompany ?/co))
               :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                :ns "ex" :prefix "DEPT_"}]
                        [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                   :ns "ex" :prefix "DEPT_"}])
               })

(deftest test-expected-namespaces-in-head-with-bad-ns
  (is (= 1 (count (rule-heads-use-proper-namespaces (list fs-rule-bad-ns))))))


(def rule-missing-slash '{:name "rule-missing-slash"
                          :head  ((?hacker ex/inDept ?/dept)
                                   (?/dept ex/deptID ?/deptid)
                                   (?/dept rdf/type ex/Department))
                          :body  ((?/hacker ex/hasBoss ?/boss)
                                   (?/hacker ex/atCompany ?/co))
                          :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                           :ns "ex" :prefix "DEPT_"}]
                                   [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                              :ns "ex" :prefix "DEPT_"}])
                          })


(def rule-missing-slash-in-body '{:name "rule-missing-slash-on-body"
                          :head  ((?/hacker ex/inDept ?/dept)
                                   (?/dept ex/deptID ?/deptid)
                                   (?/dept rdf/type ex/Department))
                          :body  ((?/hacker ex/hasBoss ?/boss)
                                   (?/hacker ex/atCompany ?co))
                          :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                           :ns "ex" :prefix "DEPT_"}]
                                   [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                              :ns "ex" :prefix "DEPT_"}])
                          })

(def rule-missing-slash-in-head-sparql '{:name "rule1"
                      :head  ((?hacker ex/inDept ?/dept)
                               (?/dept ex/deptID ?/deptid)
                               (?/dept rdf/type ex/Department))
                      :body  "prefix ex: <http://www.example.org/>
                                       select ?hacker ?boss ?co
                                       where {
                                          ?hacker ex:hasBoss ?boss .
                                          ?hacker ex:atCompany ?co .
                                       }"
                      :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                                       :ns "ex" :prefix "DEPT_"}]
                               [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                          :ns "ex" :prefix "DEPT_"}])
                      })

(def rule-missing-slash-in-reify-sparql '{:name "rule1"
                                         :head  ((?/hacker ex/inDept ?/dept)
                                                  (?/dept ex/deptID ?/deptid)
                                                  (?/dept rdf/type ex/Department))
                                         :body  "prefix ex: <http://www.example.org/>
                                       select ?hacker ?boss ?co
                                       where {
                                          ?hacker ex:hasBoss ?boss .
                                          ?hacker ex:atCompany ?co .
                                       }"
                                         :reify ([?/dept {:ln (:sha-1 ?/boss ?co)
                                                          :ns "ex" :prefix "DEPT_"}]
                                                  [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                                             :ns "ex" :prefix "DEPT_"}])
                                         })

(deftest test-rule-no-missing-slash
  (is (= 0 (count (rules-with-missing-slashes (list fs-rule))))))

(deftest test-rule-missing-slash-in-head
  (is (= 1 (count (rules-with-missing-slashes (list rule-missing-slash))))))

(deftest test-rule-missing-slash-in-body
  (is (= 1 (count (rules-with-missing-slashes (list rule-missing-slash-in-body))))))

(deftest test-rule-missing-slash-in-head-sparql
  (is (= 1 (count (rules-with-missing-slashes (list rule-missing-slash-in-head-sparql))))))

(deftest test-rule-missing-slash-in-reify-sparql
  (is (= 1 (count (rules-with-missing-slashes (list rule-missing-slash-in-reify-sparql))))))