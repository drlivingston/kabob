;; -----------------------------------------------
;; --------- Ontology Concept Record Gen ---------
;; -----------------------------------------------
;; creates ontology concept record for every ontology concept
`{:name "ontology-concept-record-gen"
  :head ((?/record rdf/type ccp/IAO_EXT_0000191) ; ontology concept record
         (?/record obo/BFO_0000051 ?/id_field_value) ; has part
         (?/id_field_value rdf/type ccp/IAO_EXT_0000194) ; id field value
         (?/id_field_value rdf/type ?/id) ; ontology concept identifier
         (?/id_field_value rdfs/label ?/id_string)
         ;(?/record obo/BFO_0000051 ?/definition_field_value) ; has part
         ;(?/definition_field_value rdf/type ccp/IAO_EXT_0000195) ; ontology concept record definition field value
         ;(?/definition_field_value rdfs/label ?/definition)
         (?/record obo/BFO_0000051 ?/label_field_value)
         (?/label_field_value rdf/type ccp/IAO_EXT_0000196) ; ontology concept record label field value
         (?/label_field_value rdfs/label ?/label))
  :reify ([?/record {:ln (:sha-1 ?/id)
                    :ns "ccp" :prefix "R_" :suffix ""}]
           [?/id_field_value {:ln (:sha-1 ?/id)
               :ns "ccp" :prefix "F_" :suffix ""}]
           ;[?/definition_field_value {:ln (:sha-1 ?/definition)
               ;:ns "ccp" :prefix "F_" :suffix ""}]
           [?/label_field_value {:ln (:sha-1 ?/label)
               :ns "ccp" :prefix "F_" :suffix ""}])
  :sparql-string "prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?id ?class ?bound_definition ?label ?id_string {
                  ?id rdf:type ccp:IAO_EXT_0000088 .
                  ?id obo:IAO_0000219 ?class .
                  optional {?class obo:IAO_0000115 ?definition } .
                  optional {?class rdfs:label ?label } .
                  optional {?class oboInOwl:id ?id_string } .
                  bind(IF(bound(?definition), ?definition, 'no definition') as ?bound_definition) .
                  }" }
