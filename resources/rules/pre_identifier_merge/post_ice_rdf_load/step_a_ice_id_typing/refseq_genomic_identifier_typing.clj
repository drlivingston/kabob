;; ----------------------------------------------------------
;; --------- refseq genomic identifier typing ---------------
;; ----------------------------------------------------------
`{:name "refseq-genomic-identifier-typing"
  :description "This rule specifically the basic formal ontology identifier"
  :head ((?/id rdf/type ccp/IAO_EXT_0001640)) ; CCP:refseq_genomic_identifier
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id {
                  ?id rdf:type ccp:IAO_EXT_0000263 . # CCP:refseq_identifier
                  filter (contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_AC_') ||
                  contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_NC_') ||
                  contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_NG_') ||
                  contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_NT_') ||
                  contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_NW_') ||
                  contains (str(?ontology_concept), 'http://ccp.ucdenver.edu/obo/ext/REFSEQ_NZ_'))
                  }"
  }