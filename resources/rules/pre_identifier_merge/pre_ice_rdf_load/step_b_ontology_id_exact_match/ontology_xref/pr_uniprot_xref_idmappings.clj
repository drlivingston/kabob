;; ----------------------------------------------
;; --------- PR-to-UniProt xref id mappings ---------
;; ----------------------------------------------
`{:name "pr-uniprot-xref-idmappings"
  :description "This rule creates protein ontology id to uniprot id mappings"
  :head ((?/pr_id skos/exactMatch ?/uniprot_id))
  :reify ([?/uniprot_id {:ln (:localname ?/uniprot_identifier)
                  :ns "ccp" :prefix "UNIPROT_" :suffix ""}])
  :sparql-string "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?pr_id ?uniprot_identifier {
                  ?pr_id rdfs:subClassOf ccp:IAO_EXT_0000112 . # CCP:protein_ontology_identifier
                  ?pr_id obo:IAO_0000219 ?pr_class .
                  ?pr_class oboInOwl:hasDbXref ?xref .
                  filter regex(?xref, '^UniProtKB:') .
                  bind (strafter(?xref, 'UniProtKB:') as ?uniprot_identifier)
                  }"
  }