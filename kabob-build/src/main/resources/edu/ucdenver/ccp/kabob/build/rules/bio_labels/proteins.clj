
{:name "protein-labels-UniProt-SwissProt"

 ;; The form of the new triples that will be constructed.
 :head '((?/bio rdfs/label ?/proteinName))

 ;; The query pattern used to retrieve the data that will be used to construct
 ;; the new triples.
 :body
 '(;; UniProt ICE that denotes a BIO entity that is a protein.
   (?/ice kiao/denotesSubClassOf obo/CHEBI_36080)
   (?/ice obo/IAO_0000219 ?/bio)
   (?/bio rdfs/subClassOf obo/CHEBI_36080)
   ;; Find the source field from which the ID was constructed.
   (_/accessionRecordField obo/IAO_0000219 ?/ice)
   (_/accessionRecordField kiao/hasTemplate iaouniprot/UniProtFileRecord_accessionDataField1)
   (_/accessionRecordField rdf/type kiao/FieldValue)
   ;; Link the source field to the record in which it was found.
   (_/record rdf/type iaouniprot/UniProtFileRecord)
   (_/record obo/BFO_0000051 _/accessionRecordField)
   ;; Necessarily a record that describes a protein.
   (_/record obo/BFO_0000051 _/typeRecordField)
   (_/typeRecordField kiao/hasTemplate iaouniprot/ProteinSchema1)
   (_/typeRecordField rdf/type iaouniprot/Protein)
   ;; This record will also contain the unique name of the protein.
   (_/record obo/BFO_0000051 _/nameRecord)
   (_/nameRecord kiao/hasTemplate iaouniprot/UniProtFileRecord_nameDataField1)
   (_/nameRecord obo/IAO_0000219 ?/proteinName))

 ;; In this case no `:reify` entry is required, since all of the data required
 ;; to form the new triples already exist.

 :options
 {:magic-prefixes
  [;; AllegroGraph processing directives; http://franz.com/agraph/support/documentation/current/sparql-reference.html#header2-138

   ;; Note: DO NOT set the clause reorderer to `identity`.  The default
   ;; `statistical` reorderer produces much more efficient results (as of this
   ;; writing, a small number of minutes for `statistical` compared to over an
   ;; hour (incomplete, terminated) for `identity`).

   ;; AllegroGraph should use "chunk at a time" processing.
   ;; This reduces memory consumption at the cost of query
   ;; execution time.  Given that the BIO entity set is
   ;; potentially quite large and that this is not a realtime
   ;; operation, this is not an unreasonable trade-off.
   ["franzOption_chunkProcessingAllowed" "franz:yes"]]}}

{:name "protein-labels-UniProt-Trembl"

 ;; The query pattern used to retrieve the data that will be used to construct
 ;; the new triples.
 :body
 '(;; UniProt ICE that denotes a BIO entity that is a protein.
   (?/ice kiao/denotesSubClassOf obo/CHEBI_36080)
   (?/ice obo/IAO_0000219 ?/bio)
   (?/bio rdfs/subClassOf obo/CHEBI_36080)
   ;; Find the source field from which the ID was constructed.
   (_/accessionRecordField obo/IAO_0000219 ?/ice)
   (_/accessionRecordField kiao/hasTemplate iaouniprot/SparseUniProtFileRecord_accessionDataField1)
   (_/accessionRecordField rdf/type kiao/FieldValue)
   ;; Link the source field to the record in which it was found.
   (_/record rdf/type iaouniprot/SparseUniProtFileRecord)
   (_/record obo/BFO_0000051 _/accessionRecordField)
   ;; This record will also contain the unique name of the protein.
   (_/record obo/BFO_0000051 _/nameRecord)
   (_/nameRecord kiao/hasTemplate iaouniprot/SparseUniProtFileRecord_nameDataField1)
   (_/nameRecord obo/IAO_0000219 ?/proteinName))

 ;; The form of the new triples that will be constructed.
 :head
 '((?/bio rdfs/label ?/proteinName))

 ;; In this case no `:reify` entry is required, since all of the data required
 ;; to form the new triples already exist.

 :options
 {:magic-prefixes
  [;; AllegroGraph processing directives; http://franz.com/agraph/support/documentation/current/sparql-reference.html#header2-138

   ;; Note: DO NOT set the clause reorderer to `identity`.  The default
   ;; `statistical` reorderer produces much more efficient results (as of this
   ;; writing, a small number of minutes for `statistical` compared to over an
   ;; hour (incomplete, terminated) for `identity`).

   ;; AllegroGraph should use "chunk at a time" processing.
   ;; This reduces memory consumption at the cost of query
   ;; execution time.  Given that the BIO entity set is
   ;; potentially quite large and that this is not a realtime
   ;; operation, this is not an unreasonable trade-off.
   ["franzOption_chunkProcessingAllowed" "franz:yes"]]}}
