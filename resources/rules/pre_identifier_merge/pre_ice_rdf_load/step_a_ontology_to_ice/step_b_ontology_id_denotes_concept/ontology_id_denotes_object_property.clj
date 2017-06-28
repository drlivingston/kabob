;; ----------------------------------------------------------------------
;; --------- Object Property Identifier Denotes Object Property ---------
;; ----------------------------------------------------------------------
`{:name "ontology-id-denotes-object-property-gen"
  :description "This rule generates an object property identifier for every non-top-level object property."
  :head ((?/id obo/IAO_0000219 ?/object_property) ; IAO:denotes
         (?/id rdf/type ccp/IAO_EXT_0000306)) ; CCP:object_property_identifier
  :reify ([?/id {:ln (:localname ?/object_property)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?object_property {
                  ?property_id rdf:type ccp:IAO_EXT_0000308 . # CCP:top-level_object_property_identifier
                  ?property_id obo:IAO_0000219 ?top_level_property . # IAO:denotes
                  ?object_property rdfs:subPropertyOf* ?top_level_property .
                  minus {?object_property owl:deprecated true} .
                  minus {?object_property rdf:type ccp:IAO_EXT_0000308} # CCP:top-level_object_property_identifier
                  # exclude ICE world properties (IAO, CCP extension ontology, OA ontology)
                  filter (!contains (str(?object_property), 'ext/IAO_'))
                  filter (!contains (str(?object_property), 'obo/IAO_'))
                  filter (!contains (str(?object_property), 'http://www.w3.org/ns/oa#'))
                  }"
  }