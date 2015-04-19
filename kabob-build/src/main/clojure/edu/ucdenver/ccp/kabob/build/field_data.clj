(ns edu.ucdenver.ccp.kabob.build.field-data
  (:gen-class)
  (use clojure.pprint
       clojure.stacktrace

       edu.ucdenver.ccp.utils

       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kabob.build.input-kb
       edu.ucdenver.ccp.kabob.stats.field-counts))

;;; --------------------------------------------------------
;;; globals
;;; --------------------------------------------------------

(def field-kb-details '{:server-url "http://localhost:10035"
                        :repo-name  "kabob"
                        :username   "xx_user_xx"
                        :password    "xx_pass_xx"})
  
(def data-kb-details '{:server-url "http://localhost:10035"
                       :repo-name  "ice20121201"
                       :username   "xx_user_xx"
                       :password   "xx_pass_xx"})

(def cached-file "/temp/kabob/field-counts/field-counts.clj")


(def kiao-field-list
  '(iaodip/DipInteractionDetectionMethod_detectionMethodIdDataField1
 iaodip/DipInteractionSourceDatabase_sourceDatabaseIdDataField1
 iaodip/DipInteractionType_interactionTypeIdDataField1
 iaodip/DipInteractor_dbXReferenceIDsDataField1
 iaodip/DipPublication_pmidDataField1
 iaodrugbank/DrugBankDrugRecord_ahfsCodesDataField1
 iaodrugbank/DrugBankDrugRecord_atcCodesDataField1
 iaodrugbank/DrugBankDrugRecord_casNumberDataField1
 iaodrugbank/DrugBankDrugRecord_externalIdentifiersDataField1
 iaodrugbank/DrugBankPartnerRecord_externalIdentifiersDataField1
 iaodrugbank/Reference_pmidDataField1
 iaoeg/EntrezGene2RefseqFileData_RNA_nucleotide_giDataField1
 iaoeg/EntrezGene2RefseqFileData_genomic_nucleotide_giDataField1
 iaoeg/EntrezGene2RefseqFileData_protein_giDataField1
 iaoeg/EntrezGeneInfoFileData_dbXrefsDataField1
 iaogad/GeneticAssociationDbAllTxtFileData_nucleotideDbIdDataField1
 iaogad/GeneticAssociationDbAllTxtFileData_pubmedIDDataField1
 iaogad/GeneticAssociationDbAllTxtFileData_unigeneAccessionIDDataField1
 iaohgnc/HgncDownloadFileData_accessionNumbersDataField1
 iaohgnc/HgncDownloadFileData_ccdsIDsDataField1
 iaohgnc/HgncDownloadFileData_ecNumbersDataField1
 iaohgnc/HgncDownloadFileData_ensemblGeneIDDataField1
 iaohgnc/HgncDownloadFileData_pubmedIDsDataField1
 iaohgnc/HgncDownloadFileData_suppliedEnsemblIdDataField1
 iaohgnc/HgncDownloadFileData_suppliedGdbIdDataField1
 iaohgnc/HgncDownloadFileData_suppliedRgdIdDataField1
 iaohgnc/HgncDownloadFileData_suppliedUcscIdDataField1
 iaohgnc/HgncDownloadFileData_vegaIDsDataField1
 iaohgnc/SpecialistDbIdLinkPair_specialistDbIdDataField1
 iaohomologene/HomoloGeneDataFileData_proteinGIDataField1
 iaohprd/HprdIdMappingsTxtFileData_nucleotideAccessionDataField1
 iaohprd/HprdIdMappingsTxtFileData_proteinAccessionDataField1
 iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1
 iaoirefweb/IRefWebInteractionSourceDatabase_sourceDatabaseIdDataField1
 iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1
 iaoirefweb/IRefWebInteraction_imexIdDataField1
 iaoirefweb/IRefWebInteraction_interactionDbIdsDataField1
 iaoirefweb/IRefWebInteraction_pmidsDataField1
 iaoirefweb/IRefWebInteractorBiologicalRole_biologicalRoleIdDataField1
 iaoirefweb/IRefWebInteractorExperimentalRole_experimentalRoleIdDataField1
 iaoirefweb/IRefWebInteractorType_interactorTypeIdDataField1
 iaoirefweb/IRefWebInteractor_alternateIdsDataField1
 iaoirefweb/IRefWebInteractor_finalReferenceDataField1
 iaoirefweb/IRefWebInteractor_originalReferenceDataField1
 iaomgi/MGIPhenoGenoMPFileData_mammalianPhenotypeIDDataField1
 iaomgi/MGIPhenoGenoMPFileData_pubmedIDsDataField1
 iaomgi/MRKReferenceFileData_pubmedIDsDataField1
 iaomgi/MRKSequenceFileData_ensemblProteinIdsDataField1
 iaomgi/MRKSequenceFileData_ensemblTranscriptIdDataField1
 iaomgi/MRKSequenceFileData_genBankAccessionIDsDataField1
 iaomgi/MRKSequenceFileData_vegaProteinIdsDataField1
 iaomgi/MRKSequenceFileData_vegaTranscriptIdsDataField1
 iaopharmgkb/PharmGkbDiseaseFileRecord_externalVocabularyDataField1
 iaopharmgkb/PharmGkbDrugFileRecord_crossReferencesDataField1
 iaopharmgkb/PharmGkbGeneFileRecord_crossReferencesDataField1
 iaopharmgkb/PharmGkbGeneFileRecord_ensemblGeneIdDataField1
 iaopharmgkb/PharmGkbRelationFileRecord_entity1IdDataField1
 iaopharmgkb/PharmGkbRelationFileRecord_entity2IdDataField1
 iaopharmgkb/PharmGkbRelationFileRecord_pmidsDataField1
 iaorefseq/RefSeqReleaseCatalogFileData_giDataField1
 iaouniprot/DbReference_idDataField1
 iaouniprot/Interactant_intactIdDataField1
 iaouniprot/UniProtIDMappingFileData_ipiIdsDataField1))


