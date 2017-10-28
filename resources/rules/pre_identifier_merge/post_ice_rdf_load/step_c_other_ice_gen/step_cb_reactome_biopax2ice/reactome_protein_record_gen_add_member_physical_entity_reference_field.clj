`{:name "reactome-protein-record-gen_add-member-physical-entity-reference-field-value"
  :description "This rule finds any previously reified protein record from Reactome that belongs to a functional group of proteins/physical entities, and traces out the relation between the group entity and the member entities.  Like The Thing, we leave this relation in the ICE for now."

  :head ((?/prot_record obo/BFO_0000051 ?/member_prot_record)  ;; has_part
         (?/member_prot_record rdf/type ccp/IAO_EXT_0001528)) ;; Reactome member entity reference field value

  :sparql-string
  "PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX obo: <http://purl.obolibrary.org/obo/>
   SELECT ?prot_record ?member_prot_record {
      ?prot bp:memberPhysicalEntity ?member_prot .
      ?prot rdf:type bp:Protein .
      ?prot_record obo:IAO_0000219 ?prot .
      ?prot_record rdf:type ccp:IAO_EXT_0001513 .
      ?member_prot rdf:type bp:Protein .
      ?member_prot_record obo:IAO_0000219 ?member_prot .
      ?member_prot_record rdf:type ccp:IAO_EXT_0001513 .
   }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

          
  }
