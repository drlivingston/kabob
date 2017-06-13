;; ------------------------------------------------------------------------
;; --------- Copy Owl Restriction to Bio ---------
;; ------------------------------------------------------------------------
`{:name "copy-owl-restriction-to-bio"
  :description "This rule makes a copy of all owl:Restriction instances and connects the copy to a generated Restriction Record instance."
  :head ((?/restriction_record rdf/type ccp/IAO_EXT_0000305) ; ccp:OWL restriction record
          (?/restriction_record obo/BFO_0000051 ?/id_field_value) ; has part
          (?/id_field_value rdf/type ccp/IAO_EXT_0000345) ; ccp:OWL restriction identifier field value
          (?/id_field_value rdf/type ?/id)
          (?/id rdf/type ccp/IAO_EXT_0000352)
          (?/id obo/IAO_0000219 ?/obo_restriction)
          (?/id obo/IAO_0000219 ?/bio_restriction) ;; obo:denotes
          (?/bio_restriction rdf/type owl/Restriction))
  :reify ([?/bio_restriction {:ln (:restriction)
                     :ns "ccp" :prefix "RS_"}]
          [?/restriction_record {:ln (:sha-1 ?/obo_restriction)
                     :ns "ccp" :prefix "R_"}]
           [?/id_field_value {:ln (:sha-1 ?/id)
                              :ns "ccp" :prefix "F_" :suffix ""}]
           [?/id {:ln (:sha-1 ?/obo_restriction)
                  :ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select distinct ?obo_restriction where {
                    ?obo_restriction rdf:type owl:Restriction .
                    filter(contains(str(?obo_restriction), '/bnode/'))
                  }"
  }