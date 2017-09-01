;; --------------------------------------------------------
;; ------ Uniprot SwissProt Protein Label Assignment ------
;; --------------------------------------------------------
`{:name "uniprot-swissprot-protein-labels"
  :description "This rule creates a label for every swissprot protein record and types it as (IAO_EXT_0000932)"
  :head ((?/protein rdfs/label ?/protein_record_name))


  :sparql-string
  "PREFIX obo: <http://purl.obolibrary.org/obo/>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

   SELECT distinct ?protein ?protein_record_name
   WHERE {  ?record rdf:type ccp:IAO_EXT_0000234 . # ccp:UniProt_protein_record
            ?record obo:BFO_0000051 ?protein_field_value . # BFO:has_part
            ?protein_field_value rdf:type ccp:IAO_EXT_0000240 . # ccp:UniProt_protein_record__primary_accession_field_value
            ?protein_field_value rdf:type ?protein_ice_id .
            ?protein_ice_id obo:IAO_0000219 ?protein . # IAO:denotes
            ?record obo:BFO_0000051 ?protein_record_name_field_value . # BFO:has_part
            ?protein_record_name_field_value rdf:type ccp:IAO_EXT_0000932 . # ccp:UniProt_protein_record__name_field_value
            ?protein_record_name_field_value rdfs:label ?protein_record_name .
         }"

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }