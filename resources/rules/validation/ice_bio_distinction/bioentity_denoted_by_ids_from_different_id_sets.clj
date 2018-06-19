`{:name        "validation_bioentity-denoted-by-ids-from-different-id-sets-EXPECT-0"
  :description "If an entity on the bio side of kabob is denoted by more than one identifier, all identifiers that denote that entity should belong to the same identifier set. If this rule returns any hits, then there is an issue."
  :head        ()
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
         select ?id1 ?id2 ?bioentity {
            ?id1 obo:IAO_0000219 ?bioentity .
            ?idset1 obo:RO_0002351 ?id1 .
            ?id2 obo:IAO_0000219 ?bioentity .
            filter (?id1 != ?id2)
            ?idset2 obo:RO_0002351 ?id2 .
            filter (?idset1 != ?idset2)
            }"
  }
