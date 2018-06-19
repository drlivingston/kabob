;; ----------------------------------------------------------------------
;; --------- Object Property Identifier Denotes Object Property ---------
;; ----------------------------------------------------------------------
`{:name "step-ab_ontology-id-denotes-object-property-gen"
  :description "This rule generates an object property identifier for every non-top-level object property."
  :head ((?/id obo/IAO_0000219 ?/object_property) ; IAO:denotes
         (?/id rdfs/subClassOf ccp/IAO_EXT_0000306)) ; CCP:object_property_identifier
  :reify ([?/id {:ln (:localname ?/object_property)
                 :ns "kice" :prefix "" :suffix ""}])
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?object_property {
                  graph ?g {
                     ?object_property rdf:type owl:ObjectProperty .
                     minus{?object_property owl:deprecated true}
                     # exclude the oboInOwl:ObsoleteProperty property
                     filter (?object_property != oboInOwl:ObsoleteProperty)
                     # exclude ICE world properties (IAO, CCP extension ontology, OA ontology)
                     }
                     filter (!contains (str(?object_property), 'http://www.w3.org/2000/01/rdf-schema#'))
                     filter (!contains (str(?object_property), 'http://www.w3.org/ns/prov'))
                     filter (!contains (str(?object_property), 'ext/IAO_'))
                     filter (!contains (str(?object_property), 'obo/IAO_'))
                     filter (!contains (str(?object_property), 'http://www.w3.org/ns/oa#'))
                     filter (!contains (str(?ontology_concept), 'http://www.w3.org/2002/07/owl#'))
                    }"
  }