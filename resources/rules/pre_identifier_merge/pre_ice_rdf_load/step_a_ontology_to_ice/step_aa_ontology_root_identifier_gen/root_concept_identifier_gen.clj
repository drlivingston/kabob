;; -------------------------------------------
;; --------- Root Concept Assignment ---------
;; -------------------------------------------
`{:name "root-concept-identifier-gen"
  :description "This rule creates an identifier for each root ontology class and types it as a root concept identifier (IAO_EXT_0000190)"
  :head ((?/id rdf/type ccp/IAO_EXT_0000190) ; CCP:ontology_root_concept_identifer
         (?/id obo/IAO_0000219 ?/root)) ; IAO:denotes
  :reify ([?/id {:ln (:localname ?/root)
                 :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
  select ?root {
                ?root rdf:type owl:Class .
                # return classes that are subClassOf owl:Thing or classes that are not subClassOf anything
                # make sure to include classes that are only subclass of restrictions, however
                filter ((exists {?root rdfs:subClassOf owl:Thing})
                         || (not exists {?root rdfs:subClassOf ?class}
                         || (exists {?root rdfs:subClassOf ?r .
                             ?r rdf:type owl:Restriction .})))
                # but don't return classes that are subClassOf owl:Thing and subClass of some other class
                filter (not exists {?root rdfs:subClassOf owl:Thing .
                                    ?root rdfs:subClassOf ?class .
                                    filter (?class != owl:Thing)})
                # and also don't return classes that are subClassOf of a restriction and
                # subClass of some other non-restriction/non-owl:thing class
                filter (not exists {?root rdfs:subClassOf ?r .
                                    ?r rdf:type owl:Restriction .
                                    ?root rdfs:subClassOf ?class .
                                    filter (?class = owl:Thing)})
                filter (not exists {?root rdfs:subClassOf ?r .
                                    ?r rdf:type owl:Restriction .
                                    ?root rdfs:subClassOf ?class .
                                    filter (not exists {?class rdf:type owl:Restriction})})
                # exclude all deprecated classes
                filter (not exists {?root owl:deprecated true})
                # exclude any blank nodes
                filter (!contains(str(?root), '/bnode/'))
                # exclude the oboInOwl:ObsoleteClass class
                filter (?root != oboInOwl:ObsoleteClass)
                # exclude ICE world concepts (IAO, CCP extension ontology, OA ontology)
                filter (!contains (str(?root), 'ext/IAO_'))
                filter (!contains (str(?root), 'obo/IAO_'))
                filter (!contains (str(?root), 'http://www.w3.org/ns/oa#'))
                filter (!contains (str(?root), 'http://www.w3.org/2004/02/skos/core#'))
                filter (!contains (str(?root), 'http://www.w3.org/1999/02/22-rdf-syntax-ns#'))
}"
  }