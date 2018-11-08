`{:description "This rule finds any catalysis record described in Reactome, as well as its GO MF field.",
 :name "add_reactome_catalyses_to_ice",
 :reify ([?/catal_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome catalysis record" ?/catal), :prefix "R_"}]
         ),
 :head ((?/record_set obo/BFO_0000051 ?/catal_record)
        (?/catal_record rdf/type ccp/IAO_EXT_0001574) ;; catalysis record
        (?/catal_record obo/BFO_0000051 ?/xref_record)
        (?/xref_id_field rdf/type ?/go_mf_ice)
        (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
        (?/catal ccp/ekws_temp_biopax_connector_relation ?/catal_record)
        ),
 :body "#add_reactome_catalyses_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?record_set ?catal ?xref_record ?xref_id_field ?go_mf_ice WHERE {
 ?record_set rdfs:label \"Reactome BioPAX record set, Homo sapiens\"@en .
 ?record_set rdf:type ccp:IAO_EXT_0000012 .
 ?record_set obo:IAO_0000142 ?download .
 ?download rdfs:label \"http://www.reactome.org/pages/download-data/biopax/Homo_sapiens.owl\"@en .
 ?catal rdf:type bp:Catalysis .
 ?catal bp:xref ?xref .
 ?xref ccp:ekws_temp_biopax_connector_relation ?xref_record .
 ?xref_record obo:BFO_0000051 ?xref_id_field .
 ?xref_id_field rdf:type ccp:IAO_EXT_0001520 .
 ?xref_id_field rdfs:label ?go_id .
 bind (uri (concat (str (\"http://ccp.ucdenver.edu/kabob/ice/GO_\"), strafter (str(?go_id), \":\"))) as ?go_mf_ice) . 
 ?xref_record obo:BFO_0000051 ?xref_db_field .
 ?xref_db_field rdf:type ccp:IAO_EXT_0001519 .
 ?xref_db_field rdfs:label \"GENE ONTOLOGY\"@en .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}




         

         
