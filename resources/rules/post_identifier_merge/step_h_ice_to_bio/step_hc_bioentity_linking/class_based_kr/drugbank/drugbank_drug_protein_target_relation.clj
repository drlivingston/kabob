;; ---------------------------------------------------------
;; --------- DrugBank Drug Protein Target Relation ---------
;; ---------------------------------------------------------
`{:name          "drugbank-drug-to-protein-target-relation"
  :description   "This rule generates bio-representations for the drug-target relationships cataloged by DrugBank where the target is a protein"
  :head          (
                   ;; create an interaction from the subclass of direct binding and binding
                   (?/interaction rdfs/subClassOf ?/direct_interaction) ; MI:direct interaction
                   (?/interaction rdfs/subClassOf ?/binding) ; GO:binding

                   ;; create a subclass of the protein target that will participate in the interaction
                   (?/target_sc rdfs/subClassOf ?/target_protein)

                   ;; link the interaction to the protein target participant
                   (?/target_participant_restriction rdf/type owl/Restriction)
                   (?/target_participant_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/target_participant_restriction owl/someValuesFrom ?/target_sc)

                   ;; create a subclass of the drug that will participate in the interaction
                   (?/drug_sc rdfs/subClassOf ?/drug)

                   ;; link the interaction to the drug participant
                   (?/drug_participant_restriction rdf/type owl/Restriction)
                   (?/drug_participant_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/drug_participant_restriction owl/someValuesFrom ?/drug_sc)

                   ;; this interaction realizes the drug_role that inheres_in the drug
                   (?/realizes_drug_role_restriction rdf/type owl/Restriction)
                   (?/realizes_drug_role_restriction owl/onProperty ?/realizes) ; BFO:realizes
                   (?/realizes_drug_role_restriction owl/someValuesFrom ?/inheres)

                   ;; create a new anonymous class (CHEBI drug role) that inheres_in in this specific drug so that it can be realized by the restriction above
                   (?/inheres_restriction rdf/type owl/Restriction)
                   (?/inheres_restriction owl/onProperty ?/inheres_in) ; RO:inheres_in
                   (?/inheres_restriction owl/someValuesFrom ?/drug_sc)
                   (?/inheres rdfs/subClassOf ?/drug_role)  ; CHEBI:drug
                   (?/inheres rdfs/subClassOf ?/inheres_restriction)

                   ;; make the interaction be part of these 3 restrictions
                   (?/interaction rdfs/subClassOf ?/target_participant_restriction)
                   (?/interaction rdfs/subClassOf ?/drug_participant_restriction)
                   (?/interaction rdfs/subClassOf ?/realizes_drug_role_restriction))

  :reify         ([?/interaction {:ln (:sha-1 "interaction" ?/target_protein ?/drug)
                                  :ns "ccp" :prefix "I_"}]
                   [?/target_sc {:ln (:sha-1 ?/target_protein ?/drug "target")
                                 :ns "ccp" :prefix "B_"}]
                   [?/drug_sc {:ln (:sha-1 ?/target_protein ?/drug "drug")
                               :ns "ccp" :prefix "B_"}]
                   [?/target_participant_restriction {:ln (:restriction)
                                                      :ns "ccp" :prefix "RS_"}]
                   [?/drug_participant_restriction {:ln (:restriction)
                                                    :ns "ccp" :prefix "RS_"}]
                   [?/realizes_drug_role_restriction {:ln (:restriction)
                                                      :ns "ccp" :prefix "RS_"}]
                   [?/inheres_restriction {:ln (:restriction)
                                           :ns "ccp" :prefix "RS_"}]
                   [?/inheres {:ln (:sha-1 "drugrole" ?/drug)
                               :ns "ccp" :prefix "B_"}])

  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  SELECT ?target_protein ?drug ?binding ?has_participant ?realizes ?inheres_in ?drug_role ?drugbank_drug_record ?direct_interaction
  WHERE {
    ?drugbank_drug_record rdf:type ccp:IAO_EXT_0000426 . # ccp:DrugBank_record
    ?drugbank_drug_record obo:BFO_0000051 ?drugbank_identifier_field_value .
    ?drugbank_identifier_field_value rdf:type ccp:IAO_EXT_0000360 . # ccp:Drugbank_drug_record__drugbank_identifier_field_value
    ?drugbank_identifier_field_value rdf:type ?drugbank_identifier .
    ?drugbank_identifier rdfs:subClassOf ccp:IAO_EXT_0001309 . # CCP:drugbank_identifier
    ?drugbank_identifier obo:IAO_0000219 ?drug .

    # retrieve the target bioentity participant identifier
    ?drugbank_drug_record obo:BFO_0000051 ?target_record_as_field_value .
    ?target_record_as_field_value rdf:type ccp:IAO_EXT_0000410 . # ccp:Drugbank_drug_record__targets_field_value
    ?target_record_as_field_value obo:BFO_0000051 ?polypeptide_record_as_field_value .
    ?polypeptide_record_as_field_value rdf:type ccp:IAO_EXT_0000447 . # ccp:Drugbank_target_record__polypeptides_field_value

    # the vast majority of targets contain a polypeptide record which links to a protein bio-entity
    # of 12,704 target records, all but 225 have an associated polypeptide record we should look into these, they are currently excluded (https://trello.com/c/JulbRanl)
    ?polypeptide_record_as_field_value obo:BFO_0000051 ?identifier_field_value .
    ?identifier_field_value rdf:type ccp:IAO_EXT_0000527 . # CCP:Drugbank_polypeptide_record__identifier_field_value
    ?identifier_field_value rdf:type ?target_identifier .
    ?target_identifier rdfs:subClassOf* ccp:IAO_EXT_0000188 . # CCP:protein_identifier
    ?target_identifier obo:IAO_0000219 ?target_protein .


     {
              select ?direct_interaction {
                                         ccp:MI_0407 obo:IAO_0000219 ?direct_interaction .
            filter (?direct_interaction != obo:MI_0407) .
                                         }
              }
            {
             select ?binding {
                              ccp:GO_0005488 obo:IAO_0000219 ?binding .
                              filter (?binding != obo:GO_0005488) .
                              }
             }
             {
              select ?has_participant {
                                      ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
                                      filter (?has_participant != obo:RO_0000057) .
                                      }
              }
             {
              select ?realizes {
                               ccp:BFO_0000055 obo:IAO_0000219 ?realizes .
                               filter (?realizes != obo:BFO_0000055) .
                               }
             }
             {
              select ?inheres_in {
                                  ccp:RO_0000052 obo:IAO_0000219 ?inheres_in .
                                  filter (?inheres_in != obo:RO_0000052) .
                                  }
              }
              {
               select ?drug_role {
                                 ccp:CHEBI_23888 obo:IAO_0000219 ?drug_role .
                                 filter (?drug_role != obo:CHEBI_23888) .
                                 }
               }
     }"

  }