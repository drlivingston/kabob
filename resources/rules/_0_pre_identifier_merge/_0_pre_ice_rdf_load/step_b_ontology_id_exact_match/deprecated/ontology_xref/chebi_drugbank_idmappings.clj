;; ----------------------------------------------
;; --------- CHEBI DrugBank id mappings ---------
;; ----------------------------------------------
;; create chemical id to drugbank id to mappings
`{:name "step-b_chebi-drugbank-idmappings"
  :description "This rule creates chemical id to drugbank id to mappings"
  :head ((?/chebi_id skos/exactMatch ?/db_id))
  :reify ([?/db_id {:ln (:localname ?/drug_bank_id)
                  :ns "kice" :prefix "DRUGBANK_" :suffix ""}])
  :body "prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?chebi_id ?xref ?drug_bank_id {
                  ?chebi_id rdfs:subClassOf ccp:IAO_EXT_0000198 . # CCP:chebi_ontology_identifier
                  ?chebi_id obo:IAO_0000219 ?chebi_class .
                  ?chebi_class oboInOwl:hasDbXref ?xref .
                  filter regex(?xref, '^DrugBank:') .
                  bind (strafter(?xref, 'DrugBank:') as ?drug_bank_id)
                  }"
  }