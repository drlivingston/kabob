;; -----------------------------------------------
;; --------- ncbitaxon identifier typing ---------
;; -----------------------------------------------
;; specifically types the ontology identifiers
`{:name "ncbitaxon-identifier-typing"
  :head ((?/id rdf/type ccp/IAO_EXT_0000204)) ; ncbi taxon ontology identifier
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id {
                  ?id rdf:type ccp:IAO_EXT_0000088 .
                  ?id obo:IAO_0000219 ?c .
                  ?c oboInOwl:hasOBONamespace 'ncbi_taxonomy'}" 
  }