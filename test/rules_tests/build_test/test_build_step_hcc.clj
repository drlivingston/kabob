(ns rules-tests.build_test.test_build_step_hcc
  (use clojure.test
       kr.sesame.kb
       kr.sesame.sparql
       kr.sesame.rdf
       )
  (:require [kabob.build.run-rules :refer [query-variables]]
            [kr.core.forward-rule :refer [add-reify-fns]]
            [kr.core.sparql :refer [sparql-select-query query sparql-query ask]]
            [kr.core.rdf :refer [register-namespaces synch-ns-mappings add! load-rdf]]
            [kr.core.kb :refer [kb open close]]
            [kabob.core.namespace :refer [*namespaces*]]
            [kabob.core.rule :refer [kabob-load-rules-from-classpath]]
            [kabob.build.output-kb :refer [output-kb]]
            [clojure.pprint :refer [pprint]]
            [rules-tests.build-test.test-build-util :refer [initial-plus-ice-triples run-build-rule run-build-rules
                                                            test-kb build-rules-step-a build-rules-step-b
                                                            build-rules-step-ca build-rules-step-cb build-rules-step-cc
                                                            build-rules-step-da build-rules-step-db build-rules-step-dc
                                                            build-rules-step-fa
                                                            build-rules-step-fb
                                                            build-rules-step-ga build-rules-step-gb build-rules-step-gc
                                                            build-rules-step-ha build-rules-step-hb
                                                            build-rules-step-hca build-rules-step-hcb build-rules-step-hcc
                                                            build-rules-step-hcd build-rules-step-hce
                                                            expected-subpropertyof-links expected-inverseof-links
                                                            expected-subclassof-links expected-disjointwith-links
                                                            expected-restrictions expected-restrictions-in-lists
                                                            expected-equivalent-class-links expected-rdfs-domain-links
                                                            expected-rdfs-range-links]]
            [test-with-files.core :refer [with-tmp-dir tmp-dir]]
            [kabob.build.id-sets.generate :refer [generate-all-id-sets]]
            [clojure.java.io :as io])
  (:import [java.io File]
           [java.util.Arrays]))




