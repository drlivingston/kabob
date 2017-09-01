`{:name "transfer-links-to-nil"
  :description "This rule transfers links to rdf:nil to bio world. These links are
  predominantly '?x rdf:rest rdf/nil'."
  :head ((?/bio_list_member ?/p rdf/nil))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>

                  select ?bio_list_member ?p {
                  ?id obo:IAO_0000219 ?list_member .
                  ?list_member ?p rdf:nil .
                  ?id obo:IAO_0000219 ?bio_list_member .
                  filter (?list_member != ?bio_list_member && contains(str(?bio_list_member),'http://ccp.ucdenver.edu/obo/ext/'))
                  }"}