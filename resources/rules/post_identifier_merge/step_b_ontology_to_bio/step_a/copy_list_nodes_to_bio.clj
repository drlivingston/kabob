`{:name "copy-list-nodes-to-bio"
  :description "This rule makes a copy of all rdf:List instances and connects the copy to a generated List Record (ccp:IAO_EXT_0000317) instance."
  :head ((?/list_record rdf/type ccp/IAO_EXT_0000317) ; ccp:RDF list record
          (?/list_record obo/BFO_0000051 ?/id_field_value) ; has part
          (?/id_field_value rdf/type ccp/IAO_EXT_0000346) ; ccp:RDF list identifier field value
          (?/id_field_value rdf/type ?/id)
          (?/id rdf/type ccp/IAO_EXT_0000354)
          (?/id obo/IAO_0000219 ?/listmember)
          (?/id obo/IAO_0000219 ?/bio_listmember) ; IAO:denotes
          (?/bio_listmember rdf/type rdf/List))
  :reify ([?/list_record {:ln (:sha-1 ?/listmember)
                           :ns "ccp" :prefix "R_"}]
           [?/bio_listmember {:ln (:sha-1 ?/listmember)
                              :ns "ccp" :prefix "L_"}]
           [?/id_field_value {:ln (:sha-1 ?/id)
                              :ns "ccp" :prefix "F_" :suffix ""}]
           [?/id {:ln (:sha-1 ?/listmember)
                  :ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  select distinct ?listmember {
                    ?listmember rdf:type rdf:List .
                  }"
  }