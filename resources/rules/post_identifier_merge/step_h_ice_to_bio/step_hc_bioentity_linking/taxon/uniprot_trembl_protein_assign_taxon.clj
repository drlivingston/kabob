;; -----------------------------------------------------------
;; --------- Uniprot Trembl Protein Taxon Assignment ---------
;; -----------------------------------------------------------
`{:name "uniprot-trembl-protein-assign-taxon"
  :description "This rule assigns a taxon to proteins represented by uniprot trembl protein identifiers"
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
  WHERE {  ?record rdf:type ccp:IAO_EXT_0000987 . # ccp:sparse_UniProt_protein_record
    ?record obo:BFO_0000051 ?primary_accession_field_value . # BFO:has_part
    ?primary_accession_field_value rdf:type ccp:IAO_EXT_0000989 . # ccp:sparse_UniProt_protein_record__primary_accession_field_value
    ?primary_accession_field_value rdf:type ?uniprot_id .
         ?uniprot_id obo:IAO_0000219 ?bioentity . # IAO:denotes
    ?record obo:BFO_0000051 ?organism_record . # BFO:has_part
    ?organism_record rdf:type ccp:IAO_EXT_0000992 . # ccp:sparse_UniProt_protein_record__organism_field_value
    ?organism_record rdf:type ccp:IAO_EXT_0000972 . # ccp:uniprot_organism_record
    ?organism_record obo:BFO_0000051 ?db_ref_record .
         ?db_ref_record rdf:type ccp:IAO_EXT_0000955 . # ccp:uniprot_db_reference_record
    ?db_ref_record obo:BFO_0000051 ?id_field_value .
         ?id_field_value rdf:type ccp:IAO_EXT_0001127 . # ccp:uniprot_db_reference_record__identifier_field_value
    ?id_field_value rdf:type ?taxon_id .
         ?taxon_id rdfs:subClassOf ccp:IAO_EXT_0000204 . # ccp:NCBITaxon_identifier
    ?taxon_id obo:IAO_0000219 ?taxon . # IAO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
         filter (contains (str(?taxon), 'http://ccp.ucdenver.edu/obo/ext/'))
         }"
   }