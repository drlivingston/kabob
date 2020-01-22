`{:description "This rule finds any relationship xref record described in Reactome.",
 :name "add_reactome_relationship_xrefs_to_ice",
  :reify ([?/xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome relationship type xref" ?/xref), :prefix "R_"}]
          [?/xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome id field" ?/react_id), :prefix "F_"}]
          [?/xref_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome db field" ?/react_db), :prefix "F_"}]),
  :head ((?/xref_record rdf/type ccp/IAO_EXT_0001570) ;; relationship xref
         (?/xref_record obo/BFO_0000051 ?/xref_db_field)
         (?/xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; database field
         (?/xref_db_field rdfs/label ?/react_db)
         (?/xref_record obo/BFO_0000051 ?/xref_id_field)
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001520) ;; database id field
         (?/xref_id_field rdfs/label ?/react_id)
         (?/xref_record obo/BFO_0000051 ?/relationship_type_xref_record)
         (?/relationship_type_xref_record rdf/type ccp/IAO_EXT_0001947) ;; relationship type field
         (?/xref ccp/ekws_temp_biopax_connector_relation ?/xref_record)),
  :body "#add_reactome_relationship_xrefs_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?xref ?react_db ?react_id ?react_relationship_type ?relationship_type_xref_record WHERE {
?xref rdf:type bp:RelationshipXref .
?xref bp:db ?react_db .
?xref bp:id ?react_id .
?xref bp:relationshipType ?react_relationship_type .
?react_relationship_type ccp:ekws_temp_biopax_connector_relation ?relationship_type_xref_record .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

