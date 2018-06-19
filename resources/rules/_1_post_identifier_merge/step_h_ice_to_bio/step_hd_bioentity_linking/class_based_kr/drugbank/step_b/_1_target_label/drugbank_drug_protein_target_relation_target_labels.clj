;; ---------------------------------------------------------
;; --------- DrugBank Drug Protein Target Relation ---------
;; ---------------------------------------------------------
`{:name        "step-hdb_drugbank-drug-to-protein-target-relation-target-labels"
  :description "This rule generates bio-representations for the drug-target relationships cataloged by DrugBank where the target is a protein"
  :head        ((?/interaction rdfs/label ?/interaction_label)
                 (?/target_sc rdfs/label ?/target_label))
  :body        "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX obo: <http://purl.obolibrary.org/obo/>
  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
  select ?interaction ?target_sc ?target_label ?interaction_label {

       {
        select ?has_participant {
                                 kice:RO_0000057 obo:IAO_0000219 ?has_participant .
                                 filter (?has_participant != obo:RO_0000057) .
                                 }
        }

                    {
                     select ?inheres_in {
                                         kice:RO_0000052 obo:IAO_0000219 ?inheres_in .
                                         filter (?inheres_in != obo:RO_0000052) .
                                         }
                     }

       ?interaction rdfs:subClassOf ccp:temp_drugbank_interaction .
       ?interaction rdfs:subClassOf ?r1 .
       ?r1 owl:someValuesFrom ?drug_sc .
       ?r1 owl:onProperty ?has_participant .
       ?inheres_r owl:someValuesFrom ?drug_sc .
       ?drug_sc rdfs:label ?drug_label .
       ?inheres_r owl:onProperty ?inheres_in .
       ?interaction rdfs:subClassOf ?r2 .
       filter (?r1 != ?r2)
       ?r2 owl:someValuesFrom ?target_sc .
       ?r2 owl:onProperty ?has_participant .
       ?target_sc rdfs:subClassOf ?target .

       optional {?target rdfs:label ?pl}
       bind(coalesce(?pl, \"Unnamed interacting drug target\") as ?protein_name)
       bind(concat(str(?protein_name), \"; drug-protein target interaction participant\") as ?target_label)
       bind(concat(\"Drug-protein interaction between \", strbefore(?drug_label,\";\"), \" and \", str(?protein_name)) as ?interaction_label)

       }"
  }