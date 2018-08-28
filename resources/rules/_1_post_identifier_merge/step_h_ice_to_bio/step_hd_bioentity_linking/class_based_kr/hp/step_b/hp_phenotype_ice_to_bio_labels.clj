;; -------------------------------------------------------------------------
;; --------- Human Phenotype Ontology Phenotype Concept Assignment ---------
;; -------------------------------------------------------------------------
`{:name          "step-hdb_hp-phenotype-ice-to-bio-labels"
  :description   "This rule creates a subclass of every human phenotype and types it as a human phenotype concept identifier (IAO_EXT_0000208)"
  :head          ((?/phenotype_sc rdfs/label ?/human_phenotype_label)
                   (?/bioentity_sc rdfs/label ?/causal_bioentity_label))
  :body "PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix kice: <http://ccp.ucdenver.edu/kabob/ice/>
                  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  select  ?phenotype_sc ?bioentity_sc ?causal_bioentity_label ?human_phenotype_label {

                     {
                      select ?cause_or_contributes_to_condition {
                                                                 kice:RO_0003302 obo:IAO_0000219 ?cause_or_contributes_to_condition .
                                                                 filter (?cause_or_contributes_to_condition != obo:RO_0003302) .
                                                                 }
                      }
                     ?phenotype_sc rdfs:subClassOf ccp:temp_human_phenotype .
                     ?phenotype_sc rdfs:subClassOf ?hp .
                     filter (?hp != ccp:temp_human_phenotype)
                     ?r owl:someValuesFrom ?phenotype_sc .
                     ?r owl:onProperty ?cause_or_contributes_to_condition .
                     ?bioentity_sc rdfs:subClassOf ?r .
                     ?bioentity_sc rdfs:subClassOf ?causal_bioentity .
                     filter (?r != ?causal_bioentity)

                     optional {?hp rdfs:label ?hpl}
                     bind(coalesce(?hpl, \"Unnamed human phenotype\") as ?hp_name)

                     optional {?causal_bioentity rdfs:label ?bl}
                     bind(coalesce(?bl, \"Unnamed causal bioentity\") as ?bioentity_name)

                     bind(concat(str(?bioentity_name), \"; causes_or_contributes_to: \", str(?hp_name)) as ?causal_bioentity_label)
                     bind(concat(str(?hp_name), \"; caused_or_contributed_from: \", str(?bioentity_name)) as ?human_phenotype_label)
                     }"

  }