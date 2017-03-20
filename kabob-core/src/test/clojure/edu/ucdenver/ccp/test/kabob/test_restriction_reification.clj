(ns edu.ucdenver.ccp.test.kabob.test-restriction-reification
  (use clojure.test

       edu.ucdenver.ccp.kabob.util.hashing
       edu.ucdenver.ccp.kabob.rule
       edu.ucdenver.ccp.kr.forward-rule
       
       edu.ucdenver.ccp.kabob.namespace
       edu.ucdenver.ccp.kr.sesame.kb

       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       
       edu.ucdenver.ccp.utils

       ;;clojure.pprint

       ;;edu.ucdenver.ccp.kr.rule
       ;;edu.ucdenver.ccp.kr.forward-rule
       ;;edu.ucdenver.ccp.kabob.rule
       ;;edu.ucdenver.ccp.test.kabob.build.live-test-utils

       ;;edu.ucdenver.ccp.kabob.build.ice-helper
       )
  (require kabob
           clojure.string))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

(def old-triples '((ex/rec1 ex/drug ex/drug1)
                   (ex/rec1 ex/bio ex/bio1)
                   (ex/rec2 ex/drug ex/drug1)
                   (ex/rec2 ex/bio ex/bio2)))

(def new-triples '())

(def result-triples '())

(def test-rule
'{:name "test-rule"
  :head ((?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ;; RO_0000057 = has_participant
         (?/r1 owl/someValuesFrom ?/drug)
         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r2 owl/someValuesFrom ?/bio)
         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/r2))
  :body
  ((?/rec ex/drug ?/drug)
   (?/rec ex/bio ?/bio))
  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/bio ?/drug)
                          :ns "ex" :prefix "I_"}]
            [?/r1 {:ln (:restriction)
                   :ns "ex" :prefix "R_"}]
            [?/r2 {:ln (:restriction)
                   :ns "ex" :prefix "R_"}])
  })


;; HARD-CODED SHA1 KEYS!!!!

(def result-syms #{'ex/R_XTThXzY88gvwfoc57ivMafWgG3Q
                   'ex/R_TIQCT4s9j0qMaZ-8JMYhRvw8960
                   'ex/R_N3vQlyC9YqEeGjprdvFcU3TvHB4
                   })


(def dynamically-generated-syms
  (set
   (map (fn [sym-name]
          (symbol "ex" sym-name))
        (map (fn [sym-list]
               (str "R_"
                    (sha-1 (apply str (sort (map str sym-list))))))
             '((owl/Restriction
                owl/onProperty obo/RO_0000057 ; has_participant
                owl/someValuesFrom ex/drug1)
               (owl/Restriction
                owl/onProperty obo/RO_0000057 ; has_participant
                owl/someValuesFrom ex/bio1)
               (owl/Restriction
                owl/onProperty obo/RO_0000057 ; has_participant
                owl/someValuesFrom ex/bio2))))))

;;; --------------------------------------------------------
;;; kbs
;;; --------------------------------------------------------

(defn test-kb [triples]
  (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
                                *namespaces*)]
    (dorun (map (partial add! kb) triples))
    kb))

(defn old-kb []
  (test-kb old-triples))

;;; --------------------------------------------------------
;;; tests
;;; --------------------------------------------------------

(deftest test-restriction-reification-signature
  (let [output-kb (test-kb '())
        source-kb (old-kb)]
    (run-forward-rule source-kb output-kb test-rule)
    
    (is (= 3 (count (query output-kb '((?/r rdf/type owl/Restriction))))))

    ;;(pprint result-syms)
    ;;(pprint dynamically-generated-syms)

    (doseq [sym result-syms]
      (is (dynamically-generated-syms sym)))
    (doseq [sym dynamically-generated-syms]
      (is (result-syms sym)))

    ;; (let [interaction-syms
    ;;       (query-template output-kb '?/interaction
    ;;                       '((?/interaction rdfs/subClassOf obo/MI_0000)))]
    ;;   (pprint "interactions")
    ;;   (pprint interaction-syms))

    
    (let [kb-syms (query-template output-kb '?/r
                                  '((?/r rdf/type owl/Restriction)))]
      ;;(pprint kb-syms)
      (doseq [sym kb-syms]
        (is (dynamically-generated-syms sym)))
      (doseq [sym kb-syms]
        (is (result-syms sym))))
    ))

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
