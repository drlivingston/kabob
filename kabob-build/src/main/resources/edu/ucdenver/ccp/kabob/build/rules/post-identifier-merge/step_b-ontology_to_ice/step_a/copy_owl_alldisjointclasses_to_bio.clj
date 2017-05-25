`{:name "copy-owl-alldisjointclasses-to-bio"
  :description "This rule makes a copy of all owl:AllDisjointClasses instances and connects the copy to a generated AllDisjointClasses Record (ccp:IAO_EXT_0000319) instance."
  :head ((?/adc_record obo/IAO_0000219 ?/adc) ;; obo:denotes
  	(?/adc_record rdf/type obo/IAO_EXT_0000319) ;; ccp:OWL AllDisjointClasses record
  	(?/adc_record obo/IAO_0000219 ?/bio_adc) ;; obo:denotes
  	(?/bio_adc rdf/type owl/AllDisjointClasses)) 
  :reify ([?/adc_record {:ln (:sha-1 ?/adc)
                     :ns "ccp" :prefix "R_"}]
          [?/bio_adc {:ln (:sha-1 ?/adc)
                  :ns "ccp" :prefix "ADC_"}])
  :sparql-string "select ?adc {
                    ?adc rdf:type owl:AllDisjointClasses ."
  }