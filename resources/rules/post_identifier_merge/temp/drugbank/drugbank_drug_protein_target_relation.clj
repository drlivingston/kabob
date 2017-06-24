;; ---------------------------------------------------------
;; --------- DrugBank Drug Protein Target Relation ---------
;; ---------------------------------------------------------
`{:name "drugbank-drug-to-protein-target-relation"
  :description "This rule generates bio-representations for the drug-target relationships cataloged by DrugBank where the target is a protein"
  :head (
         ;; create an interaction from the subclass of direct binding and binding
         (?/interaction rdfs/subClassOf obo/MI_0407) ; MI:direct interaction
         (?/interaction rdfs/subClassOf obo/GO_0005488) ; GO:binding

         ;; create a subclass of the protein target that will participate in the interaction
         (?/target_sc rdfs/subClassOf ?/target)

         ;; link the interaction to the protein target participant
         (?/target_participant_restriction rdf/type owl/Restriction)
         (?/target_participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/target_participant_restriction owl/someValuesFrom ?/target_sc)

         ;; create a subclass of the drug that will participate in the interaction
         (?/drug_sc rdfs/subClassOf ?/drug)

         ;; link the interaction to the drug participant
         (?/drug_participant_restriction rdf/type owl/Restriction)
         (?/drug_participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/drug_participant_restriction owl/someValuesFrom ?/drug_sc)

         ;; this interaction realizes the drug_role that inheres_in the drug
         (?/realizes_drug_role_restriction rdf/type owl/Restriction)
         (?/realizes_drug_role_restriction owl/onProperty obo/BFO_0000055) ; BFO:realizes
         (?/realizes_drug_role_restriction owl/someValuesFrom ?/inheres)

         ;; create a new anonymous class (CHEBI drug role) that inheres_in in this specific drug so that it can be realized by the restriction above
         (?/inheres_restriction rdf/type owl/Restriction)
         (?/inheres_restriction owl/onProperty obo/RO_0000052) ; RO:inheres_in
         (?/inheres_restriction owl/someValuesFrom ?/drug_sc)
         (?/inheres rdfs/subClassOf obo/CHEBI_23888) ; CHEBI:drug
         (?/inheres rdfs/subClassOf ?/inheres_restriction)

         ;; make the interaction be part of these 3 restrictions
         (?/interaction rdfs/subClassOf ?/target_participant_restriction)
         (?/interaction rdfs/subClassOf ?/drug_participant_restriction)
         (?/interaction rdfs/subClassOf ?/realizes_drug_role_restriction))

  :body ((?/drug_ice_id rdf/type ccp/IAO_EXT_0000426) ; ccp:DrugBank_drug_record
         (?/drug_ice_id obo/IAO_0000219 ?/drug) ; IAO:denotes
         (?/drug_field_value rdf/type ?/drug_ice_id)
         (?/drug_field_value rdf/type ccp/IAO_EXT_0000360) ; ccp:Drugbank_drug_record__drugbank_identifier_field_value 
         (?/drug_record obo/BFO_0000051 ?/drug_field_value) ; BFO:has_part

         ;; retrieve the target bioentity participant identifier
         (?/drug_record obo/BFO_0000051 ?/bioentity_field_value) ; BFO:has_part
         (?/bioentity_field_value rdf/type ccp/IAO_EXT_0000410) ; ccp:Drugbank_drug_record__targets_field_value
         (?/bioentity_field_value rdf/type ?/bioentity_ice_id) 
         (?/bioentity_ice_id obo/IAO_0000219 ?/target) ; IAO:denotes
          
         ;; the vast majority of targets contain a polypeptide record which links to a protein bio-entity
         ;; of 12,704 target records, all but 225 have an associated polypeptide record we should look into these, they are currently excluded (https://trello.com/c/JulbRanl)
         (?/polypeptide_ice_id rdf/type ccp/IAO_EXT_0000428) ; ccp:DrugBank_target_record
         (?/polypeptide_ice_id obo/IAO_0000219 ?/target) ; IAO:denotes
         (?/polypeptide_field_value rdf/type ?/polypeptide_ice_id)
         (?/polypeptide_field_value rdf/type ccp/IAO_EXT_0000447) ; ccp:Drugbank_target_record__polypeptides_field_value
         (?/target_record obo/BFO_0000051 ?/polypeptide_field_value)) ; BFO:has_part

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/target ?/drug)
                          :ns "ccp" :prefix "I_"}]
          [?/target_sc {:ln (:sha-1 ?/target ?/drug)
                       :ns "ccp" :prefix "B_"}]
          [?/drug_sc {:ln (:sha-1 ?/target ?/drug)
                       :ns "ccp" :prefix "D_"}]
          [?/target_participant_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/drug_participant_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/realizes_drug_role_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/inheres_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/inheres {:ln (:sha-1 "drugrole" ?/drug)
                          :ns "ccp" :prefix "D_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed" "franz:yes"]]}
  }