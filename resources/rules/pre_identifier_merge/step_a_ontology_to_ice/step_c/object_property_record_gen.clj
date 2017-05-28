;; -----------------------------------------------
;; --------- Object Property Record Gen ---------
;; -----------------------------------------------
`{:name "object-property-record-gen"
  :description "This rule generates an instance of an Object Property
  Record (IAO_EXT_0000310) for each object property, including field
  values for the property identifier, definition, and label."
  :head ((?/record rdf/type ccp/IAO_EXT_0000310) ;; ccp:object property record
         (?/record obo/BFO_0000051 ?/id_field_value) ; obo:has part
         (?/id_field_value rdf/type ccp/IAO_EXT_0000314) ; ccp:object property id field value
         (?/id_field_value rdf/type ?/id) 
         (?/id_field_value rdfs/label ?/bound_id_string)
         (?/record obo/BFO_0000051 ?/definition_field_value) ; obo:has part
         (?/definition_field_value rdf/type ccp/IAO_EXT_0000313) ; ccp:object property record definition field value
         (?/definition_field_value rdfs/label ?/bound_definition)
         (?/record obo/BFO_0000051 ?/label_field_value)
         (?/label_field_value rdf/type ccp/IAO_EXT_0000315) ; ccp:object property record label field value
         (?/label_field_value rdfs/label ?/bound_label))
  :reify ([?/record {:ln (:sha-1 ?/id)
                    :ns "ccp" :prefix "R_" :suffix ""}]
           [?/id_field_value {:ln (:sha-1 ?/id)
               :ns "ccp" :prefix "F_" :suffix ""}]
           [?/definition_field_value {:ln (:sha-1 ?/bound_definition)
               :ns "ccp" :prefix "F_" :suffix ""}]
           [?/label_field_value {:ln (:sha-1 ?/bound_label)
               :ns "ccp" :prefix "F_" :suffix ""}])
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id ?property ?bound_definition ?bound_label ?bound_id_string {
                  ?id rdf:type ccp:IAO_EXT_0000306 .
                  ?id obo:IAO_0000219 ?property .
                  optional {?property obo:IAO_0000115 ?definition } .
                  optional {?property rdfs:label ?label } .
                  optional {?property oboInOwl:id ?id_string } .
                  bind(IF(bound(?definition), ?definition, 'no definition') as ?bound_definition) .
                  bind(IF(bound(?id_string), ?id_string, 'no id string') as ?bound_id_string) .
                  bind(IF(bound(?label), ?label, 'no label') as ?bound_label) .
                  }" }
