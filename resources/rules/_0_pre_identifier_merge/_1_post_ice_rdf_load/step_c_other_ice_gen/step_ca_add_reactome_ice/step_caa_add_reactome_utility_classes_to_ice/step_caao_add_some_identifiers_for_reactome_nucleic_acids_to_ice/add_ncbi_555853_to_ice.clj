`{:description "This rule creates an NCBI record that is an exact match to GENBANK U13369.  See https://www.ncbi.nlm.nih.gov/nuccore/U13369 and https://www.ncbi.nlm.nih.gov/nuccore/555853 for why I think this match should exist.",
  :name "add_ncbi_555853_to_ice",
  :reify ([?/ncbi_ice]
          ),
  :head ((?/ncbi_ice skos/exactMatch ?/genbank_ice)
         (?/ncbi_ice rdfs/subClassOf ccp/IAO_EXT_0000262)
         )
 :body
"PREFIX franzOption_chunkProcessingAllowed: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bp: <http://www.biopax.org/release/biopax-level3.owl#>
PREFIX kice: <http://ccp.ucdenver.edu/kabob/ice/>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT ?genbank_ice ?ncbi_ice WHERE {
bind (uri (\"http://ccp.ucdenver.edu/kabob/ice/NCBI_GI_555853\") as ?ncbi_ice)
bind (uri (\"http://ccp.ucdenver.edu/kabob/ice/GENBANK_U13369\") as ?genbank_ice)
}",
  :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}


