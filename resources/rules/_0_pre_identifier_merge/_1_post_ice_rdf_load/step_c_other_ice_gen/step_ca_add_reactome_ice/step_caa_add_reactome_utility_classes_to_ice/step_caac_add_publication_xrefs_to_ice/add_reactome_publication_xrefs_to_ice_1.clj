`{:description "This rule finds any publication xref record described in Reactome with a pubmed id.",
 :name "add_reactome_publication_xrefs_to_ice_1",
  :reify ([?/xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome publication xref record" ?/xref), :prefix "R_"}]
          [?/xref_title_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome title field" ?/react_title), :prefix "F_"}]
          [?/xref_author_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome author field" ?/react_author), :prefix "F_"}]
          [?/xref_source_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome source field" ?/react_source), :prefix "F_"}]
          [?/xref_year_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome year field" ?/react_year), :prefix "F_"}]
          [?/xref_id_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome id field" ?/react_id), :prefix "F_"}]
          [?/xref_db_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome db field" ?/react_db), :prefix "F_"}]),
 :head ((?/xref_record rdf/type ccp/IAO_EXT_0001571) ;; publication xref
        (?/xref_record obo/BFO_0000051 ?/xref_db_field)
        (?/xref_db_field rdf/type ccp/IAO_EXT_0001519) ;; database field
        (?/xref_db_field rdfs/label ?/react_db)
        (?/xref_record obo/BFO_0000051 ?/xref_id_field)
        (?/xref_id_field rdf/type ccp/IAO_EXT_0001520) ;; database id field
        (?/xref_id_field rdfs/label ?/react_id),
        (?/xref_record obo/BFO_0000051 ?/xref_author_field)
        (?/xref_author_field rdf/type ccp/IAO_EXT_0001529) ;; author field
        (?/xref_author_field rdfs/label ?/react_author),
        (?/xref_record obo/BFO_0000051 ?/xref_title_field)
        (?/xref_title_field rdf/type ccp/IAO_EXT_0001536) ;; title field
        (?/xref_title_field rdfs/label ?/react_title),
        (?/xref_record obo/BFO_0000051 ?/xref_year_field)
        (?/xref_year_field rdf/type ccp/IAO_EXT_0001530) ;; year field
        (?/xref_year_field rdfs/label ?/react_year),
        (?/xref_record obo/BFO_0000051 ?/xref_source_field)
        (?/xref_source_field rdf/type ccp/IAO_EXT_0001531) ;; source field
        (?/xref_source_field rdfs/label ?/react_source)
        (?/xref ccp/ekws_temp_biopax_connector_relation ?/xref_record)),
  :body "#add_reactome_publication_xrefs_to_ice_1.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?xref ?react_db ?react_id ?react_source ?react_author ?react_title ?react_year WHERE {
?xref rdf:type bp:PublicationXref .
?xref bp:author ?react_author .
?xref bp:title ?react_title .
?xref bp:source ?react_source .
?xref bp:year ?react_year .
?xref bp:db ?react_db .
?xref bp:id ?react_id .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

