;; --------------------------------------------------------
;; ------ Uniprot SwissProt Protein Label Assignment ------
;; --------------------------------------------------------
`{:name        "step-hbb_uniprot-swissprot-protein-isoform-links-and-labels"
  :description "This rule creates links between uniprot isoforms and canonical proteins, and adds a label to the isoform."
  :head        ((?/isoform_bioentity ?/variant_of ?/protein_bioentity)
                 (?/isoform_bioentity rdfs/label ?/isoform_label))
  :body
               "PREFIX obo: <http://purl.obolibrary.org/obo/>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
   select ?isoform_identifier ?isoform_label ?isoform_bioentity ?protein_ice_id ?protein_bioentity ?variant_of {
  ?isoform_record rdf:type ccp:IAO_EXT_0000966 .
  ?isoform_record obo:BFO_0000051 ?isoform_record_identifier_field_value .
  ?isoform_record_identifier_field_value rdf:type ccp:IAO_EXT_0001159 .
  ?isoform_record_identifier_field_value rdf:type ?isoform_identifier .
  ?isoform_record_identifier_field_value rdfs:label ?isoform_identifier_label .
  ?isoform_identifier rdfs:subClassOf ccp:IAO_EXT_0001495 .
  ?isoform_identifier obo:IAO_0000219 ?isoform_bioentity .
  ?comment_record obo:BFO_0000051 ?isoform_record .
  ?comment_record rdf:type ccp:IAO_EXT_0000950 .
  ?uniprot_record obo:BFO_0000051 ?comment_record .
  ?uniprot_record rdf:type ccp:IAO_EXT_0000234 . # ccp:UniProt_protein_record
  ?uniprot_record obo:BFO_0000051 ?protein_field_value . # BFO:has_part
  ?protein_field_value rdf:type ccp:IAO_EXT_0000240 . # ccp:UniProt_protein_record__primary_accession_field_value
  ?protein_field_value rdf:type ?protein_ice_id .
  ?protein_ice_id obo:IAO_0000219 ?protein_bioentity . # IAO:denotes
  ?uniprot_record obo:BFO_0000051 ?protein_record_name_field_value . # BFO:has_part
  ?protein_record_name_field_value rdf:type ccp:IAO_EXT_0000932 . # ccp:UniProt_protein_record__name_field_value
  ?protein_record_name_field_value rdfs:label ?protein_record_name .
  bind(concat(\"Isoform of \", str(?protein_record_name), \" (\", str(?isoform_identifier_label), \")\") as ?isoform_label)


  {
    select ?variant_of {
      <http://ccp.ucdenver.edu/kabob/ice/so#variant_of> obo:IAO_0000219 ?variant_of .
      filter (?variant_of != <http://purl.obolibrary.org/obo/so#variant_of>)

    }
  }
  }"

   :options {:magic-prefixes [["franzOption_chunkProcessingAllowed" "franz:yes"]]}
   }