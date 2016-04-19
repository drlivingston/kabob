
{:name "protein-labels-UniProt"

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
   ;; This record will also contain the recommended name of the protein.
   (_/typeRecordField obo/BFO_0000051 _/recommendedNameRecord)
   (_/recommendedNameRecord kiao/hasTemplate iaouniprot/RecommendedNameSchema1)
   (_/recommendedNameRecord rdf/type iaouniprot/RecommendedName)
   ;; The recommended name field is an evidenced string.
   (_/recommendedNameRecord obo/BFO_0000051 _/evidencedStringRecord)
   (_/evidencedStringRecord kiao/hasTemplate iaouniprot/EvidencedStringSchema1)
   (_/evidencedStringRecord rdf/type iaouniprot/EvidencedString)
   ;; The value of the evidenced string is what we want as the protein name.
   (_/evidencedStringRecord obo/BFO_0000051 _/evidencedStringValue)
   (_/evidencedStringValue obo/IAO_0000219 ?/proteinName))

 ;; The form of the new triples that will be constructed.
 :head
 '((?/bio rdf/label ?/proteinName))

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
