

;;iao/ncbi_taxon/NCBI_TAXON_215158_ICE
;;iao/ncbi_taxon/NCBI_TAXON_215158_ICE

;; `{:name "ncbitaxon-ice-gen"
;;   :head ((?/ice obo/IAO_0000219 ?/subclass))
;;   :body ((?/subclass [rdfs/subClassOf *] obo/NCBITaxon_1))
;;   :reify ([?/ice {:ln (:localname ?/subclass)
;;                   :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}


`{:name "ncbitaxon-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/subclass))
  :body ((?/subclass [rdfs/subClassOf *] obo/NCBITaxon_1))
  :reify ([?/ice {:ln (:regex "obo/NCBITaxon" "NCBI_TAXON" ?/subclass)
                  :ns "iaoncbitaxon" :prefix "" :suffix "_ICE"}])}
