;; -----------------------------------------------
;; --------- PharmGKB Drug Gene Relation ---------
;; -----------------------------------------------
`{:name "pharmgkb-abstract-interaction-relation"
  :description "This rule assigns an abstract interaction relation between pharmgkb entities"
  :head (

         (?/interaction rdfs/subClassOf obo/MI_0000) ;interaction
         
         ;; create a subclass of the gene that will participate in the interaction
         (?/entity1_sc rdfs/subClassOf ?/entity1)
          (?/entity2_sc rdfs/subClassOf ?/entity2)

         ;; link the interaction to the protein target participant
         (?/gene_or_gene_product_participant_restriction rdf/type owl/Restriction)
         (?/gene_or_gene_product_participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/gene_or_gene_product_participant_restriction owl/someValuesFrom ?/gene_or_gene_product_sc)

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
         (?/interaction rdfs/subClassOf ?/gene_or_gene_product_participant_restriction)
         (?/interaction rdfs/subClassOf ?/drug_participant_restriction)
         (?/interaction rdfs/subClassOf ?/realizes_drug_role_restriction))
  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/gene_or_gene_product ?/drug)
                          :ns "ccp" :prefix "I_"}]
           [?/gene_or_gene_product_sc {:ln (:sha-1 ?/gene_or_gene_product ?/drug)
                                       :ns "ccp" :prefix "B_"}]
           [?/drug_sc {:ln (:sha-1 ?/gene_or_gene_product ?/drug)
                       :ns "ccp" :prefix "D_"}]
           [?/gene_or_gene_product_participant_restriction {:ln (:restriction)
                                                            :ns "ccp" :prefix "R_"}]
           [?/drug_participant_restriction {:ln (:restriction)
                                            :ns "ccp" :prefix "R_"}]
           [?/realizes_drug_role_restriction {:ln (:restriction)
                                              :ns "ccp" :prefix "R_"}]
           [?/inheres_restriction {:ln (:restriction)
                                   :ns "ccp" :prefix "R_"}]
           [?/inheres {:ln (:sha-1 "drugrole" ?/drug)
                       :ns "ccp" :prefix "D_"}])

  :sparql-string

  "SELECT ?drug_sc ?gene_or_gene_product_sc
  WHERE {  ?record rdf:type ccp:IAO_EXT_0000823 . # ccp:PharmGKB_relation_record
?record obo:BFO_0000051 ?type_1_field_value . # BFO:has_part
?type_1_field_value rdf:type ccp:IAO_EXT_0001038 . # ccp:PharmGKB_relation_record__entity_1_type_field_value
?type_1_field_value rdf:type ?type_1_type_id .
?type_1_id obo:IAO_0000219 ?drug_sc . # IAO:denotes
?record obo:BFO_0000051 ?identifier_1_field_value . # BFO:has_part
?identifier_1_field_value rdf:type ccp:IAO_EXT_0001036 . # ccp:PharmGKB_relation_record__entity_1_identifier_field_value
?identifier_1_field_value rdf:type ?identifier_1_ice_id .
?identifier_1_ice_id obo:IAO_0000219 ?drug_sc .
?record obo:BFO_0000051 ?type_2_field_value . # BFO:has_part
?type_2_field_value rdf:type ccp:IAO_EXT_0001040 . ccp:PharmGKB_relation_record__entity_2_type_field_value
?type_2_field_value rdf:type ?type_2_id .
?type_2_id obo:IAO_0000219 ?gene_or_gene_product_sc . # IAO:denotes
?record obo:BFO_0000051 ?identifier_2_field_value . # BFO:has_part
?identifier_2_field_value rdf:type ccp:IAO_EXT_0001041 . ccp:PharmGKB_relation_record__entity_2_identifier_field_value
?identifier_2_field_value rdf:type ?identifier_2_ice_id .
?identifier_2_ice_id obo:IAO_0000219 ?gene_or_gene_product_sc . # IAO:denotes
}"


:body ((?/record rdf/type ccp/IAO_EXT_0000823) ;
         
         ;; retrieve entity type data field 1 - drug
         (?/record obo/BFO_0000051 ?/type_1_field_value) ;
         (?/type_1_field_value rdf/type ccp/IAO_EXT_0001038) ;
         (?/type_1_field_value rdf/type ?/type_1_type_id)
         (?/type_1_id obo/IAO_0000219 ?/drug_sc) ;
         
         ;; retrieve entity data identifier field 1 - drug
         (?/record obo/BFO_0000051 ?/identifier_1_field_value) ; BFO:has_part
         (?/identifier_1_field_value rdf/type ccp/IAO_EXT_0001036) ;
         (?/identifier_1_field_value rdf/type ?/identifier_1_ice_id)
         (?/identifier_1_ice_id obo/IAO_0000219 ?/drug_sc) ; IAO:denotes
         
         ;; retrieve entity type data field 2 - gene
         (?/record obo/BFO_0000051 ?/type_2_field_value) ; BFO:has_part
         (?/type_2_field_value rdf/type ccp/IAO_EXT_0001040) ;
         (?/type_2_field_value rdf/type ?/type_2_id)
         (?/type_2_id obo/IAO_0000219 ?/gene_or_gene_product_sc) ; IAO:denotes
         
         ;; retrieve entity data identifier field 2 - gene
         (?/record obo/BFO_0000051 ?/identifier_2_field_value) ; BFO:has_part
         (?/identifier_2_field_value rdf/type ccp/IAO_EXT_0001041) ;
         (?/identifier_2_field_value rdf/type ?/identifier_2_ice_id)
         (?/identifier_2_ice_id obo/IAO_0000219 ?/gene_or_gene_product_sc)) ; IAO:denotes


  }