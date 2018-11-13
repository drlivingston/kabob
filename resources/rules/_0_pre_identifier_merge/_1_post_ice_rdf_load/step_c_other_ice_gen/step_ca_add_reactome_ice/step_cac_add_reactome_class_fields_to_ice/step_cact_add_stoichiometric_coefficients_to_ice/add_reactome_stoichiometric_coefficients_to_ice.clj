`{:description "This rule finds any stoichiometric coefficient field described in Reactome.",
 :name "add_reactome_stoichiometric_coefficients_to_ice",
 :reify ([?/stoichiometric_coefficient_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometric coefficient field" ?/stoichiometric_coefficient), :prefix "F_"}])
 :head ((?/reactome_thing_record obo/BFO_0000051 ?/stoichiometric_coefficient_field)
        (?/stoichiometric_coefficient_field rdf/type ccp/IAO_EXT_0001540) ;; stoichiometric coefficient field
        (?/stoichiometric_coefficient_field rdfs/label ?/stoichiometric_coefficient)
        ),
 :body "#add_reactome_stoichiometric_coefficients_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?reactome_thing_record ?stoichiometric_coefficient WHERE {
 ?reactome_thing bp:stoichiometricCoefficient ?stoichiometric_coefficient .
 ?reactome_thing ccp:ekws_temp_biopax_connector_relation ?reactome_thing_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
