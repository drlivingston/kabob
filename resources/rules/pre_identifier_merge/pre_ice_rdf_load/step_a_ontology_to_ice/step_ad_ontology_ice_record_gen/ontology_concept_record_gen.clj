;; -----------------------------------------------
;; --------- Ontology Concept Record Gen ---------
;; -----------------------------------------------
`{:name "ontology-concept-record-gen"
  :description "This rule generates an instance of an Ontology Concept Record (IAO_EXT_0000191) for each ontology concept, including field values for the concept identifier, definition, and label."
  :head ((?/record rdf/type ccp/IAO_EXT_0000191) ; CCP:ontology_concept_record
          (?/record obo/BFO_0000051 ?/id_field_value) ; BFO:has_part
          (?/id_field_value rdf/type ccp/IAO_EXT_0000034) ; CCP:identifier_field_value
          (?/id_field_value rdf/type ?/id) ; CCP:ontology_concept_identifier
          (?/id_field_value rdfs/label ?/bound_id_string)
         (?/record obo/BFO_0000051 ?/definition_field_value) ; BFO:has_part
         (?/definition_field_value rdf/type ccp/IAO_EXT_0000195) ; CCP:ontology_concept_record_definition_field_value
         (?/definition_field_value rdfs/label ?/bound_definition)
         (?/record obo/BFO_0000051 ?/label_field_value); BFO:has_part
         (?/label_field_value rdf/type ccp/IAO_EXT_0000196) ; CCP:ontology_concept_record_label_field_value
         (?/label_field_value rdfs/label ?/bound_label))
  :reify ([?/record {:ln (:sha-1 ?/id)
                    :ns "ccp" :prefix "R_" :suffix ""}]
           [?/id_field_value {:ln (:sha-1 ?/id)
               :ns "ccp" :prefix "F_" :suffix ""}]
           [?/definition_field_value {:ln (:sha-1 ?/bound_definition)
               :ns "ccp" :prefix "F_" :suffix ""}]
           [?/label_field_value {:ln (:sha-1 ?/bound_label)
               :ns "ccp" :prefix "F_" :suffix ""}])
  :sparql-string "prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select distinct ?id ?class ?bound_definition ?bound_label ?bound_id_string {
                  ?id rdfs:subClassOf ccp:IAO_EXT_0000088 . # CCP:ontology_concept_identifier
                  ?id obo:IAO_0000219 ?class . # IAO:dentoes
                  optional {?class obo:IAO_0000115 ?definition} .
                  optional {?class obo:IAO_0000600 ?elucidation} .
                  optional {?class rdfs:label ?label } .
                  optional {?class oboInOwl:id ?id_string } .
                  bind(IF( bound(?definition), ?definition,
                                       if(bound(?elucidation), ?elucidation, 'no definition')
                                       ) as ?bound_definition) .
                  bind(IF(bound(?id_string), ?id_string, 'no id string') as ?bound_id_string) .
                  bind(IF(bound(?label), ?label, 'no label') as ?bound_label) .
                  }" 
  }