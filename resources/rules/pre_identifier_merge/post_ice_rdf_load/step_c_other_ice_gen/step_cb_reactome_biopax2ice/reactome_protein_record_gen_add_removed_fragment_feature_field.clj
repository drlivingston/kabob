`{:name "reactome-protein-record-gen_add-removed-fragment-feature-field-value"
  :description "This rule finds any previously reified protein record from Reactome and traces out any bits of the protein that have been cleaved off (not its sequence modifications, which are described separately)."

  :head (
         (?/prot_record obo/BFO_0000051 ?/fragment_feature_record)  ;; has_part
         (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001527) ;; Reactome feature field value
         (?/fragment_feature_record rdf/type ccp/IAO_EXT_0001587) ;; Reactome fragment feature record 
         
         (?/fragment_feature_record obo/BFO_0000051 ?/sequence_interval_record)  ;; has_part
         (?/sequence_interval_record rdf/type ccp/IAO_EXT_0001532) ;; Reactome feature location field value
         (?/sequence_interval_record rdf/type ccp/IAO_EXT_0001576) ;; Reactome sequence interval record
         (?/sequence_interval_record obo/BFO_0000051 ?/start_sequence_site_record);; has_part
         (?/start_sequence_site_record rdf/type ccp/IAO_EXT_0001534) ;; Reactome sequence interval start field
         (?/start_sequence_site_record rdf/type ccp/IAO_EXT_0001575) ;; Reactome sequence site record
         (?/start_sequence_site_record obo/BFO_0000051 ?/start_position_status)
         (?/start_position_status rdf/type ccp/IAO_EXT_0001537) ;; Reactome position status field value
         (?/start_position_status rdfs/label ["EQUAL" "en"]) ;; Reactome position status field value
         (?/start_sequence_site_record obo/BFO_0000051 ?/start_sequence_position)
         (?/start_sequence_position rdf/type ccp/IAO_EXT_0001538) ;; Reactome sequence position field value
         (?/start_sequence_position rdfs/label ?/start) ;; start of cleaved residues
         (?/sequence_interval_record obo/BFO_0000051 ?/end_sequence_site_record);; has_part
         (?/end_sequence_site_record rdf/type ccp/IAO_EXT_0001535) ;; Reactome sequence interval end field
         (?/end_sequence_site_record rdf/type ccp/IAO_EXT_0001575) ;; Reactome sequence site record
         (?/end_sequence_site_record obo/BFO_0000051 ?/end_position_status)
         (?/end_position_status rdf/type ccp/IAO_EXT_0001537) ;; Reactome position status field value
         (?/end_position_status rdfs/label ["EQUAL" "en"]) ;; Reactome position status field value
         (?/end_sequence_site_record obo/BFO_0000051 ?/end_sequence_position)
         (?/end_sequence_position rdf/type ccp/IAO_EXT_0001538) ;; Reactome sequence position field value
         (?/end_sequence_position rdfs/label ?/end)) ;; start of cleaved residues

  
  :reify ([?/fragment_feature_record {:ln (:sha-1 "Reactome fragment feature record" "EQUAL" ?/start "EQUAL" ?/end)
                                  :ns "ccp" :prefix "R_"}]
          [?/sequence_interval_record {:ln (:sha-1 "Reactome sequence interval record" "EQUAL" ?/start "EQUAL" ?/end)
                                  :ns "ccp" :prefix "R_"}]
          [?/start_sequence_site_record {:ln (:sha-1 "Reactome sequence site record" "EQUAL" ?/start)
                  :ns "ccp" :prefix "R_"}]
          [?/end_sequence_site_record {:ln (:sha-1 "Reactome sequence site record" "EQUAL" ?/end)
                  :ns "ccp" :prefix "R_"}]
          [?/start_sequence_position {:ln (:sha-1 "Reactome sequence site record sequence position field" ?/start)
                  :ns "ccp" :prefix "F_"}]
          [?/start_position_status {:ln (:sha-1 "Reactome sequence site record position status field" "EQUAL")
                  :ns "ccp" :prefix "F_"}]
          [?/end_sequence_position {:ln (:sha-1 "Reactome sequence site record sequence position field" ?/end)
                  :ns "ccp" :prefix "F_"}]
          [?/end_position_status {:ln (:sha-1 "Reactome sequence site record position status field" "EQUAL")
                  :ns "ccp" :prefix "F_"}])

  :sparql-string
  "PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX obo: <http://purl.obolibrary.org/obo/>
   SELECT ?end ?prot_record ?start {
      ?prot_record rdf:type ccp:IAO_EXT_0001513 .
      ?prot_record obo:IAO_0000219 ?prot .
      ?prot rdf:type biopax:Protein .

      ?prot biopax:feature ?frag_feature .
      ?frag_feature rdf:type biopax:FragmentFeature .
      ?frag_feature biopax:featureLocation ?removed_chunk .
      ?removed_chunk rdf:type biopax:SequenceInterval .
      ?removed_chunk biopax:sequenceIntervalBegin ?site1 .
      ?site1 rdf:type biopax:SequenceSite .
      ?site1 biopax:positionStatus ?pos_stat_1 .
      ?site1 biopax:sequencePosition ?start .
      ?removed_chunk biopax:sequenceIntervalEnd ?site2 .
      ?site2 rdf:type biopax:SequenceSite .
      ?site2 biopax:positionStatus ?pos_stat_2 .
      ?site2 biopax:sequencePosition ?end .
      filter( ( ?site1 != ?site2 ) )
      filter( regex( str(?pos_stat_1) , \"^EQUAL$\") )
      filter( regex( str(?pos_stat_2) , \"^EQUAL$\") )
   }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}
  }


