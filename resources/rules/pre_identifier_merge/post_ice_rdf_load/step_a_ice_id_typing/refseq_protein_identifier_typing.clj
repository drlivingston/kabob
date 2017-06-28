;; ----------------------------------------------------------
;; --------- refseq protein identifier typing ---------------
;; ----------------------------------------------------------
`{:name          "refseq-protein-identifier-typing"
  :description   "This rule specifically the basic formal ontology identifier"
  :head          ((?/refseq_identifier rdf/type ccp/IAO_EXT_0001638)) ; CCP:refseq_protein_identifier
  :reify ()
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  select ?refseq_identifier {
                     ?record rdf:type ccp:IAO_EXT_0000792 . # CCP:refseq_catalog_record
                     ?record obo:BFO_0000051 ?molecule_type_field_value .
                     ?molecule_type_field_value rdf:type ccp:IAO_EXT_0000805 . # CCP:refseq_catalog_record_molecule_type_field_value
                     ?molecule_type_field_value rdfs:label 'Protein'@en .
                     ?record obo:BFO_0000051 ?refseq_identifier_field_value .
                     ?refseq_identifier_field_value rdf:type ccp:IAO_EXT_0000800 . # CCP:refseq_catalog_record_refseq_identifier_field_value
                     ?refseq_identifier_field_value rdf:type ?refseq_identifier .
                     ?refseq_identifier rdfs:subClassOf ccp:IAO_EXT_0000263 . # CCP:refseq_identifier
                  }"
  }