`{:name "step-gcc_transfer-direct-links-from-ontology-concepts"
  :description "This rule transfers links between ontology components to representations of those components in bioworld."
  :head ((?/bio_concept ?/p ?/bio_other))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>

                 select distinct ?bio_concept ?p  ?bio_other {
                    ?concept ccp:temporary_link ?bio_concept .
                    ?concept ?p ?other .
                    ?other ccp:temporary_link ?bio_other .
                    }"
  }
