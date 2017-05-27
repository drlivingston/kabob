;; All UniProt identifiers are of type kiao/ProteinIdentifier
;; original:
;; `{:name "uniprot-id-type-assertion"
;;  :head ((?/ice rdf/type kiao/ProteinIdentifier))
;;   :body
;;   ((_/f kiao/hasTemplate
;;         iaouniprot/UniProtDatFileData_primaryUniProtIDDataField1)
;;    (_/f obo/IAO_0000219 ?/ice))}

;; All UniProt identifiers are of type kiao/ProteinIdentifier
`{:name "uniprot-primary-accession-id-type"
 :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
  :body
  ((_/f kiao/hasTemplate
        iaouniprot/UniProtFileRecord_primaryAccessionDataField1)
   (_/f obo/IAO_0000219 ?/ice))} ; denotes


`{:name "uniprot-trembl-primary-accession-id-type"
 :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
  :body
  ((_/f kiao/hasTemplate
        iaouniprot/SparseUniProtFileRecord_primaryAccessionDataField1)
   (_/f obo/IAO_0000219 ?/ice))} ; denotes


;; All UniProt identifiers are of type kiao/ProteinIdentifier
;; TODO:verfiy and enable this rule
;; `{:name "uniprot-accession-id-type"
;;  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
;;   :body
;;   ((_/f kiao/hasTemplate
;;         iaouniprot/UniProtFileRecord_accessionDataField1)
;;    (_/f obo/IAO_0000219 ?/ice))}



;;new uniprot (from xml) and sparse trembl
;; All UniProt identifiers are of type kiao/ProteinIdentifier
;; `{:name "swissprot-id-type-assertion"
;;  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
;;   :body
;;   ((_/f kiao/hasTemplate
;;         iaouniprot/UniProtDatFileRecord_primaryAccessionDataField1)
;;    (_/f obo/IAO_0000219 ?/ice))}


;; `{:name "trembl-id-type-assertion"
;;  :head ((?/ice kiao/denotesSubClassOf obo/CHEBI_36080)) ;protein
;;   :body
;;   ((_/f kiao/hasTemplate
;;         iaouniprot/SparseUniProtDatFileRecord_primaryAccessionDataField1)
;;    (_/f obo/IAO_0000219 ?/ice))}

