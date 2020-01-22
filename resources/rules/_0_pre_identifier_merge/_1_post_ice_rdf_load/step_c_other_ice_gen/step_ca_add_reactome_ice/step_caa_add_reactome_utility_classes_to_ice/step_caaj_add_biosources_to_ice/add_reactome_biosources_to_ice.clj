`{:description "This rule finds any biosource record described in Reactome.",
 :name "add_reactome_biosources_to_ice",
  :reify ([?/bio_source_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome biosource record" ?/bio_source), :prefix "R_"}]
         ),
  :head ((?/bio_source_record rdf/type ccp/IAO_EXT_0001578) ;; biosource record
         (?/bio_source_record obo/BFO_0000051 ?/bio_source_xref_record)
         (?/bio_source_xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/bio_source ccp/ekws_temp_biopax_connector_relation ?/bio_source_record)),
  :body "#add_reactome_biosources_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?bio_source ?bio_source_xref_record WHERE {
?bio_source rdf:type bp:BioSource .
?bio_source bp:xref ?react_xref .
?react_xref ccp:ekws_temp_biopax_connector_relation ?bio_source_xref_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

