`{:description "This rule finds any pathway step record described in Reactome.",
 :name "add_reactome_pathway_steps_to_ice",
 :reify ([?/pws_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome pathway step record" ?/pws), :prefix "R_"}]
         ),
 :head ((?/pws_record rdf/type ccp/IAO_EXT_0001573) ;; pathway step record
        (?/pws ccp/ekws_temp_biopax_connector_relation ?/pws_record)
        ),
 :body"#add_reactome_pathway_steps_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?pws WHERE {
 ?pws rdf:type bp:PathwayStep .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
