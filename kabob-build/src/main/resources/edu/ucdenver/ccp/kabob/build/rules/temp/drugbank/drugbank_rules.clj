
`{:name "drugbank-drug-to-protein-target-relation"
  :description "The DrugBank resources lists targets for many of the drugs that it catalogs.
  When this rule was originally composed, of the 8,203 DrugBank records, 6,827 are annotated
  with at least one drug target. Most of the targets are proteins (12,479 out of 12,704). This
  rule generates bio-representations for the drug-target relationships cataloged by DrugBank
  where the target is a protein. The DrugBank documentation defines target as 'A protein,
  macromolecule, nucleic acid, or small molecule to which a given drug binds, resulting in an
  alteration of the normal function of the bound molecule and a desirable therapeutic effect.
  Drug targets are most commonly proteins such as enzymes, ion channels, and receptors.' We
  therefore model the drug-target interaction as a binding using concepts from both the Gene
  Ontology and the Molecular Interaction Ontology. Both the drug and target are modeled as
  participants in the binding, with the drug also modeled as inhereing the CHEBI:drug role."
  :head (
         (?/interaction rdfs/subClassOf obo/MI_0407) ;direct interaction
         (?/interaction rdfs/subClassOf obo/GO_0005488) ;binding

         ;; create a subclass of the target that will participate in this
         ;; particular binding
         (?/target rdfs/subClassOf ?/target_bioentity)

          ;; link the binding event to the target participant (protein in this case)
         (?/r_target_participant rdf/type owl/Restriction)
         (?/r_target_participant owl/onProperty obo/RO_0000057) ; has_participant
         (?/r_target_participant owl/someValuesFrom ?/target)

         ;; create a subclass of the drug molecule that will participate
         ;; in this particular binding
         (?/drug rdfs/subClassOf ?/drug_bioentity)

          ;; link the binding event to the drug
         (?/r_drug_participant rdf/type owl/Restriction)
         (?/r_drug_participant owl/onProperty obo/RO_0000057) ; has_participant
         (?/r_drug_participant owl/someValuesFrom ?/drug)

          ;; this binding realizes the drug_role that inheres_in the drug
         (?/r_realizes_drug_role rdf/type owl/Restriction)
         (?/r_realizes_drug_role owl/onProperty obo/BFO_0000055) ; realizes
         (?/r_realizes_drug_role owl/someValuesFrom ?/inheres)

         ;;create a new anonymous class that is CHEBI drug role
         ;;   that inheres_in in this specific drug
         ;;   so that it can be realized by the restriction above
         (?/r_inheres rdf/type owl/Restriction)
         (?/r_inheres owl/onProperty obo/RO_0000052) ;inheres_in
         (?/r_inheres owl/someValuesFrom ?/drug)

         (?/inheres rdfs/subClassOf obo/CHEBI_23888) ;drug (role)
         (?/inheres rdfs/subClassOf ?/r_inheres)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/interaction rdfs/subClassOf  ?/r_target_participant)
         (?/interaction rdfs/subClassOf ?/r_drug_participant)
         (?/interaction rdfs/subClassOf ?/r_realizes_drug_role))

  :body
  (
    ;; each DrugBank record has an ID field. We use that
    ;; ID to link to the drug bioentity
    (_/id_field kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
    (_/id_field obo/IAO_0000219 _/drug_id)
  	(_/drug_id obo/IAO_0000219 ?/drug_bioentity)

    ;; the majority of DrugBank records link to a Target record.
  	(_/base_record obo/BFO_0000051 _/id_field)
  	(_/base_record obo/BFO_0000051 _/target_record)
  	(_/target_record kiao/hasTemplate iaodrugbank/TargetSchema1)

    ;; the vast majority of targets contain a polypeptide record which
    ;; links to a protein bio-entity
    ;; of 12,704 target records, all but 225 have an associated polypeptide record
    ;; we should look into those 225 targets at some point as they are currently excluded
    (_/target_record obo/BFO_0000051 _/polypeptide_record)
    (_/polypeptide_record kiao/hasTemplate iaodrugbank/PolypeptideSchema1)
    (_/polypeptide_record obo/BFO_0000051 _/polypeptide_id_field)
    (_/polypeptide_id_field obo/IAO_0000219 _/protein_ice_id)
    (_/protein_ice_id obo/IAO_0000219 ?/target_bioentity)
  )

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/target_bioentity ?/drug_bioentity)
                          :ns "kbio" :prefix "I_"}]
          [?/target {:ln (:sha-1 ?/target_bioentity ?/drug_bioentity)
                    :ns "kbio" :prefix "T_"}]
          [?/drug {:ln (:sha-1 ?/target_bioentity ?/drug_bioentity)
                    :ns "kbio" :prefix "D_"}]
          [?/r_target_participant {:ln (:restriction)
                 :ns "kbio" :prefix "RESTR_"}]
          [?/r_drug_participant {:ln (:restriction)
                 :ns "kbio" :prefix "RESTR_"}]
          [?/r_realizes_drug_role {:ln (:restriction)
                 :ns "kbio" :prefix "RESTR_"}]
          [?/r_inheres {:ln (:restriction)
                 :ns "kbio" :prefix "RESTR_"}]
          [?/inheres {:ln (:sha-1 "drugrole" ?/drug_bioentity)
                          :ns "kbio" :prefix "D_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed" "franz:yes"]]}
  }
