;; -------------------------------------------------------------------------
;; --------- BioGRID identifier to NCBI Gene Identifier Exact Match --------
;; -------------------------------------------------------------------------
`{:name "step-db_biogrid-gene-identifier-ncbi-gene-identifier-exact-match"
  :description "This rule asserts an exact match between a biogrid gene and an ncbi gene"
  :head ((?/interactor_b_biogrid_identifier skos/exactMatch ?/interactor_b_ncbi_gene_identifier))
  :body "prefix franzOption_chunkProcessingAllowed: <franz:yes>
  prefix franzOption_clauseReorderer: <franz:identity>
  PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                  PREFIX obo: <http://purl.obolibrary.org/obo/>
                  PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                  PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                  SELECT distinct ?interactor_b_biogrid_identifier ?interactor_b_ncbi_gene_identifier
                  WHERE {

                  {
                    select distinct ?record ?interactor_b_biogrid_identifier {
                      ?record rdf:type ccp:IAO_EXT_0001777 . # CCP:BioGRID_protein_interaction_record
                      ?record obo:BFO_0000051 ?interactor_b_biogrid_identifier_field_value .
                      ?interactor_b_biogrid_identifier_field_value rdf:type ccp:IAO_EXT_0001810 . # CCP:interactor_b_biogrid_identifier_field_value
                      ?interactor_b_biogrid_identifier_field_value rdf:type ?interactor_b_biogrid_identifier .
                      ?interactor_b_biogrid_identifier rdfs:subClassOf ccp:IAO_EXT_0001772 . # CCP:BioGrid_interactor_identifier
                    }
                  }
                  {
                  select distinct ?record ?interactor_b_ncbi_gene_identifier {
                      ?record rdf:type ccp:IAO_EXT_0001777 . # CCP:BioGRID_protein_interaction_record
                      ?record obo:BFO_0000051 ?interactor_b_ncbi_gene_identifier_field_value .
                      ?interactor_b_ncbi_gene_identifier_field_value rdf:type ccp:IAO_EXT_0001808 . # CCP:interactor_b_ncbi_gene_identifier_field_value
                      ?interactor_b_ncbi_gene_identifier_field_value rdf:type ?interactor_b_ncbi_gene_identifier .
                      ?interactor_b_ncbi_gene_identifier rdfs:subClassOf ccp:IAO_EXT_0000084 . # ccp:NCBI_gene_identifier
                     }
                    }
                  }"
}
