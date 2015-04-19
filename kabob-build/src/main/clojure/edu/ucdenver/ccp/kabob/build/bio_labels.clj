;; Adds labels to bio entities in the form of hasId properties
(ns edu.ucdenver.ccp.kabob.build.bio-labels
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))



;; (defn has-id-property-hierarchy []
;;  {:name "hasId-property-hierarchy"
;;   ;; dummy query designed to return nothing - 
;;   ;; so that we can use same serialize-rule-output framework
;;   ;;  but specify the output directly
;;   :query '((rdfs/label rdfs/label ?o))
;;   :post-process 
;;   (fn [bindings]
;;     (and '((kiao/hasId rdfs/subPropertyOf rdfs/label)
;;            (kiao/hasEntrezGeneId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasUniProtId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasSecondaryUniProtId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasMgiId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasHgncId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasHprdId rdfs/subPropertyOf kiao/hasId)
;;            (kiao/hasRefSeqGeneId rdfs/subPropertyOf kiao/hasId)))) })


(def ^:dynamic *has-id-property-hierarchy* 
  '((kiao/hasId rdfs/subPropertyOf rdfs/label)
    (kiao/hasEntrezGeneId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasUniProtId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasUniProtEntryName rdfs/subPropertyOf kiao/hasId)
    (kiao/hasSecondaryUniProtId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasMgiId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasHgncId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasHprdId rdfs/subPropertyOf kiao/hasId)
    (kiao/hasRefSeqGeneId rdfs/subPropertyOf kiao/hasId)
    
    (kiao/hasGeneSymbol rdfs/subPropertyOf rdfs/label)))

