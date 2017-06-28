;; ------------------------------------------------
;; --------- Entrez Gene Taxon Assignment ---------
;; ------------------------------------------------
`{:name "entrez-gene-assign-taxon"
  :description "This rule assigns taxons to genes represented by entrez gene identifiers"
  :head (
         ;; create a taxon restriction
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty obo/RO_0002160) ; RO:only_in_taxon
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/bioentity rdfs/subClassOf ?/restriction))
  :reify ([?/restriction {:ln (:restriction)
                          :ns "ccp" :prefix "RS_"}])
  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  SELECT ?taxon ?bioentity
                  WHERE {  ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_record
                           ?record obo:BFO_0000051 ?gene_field_value . # BFO:has_part
                           ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
                           ?gene_field_value rdf:type ?gene_id .
                           ?gene_id obo:IAO_0000219 ?bioentity . # IAO:denotes
                           ?record obo:BFO_0000051 ?taxon_field_value . #BFO:has_part
                           ?taxon_field_value rdf:type ccp:IAO_EXT_0000875 . # ccp:NCBI_gene_info_record__taxon_identifier_field_value
                           ?taxon_field_value rdf:type ?taxon_id .
                           ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
                           # ensure it's a kabob bioentity (not an obo bioentity)
                           filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/obo/ext/'))
                  }"

}