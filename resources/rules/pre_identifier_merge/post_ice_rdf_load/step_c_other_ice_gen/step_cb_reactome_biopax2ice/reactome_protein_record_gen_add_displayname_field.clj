`{:name "reactome-protein-record-gen_add-display-name-field-value"
  :description "This rule finds any previously reified protein record from Reactome and records its display name field(s)."

  :head ((?/prot_record obo/BFO_0000051 ?/name_field)  ;; has_part
          (?/name_field rdf/type ccp/IAO_EXT_0001526) ;; Reactome display name field value
          (?/name_field rdfs/label ?/name))

  :reify ([?/name_field {:ln (:sha-1 "Reactome protein record display name field" ?/name)
                         :ns "ccp" :prefix "F_"}])

  :sparql-string
  "PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX obo: <http://purl.obolibrary.org/obo/>
   SELECT ?prot_record ?name {
     ?prot_record rdf:type ccp:IAO_EXT_0001513 .
     ?prot_record obo:IAO_0000219 ?prot .
     ?prot rdf:type biopax:Protein .
     ?prot biopax:displayName ?name .
   }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

  }
