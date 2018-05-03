;; --------------------------------------------------------------
;; --------- Uniprot SwissProt Protein Taxon Assignment ---------
;; --------------------------------------------------------------
`{:name "step-hcc_uniprot-swissprot-protein-assign-taxon"
  :description "This rule assigns a taxon to proteins represented by uniprot swissprot protein identifiers"
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
    SELECT ?taxon ?bioentity ?only_in_taxon
  WHERE {  ?record rdf:type ccp:IAO_EXT_0000234 . # ccp:UniProt_protein_record
    ?record obo:BFO_0000051 ?primary_accession_field_value . # BFO:has_part
    ?primary_accession_field_value rdf:type ccp:IAO_EXT_0000240 . # ccp:UniProt_protein_record__primary_accession_field_value
    ?primary_accession_field_value rdf:type ?uniprot_id .
         ?uniprot_id obo:IAO_0000219 ?bioentity . # IAO:denotes
    ?record obo:BFO_0000051 ?organism_record . # BFO:has_part
    ?organism_record rdf:type ccp:IAO_EXT_0000935 . # ccp:UniProt_protein_record__organism_field_value
    ?organism_record rdf:type ccp:IAO_EXT_0000972 . # ccp:uniprot_organism_record
    ?organism_record obo:BFO_0000051 ?db_ref_record .
         ?db_ref_record rdf:type ccp:IAO_EXT_0000955 . # ccp:uniprot_db_reference_record
    ?db_ref_record obo:BFO_0000051 ?id_field_value .
         ?id_field_value rdf:type ccp:IAO_EXT_0001127 . # ccp:uniprot_db_reference_record__identifier_field_value
    ?id_field_value rdf:type ?taxon_id .
         ?taxon_id rdfs:subClassOf ccp:IAO_EXT_0001832 . # ccp:NCBITaxon_taxon_identifier
    ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
         filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/kabob/bio/'))

         # ignore any bioentities that already have a taxon restriction
  minus {
         ?bioentity rdfs:subClassOf ?taxon_r .
         ?taxon_r owl:onProperty ?only_in_taxon .
         }

         {
                             select ?only_in_taxon {
                                                    kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                                                    filter (?only_in_taxon != obo:RO_0002160) .
                                                    }
                             }
  }"


   }