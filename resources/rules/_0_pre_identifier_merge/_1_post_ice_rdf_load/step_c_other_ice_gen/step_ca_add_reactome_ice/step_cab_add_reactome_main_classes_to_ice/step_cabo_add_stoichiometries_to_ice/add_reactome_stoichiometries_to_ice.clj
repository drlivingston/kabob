`{:description "This rule finds any stoichiometry record described in Reactome.",
 :name "add_reactome_stoichiometries_to_ice",
 :reify ([?/stoi_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome stoichiometry record" ?/stoi), :prefix "R_"}]
         ),
 :head ((?/stoi_record rdf/type ccp/IAO_EXT_0001565) ;; stoichiometry record
        (?/stoi ccp/ekws_temp_biopax_connector_relation ?/stoi_record)
        ),
 :body "#add_reactome_stoichiometries_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?stoi WHERE {
 ?stoi rdf:type bp:Stoichiometry .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
