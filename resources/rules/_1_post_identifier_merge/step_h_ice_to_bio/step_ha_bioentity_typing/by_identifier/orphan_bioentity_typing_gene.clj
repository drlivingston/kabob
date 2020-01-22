;; --------------------------------------------------------
;; --------- Orphan bioentity typing == gene --------------
;; --------------------------------------------------------
`{:name "step-ha_orphan-bioentity-typing-gene"
  :description "for any bioentity denoted by a DNA identifier, this rule adds a subclass relation to SO:gene if that bioentity does not already have a parent concept"
  :head ((?/bioentity rdfs/subClassOf ?/gene_bioentity))
  :body "PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  select ?bioentity ?gene_bioentity {
    ?id rdfs:subClassOf* ccp:IAO_EXT_0000182 . # CCP:genomic identifier
    ?id obo:IAO_0000219 ?bioentity . # OBO:denotes
    # ensure it's a kabob bioentity (not an obo bioentity)
    filter (contains (str(?bioentity), 'http://ccp.ucdenver.edu/kabob/bio/'))
    # if it already has a subClassOf relation, then it's already part of a hierarchy so we exclude it
    minus {?bioentity rdfs:subClassOf ?x}
    {
      # get the kabob bioentity that corresponds to SO:gene
      select ?gene_bioentity {
        kice:SO_0000704 obo:IAO_0000219 ?gene_bioentity . # OBO:denotes
        filter (?gene_bioentity != obo:SO_0000704) # OBO:gene
      }
    }
  }"
  }
