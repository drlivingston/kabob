(ns edu.ucdenver.ccp.kabob.build.id-mapping
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))

;; NOTE: IF THIS CODE IS RESURRECTED:
;; records obo/BFO_0000051 (has_part) field values
;; this code uses field values obo/BFO_0000050 part_of records



;; TODO - should log a warning if none of the conditions are met, 
;; e.g. if cls1 is a gene and cls2 is a protein
(defn determine-bio-relation [cls1 cls2]
  (if (= cls1 cls2) 'owl/sameAs
    (if (and (= cls1 'chebi/CHEBI_36080) 
          (= cls2 'so/SO_0000704)) 'kiao/hasIndirectTemplate
      (if (and (= cls1 'so/SO_0000234) 
            (= cls2 'so/SO_0000704)) 'kiao/hasDirectTemplate
        (if (and (= cls1 'chebi/CHEBI_36080) 
              (= cls2 'so/SO_0000234)) 'kiao/hasDirectTemplate
          (if (and (= cls1 'chebi/CHEBI_36080) 
                (= cls2 'so/SO_0000356)) 'kiao/hasDirectTemplate
            (if (and (= cls1 'so/SO_0000356) 
                  (= cls2 'so/SO_0000704)) 'kiao/hasDirectTemplate nil)))))))


(defn determine-pro-bio-relation [related-cls]
  (prn "Getting pro-bio relation for: " (name related-cls))
  (cond 
    (.startsWith (name related-cls) "MGI") 'kiao/hasIndirectTemplate
    (.startsWith (name related-cls) "HGNC") 'kiao/hasIndirectTemplate
    (.startsWith (name related-cls) "UNIPROT") 'owl/sameAs
    (.startsWith (name related-cls) "REACTOME") nil
    (.startsWith (name related-cls) "ECOCYC") nil     
    :else ((prn "unhandled id mapped to a pro concept: " (name related-cls)) nil )
    )
  )

(defn get-de-ice-ns [ice]
  (prn "Getting de-ice ns for: " (name ice))
  (cond 
    (.startsWith (name ice) "MGI") "mgi"
    (.startsWith (name ice) "HGNC") "hgnc"
    (.startsWith (name ice) "UNIPROT") "uniprot"
    (.startsWith (name ice) "REACTOME") "reactome"
    (.startsWith (name ice) "ECOCYC") "ecocyc"
    :else ((prn "unhandled id type mapped to a pro concept: " (name ice)) nil)
    )
  )

;; generic rule definition for linking two bio world instances by a relation. 
(defn id-mapping-post-processing-rule [rule-name record-template field1 field2]
  {:name rule-name
   :query `((_/record kiao/hasTemplate ~record-template)
            (_/fv1 obo/BFO_0000050 _/record)
            (_/fv1 kiao/hasTemplate ~field1)
            (_/fv1 iao/IAO_0000219 _/fv1ice)
            (_/fv1ice iao/IAO_0000219 ?bio1)
            (?bio1 rdfs/subClassOf ?bio1ParentCls)
            (_/fv2 obo/BFO_0000050 _/record)
            (_/fv2 kiao/hasTemplate ~field2)
            (_/fv2 iao/IAO_0000219 _/fv2ice)
            (_/fv2ice iao/IAO_0000219 ?bio2)
            (?bio2 rdfs/subClassOf ?bio2ParentCls))
   :post-process (fn [bindings]
                   (let [bio1 (get bindings '?bio1)
                         bio2 (get bindings '?bio2)
                         bio1SuperCls (get bindings '?bio1ParentCls)
                         bio2SuperCls (get bindings '?bio2ParentCls)
                         relation-1to2 (determine-bio-relation bio1SuperCls 
                                                               bio2SuperCls)]
                     (and bio1 bio2 relation-1to2
                          `((~bio1 ~relation-1to2 ~bio2))))) })


(defn de-ice [ice new-base]
  (let [id (second (re-find #"^(.*)_ICE$" (name ice)))]
    (and id
         (symbol new-base id))))

;; filters out the is_a relations - only includes those marked as "exact" - 
;; creates "PRO relation entity" triples where the relation is owl/sameAs if 
;; the entity is a protein and kiao/hasIndirectTemplate if the entity is a gene
;;(defn pro-exact-processing-rule [rule related-supercls relation]
;;  (assoc rule :query 
;;    '((_/record kiao/hasTemplate ~record-template)
;;       (_/fv1 obo/BFO_0000050 _/record)
;;       (_/fv1 kiao/hasTemplate ~field1)
;;       (_/fv1 iao/IAO_0000219 ?fv1ice)
;;       (_/fv2 obo/BFO_0000050 _/record)
;;       (_/fv2 kiao/hasTemplate ~field2)
;;       (_/fv2 iao/IAO_0000219 _/fv2ice)
;;       (_/fv2ice iao/IAO_0000219 ?bio2)
;;       (?bio2 rdfs/subClassOf ~related-supercls)
;;       (_/fv3 obo/BFO_0000050 _/record)
;;       (_/fv3 kiao/hasTemplate 'pr/prmappingTypeDataField1)
;;       (_/fv3 iao/IAO_0000219 "exact")))
;;  (assoc rule :post-process (fn [bindings]
;;                              (let [pro (de-ice (get bindings '?fv1ice) "pro")
;;                                   bio2 (get bindings '?bio2)]
;;                                (and pro bio2 
;;                                  `((~pro ~relation ~bio2)))))))

;;!!!
;;record-template looks like it might always be null? not sure.
;;!!!
(defn pro-exact-processing-rule [rule-name record-template field1 field2]
  {:name rule-name
   :query `((_/record kiao/hasTemplate ~record-template)
       (_/f1 obo/BFO_0000050 _/record)
       (_/f1 kiao/hasTemplate ~field1)
       (_/f1 iao/IAO_0000219 ?f1ice)
       (_/f2 obo/BFO_0000050 _/record)
       (_/f2 kiao/hasTemplate ~field2)
       (_/f2 iao/IAO_0000219 ?f2ice)
       (_/f3 obo/BFO_0000050 _/record)
       (_/f3 kiao/hasTemplate iaopr/prmappingTypeDataField1)
       (_/f3 iao/IAO_0000219 "exact"))
   :post-process (fn [bindings]
                   (let [pro (de-ice (get bindings '?f1ice) "pr")
                         related-ice (get bindings '?f2ice)
                         related-bio-concept (de-ice related-ice (get-de-ice-ns related-ice))
                         relation (determine-pro-bio-relation related-ice)]
                     (and pro related-bio-concept relation
                       `((~pro ~relation ~related-bio-concept))))) })



(defn filter-refseq-rule [rule]
  (assoc rule 
         :query (concat (:query rule) 
               '((?fv3 obo/BFO_0000050 _/record)
                 (?fv3 kiao/hasTemplate iaoeg/egstatusDataField1)
									(:union ((?fv3 iao/IAO_0000219 "VALIDATED"))
									        ((?fv3 iao/IAO_0000219 "REVIEWED")))
               ))))


;; due to the processing query above, if there is a protein field in the id 
;; mapping, list it as the first field argument and put the gene field (or 
;; whatever the other field is) second.
(def ^:dynamic *id-mapping-post-processing-rules*
     (list
       
       ;; map PRO IDs to Uniprot IDs - sameAs
       (pro-exact-processing-rule 'pro-to-protein-exact-id-mapping
                             'iaopr/prProMappingRecordSchema1
                             'iaopr/prproteinOntologyIdDataField1
                             'iaopr/prtargetRecordIdDataField1)
       
       
       
       
       ;; WORKS
       ;; map Entrez Gene IDs to protein RefSeq IDs 
       ;; from the Entrez Gene gene2accession file
;;       (filter-refseq-rule (id-mapping-post-processing-rule 'eg-to-protein-refseq-id-mapping
;;                           'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1
;;                           'iaoeg/egprotein_accession_dot_versionDataField1
;;                           'iaoeg/eggeneIDDataField1))


       ;; WORKS
       ;; map Entrez Gene IDs to genomic RefSeq IDs 
       ;; from the Entrez Gene gene2accession file
;;       (filter-refseq-rule (id-mapping-post-processing-rule 'eg-to-genomic-refseq-id-mapping
;;                   'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1
;;                   'iaoeg/eggeneIDDataField1
;;                   'iaoeg/eggenomic_nucleotide_accession_dot_versionDataField1))
       
       
       ;; WORKS
       ;; map Entrez Gene IDs to RNA RefSeq IDs 
       ;; from the Entrez Gene gene2accession file
;;       (filter-refseq-rule (id-mapping-post-processing-rule 'eg-to-rna-refseq-id-mapping
;;                        'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1
;;                        'iaoeg/egRNA_nucleotide_accession_dot_versionDataField1
;;                       'iaoeg/eggeneIDDataField1))
       
       
      
       ;; WORKS
       ;; map HPRD IDs to EntrezGene gene IDs from the HPRD data file
;;       (id-mapping-post-processing-rule 'hprd-to-entrezgene-id-mapping
;;                                'iaohprd/hprdHprdIdMappingsTxtFileDataSchema1
;;                                'iaohprd/hprdhprdIDDataField1
;;                                'iaohprd/hprdentrezGeneIDDataField1)

       
       ;; KEGG gene ID to external references (only EG at this point) 
       ;; from the KEGG GeneIDList file
;;       (id-mapping-post-processing-rule 'kegg-gene-id-mapping
;;                                'iaokegg/keggKeggGeneIdListFileDataSchema1
;;                                'iaokegg/kegginternalKeggGeneIDDataField1
;;                                'iaokegg/keggexternalGeneIDDataField1)
       
       ;; WORKS
       ;; MGI to Entrez Gene ID from MGIEntrezGene.rpt file
;;       (id-mapping-post-processing-rule 'mgi-entrezgene-id-mapping
;;                                'iaomgi/mgiMGIEntrezGeneFileDataSchema1
;;                                'iaomgi/mgimgiAccessionIDDataField1
;;                                'iaomgi/mgientrezGeneIDDataField1)
       ;; WORKS
       ;; MGI to RefSeq ID from MRKSequence.rpt file
;;      (id-mapping-post-processing-rule 'mgi-refseq-id-mapping
;;                               'iaomgi/mgiMRKSequenceFileDataSchema1
;;                                'iaomgi/mgirefSeqAccessionIDsDataField1
;;                                'iaomgi/mgimgiAccessionIDDataField1)
       
       ;; WORKS
       ;; MGI to UniProt ID from MRKSwissprot.rpt file
;;       (id-mapping-post-processing-rule 'mgi-uniprot-id-mapping
;;                                'iaomgi/mgiMRKSwissProtFileDataSchema1
;;                                'iaomgi/mgiswissProtAccessionIDsDataField1
;;                                'iaomgi/mgimgiAccessionIDDataField1)

       ;; WORKS
       ;; PharmGKB to EntrezGene ID from PharmGKB genes file
;;       (id-mapping-post-processing-rule 'pharmgkb-entrezgene-id-mapping
;;                             'iaopharmgkb/pharmgkbPharmGkbGeneFileRecordSchema1
;;                             'iaopharmgkb/pharmgkbaccessionIdDataField1
;;                             'iaopharmgkb/pharmgkbentrezGeneIdDataField1)
       
       ;; WORKS
       ;; PharmGKB to UniProt ID from PharmGKB genes file
;;       (id-mapping-post-processing-rule 'pharmgkb-uniprot-id-mapping
;;                             'iaopharmgkb/pharmgkbPharmGkbGeneFileRecordSchema1
;;                             'iaopharmgkb/pharmgkbuniprotIdDataField1
;;                             'iaopharmgkb/pharmgkbaccessionIdDataField1)
  
       ;; WORKS
       ;; Transfac Gene to EntrezGene ID from TRANSFAC gene.dat file
;;       (id-mapping-post-processing-rule 'transfac-entrezgene-id-mapping
;;                  'iaotransfac/transfacTransfacGeneDatFileDataSchema1
;;                  'iaotransfac/transfactransfacGeneIDDataField1
;;                  'iaotransfac/transfacentrezGeneDatabaseReferenceIDDataField1)
       
       ;; WORKS
       ;; Transfac Gene to MGI ID from TRANSFAC gene.dat file
;;       (id-mapping-post-processing-rule 'transfac-mgi-id-mapping
;;                         'iaotransfac/transfacTransfacGeneDatFileDataSchema1
;;                         'iaotransfac/transfactransfacGeneIDDataField1
;;                         'iaotransfac/transfacmgiDatabaseReferenceIDDataField1)
       
              
       ;; WORKS
       ;; map DIP IDs to external references from the DIP data file
;;       (id-mapping-post-processing-rule 'dip-id-mapping
;;                                'iaodip/dipDipInteractorSchema1
;;                                'iaodip/dipinteractorIDDataField1
;;                                'iaodip/dipdbXReferenceIDsDataField1)
       
        ;; WORKS
       ;; map HGNC Symbols (which are unique) to EntrezGene IDs 
       ;; from the HGNC download data file
;;       (id-mapping-post-processing-rule 'hgnc-symbol-mapping
;;                                'iaohgnc/hgncHgncDownloadFileDataSchema1
;;                                'iaohgnc/hgnchgncGeneSymbolDataField1
;;                                'iaohgnc/hgncentrezGeneIDDataField1)
       
       

       

      
       
       
       ;; WORKS
       ;; map HPRD IDs to RefSeq gene IDs from the HPRD data file
;;       (id-mapping-post-processing-rule 'hprd-to-refseq-gene-id-mapping
;;                                'iaohprd/hprdHprdIdMappingsTxtFileDataSchema1
;;                                'iaohprd/hprdhprdIDDataField1
;;                                'iaohprd/hprdnucleotideAccessionDataField1)
       ;; WORKS
       ;; map HPRD IDs to RefSeq protein IDs from the HPRD data file
;;       (id-mapping-post-processing-rule 'hprd-to-refseq-protein-id-mapping
;;                                'iaohprd/hprdHprdIdMappingsTxtFileDataSchema1
;;                                'iaohprd/hprdhprdIDDataField1
;;                                'iaohprd/hprdproteinAccessionDataField1)
       
       ;; WORKS
       ;; map HPRD IDs to UniProt protein IDs from the HPRD data file
;;       (id-mapping-post-processing-rule 'hprd-to-uniprot-id-mapping
;;                                'iaohprd/hprdHprdIdMappingsTxtFileDataSchema1
;;                                'iaohprd/hprdhprdIDDataField1
;;                                'iaohprd/hprdswissProtIDsDataField1)
       
       ;; FAILS       
       ;; Transfac Gene to EMBL ID from TRANSFAC gene.dat file
;;       (id-mapping-post-processing-rule 'transfac-embl-id-mapping
;;                        'iaotransfac/transfacTransfacGeneDatFileDataSchema1
;;                        'iaotransfac/transfactransfacGeneIDDataField1
;;                        'iaotransfac/transfacemblDatabaseReferenceIDsDataField1)

       ;; FAILS - due to sequence version number for embl ids appended in the uniprot id mappings file. This will be removed during the next build, and then this rule should function properly
       ;; map UniProt IDs to EMBL Ids 
       ;; from the UniProt idmappings_selected.tab file
;;       (id-mapping-post-processing-rule 'uniprot-to-embl-id-mapping
;;                             'iaouniprot/uniprotUniProtIDMappingFileDataSchema1
;;                             'iaouniprot/uniprotuniProtAccessionIDDataField1
;;                             'iaouniprot/uniprotemblIDsDataField1)

       ;; WORKS
       ;; map IRefWeb IDs for interactor A to external references 
       ;; from the IRefWeb data file
;;       (id-mapping-post-processing-rule 'irefweb-interactor-a-id-mapping
;;                             'iaoirefweb/irefwebIRefWebMitab4_0FileDataSchema1
;;                             'iaoirefweb/irefwebirefwebInteractorID_ADataField1
;;                             'iaoirefweb/irefwebdatabaseReferences_ADataField1)
       
       ;; WORKS
       ;; map IRefWeb IDs for interactor B to external references 
       ;; from the IRefWeb data file
;;       (id-mapping-post-processing-rule 'irefweb-interactor-b-id-mapping
;;                             'iaoirefweb/irefwebIRefWebMitab4_0FileDataSchema1
;;                             'iaoirefweb/irefwebirefwebInteractorID_BDataField1
;;                             'iaoirefweb/irefwebdatabaseReferences_BDataField1)
       
       ;; WORKS
       ;; map UniProt IDs to Entrez Gene Ids 
       ;; from the UniProt idmappings_selected.tab file
;;       (id-mapping-post-processing-rule 'uniprot-to-eg-id-mapping
;;                             'iaouniprot/uniprotUniProtIDMappingFileDataSchema1
;;                             'iaouniprot/uniprotuniProtAccessionIDDataField1
;;                             'iaouniprot/uniprotentrezGeneIDsDataField1)

       
       
       ))

