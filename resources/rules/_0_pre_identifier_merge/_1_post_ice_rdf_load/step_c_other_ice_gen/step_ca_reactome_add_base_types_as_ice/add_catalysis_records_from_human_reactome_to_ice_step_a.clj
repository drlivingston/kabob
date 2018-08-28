{:description "This rule finds any catalysis record described in Reactome; no Reactome IDs are available.",
 :name "add_catalysis_records_from_human_reactome_to_ice_step_a",
 :reify ([?/record_set {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome v60 record set"), :prefix "R_"}]
         [?/download {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome v60 download"), :prefix "D_"}]
         [?/catal_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome catalysis record" ?/catal), :prefix "R_"}] ),
 :head ((?/record_set rdf/type ccp/IAO_EXT_00000012)
        (?/record_set obo/IAO_0000142 ?/download)
        (?/download rdfs/label "http://www.reactome.org/pages/download-data/biopax/Homo_sapiens.owl")
        (?/record_set obo/BFO_0000051 ?/catal_record)
        (?/catal obo/IAO_0000142 ?/catal_record)
        (?/catal_record rdf/type ccp/IAO_EXT_0001574)),
 :sparql-string "#ekw
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?catal WHERE {
?catal rdf:type bp:Catalysis . 
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
