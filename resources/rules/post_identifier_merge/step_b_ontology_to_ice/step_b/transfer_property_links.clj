`{



  :sparql-string "select ?ont_component_record ?bio_member {
  # things that are denoted by something are bio things, but could be kabob-bio or obo-bio
  ?thing obo:IAO_0000219 ?bio_member .

  # the thing is either an identifier, or a ontology_component_record instance

  #?ont_component_record rdfs:subClassOf* ccp:IAO_EXT_0000309 .
  #?ont_component_record_instance rdf:type ?ont_component_record .
  #?ont_component_record_instance obo:IAO_0000219 ?bio_member .
  }"
  }


keep in mind the properties themselves need to be denoted by something, so we need to make the property hierarchy prior
to implementing this rule.

Can this be a single rule (would be slick), but I wonder if it needs to be separated into ont_record-ont_record relations and ont_record-bio_entity relations, etc.




Looks like there are rules for the pre-merge part of the property transfer.
Test those rules on CL.

Also test the rules on the RO + all referenced ontologies (see trello card)

Write rules (this one and others) to transfer the property hierarchy. See trello card for list of constructs that need to be transfered


###
### Writing functionality for sanity check queries might be most important next step ###
###
Need sanity checks (queries) to test for ontology classes that are referenced by range/domain expressions that are missing.