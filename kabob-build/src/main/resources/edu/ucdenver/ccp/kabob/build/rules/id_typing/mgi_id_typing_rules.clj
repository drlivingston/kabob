;; MGI Ids can represent the following types:
;;  BAC/YAC end, Complex/Cluster/Region, Cytogenetic Marker, DNA Segment,
;;  Gene, Other Genome Feature, Pseudogene, QTL, Transgene
`{:name "mgi-id-type-assertion"
 :head ((?/ice kiao/denotesSubClassOf obo/SO_0000352)) ;dna
  :body ((_/f kiao/hasTemplate iaomgi/MRKListFileData_mgiAccessionIDDataField1)
         (_/f obo/IAO_0000219 ?/ice))}



