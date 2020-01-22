`{:description "This rule finds any sequence site record described in Reactome.",
 :name "add_reactome_sequence_sites_to_ice",
  :reify ([?/site_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence site record" ?/site), :prefix "R_"}]
          [?/site_position_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome sequence position field" ?/sequence_position), :prefix "F_"}]
          [?/site_position_status_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome position status field" ?/position_status), :prefix "F_"}]),
  :head ((?/site_record rdf/type ccp/IAO_EXT_0001575) ;; sequence site
         (?/site_record obo/BFO_0000051 ?/site_position_field)
         (?/site_position_field rdf/type ccp/IAO_EXT_0001538) ;; sequence position field
         (?/site_position_field rdfs/label ?/sequence_position)
         (?/site_record obo/BFO_0000051 ?/site_position_status_field)
         (?/site_position_status_field rdf/type ccp/IAO_EXT_0001537) ;; position status field
         (?/site_position_status_field rdfs/label ?/position_status)
         (?/site ccp/ekws_temp_biopax_connector_relation ?/site_record)),
  :body "#add_reactome_sequence_sites_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?site ?sequence_position ?position_status WHERE {
?site rdf:type bp:SequenceSite .
?site bp:sequencePosition ?sequence_position .
?site bp:positionStatus ?position_status .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

