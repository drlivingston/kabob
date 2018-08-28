;; ------------------------------------------------------------------------
;; --------- Ontology Concept Identifier Denotes Ontology Concept ---------
;; ------------------------------------------------------------------------
`{:name "step_b_pr_gene_ncbi_gene_exact_match"
  :description "This rule generates a skos:exactMatch relation between gene identifiers used by the protein ontology and NCBI gene identifiers"
  :head ((?/id obo/IAO_0000219 ?/ontology_concept) ; IAO:denotes
         (?/id rdfs/subClassOf ccp/IAO_EXT_0000088) ; CCP:ontology_concept_identifier
         (?/id rdfs/subClassOf ccp/IAO_EXT_0000084)) ; CCP:NCBI_gene_identifier
  :reify ([?/id {:ln (:regex "NCBIGene:" "NCBI_GENE_" ?/concept_id)
                 :ns "kice" :prefix "" :suffix ""}])
  :body "prefix franzOption_clauseReorderer: <franz:identity>
                  prefix franzOption_chunkProcessingAllowed: <franz:yes>
                  prefix ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  prefix obo: <http://purl.obolibrary.org/obo/>
                  prefix oboInOwl: <http://www.geneontology.org/formats/oboInOwl#>
                  select ?ontology_concept ?concept_id {
                  ?ontology_concept oboInOwl:id ?concept_id .
                  # include only concepts with the NCBI namespace
                  filter (contains (str(?ontology_concept), 'http://www.ncbi.nlm.nih.gov/gene'))
                  minus {?ontology_concept owl:deprecated true} .
                  }"
  }