;; --------------------------------------------------
;; --------- Entrez Gene Label Assignment -----------
;; --------------------------------------------------
`{:name "ncbi-gene-labels"
  :description "This rule creates a label for every gene record and types it as (IAO_EXT_0000877)"
  :head (
         ;; create a symbol label for gene
         (?/gene rdfs/label ?/gene_symbol))

  :sparql-string
  "PREFIX obo: <http://purl.obolibrary.org/obo/>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>

   SELECT distinct ?gene_symbol ?gene
   WHERE {  ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_info_record
            ?record obo:BFO_0000051 ?gene_field_value . # BFO:has_part
            ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
            ?gene_field_value rdf:type ?gene_id .
            ?gene_id obo:IAO_0000219 ?gene . # IAO:denotes
            ?record obo:BFO_0000051 ?gene_symbol_field_value . # BFO:has_part
            ?gene_symbol_field_value rdf:type ccp:IAO_EXT_0000877 . # ccp:NCBI_gene_info_record__symbol_field_value
            ?gene_symbol_field_value rdfs:label ?gene_symbol .
         }"

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }