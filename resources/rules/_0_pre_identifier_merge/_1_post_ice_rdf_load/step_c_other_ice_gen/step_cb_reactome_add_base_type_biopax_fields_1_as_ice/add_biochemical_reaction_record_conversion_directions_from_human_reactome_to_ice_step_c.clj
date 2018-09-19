{:description "This rule finds any previously reified biochemical reaction record from Reactome and records its conversion direction field(s).",
 :name "add_biochemical_reaction_record_conversion_directions_from_human_reactome_to_ice_step_c",
 :reify ([?/direction_field {:ns "http://ccp.ucdenver.edu/kabob/ice/", :ln (:sha-1 "Reactome biochemical reaction record conversion direction field" ?/direction), :prefix "F_"}]),
 :head ((?/bcr_record obo/BFO_0000051 ?/direction_field)
        (?/direction_field rdf/type ccp/IAO_EXT_0001546)
        (?/direction_field rdfs/label ?/direction)),
 :sparql-string "#add_biochemical_reaction_record_conversion_directions_from_human_reactome_to_ice_step_c.clj
PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX bp3: <http://www.reactome.org/biopax/63/48887#>
SELECT ?bcr_record ?direction 
WHERE { 
?bcr rdf:type bp:BiochemicalReaction . # a biochemical reaction 
?bcr bp:conversionDirection ?direction .
GRAPH <file://add_biochemical_reaction_records_and_reactome_ids_from_human_reactome_to_ice_step_a.nt>
{
?bcr obo:IAO_0000142 ?bcr_record .
} 
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
