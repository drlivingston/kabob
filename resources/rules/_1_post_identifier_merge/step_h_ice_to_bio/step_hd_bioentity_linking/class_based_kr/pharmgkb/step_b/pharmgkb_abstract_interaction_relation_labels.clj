;; --------------------------------------------------------------------
;; --------- PharmGKB abstract bioentity interaction Relation ---------
;; --------------------------------------------------------------------
`{:name "step-hdb_pharmgkb-abstract-interaction-relation-labels"
  :description "This rule assigns an abstract interaction relation between pharmgkb entities"
  :head ((?/interaction rdfs/label ?/interaction_label) ; transfer label to the subclass
          (?/b1_sc rdfs/label ?/bioentity1_label) ; transfer label to the subclass
          (?/b2_sc rdfs/label ?/bioentity2_label)) ; transfer label to the subclass

  :body
     "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
     prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
      PREFIX obo: <http://purl.obolibrary.org/obo/>
      PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
      PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      select ?interaction ?b1_sc ?b2_sc ?bioentity1_label ?bioentity2_label ?interaction_label {

          ?interaction rdfs:subClassOf ccp:temp_pharmgkb_interaction .
          ?interaction rdfs:subClassOf ?r1 .
          ?r1 owl:someValuesFrom ?b1_sc .
          ?b1_sc rdfs:subClassOf ?b1 .
          ?interaction rdfs:subClassOf ?r2 .
          filter (?r1 != ?r2)
          ?r2 owl:someValuesFrom ?b2_sc .
          ?b2_sc rdfs:subClassOf ?b2 .

          optional {?b1 rdfs:label ?label1}
          bind(coalesce(?label1, \"Unnamed interacting bioentity\") as ?bioentity1_name)
          bind(concat(str(?bioentity1_name), \"; interaction participant\") as ?bioentity1_label)


          optional {?b2 rdfs:label ?label2}
          bind(coalesce(?label2, \"Unnamed interacting bioentity\") as ?bioentity2_name)
          bind(concat(str(?bioentity2_name), \"; interaction participant\") as ?bioentity2_label)
          bind(concat(\"Interaction between \", str(?bioentity1_name), \" and \", str(?bioentity2_name)) as ?interaction_label)

          }"


  }