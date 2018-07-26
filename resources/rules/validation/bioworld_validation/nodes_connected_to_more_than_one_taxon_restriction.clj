`{:name        "validation_nodes-connected-to-more-than-one-taxon-restriction-EXPECT-0"
  :description "Checks for bioentities that are subClassOf multiple taxonomy restrictions that reference different taxonomies."
  :head        ()
  :body
               "prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
               PREFIX obo: <http://purl.obolibrary.org/obo/>
               select distinct ?bioentity {

                   {
                    select ?only_in_taxon {
                                           kice:RO_0002160 obo:IAO_0000219 ?only_in_taxon .
                                           filter (?only_in_taxon != obo:RO_0002160) .
                                           }
                    }

                   ?r1 owl:onProperty ?only_in_taxon .
                   ?r1 owl:someValuesFrom ?taxon1 .
                   ?bioentity rdfs:subClassOf ?r1 .
                   ?bioentity rdfs:subClassOf ?r2 .
                   ?r2 owl:onProperty ?only_in_taxon .
                   ?r2 owl:someValuesFrom ?taxon2 .
                   filter (?taxon1 != ?taxon2)

               }"
  }
