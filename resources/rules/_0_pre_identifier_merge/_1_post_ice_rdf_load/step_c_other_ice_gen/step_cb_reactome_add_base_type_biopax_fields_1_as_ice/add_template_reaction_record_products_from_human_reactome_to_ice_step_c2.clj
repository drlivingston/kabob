{:description "This rule finds any previously reified template reaction record from Reactome and sets its product fields.",
 :name "add_template_reaction_record_products_from_human_reactome_to_ice_step_c2",
 :head ((?/trxn_record obo/BFO_0000051 ?/product_participant_record)
        (?/product_participant_record rdf/type ccp/IAO_EXT_0001550) ;; Reactome template reaction right field type
        ),
 :sparql-string "#add_template_reaction_record_products_from_human_reactome_to_ice_step_c2.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?trxn_record ?product_participant ?product_participant_record 
WHERE {
?trxn rdf:type bp:TemplateReaction .    # a template reaction
?trxn bp:product ?product_participant .       # with a product participant 
GRAPH <file://add_template_reaction_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt> { 
?trxn obo:IAO_0000142 ?trxn_record .
}
?product_participant obo:IAO_0000142 ?product_participant_record .
}"
}
