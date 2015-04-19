;; -------------------------------------------
;; --------- RGD Pathway Ontology ----------  
;; -------------------------------------------
;; create ICE URI's for each term in the RGD Pathway Ontology
`{:name "rgd-pw-ontology-ice-gen"
  :head ((?/ice obo/IAO_0000219 ?/cls))
  :body ((?/cls oboInOwl/hasOBONamespace "pathway")
          (?/cls rdf/type owl/Class))
  :reify ([?/ice {:ln (:localname ?/subclass)
                  :ns "iaopw" :prefix "" :suffix "_ICE"}])
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"]]}
  }