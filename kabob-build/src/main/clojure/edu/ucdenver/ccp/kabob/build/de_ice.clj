(ns edu.ucdenver.ccp.kabob.build.de-ice
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))

;; NOTE: IF THIS CODE IS RESURRECTED:
;; records obo/BFO_0000051 (has_part) field values
;; this code uses field values obo/BFO_0000050 part_of records


(defn de-ice [ice new-base]
  (let [id (second (re-find #"^(.*)_ICE$" (name ice)))]
    (and id
         (symbol new-base id))))

(defn de-ice-post-processing-rule
  "Constructs map that uses values provded by name, query and 
   post-process keys to de-ice query results to biological entities."
  [name field new-ns class recordTemplate]
  {:name name
   :query `((_/record kiao/hasTemplate ~recordTemplate)
            (_/fv obo/BFO_0000050 _/record)
            (_/fv kiao/hasTemplate ~field)
            (_/fv iao/IAO_0000219 ?ice))
   :post-process (fn [bindings]
                   (let [ice (get bindings '?ice)
                         bio (de-ice ice new-ns)]
                     (and bio
                          `((~ice iao/IAO_0000219 ~bio)
                            (~bio rdfs/subClassOf ~class))))) })

(defn get-refseq-superclass [refseq-id]
  (let [prefix (second (re-find #"^REFSEQ_([A-Z][A-Z])_" (name refseq-id)))
        superclassmap {"AC" 'so/SO_0000704, "AP" 'chebi/CHEBI_36080,
                       "NC" 'so/SO_0000704,"NG" 'so/SO_0000704,
                       "NM" 'so/SO_0000234, "NP" 'chebi/CHEBI_36080,
                       "NR" 'so/SO_0000356, "NT" 'so/SO_0000704 ,
                       "NW" 'so/SO_0000704, "NZ" 'so/SO_0000704 ,
                       "XM" 'so/SO_0000234, "XP" 'chebi/CHEBI_36080,
                       "XR" 'so/SO_0000356, "YP" 'chebi/CHEBI_36080,
                       "ZP" 'chebi/CHEBI_36080, "NS" 'so/SO_0000704}]
   
    (and prefix
         (superclassmap prefix))
  ))

(defn refseq-de-ice-post-processing-rule
  "The superclass for a RefSeq ID must be inferred from the first two letters 
   of the ID itself, hence this refseq-specific rule implementation"
  [name field new-ns recordTemplate]
  {:name name
   :query `((_/fv kiao/hasTemplate ~field)
             (_/fv obo/BFO_0000050 _/record)
             (_/record kiao/hasTemplate ~recordTemplate)
             (_/fv iao/IAO_0000219 ?ice))
   :post-process (fn [bindings]
                   (let [ice (get bindings '?ice)
                         bio (de-ice ice new-ns)
                         superclass (get-refseq-superclass bio)]
                     (and bio
                          `((~ice iao/IAO_0000219 ~bio)
                             (~bio rdfs/subClassOf ~superclass))))) })



