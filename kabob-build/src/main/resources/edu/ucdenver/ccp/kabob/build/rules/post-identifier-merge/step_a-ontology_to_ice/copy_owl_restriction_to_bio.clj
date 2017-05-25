;; ------------------------------------------------------------------------
;; --------- Copy Owl Restriction to Bio ---------
;; ------------------------------------------------------------------------
`{:name "copy-owl-restriction-to-bio"
  :description "This rule makes a copy of all owl:Restriction instances and connects the copy to a generated Restriction Record instance."
  :head ((?/restriction_record obo/IAO_0000219 ?/obo_restriction) ;; obo:denotes
         (?/restriction_record rdf/type ccp/IAO_EXT_0000305) ;; ccp:OWL restriction record
         (?/restriction_record obo/IAO_0000219 ?/bio_restriction) ;; obo:denotes
         (?/bio_restriction rdf/type owl/Restriction)
         (?/bio_restriction owl/onProperty ?/bio_property))
  :reify ([?/bio_restriction {:ln (:restriction)
                     :ns "ccp" :prefix "RS_"}]
          [?/restriction_record {:ln (:sha-1 ?/obo_restriction)
                     :ns "ccp" :prefix "R_"}])
  :sparql-string "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?obo_restriction ?bio_property where {
                  ?obo_restriction rdf:type owl:Restriction .
                  filter(contains(str(?obo_restriction), '/bnode/'))
                  ?obo_restriction owl:onProperty ?property .
                  ?property_id obo:IAO_0000219 ?property .
                  ?property_id obo:IAO_0000219 ?bio_property .
                  filter (?property != ?bio_property)    
                  }"
  }