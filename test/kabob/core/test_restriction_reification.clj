(ns kabob.core.test-restriction-reification
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
  (:require  [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                             test-kb]]
             [kabob.build.output-kb :refer [output-kb]]
             ))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

(def old-triples '((ex/rec1 ex/drug ex/drug1)
                   (ex/rec1 ex/bio ex/bio1)))
                   ;(ex/rec2 ex/drug ex/drug1)
                   ;(ex/rec2 ex/bio ex/bio2)))

(def new-triples '())

(defn old-kb []
  (test-kb old-triples))

;;; --------------------------------------------------------
;;; tests
;;; --------------------------------------------------------



(def test-rule-reify-proper-order
  '{:name "test-rule-2a"
    :head (;(?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
            ;(?/r1 rdf/type owl/Restriction)
            ;(?/r1 owl/onProperty obo/RO_0000057) ;; RO_0000057 = has_participant
            ;(?/r1 owl/someValuesFrom ?/drug)
            ;(?/r2 rdf/type owl/Restriction)
            ;(?/r2 owl/onProperty obo/RO_0000057) ; has_participant
            ;(?/r2 owl/someValuesFrom ?/bio_sc)
            (?/bio_sc rdfs/subClassOf ?/bio)
            (?/bio_sc ex/other ?/other)
            ;(?/interaction rdfs/subClassOf ?/r1)
            ;(?/interaction rdfs/subClassOf ?/r2))
            )
    :body
          ((?/rec ex/drug ?/drug)
            (?/rec ex/bio ?/bio))
    :reify ([?/other {:ln (:sha-1 ?/bio "other" )
                      :ns "ex" :prefix "B_"}]
             ;[?/r1 {:ln (:restriction)
             ;       :ns "ex" :prefix "R_"}]
             ;[?/r2 {:ln (:restriction)
             ;       :ns "ex" :prefix "R_"}]
             ;[?/interaction {:ln (:sha-1 "interaction" ?/bio ?/drug ?/r1 ?/r2)
             ;                :ns "ex" :prefix "I_"}]
             [?/bio_sc {:ln (:sha-1 ?/bio ?/other)
                        :ns "ex" :prefix "B_"}])
    })


(def test-rule-reify-improper-order
  '{:name "test-rule-2b"
    :head (;(?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
            ;(?/r1 rdf/type owl/Restriction)
            ;(?/r1 owl/onProperty obo/RO_0000057) ;; RO_0000057 = has_participant
            ;(?/r1 owl/someValuesFrom ?/drug)
            ;(?/r2 rdf/type owl/Restriction)
            ;(?/r2 owl/onProperty obo/RO_0000057) ; has_participant
            ;(?/r2 owl/someValuesFrom ?/bio_sc)
            (?/bio_sc rdfs/subClassOf ?/bio)
            (?/bio_sc ex/other ?/other)
            ;(?/interaction rdfs/subClassOf ?/r1)
            ;(?/interaction rdfs/subClassOf ?/r2))
            )
    :body
          ((?/rec ex/drug ?/drug)
            (?/rec ex/bio ?/bio))
    :reify ([?/bio_sc {:ln (:sha-1 ?/bio ?/other)
                       :ns "ex" :prefix "B_"}]
             [?/other {:ln (:sha-1 ?/bio "other")
                       :ns "ex" :prefix "B_"}]
             ;[?/interaction {:ln (:sha-1 "interaction" ?/bio ?/drug ?/r1 ?/r2)
             ;               :ns "ex" :prefix "I_"}]
             ;[?/r1 {:ln (:restriction)
             ;       :ns "ex" :prefix "R_"}]
             ;[?/r2 {:ln (:restriction)
             ;       :ns "ex" :prefix "R_"}]
             ; ))
             )
    })

