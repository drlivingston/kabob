`{:name "step-gca_svf-owl-thing-links"
  :description "This rule transfers links from restriction nodes from owl:someValuesFrom to owl:Thing."
  :head ((?/bio_restriction owl/someValuesFrom owl/Thing))
  :body "prefix franzOption_clauseReorderer: <franz:identity>
         prefix franzOption_chunkProcessingAllowed: <franz:yes>
         prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
         prefix obo: <http://purl.obolibrary.org/obo/>
         select distinct ?id ?bio_restriction {
             ?obo_restriction owl:someValuesFrom owl:Thing .
             ?id obo:IAO_0000219 ?obo_restriction .
             ?id rdf:type ccp:IAO_EXT_0001710 .
             ?id obo:IAO_0000219 ?bio_restriction .
             filter (contains (str (?bio_restriction), \"kabob\"))
           }"

  }