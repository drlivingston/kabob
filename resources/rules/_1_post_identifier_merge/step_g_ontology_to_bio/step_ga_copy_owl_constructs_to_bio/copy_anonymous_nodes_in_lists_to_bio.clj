;; ------------------------------------------------------------------------
;; --------- Copy Owl Restriction to Bio ---------
;; ------------------------------------------------------------------------
`{:name          "step-ga_copy-anonymous-nodes-in-list-to-bio"
  :description   "This rule makes a copy of all non-owl:Restriction anonymous node instances and connects the copy to a generated Anonymous Node record instance."
  :head          ((?/anon_record rdf/type ccp/IAO_EXT_0001707) ; ccp:anonymous_node_record
                   (?/anon_record obo/BFO_0000051 ?/id_field_value) ; has part
                   (?/id_field_value rdf/type ccp/IAO_EXT_0001709) ; ccp:anonymous node identifier field value
                   (?/id_field_value rdf/type ?/id)
                   (?/id rdf/type ccp/IAO_EXT_0001710) ; ccp:anonymous_node_identifier
                   (?/id obo/IAO_0000219 ?/blank_node)
                   (?/id obo/IAO_0000219 ?/bio_blank_node)) ; IAO:denotes
  :reify         ([?/bio_blank_node {:ln (:localname ?/blank_node)
                                      :ns "kbio" :prefix ""}]
                   [?/anon_record {:ln (:sha-1 ?/blank_node)
                                          :ns "kice" :prefix "R_"}]
                   [?/id_field_value {:ln (:sha-1 ?/id)
                                      :ns "kice" :prefix "F_" :suffix ""}]
                   [?/id {:ln (:sha-1 ?/blank_node)
                          :ns "kice" :prefix "ID_" :suffix ""}])
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select distinct ?blank_node {
                  graph ?g {
                                      ?listmember rdf:type rdf:List .
                                      ?listmember rdf:first ?blank_node .
                                      filter (contains(str(?blank_node), '/bnode/'))
                                      minus {?blank_node rdf:type ?t}
                                      minus {?blank_node rdfs:subClassOf ?c}
                                      }
                 filter (!contains (str (?g), \"ccp-extension\"))
  }"
  }