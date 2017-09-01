;; ----------------------------------------------------
;; --------- refseq genomic identifier typing ---------
;; ----------------------------------------------------
`{:name "obsolete-uniprot-identifier-typing"
  :description "This rule types all UniProt secondary accession identifiers as obsolete identifiers so that they won't be used in the identifier merging step."
  :head ((?/secondary_uniprot_identifier rdf/type ccp/IAO_EXT_0001711)) ; CCP:obsolete_identifier
  :reify ()
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  SELECT ?secondary_uniprot_identifier
                  WHERE {  ?record rdf:type ccp:IAO_EXT_0000234 . # CCP:uniprot_knowledge_base_record
                           ?record obo:BFO_0000051 ?primary_accession_field_value .
                           ?primary_accession_field_value rdf:type ccp:IAO_EXT_0000240 . # CCP:uniprot_protein_record_primary_accession_field_value
                           ?primary_accession_field_value rdf:type ?primary_uniprot_identifier .
                           ?primary_uniprot_identifier rdfs:subClassOf ccp:IAO_EXT_0000184 . # CCP:uniprot_identifier
                           ?record obo:BFO_0000051 ?accession_field_value .
                           ?accession_field_value rdf:type ccp:IAO_EXT_0000931 . # CCP:uniprot_protein_record_accession_field_value
                           ?accession_field_value rdf:type ?secondary_uniprot_identifier .
                           ?secondary_uniprot_identifier rdfs:subClassOf ccp:IAO_EXT_0000184 . # CCP:uniprot_identifier
                           filter (?secondary_uniprot_identifier != ?primary_uniprot_identifier)
                  }"
  }