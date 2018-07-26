`{:name "step-gbb_transfer-concept-uris-as-labels"
  :description "There are some owl:Classes that do not have an rdfs:label. This rule finds these cases and transfers the URI as the label to bioworld."
  :head ((?/bioentity rdfs/label ?/lbl))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
          prefix franzOption_chunkProcessingAllowed: <franz:yes>
          prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          prefix owl: <http://www.w3.org/2002/07/owl#>
          prefix obo: <http://purl.obolibrary.org/obo/>

          select ?lbl ?bioentity {
             ?c rdf:type owl:Class .
             minus {?c rdfs:label ?label}
             ?id obo:IAO_0000219 ?c .
             ?id obo:IAO_0000219 ?bioentity .
             filter (?c != ?bioentity && contains(str(?bioentity),'http://ccp.ucdenver.edu/kabob/bio/'))
             bind(concat(\"Unnamed: \", str(?c)) as ?lbl)
             filter (!contains (str(?c), 'bnode'))
             filter (!contains (str(?c), 'kabob'))
           }"
  }





