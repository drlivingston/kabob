`{:name "transfer-direct-links"
  :description "This rule transfers links between ontology components to representations of those components in bioworld."
  :head ((?/bio1 ?/bound_p ?/bio2))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>

                  select ?bio1 ?bound_p ?bio2 {
                                                ?id1 obo:IAO_0000219 ?thing1 .
                                                # transfer of the ObjectProperty hierarchy is handled by a different rule so we exclude it here.
                                                # thing2 below is not excluded from being an ObjectProperty however so that the owl:onProperty links can be transferred.
                                                minus {?thing1 rdf:type owl:ObjectProperty .}
                                                ?field_value_1 rdf:type ?id1 .
                                                ?record_1 obo:BFO_0000051 ?field_value_1 .
                                                ?record_1 rdf:type ?record_type_1 .
                                                ?record_type_1 rdfs:subClassOf ccp:IAO_EXT_0000309 .
                                                ?id1 obo:IAO_0000219 ?bio1 .
                                                filter (?thing1 != ?bio1 && contains(str(?bio1),'http://ccp.ucdenver.edu/obo/ext/'))

                                                ?thing1 ?p ?thing2 .

                                                ?id2 obo:IAO_0000219 ?thing2 .
                                                ?field_value_2 rdf:type ?id2 .
                                                ?record_2 obo:BFO_0000051 ?field_value_2 .
                                                ?record_2 rdf:type ?record_type_2 .
                                                ?record_type_2 rdfs:subClassOf ccp:IAO_EXT_0000309 .
                                                ?id2 obo:IAO_0000219 ?bio2 .
                                                filter (?thing2 != ?bio2 && contains(str(?bio2),'http://ccp.ucdenver.edu/obo/ext/'))

                                                # The following optional block checks to see if the property linking the two things found above has itself a
                                                # property record. If it does, then the query returns the bioworld version of the property. If not (e.g. rdfs:subPropertyOf or rdfs:subClassOf), then the property itself is returned.
                                                optional {?p_id obo:IAO_0000219 ?p .
                                                          ?field_value_3 rdf:type ?p_id .
                                                          ?field_value_3 rdf:type ccp:IAO_EXT_0000314 . # ccp:object_property_identifier_field_value
                                                          ?p_id obo:IAO_0000219 ?bio_p .
                                                          filter (?p != ?bio_p && contains(str(?bio_p),'http://ccp.ucdenver.edu/obo/ext/'))
                                                          }
                                                bind(IF(bound(?bio_p), ?bio_p, ?p) as ?bound_p) .
                                                }"
  }