;; Confirms that order of reify clauses DOES have an effect on the generated RDF.
;; If a reified variable uses another reified variable within it's hash function, and
;; that variable is assigned below, then it will be as if the hash uses nil.
(deftest test-restriction-reification-order-independent
  (let [target-kb-1 (test-kb '())
        target-kb-2 (test-kb '())
        source-kb (old-kb)]

    (run-forward-rule source-kb target-kb-1 test-rule-reify-proper-order)
    (run-forward-rule source-kb target-kb-2 test-rule-reify-improper-order)

    ;(is (= 2 (count (query target-kb-1 '((?/r rdf/type owl/Restriction))))))
    ;(is (= 2 (count (query target-kb-2 '((?/r rdf/type owl/Restriction))))))

    (is (= (query target-kb-1 '((?/s ex/other ?/o))) (query target-kb-2 '((?/s ex/other ?/o)))))


    ;(let [log-kb (output-kb "/tmp/triples1.nt")]
    ;  (run-forward-rule source-kb log-kb test-rule-reify-proper-order)
    ;  (close log-kb))
    ;
    ;(let [log-kb (output-kb "/tmp/triples2.nt")]
    ;  (run-forward-rule source-kb log-kb test-rule-reify-improper-order)
    ;  (close log-kb))


    ;;(pprint result-syms-2)
    ;(pprint dynamically-generated-syms-2)
    ;
    ;(doseq [sym result-syms-2]
    ;  (is (dynamically-generated-syms-2 sym)))
    ;(doseq [sym dynamically-generated-syms]
    ;  (is (result-syms-2 sym)))
    ;
    ;;; (let [interaction-syms
    ;;;       (query-template output-kb '?/interaction
    ;;;                       '((?/interaction rdfs/subClassOf obo/MI_0000)))]
    ;;;   (pprint "interactions")
    ;;;   (pprint interaction-syms))
    ;
    ;
    ;(let [kb-syms (query-template output-kb '?/r
    ;                              '((?/r rdf/type owl/Restriction)))]
    ;  ;;(pprint kb-syms)
    ;  (doseq [sym kb-syms]
    ;    (is (dynamically-generated-syms-2 sym)))
    ;  (doseq [sym kb-syms]
    ;    (is (result-syms-2 sym))))
    ))


(deftest test-reification-clause-ordering-2
  (let [kb (test-kb old-triples)]
    (println (str "TESTING REIFICATION: initial triple count: " (count (query kb '((?/s ?/p ?/o))))))
    (run-forward-rule kb kb test-rule-reify-proper-order)
    (let [triples (query kb '((?/s ?/p ?/o)))]
      (println (str "================= RULE 8 (" (count triples) ") ===================="))
      (pprint triples)
      (println "=============================================")
      (run-forward-rule kb kb test-rule-reify-improper-order)
      (let [new-triples (query kb '((?/s ?/p ?/o)))]
        (println (str "+++++++++++++++++++ RULE 8 INV (" (count new-triples) ") +++++++++++++++++++++++++"))
        (pprint new-triples)
        (println "++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
        (is (= (count triples) (count new-triples)))))))




(def rule-8 '{:head ((?/hacker ex/inDept    ?/dept)
                      (?/dept ex/deptID    ?/deptid)
                      (?/dept   rdf/type     ex/Department))
              :body ((?/hacker ex/hasBoss   ?/boss)
                      (?/hacker ex/atCompany ?/co))
              :reify ([?/dept {:ln (:sha-1 ?/boss ?/co)
                               :ns "ex" :prefix "DEPT_"}]
                       [?/deptid {:ln (:sha-1 ?/dept ?/co)
                                  :ns "ex" :prefix "DEPT_"}])
              })

(def rule-8-inv '{:head ((?/hacker ex/inDept    ?/dept)
                          (?/dept ex/deptID    ?/deptid)
                          (?/dept   rdf/type     ex/Department))
                  :body ((?/hacker ex/hasBoss   ?/boss)
                          (?/hacker ex/atCompany ?/co))
                  :reify ([?/deptid {:ln (:sha-1 ?/dept ?/co)
                                     :ns "ex" :prefix "DEPT_"}]
                           [?/dept {:ln (:sha-1 ?/boss ?/co)
                                    :ns "ex" :prefix "DEPT_"}])
                  })

(def test-triples-md5
  '((ex/a    foaf/firstname   "Alice" )
     (ex/a ex/hasBoss ex/boss1)
     (ex/a ex/atCompany ex/co1)

     (ex/b ex/hasBoss ex/boss1)
     (ex/b ex/atCompany ex/co1)

     (ex/c ex/hasBoss ex/boss2)
     (ex/c ex/atCompany ex/co2)))

(deftest test-reification-clause-ordering
  (let [kb (test-kb test-triples-md5)]
         (println (str "TESTING REIFICATION: initial triple count: " (count (query kb '((?/s ?/p ?/o))))))
         (run-forward-rule kb kb rule-8)
         (let [triples (query kb '((?/s ?/p ?/o)))]
           (println (str "================= RULE 8 (" (count triples) ") ===================="))
           (pprint triples)
           (println "=============================================")
           (run-forward-rule kb kb rule-8-inv)
           (let [new-triples (query kb '((?/s ?/p ?/o)))]
             (println (str "+++++++++++++++++++ RULE 8 INV (" (count new-triples) ") +++++++++++++++++++++++++"))
             (pprint new-triples)
             (println "++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
             (is (= (count triples) (count new-triples)))))))




;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