(defn get-only-files
  "given a directory path, return only the files that are in the directory"
  [path]
  (filter #(.isFile %) (.listFiles (io/as-file path))))

(def base-kb (let [source-kb (test-kb initial-plus-ice-triples)]
               (run-build-rules source-kb build-rules-step-a)
               (run-build-rules source-kb build-rules-step-b)
               (run-build-rules source-kb build-rules-step-ca)
               (run-build-rules source-kb build-rules-step-cb)
               (run-build-rules source-kb build-rules-step-cc)
               (run-build-rules source-kb build-rules-step-da)
               (run-build-rules source-kb build-rules-step-db)
               (run-build-rules source-kb build-rules-step-dc)

               (with-tmp-dir
                 ;; generate identifier set ntriple files and load into the source-kb
                 (generate-all-id-sets source-kb (str tmp-dir "/"))
                 (prn (str "PRINTING FILE LIST: " (count (get-only-files tmp-dir))))
                 (dorun (map (fn [f] (prn (str "FILE TO LOAD:" f))
                               (load-rdf source-kb (java.util.zip.GZIPInputStream.
                                                     (clojure.java.io/input-stream
                                                       f)) :ntriple))
                             (get-only-files tmp-dir))))
               (run-build-rules source-kb build-rules-step-fa)
               (run-build-rules source-kb build-rules-step-fb)
               (run-build-rules source-kb build-rules-step-ga)
               (run-build-rules source-kb build-rules-step-gb)
               (run-build-rules source-kb build-rules-step-gc)
               (run-build-rules source-kb build-rules-step-ha)
               (run-build-rules source-kb build-rules-step-hb)
               (run-build-rules source-kb build-rules-step-hca)
               (run-build-rules source-kb build-rules-step-hcb)
               source-kb))


;;; Test that proteins not involved in a has_gene_template relation get a corresponding gene
(deftest step-hcc-ncbi-gene-assign-taxon
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (run-build-rule source-kb target-kb build-rules-step-hcc 0)


;    (prn (str "--------------------------------"))
;    (doall (map #(prn (str %)) (sparql-query source-kb
;     "PREFIX obo: <http://purl.obolibrary.org/obo/>
;     PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
;     prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
;     PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
;    SELECT ?taxon ?bioentity ?only_in_taxon ?gene_id
;WHERE {  ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_record
;    ?record obo:BFO_0000051 ?gene_field_value . # BFO:has_part
;    ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
;    ?gene_field_value rdf:type ?gene_id .
;       ?gene_id obo:IAO_0000219 ?bioentity . # IAO:denotes
;    ?record obo:BFO_0000051 ?taxon_field_value . #BFO:has_part
;    ?taxon_field_value rdf:type ccp:IAO_EXT_0000875 . # ccp:NCBI_gene_info_record__taxon_identifier_field_value
;    ?taxon_field_value rdf:type ?taxon_id .
;       ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
;    # ensure it's a kabob bioentity (not an obo bioentity)
;       filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/kabob/bio/'))
;
;       {
;        select ?only_in_taxon {
;                               kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
;                               filter (?only_in_taxon != obo:RO_0002160) .
;                               }
;        }
;       }"
;                                             )))
;    (prn (str "--------------------------------"))


    ;; confirm that the restriction on gene (NCBI_GENE_7048; HGNC_11773) exists
    (is (ask source-kb '((kice/HGNC_11773 obo/IAO_0000219 ?/bioentity)
                          (?/bioentity rdfs/subClassOf ?/taxon_r)
                          (?/taxon_r owl/onProperty ?/only_in_taxon)
                          (kice/RO_0002160 obo/IAO_0000219 ?/only_in_taxon))))

    (is (ask source-kb '((kice/NCBI_GENE_7048 obo/IAO_0000219 ?/bioentity)
                          (?/bioentity rdfs/subClassOf ?/taxon_r)
                          (?/taxon_r owl/onProperty ?/only_in_taxon)
                          (kice/RO_0002160 obo/IAO_0000219 ?/only_in_taxon))))

    ;; although there is a NCBI Gene with a taxon field specified, this particular gene already has a
    ;; only_in_taxon restriction that should have been brought in by the Protein Ontology
    (is (= 0 (count (query target-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (?/b rdfs/subClassOf ?/r)
                                        )))))

    (let [log-kb (output-kb "/tmp/triples.nt")]
      (run-build-rule source-kb log-kb build-rules-step-hcc 0)
      (close log-kb))

    ))


(deftest step-hcc-refseq-assign-taxon
  (let [source-kb base-kb
        target-kb (test-kb '())]

    (run-build-rule source-kb target-kb build-rules-step-hcc 1)


    ;;REFSEQ_NP_006752
    ;;REFSEQ_NP_001020018 - b/c it links to a uniprot isoform that is not brought in by the protein ontology
    (is (= 2 (count (query target-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (?/b rdfs/subClassOf ?/r)
                                        )))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-hcc 1)
    ;  (close log-kb))

    (prn (str "--------------------------------"))
    (doall (map #(prn (str %)) (sparql-query source-kb
                                             "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                                             prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                                             PREFIX obo: <http://purl.obolibrary.org/obo/>
                                             PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    SELECT ?refseq_id ?bioentity ?taxon ?only_in_taxon
WHERE {  ?record rdf:type ccp:IAO_EXT_0000792 . # ccp:RefSeq_catalog_record
    ?record obo:BFO_0000051 ?catalog_field_value . # BFO:has_part
    ?catalog_field_value rdf:type ccp:IAO_EXT_0000800 . # ccp:RefSeq_catalog_record__RefSeq_identifier_field_value
    ?catalog_field_value rdf:type ?refseq_id .
       ?refseq_id obo:IAO_0000219 ?bioentity . # IAO:denotes
    ?record obo:BFO_0000051 ?taxon_field_value . # BFO:has_part
    ?taxon_field_value rdf:type ccp:IAO_EXT_0000796 . # ccp:RefSeq_catalog_record__taxonomy_identifier_field_value
    ?taxon_field_value rdf:type ?taxon_id .
       ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
       filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/kabob/bio/'))

       # ignore any bioentities that already have a taxon restriction
       minus {
              ?bioentity rdfs:subClassOf ?taxon_r .
              ?taxon_r owl:onProperty ?only_in_taxon .
              }


       {
        select ?only_in_taxon {
                               kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                               filter (?only_in_taxon != obo:RO_0002160) .
                               }
        }
       }"
                                             )))

    (prn (str "--------------------------------"))


    ))

