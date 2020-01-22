`{:description "This rule finds any publication xref record described in Reactome that uses a url.",
 :name "add_reactome_publication_xrefs_to_ice_2",
  :reify ([?/xref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome publication xref record" ?/xref), :prefix "R_"}]
          [?/xref_title_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome title field" ?/react_title), :prefix "F_"}]
          [?/xref_author_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome author field" ?/react_author), :prefix "F_"}]
          [?/xref_url_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome url field" ?/react_url), :prefix "F_"}]
          ),
  :head ((?/xref_record rdf/type ccp/IAO_EXT_0001571) ;; publication xref
         (?/xref_record obo/BFO_0000051 ?/xref_author_field)
         (?/xref_author_field rdf/type ccp/IAO_EXT_0001529) ;; author field
         (?/xref_author_field rdfs/label ?/react_author),
         (?/xref_record obo/BFO_0000051 ?/xref_title_field)
         (?/xref_title_field rdf/type ccp/IAO_EXT_0001536) ;; title field
         (?/xref_title_field rdfs/label ?/react_title),
         (?/xref_record obo/BFO_0000051 ?/xref_url_field)
         (?/xref_url_field rdf/type ccp/IAO_EXT_0001950) ;; url field
         (?/xref_url_field rdfs/label ?/react_url)
         (?/xref ccp/ekws_temp_biopax_connector_relation ?/xref_record)),
  :body "#add_reactome_publication_xrefs_to_ice_2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?xref ?react_author ?react_title ?react_url WHERE {
?xref rdf:type bp:PublicationXref .
?xref bp:author ?react_author .
?xref bp:title ?react_title .
?xref bp:url ?react_url .
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

