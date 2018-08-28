{:description "This rule finds any physical entity record described in Reactome and traces out its link to a unification xref to extract the physical entity's Reactome ID.",
 :name "add_physical_entity_records_and_reactome_ids_from_human_reactome_to_ice_step_a",
 :reify ([?/record_set {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome v60 record set"), :prefix "R_"}]
         [?/download {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome v60 download"), :prefix "D_"}]
         [?/phent_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome physical entity record" ?/phent), :prefix "R_"}]
         [?/xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record" ?/xref), :prefix "R_"}]
         [?/xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record id field" ?/react_id), :prefix "F_"}]
         [?/xref_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome unification xref record db field" ?/react_db), :prefix "F_"}]),
 :head ((?/record_set rdf/type ccp/IAO_EXT_00000012)
        (?/record_set obo/IAO_0000142 ?/download)
        (?/download rdfs/label "http://www.reactome.org/pages/download-data/biopax/Homo_sapiens.owl")
        (?/record_set obo/BFO_0000051 ?/phent_record)
        (?/phent obo/IAO_0000142 ?/phent_record)
        (?/phent_record rdf/type ccp/IAO_EXT_0001515)
        (?/phent_record obo/BFO_0000051 ?/xref_record)
        (?/xref_record rdf/type ccp/IAO_EXT_0001588)
        (?/xref_record rdf/type ccp/IAO_EXT_0001572)
        (?/xref_record obo/BFO_0000051 ?/xref_db_field)
        (?/xref_record obo/BFO_0000051 ?/xref_id_field)
        (?/xref_db_field rdf/type ccp/IAO_EXT_0001519)
        (?/xref_db_field rdfs/label "Reactome")
        (?/xref_id_field rdf/type ccp/IAO_EXT_0001520)
        (?/xref_id_field rdf/type ccp/IAO_EXT_0001517)
        (?/xref_id_field rdfs/label ?/clean_react_id)),
 :sparql-string "#ekw
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/64/48887#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?phent ?xref ?react_id ?react_db ?clean_react_id
WHERE {
?phent rdf:type bp:PhysicalEntity .
?phent bp:xref ?xref .
?xref rdf:type bp:UnificationXref .
?xref bp:db ?react_db .
filter (regex (str (?react_db), \"^Reactome$\")) .
?xref bp:id ?react_id .
bind (str (?react_id) as ?clean_react_id) .
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
