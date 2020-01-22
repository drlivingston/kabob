;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "step-hd_bioplex-interactions-instance-based"
  :description   "This rule generates bio-representations the (biophysical) interactions cataloged by BioPlex"
  :head          ((?/interaction rdf/type ?/physical_association)
                   (?/bioentity1_instance rdf/type ?/bioentity1)
                   (?/bioentity2_instance rdf/type ?/bioentity2)
                   (?/interaction ?/has_participant ?/bioentity1_instance)
                   (?/interaction ?/has_participant ?/bioentity2_instance)
                   (?/record obo/IAO_0000219 ?/interaction))
  :reify         ([?/interaction {:ln (:sha-1 ?/bioentity1 ?/bioentity2 ?/physical_association)
                                  :ns "kbio" :prefix "B_"}]
                   [?/bioentity1_instance {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "kbio" :prefix "B_"}]
                   [?/bioentity2_instance {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "kbio" :prefix "B_"}])
  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    select ?record ?bioentity1 ?bioentity2 ?physical_association {

     {
      select ?physical_association {
          kice:MI_0915 obo:IAO_0000219 ?physical_association .
          filter (?physical_association != obo:MI_0915) . # obo:physical_association
       }
      }

     {
      select ?has_participant {
         kice:RO_0000057 obo:IAO_0000219 ?has_participant .
         filter (?has_participant != obo:RO_0000057) . # obo:has_participant
       }
      }

      ?record rdf:type ccp:IAO_EXT_0001761 . # ccp:BIOPLEX_RECORD
      ?record obo:BFO_0000051 ?uniprot_identifier_a_field_value .
      ?uniprot_identifier_a_field_value rdf:type ccp:IAO_EXT_0001765 . # ccp:BIOPLEX_RECORD___UNIPROT_IDENTIFIER_A_FIELD_VALUE
      ?uniprot_identifier_a_field_value rdf:type ?uniprot_a_identifier .
      ?uniprot_a_identifier obo:IAO_0000219 ?bioentity1 .
      ?record obo:BFO_0000051 ?uniprot_identifier_b_field_value .
      ?uniprot_identifier_b_field_value rdf:type ccp:IAO_EXT_0001766 . # ccp:BIOPLEX_RECORD___UNIPROT_IDENTIFIER_B_FIELD_VALUE
      ?uniprot_identifier_b_field_value rdf:type ?uniprot_b_identifier .
      ?uniprot_b_identifier obo:IAO_0000219 ?bioentity2 .

      }"

  }