(defn de-ice [ice new-base]
  (let [id (second (re-find #"^(.*)_ICE$" (name ice)))]
    (and id
         (symbol new-base id))))


(defn eg-id-from-ice [ice]
  (let [id (second (re-find #"^EG_(.*)_ICE$" (name ice)))]
    (and id)))

;; Processes records from the EntrezGene gene_info file.
;; Attaches the entrez gene ID and gene symbol to the EntrezGene 
;; gene bioworld entity
(defn entrezgene-label-rules []
  {:name "entrezgene-labels"
   :query `((_/record kiao/hasTemplate iaoeg/egEntrezGeneInfoFileDataSchema1)
             (_/fv ro/part_of _/record)
             (_/fv kiao/hasTemplate iaoeg/eggeneIDDataField1)
             (_/fv iao/IAO_0000219 ?ice)
             (_/fv2 ro/part_of _/record)
             (_/fv2 kiao/hasTemplate iaoeg/egsymbolDataField1)
             (_/fv2 iao/IAO_0000219 ?symbol)
             (_/fv3 ro/part_of _/record)
             (_/fv3 kiao/hasTemplate iaoeg/egtaxonIDDataField1)
             (_/fv3 iao/IAO_0000219 ?taxonice))
   :post-process 
   (fn [bindings]
     (let [ice (get bindings '?ice)
           bio (de-ice ice "iaoeg")
           egid (eg-id-from-ice ice)
           sym (get bindings '?symbol)
           taxon-bio (de-ice (get bindings '?taxonice) "iaoncbitaxon")]
       (and bio
            `((~bio kiao/hasEntrezGeneId ~egid)
              (~bio kiao/hasGeneSymbol ~sym)
              (~bio ro/located_in ~taxon-bio))))) })


(defn uniprot-id-from-ice [ice]
  (let [id (second (re-find #"^UNIPROT_(.*)_ICE$" (name ice)))]
    (and id)))


;; Processes records from the UniProt dat files. 
;; Attaches the UniProt ID and protein symbol and 
;;   secondary accession IDs to the UniProt protein bioworld entity
(defn uniprot-label-rules []
  {:name "uniprot-labels"
   :query
   `((_/record kiao/hasTemplate iaouniprot/uniprotUniProtDatFileDataSchema1)
     (_/fv ro/part_of _/record)
     (_/fv kiao/hasTemplate iaouniprot/uniprotprimaryUniProtIDDataField1)
     (_/fv iao/IAO_0000219 ?ice)
     ;; not every record has a secondary ID field, 
     ;; so this may need to be queryied for separately.. not sure.
     ;;(_/fv2 ro/part_of _/record)
     ;;(_/fv2 kiao/hasTemplate iaouniprot/uniprotsecondaryUniProtIDsDataField1)
     ;;(_/fv2 iao/IAO_0000219 ?secondary)
     (_/fv3 ro/part_of _/record)
     (_/fv3 kiao/hasTemplate iaouniprot/uniprotuniprotEntryNameDataField1)
     (_/fv3 iao/IAO_0000219 ?entryname)
     (_/fv4 ro/part_of _/record)
     (_/fv4 kiao/hasTemplate iaouniprot/uniprotncbiTaxonomyIDDataField1)
     (_/fv4 iao/IAO_0000219 ?taxonice))
   :post-process 
   (fn [bindings]
     (let [ice (get bindings '?ice)
           bio (de-ice ice "iaouniprot")
           primaryid (uniprot-id-from-ice ice)
           entryname (uniprot-id-from-ice (get bindings '?entryname))
           ;; can have multiple secondary IDs.
           ;; need to figure out how to create 0-to-many triples here
           ;; secondaryids (get bindings '?secondary)
           taxon-bio (de-ice (get bindings '?taxonice) "iaoncbitaxon")]
       (and bio primaryid
            `((~bio kiao/hasUniProtId ~primaryid)
              (~bio kiao/hasUniProtEntryName ~entryname)
              (~bio ro/located_in ~taxon-bio))))) })

(defn mgi-id-from-ice [ice]
  (let [id (second (re-find #"^MGI_(.*)_ICE$" (name ice)))]
    (and id)))

;; Processes records from the MGI MRK_List file. 
;; Attaches the MGI gene ID and gene symbol to the MGI gene bioworld entity
(defn mgigene-label-rules []
  {:name "mgigene-labels"
   :query `((_/record kiao/hasTemplate iaomgi/mgiMRKListFileDataSchema1)
             (_/fv ro/part_of _/record)
             (_/fv kiao/hasTemplate iaomgi/mgimgiAccessionIDDataField1)
             (_/fv iao/IAO_0000219 ?ice)
             (_/fv2 ro/part_of _/record)
             (_/fv2 kiao/hasTemplate iaomgi/mgimarkerSymbolDataField1)
             (_/fv2 iao/IAO_0000219 ?symbol))
   :post-process (fn [bindings]
                   (let [ice (get bindings '?ice)
                         bio (de-ice ice "iaomgi")
                         mgiid (mgi-id-from-ice ice)
                         sym (get bindings '?symbol)]
                     (and bio
                          `((~bio kiao/hasMgiId ~mgiid)
                             (~bio kiao/hasGeneSymbol ~sym))))) })



(defn hgnc-id-from-ice [ice]
  (let [id (second (re-find #"^HGNC_(.*)_ICE$" (name ice)))]
    (and id)))

;; Processes records from the EntrezGene gene_info file. 
;; Attaches the entrez gene ID and gene symbol 
;;  to the EntrezGene gene bioworld entity
(defn hgncgene-label-rules []
  {:name "hgncgene-labels"
   :query `((_/record kiao/hasTemplate iaohgnc/hgncHgncDownloadFileDataSchema1)
             (_/fv ro/part_of _/record)
             (_/fv kiao/hasTemplate iaohgnc/hgnchgncIDDataField1)
             (_/fv iao/IAO_0000219 ?ice)
             (_/fv2 ro/part_of _/record)
             (_/fv2 kiao/hasTemplate iaohgnc/hgnchgncGeneSymbolDataField1)
             (_/fv2 iao/IAO_0000219 ?symbol))
   :post-process (fn [bindings]
                   (let [ice (get bindings '?ice)
                         bio (de-ice ice "iaoeg")
                         hgncid (hgnc-id-from-ice ice)
                         sym (hgnc-id-from-ice (get bindings '?symbol))]
                     (and bio
                          `((~bio kiao/hasHgncGeneId ~hgncid)
                             (~bio kiao/hasGeneSymbol ~sym))))) })


(defn hprd-id-from-ice [ice]
  (let [id (second (re-find #"^HPRD_(.*)_ICE$" (name ice)))]
    (and id)))

;; Processes records from the EntrezGene gene_info file.
;; Attaches the entrez gene ID and gene symbol
;; to the EntrezGene gene bioworld entity
(defn hprdgene-label-rules []
  {:name "hprdgene-labels"
   :query 
   `((_/record kiao/hasTemplate iaohprd/hprdHprdIdMappingsTxtFileDataSchema1)
     (_/fv ro/part_of _/record)
     (_/fv kiao/hasTemplate iaohprd/hprdhprdIDDataField1)
     (_/fv iao/IAO_0000219 ?ice)
     (_/fv2 ro/part_of _/record)
     (_/fv2 kiao/hasTemplate iaohprd/hprdgeneSymbolDataField1)
     (_/fv2 iao/IAO_0000219 ?symbol))
   :post-process 
   (fn [bindings]
     (let [ice (get bindings '?ice)
           bio (de-ice ice "iaohprd")
           hprdid (hprd-id-from-ice ice)
           sym (get bindings '?symbol)]
       (and bio
            `((~bio kiao/hasHprdGeneId ~hprdid)
              (~bio kiao/hasGeneSymbol ~sym))))) })


(def ^:dynamic *bioworld-entity-label-rules*
     (list
       ;;(entrezgene-label-rules)
       (uniprot-label-rules)
       ;;(mgigene-label-rules)
       ;;(hgncgene-label-rules)
       ;;(hprdgene-label-rules)
      ))

