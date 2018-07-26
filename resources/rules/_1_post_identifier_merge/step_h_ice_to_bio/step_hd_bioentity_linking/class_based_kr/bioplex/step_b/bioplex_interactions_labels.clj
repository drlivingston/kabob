;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "step-hd_bioplex-interactions"
  :description   "This rule generates bio-representations the (biophysical) interactions cataloged by BioPlex"
  :head          ((?/interaction rdfs/label ?/interaction_label)
                   (?/b1_sc rdfs/label ?/b1_label)
                   (?/b2_sc rdfs/label ?/b2_label))

  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    select  ?interaction ?b1_sc ?b2_sc ?b1_label ?b2_label ?interaction_label {
    ?interaction rdfs:subClassOf ccp:temp_bioplex_interaction .
    ?interaction rdfs:subClassOf ?r1 .
    ?r1 owl:someValuesFrom ?b1_sc .
    ?b1_sc rdfs:subClassOf ?b1 .
    ?interaction rdfs:subClassOf ?r2 .
    filter (?r1 != ?r2)
    ?r2 owl:someValuesFrom ?b2_sc .
    ?b2_sc rdfs:subClassOf ?b2 .

    optional {?b1 rdfs:label ?label1}
    bind(coalesce(?label1, \"Unnamed interacting protein\") as ?b1_name)
    bind(concat(str(?b1_name), \"; interaction participant\") as ?b1_label)
    optional {?b2 rdfs:label ?label2}
    bind(coalesce(?label2, \"Unnamed interacting protein\") as ?b2_name)
    bind(concat(str(?b2_name), \"; interaction participant\") as ?b2_label)
    bind(concat(\"Protein-protein interaction between \", str(?b1_name), \" and \", str(?b2_name)) as ?interaction_label)
    }"
  }