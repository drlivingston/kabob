`{:name "step-gcb_temporarily_link_ontology_to_bio_concepts"
  :description "This rule creates a link between ontology concepts and bioworld concepts that are denoted by the same identifier. This link is considered temporary and will be removed later in the build process."
  :head ((?/concept ccp/temporary_link ?/bio_concept))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>

                  select distinct ?concept_id ?concept ?bio_concept  {
                    ?concept_id obo:IAO_0000219 ?concept .
                    ?concept_id obo:IAO_0000219 ?bio_concept .
                    filter (?concept != ?bio_concept && contains(str(?bio_concept),'http://ccp.ucdenver.edu/kabob/bio/'))
                    }"
  }
