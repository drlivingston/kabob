

`{:name "idset-mentions-bioentity"
  :description "links identifier sets to bioentities using IAO:mentions"
  :head ((?/idset obo/IAO_0000142 ?/entity)) ;; obo:mentions
  :body ((?/idset rdf/type ccp/IAO_EXT_0000316)) ;; ccp:identifier set
  :reify ([?/entity {:ln (:sha-1 ?/idset)
                     :ns "ccp" :prefix "B_" :suffix ""}])}

