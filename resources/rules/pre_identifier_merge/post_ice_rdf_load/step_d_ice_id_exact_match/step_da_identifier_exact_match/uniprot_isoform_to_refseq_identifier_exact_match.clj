;; ---------------------------------------------------------------------------
;; --------- UniProt Protein RefSeq Identifier Exact Match --------
;; ---------------------------------------------------------------------------
`{:name "uniprot-protein-refseq-identifier-exact-match"
  :description "This rule asserts an exact match between uniprot protein identifiers and refseq identifiers"
  :head ((?/uniprot_id skos/exactMatch ?/refseq_id))
:sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                select ?refseq_id ?uniprot_id {
                  ?db_ref_record rdf:type ccp:IAO_EXT_0000955 . # CCP:uniprot_database_reference_record
                  ?db_ref_record rdf:type ccp:IAO_EXT_0000940 . # CCP:uniprot_kb_record_database_reference_field_value
                  ?db_ref_record obo:BFO_0000051 ?id_field_value .
                  ?id_field_value rdf:type ?refseq_id .
                  ?refseq_id rdfs:subClassOf ccp:IAO_EXT_0001638 . # ccp:refseq_protein_identifier
                  ?db_ref_record obo:BFO_0000051 ?molecule_record .
                  ?molecule_record rdf:type ccp:IAO_EXT_0001712 . # CCP:uniprot_database_reference_record___molecule_field_value
                  ?molecule_record rdf:type ccp:IAO_EXT_0000961 . # CCP:uniprot_molecule_type_record
                  ?molecule_record obo:BFO_0000051 ?id_field .
                  ?id_field rdf:type ccp:IAO_EXT_0001147 . # CCP:uniprot_molecule_type_record___identifier_field_value
                  ?id_field rdf:type ?uniprot_id .
                  ?uniprot_id rdfs:subClassOf ccp:IAO_EXT_0001495 . # CCP:uniprot_isoform_identifier
                }"
}
