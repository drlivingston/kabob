`{:name "copy-list-nodes-to-bio"
  :description "This rule makes a copy of all rdf:List instances and connects the copy to a generated List Record (ccp:IAO_EXT_0000317) instance."
  :head ((?/list_record obo/IAO_0000219 ?/listmember) ;; obo:denotes
        (?/list_record rdf/type ccp/IAO_EXT_0000317) ;; ccp:RDF list record
        (?/list_record obo/IAO_0000219 ?/bio_listmember) ;; obo:denotes
        (?/bio_listmember rdf/Type rdf/List))
  :reify ([?/list_record {:ln (:sha-1 ?/listmember)
                     :ns "ccp" :prefix "R_"}]
          [?/bio_listmember {:ln (:sha-1 ?/listmember)
                     :ns "ccp" :prefix "L_"}])
  :sparql-string "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> 
                  select distinct ?listmember {
                    ?listmember rdf:type rdf:List .
                  }"
  }