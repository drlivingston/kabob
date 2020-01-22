;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "biogrid-interactions"
  :description   "This rule generates bio-representations for all binary protein interactions cataloged by IRefWeb"
  :head          (;; create subclasses of interaction type and id
                   (?/interaction rdfs/subClassOf ?/interaction_type) ;interaction

                   ;; create subclasses of the proteins
                   (?/bioentity1_sc rdfs/subClassOf ?/bioentity1)
                   ;(?/bioentity1_sc rdfs/label ?/bioentity1_label) ; transfer label to the subclass
                   (?/bioentity2_sc rdfs/subClassOf ?/bioentity2)
                   ;(?/bioentity2_sc rdfs/label ?/bioentity2_label) ; transfer label to the subclass

                   ;; create restriction for protein interators
                   (?/bioentity1_sc_restriction rdf/type owl/Restriction)
                   (?/bioentity1_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/bioentity1_sc_restriction owl/someValuesFrom ?/bioentity1_sc)
                   (?/bioentity2_sc_restriction rdf/type owl/Restriction)
                   (?/bioentity2_sc_restriction owl/onProperty ?/has_participant) ; RO:has_participant
                   (?/bioentity2_sc_restriction owl/someValuesFrom ?/bioentity2_sc)

                   ;; create a cardinality restriction of 2 on the has_participant property
                   ;(?/card_restriction rdf/type owl/Restriction)
                   ;(?/card_restriction owl/onProperty obo/RO_0000057) ; RO:has_participant
                   ;(?/card_restriction owl/cardinality 2)   ; binary interactions must have 2 participants.

                   ;; join interaction restrictions
                   (?/interaction rdfs/subClassOf ?/bioentity1_sc_restriction)
                   (?/interaction rdfs/subClassOf ?/bioentity2_sc_restriction)
                   ;(?/interaction rdfs/subClassOf ?/card_restriction)

                   (?/super_record obo/IAO_0000219 ?/interaction))

  :reify         ([?/interaction {:ln (:sha-1 ?/interaction_irig_identifier)
                                  :ns "kbio" :prefix "B_"}]
                   [?/bioentity1_sc_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}]
                   [?/bioentity2_sc_restriction {:ln (:restriction)
                                                 :ns "kbio" :prefix "RS_"}]
                   ;[?/card_restriction {:ln (:restriction)
                   ;                     :ns "kbio" :prefix "R_"}]
                   [?/bioentity1_sc {:ln (:sha-1 ?/interaction ?/bioentity1)
                                     :ns "kbio" :prefix "B_"}]
                   [?/bioentity2_sc {:ln (:sha-1 ?/interaction ?/bioentity2)
                                     :ns "kbio" :prefix "B_"}])

  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    "
  ;select * { #?record ?bioentity1 ?bioentity2 ?physical_association {
  ;
  ;                                                                                       {
  ;                                                                                        select ?physical_association {
  ;                                                                                                                      ccp:MI_0915 obo:IAO_0000219 ?physical_association .
  ;                                                                                                                      filter (?physical_association != obo:MI_0915) . # obo:physical_association
  ;                                                                                                                                      }
  ;                                                                                                                      }
  ;
  ;                                                                                               {
  ;                                                                                                select ?has_participant {
  ;                                                                                                                         ccp:RO_0000057 obo:IAO_0000219 ?has_participant .
  ;                                                                                                                         filter (?has_participant != obo:RO_0000057) .
  ;                                                                                                                         }
  ;                                                                                                }
  ;                                                                                        {
  ;                                                                                         select ?has_gene_template {
  ;                                                                                                                    ?has_gene_template_id obo:IAO_0000219 obo_pr:has_gene_template .
  ;                                                                                                                    ?has_gene_template_id obo:IAO_0000219 ?has_gene_template .
  ;                                                                                                                    # ensure it's a kabob bioentity (not an obo bioentity)
  ;                                                                                                                                          filter (contains (str(?has_gene_template), 'http://ccp.ucdenver.edu/kabob/bio/'))
  ;                                                                                                                    }
  ;                                                                                         }
  ;                                                                                               {
  ;                                                                                                select ?protein_coding_gene {
  ;                                                                                                                             ccp:SO_0001217 obo:IAO_0000219 ?protein_coding_gene .
  ;                                                                                                                             filter (?protein_coding_gene != obo:SO_0001217) # OBO:protein_coding_gene
  ;                                                                                                   }
  ;                                                                                                                             }
  ;                                                                                                       {
  ;                                                                                                        select ?protein {
  ;                                                                                                                         ccp:CHEBI_36080 obo:IAO_0000219 ?protein .
  ;                                                                                                                         filter (?protein != obo:CHEBI_36080) .
  ;                                                                                                                         }
  ;                                                                                                        }
  ;                                                                                                {
  ;                                                                                                 select ?ncRNA {
  ;                                                                                                                ccp:SO_0000655 obo:IAO_0000219 ?ncRNA .
  ;                                                                                                                filter (?ncRNA != obo:SO_0000655) .
  ;                                                                                                                }
  ;                                                                                                 }
  ;
  ;
  ;                                                                                                       ?record rdf:type ccp:IAO_EXT_0001777 . # ccp:BIOGRID_PROTEIN_INTERACTION_RECORD
  ;                                                                                                           ?record obo:BFO_0000051 ?ncbi_gene_identifier_a_field_value .
  ;                                                                                                ?ncbi_gene_identifier_a_field_value rdf:type ccp:IAO_EXT_0001807 . # ccp:BIOGRID_PROTEIN_INTERACTION_RECORD___INTERACTOR_A_NCBI_GENE_IDENTIFIER_FIELD_VALUE
  ;                                                                                                           ?ncbi_gene_identifier_a_field_value rdf:type ?ncbi_gene_a_identifier .
  ;                                                                                                ?ncbi_gene_a_identifier obo:IAO_0000219 ?gene1 .
  ;                                                                                                # {
  ;                                                                                                   # get either the protein encoded by the gene
  ;                                                                                                                ?gene1 rdfs:subClassOf ?protein_coding_gene .
  ;                                                                                                                ?has_gene_template_r owl:someValuesFrom ?gene1 .
  ;                                                                                                                ?has_gene_template_r owl:onProperty ?has_gene_template .
  ;                                                                                                                ?bioentity1 rdfs:subClassOf ?has_gene_template_r .
  ;                                                                                                                ?bioentity1 rdfs:subClassOf* ?protein .
  ;                                                                                                                # } union {
  ;                                                                                                                           #    # or get the ncRNA if it's not a protein encoding gene
  ;                                                                                                                           #   ?has_gene_template_r owl:someValuesFrom ?gene1 .
  ;                                                                                                                           #   ?has_gene_template_r owl:onProperty ?has_gene_template .
  ;                                                                                                                           #   ?bioentity1 rdfs:subClassOf ?has_gene_template_r .
  ;                                                                                                                           #   ?bioentity1 rdfs:subClassOf* ?ncRNA .
  ;                                                                                                                           # }
  ;
  ;                                                                                                                           ?record obo:BFO_0000051 ?ncbi_gene_identifier_b_field_value .
  ;                                                                                                                           ?ncbi_gene_identifier_b_field_value rdf:type ccp:IAO_EXT_0001808 . # ccp:BIOGRID_PROTEIN_INTERACTION_RECORD___INTERACTOR_B_NCBI_GENE_IDENTIFIER_FIELD_VALUE
  ;                                                                                                                                             ?ncbi_gene_identifier_b_field_value rdf:type ?ncbi_gene_b_identifier .
  ;                                                                                                                           ?ncbi_gene_b_identifier obo:IAO_0000219 ?gene2 .
  ;                                                                                                                           # {
  ;                                                                                                                              # get either the protein encoded by the gene
  ;                                                                                                                                           ?gene2 rdfs:subClassOf ?protein_coding_gene .
  ;                                                                                                                                           ?has_gene_template_r2 owl:someValuesFrom ?gene2 .
  ;                                                                                                                                           ?has_gene_template_r2 owl:onProperty ?has_gene_template .
  ;                                                                                                                                           ?bioentity2 rdfs:subClassOf ?has_gene_template_r2 .
  ;                                                                                                                                           ?bioentity2 rdfs:subClassOf* ?protein .
  ;
  ;
  ;                                                                                                                              }

  }