;; ------------------------------------------------
;; --------- Entrez Gene Taxon Assignment ---------
;; ------------------------------------------------
`{:name "step-hcb_entrez-gene-assign-taxon"
  :description "This rule assigns taxons to genes represented by entrez gene identifiers"
  :head (
         ;; create a taxon restriction
         (?/restriction rdf/type owl/Restriction)
         (?/restriction owl/onProperty ?/only_in_taxon)
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/bioentity rdfs/subClassOf ?/restriction))
  :reify ([?/restriction {:ln (:restriction)
                          :ns "kbio" :prefix "RS_"}])
  :body "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  SELECT distinct ?taxon ?bioentity ?only_in_taxon
                  WHERE {

                  {
                            select ?only_in_taxon {
                                                   kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                                                   filter (?only_in_taxon != obo:RO_0002160) .
                                                   }
                            }
                  ?record rdf:type ccp:IAO_EXT_0000681 . # ccp:NCBI_gene_record
                           ?record obo:BFO_0000051 ?gene_field_value . # BFO:has_part
                           ?gene_field_value rdf:type ccp:IAO_EXT_0000876 . # ccp:NCBI_gene_info_record__gene_identifier_field_value
                           ?gene_field_value rdf:type ?gene_id .
                           ?gene_id obo:IAO_0000219 ?bioentity . # IAO:denotes
                           ?record obo:BFO_0000051 ?taxon_field_value . #BFO:has_part
                           ?taxon_field_value rdf:type ccp:IAO_EXT_0000875 . # ccp:NCBI_gene_info_record__taxon_identifier_field_value
                           ?taxon_field_value rdf:type ?taxon_id .
                           ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
                           # ensure it's a kabob bioentity (not an obo bioentity)
                           filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/kabob/bio/'))

                           # ignore any bioentities that already have a taxon restriction
                           minus {
                            ?bioentity rdfs:subClassOf ?taxon_r .
                            ?taxon_r owl:onProperty ?only_in_taxon .
                           }


                  }"

}