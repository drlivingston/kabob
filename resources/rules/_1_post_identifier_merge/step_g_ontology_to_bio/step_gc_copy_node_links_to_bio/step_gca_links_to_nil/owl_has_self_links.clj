`{:name "step-gca_owl-has-self-links"
  :description "This rule transfers links from restriction nodes via the owl:hasSelf property (typically to true)."
  :head ((?/bio_restriction owl/hasSelf ?/value))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
         prefix franzOption_chunkProcessingAllowed: <franz:yes>
         prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
         prefix obo: <http://purl.obolibrary.org/obo/>
         select distinct ?id ?bio_restriction ?value {
           ?id rdf:type ccp:IAO_EXT_0001710 .
           ?id obo:IAO_0000219 ?obo_restriction .
           ?obo_restriction owl:hasSelf ?value .
           ?id obo:IAO_0000219 ?bio_restriction .
           filter (contains (str (?bio_restriction), \"kabob\"))
           }"

  }