;;; --------------------------------------------------------
;;; arguments and helpers
;;; --------------------------------------------------------

(defn command-line-args [original-args]
  {;; The output directory where generated triple files will be placed. 
   ;; IMPORTANT: The output directory path must end with a forward slash.
   :output-directory (nth original-args 0)
   })

(defn logging-header [args]
  (prn (str "Clojure version: " *clojure-version*))
  (prn (str "Field Name KB URL: " field-kb-details))
  (prn (str "Field Data KB URL: " data-kb-details))
  (prn (str "Output directory: " (:output-directory args))))


;;; --------------------------------------------------------
;;; analyze data
;;; --------------------------------------------------------

(defn load-cached-data [file]
  (reduce conj
          {}
          (read-all-input file)))

(defn empty-fields [field-data]
  (remove (fn [[k v]]
            (not (empty? v)))
          field-data))

(defn multi-fields [field-data]
  (remove (fn [[k v]]
            (empty? (rest v)))
          field-data))


(defn kiao-only-fields [field-data]
  (remove (fn [[k v]]
            (or (empty? v)
                (not (empty? (rest v)))
                (not (= "kiao" (first (first v))))))
          field-data))

(defn kiao-fields [field-data]
  (remove (fn [[k v]]
            (or (empty? v)
                (not (get v "kiao" false))))
          field-data))

(defn string-fields [field-data]
  (remove (fn [[k v]]
            (or (empty? v)
                (not (get v 'java.lang.String false))))
          field-data))

(defn non-symbol-non-string-fields [field-data]
  (remove (fn [[k v]]
            (or (empty? v)
                (get v 'java.lang.String false)
                (not (some (fn [[ns-or-type count]]
                             (symbol? ns-or-type))
                           v))))
          field-data))



;;; --------------------------------------------------------
;;; field list
;;; --------------------------------------------------------

;; (defn field-list []
;;   (let [kb (open-kb field-kb-details)]
;;     (try
;;       (doall (all-fields kb))
;;       (catch Exception e (print-cause-trace e))
;;       (finally (close kb)))))

(defn field-list []
  kiao-field-list)
  
;;; --------------------------------------------------------
;;; collect all data
;;; --------------------------------------------------------

(defn log-field-data [output-dir fields]
  (let [kb (open-kb data-kb-details)]
    (try
      (write-all-field-counts output-dir kb fields)
      (catch Exception e (print-cause-trace e))
      (finally (close kb)))))

(defn process-all-fields [output-dir]
  (println "getting field list:")
  (let [fields (time (field-list))]
    (pprint fields)
    (flush)
    (log-field-data output-dir fields)
    (flush)))

;;; --------------------------------------------------------
;;; command line and main
;;; --------------------------------------------------------


(defn commandline-process-forward-rules [args]
  (logging-header args)
  (process-all-fields (:output-directory args)))

(defn -main [& args]
  (println "global:")
  (pprint *command-line-args*)
  (println "local:")
  (pprint args)
  (commandline-process-forward-rules (command-line-args args))
  (System/exit 0))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
