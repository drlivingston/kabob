`{:name "transfer-object-property-labels"
  :description "This rule transfers labels to ObjectProperty instances in bio world"
  :head ((?/bio_p rdfs/label ?/label))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>

                  select ?p ?bio_p ?label {
                         ?p rdf:type owl:ObjectProperty .
                         ?p rdfs:label ?label .
                         ?property_id obo:IAO_0000219 ?p .
                         ?property_id obo:IAO_0000219 ?bio_p .
                         filter (?p != ?bio_p && contains(str(?bio_p),'http://ccp.ucdenver.edu/obo/ext/'))
                  }"
  }