(deftest step-hcc-uniprot-assign-taxon
  (let [source-kb base-kb
        target-kb (test-kb '())]


;    (prn (str "--------------------------------"))
;    (doall (map #(prn (str %)) (sparql-query source-kb
;                                             "PREFIX obo: <http://purl.obolibrary.org/obo/>
;                                             PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
;                                             prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
;                                             PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
;    SELECT ?uniprot_id ?taxon ?bioentity ?only_in_taxon
;WHERE {  ?record rdf:type ccp:IAO_EXT_0000234 . # ccp:UniProt_protein_record
;    ?record obo:BFO_0000051 ?primary_accession_field_value . # BFO:has_part
;    ?primary_accession_field_value rdf:type ccp:IAO_EXT_0000240 . # ccp:UniProt_protein_record__primary_accession_field_value
;    ?primary_accession_field_value rdf:type ?uniprot_id .
;       ?uniprot_id obo:IAO_0000219 ?bioentity . # IAO:denotes
;    ?record obo:BFO_0000051 ?organism_record . # BFO:has_part
;    ?organism_record rdf:type ccp:IAO_EXT_0000935 . # ccp:UniProt_protein_record__organism_field_value
;    ?organism_record rdf:type ccp:IAO_EXT_0000972 . # ccp:uniprot_organism_record
;    ?organism_record obo:BFO_0000051 ?db_ref_record .
;       ?db_ref_record rdf:type ccp:IAO_EXT_0000955 . # ccp:uniprot_db_reference_record
;    ?db_ref_record obo:BFO_0000051 ?id_field_value .
;       ?id_field_value rdf:type ccp:IAO_EXT_0001127 . # ccp:uniprot_db_reference_record__identifier_field_value
;    ?id_field_value rdf:type ?taxon_id .
;       ?taxon_id rdfs:subClassOf ccp:IAO_EXT_0000204 . # ccp:NCBITaxon_identifier
;    ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
;    # ensure it's a kabob bioentity (not an obo bioentity)
;       filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/kabob/bio/'))
;
;       # ignore any bioentities that already have a taxon restriction
;       minus {
;              ?bioentity rdfs:subClassOf ?taxon_r .
;              ?taxon_r owl:onProperty ?only_in_taxon .
;              }
;
;       {
;        select ?only_in_taxon {
;                               kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
;                               filter (?only_in_taxon != obo:RO_0002160) .
;                               }
;        }
;       }"
;                                             )))
;    (prn (str "--------------------------------"))

    (run-build-rule source-kb target-kb build-rules-step-hcc 2)


    ;; P62258 is assigned a taxon. P37173 is not b/c it already has one assigned by the Protein Ontology
    (is (= 1 (count (query target-kb '((?/r rdf/type owl/Restriction)
                                        (?/r owl/onProperty ?/prop)
                                        (?/b rdfs/subClassOf ?/r)
                                        )))))

    ;(let [log-kb (output-kb "/tmp/triples.nt")]
    ;  (run-build-rule source-kb log-kb build-rules-step-ia 0)
    ;  (close log-kb))

    ))





