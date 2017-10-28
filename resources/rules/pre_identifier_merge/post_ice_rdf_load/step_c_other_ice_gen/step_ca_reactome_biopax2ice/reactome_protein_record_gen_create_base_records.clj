`{
  :name "reactome-protein-record-gen_create-base-records"
  :description "This rule finds any protein record described in Reactome and traces out its link to a unification xref to extract the protein's Reactome ID.  Several Reactome protein records may correspond to the same UniProt ID, but the different records represent the protein in different modified forms or in different cellular compartments."

  :head (
         (?/record_set obo/BFO_0000051 ?/prot_record) ;; has_part

         (?/prot_record obo/IAO_0000219 ?/prot) ;; obo:denotes
         
         (?/prot_record rdf/type ccp/IAO_EXT_0001513)  ;; Reactome protein record
         (?/prot_record obo/BFO_0000051 ?/xref_record)  ;; has_part
         (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; Reactome xref field value
         (?/xref_record rdf/type ccp/IAO_EXT_0001572) ;; Reactome unification xref 
         
         (?/xref_record obo/BFO_0000051 ?/xref_db_field)
         (?/xref_db_field rdf/type ccp/IAO_EXT_0001519)  ;; Reactome unification xref db field value
         (?/xref_record obo/BFO_0000051 ?/xref_id_field)
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001520)  ;; Reactome unification xref database id field value
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001517)  ;; Reactome identifier field value
         (?/xref_id_field rdfs/label ?/react_id))

  :reify ([?/prot_record {:ln (:sha-1 "Reactome protein record" ?/react_id)
                  :ns "ccp" :prefix "R_"}]
          [?/xref_record {:ln (:sha-1 "Reactome unification xref record" "Reactome" ?/react_id)
                  :ns "ccp" :prefix "R_"}]
          [?/xref_id_field {:ln (:localname ?/react_id)
                  :ns "ccp" :prefix "REACTOME_"}]
          [?/xref_db_field {:ln (:sha-1 "Reactome unification xref record db field" "Reactome")
                   :ns "ccp" :prefix "F_"}])

  :sparql-string
  "PREFIX biopax: <http://www.biopax.org/release/biopax-level3.owl#>
   PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
   PREFIX dc: <http://purl.org/dc/elements/1.1/>
   PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    SELECT ?record_set ?react_id ?prot {
       ?prot rdf:type biopax:Protein .                    # a protein
       ?prot biopax:xref ?xref .                          # with an xref
       ?xref rdf:type biopax:UnificationXref .            # that's a unification xref
       ?xref biopax:db ?react_db .                        # to a Reactome database
       filter( regex( str(?react_db) , \"^Reactome$\") )
       ?xref biopax:id ?react_id .                        # and an identifier

        {
        # find the Reactome record set, i.e. the record set that is sourced from the biopax.zip file
          select ?record_set {
                       ?record_set rdf:type ccp:IAO_EXT_0000012 . # ccp:record_set
                       ?record_set dc:source ?source .
                       ?source dc:source <http://www.reactome.org/download/current/biopax.zip> .
          }
       }
    }"
  
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]
                             ["franzOption_clauseReorderer" "franz:identity"]]}

          
  }
