`{:name        "validation_ice-identifier-denotes-multiple-kabob-entities-EXPECT-0"
  :description "Tests that each identifier denotes at most one kabob bioentity. Excludes deprecated identifiers as they can denote multiple bioentities, e.g. secondary UniProt accessions."
  :head        ()
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
         select ?id ?entity {
              ?id obo:IAO_0000219 ?entity1 .
              minus {?id rdfs:subClassOf ccp:IAO_EXT_0001711} # CCP:deprecated_identifier
              filter (contains(str(?entity1), 'kabob'))
              ?id obo:IAO_0000219 ?entity2 .
              filter (?entity1 != ?entity2)
              filter (contains(str(?entity2), 'kabob'))
         }"
  }
