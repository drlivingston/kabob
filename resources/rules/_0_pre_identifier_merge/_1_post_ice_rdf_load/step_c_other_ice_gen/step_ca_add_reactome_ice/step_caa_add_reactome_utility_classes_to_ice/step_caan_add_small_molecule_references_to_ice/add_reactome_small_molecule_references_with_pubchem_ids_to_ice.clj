`{:description "This rule finds any small molecule reference record described in Reactome with a PubChem ID.",
 :name "add_reactome_small_molecule_references_with_pubchem_ids_to_ice",
 :reify ([?/smmol_ref_record {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome small molecule reference record" ?/smmol_ref), :prefix "R_"}]
         ),
  :head ((?/smmol_ref_record rdf/type ccp/IAO_EXT_0001552) ;; small molecule reference
         (?/smmol_ref_record obo/BFO_0000051 ?/xref_record)
         (?/xref_record rdf/type ccp/IAO_EXT_0001588) ;; xref field
         (?/xref_id_field rdf/type ccp/IAO_EXT_0001942) ;; reactome pubchem compound id field
         (?/xref_id_field rdf/type ?/pubchem_ice)
         (?/smmol_ref ccp/ekws_temp_biopax_connector_relation ?/smmol_ref_record)),
  :body "#add_reactome_protein_references_with_pubchem_ids_to_ice.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?smmol_ref ?xref_record ?xref_id_field ?pubchem_ice WHERE {
?smmol_ref rdf:type bp:SmallMoleculeReference .
?smmol_ref bp:xref ?xref .
?xref ccp:ekws_temp_biopax_connector_relation ?xref_record .
?xref_record obo:BFO_0000051 ?xref_id_field .
?xref_id_field rdf:type ccp:IAO_EXT_0001520 .
?xref_id_field rdfs:label ?pubchem_id .
?xref_record obo:BFO_0000051 ?xref_db_field .
?xref_db_field rdf:type ccp:IAO_EXT_0001519 .
?xref_db_field rdfs:label \"PubChem Compound\"@en .
bind (uri (concat (str (\"http://ccp.ucdenver.edu/kabob/ice/PUBCHEMCOMPOUND_\"), str (?pubchem_id))) as ?pubchem_ice) . 
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}

