`{:description "This rule defines a document set for Reactome's BioPAX download and a Reactome identifier.  It's a complete hack, finding any triple in the body in order to assert the document set triples in the head, but it's good enough to make one document set instead of the 6 previously generated for each Reactome continuant.",
  :name "add_reactome_document_set_to_ice",
  :reify ([?/record_set {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome record set"), :prefix "DS_"}]
          [?/download {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome download"), :prefix "D_"}] ),
 :head  ((?/record_set rdf/type ccp/IAO_EXT_0000012)
         (?/record_set obo/IAO_0000142 ?/download)
         (?/download rdfs/label "http://www.reactome.org/pages/download-data/biopax/Homo_sapiens.owl")
         (?/record_set rdfs/label "Reactome BioPAX record set, Homo sapiens"))
 :body "select ?s where {?s ?p ?o} LIMIT 1"
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
 
         
