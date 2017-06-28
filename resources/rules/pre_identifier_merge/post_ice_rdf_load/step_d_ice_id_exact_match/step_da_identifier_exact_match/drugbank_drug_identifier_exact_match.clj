;; TODO: make this rule more specific so that it matches identifier types that we can be certain are exact matches
;; --------------------------------------------------------
;; --------- DrugBank Drug Identifier Exact Match ---------
;; --------------------------------------------------------
`{:name          "drugbank-drug-identifier-exact-match"
  :description   "This rule asserts an exact match between drugbank drugs and other drug identifiers"
  :head          ((?/drugbank_identifier skos/exactMath ?/other_identifier))
  :sparql-string "

  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix obo: <http://purl.obolibrary.org/obo/>
  SELECT ?drugbank_identifier ?other_identifier
WHERE {
       ?record rdf:type ccp:IAO_EXT_0000426 .
       ?record obo:BFO_0000051 ?drugbank_id_field_value .
       ?drugbank_id_field_value rdf:type ccp:IAO_EXT_0000360 .
       ?drugbank_id_field_value rdf:type ?drugbank_identifier .
       ?drugbank_identifier rdfs:subClassOf ccp:IAO_EXT_0001309 .
       ?record obo:BFO_0000051 ?external_identifiers_field_value .
       ?external_identifiers_field_value rdf:type ccp:IAO_EXT_0000404 .
       ?external_identifiers_field_value rdf:type ?other_identifier .
       ?other_identifier rdfs:subClassOf* ccp:IAO_EXT_0000342 .
       }"
  }
