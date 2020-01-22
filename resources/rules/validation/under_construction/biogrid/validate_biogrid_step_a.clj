;; -----------------------------------------------
;; --------- IRefWeb Binary Interactions ---------
;; -----------------------------------------------
`{:name          "validation_only-two-bioentities-mentioned-per-biogrid-record-EXPECT-0"
  :description   "This rule produces no triples, but instead validates that there are no more than two distinct entities
                  mentioned by each biogrid record. This check validates the first step in the biogrid ice-to-bio transformation.
                  Steps were necessary to simplify the overall queries."
  :head          ()

  :body "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
    PREFIX obo: <http://purl.obolibrary.org/obo/>
    PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
    PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
    select (count (distinct ?record) as ?count) {
       ?record rdf:type ccp:IAO_EXT_0001777 . # ccp:BIOGRID_PROTEIN_INTERACTION_RECORD
       ?record obo:IAO_0000142 ?bioentity1 .
       ?record obo:IAO_0000142 ?bioentity2 .
       ?record obo:IAO_0000142 ?bioentity3 .
       filter (?bioentity1 != ?bioentity2)
       filter (?bioentity1 != ?bioentity3)
       filter (?bioentity3 != ?bioentity2)
       }"

  }