;;new way to list ice post processing rules
(def ^:dynamic *de-ice-post-processing-rules*
     (list
       
       ;; RefSeq
;;       (refseq-de-ice-post-processing-rule 'refseq-ice
;;                          'iaorefseq/refseqrefseqIdDataField1
;;                          "refseq"
;;                          'iaorefseq/refseqRefSeqReleaseCatalogFileDataSchema1)

       
       ;; processes Entrez Gene IDs found in the gene_info file
      (de-ice-post-processing-rule 'eg-ice
                                'iaoeg/eggeneIDDataField1 
                                "eg" 
                                'so/SO_0000704
                                'iaoeg/egEntrezGeneInfoFileDataSchema1)
      
      ;; processes UniProt IDs found as part of the uniprot dat files 
      ;; NOTE: currently only swissprot is being processed 
      ;;       so this will cover swissprot proteins only
;;      (de-ice-post-processing-rule 'uniprot-ice
;;                                'iaouniprot/uniprotprimaryUniProtIDDataField1 
;;                                "uniprot" 
;;                                'chebi/CHEBI_36080
;;                                'iaouniprot/uniprotUniProtDatFileDataSchema1)

      ;; processes MGI IDs found in the MRK_List2.rpt file
;;      (de-ice-post-processing-rule 'mgi-ice
;;                                'iaomgi/mgimgiAccessionIDDataField1 
;;                                "mgi" 
;;                                'so/SO_0000704
;;                                'iaomgi/mgiMRKListFileDataSchema1)

      ;; processes HGNC IDs found in the HGNC download file
;;      (de-ice-post-processing-rule 'hgnc-ice
;;                                'iaohgnc/hgnchgncGeneSymbolDataField1 
;;                                "hgnc" 
;;                                'so/SO_0000704
;;                                'iaohgnc/hgncHgncDownloadFileDataSchema1)
      
      ;; processes HPRD IDs found in the HPRD mappings.txt file
;;      (de-ice-post-processing-rule 'hprd-ice
;;                                'iaohprd/hprdhprdIDDataField1 
;;                                "hprd" 
;;                                'chebi/CHEBI_36080
;;                                'iaohprd/hprdHprdIdMappingsTxtFileDataSchema1)
      
      ;; KEGG      
      ;; processes kegg gene IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'kegg-gene-ice
;;                                'iaokegg/kegginternalKeggGeneIDDataField1 
;;                                "kegg" 
;;                                'so/SO_0000704
;;                                'iaokegg/keggKeggGeneIdListFileDataSchema1)

      ;; DIP
      ;; processes DIP interactor (node) IDs
;;      (de-ice-post-processing-rule 'dip-interactor-ice
;;                                'iaodip/dipinteractorIDDataField1
;;                                "dip"
;;                                'chebi/CHEBI_36080
;;                                'iaodip/dipDipInteractorSchema1)   
      
      ;; IREFWEB - TODO - should read from the output of the 
      ;;IRefWebMitabFileInteractorIterator to avoid generation of duplicate 
      ;;references.
      
      ;; processes interactor A IDs
;;      (de-ice-post-processing-rule 'irefweb-id-a-ice
;;                             'iaoirefweb/irefwebirefwebInteractorID_ADataField1
;;                             "irefweb"
;;                             'chebi/CHEBI_36080
;;                             'iaoirefweb/irefwebIRefWebMitab4_0FileDataSchema1)
     
      ;; processes interactor B IDs
;;      (de-ice-post-processing-rule 'irefweb-id-b-ice
;;                             'iaoirefweb/irefwebirefwebInteractorID_BDataField1
;;                             "irefweb"
;;                             'chebi/CHEBI_36080
;;                             'iaoirefweb/irefwebIRefWebMitab4_0FileDataSchema1)
      
      ;; PHARMGKB
;;      (de-ice-post-processing-rule 'pharmgkb-genes-ice
;;                            'iaopharmgkb/pharmgkbaccessionIdDataField1
;;                            "pharmgkb"
;;                            'so/SO_0000704
;;                            'iaopharmgkb/pharmgkbPharmGkbGeneFileRecordSchema1)
      
      ;; TRANSFAC
      ;; processes transfac gene ids 
;;      (de-ice-post-processing-rule 'transfac-gene-ice
;;                           'iaotransfac/transfactransfacGeneIDDataField1
;;                           "transfac"
;;                           'so/SO_0000704
;;                           'iaotransfac/transfacTransfacGeneDatFileDataSchema1)
      
      ;; EMBL
;;      (de-ice-post-processing-rule 'embl-gene-ice
;;                          'iaoembl/emblemblIdDataField1
;;                          "embl"
;;                          'so/SO_0000704
;;                          'iaoembl/emblEmblAccToTaxIdMappingTxtFileDataSchema1)
      
       ;; RULES BELOW MAY OR MAY NOT BE NEEDED IN THE FUTURE
     
          ;; processes transfac matrix ids ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'transfac-matrix-ice
;;                     'iaotransfac/transfacinternalMatrixAccessionIDDataField1
;;                     "transfac"
;;                     'so/SO_0000704
;;                     'iaotransfac/transfacTransfacMatrixDatFileDataSchema1)

     
            ;; PREMOD
      ;; processes premod ids ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'premod-name-ice
;;                            'iaoomim/premodpremodIDDataField1
;;                            "premod"
;;                            'so/SO_0000704
;;                            'iaopremod/premodPReModModuleTabFileDataSchema1)
     
     
            ;; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'pharmgkb-diseases-ice
;;                       'iaopharmgkb/pharmgkbaccessionIdDataField1
;;                       "pharmgkb"
;;                       'so/SO_0000704
;;                       'iaopharmgkb/pharmgkbPharmGkbDiseaseFileRecordSchema1)    
     
;;      (de-ice-post-processing-rule 'pharmgkb-drugs-ice
;;                          'iaopharmgkb/pharmgkbaccessionIdDataField1
;;                          "pharmgkb"
;;                          'so/SO_0000704
;;                          'iaopharmgkb/pharmgkbPharmGkbDrugFileRecordSchema1)
     
            ;; OMIM
      ;; processes mim numbers ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'omim-mimnumber-ice
;;                                'iaoomim/omimmimNumberDataField1
;;                                "omim"
;;                                'so/SO_0000704
;;                                'iaoomim/omimOmimTxtFileDataSchema1)
     
     ;; processes DIP interaction IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'dip-interaction-ice
;;                                'iaodip/dipinteractionIDDataField1
;;                                "dip"
;;                                'so/SO_0000704
;;                                'iaodip/dipDipYYYYMMDDFileDataSchema1)
     
      ;; GAD
      ;; processes GAD IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'gad-id-ice
;;                        'iaogad/gadgadIDDataField1
;;                        "gad"
;;                        'so/SO_0000704
;;                        'iaogad/gadGeneticAssociationDbAllTxtFileDataSchema1)
     
      ;; HOMOLOGENE
      ;; processes Group IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'homologene-group-id-ice
;;                             'iaohomologene/homologenegroupIDDataField1
;;                             "homologene"
;;                             'so/SO_0000704
;;                             'iaohomologene/homologeneHomoloGeneGroupSchema1)
     
      ;; INTERPRO
      ;; processes interpro IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'interpro-id-ice
;;                        'iaointerpro/interprointerProIDDataField1
;;                        "interpro"
;;                        'so/SO_0000704
;;                        'iaointerpro/interproInterProNamesDatFileDataSchema1)

      ;; REACTOME
   ;; processes reactome pathway IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'reactome-pathway-ice
;;          'iaoreactome/reactomereactionIDDataField1
;;          "reactome"
;;          'so/SO_0000704
;;          'iaoreactome/reactomeReactomeUniprot2PathwayStidTxtFileDataSchema1)

      ;; processes kegg pathway IDs ; TODO - find correct subclass value
;;      (de-ice-post-processing-rule 'kegg-pathway-ice
;;                               'iaokegg/keggkeggPathwayIDDataField1
;;                                "kegg"
;;                                'so/SO_0000704
;;                                'iaokegg/keggKeggMapTitleTabFileDataSchema1)


     
      ;; REFSEQ RULES BELOW SHOULD NO LONGER BE NEEDED
           
      ;; processes RNA RefSeq IDs found in the Entrez Gene gene2accession file
;;      (de-ice-post-processing-rule 'refseq-rna-ice
;;                      'iaoeg/egRNA_nucleotide_accession_dot_versionDataField1
;;                      "refseq"
;;                      'so/SO_0000356
;;                      'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1)
     
   ;; processes protein RefSeq IDs found in the Entrez Gene gene2accession file
;;      (de-ice-post-processing-rule 'refseq-protein-ice
;;                        'iaoeg/egprotein_accession_dot_versionDataField1
;;                        "refseq"
;;                        'chebi/CHEBI_36080
;;                        'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1)
     
   ;; processes genomic RefSeq IDs found in the Entrez Gene gene2accession file
;;      (de-ice-post-processing-rule 'refseq-genomic-ice
;;                  'iaoeg/eggenomic_nucleotide_accession_dot_versionDataField1
;;                  "refseq"
;;                  'so/SO_0000704
;;                  'iaoeg/egEntrezGene2AccessionOrRefseqFileDataSchema1)
 
     
     
      ))

