(ns edu.ucdenver.ccp.kabob.kr.entities
  (use edu.ucdenver.ccp.kr.sparql))

(defn ice-denoting [kb entity]
  (query-template kb
                  '?/ice
                  `((?/ice obo/IAO_0000219 ~entity))))

(defn denoted-entity [kb ice]
  (query-template kb
                  '?/entity
                  `((~ice obo/IAO_0000219 ?/entity))))