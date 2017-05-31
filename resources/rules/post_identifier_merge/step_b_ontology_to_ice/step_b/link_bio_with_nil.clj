`{:name "transfer-rdf-list-links-with-nil"
  :description "This rule transfers links to rdf:nil from list members to bio world. These links are
  predominantly '?x rdf:rest rdf/nil'."
  :head ((?/bio_list_member ?/p rdf/nil))
  :sparql-string "select ?bio_list_member ?p {
  ?list_record rdf:type ccp:IAO_EXT_0000317 .
  ?list_record obo:IAO_0000219 ?list_member .
  ?list_member ?p rdf:nil .
  ?list_record obo:IAO_0000219 ?bio_list_member .
  filter (?list_member != ?bio_list_member)
  }"}