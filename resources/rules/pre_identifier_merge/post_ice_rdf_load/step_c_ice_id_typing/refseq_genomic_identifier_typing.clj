;; ----------------------------------------------------
;; --------- refseq genomic identifier typing ---------
;; ----------------------------------------------------
`{:name "refseq-genomic-identifier-typing"
  :description "This rule types RefSeq identifiers that are genomic identifiers to the more specific ccp:IAO_EXT_0001640 (RefSeq genomic identifier) type."
  :head ((?/refseq_identifier rdfs/subClassOf ccp/IAO_EXT_0001640)) ; CCP:refseq_genomic_identifier
  :reify ()
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  select ?refseq_identifier {
                     ?record rdf:type ccp:IAO_EXT_0000792 . # CCP:refseq_catalog_record
                     ?record obo:BFO_0000051 ?molecule_type_field_value .
                     ?molecule_type_field_value rdf:type ccp:IAO_EXT_0000805 . # CCP:refseq_catalog_record_molecule_type_field_value
                     ?molecule_type_field_value rdfs:label 'Genomic'@en .
                     ?record obo:BFO_0000051 ?refseq_identifier_field_value .
                     ?refseq_identifier_field_value rdf:type ccp:IAO_EXT_0000800 . # CCP:refseq_catalog_record_refseq_identifier_field_value
                     ?refseq_identifier_field_value rdf:type ?refseq_identifier .
                     ?refseq_identifier rdfs:subClassOf ccp:IAO_EXT_0000263 . # CCP:refseq_identifier
                  }"
  }