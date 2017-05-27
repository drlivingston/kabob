`{:name "ice-denotes-entity"
  :head ((?/id obo/IAO_0000219 ?/entity)) ;; obo:mentions
  :body ((?/idset rdf/type ccp/IAO_EXT_0000316) ;; ccp:identifier set
         (?/idset obo/RO_0002351 ?/id)) ;; obo:has_member
  :reify ([?/entity {:ln (:sha-1 ?/idset)
                     :ns "ccp" :prefix "B_" :suffix ""}])}
