;; ---------------------------------------------------
;; --------- RefSeq Catalog Taxon Assignment ---------
;; ---------------------------------------------------
`{:name "step-hcb_refseq-catalog-assign-taxon"
  :description "This rule assigns taxons to bio-entities represented by refseq catalog identifiers"
  :head (
         ;; create a taxon restriction
         (?/restriction rdf/type owl/Restriction)
          (?/restriction owl/onProperty ?/only_in_taxon)
         (?/restriction owl/someValuesFrom ?/taxon)
         (?/bioentity rdfs/subClassOf ?/restriction))
  :reify ([?/restriction {:ln (:restriction)
                          :ns "kbio" :prefix "RS_"}])
  :body
  "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    SELECT distinct ?bioentity ?taxon ?only_in_taxon
  WHERE {
  {
      select ?only_in_taxon {
                             kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                             filter (?only_in_taxon != obo:RO_0002160) .
                             }
      }
  ?record rdf:type ccp:IAO_EXT_0000792 . # ccp:RefSeq_catalog_record
    ?record obo:BFO_0000051 ?catalog_field_value . # BFO:has_part
    ?catalog_field_value rdf:type ccp:IAO_EXT_0000800 . # ccp:RefSeq_catalog_record__RefSeq_identifier_field_value
    ?catalog_field_value rdf:type ?refseq_id .
         ?refseq_id obo:IAO_0000219 ?bioentity . # IAO:denotes
    ?record obo:BFO_0000051 ?taxon_field_value . # BFO:has_part
    ?taxon_field_value rdf:type ccp:IAO_EXT_0000796 . # ccp:RefSeq_catalog_record__taxonomy_identifier_field_value
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