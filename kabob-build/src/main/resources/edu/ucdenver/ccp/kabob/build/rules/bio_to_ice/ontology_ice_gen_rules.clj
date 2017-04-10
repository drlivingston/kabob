;; -------------------------------------------
;; --------- BRENDA Tissue Ontology ----------
;; -------------------------------------------
;; create ICE URI's for each term in the BRENDA Tissue Ontology
`{:name "brenda-ontology-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/BTO_0000000))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaobto" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; -------------- Cell Ontology --------------
;; -------------------------------------------
;; create ICE URI's for each term in the Cell Ontology
`{:name "cell-ontology-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/CL_0000000))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaocl" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; ------------- ChEBI Ontology --------------
;; -------------------------------------------
;; create ICE URI's for each term in the ChEBI:subatomic particle subontology
`{:name "chebi-subatomic-particle-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/CHEBI_36342))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaochebi" :prefix "" :suffix "_ICE"}])}

;; create ICE URI's for each term in the ChEBI:role subontology
`{:name "chebi-role-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/CHEBI_50906))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaochebi" :prefix "" :suffix "_ICE"}])}

;; create ICE URI's for each term in the ChEBI:chemical entity subontology
`{:name "chebi-chemical-entity-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/CHEBI_24431))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaochebi" :prefix "" :suffix "_ICE"}])}


;; -------------------------------------------
;; --------------- GO Ontology ---------------
;; -------------------------------------------
;; create ICE URI's for each term in the GO BP subontology
`{:name "go-bp-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/GO_0008150))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaogo" :prefix "" :suffix "_ICE"}])}

;; create ICE URI's for each term in the GO MF subontology
`{:name "go-mf-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/GO_0003674))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaogo" :prefix "" :suffix "_ICE"}])}

;; create ICE URI's for each term in the GO CC subontology
`{:name "go-cc-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/GO_0005575))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaogo" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; --------------- MI Ontology ---------------
;; -------------------------------------------
;; create ICE URI's for each term in the PSI Molecular Interaction Ontology
`{:name "mi-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/cls)) ; denotes
  :body ((?/cls oboInOwl/hasOBONamespace ["PSI-MI"])
         (?/cls rdf/type owl/Class))
  :reify ([?/ice {:ln (:localname ?/cls)
                  :ns "iaomi" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; --------------- MP Ontology ---------------
;; -------------------------------------------
;; create ICE URI's for each term in the Mammalian Phenotype ontology
`{:name "mpheno-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/MP_0000001)
         (:not (:isBlank ?/subclass)))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaompheno" :prefix "" :suffix "_ICE"}])}


;; -------------------------------------------
;; ----------- NCBITaxon Ontology ------------
;; -------------------------------------------

;;iao/ncbi_taxon/NCBI_TAXON_215158_ICE

`{:name "ncbitaxon-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/NCBITaxon_1))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}


;; `{:name "ncbitaxon-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] obo/NCBITaxon_1))
;;   :reify ([?/ice {:ln (:regex "NCBITaxon" "NCBI_TAXON" ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}


;; ;; create ICE URI's for each term in the NCBITaxon:archaea subontology
;; `{:name "b2i8-ncbitaxon-archaea-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_2157))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:bacteria subontology
;; `{:name "b2i9-ncbitaxon-bacteria-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_2))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:eukaryota subontology
;; `{:name "b2i10-ncbitaxon-eukaryota-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_2759))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:viroids subontology
;; `{:name "b2i11-ncbitaxon-viroids-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_12884))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:viruses subontology
;; `{:name "b2i12-ncbitaxon-viruses-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_10239))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:other subontology
;; `{:name "b2i13-ncbitaxon-other-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_28384))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; ;; create ICE URI's for each term in the NCBITaxon:unclassified subontology
;; `{:name "b2i14-ncbitaxon-unclassified-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] ncbitaxon/NCBITaxon_12908))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; ------------ Protein Ontology -------------
;; -------------------------------------------


`{:name "pr-macromolecular-complex-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/GO_0032991)
         (:regex (:str ?/subclass) "^http://purl.obolibrary.org/obo/PR"))
  ;;         (:regex ?/subclass "^http://purl.obolibrary.org/obo/PR"))
  ;;(= "pr" (:namespace ?/subclass))) ;; ensure only PR terms are processed
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaopr" :prefix "" :suffix "_ICE"}])}


`{:name "pr-aa-chain-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/PR_000018263)
         (:regex (:str ?/subclass) "^http://purl.obolibrary.org/obo/PR"))
         ;;(:regex ?/subclass "^http://purl.obolibrary.org/obo/PR"))
  ;;(= "pr" (:namespace ?/subclass))) ;; ensure only PR terms are processed
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaopr" :prefix "" :suffix "_ICE"}])}


;; ;;NOTE QUOTE NOT BACKQUOTE
;; '{:name "pr-macromolecular-complex-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] obo/GO_0032991)
;;           (= "pr" (:namespace ?/subclass))) ;; ensure only PR terms are processed
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaopr" :prefix "" :suffix "_ICE"}])}

;; ;;NOTE QUOTE NOT BACKQUOTE
;; '{:name "pr-aa-chain-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] obo/PR_000018263)
;;           (= "pr" (:namespace ?/subclass))) ;; ensure only PR terms are processed
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaopr" :prefix "" :suffix "_ICE"}])}

;; -------------------------------------------
;; ----------- Sequence Ontology -------------
;; -------------------------------------------
`{:name "so-sequence-attribute-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/SO_0000400))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaoso" :prefix "" :suffix "_ICE"}])}

`{:name "so-sequence-collection-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/SO_0001260))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaoso" :prefix "" :suffix "_ICE"}])}

`{:name "so-sequence-feature-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/SO_0000110))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaoso" :prefix "" :suffix "_ICE"}])}

`{:name "so-sequence-variant-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass)) ; denotes
  :body ((?/subclass [rdfs/subClassOf *] obo/SO_0001060))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaoso" :prefix "" :suffix "_ICE"}])}
