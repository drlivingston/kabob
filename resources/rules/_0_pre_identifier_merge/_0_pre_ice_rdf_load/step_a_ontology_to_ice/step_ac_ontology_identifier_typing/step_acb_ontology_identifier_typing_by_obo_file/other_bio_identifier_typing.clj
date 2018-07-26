;; --------------------------------------------------------------------
;; --------- pathogen transmission ontology identifier typing ---------
;; --------------------------------------------------------------------
`{:name "step-acb_other-bio-identifier-typing"
  :description "This rule assumes that classes in non-ice ontology graphs that are not
                already a type of bio identifier, are indeed bio identifiers."
  :head ((?/id rdfs/subClassOf ccp/IAO_EXT_0000342)) ; CCP:identifier_of_a_biological_entity
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
         prefix franzOption_clauseReorderer: <franz:identity>
         prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
         prefix obo: <http://purl.obolibrary.org/obo/>
         prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
         select distinct ?id {
            {
             select ?c ?g {
                   graph ?g {
                             ?c rdf:type owl:Class .
                             }

                   filter (!contains (str(?g), 'ccp-extension'))
                   filter (!contains (str(?g), 'oa.owl'))
               }
             }
            minus { ?c owl:deprecated true }
            ?id obo:IAO_0000219 ?c . # IAO:denotes
            minus {?id rdfs:subClassOf ccp:IAO_EXT_0000342}
            minus {?c rdfs:subClassOf* <http://purl.obolibrary.org/obo/NCBITaxon#_taxonomic_rank> .}

            }"
  }