`{:name          "copy-owl-alldisjointclasses-to-bio"
  :description   "This rule makes a copy of all owl:AllDisjointClasses instances and connects the copy to a generated AllDisjointClasses Record (ccp:IAO_EXT_0000319) instance."
  :head          ((?/adc_record rdf/type ccp/IAO_EXT_0000347) ; ccp:AllDisjointClasses record
                   (?/adc_record obo/BFO_0000051 ?/id_field_value) ; has part
                   (?/id_field_value rdf/type ccp/IAO_EXT_0000349) ; ccp:AllDisjointClasses identifier field value
                   (?/id_field_value rdf/type ?/id)
                   (?/id rdf/type ccp/IAO_EXT_0000353)
                   (?/id obo/IAO_0000219 ?/adc)
                   (?/id obo/IAO_0000219 ?/bio_adc)         ;; obo:denotes
                   (?/bio_adc rdf/type owl/AllDisjointClasses))
  :reify         ([?/adc_record {:ln (:sha-1 ?/adc)
                                 :ns "ccp" :prefix "R_"}]
                   [?/bio_adc {:ln (:sha-1 ?/adc)
                               :ns "ccp" :prefix "ADC_"}]
                   [?/id_field_value {:ln (:sha-1 ?/id)
                                      :ns "ccp" :prefix "F_" :suffix ""}]
                   [?/id {:ln (:sha-1 ?/adc)
                          :ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
		prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
prefix obo: <http://purl.obolibrary.org/obo/>
prefix owl: <http://www.w3.org/2002/07/owl#>
		prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select ?adc {
                    ?adc rdf:type owl:AllDisjointClasses .
                   }"
  }