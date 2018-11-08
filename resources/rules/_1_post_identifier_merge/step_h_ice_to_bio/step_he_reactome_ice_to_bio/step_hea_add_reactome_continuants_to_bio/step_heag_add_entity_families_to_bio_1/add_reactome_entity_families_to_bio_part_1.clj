`{:description "This rule finds any functional family described in Reactome, generates its ICE identifier, and places the top-level entity list on the BIO side.  It lays the groundwork for building these into RDF lists, but they are connected programmatically in part 2.",
 :name "add_reactome_entity_families_to_bio_part_1",
 :reify ([?/union_class {:ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_", :ln (:sha-1 "Reactome union set" ?/family_record)}]
         [?/extra_node {:ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_", :ln (:sha-1 "Reactome set extra node" ?/family_record)}],
         [?/member_list {:ln (:sha-1 "rdf list" ?/family_record ?/member_bio), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "L_"}]),
 :head ((?/family_reactome_ice obo/IAO_0000219 ?/union_class)
        ;; each reactome ID connects to a set of list nodes
        (?/family_reactome_ice ccp/ekws_temp_list_connector_relation ?/member_list)
        (?/union_class owl/equivalentClass ?/extra_node)
        (?/member_list rdf/first ?/member_bio)
        (?/member_list rdf/type rdf/List) 
        (?/family_reactome_ice rdfs/subClassOf ccp/IAO_EXT_0001643)
        (?/family_reactome_ice rdfs/subClassOf ccp/IAO_EXT_0000088)
        ),
 :body "#add_reactome_entity_families_to_bio_part_1.clj
PREFIX franzOption_memoryLimit: <franz:85g>
PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95>
PREFIX franzOption_logQuery: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT DISTINCT ?family_reactome_ice ?member_bio ?family_record ?member_record ?name
WHERE {
?family_record obo:BFO_0000051 ?member_record .
?member_record rdf:type ccp:IAO_EXT_0001528 . # member physical entity field
?family_record obo:BFO_0000051 ?family_xref_record .
?family_xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?family_xref_record obo:BFO_0000051 ?family_xref_id_field .
?family_xref_id_field rdf:type ?family_reactome_ice .
?family_reactome_ice rdfs:subClassOf ccp:IAO_EXT_0001643 . # Reactome identifier
?member_record obo:BFO_0000051 ?member_xref_record .
?member_xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?member_xref_record obo:BFO_0000051 ?member_xref_id_field .
?member_xref_id_field rdf:type ?member_reactome_ice .
?member_reactome_ice rdfs:subClassOf ccp:IAO_EXT_0001643 . # Reactome identifier
?member_reactome_ice obo:IAO_0000219 ?member_bio .      
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

        
