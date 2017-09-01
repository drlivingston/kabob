;; the refseq/uniprot collab file does not contain exact matches, so this rule is faulty
;; TODO: only make links if the refseq id is not already linked to a child of the uniprot id -- they might not necessarily be parent-child, so only link where refseq is not already linked.
;; -----------------------------------------------------------------
;; --------- RefSeq Protein Uniprot Identifier Exact Match ---------
;; -----------------------------------------------------------------
`{:name "refseq-protein-uniprot-identifier-exact-match"
  :description "This rule links mappings among protein identifiers using the skos:exactMatch predicate"
  :head ((?/refseq_identifier skos/exactMatch ?/uniprot_identifier))
:sparql-string "PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
                PREFIX obo: <http://purl.obolibrary.org/obo/>
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                prefix skos: <http://www.w3.org/2004/02/skos/core#>
                SELECT ?refseq_identifier ?uniprot_identifier WHERE {
                  ?record rdf:type ccp:IAO_EXT_0000692 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record
                  ?record obo:BFO_0000051 ?refseq_protein_identifier_field_value .
                  ?refseq_protein_identifier_field_value rdf:type ccp:IAO_EXT_0000927 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_refseq_protein_identifier_field_value
                  ?refseq_protein_identifier_field_value rdf:type ?refseq_identifier .
                  ?refseq_identifier rdfs:subClassOf ccp:IAO_EXT_0001638 . # CCP:refseq_protein_identifier
                  ?record obo:BFO_0000051 ?uniprot_identifier_field_value .
                  ?uniprot_identifier_field_value rdf:type ccp:IAO_EXT_0000928 . # CCP:ncbi_gene_refseq_uniprotkb_collaboration_record_uniprot_identifier_field_value
                  ?uniprot_identifier_field_value rdf:type ?uniprot_identifier .
                  ?uniprot_identifier rdfs:subClassOf ccp:IAO_EXT_0000184 . # CCP:uniprot_identifier

                  # only include matches if the refseq identifier is not already in a skos:exactMatch relation
                  # b/c if it is, then it is already directly linked to a uniprot isoform id
                  minus {?refseq_identifier skos:exactMatch ?other_id}
                  minus {?other_id skos:exactMatch ?refseq_identifier}
                }"
}
