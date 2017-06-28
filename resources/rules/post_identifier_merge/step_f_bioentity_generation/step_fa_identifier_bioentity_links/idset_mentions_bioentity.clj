

`{:name "idset-mentions-bioentity"
  :description "links identifier sets to bioentities using IAO:mentions"
  :head ((?/id_set obo/IAO_0000142 ?/entity)) ;; obo:mentions
  :reify ([?/entity {:ln (:sha-1 ?/id_set)
                     :ns "ccp" :prefix "B_" :suffix ""}])
  ;;:body ((?/idset rdf/type ccp/IAO_EXT_0000316)) ;; ccp:identifier set
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  select ?id_set {
                  ?id_set rdf:type ccp:IAO_EXT_0000316 .
                  }"
  }

