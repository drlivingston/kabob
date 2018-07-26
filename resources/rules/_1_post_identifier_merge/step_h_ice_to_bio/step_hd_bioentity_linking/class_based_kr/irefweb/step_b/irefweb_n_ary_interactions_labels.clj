;; ----------------------------------------------
;; --------- IRefWeb N-Ary Interactions ---------
;; ----------------------------------------------
`{:name "step-hdb_irefweb-n-ary-interactions-labels"
  :description "This rule generates bio-representations for all n-ary protein interactions cataloged by IRefWeb, where n>2"
  :head ((?/bioentity_sc rdfs/label ?/bioentity_label))

  :body
  "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
  select ?bioentity_sc ?bioentity_label {

       ?interaction rdfs:subClassOf ccp:temp_irefweb_nary_interaction .
       ?interaction rdfs:subClassOf ?r1 .
       ?r1 owl:someValuesFrom ?bioentity_sc .
       ?bioentity_sc rdfs:subClassOf ?b1 .

       optional {?b1 rdfs:label ?label}
       bind(coalesce(?label, \"Unnamed interacting protein\") as ?bioentity_name)
       bind(concat(str(?bioentity_name), \"; interaction participant\") as ?bioentity_label)

       }"

  :options {:magic-prefixes [["franzOption_clauseReorderer" "franz:identity"]]}
  }