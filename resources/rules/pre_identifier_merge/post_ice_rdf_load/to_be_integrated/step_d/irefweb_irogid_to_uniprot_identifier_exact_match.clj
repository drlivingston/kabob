;; TODO: not sure this rule is needed since no other source links to the irefweb internal identifiers
;; --------------------------------------------------------
;; --------- IRefWeb Protein Identifier Exact Match -------
;; --------------------------------------------------------
`{:name "irefweb-irogid-to-uniprot-exact-match"
  :description "This rule asserts an exact match relation between irefweb identifiers and uniprot identifiers"
  :head ((?/irefweb_irogid_identifier skos/exactMatch ?/uniprot_identifier))
:sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                SELECT ?irefweb_irogid_identifier ?uniprot_identifier
                WHERE {  ?record <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000065> . # CCP:IRefWeb_interactor_record
                     ?record <http://purl.obolibrary.org/obo/BFO_0000051> ?irogid_identifier_field_value .
                     ?irogid_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000772> . # CCP:IrefWeb_interactor_record_integer_rog_identifier_field value
                     ?irogid_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?irefweb_irogid_identifier .
                     ?irefweb_irogid_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0001377> . # CCP:IrefWeb_IROGID_identifier
                     ?record <http://purl.obolibrary.org/obo/BFO_0000051> ?unique_identifier_field_value .
                     ?unique_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000755> . # CCP:IrefWeb_interactor_record_unique_identifier_field value
                     ?unique_identifier_field_value <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?uniprot_identifier .
                     ?uniprot_identifier <http://www.w3.org/2000/01/rdf-schema#subClassOf>* <http://ccp.ucdenver.edu/obo/ext/IAO_EXT_0000184> . # CCP:uniprot_identifier
                }"
}



;;TODO replicate the above for Complexes
