`{:name "transfer-direct-links-from-ontology-concepts"
  :description "This rule transfers links between ontology components to representations of those components in bioworld."
  :head ((?/bio_concept ?/p ?/bio_other))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>

                  select ?bio_concept ?p ?bio_other {
                      ?concept_id obo:IAO_0000219 ?concept .
                      ?concept_id obo:IAO_0000219 ?bio_concept .
                      filter (?concept != ?bio_concept && contains(str(?bio_concept),'http://ccp.ucdenver.edu/obo/ext/'))

                      ?concept ?p ?other .

                      ?other_id obo:IAO_0000219 ?other .
                      ?other_id obo:IAO_0000219 ?bio_other .
                      filter (?other != ?bio_other && contains(str(?bio_other),'http://ccp.ucdenver.edu/obo/ext/'))
                  }"
  }
