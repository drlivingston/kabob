`{:name "identifier-denotes-bioentity"
  :description "links identifiers to bioentities using iao:denotes"
  :head ((?/id obo/IAO_0000219 ?/entity)) ;; obo:mentions
  :reify ([?/entity {:ln (:sha-1 ?/id_set)
                     :ns "ccp" :prefix "B_" :suffix ""}])
  ;:body ((?/idset rdf/type ccp/IAO_EXT_0000316) ;; ccp:identifier set
  ;       (?/idset obo/RO_0002351 ?/id)) ;; obo:has_member
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?id ?id_set {
                     ?id_set rdf:type ccp:IAO_EXT_0000316 .
                     ?id_set obo:RO_0002351 ?id .
                  }"
  }
