`{:name "reactome-protein-record-gen_add-uniprot-isoform-identifier-field-value"
  :description "This rule finds any previously reified protein record from Reactome and traces out its parent entity via a unification xref to extract the protein's UniProt Isoform ID."
  :head (
          (?/prot_record obo/BFO_0000051 ?/entity_record)  ;; has_part
          ;; TODO: replace dc/source line below with a proper field value
          (?/entity_record dc/source ?/entity_ref)
          (?/entity_record rdf/type ccp/IAO_EXT_0001518) ;; Reactome entity reference field value
          (?/entity_record rdf/type ccp/IAO_EXT_0001551) ;; Reactome protein reference record

          (?/entity_record obo/BFO_0000051 ?/entity_xref_record)  ;; has_part
          (?/entity_xref_record rdf/type ccp/IAO_EXT_0001572) ;; unification xref record
          (?/entity_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field value
          (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_db_field)
          (?/entity_xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; xref database field

          (?/entity_xref_record obo/BFO_0000051 ?/entity_xref_id_field)
          (?/entity_xref_id_field rdf/type ccp/IAO_EXT_0001520) ;; xref database id field
          (?/entity_xref_id_field rdfs/label ?/up_id)
          (?/entity_xref_id_field rdf/type ccp/IAO_EXT_0001495) ;; uniprot isoform identifier

          )

  :reify ([?/entity_record {:ln (:sha-1 "Reactome protein reference record" "UniProt Isoform" ?/up_id)
                            :ns "ccp" :prefix "R_"}]
           [?/entity_xref_record {:ln (:sha-1 "Reactome unification xref record" "UniProt Isoform" ?/up_id)
                                  :ns "ccp" :prefix "R_"}]
           [?/entity_xref_id_field {:ln (:localname ?/up_id)
                                    :ns "ccp" :prefix "UNIPROT_"}]
           [?/entity_xref_db_field {:ln (:sha-1 "Reactome unification xref recorddb field" "UniProt Isoform")
                                    :ns "ccp" :prefix "F_"}])
  :sparql-string
  "PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX obo: <http://purl.obolibrary.org/obo/>

   SELECT ?entity_ref ?prot_record ?up_id {
     ?prot_record rdf:type ccp:IAO_EXT_0001513 .
     ?prot_record obo:IAO_0000219 ?prot .
     ?prot rdf:type biopax:Protein .

     ?prot biopax:entityReference ?entity_ref .
     ?entity_ref biopax:xref ?entity_xref .
     ?entity_xref rdf:type biopax:UnificationXref .
     ?entity_xref biopax:db ?up_db .
     filter( regex( str(?up_db) , \"^UniProt Isoform$\") )
     ?entity_xref biopax:id ?up_id .
   }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

  }