;; ---------------------------------------------------------
;; --------- DrugBank Drug Protein Target Relation ---------
;; ---------------------------------------------------------
`{:name          "drugbank-drug-to-protein-target-relation-instance-based-kr"
  :description   "This rule generates bio-representations for the drug-target relationships cataloged by DrugBank where the target is a protein"
  :head          (
                   ;; create an interaction as instances of both direct binding and binding
                   (?/interaction rdf/type ?/direct_interaction) ; MI:direct interaction
                   (?/interaction rdf/type ?/binding) ; GO:binding

                   ;; create an instance of the protein target that will participate in the interaction
                   (?/target_instance rdf/type ?/target_protein)

                   (?/interaction ?/has_participant ?/target_instance)

                   ;; create an instance of the drug that will participate in the interaction
                   (?/drug_instance rdf/type ?/drug)

                   (?/interaction ?/has_participant ?/drug_instance)

                   (?/drugbank_drug_record obo/IAO_0000219 ?/interaction))


  :reify         ([?/interaction {:ln (:sha-1 "interaction" ?/target_protein ?/drug)
                                  :ns "kbio" :prefix "I_"}]
                   [?/target_instance {:ln (:sha-1 ?/target_protein ?/drug "target")
                                 :ns "kbio" :prefix "B_"}]
                   [?/drug_instance {:ln (:sha-1 ?/target_protein ?/drug "drug")
                               :ns "kbio" :prefix "B_"}])

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