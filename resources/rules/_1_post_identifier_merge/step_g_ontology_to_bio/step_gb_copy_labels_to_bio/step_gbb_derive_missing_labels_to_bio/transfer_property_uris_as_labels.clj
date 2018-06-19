`{:name "step-gbb_transfer-property-uris-as-labels"
  :description "Often, for ObjectProperties that do not have an rdfs:label, the label name is embedded in the URI. This rule finds these cases and transfers the URI as the label to bioworld."
  :head ((?/bioprop rdfs/label ?/lbl))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
          prefix franzOption_chunkProcessingAllowed: <franz:yes>
          prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
          prefix owl: <http://www.w3.org/2002/07/owl#>
          prefix obo: <http://purl.obolibrary.org/obo/>

          select distinct ?bioprop ?lbl {
            ?prop rdf:type owl:ObjectProperty .
            minus {?prop rdfs:label ?label .}
            bind(concat(\"Unnamed: \", str(?prop)) as ?lbl)
            ?id obo:IAO_0000219 ?prop .
            ?id obo:IAO_0000219 ?bioprop .
            filter (?prop != ?bioprop && contains(str(?bioprop),'http://ccp.ucdenver.edu/kabob/bio/'))
            minus {?bioprop rdfs:label ?label .}

          }"
  }





