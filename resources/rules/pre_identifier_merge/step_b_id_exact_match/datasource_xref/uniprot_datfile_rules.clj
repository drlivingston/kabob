;; UniProt dat files
;; link primary UniProt identifiers to secondary UniProt identifiers

;; skos:exactMatch is not appropriate here, perhaps skos:closeMatch
;;  but in all liklihood secondary ids should probably be added to bioworld
;;  in some later post-processing step.
;; UniProt_ID skos:exactMatch Seconary_UniProt_ID
;;`{:name "uniprot-exact-altuniprot-id-mapping-assertion"
;; :head ((?/uniprotIce skos/exactMatch ?/secondaryUniprotIce))
;;:body ((_/record kiao/hasTemplate iaouniprot/uniprotUniProtDatFileDataSchema1)
;;         (_/f ro/part_of _/record)
;;         (_/f kiao/hasTemplate iaouniprot/uniprotprimaryUniProtIDDataField1)
;;         (_/f iao/IAO_0000219 ?/uniprotIce)
;;         (_/f2 ro/part_of _/record)
;;       (_/f2 kiao/hasTemplate iaouniprot/uniprotsecondaryUniprotIDsDataField1)
;;         (_/f2 iao/IAO_0000219 ?/secondaryUniprotIce))}



`{:name "uniprot-refseq-exact-mapping-assertion"
 :head ((?/uniprot skos/exactMatch ?/refseq))
  :body
  (~@(kabob/rtv _/record
      iaouniprot/UniProtIDMappingFileData_uniProtAccessionIDDataField1
                                                              ?/uniprot
      iaouniprot/UniProtIDMappingFileData_refseqIdsDataField1
                                                              ?/refseq)
   (?/refseq kiao/denotesSubClassOf obo/CHEBI_36080))} ;protein
             ;;rdf/type kiao/ProteinIdentifier))} ; is this triple redundant?
