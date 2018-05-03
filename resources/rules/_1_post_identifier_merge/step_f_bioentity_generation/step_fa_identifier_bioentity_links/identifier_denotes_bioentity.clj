`{:name "step-fa_identifier-denotes-bioentity"
  :description "links identifiers to bioentities using iao:denotes"
  :head ((?/id obo/IAO_0000219 ?/entity)) ;; obo:mentions
  :reify ([?/entity {:ln (:sha-1 ?/id_set)
                     :ns "kbio" :prefix "B_" :suffix ""}])
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  select ?id ?id_set {
                     ?id_set rdf:type ccp:IAO_EXT_0000316 .
                     ?id_set obo:RO_0002351 ?id .
                  }"
  }
