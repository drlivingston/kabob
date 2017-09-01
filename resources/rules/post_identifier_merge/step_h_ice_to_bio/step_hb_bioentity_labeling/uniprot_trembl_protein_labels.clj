;; -----------------------------------------------------------
;; --------- Uniprot Trembl Protein Label Assignment ---------
;; -----------------------------------------------------------
`{:name "uniprot-trembl-protein-labels"
  :description "This rule creates a label for every trembl protein record and types it as (IAO_EXT_0000991)"
  :head (
         ;; create a label for protein
         (?/protein rdfs/label ?/protein_name))

  :sparql-string
    "PREFIX obo: <http://purl.obolibrary.org/obo/>
     PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
     PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
     PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
     SELECT distinct ?protein ?protein_name ?protein_id
     WHERE {  ?record rdf:type ccp:IAO_EXT_0000987 . # ccp:Sparse_UniProt_record
              ?record obo:BFO_0000051 ?protein_field_value . # BFO:has_part
              ?protein_field_value rdf:type ccp:IAO_EXT_0000989 . # ccp:Sparse_UniProt_record__primary_accession_field_value
              ?protein_field_value rdf:type ?protein_id .
              ?protein_id obo:IAO_0000219 ?protein . # IAO:denotes
              ?record obo:BFO_0000051 ?protein_name_field_value . # BFO:has_part
              ?protein_name_field_value rdf:type ccp:IAO_EXT_0000991 . # ccp:Sparse_UniProt_record__name_field_value
              ?protein_name_field_value rdfs:label ?protein_name .
           }"


   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }