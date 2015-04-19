

`{:name "hgnc-symbol-eg-id-exact-mapping"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body
  (~@(kabob/rtv _/record
        iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce
        iaohgnc/HgncDownloadFileData_entrezGeneIDDataField1  ?/egIce))}


`{:name "hgnc-symbol-refseq-exact-mapping"
  :head ((?/hgncSymbolIce skos/exactMatch ?/refseq))
  :body
  (~@(kabob/rtv _/record
        iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce
        iaohgnc/HgncDownloadFileData_refseqIDsDataField1      ?/refseq)
   (?/refseq kiao/denotesSubClassOf obo/SO_0000352))} ;dna
             ;;rdf/type kiao/DNAIdentifier))}


;;TODO what's the difference between the "supplied" version below and the above

`{:name "hgnc-symbol-supplied-eg-id-exact-mapping"
  :head ((?/hgncSymbolIce skos/exactMatch ?/egIce))
  :body
  (~@(kabob/rtv _/record
        iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce
        iaohgnc/HgncDownloadFileData_suppliedEntrezGeneIdDataField1  ?/egIce))}

`{:name "hgnc-symbol-supplied-refseq-exact-mapping"
  :head ((?/hgncSymbolIce skos/exactMatch ?/refseq))
  :body
  (~@(kabob/rtv _/record
        iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1   ?/hgncSymbolIce
        iaohgnc/HgncDownloadFileData_suppliedRefseqIdDataField1 ?/refseq)
   (?/refseq kiao/denotesSubClassOf obo/SO_0000352))} ;dna
             ;;rdf/type kiao/DNAIdentifier))}




       