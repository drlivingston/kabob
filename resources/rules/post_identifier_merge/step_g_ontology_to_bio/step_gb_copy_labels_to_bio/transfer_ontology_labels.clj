`{:name "transfer-ontology-labels"
  :description "This rule transfers labels to bio world"
  :head ((?/bioentity rdfs/label ?/label))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>

                  select ?bioentity ?label {
                         ?s rdfs:label ?label .
                         ?id obo:IAO_0000219 ?s .
                         ?id obo:IAO_0000219 ?bioentity .
                         filter (?s != ?bioentity && contains(str(?bioentity),'http://ccp.ucdenver.edu/obo/ext/'))
                  }"
  }





