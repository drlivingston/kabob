;; ------------------------------------------------------------------------
;; --------- Copy Owl Restriction to Bio ---------
;; ------------------------------------------------------------------------
`{:name          "copy-anonymous-nodes-to-bio"
  :description   "This rule makes a copy of all non-owl:Restriction anonymous node instances and connects the copy to a generated Anonymous Node record instance."
  :head          ((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
                   (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
                   (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
                   (?/id_field_value rdf/type ?/id)
                   (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
                   (?/id obo/IAO_0000219 ?/blank_node)
                   (?/id obo/IAO_0000219 ?/bio_blank_node)) ; IAO:denotes
  :reify         ([?/bio_blank_node {:ln (:localname ?/blank_node)
                                      :ns "ccp" :prefix "BN_"}]
                   [?/anon_record {:ln (:sha-1 ?/blank_node)
                                          :ns "ccp" :prefix "R_"}]
                   [?/id_field_value {:ln (:sha-1 ?/id)
                                      :ns "ccp" :prefix "F_" :suffix ""}]
                   [?/id {:ln (:sha-1 ?/blank_node)
                          :ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select ?blank_node {
                     # start at a node we know is in bio
                     ?id obo:IAO_0000219 ?s .
                     ?s owl:equivalentClass ?blank_node .
                     ?blank_node rdf:type owl:Class .
                     # the blank nodes we are after don't have a parent class
                     minus {?blank_node rdfs:subClassOf ?type} .
                  }"
  }