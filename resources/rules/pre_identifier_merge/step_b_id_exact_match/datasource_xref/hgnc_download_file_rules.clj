`{:name "hgnc-symbol-hgnc-id-exact-mapping"
  :description "HGNC has two unique identifiers for each record,
  an approved gene symbol and a unique integer identifer. In the id-typing
  rules we declare that both types kiao/denotesSubClassOf obo/SO_0000352. In
  doing so, the bioentity generation machinery will create bio-entities for each
  type. This particular rule is required to ensure that only a single bio-entity
  gets created for each HGNC record. If the HGNC approved symbol and integer
  identifier for a given HGNC record are not declared as skos/exactMatch then
  two distinct bio-entities will be erroneously generated instead of just one."
  :head ((?/hgncSymbolIce skos/exactMatch ?/hgncIntegerIdIce))
  :body
  (~@(kabob/rtv _/record
        iaohgnc/HgncDownloadFileData_hgncGeneSymbolDataField1 ?/hgncSymbolIce
        iaohgnc/HgncDownloadFileData_hgncIDDataField1  ?/hgncIntegerIdIce))}

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




