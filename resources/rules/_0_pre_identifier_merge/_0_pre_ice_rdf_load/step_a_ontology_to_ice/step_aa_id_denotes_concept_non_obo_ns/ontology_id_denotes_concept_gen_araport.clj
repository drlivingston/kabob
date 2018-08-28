;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
`{:name "ontology-id-denotes-concept-gen-araport"
  :description "This rule generates an ontology concept identifier for every non-root ontology concept with the ARAPORT namespace."
  :head ((?/std_ice_id obo/IAO_0000219 ?/ontology_concept) ; IAO:denotes
          (?/std_ice_id rdfs/subClassOf ccp/IAO_EXT_0000088) ; CCP:ontology_concept_identifier
          (?/std_ice_id rdfs/subClassOf ccp/IAO_EXT_0001858) ; CCP:Araport_identifier
          (?/std_ice_id skos/exactMatch ?/ice_id))
  ;; why are there two ice id's being created? This is inefficient (b/c an extra ice identifier is created) but necessary.
  ;; The std_ice_id is an ice identifier created to match the ice identifiers generated
  ;; by the file parsing machinery. The ice_id is an identifier that will get generated
  ;; in step_ab. We create them both here so that we can apply the skos:exactMatch link
  ;; between them (to ensure that only a single bioentity is created downstream). The
  ;; original intent of the rules in step_aa was to create the std_ice_id only however
  ;; due to a need to use the ?graph variable as part of the query in step_ab, it is
  ;; not possible to skip the generation of the ice_id. Adding the skos:exactMatch link
  ;; is a simple compromise.
  :reify ([?/ice_id {:ln (:localname ?/ontology_concept)
                     :ns "kice" :prefix "" :suffix ""}]
           [?/std_ice_id {:ln (:regex "Araport:" "ARAPORT_" ?/concept_id)
                 :ns "kice" :prefix "" :suffix ""}])
  :body "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept ?concept_id {
                  ?ontology_concept oboInOwl:id ?concept_id .
                  # include only concepts with the TAIR namespace
                  filter (contains (str(?ontology_concept), 'https://www.araport.org/locus/'))
                  minus {?ontology_concept owl:deprecated true} .
                  }"
  }