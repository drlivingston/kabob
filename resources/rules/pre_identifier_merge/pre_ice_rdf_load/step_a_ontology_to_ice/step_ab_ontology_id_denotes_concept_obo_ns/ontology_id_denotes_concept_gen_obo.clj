;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
;; create id denotes concept triple for every ontology concept
`{:name          "ontology-id-denotes-concept-gen-obo"
  :description   "This rule generates an ontology concept identifier for every non-root ontology concept with the OBO namespace. The Protein Ontology references some classes that use non-OBO namespaces. Source-specific rules have been written for those namespaces and these rules can be found in the same directory as this more general OBO rule."
  :head          ((?/id obo/IAO_0000219 ?/ontology_concept) ; IAO:denotes
                   (?/id rdfs/subClassOf ccp/IAO_EXT_0000088)) ; CCP:ontology_concept_identifier
  :reify         ([?/id {:ln (:localname ?/ontology_concept)
                         :ns "ccp" :prefix "" :suffix ""}])
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept {
                       ?ontology_concept rdf:type owl:Class .
                       # exclude deprecated concepts
                       minus { ?ontology_concept owl:deprecated true }
                       # exclude concepts that already have an identifier (non-obo concept identifiers have been created in step aa)
                       minus { ?identifier obo:IAO_0000219 ?ontology_concept }
                       # exclude blank nodes - we want concepts with identifiers here
                       filter (!contains(str(?ontology_concept), '/bnode/'))
                       # exclude the oboInOwl:ObsoleteClass class
                       filter (?ontology_concept != oboInOwl:ObsoleteClass)
                       # exclude ICE world concepts (IAO, CCP extension ontology, OA ontology)
                       filter (!contains (str(?ontology_concept), 'ext/IAO_'))
                       filter (!contains (str(?ontology_concept), 'obo/IAO_'))
                       filter (!contains (str(?ontology_concept), 'http://www.w3.org/ns/oa#'))
                       filter (!contains (str(?ontology_concept), 'http://www.w3.org/2004/02/skos/core#'))
                       filter (!contains (str(?ontology_concept), 'http://www.w3.org/1999/02/22-rdf-syntax-ns#'))
                  }"
  }
