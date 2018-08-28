`{:name "step-cc_reactome_protein_record_gen_add_names_from_protein_references_field.clj"
  :description "This rule finds any previously reified protein record from Reactome that refers back to a UniProt entry via a protein reference record, and traces out any new names in the protein reference record."
  
  :head ((?/prot_ref_record obo/BFO_0000051 ?/name_field)  ;; has_part
         (?/name_field rdf/type ccp/IAO_EXT_0001525) ;; Reactome name field value
         (?/name_field rdfs/label ?/name))
  
  :reify ([?/name_field {:ln (:sha-1 "Reactome protein reference record name field" ?/name)
                            :ns "kice" :prefix "F_"}])

  :body
  "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
   PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   SELECT distinct ?prot_ref_record ?name {
     ?prot_ref rdf:type biopax:ProteinReference .
     ?prot_ref biopax:name ?name .
     ?prot_ref rdf:type biopax:ProteinReference .
     ?prot biopax:entityReference ?prot_ref .
     ?prot rdf:type biopax:Protein .

     ?prot_record obo:IAO_0000219 ?prot .
     ?prot_record rdf:type ccp:IAO_EXT_0001513 .
     ?prot_record obo:BFO_0000051 ?prot_ref_record .
     ?prot_ref_record rdf:type ccp:IAO_EXT_0001551 .
   }"

  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}


  }

