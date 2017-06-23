`{:name "transfer-direct-object-property-links"
  :description "This rule transfers direct links between ObjectProperty instances to bio world, e.g. rdfs:subPropertyOf, owl:inverseOf, etc."
  :head ((?/bio_p1 ?/bound_p ?/bio_p2))
  :sparql-string "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  prefix owl: <http://www.w3.org/2002/07/owl#>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>

                  select ?bio_p1 ?bound_p ?bio_p2 {
                                 ?p1 rdf:type owl:ObjectProperty .
                                 ?property_id_1 obo:IAO_0000219 ?p1 .
                                 ?field_value_1 rdf:type ?property_id_1 .
                                 ?field_value_1 rdf:type ccp:IAO_EXT_0000314 . # ccp:object_property_identifier_field_value
                                 ?property_id_1 obo:IAO_0000219 ?bio_p1 .
                                 filter (?p1 != ?bio_p1 && contains(str(?bio_p1),'http://ccp.ucdenver.edu/obo/ext/'))
                                 ?p1 ?p ?p2 .
                                 ?p2 rdf:type owl:ObjectProperty .
                                 ?property_id_2 obo:IAO_0000219 ?p2 .
                                 ?field_value_2 rdf:type ?property_id_2 .
                                 ?field_value_2 rdf:type ccp:IAO_EXT_0000314 . # ccp:object_property_identifier_field_value
                                 ?property_id_2 obo:IAO_0000219 ?bio_p2 .
                                 filter (?p2 != ?bio_p2 && contains(str(?bio_p2),'http://ccp.ucdenver.edu/obo/ext/'))

                                 # most relations between ObjectProperty instances use the rdfs and owl namespaces, e.g. rdfs:subPropertyOf, owl:inverseOf.
                                 # The following optional block checks to see if the property linking the two ObjectProperty instances found above has itself a
                                 # property record. If it does, then the query returns the bioworld version of the property. If not (e.g. rdfs:subPropertyOf), then the property itself is returned.
                                 optional {?p_id obo:IAO_0000219 ?p .
                                           ?field_value_3 rdf:type ?p_id .
                                           ?field_value_3 rdf:type ccp:IAO_EXT_0000314 . # ccp:object_property_identifier_field_value
                                           ?p_id obo:IAO_0000219 ?bio_p . # obo:denotes
                                           filter (?p != ?bio_p && contains(str(?bio_p),'http://ccp.ucdenver.edu/obo/ext/'))
                                           }
                                 bind(IF(bound(?bio_p), ?bio_p, ?p) as ?bound_p) .
                                 }"
  }





