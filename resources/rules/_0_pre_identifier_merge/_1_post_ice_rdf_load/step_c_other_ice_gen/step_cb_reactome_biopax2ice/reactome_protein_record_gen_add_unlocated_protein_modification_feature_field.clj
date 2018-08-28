`{:name "step-cb_reactome-protein-record-gen_add-unlocated-modification-feature-field-value"
  :description "This rule finds any previously reified protein record from Reactome and traces out its sequence modifications (not cleavages, which are described separately) via a unification xref to extract the PSI-MOD modification type ID assuming it is not precisely located."
  ;; (run-rules "http://amc-tantor.ucdenver.pvt:10035" "kabob-dev" "kb2" "kb2" "/Users/elizabethwhite/Documents/kabob-rules/output/human/revised/tier0/" "/Users/elizabethwhite/Documents/kabob-rules/reactome/human/revised/tier0/create-protein-record-reactome-1e.clj")
  :head (
          (?/prot_record obo/BFO_0000051 ?/modification_record)  ;; has_part
          (?/modification_record rdf/type ccp/IAO_EXT_0001527) ;; Reactome feature field value
          (?/modification_record rdf/type ccp/IAO_EXT_0001586) ;; Reactome modification feature record

          (?/modification_record obo/BFO_0000051 ?/modification_type_record)  ;; has_part
          (?/modification_type_record rdf/type ccp/IAO_EXT_0001547) ;; Reactome modification type field value
          (?/modification_type_record rdf/type ccp/IAO_EXT_0001583) ;; Reactome sequence modification vocabulary record
          (?/modification_type_record obo/BFO_0000051 ?/modification_xref_record)
          (?/modification_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field value
          (?/modification_xref_record rdf/type ccp/IAO_EXT_0001572) ;; unification xref record
          (?/modification_xref_record obo/BFO_0000051 ?/modification_xref_db_field)
          (?/modification_xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; xref database field

          (?/modification_xref_record obo/BFO_0000051 ?/modification_xref_id_field)
          (?/modification_xref_id_field rdf/type ccp/IAO_EXT_0001520) ;; xref database id field
          (?/modification_xref_id_field rdfs/label ?/psi_mod_id)
          (?/modification_xref_id_field rdf/type ccp/IAO_EXT_0001454)) ;; PSI-MOD identifier


  :reify ([?/modification_record {:ln (:sha-1 "Reactome modification feature record" "MOD" ?/psi_mod_id )
                                  :ns "kice" :prefix "R_"}]
           [?/modification_type_record {:ln (:sha-1 "Reactome sequence modification vocabulary record" "MOD" ?/psi_mod_id)
                                        :ns "kice" :prefix "R_"}]
           [?/modification_xref_record {:ln (:sha-1 "Reactome unification xref record" "MOD" ?/psi_mod_id)
                                        :ns "kice" :prefix "R_"}]
           [?/modification_xref_db_field {:ln (:sha-1 "Reactome unification xref record db field" "MOD")
                                          :ns "kice" :prefix "F_"}]
           [?/modification_xref_id_field {:ln (:regex ":" "_" ?/psi_mod_id)
                                          :ns "kice" :prefix ""}])

  :body
  "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX obo: <http://purl.obolibrary.org/obo/>
   SELECT distinct ?psi_mod_id ?prot_record {
     ?prot_record rdf:type ccp:IAO_EXT_0001513 .
     ?prot_record obo:IAO_0000219 ?prot .
     ?prot rdf:type biopax:Protein .

     ?prot biopax:feature ?mod_feature .
     ?mod_feature rdf:type biopax:ModificationFeature .
     ?mod_feature biopax:modificationType ?mod_type .
     ?mod_type rdf:type biopax:SequenceModificationVocabulary .
     ?mod_type biopax:xref ?mod_xref .
     ?mod_xref rdf:type biopax:UnificationXref .
     ?mod_xref biopax:db ?psi_mod_db .
     filter( regex( str(?psi_mod_db) , \"^MOD$\") )
     ?mod_xref biopax:id ?psi_mod_id .

     OPTIONAL {
         ?mod_feature biopax:featureLocation ?seq_site .
         ?seq_site rdf:type biopax:SequenceSite .
         ?seq_site biopax:positionStatus ?pos_stat .
         ?seq_site biopax:sequencePosition ?mod_aa_position .
     }

     filter( !  bound(?pos_stat) )
}"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

  }