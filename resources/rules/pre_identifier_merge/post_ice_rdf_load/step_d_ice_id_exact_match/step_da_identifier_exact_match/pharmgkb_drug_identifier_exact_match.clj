   ;; ------------------------------------------------------
;; --------- PharmGKB Drug Identifier Exact Match -------
;; ------------------------------------------------------
`{:name "pharmgkb-drug-identifier-exact-match"
  :description "This rule creates an exact match mapping between pahrmgkb drugs and other drugs"
  :head ((?/pharmgkb_drug_identifier skos/exactMatch ?/xref_identifier))
   :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
       PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
       SELECT ?xref_identifier ?pharmgkb_drug_identifier
WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000821> . # CCP:PharmGKB_drug_record
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?accession_identifier_field_value .
?accession_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001010> . # CCP:PharmGKB_drug_record_accession_identifier_field value
?accession_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?pharmgkb_drug_identifier .
?pharmgkb_drug_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001429> . # CCP:PharmGKB_identifier
?record <http://purl.obolibrary.org/obo/BFO_0000051> ?cross_references_field_value .
?cross_references_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001016> . # CCP:PharmGKB_drug_record_cross_references_field value
?cross_references_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?xref_identifier .
?xref_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf>* <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000342> . # CCP:identifier_of_a_biological_entity
}"
}


