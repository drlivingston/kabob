`{:description "This rule finds any fragment feature record described in Reactome.",
 :name "add_reactome_fragment_features_to_ice",
 :reify ([?/feature_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome fragment feature record" ?/feature), :prefix "R_"}]),
 :head ((?/feature_record rdf/type ccp/IAO_EXT_0001587) ;; fragment feature
        (?/feature_record obo/BFO_0000051 ?/interval_record)
        (?/interval_record rdf/type ccp/IAO_EXT_0001532) ;; feature location field
        (?/feature ccp/ekws_temp_biopax_connector_relation ?/feature_record)),
 :body "#add_reactome_sequence_intervals_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?feature ?interval_record WHERE {
?feature rdf:type bp:FragmentFeature .
?feature bp:featureLocation ?interval .
?interval ccp:ekws_temp_biopax_connector_relation ?interval_record .
}",
   :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}


