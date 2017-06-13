`{:name "transfer-rdf-list-links-to-nil"
  :description "This rule transfers links to rdf:nil from list members to bio world. These links are
  predominantly '?x rdf:rest rdf/nil'."
  :head ((?/bio_list_member ?/p rdf/nil))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>

                  select ?bio_list_member ?p {
                  ?list_record rdf:type ccp:IAO_EXT_0000317 .
                  ?list_record obo:BFO_0000051 ?id_field_value .
                  ?id_field_value rdf:type ccp:IAO_EXT_0000346 . # ccp:RDF_list_identifier_field_value
                  ?id_field_value rdf:type ?id .
                  ?id rdf:type ccp:IAO_EXT_0000354 .
                  ?id obo:IAO_0000219 ?list_member .
                  ?list_member ?p rdf:nil .
                  ?id obo:IAO_0000219 ?bio_list_member .
                  filter (?list_member != ?bio_list_member && contains(str(?bio_list_member),'http://ccp.ucdenver.edu/obo/ext/'))
                  }"}