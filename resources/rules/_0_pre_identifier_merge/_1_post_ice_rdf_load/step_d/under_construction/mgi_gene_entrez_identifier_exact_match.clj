;; --------------------------------------------------------
;; --------- MGI Gene Entrez Identifier Exact Match -------
;; --------------------------------------------------------
;; TODO: use IAO_EXT_ concepts
`{:name "mgi-gene-entrez-identifier-exact-match"
  :description "This rule file asserts skos:exactMatch between each pair of mgi and entrez gene identifiers"
  :head ((?/mgiIce skos/exactMatch ?/egIce))
  :body ((?/record ccp/MGIEntrezGeneFileData_mgiAccessionIDDataField1 ?/mgiIce)
         (?/record ccp/MGIEntrezGeneFileData_entrezGeneIDDataField1 ?/egIce))
  }

;; MGI_Accession skos:???Match Secondary_MGI_Accession
;;`{:name "mgi-exact-altmgi-id-mapping-assertion"
;; :head ((?/mgiIce skos/exactMatch ?/secondaryMgiIce))
;; :body ((_/record kiao/hasTemplate iaomgi/mgiMGIEntrezGeneFileDataSchema1)
;;            (_/f ro/part_of _/record)
;;            (_/f kiao/hasTemplate iaomgi/mgimgiAccessionIDDataField1)
;;            (_/f iao/IAO_0000219 ?/mgiIce)
;;            (_/f2 ro/part_of _/record)
;;            (_/f2 kiao/hasTemplate iaomgi/mgisecondaryAccessionIDs)
;;            (_/f2 iao/IAO_0000219 ?/secondaryMgiIce))}

