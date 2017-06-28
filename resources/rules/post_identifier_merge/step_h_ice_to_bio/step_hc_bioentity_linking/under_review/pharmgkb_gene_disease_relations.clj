;; --------------------------------------------------
;; --------- PharmGKB Gene Disease Relation ---------
;; --------------------------------------------------
`{:name "pharmgkb-gene-disease-relation"
  :description "This rule assigns relations between genes and diseases"
  ;; this need to be hashed and have proper classes
  :head (
         ;; create a subclass of the drug that will participate in the interaction
         (?/disease_sc rdfs/subClassOf ?/disease)
         
         ;; create a subclass of the gene_or_gene_product that will participate in the interaction
         (?/gene_or_gene_product_sc rdfs/subClassOf ?/gene_or_gene_product)
          
         ;; create interaction subclass
         (?/interaction rdfs/subClassOf obo/MI_0000) ; MI:interaction
         
         ;; link the interaction to the disease participant
         (?/disease_participant_restriction rdf/type owl/Restriction)
         (?/disease_participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/disease_participant_restriction owl/someValuesFrom ?/disease_sc)

         ;; link the interaction to the gene or gene product participant
         (?/gene_or_gene_product_participant_restriction rdf/type owl/Restriction)
         (?/gene_or_gene_product_participant_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
         (?/gene_or_gene_product_participant_restriction owl/someValuesFrom ?/gene_or_gene_product_sc)

         ;; this interaction realizes the disease_role that inheres_in the disease
         (?/realizes_disease_role_restriction rdf/type owl/Restriction)
         (?/realizes_disease_role_restriction owl/onProperty obo/BFO_0000055) ; BFO:realizes
         (?/realizes_disease_role_restriction owl/someValuesFrom ?/inheres)

         ;; create a new anonymous class that inheres_in in this specific disease so that it can be realized by the restriction above
         (?/inheres_restriction rdf/type owl/Restriction)
         (?/inheres_restriction owl/onProperty obo/RO_0000052) ; RO:inheres_in
         (?/inheres_restriction owl/someValuesFrom ?/disease_sc)
         (?/inheres rdfs/subClassOf obo/CHEBI_23888) ; CHEBI:drug - NEEDS TO BE FIXED
         (?/inheres rdfs/subClassOf ?/inheres_restriction)

         ;;make the interaction be necessarily part of these 3 restrictions
         ;;still need to add a sufficient definition
         (?/interaction rdfs/subClassOf ?/disease_participant_restriction)
         (?/interaction rdfs/subClassOf ?/gene_or_gene_product_participant_restriction)
         (?/interaction rdfs/subClassOf ?/realizes_disease_role_restriction))

  :body ((?/record rdf/type ccp/IAO_EXT_0000823) ; ccp:PharmGKB_relation_record
         
         ;; retrieve entity type data field 1 - gene
         (?/record obo/BFO_0000051 ?/type_1_field_value) ; BFO:has_part
         (?/type_1_field_value rdf/type ccp/IAO_EXT_0001038) ; ccp:PharmGKB_relation_record__entity_1_type_field_value
         (?/type_1_field_value rdf/type ?/type_1_type_id)
         (?/type_1_id obo/IAO_0000219 ?/gene_or_gene_product_sc) ; IAO:denotes
         
         ;; retrieve entity data identifier field 1 - gene
         (?/record obo/BFO_0000051 ?/identifier_1_field_value) ; BFO:has_part
         (?/identifier_1_field_value rdf/type ccp/IAO_EXT_0001036) ; ccp:PharmGKB_relation_record__entity_1_identifier_field_value
         (?/identifier_1_field_value rdf/type ?/identifier_1_ice_id)
         (?/identifier_1_ice_id obo/IAO_0000219 ?/gene_or_gene_product_sc) ; IAO:denotes
         
         ;; retrieve entity type data field 2 - disease
         (?/record obo/BFO_0000051 ?/type_2_field_value) ; BFO:has_part
         (?/type_2_field_value rdf/type ccp/IAO_EXT_0001040) ; ccp:PharmGKB_relation_record__entity_2_type_field_value
         (?/type_2_field_value rdf/type ?/type_2_id)
         (?/type_2_id obo/IAO_0000219 ?/disease_sc) ; IAO:denotes
         
         ;; retrieve entity data identifier field 2 - disease
         (?/record obo/BFO_0000051 ?/identifier_2_field_value) ; BFO:has_part
         (?/identifier_2_field_value rdf/type ccp/IAO_EXT_0001041) ; ccp:PharmGKB_relation_record__entity_2_identifier_field_value
         (?/identifier_2_field_value rdf/type ?/identifier_2_ice_id)
         (?/identifier_2_ice_id obo/IAO_0000219 ?/disease_sc)) ; IAO:denotes

  :reify ([?/interaction {:ln (:sha-1 "interaction" ?/gene_or_gene_product ?/disease)
                          :ns "ccp" :prefix "I_"}]
          [?/gene_or_gene_product_sc {:ln (:sha-1 ?/gene_or_gene_product ?/disease)
                       :ns "ccp" :prefix "B_"}]
          [?/disease_sc {:ln (:sha-1 ?/gene_or_gene_product ?/disease)
                       :ns "ccp" :prefix "D_"}]
          [?/gene_or_gene_product_participant_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/disease_participant_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/realizes_disease_role_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/inheres_restriction {:ln (:restriction)
                 :ns "ccp" :prefix "R_"}]
          [?/inheres {:ln (:sha-1 "drugrole" ?/disease)
                          :ns "ccp" :prefix "D_"}])    
  }