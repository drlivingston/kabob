`{:name "reactome-protein-record-gen_add-cellular-locations"
  :description "This rule finds any previously reified protein record from Reactome and traces out its location via a unification xref to extract the protein's cellular location (a Gene Ontology cellular compartment)."

  :head (
         (?/prot_record obo/BFO_0000051 ?/cellular_location_record)  ;; has_part
         (?/cellular_location_record rdf/type ccp/IAO_EXT_0001521) ;; Reactome cellular location field value
         (?/cellular_location_record rdf/type ccp/IAO_EXT_0001584) ;; Reactome cellular location vocabulary record 
         ;; TODO: simplify the nesting of identifier field values below
         (?/cellular_location_record obo/BFO_0000051 ?/go_xref_record)  ;; has_part
         (?/go_xref_record rdf/type ccp/IAO_EXT_0001572) ;; unification xref record
         (?/go_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field value
         (?/go_xref_record obo/BFO_0000051 ?/go_xref_db_field)  ;; has_part
         (?/go_xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; xref database field
         (?/go_xref_record obo/BFO_0000051 ?/go_xref_id_field)  ;; has_part
         (?/go_xref_id_field rdf/type ccp/IAO_EXT_0001520) ;; xref database id field
         (?/go_xref_id_field rdfs/label ?/go_cc_id)
         (?/go_xref_id_field rdf/type ccp/IAO_EXT_0000200)) ;; Gene Ontology cellular component identifier
  
  :reify ([?/cellular_location_record {:ln (:sha-1 "Reactome cellular location vocabulary record" "GENE ONTOLOGY" ?/go_cc_id)
                          :ns "ccp" :prefix "R_"}]
          [?/go_xref_record {:ln (:sha-1 "Reactome unification xref record" "GENE ONTOLOGY" ?/go_cc_id)
                          :ns "ccp" :prefix "R_"}]
          [?/go_xref_id_field {:ln (:regex ":" "_" ?/go_cc_id)
                            :ns "ccp" :prefix ""}]
          [?/go_xref_db_field {:ln (:sha-1 "Reactome unification xref record db field" "GENE ONTOLOGY")
                            :ns "ccp" :prefix "F_" :suffix ""}])
  :sparql-string
    "PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
     PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
     PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
     PREFIX obo: <http://purl.obolibrary.org/obo/>
     SELECT ?prot_record ?go_cc_id {
       ?prot_record rdf:type ccp:IAO_EXT_0001513 .
       ?prot_record obo:IAO_0000219 ?prot .
       ?prot rdf:type biopax:Protein .

       ?prot biopax:cellularLocation ?go_cc .
       ?go_cc biopax:xref ?go_cc_xref .
       ?go_cc_xref rdf:type biopax:UnificationXref .
       ?go_cc_xref biopax:db ?go_db .
       filter( regex( str(?go_db) , \"^GENE ONTOLOGY$\") )
       ?go_cc_xref biopax:id ?go_cc_id .
      }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

  }
