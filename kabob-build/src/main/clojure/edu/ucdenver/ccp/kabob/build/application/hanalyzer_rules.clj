(ns edu.ucdenver.ccp.kabob.build.application.hanalyzer-rules
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))

;;(def ^:dynamic *graph-name* (str "test-graph"))


(def ^:dynamic *hanalyzer-node-construct-rules*
     (list
       ;; currently requires the ICE KB including output from de-ice rules, id-mapping rules, hanalyer-node-generation rules
      {:name 'skolem-gene-nodes
       :query '((?protein)) ;; look for proteins that don't have an accompanying kiao/hasIndirectTemplate link
       :post-process
       (fn [bindings]
         )
       }
       ))


(defn create-hanalyzer-edge-stmts [han-node1 han-node2 evidence-record source-name]
  (and han-node1 han-node2 evidence-record source-name 
       (let [edge-name (gensym "hanedge")] 
         '(((symbol "iaohanalyzer" ~edge-name) rdf/type iaohanalyzer/HanalyzerEdge)
            ((symbol "iaohanalyzer" ~edge-name) kiao/connects ~han-node1)
            ((symbol "iaohanalyzer" ~edge-name) kiao/connects ~han-node2)
            ((symbol "iaohanalyzer" ~edge-name) iao/IOA_0000219 ~evidence-record)
            ((symbol "iaohanalyzer" ~edge-name) kiao/hasSource ~source-name)
            ))))

(def ^:dynamic *hanalyzer-edge-construct-rules*
     (list
       ;; currently requires the ICE KB including output from de-ice rules, id-mapping rules, hanalyer-node-generation rules
      {:name "hanalyzer-edges-dip"
       :query 
       '(
          ;; there is a DIP interaction record 
          (?dip-interaction-record kiao/hasTemplate iaodip/dipDipYYYYMMDDFileDataSchema1)
          ;; that has a dipinteractorID_A data field with value ?dip-interactor-a-ice
          (_/f1 ro/part_of dip-interaction-record)
          (_/f1 kiao/hasTemplate iaodip/dipinteractorID_ADataField1)
          (_/f1 iao/IAO_0000219 _/dip-interactor-a-ice)
          ;; the dip interactor A ice should denote a corresponding bio (non-ice) concept
          (_/dip-interactor-a-ice iao/IAO_000219 _/dip-interactor-a)
          ;; there should be a hanalyzer node that denotes a gene that is the indirect template for dip-interactor-a
          (_/dip-interactor-a kiao/hasIndirectTemplate _/gene-a) 
          (?han-node-a iao/IAO_0000219 _/gene-a)
          
          ;; REPEAT the above for the 2nd interactor
          ;; the record also has a dipinteractorID_B data field with value ?dip-interactor-b-ice
          (_/f2 ro/part_of dip-interaction-record)
          (_/f2 kiao/hasTemplate iaodip/dipinteractorID_BDataField1)
          (_/f2 iao/IAO_0000219 ?dip-interactor-b-ice)
          ;; the dip interactor B ice should denote a corresponding bio (non-ice) concept
          (_/dip-interactor-b-ice iao/IAO_000219 ?dip-interactor-b)
           ;; there should be a hanalyzer node that denotes a gene that is the indirect template for dip-interactor-b
          (_/dip-interactor-b kiao/hasIndirectTemplate _/gene-b) 
          (?han-node-b iao/IAO_0000219 _/gene-b)
          
          ;; we also need to extract the experimental detection method
          ;; the interaction record references an experiment record
          (_/f3 ro/part_of dip-interaction-record)
          (_/f3 kiao/hasTemplate iaodip/dipinteractionIDDataField1)
          (_/f3 iao/IAO_0000219 _/dip-experiment-id-ice)
          ;; there is an experiment record that has the dip-experiment-id-ice as its id value
          (_/dip-experiment-record kiao/hasTemplate iaodip/dipDipInteractionExperimentSchema1)
          (_/f4 ro/part_of dip-experiment-record)
          (_/f4 kiao/hasTemplate ioadip/dipexperimentIDDataField1)
          (_/f4 iao/IAO_0000219 _/dip-experiment-id-ice)
          ;; the experiment record also has a field referencing the experimental detection method
          (_/f5 ro/part_of dip-experiment-record)
          (_/f5 kiao/hasTemplate ioadip/dipinteractionDetectionMethodDataField1)
          (_/f5 iao/IAO_0000219 ?detection-method-ice))
       :post-process 
       (fn [bindings]
         (let [source-name (get bindings '?detection-method-ice)
               han-node1 (get bindings '?han-node-a)
               han-node2 (get bindings '?han-node-b)
               evidence-record (get bindings '?dip-interaction-record)]
           (create-hanalyzer-edge-stmts han-node1 han-node2 evidence-record source-name)))} 
      ))





;; dip ppi
;; proteins have interpro domain
;; goa
;; gad
;; irefweb ppi
;; kegg
;; mgi - interpro, mgi - pheno
;; eg - omim, eg - pubmed?, 
;; homologene
;; pharmgkb
;; premod
;; reactome
;; transfac
