;; --------------------------------------------------------
;; --------- Orphan bioentity typing == drugbank -----------
;; --------------------------------------------------------
`{:name "orphan-bioentity-typing-drugbank"
  :description "for any bioentity denoted by a DrugBank identifier, this rule adds a subclass relation to CHEBI:drug if that bioentity does not already have a parent concept"
  :head ((?/bioentity rdfs/subClassOf ?/drug_bioentity))
  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  select ?bioentity ?drug_bioentity {
    ?id rdfs:subClassOf ccp:IAO_EXT_0001309 . # CCP:drugbank_identifier
    ?id obo:IAO_0000219 ?bioentity . # OBO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
    filter (contains (str(?bioentity), 'http://ccp.ucdenver.edu/obo/ext/'))
    # if it already has a subClassOf relation, then it's already part of a hierarchy so we exclude it
    minus {?bioentity rdfs:subClassOf ?x}
    {
      # get the kabob bioentity that corresponds to CHEBI:drug
      #####
      #TODO: should this really realize the drug role and not be a subclass of it?
      #####
      select ?drug_bioentity {
        ccp:CHEBI_23888 obo:IAO_0000219 ?drug_bioentity . # OBO:denotes
        filter (?drug_bioentity != obo:CHEBI_23888)  # OBO:drug
      }
    }
  }"
  }
