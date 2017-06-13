`{:name "copy-owl-alldisjointclasses-to-bio"
  :description "This rule makes a copy of all owl:AllDisjointClasses instances and connects the copy to a generated AllDisjointClasses Record (ccp:IAO_EXT_0000319) instance."
  :head ((?/adc_record rdf/type ccp/IAO_EXT_0000347) ; ccp:AllDisjointClasses record
					(?/adc_record obo/BFO_0000051 ?/id_field_value) ; has part
					(?/id_field_value rdf/type ccp/IAO_EXT_0000349) ; ccp:AllDisjointClasses identifier field value
					(?/id_field_value rdf/type ?/id)
          (?/id rdf/type ccp/IAO_EXT_0000353)
					(?/id obo/IAO_0000219 ?/adc)
					(?/id obo/IAO_0000219 ?/bio_adc) ;; obo:denotes
					(?/bio_adc rdf/type owl/AllDisjointClasses))
  :reify ([?/adc_record {:ln (:sha-1 ?/adc)
                     :ns "ccp" :prefix "R_"}]
          [?/bio_adc {:ln (:sha-1 ?/adc)
                  :ns "ccp" :prefix "ADC_"}]
					 [?/id_field_value {:ln (:sha-1 ?/id)
															:ns "ccp" :prefix "F_" :suffix ""}]
					 [?/id {:ln (:sha-1 ?/adc)
									:ns "ccp" :prefix "ID_" :suffix ""}])
  :sparql-string "select ?adc {
                    ?adc rdf:type owl:AllDisjointClasses .
                   }"
  }