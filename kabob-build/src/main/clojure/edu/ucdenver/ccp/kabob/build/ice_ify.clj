(ns edu.ucdenver.ccp.kabob.build.ice-ify
  (use edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.unify
       edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sparql
       edu.ucdenver.ccp.kr.rule))


(defn make-ice [s new-base]
  (symbol new-base (str (name s) "_ICE")))


;; {:name cl-ice 
;;  :query '((?subclass rdfs/subClassOf http://purl.org/obo/owl//CL#CL_0000000))
;;  :postprocess 
;;  (fn [bindings] 
;;                 (let [subclass (get bindings '?subclass)] 
;;                   (and (not (anon? subclass)) 
;;           `((~(make-ice subclass "http://kabob.ucdenver.edu/iao/cc/") 
;;              ioa/IAO_0000219 ~subclass)))))


;; this function defines a rule used to ice'ify at URI. 
;;   In kabob we will use these rules to add "ICE denotes ID" triples for 
;; the ontologies that are imported in via owl files.
(defn ice-ify-post-processing-rule [name root-node new-ns]
  {:name name
   :query `((?subclass rdfs/subClassOf ~root-node))
   :post-process 
   (fn [bindings]
     (let [subclass (get bindings '?subclass)] 
       (when (not (anon? subclass)) 
         `((~(make-ice subclass new-ns) iao/IAO_0000219 ~subclass))))) })


;; The PRO ontology contains terms from GO and ChEBI which do not need to be 
;; ice'ified again here so this rule, specific to processing PRO, restricts 
;; itself to ice'ifying terms that start with "PR_"
(defn pro-ice-ify-post-processing-rule [rule-name root-node new-ns]
  {:name rule-name
   :query `((?subclass rdfs/subClassOf ~root-node))
   :post-process 
   (fn [bindings]
     (let [subclass (get bindings '?subclass)] 
       (prn (str "SubClass:" subclass))
       (prn (str "Anon? subclass: "  (anon? subclass)))
       (prn (str "Subclass long name: "  (str (sym-to-long-name subclass))))
       (prn (str "Subclass name: "  (str (name subclass))))
       (prn (str "Subclass namespace: "  (str (namespace subclass))))
       (prn (str "namespace is pr: "  (.equals (str (namespace subclass)) "pr")))
       (prn (str "subclass class: "  (.getName (.getClass subclass))))
       (when (and 
               (not (anon? subclass)) 
               (.equals (str (namespace subclass)) "pr")) 
         `((~(make-ice subclass new-ns) iao/IAO_0000219 ~subclass))))) })



;; defines a list of ice'ify rules, one for each ontology imported into kabob
;; bfo-1.1.owl, chebi.owl, go-bp.owl, go-mf.owl, mi.owl, ncbi-taxonomy.owl,
;;   ro.owl, bto.owl, cl.owl, go-cc.owl, iao.owl, mpheno.owl, pr.owl, so.owl
;; IAO and RO are the only two ontologies to not have a rule. 
(def ^:dynamic *ice-ify-post-processing-rules*
  (list
    
    ;; mi:alias type
    (ice-ify-post-processing-rule 'mi-at-ice
                                  'mi/MI_0300 
                                  "iaomi")
    
    ;; mi:attribute name
    (ice-ify-post-processing-rule 'mi-an-ice
                                  'mi/MI_0590 
                                  "iaomi")
    
    ;; mi:biological role
    (ice-ify-post-processing-rule 'mi-br-ice
                                  'mi/MI_0500 
                                  "iaomi")
    
    ;; mi:cross-reference type
    (ice-ify-post-processing-rule 'mi-crt-ice
                                  'mi/MI_0353 
                                  "iaomi")
    
    ;; mi:curation quality
    (ice-ify-post-processing-rule 'mi-cq-ice
                                  'mi/MI_0954
                                  "iaomi")
    ;; mi:curation coverage
    (ice-ify-post-processing-rule 'mi-cc-ice
                                  'mi/MI_0956
                                  "iaomi")
    ;; mi:curation depth
    (ice-ify-post-processing-rule 'mi-cd-ice
                                  'mi/MI_0955
                                  "iaomi")
    
    ;; mi:database citation
    (ice-ify-post-processing-rule 'mi-dc-ice
                                  'mi/MI_0444 
                                  "iaomi")
    
    ;; mi:experimental preparation
    (ice-ify-post-processing-rule 'mi-ep-ice
                                  'mi/MI_0346 
                                  "iaomi")
    
    ;; mi:experimental role
    (ice-ify-post-processing-rule 'mi-er-ice
                                  'mi/MI_0495 
                                  "iaomi")
    
    ;; mi:feature detection method
    (ice-ify-post-processing-rule 'mi-fdm-ice
                                  'mi/MI_0003 
                                  "iaomi")
    
    ;; mi:feature range status
    (ice-ify-post-processing-rule 'mi-frs-ice
                                  'mi/MI_0333 
                                  "iaomi")
    
    ;; mi:feature type
    (ice-ify-post-processing-rule 'mi-ft-ice
                                  'mi/MI_0116 
                                  "iaomi")
    
    ;; mi:interaction detection method
    (ice-ify-post-processing-rule 'mi-idm-ice
                                  'mi/MI_0001 
                                  "iaomi")
    
    ;; mi:interaction type
    (ice-ify-post-processing-rule 'mi-interactiontype-ice
                                  'mi/MI_0190 
                                  "iaomi")
    
    ;; mi:interactor type
    (ice-ify-post-processing-rule 'mi-interactortype-ice
                                  'mi/MI_0313 
                                  "iaomi")
    
    ;; mi:parameter type
    (ice-ify-post-processing-rule 'mi-pt-ice
                                  'mi/MI_0640 
                                  "iaomi")
    
    ;; mi:parameter unit
    (ice-ify-post-processing-rule 'mi-pu-ice
                                  'mi/MI_0647 
                                  "iaomi")
    
    ;; mi:participant identifcation method
    (ice-ify-post-processing-rule 'mi-pim-ice
                                  'mi/MI_0002 
                                  "iaomi")
    
    ;; cell:cell
    (ice-ify-post-processing-rule 'cl-ice
                                  'cl/CL_0000000 
                                  "iaocl")
    ;; chebi:subatomic particle
    (ice-ify-post-processing-rule 'chebi-sp-ice
                                  'chebi/CHEBI_36342 
                                  "iaochebi") 
    ;; chebi:role
    (ice-ify-post-processing-rule 'chebi-r-ice
                                  'chebi/CHEBI_50906 
                                  "iaochebi") 
    ;; chebi:chemical entity
    (ice-ify-post-processing-rule 'chebi-ce-ice
                                  'chebi/CHEBI_24431 
                                  "iaochebi") 
 
    ;; go:mf
    (ice-ify-post-processing-rule 'go-mf-ice
                                  'go/GO_0003674 
                                  "iaogo")
    
    ;; go:bp
    (ice-ify-post-processing-rule 'go-bp-ice
                                  'go/GO_0008150 
                                  "iaogo")
    
    ;; go:cc (this should also capture part of the pr ontology. 
    ;; GO:0032991 "macromolecular complex" is a root node for
    ;;   much of the pr ontology)
    (ice-ify-post-processing-rule 'go-cc-ice
                                  'go/GO_0005575 
                                  "iaogo")
    
    
    ;; mpheno:mammalian phenotype
    (ice-ify-post-processing-rule 'mpheno-ice
                                  'mpheno/MP_0000001 
                                  "iaompheno")
    ;; so:sequence_attribute
    (ice-ify-post-processing-rule 'so-sa-ice
                                  'so/SO_0000400 
                                  "iaoso")
    
    ;; so:sequence_feature
    (ice-ify-post-processing-rule 'so-sf-ice
                                  'so/SO_0000110 
                                  "iaoso")
    
    ;; so:sequence_collection
    (ice-ify-post-processing-rule 'so-sc-ice
                                  'so/SO_0001260 
                                  "iaoso")
    
    ;; so:sequence_variant
    (ice-ify-post-processing-rule 'so-sa-ice
                                  'so/SO_0001060
                                  "iaoso")
    
    ;; bto:brenda source tissue ontology
    (ice-ify-post-processing-rule 'bto-ice
                                  'bto/BTO_0000000
                                  "iaobto")
    
    ;; ncbitaxon:archaea
    (ice-ify-post-processing-rule 'ncbitaxon-archaea-ice
                                  'ncbitaxon/NCBITaxon_2157
                                  "iaoncbitaxon")
    
    ;; ncbitaxon:bacteria
    (ice-ify-post-processing-rule 'ncbitaxon-bacteria-ice
                                  'ncbitaxon/NCBITaxon_2
                                  "iaoncbitaxon")
    
    ;; ncbitaxon:eukaryota
    (ice-ify-post-processing-rule 'ncbitaxon-eukaryota-ice
                                  'ncbitaxon/NCBITaxon_2759
                                 "iaoncbitaxon")
    
    ;; ncbitaxon:viroids
    (ice-ify-post-processing-rule 'ncbitaxon-viroids-ice
                                  'ncbitaxon/NCBITaxon_12884
                                  "iaoncbitaxon")
    
    ;; ncbitaxon:viruses
    (ice-ify-post-processing-rule 'ncbitaxon-viruses-ice
                                  'ncbitaxon/NCBITaxon_10239
                                  "iaoncbitaxon")
    
    ;; ncbitaxon:other
    (ice-ify-post-processing-rule 'ncbitaxon-other-ice
                                  'ncbitaxon/NCBITaxon_28384
                                  "iaoncbitaxon")
    ;; ncbitaxon:unclassified
    (ice-ify-post-processing-rule 'ncbitaxon-unclassified-ice
                                  'ncbitaxon/NCBITaxon_12908
                                  "iaoncbitaxon")
    
    
    ;; pro:GO:0032991
    (pro-ice-ify-post-processing-rule 'pro-complex-ice
                                  'go/GO_0032991
                                  "iaopr")
    
    ;; pro:aa-chain
    (pro-ice-ify-post-processing-rule 'pro-aa-chain-ice
                                  'pr/PR_000018263
                                 "iaopr")
    
    
    
    

      ))

