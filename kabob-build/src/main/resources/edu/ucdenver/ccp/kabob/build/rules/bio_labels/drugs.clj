{:name "drug-labels-DrugBank"

 ;; The query pattern used to retrieve the data that will be used to construct
 ;; the new triples.
 :body
 '(;; field value that contains the identifier
   (_/drugbank_id_field kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugBankIdDataField1)
   (_/drugbank_id_field rdf/type kiao/FieldValue)
   ;; the file record in which the field value appeared (record has-part
   ;; field-value)
   (_/record obo/BFO_0000051 _/drugbank_id_field)
   (_/record kiao/hasTemplate iaodrugbank/DrugBankDrugRecordSchema1)
   ;; the record will also have fields for the drug name
   (_/record obo/BFO_0000051 _/drug_name_field)
   (_/drug_name_field kiao/hasTemplate iaodrugbank/DrugBankDrugRecord_drugNameDataField1)
   (_/drug_name_field obo/IAO_0000219 ?/drug_name)
   ;; ICE that denotes a subclass of drug .
   (_/drugbank_id_field obo/IAO_0000219 _/ice)
   (_/ice kiao/denotesSubClassOf obo/CHEBI_23888) ;; CHEBI:drug
   (_/ice obo/IAO_0000219 ?/bio))

 ;; The form of the new triples that will be constructed.
 :head
 '((?/bio rdfs/label ?/drug_name))

 ;; In this case no `:reify` entry is required, since all of the data required
 ;; to form the new triples already exist.

 :options
 {:magic-prefixes
  [;; AllegroGraph processing directives; http://franz.com/agraph/support/documentation/current/sparql-reference.html#header2-138

   ;; AllegroGraph should use "chunk at a time" processing.
   ;; This reduces memory consumption at the cost of query
   ;; execution time.  Given that the BIO entity set is
   ;; potentially quite large and that this is not a realtime
   ;; operation, this is not an unreasonable trade-off.
   ["franzOption_chunkProcessingAllowed" "franz:yes"]]}}
