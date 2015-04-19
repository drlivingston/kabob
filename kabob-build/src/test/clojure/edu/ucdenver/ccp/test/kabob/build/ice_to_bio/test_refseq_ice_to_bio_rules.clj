(ns edu.ucdenver.ccp.test.kabob.build.ice-to-bio.test-refseq-ice-to-bio-rules
  (use clojure.test
       [edu.ucdenver.ccp.test.kr.sesame.test-kb :exclude [test-ns-hook]]
       
       [edu.ucdenver.ccp.test.kr.test-kb :exclude [*namespaces*]]
       
       edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule
       edu.ucdenver.ccp.kr.forward-rule
       
       edu.ucdenver.ccp.kabob.rule
       kabob
       
       clojure.pprint)
  (import java.io.File
          );;edu.ucdenver.ccp.test.kabob.build.SourceFileFactory)
  (require edu.ucdenver.ccp.kabob.namespace))



(defn kb-fixture [test-function]
  (binding [edu.ucdenver.ccp.test.kr.test-kb/*kb-creator-fn*
            sesame-memory-test-kb
            edu.ucdenver.ccp.test.kr.test-kb/*namespaces* 
            edu.ucdenver.ccp.kabob.namespace/*namespaces*]
    (test-function)))

(use-fixtures :each kb-fixture)

;;; --------------------------------------------------------
;;; constants
;;; --------------------------------------------------------

(def refseq-rule-i2b0-context-triples
     '((iaoeg/EG_23_ICE  obo/IAO_0000219       kbio/DNA_sha1 )
       (iaorefseq/REFSEQ_NT_167246_ICE  obo/IAO_0000219   kbio/DNA_sha2)))

(def refseq-rule-i2b0-expected-triples
  '((kbio/DNA_sha1 rdfs/subClassOf kso/DNA_subsequence)
     (kbio/DNA_sha2 rdfs/subClassOf kso/DNA_sequence)
     (kbio/DNASubSeq_13b86317b70ecc1168bb62221281b54a 
       obo/proper_part_of kbio/DNA_sha2)
     (kbio/DNASubSeq_13b86317b70ecc1168bb62221281b54a 
       kso/has_start_position 1887241)
     (kbio/DNASubSeq_13b86317b70ecc1168bb62221281b54a 
       kso/has_end_position 1907380)
     (kbio/DNASubSeq_13b86317b70ecc1168bb62221281b54a 
       rdfs/subClassOf kbio/DNA_sha1)))

;;; --------------------------------------------------------
;;; rule import
;;; --------------------------------------------------------

(def refseq-rules-path 
  "edu/ucdenver/ccp/kabob/build/ice_to_bio/refseq_ice_to_bio_rules.clj")
;(def ^:dynamic *rule-paths* (list refseq-rules-path))

(def refseq-rules (kabob-load-rules-from-classpath refseq-rules-path))

(def rules-map (reduce (fn [map-were-building-up {name :name :as rule}]
                  (assoc map-were-building-up
                         name    ;key
                         rule))  ;value
              {} ;an empty starting map
                       refseq-rules))

;;; --------------------------------------------------------
;;; helper functions
;;; --------------------------------------------------------

(defn verify-triples 
  "queries the input kb for each expected triple. 
   fails if an expected triple is not in the kb"
  [kb expected-triples]
  (doseq [t expected-triples]
    (is (ask-rdf kb t))))

(defn count-triples [kb]
  (count (query kb '((?/s ?/p ?/o)))))

(defn print-triples [kb]
  (pprint (query kb '((?/s ?/p ?/o)))))

;;; --------------------------------------------------------
;;; testing refseq rules
;;; --------------------------------------------------------

;; ;;testing refseq-rule-i2b0
;; (kb-test test-refseq-rule-1 refseq-rule-i2b0-context-triples
;;          (println "refseq rule i2b0 test")
;;          (let [rdf-file (SourceFileFactory/createIceRdf_gene2refseq)]
;;            (load-rdf-file *kb* rdf-file)
;;            (.delete rdf-file))
;;          (let [before-count (count-triples *kb*)
;;                expected-triple-count (count refseq-rule-i2b0-expected-triples)]
;;            (run-forward-rule *kb* *kb* (get rules-map 
;;                        "refseq-i2b0-kabobdna-partof-genomic-refseq-assertions"))
;;            (is (= expected-triple-count
;;                   (- (count-triples *kb*) before-count))))
;;          (verify-triples *kb* refseq-rule-i2b0-expected-triples))
