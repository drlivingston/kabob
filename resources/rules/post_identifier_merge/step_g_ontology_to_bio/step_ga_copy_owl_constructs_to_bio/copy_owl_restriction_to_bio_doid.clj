;; ------------------------------------------------------------------------
;; --------- Copy Owl Restriction to Bio ---------
;; ------------------------------------------------------------------------
`{:name          "copy-owl-restriction-to-bio-doid"
  :description   "This rule makes a copy of all owl:Restriction instances and connects the copy to a generated Restriction Record instance."
  :head          ((?/restriction_record rdf/type ccp/IAO_EXT_0000305) ; ccp:OWL restriction record
                   (?/restriction_record obo/BFO_0000051 ?/id_field_value) ; has part
                   (?/id_field_value rdf/type ccp/IAO_EXT_0000345) ; ccp:OWL restriction identifier field value
                   (?/id_field_value rdf/type ?/id)
                   (?/id rdf/type ccp/IAO_EXT_0000352)
                   (?/id obo/IAO_0000219 ?/obo_restriction)
                   (?/id obo/IAO_0000219 ?/bio_restriction) ; IAO:denotes
                   (?/bio_restriction rdf/type owl/Restriction))
  :reify         ([?/bio_restriction {:ln (:localname ?/obo_restriction)
                                      :ns "ccp" :prefix "RS_"}]
                   [?/restriction_record {:ln (:sha-1 ?/obo_restriction)
                                          :ns "ccp" :prefix "R_"}]
                   [?/id_field_value {:ln (:sha-1 ?/id)
                                      :ns "ccp" :prefix "F_" :suffix ""}]
                   [?/id {:ln (:sha-1 ?/obo_restriction)
                          :ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select distinct ?obo_restriction {
                    ?ccp_id rdfs:subClassOf* ccp:IAO_EXT_0000209 . # ccp:doid_ontology_identifier
                    ?ccp_id obo:IAO_0000219 ?obo_id . # obo:denotes
                    ?obo_id (owl:equivalentClass|owl:intersectionOf|rdf:first|rdf:rest|owl:propertyChainAxiom|owl:someValuesFrom|rdfs:subClassOf)* ?obo_restriction .
                    ?obo_restriction rdf:type owl:Restriction .
                  }"
  }