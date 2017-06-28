;; --------------------------------------------------
;; --------- DrugBank Drug Label Assignment ---------
;; --------------------------------------------------
`{:name "drugbank-drug-labels"
  :description "This rule creates a label for every drug record and types it as (IAO_EXT_0000367)"
  :head (
         ;; create a label for the drug
         (?/drug rdfs/label ?/drug_name))

  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
      PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
      PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      SELECT distinct ?drug ?drug_name
      WHERE {  ?record rdf:type ccp:IAO_EXT_0000426 . # ccp:DrugBank_drug_record
               ?record obo:BFO_0000051 ?drug_field_value . # BFO:has_part
               ?drug_field_value rdf:type ccp:IAO_EXT_0000360 . # ccp:Drugbank_drug_record__drugbank_identifier_field_value
               ?drug_field_value rdf:type ?drug_id .
               ?drug_id obo:IAO_0000219 ?drug . # IAO:denotes
               ?record obo:BFO_0000051 ?drug_name_field_value . # BFO:has_part
               ?drug_name_field_value rdf:type ccp:IAO_EXT_0000367 . # ccp:Drugbank_drug_record__drug_name_field_value
               ?drug_name_field_value rdfs:label ?drug_name .
            }"

  :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
  }