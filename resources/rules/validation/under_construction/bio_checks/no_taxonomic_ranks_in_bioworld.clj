`{:name        "validation_no-taxonomic-ranks-in-bioworld-EXPECT-0"
  :description "checks that none of the NCBITaxonomy taxonomic rank classes have been ported into bioworld."
  :head        '()
  :body "select ?c {
    ?c rdfs:subClassOf* <http://purl.obolibrary.org/obo/NCBITaxon#_taxonomic_rank> .
    ?id obo:IAO_0000219 ?bio .
    filter (contains (str(?bio), 'http://ccp.ucdenver.edu/kabob/bio/'))
  }"
  }
