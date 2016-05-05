
{:name "gene-labels-EntrezGene"

 ;; The query pattern used to retrieve the data that will be used to construct
 ;; the new triples.
 :body
 '(;; field value that contains the identifier
   (_/gene_id_field kiao/hasTemplate iaoeg/EntrezGeneInfoFileData_geneIDDataField1)
   (_/gene_id_field rdf/type kiao/FieldValue)
   ;; the file record in which the field value appeared (record has-part
   ;; field-value)
   (_/record rdf/type iaoeg/EntrezGeneInfoFileData)
   (_/record obo/BFO_0000051 _/gene_id_field)
   ;; the record will also have fields for the symbol
   (_/record obo/BFO_0000051 _/eg_symbol_field)
   (_/eg_symbol_field kiao/hasTemplate iaoeg/EntrezGeneInfoFileData_symbolDataField1)
   (_/eg_symbol_field obo/IAO_0000219 ?/eg_symbol)
   ;; ICE that denotes a subclass of DNA (gene).
   (_/gene_id_field obo/IAO_0000219 _/ice)
   (_/ice kiao/denotesSubClassOf obo/SO_0000352)
   (_/ice obo/IAO_0000219 ?/bio))

 ;; The form of the new triples that will be constructed.
 :head
 '((?/bio rdfs/label ?/eg_symbol))

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
