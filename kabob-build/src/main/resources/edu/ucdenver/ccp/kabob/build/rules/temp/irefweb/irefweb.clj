
`{:name "irefweb-binary-interactions"
  :description "IRefWeb distinguishes among three different categories of protein interactions:
  those that involve a single protein interacting with itself (intra-molecular),
  those that involve two proteins (binary), and those that involve >2 proteins (n-ary).
  Documentation for IRefWeb is available [here](http://irefindex.org/wiki/index.php?title=README_MITAB2.6_for_iRefIndex).
  This rule generates bio-representations for all binary protein interactions cataloged by
  IRefWeb. Binary interactions are indicated by an 'X' in the 'edge type' column of IRefWeb
  records. For each binary interaction, this rule creates a subclass of the 'interaction type'
  (MI Ontology concept), and that subclass is restricted using ro:has_participant to the two
  interacting proteins. The rule also places a cardinality restriction of 2 on the interaction
  subclass for the ro:has_participant property."
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction
         (?/interaction rdfs/label ?/int_type_label) ; transfer label to the subclass

         ;; create subclasses of the proteins
         (?/protein1_sc rdfs/subClassOf ?/protein1)
         ;; TODO: UNCOMMENT below once kabob proteins have labels
         ;;(?/protein1_sc rdfs/label ?/protein1_label) ; transfer label to the subclass
         (?/protein2_sc rdfs/subClassOf ?/protein2)
         ;; TODO: UNCOMMENT below once kabob proteins have labels
         ;;(?/protein2_sc rdfs/label ?/protein2_label) ; transfer label to the subclass

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         (?/r2 rdf/type owl/Restriction)
         (?/r2 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r2 owl/someValuesFrom ?/protein2_sc)

         ;; create a cardinality restriction of 2 on the has_participant property
         (?/rcard rdf/type owl/Restriction)
         (?/rcard owl/onProperty obo/RO_0000057) ; has_participant
         (?/rcard owl/cardinality 2) ;; binary interactions must have 2 participants.

         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/r2)
         (?/interaction rdfs/subClassOf ?/rcard))

  :body
  ;; get binary interaction records
  ((_/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (_/edge_field obo/IAO_0000219 ["X" "en"])   ;; "X" signifies binary interaction
   (_/interaction_record obo/BFO_0000051 _/edge_field)  ;; bfo/has_part

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (_/super_record obo/BFO_0000051 _/interaction_record) ;; bfo/has_part

   ;; get the two distinct interactors for this binary interaction
   (_/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (_/super_record obo/BFO_0000051 ?/interactor_record2) ;; bfo/has_part
   (?/interactor_record2 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)
   (!= ?/interactor_record1 ?/interactor_record2)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 _/id_field1) ;; bfo/has_part
   (_/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/id_field1 obo/IAO_0000219 _/id1) ;; iao/denotes
   (_/id1 obo/IAO_0000219 ?/protein1) ;; iao/denotes

   ;; TODO: UNCOMMENT below once kabob proteins have labels
   ;;(?/protein1 rdfs:label ?/protein1_label) ;; get the protein label so that it can be
   ;; transfered to the generated protein subclass

   ;; get the bioentity (protein) corresponding to interactor 2
   (?/interactor_record2 obo/BFO_0000051 _/id_field2) ;; bfo/has_part
   (_/id_field2 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (_/id_field2 obo/IAO_0000219 _/id2) ;; iao/denotes
   (_/id2 obo/IAO_0000219 ?/protein2) ;; iao/denotes

   ;; TODO: UNCOMMENT below once kabob proteins have labels
   ;;(?/protein2 rdfs:label ?/protein2_label) ;; get the protein label so that it can be
   ;; transfered to the generated protein subclass


   ;; get the unique interaction identifier
   (_/interaction_record obo/BFO_0000051 _/interaction_checksum_field) ;; bfo/has_part
   (_/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (_/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (_/interaction_record obo/BFO_0000051 _/interaction_type_record) ;; bfo/has_part
   (_/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (_/interaction_type_record obo/BFO_0000051 _/int_type_id_field) ;; bfo/has_part
   (_/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (_/int_type_id_field obo/IAO_0000219 _/int_type_id_ice) ;; iao/denotes
   (_/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes
   (?/int_type_id rdfs/label ?/int_type_label) ;; grab the interaction_type label so that it can be
   ;;                                             applied to the generated interaction_type subclass
   ;; get the detection method
   (_/interaction_record obo/BFO_0000051 _/interaction_detection_method_record) ;; bfo/has_part
   (_/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (_/interaction_detection_method_record obo/BFO_0000051 _/detection_method_id_field) ;; bfo/has_part
   (_/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (_/detection_method_id_field obo/IAO_0000219 ?/detection_method_id)) ;; iao/denotes

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/r2 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/rcard {:ln (:restriction)
                    :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}]
          [?/protein2_sc {:ln (:sha-1 ?/interaction ?/protein2)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]
                             ["franzOption_chunkProcessingAllowed" "franz:yes"]]}
  }




`{:name "irefweb-unary-interactions"
  :description "IRefWeb distinguishes among three different categories of protein interactions:
  those that involve a single protein interacting with itself (intra-molecular),
  those that involve two proteins (binary), and those that involve >2 proteins (n-ary).
  Documentation for IRefWeb is available [here](http://irefindex.org/wiki/index.php?title=README_MITAB2.6_for_iRefIndex).
  This rule generates bio-representations for all unary (intra-molecular) protein interactions
  cataloged by IRefWeb. Unary interactions are indicated by an 'Y' in the 'edge type' column of
  IRefWeb records. For each unary interaction, this rule creates a subclass of the
  'interaction type' (MI Ontology concept), and that subclass is restricted using ro:has_participant
  to the single 'interacting' protein. It is important to note that this these are intra-molecular
  interactions (one protein molecule interacting with itself), and not for example interactions
  that form a dimer using two identical molecules of the same protein. The rule also places a
  cardinality restriction of 1 on the interaction subclass for the ro:has_participant property."
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction
         (?/interaction rdfs/label ?/int_type_label) ; transfer label to the subclass

         ;; create subclasses of the single protein involved
         (?/protein1_sc rdfs/subClassOf ?/protein1)
         ;; TODO: UNCOMMENT below once kabob proteins have labels
         ;;(?/protein1_sc rdfs/label ?/protein1_label) ; transfer label to the subclass

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         ;; create a cardinality restriction of 2 on the has_participant property
         (?/rcard rdf/type owl/Restriction)
         (?/rcard owl/onProperty obo/RO_0000057) ; has_participant
         (?/rcard owl/cardinality 1) ;; unary interactions must have 1 participant.

         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/rcard))

  :body
  ;; get binary interaction records
  ((?/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (?/edge_field obo/IAO_0000219 ["Y" "en"])   ;; "Y" signifies intra-molecular interaction
   (?/interaction_record obo/BFO_0000051 ?/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field) ;; bfo/has_part
   (?/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (?/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ;; bfo/has_part
   (?/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
   (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
   (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ;; bfo/has_part
   (?/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (?/int_type_id_field obo/IAO_0000219 ?/int_type_id_ice) ;; iao/denotes
   (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes
   (?/int_type_id rdfs/label ?/int_type_label) ;; grab the interaction_type label so that it can be
   ;;                                             applied to the generated interaction_type subclass

   ;; get the detection method
   (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ;; bfo/has_part
   (?/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (?/interaction_detection_method_record obo/BFO_0000051 ?/detection_method_id_field) ;; bfo/has_part
   (?/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (?/super_record obo/BFO_0000051 ?/interaction_record) ;; bfo/has_part

   ;; there is only one interactor for the unary interaction
   (?/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 ?/id_field1) ;; bfo/has_part
   (?/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field1 obo/IAO_0000219 ?/id1) ;; iao/denotes
   (?/id1 obo/IAO_0000219 ?/protein1) ;; iao/denotes
   ;; TODO: UNCOMMENT below once kabob proteins have labels
   ;;(?/protein1 rdfs:label ?/protein1_label) ;; get the protein label so that it can be
   ;; transfered to the generated protein subclass
   )

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/rcard {:ln (:restriction)
                    :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }



`{:name "irefweb-n-ary-interactions"
  :description "IRefWeb distinguishes among three different categories of protein interactions:
  those that involve a single protein interacting with itself (intra-molecular),
  those that involve two proteins (binary), and those that involve >2 proteins (n-ary).
  Documentation for IRefWeb is available [here](http://irefindex.org/wiki/index.php?title=README_MITAB2.6_for_iRefIndex).
  This rule generates bio-representations for all n-ary protein interactions cataloged by
  IRefWeb, where n>2. N-ary interactions are indicated by an 'C' in the 'edge type' column of
  IRefWeb records. The 'C' is referred to as 'complex' in the IRefWeb documentation, however this
  is misleading as many of the n-ary interactions are only associations determined from an assay,
  and are not necessarily protein complexes (as defined by the GO:protein_complex concept [GO:0043234]:
  'A stable macromolecular complex composed (only) of two or more polypeptide subunits...').
  For each n-ary interaction, this rule creates a subclass of the 'interaction type' (MI Ontology concept),
  and that subclass is restricted using ro:has_participant to one of the n interacting proteins. N-ary
  interactions are composed from multiple IRefWeb records as each record catalogs only a single
  participant in the n-ary interaction. IRefWeb records involved in an n-ary interaction share a unique
  interaction identifier which is used by the rule to connect all n participants for a given n-ary interaction.
  The rule also places a minimum-cardinality restriction of 3 on the interaction subclass for the
  ro:has_participant property."
  :head ((?/interaction rdfs/subClassOf ?/int_type_id) ;interaction
         (?/interaction rdfs/label ?/int_type_label) ; transfer label to the subclass

         ;; create subclasses of the proteins
         (?/protein1_sc rdfs/subClassOf ?/protein1)
         ;; TODO: UNCOMMENT below once kabob proteins have labels
         ;;(?/protein1_sc rdfs/label ?/protein1_label) ; transfer label to the subclass

         (?/r1 rdf/type owl/Restriction)
         (?/r1 owl/onProperty obo/RO_0000057) ; has_participant
         (?/r1 owl/someValuesFrom ?/protein1_sc)

         ;; create a cardinality restriction of >2 on the has_participant property
         (?/rcard rdf/type owl/Restriction)
         (?/rcard owl/onProperty obo/RO_0000057) ; has_participant
         (?/rcard owl/minCardinality 3) ;; n-ary interactions must have >2 participant.

         (?/interaction rdfs/subClassOf ?/r1)
         (?/interaction rdfs/subClassOf ?/rcard))

  :body
  ;; get n-ary interaction records
  ((?/edge_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_edgeTypeDataField1)
   (?/edge_field obo/IAO_0000219 ["C" "en"])   ;; "C" signifies "complex" in IRefWeb, but really mean n-ary interaction where n>2
   (?/interaction_record obo/BFO_0000051 ?/edge_field)  ;; bfo/has_part

   ;; get the unique interaction identifier
   (?/interaction_record obo/BFO_0000051 ?/interaction_checksum_field) ;; bfo/has_part
   (?/interaction_checksum_field kiao/hasTemplate iaoirefweb/IRefWebInteraction_checksumInteractionDataField1)
   (?/interaction_checksum_field obo/IAO_0000219 ?/unique_interaction_id) ;; iao/denotes

   ;; get the interaction type name and ID
   (?/interaction_record obo/BFO_0000051 ?/interaction_type_record) ;; bfo/has_part
   (?/interaction_type_record kiao/hasTemplate iaoirefweb/IRefWebInteractionTypeSchema1)
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_name_field) ;; bfo/has_part
   (?/int_type_name_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeNameDataField1)
   (?/int_type_name_field obo/IAO_0000219 ?/int_type_name) ;; iao/denotes
   (?/interaction_type_record obo/BFO_0000051 ?/int_type_id_field) ;; bfo/has_part
   (?/int_type_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionType_interactionTypeIdDataField1)
   (?/int_type_id_field obo/IAO_0000219 ?/int_type_id_ice) ;; iao/denotes
   (?/int_type_id_ice obo/IAO_0000219 ?/int_type_id) ;; iao/denotes
   (?/int_type_id rdfs/label ?/int_type_label) ;; grab the interaction_type label so that it can be
   ;;                                             applied to the generated interaction_type subclass

   ;; get the detection method
   (?/interaction_record obo/BFO_0000051 ?/interaction_detection_method_record) ;; bfo/has_part
   (?/interaction_detection_method_record kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethodSchema1)
   (?/interaction_detection_method_record obo/BFO_0000051 ?/detection_method_id_field) ;; bfo/has_part
   (?/detection_method_id_field kiao/hasTemplate iaoirefweb/IRefWebInteractionDetectionMethod_detectionMethodIdDataField1)
   (?/detection_method_id_field obo/IAO_0000219 ?/detection_method_id) ;; iao/denotes

   ;; there is a "super" record in IRefWeb that contains references
   ;; to the interaction record and interactor record(s)
   (?/super_record obo/BFO_0000051 ?/interaction_record) ;; bfo/has_part

   ;; there are two interactors, but one is a place holder that is used to bind
   ;; multiple records together to form n-ary interactions. The placeholder is
   ;; identical to the unique interaction ID, so we select the other interactor
   ;; (which will be linked to a bioentity (protein))
   (?/super_record obo/BFO_0000051 ?/interactor_record1) ;; bfo/has_part
   (?/interactor_record1 kiao/hasTemplate iaoirefweb/IRefWebInteractorSchema1)

   ;; get the bioentity (protein) corresponding to interactor 1
   (?/interactor_record1 obo/BFO_0000051 ?/id_field1) ;; bfo/has_part
   (?/id_field1 kiao/hasTemplate iaoirefweb/IRefWebInteractor_uniqueIdDataField1)
   (?/id_field1 obo/IAO_0000219 ?/id1) ;; iao/denotes
   (!= ?/id1 ?/unique_interaction_id)
   (?/id1 obo/IAO_0000219 ?/protein1) ;; iao/denotes
   ;; TODO: UNCOMMENT below once kabob proteins have labels
   ;;(?/protein1 rdfs:label ?/protein1_label) ;; get the protein label so that it can be
   ;; transfered to the generated protein subclass
   )

  ;; we take advantage of the fact that IRefWeb has
  ;; created a unique id for the interaction. This will allow us to reproducibly create
  ;; a hash for the interaction that can be matched across records.
  :reify ([?/interaction {:ln (:sha-1 ?/unique_interaction_id)
                          :ns "kbio" :prefix "I_"}]
          [?/r1 {:ln (:restriction)
                 :ns "kbio" :prefix "R_"}]
          [?/rcard {:ln (:restriction)
                    :ns "kbio" :prefix "R_"}]
          [?/protein1_sc {:ln (:sha-1 ?/interaction ?/protein1)
                          :ns "kbio" :prefix "P_"}])

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }
