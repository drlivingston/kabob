;; --------------------------------------------------------
;; --------- Orphan bioentity typing == mrna -----------
;; --------------------------------------------------------
`{:name "orphan-bioentity-typing-mrna"
  :description "for any bioentity denoted by a RNA identifier, this rule adds a subclass relation to SO:RNA if that bioentity does not already have a parent concept"
  :head ((?/bioentity rdfs/subClassOf ?/rna_bioentity))
  :sparql-string "PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  select ?bioentity ?rna_bioentity {
    ?id rdfs:subClassOf* ccp:IAO_EXT_0000187 . # CCP:rna_identifier
    ?id obo:IAO_0000219 ?bioentity . # OBO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
    filter (contains (str(?bioentity), 'http://ccp.ucdenver.edu/obo/ext/'))
    # if it already has a subClassOf relation, then it's already part of a hierarchy so we exclude it
    minus {?bioentity rdfs:subClassOf ?x}
    {
      # get the kabob bioentity that corresponds to SO:rna
      select ?rna_bioentity {
        ccp:SO_0000356 obo:IAO_0000219 ?rna_bioentity . # OBO:denotes
        filter (?rna_bioentity != obo:SO_0000356)  # OBO:rna
      }
    }
  }"
  }
