(ns edu.ucdenver.ccp.test.kabob.build.example-diff
  (use clojure.test
       ;;clojure.test.junit

       edu.ucdenver.ccp.kabob.kr.record-diff
       edu.ucdenver.ccp.kabob.namespace
       edu.ucdenver.ccp.kr.sesame.kb

       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       
       edu.ucdenver.ccp.utils

       clojure.pprint


       edu.ucdenver.ccp.kr.sesame.writer-kb
       edu.ucdenver.ccp.test.kabob.kb-connection))
  ;; (require kabob
  ;;          clojure.string))

;;; --------------------------------------------------------
;;; constansts
;;; --------------------------------------------------------

(def date1 ["2011-11-11T00:00:00Z" 'xsd/dateTime])

(def date2 ["2012-12-12T00:00:00Z" 'xsd/dateTime])

(def old-kb-name "")
(def new-kb-name "")

(def ^:dynamic *diff-output-file*
  "test-2011-2012-diff-triples.nt")

(def ^:dynamic *diff-output-dir*
  "/temp/kabob/diff/gad/")

;;; --------------------------------------------------------
;;; kbs
;;; --------------------------------------------------------


;; (defn test-kb [triples]
;;   (let [kb (register-namespaces (synch-ns-mappings (open (kb :sesame-mem)))
;;                                 *namespaces*)]
;;     (dorun (map (partial add! kb) triples))
;;     kb))


(defn old-kb []
  (old-test-kb))

(defn new-kb []
  (new-test-kb))

;;; --------------------------------------------------------
;;; synthetic records
;;; --------------------------------------------------------


;;; --------------------------------------------------------
;;; tests
;;; --------------------------------------------------------

(defn get-rs [kb]
  (query-template kb '?/rs
                  '((?/rs rdf/type kiao/DataSet))))

(defn preserve-fvs [old-kb new-kb base-dir fields]
  (dorun
   (map (fn [field]
          (let [output-kb (initialize-ns-mappings
                           (open (new-sesame-writer-kb
                                  (str base-dir (name field))))
                           new-kb)]
            (try
              (pprint field)
              (time
               (preserve-old-fvs old-kb new-kb output-kb field))
              (finally (close output-kb)))))
        fields)))


(defn old-gad-fields [kb]
  (println "getting gad fields:")
  (time
   (doall       
    (binding [*select-type* select-distinct] 
      (query-template kb '?/template
         '((_/fv kiao/hasTemplate ?/template)
           (_/r obo/BFO_0000051 _/fv)
           (iaogad/gadGeneticAssociationDbAllTxtFileDataDataSet20110209
            obo/BFO_0000051 _/r)))))))


(defn gad-diff-triples [old-kb new-kb]
  (let [output-kb (initialize-ns-mappings
                   (open (new-sesame-writer-kb
                          (str *diff-output-dir* *diff-output-file*)))
                   new-kb)]
    (try
      (println "record diffs:")
      (time
       (generate-record-diffs
        old-kb
        new-kb
        output-kb
        'iaogad/gadGeneticAssociationDbAllTxtFileDataDataSet20110209
        'iaogad/gadGeneticAssociationDbAllTxtFileDataDataSet20121116))

      (println "preserving old fvs:")
      (time
       (let [fields (set (old-gad-fields old-kb))]
         (time
          (preserve-fvs old-kb new-kb *diff-output-dir* fields))))

      (catch Exception e (println "EXCEPTION: " e))
      (finally (close output-kb)))))



(defn old-eg-fields [kb]
  (println "getting gad fields:")
  (time
   (doall       
    (binding [*select-type* select-distinct] 
      (query-template kb '?/template
         '((_/fv kiao/hasTemplate ?/template)
           (_/r obo/BFO_0000051 _/fv)
           (iaoeg/egEntrezGeneInfoFileDataDataSet20110209
            obo/BFO_0000051 _/r)))))))


(defn eg-fields [old-kb new-kb]
  (binding [*diff-output-dir*
            "/temp/kabob/diff/eg/"]
    (println "preserving old fvs:")
    (time
     (let [fields '(iaoeg/EntrezGeneInfoFileData_taxonIDDataField1
                    iaoeg/EntrezGeneInfoFileData_modificationDateDataField1
                    iaoeg/EntrezGeneInfoFileData_chromosomeDataField1
                    iaoeg/EntrezGeneInfoFileData_symbolDataField1
                    iaoeg/EntrezGeneInfoFileData_geneIDDataField1
                    iaoeg/EntrezGeneInfoFileData_locusTagDataField1
                    iaoeg/EntrezGeneInfoFileData_descriptionDataField1
                    iaoeg/EntrezGeneInfoFileData_otherDesignationsDataField1
                    iaoeg/EntrezGeneInfoFileData_synonymsDataField1
                    iaoeg/EntrezGeneInfoFileData_dbXrefsDataField1
        iaoeg/EntrezGeneInfoFileData_fullNameFromNomenclatureAuthorityDataField1
                    iaoeg/EntrezGeneInfoFileData_nomenclatureStatusDataField1
                    iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1
        iaoeg/EntrezGeneInfoFileData_symbolFromNomenclatureAuthorityDataField1
                    iaoeg/EntrezGeneInfoFileData_mapLocationDataField1)]
       (time
        (preserve-fvs old-kb new-kb *diff-output-dir* fields))))))

(defn eg-diff [old-kb new-kb]
  (binding [*diff-output-dir*
            "/temp/kabob/diff/eg/"]
    (let [output-kb (initialize-ns-mappings
                     (open (new-sesame-writer-kb
                            (str *diff-output-dir* *diff-output-file*)))
                     new-kb)]
      (try
        (println "record diffs:")
        (time
         (generate-record-diffs
          old-kb
          new-kb
          output-kb
          'iaoeg/egEntrezGeneInfoFileDataDataSet20110209
          'iaoeg/egEntrezGeneInfoFileDataDataSet20121116))
        
        (println "preserving old fvs:")
        (time
         (let [fields (set (old-eg-fields old-kb))]
           (eg-fields old-kb new-kb)))
           ;; (time
           ;;  (preserve-fvs old-kb new-kb *diff-output-dir* fields))))
        
        (catch Exception e (println "EXCEPTION: " e))
        (finally (close output-kb))))))

(defn bootstrap-eg-key-triples [kb]
  (add! kb '(iaoeg/egEntrezGeneInfoFileDataSchema1
             kiao/hasKeyField
             iaoeg/EntrezGeneInfoFileData_geneIDDataField1))
  (dorun
   (map (fn [f]
          (add! kb `(iaoeg/egEntrezGeneInfoFileDataSchema1
                     obo/BFO_0000051
                     ~f)))
        '(iaoeg/EntrezGeneInfoFileData_symbolDataField1
          iaoeg/EntrezGeneInfoFileData_geneIDDataField1
          iaoeg/EntrezGeneInfoFileData_locusTagDataField1
          iaoeg/EntrezGeneInfoFileData_descriptionDataField1
          iaoeg/EntrezGeneInfoFileData_taxonIDDataField1
          iaoeg/EntrezGeneInfoFileData_otherDesignationsDataField1
          iaoeg/EntrezGeneInfoFileData_synonymsDataField1
          iaoeg/EntrezGeneInfoFileData_dbXrefsDataField1
        iaoeg/EntrezGeneInfoFileData_fullNameFromNomenclatureAuthorityDataField1
          iaoeg/EntrezGeneInfoFileData_nomenclatureStatusDataField1
          iaoeg/EntrezGeneInfoFileData_typeOfGeneDataField1
          iaoeg/EntrezGeneInfoFileData_symbolFromNomenclatureAuthorityDataField1
          iaoeg/EntrezGeneInfoFileData_chromosomeDataField1
          iaoeg/EntrezGeneInfoFileData_mapLocationDataField1
          iaoeg/EntrezGeneInfoFileData_modificationDateDataField1))))

  
;; (defn record-set-diff-1-2 []
;;   (let [output-kb (test-kb '())
;;         [old-kb new-kb] (map test-kb (create-test-record-set-pairs 1 2))
;;         old-rs (first (get-rs old-kb))
;;         new-rs (first (get-rs new-kb))]
;;     (try
;;       (time
;;        (generate-record-diffs old-kb
;;                               new-kb
;;                               output-kb
;;                               old-rs
;;                               new-rs))
      
      
;;       (catch Exception e (println "EXCEPTION: " e))
;;       (finally (close output-kb)
;;                (close old-kb)
;;                (close new-kb)))))

;; (deftest test-record-set-diff-1-2
;;   (binding [*work-queue-single-threaded* false]
;;     (record-set-diff-1-2)
;;     ))
  



;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
