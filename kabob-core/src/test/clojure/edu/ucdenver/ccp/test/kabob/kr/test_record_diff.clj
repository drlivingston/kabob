(ns edu.ucdenver.ccp.test.kabob.kr.test-record-diff
  (use clojure.test
       ;;clojure.test.junit
       edu.ucdenver.ccp.kabob.parallel-utils

       edu.ucdenver.ccp.kabob.kr.record-diff
       edu.ucdenver.ccp.kabob.namespace
       edu.ucdenver.ccp.kr.sesame.kb

       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       
       edu.ucdenver.ccp.utils

       clojure.pprint

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

(def date1 ["2011-11-11T00:00:00Z" 'xsd/dateTime])

(def date2 ["2012-12-12T00:00:00Z" 'xsd/dateTime])


(def old-triples '())

(def new-triples '())

(def result-triples '())

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

(defn new-kb []
  (test-kb new-triples))

;;; --------------------------------------------------------
;;; synthetic records
;;; --------------------------------------------------------

(defn new-sym []
  (symbol "ex" (str "X_" (uuid))))

;; (defn create-field [r-type schema & [key-field?]]
;;   (let [field (new-sym)]
;;     ;;return field triple pair
;;     [field
;;      (concat (if key-field?
;;                `((~r-type kiao/hasKeyField ~field))
;;                '())
;;              `((~schema obo/BFO_0000051 ~field)))]))

;; change the schema should have the keyfields not the record

(defn create-field [schema & [key-field?]]
  (let [field (new-sym)]
    ;;return field triple pair
    [field
     (concat (if key-field?
               `((~schema kiao/hasKeyField ~field))
               '())
             `((~schema obo/BFO_0000051 ~field)))]))

(defn create-record-type [num-key-fields num-other-fields]
  (let [r-type (new-sym)
        schema (new-sym)
        def-triples `((~r-type rdfs/subClassOf kiao/Record) 
                      (~r-type kiao/hasTemplate ~schema))
        keys (for [i (range 0 num-key-fields)]
               (create-field schema true))
        others (for [i (range 0 num-other-fields)]
                 (create-field schema nil))]
    ;;return
    [r-type
     schema
     (map first keys)
     (map first others)
     (concat def-triples
             (mapcat second keys)
             (mapcat second others))]))

(defn create-field-value [field & [value]]
  (let [v (or value (new-sym))
        fv (new-sym)] ;change this to sha1 of field and v pair
    ;;return
    [fv
     `((~fv rdf/type kiao/FieldValue)
       (~fv kiao/hasTemplate ~field)
       (~fv obo/IAO_0000219 ~v))
     field
     v]))

(defn create-single-record [[r-type schema key-fields other-fields]]
  (let [r (new-sym)] ;change this to be sha1 of the sorted f v pairs
    ;; return record triple pair
    [r
     (concat `((~r rdf/type ~r-type)
               (~r kiao/hasTemplate ~schema))
             (mapcat (fn [f]
                       (let [[fv new-triples] (create-field-value f)]
                         (cons `(~r obo/BFO_0000051 ~fv)
                               new-triples)))
                     (concat key-fields other-fields)))]))

(defn create-record [r-type schema fvs]
  (let [r (new-sym)] ;change this to be sha1 of the sorted f v pairs
    ;;return record triple pair
    [r
     (concat `((~r rdf/type ~r-type)
               (~r kiao/hasTemplate ~schema))
             (map (fn [fv]
                    `(~r obo/BFO_0000051 ~fv))
                  fvs))]))
  
;;; pairs of records with overlap
;;; --------------------------------------------------------
  

(defn create-record-pair [[r-type schema key-fields other-fields :as rec]
                          [num-unique-r1 num-other-shared num-unique-r2]]
                                        ;; :as t3]]
  ;;(println t3)
  (let [keyfvs (map create-field-value key-fields)
        ;; pull off some shared fields
        shared-fields (take num-other-shared other-fields)
        rest-fields (nthnext other-fields num-other-shared)
        ;; compute triples for shared and unique fvs
        shared (map create-field-value shared-fields)
        unique-r1 (map create-field-value (take num-unique-r1 rest-fields))
        unique-r2 (map create-field-value (take num-unique-r2 rest-fields))]
    (let [[r1 r1-triples] (create-record r-type
                                         schema
                                         (concat (map first keyfvs)
                                                 (map first shared)
                                                 (map first unique-r1)))
          [r2 r2-triples] (create-record r-type
                                         schema
                                         (concat (map first keyfvs)
                                                 (map first shared)
                                                 (map first unique-r2)))]
      ;;return record-triple pairs
      [[r1 (concat r1-triples
                   (mapcat second (concat keyfvs shared unique-r1)))]
       [r2 (concat r2-triples
                   (mapcat second (concat keyfvs shared unique-r2)))]])))


;;; pairs of records sets with ranges of overlapping records
;;; --------------------------------------------------------

(defn create-record-field-allocations [other-range]
  (mapcat (fn [shared]
            (let [unique-range (range 0 (- (+ 1 other-range) shared))]
              (mapcat (fn [r1]
                        (map (fn [r2]
                               [r1 shared r2])
                             unique-range))
                      unique-range)))
          (range 0 (+ 1 other-range))))

(defn create-record-pairs-with-field-distribution [[_ _ _ others _
                                                    :as record-type]]
  (map (partial create-record-pair record-type)
       (create-record-field-allocations (count others))))


;;; record set
;;; --------------------------------------------------------

(defn create-overlapping-records [[r-type schema key-fields other-fields
                                   :as rec]]
  (let [keyfvs1 (map create-field-value key-fields)
        keyfvs2 (map create-field-value key-fields)
        shared (map create-field-value other-fields)]
    (let [[r1 r1-triples] (create-record r-type
                                         schema
                                         (concat (map first keyfvs1)
                                                 (map first shared)))
          [r2 r2-triples] (create-record r-type
                                         schema
                                         (concat (map first keyfvs2)
                                                 (map first shared)))
          
          [r1- r1--triples] (create-record r-type
                                           schema
                                           (concat (map first keyfvs1)
                                                   (map first (rest shared))))
          [r2- r2--triples] (create-record r-type
                                           schema
                                           (concat (map first keyfvs2)
                                                   (map first (rest shared))))]
      
      ;;return two lists of record-triple pairs
      [(list [r1 (concat r1-triples
                         (mapcat second (concat keyfvs1 shared)))]
             [r2 (concat r2-triples
                         (mapcat second keyfvs2))])
       (list [r1- (concat r1--triples
                          (mapcat second (concat keyfvs1 (rest shared))))]
             [r2- (concat r2--triples
                          (mapcat second keyfvs2))])])))


(defn create-shared-fv-pair [[r-type schema key-fields other-fields
                              :as rec]]
  (let [keyfvs1 (map create-field-value key-fields)
        keyfvs2 (map create-field-value key-fields)
        shared (map create-field-value other-fields)]
    (let [[r1 r1-triples] (create-record r-type
                                         schema
                                         (concat (map first keyfvs1)
                                                 (map first shared)))
          [r1- r1--triples] (create-record r-type
                                           schema
                                           (concat (map first keyfvs2)
                                                   (map first (rest shared))))]
      ;;return lists of record-triple pairs
      [[r1 (concat r1-triples
                   (mapcat second (concat keyfvs1 shared)))]
       [r1- (concat r1--triples
                    (mapcat second (concat keyfvs2 (rest shared))))]])))

;;; record set
;;; --------------------------------------------------------

(defn create-record-set [rs-type schema date records]
  (let [rs (new-sym)]
    (concat `((~rs rdf/type kiao/DataSet)
              (~rs kiao/hasTemplate ~schema)
              (~rs kiao/hasCreationDate ~date))
            (map (fn [rec]
                   `(~rs obo/BFO_0000051 ~rec))
                 records))))

;;; --------------------------------------------------------
;;; full synthentic pairs
;;; --------------------------------------------------------

(defn create-test-record-set-pairs [num-key-fields num-other-fields]
  (let [[r-type schema keys others type-triples :as record-type]
        (create-record-type num-key-fields num-other-fields)
        
        shared-rec (create-single-record record-type)
        r1-unique-rec (create-single-record record-type)
        r2-unique-rec (create-single-record record-type)
        record-variation-pairs
        (create-record-pairs-with-field-distribution record-type)
        [overlapping-a+ overlapping-a-] (create-overlapping-records record-type)
        [overlapping-b+ overlapping-b-] (create-overlapping-records record-type)

        r1-recs (cons shared-rec
                      (cons r1-unique-rec
                            (concat overlapping-a+
                                    overlapping-b-
                                    (map first record-variation-pairs))))
        r2-recs (cons shared-rec
                      (cons r2-unique-rec
                            (concat overlapping-a-
                                    overlapping-b+
                                    (map second record-variation-pairs))))]
    ;;return pair of triple lists
    [(concat type-triples
             (create-record-set nil schema date1 (map first r1-recs))
             (mapcat second r1-recs))
     (concat type-triples
             (create-record-set nil schema date2 (map first r2-recs))
             (mapcat second r2-recs))]))


(defn create-all-key-record-sets [ num-key-fields]
  (let [[r-type schema keys others type-triples :as record-type]
        (create-record-type num-key-fields 0)

        swiched-key-other [r-type schema others keys]
        
        set1-shared-fv-pair (create-shared-fv-pair swiched-key-other)
        set2-shared-fv-pair (create-shared-fv-pair swiched-key-other)
        [set1a set2a] (create-shared-fv-pair swiched-key-other)
        [set2b set1b] (create-shared-fv-pair swiched-key-other)
        
        shared-recs [(create-single-record record-type)
                     (create-single-record record-type)]
        set1-unique-recs [(create-single-record record-type)
                          (create-single-record record-type)]
        set2-unique-recs [(create-single-record record-type)
                          (create-single-record record-type)]

        s1-recs (cons set1a
                      (cons set1b
                            (concat shared-recs
                                    set1-shared-fv-pair
                                    set1-unique-recs)))
        s2-recs (cons set2a
                      (cons set2b
                            (concat shared-recs
                                    set2-shared-fv-pair
                                    set2-unique-recs)))]
    ;;return pair of triple lists
    [(concat type-triples
             (create-record-set nil schema date1 (map first s1-recs))
             (mapcat second s1-recs))
     (concat type-triples
             (create-record-set nil schema date2 (map first s2-recs))
             (mapcat second s2-recs))]))

(defn create-no-key-record-sets [ num-other-fields]
  (let [[r-type schema keys others type-triples :as record-type]
        (create-record-type 0 num-other-fields)

        set1-shared-fv-pair (create-shared-fv-pair record-type)
        set2-shared-fv-pair (create-shared-fv-pair record-type)
        [set1a set2a] (create-shared-fv-pair record-type)
        [set2b set1b] (create-shared-fv-pair record-type)
        
        shared-recs [(create-single-record record-type)
                     (create-single-record record-type)]
        set1-unique-recs [(create-single-record record-type)
                          (create-single-record record-type)]
        set2-unique-recs [(create-single-record record-type)
                          (create-single-record record-type)]

        s1-recs (cons set1a
                      (cons set1b
                            (concat shared-recs
                                    set1-shared-fv-pair
                                    set1-unique-recs)))
        s2-recs (cons set2a
                      (cons set2b
                            (concat shared-recs
                                    set2-shared-fv-pair
                                    set2-unique-recs)))]
    ;;return pair of triple lists
    [(concat type-triples
             (create-record-set nil schema date1 (map first s1-recs))
             (mapcat second s1-recs))
     (concat type-triples
             (create-record-set nil schema date2 (map first s2-recs))
             (mapcat second s2-recs))]))

;;need to create a couple of fields that are shared across records so that
;;  we can check for field values being overly serialized



;;; --------------------------------------------------------
;;; tests
;;; --------------------------------------------------------

(defn get-rs [kb]
  (query-template kb '?/rs
                  '((?/rs rdf/type kiao/DataSet))))


(defn record-set-diff-1-2 []
  (let [output-kb (test-kb '())
        [old-kb new-kb] (map test-kb (create-test-record-set-pairs 1 2))
        old-rs (first (get-rs old-kb))
        new-rs (first (get-rs new-kb))]
    (try
      ;;verify source kb record counts
      (is (= 20 (count (query old-kb '((?/rt rdfs/subClassOf kiao/Record)
                                       (?/r rdf/type ?/rt))))))
      (is (= 20 (count (query new-kb '((?/rt rdfs/subClassOf kiao/Record)
                                       (?/r rdf/type ?/rt))))))

      (is (= 44 (count (query old-kb '((?/r rdf/type kiao/FieldValue))))))
      (is (= 44 (count (query new-kb '((?/r rdf/type kiao/FieldValue))))))

      (println "record diffs:")
      (time
       (generate-record-diffs old-kb
                              new-kb
                              output-kb
                              old-rs
                              new-rs))
      (println "preserve old fvs:")
      (time
       (dorun (map (partial preserve-old-fvs old-kb new-kb output-kb)
                   (set
                    (query-template old-kb
                                    '?/field
                                    '((_/rs rdf/type kiao/DataSet)
                                      (_/rs kiao/hasTemplate _/schema)
                                      (_/schema obo/BFO_0000051 ?/field)))))))

                   
      (pprint (count (query-rdf output-kb nil nil nil)))
      (pprint (count (query output-kb '((?/part rdf/type kiao/FieldValue)))))

      ;;drops mean they are new, remember the diffs run backward in time
      ;;19 record drops
      ;;13 field drops (11 from allocations, 2 from overlaps)
      
      ;;record removes and fieldvalue removes combined
      (is (= (+ 19 13)
             (count (query output-kb '((?/diff kiao/removePart ?/part))))))

      ;;record adds and fieldvalue adds combined
      (is (= (+ 19 13)
             (count (query output-kb '((?/diff kiao/addPart ?/part))))))

      ;;the resulting new KB should have 15 more FVs
      ;; 11 from allocations, 3 from unique, and 1 from the overlapping sets
      ;; these will faile because FVs aren't serialized yet
      (is (= 15 (count (query output-kb '((?/r rdf/type kiao/FieldValue))))))

      (catch Exception e (println "EXCEPTION: " e))
      (finally (close output-kb)
               (close old-kb)
               (close new-kb)))))

(deftest test-record-set-diff-1-2
  (binding [*work-queue-single-threaded* false]
    (record-set-diff-1-2)
    ))
  


(defn record-set-diff-2 [[old-kb new-kb]]
  (let [output-kb (test-kb '())
        old-rs (first (get-rs old-kb))
        new-rs (first (get-rs new-kb))]
    (try
      ;;verify source kb record counts
      (is (= 8 (count (query old-kb '((?/rt rdfs/subClassOf kiao/Record)
                                       (?/r rdf/type ?/rt))))))
      (is (= 8 (count (query new-kb '((?/rt rdfs/subClassOf kiao/Record)
                                       (?/r rdf/type ?/rt))))))

      (is (= 13 (count (query old-kb '((?/r rdf/type kiao/FieldValue))))))
      (is (= 13 (count (query new-kb '((?/r rdf/type kiao/FieldValue))))))

      (time
       (generate-record-diffs old-kb
                              new-kb
                              output-kb
                              old-rs
                              new-rs))
      (println "preserve old fvs:")
      (time
       (dorun (map (partial preserve-old-fvs old-kb new-kb output-kb)
                   (set
                    (query-template old-kb
                                    '?/field
                                    '((_/rs rdf/type kiao/DataSet)
                                      (_/rs kiao/hasTemplate _/schema)
                                      (_/schema obo/BFO_0000051 ?/field)))))))


      (pprint (count (query-rdf output-kb nil nil nil)))
      ;;7 expected
      (pprint (count (query output-kb '((?/part rdf/type kiao/FieldValue)))))
      
      ;;record removes (no fieldvalue removes) combined
      (is (= 6
             (count (query output-kb '((?/diff kiao/removePart ?/part))))))

      ;;record adds and (no fieldvalue adds) combined
      (is (= 6
             (count (query output-kb '((?/diff kiao/addPart ?/part))))))

      (is (= 7 (count (query output-kb '((?/r rdf/type kiao/FieldValue))))))
      
      (catch Exception e (println "EXCEPTION: " e))
      (finally (close output-kb)
               (close old-kb)
               (close new-kb)))))

(deftest test-record-set-diff-0-2
  (binding [*work-queue-single-threaded* false]
    (record-set-diff-2 (map test-kb (create-no-key-record-sets 2)))))

(deftest test-record-set-diff-2-0
  (binding [*work-queue-single-threaded* false]
    (record-set-diff-2 (map test-kb (create-all-key-record-sets 2)))))



;; (deftest test-id-rules-known-syms
;;   (doseq [rule-path rule-paths]
;;     ;;(prn rule-path)
;;       (let [rules (kabob-load-rules-from-classpath rule-path)]
;;         (is (= 0 (count (bad-rules (partial all-ns-known? known-ns)
;;                                    rules)))))))


;; (deftest test-id-rules-forward-safe
;;   (doseq [rule-path rule-paths]
;;     ;;(prn rule-path)
;;       (let [rules (kabob-load-rules-from-classpath rule-path)]
;;         (is (< 0 (count rules)))
;;         (doseq [r rules]
;;           (is (not (nil? (meta r)))))
;;         (is (= 0 (count (bad-rules forward-safe-with-reification? rules)))))))




;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
