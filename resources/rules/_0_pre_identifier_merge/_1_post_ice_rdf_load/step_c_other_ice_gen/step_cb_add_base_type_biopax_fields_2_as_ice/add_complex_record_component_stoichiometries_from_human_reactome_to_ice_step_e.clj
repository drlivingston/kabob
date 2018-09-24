`{:description "This rule finds any previously reified complex record from Reactome and sets its component stoichiometry fields.",
 :name "add_complex_record_component_stoichiometries_from_human_reactome_to_ice_step_e",
 :reify ([?/stoichiometry_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometry record" ?/stoi), :prefix "R_"}]
         [?/coefficient_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometric coefficient field" ?/stoi_coeff), :prefix "F_"}])
  :head ((?/compl_record obo/BFO_0000051 ?/stoichiometry_record)
        (?/stoichiometry_record rdf/type ccp/IAO_EXT_0001565) ;; Reactome component stoichiometry field type
        (?/stoichiometry_record rdf/type ccp/IAO_EXT_0001545) ;; Reactome stoichiometry record
         (?/stoichiometry_record obo/BFO_0000051 ?/component_record)
         (?/component_record rdf/type ccp/IAO_EXT_0001539) ;; physical entity field value
         (?/stoichiometry_record obo/BFO_0000051 ?/coefficient_field)
         (?/coefficient_field rdfs/label ?/stoi_coeff)
         (?/coefficient_field rdf/type ccp/IAO_EXT_0001540) ;; stoichimetric coefficient field type
        
        ),
 :body "#add_complex_record_component_stoichiometries_from_human_reactome_to_ice_step_e.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#> 
SELECT ?compl_record ?stoi_coeff ?component_record ?component ?stoi 
WHERE {
?compl rdf:type bp:Complex .
?compl bp:componentStoichiometry ?stoi .
?stoi bp:physicalEntity ?component .
?compl bp:component ?component .
?stoi bp:stoichiometricCoefficient ?stoi_coeff .
GRAPH <file://add_complex_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> {
?compl obo:IAO_0000142 ?compl_record .
}
?component obo:IAO_0000142 ?component_record .
}"
}
