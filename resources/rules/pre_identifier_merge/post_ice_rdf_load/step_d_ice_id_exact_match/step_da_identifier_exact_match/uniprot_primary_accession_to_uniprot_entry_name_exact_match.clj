;; ---------------------------------------------------------------------------
;; --------- UniProt Protein RefSeq Identifier Exact Match --------
;; ---------------------------------------------------------------------------
`{:name "uniprot-primary-accession-to-uniprot-entry-name-exact-match"
  :description "This rule asserts an exact match between a uniprot primary accession and its corresponding record entry name, e.g. PPARD_HUMAN"
  :head ((?/uniprot_identifier skos/exactMatch ?/uniprot_entry_name_identifier))
:sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                select ?uniprot_identifier ?uniprot_entry_name_identifier {
                  ?record rdf:type ccp:IAO_EXT_0000234 . # CCP:uniprot_kb_record
                  ?record obo:BFO_0000051 ?primary_accession_field_value .
                  ?primary_accession_field_value rdf:type ccp:IAO_EXT_0000240 . # CCP:uniprot_kb_record___primary_accession_field_value
                  ?primary_accession_field_value rdf:type ?uniprot_identifier .
                  ?uniprot_identifier rdfs:subClassOf ccp:IAO_EXT_0000184 . # CCP:uniprot_id

                  ?record obo:BFO_0000051 ?entry_name_field_value .
                  ?entry_name_field_value rdf:type ccp:IAO_EXT_0000932 . # CCP:uniprot_kb_record___entry_name_field_value
                  ?entry_name_field_value rdf:type ?uniprot_entry_name_identifier .
                  ?uniprot_entry_name_identifier rdfs:subClassOf ccp:IAO_EXT_0001494 . # CCP:uniprot_entry_name_identifier

                }"
}
