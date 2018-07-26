;; --------------------------------------------------------
;; --------- Orphan bioentity typing == protein -----------
;; --------------------------------------------------------
`{:name "step-ha_orphan-bioentity-typing-protein"
  :description "for any bioentity denoted by a protein identifier, this rule adds a subclass relation to CHEBI:protein if that bioentity does not already have a parent concept"
  :head ((?/bioentity rdfs/subClassOf ?/protein_bioentity))
  :body "PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  select ?bioentity ?protein_bioentity {
    ?id rdfs:subClassOf* ccp:IAO_EXT_0000188 . # CCP:protein identifier
    ?id obo:IAO_0000219 ?bioentity . # OBO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
    filter (contains (str(?bioentity), 'http://ccp.ucdenver.edu/kabob/bio/'))
    # if it already has a subClassOf relation, then it's already part of a hierarchy so we exclude it
    minus {?bioentity rdfs:subClassOf ?x}
    {
      # get the kabob bioentity that corresponds to CHEBI:protein
      select ?protein_bioentity {
        kice:CHEBI_36080 obo:IAO_0000219 ?protein_bioentity . # OBO:denotes
        filter (?protein_bioentity != obo:CHEBI_36080)  # OBO:protein
      }
    }
  }"
  }
