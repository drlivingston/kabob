`{:description "This rule finds any unmodified MIRBASE rna record described in Reactome.  The rule generates the Reactome rna's ICE identifier, and places the top-level entity on the BIO side as a localized subclass of the BIO entity denoted by the MIRBASE ICE id.  It also makes the denotational link to a new MIRBASE BIO entity if one doesn't exist already.",
 :name "add_reactome_mirbase_rnas_to_bio_1",
 :reify ([?/original_foo {:ln (:sha-1 "bio-side reactome rna" ?/rna_record), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_"}]
         [?/mirbase_bio {:ln (:sha-1 "MIRBASE RNA" ?/accession_label), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_"}]
         [?/trans_restriction {:ln (:restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/target_restriction {:ln (:restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/gocc_loc {:ln (:sha-1 ?/gocc_bio), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/localization_sc {:ln (:sha-1 "GO_0051179" ?/target_restriction ?/trans_restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "LOC_"}]),
 :head ((?/reactome_ice obo/IAO_0000219 ?/original_foo)
        (?/mirbase_ice obo/IAO_0000219 ?/mirbase_bio)
        (?/original_foo rdfs/subClassOf ?/mirbase_bio)
        (?/gocc_loc rdfs/subClassOf ?/gocc_bio)
        (?/trans_restriction owl/someValuesFrom ?/original_foo)
        (?/trans_restriction rdf/type owl/Restriction)
        (?/trans_restriction owl/onProperty ?/transports_bio)
        (?/target_restriction owl/someValuesFrom ?/gocc_loc)
        (?/target_restriction rdf/type owl/Restriction)
        (?/target_restriction owl/onProperty ?/targets_bio)
        (?/localization_sc rdfs/subClassOf ?/target_restriction)
        (?/localization_sc rdfs/subClassOf ?/trans_restriction)
        (?/localization_sc rdfs/subClassOf ?/localization_bio)
        ),
 :body "#add_reactome_mirbase_rnas_to_bio_1
PREFIX franzOption_memoryLimit: <franz:85g>
PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95>
PREFIX franzOption_logQuery: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT DISTINCT ?reactome_ice ?mirbase_ice ?accession_label ?transports_bio ?targets_bio ?localization_bio ?gocc_bio ?rna_record 
WHERE {
?rna_record rdf:type ccp:IAO_EXT_0001558 .  # rna record
?rna_record obo:BFO_0000051 ?entity_record .
?entity_record rdf:type ccp:IAO_EXT_0001561 .  # rna reference record
?entity_record obo:BFO_0000051 ?xref_record .
?xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?xref_record obo:BFO_0000051 ?mirbase_field .
?mirbase_field rdf:type ccp:IAO_EXT_0001594 . # Reactome MIRBASE field
?mirbase_field rdfs:label ?accession_label .
?mirbase_field rdf:type ?mirbase_ice .
filter (contains (str (?mirbase_ice), \"http://ccp.ucdenver.edu/kabob/ice/MIRBASE_\")) .
bind (uri (concat (str (\"http://purl.obolibrary.org/obo/MIRBASE_\"), str (?accession_label))) AS ?obo_mirbase) .
OPTIONAL {
?mirbase_ice obo:IAO_0000219 ?mirbase_bio .
?mirbase_ice obo:IAO_0000219 ?obo_mirbase .
filter (?obo_mirbase != ?mirbase_bio) .
}
filter (!bound (?mirbase_bio)) .
# make sure there this is not a RNA variant
OPTIONAL {
?rna_record obo:BFO_0000051 ?feature_record .
?feature_record rdf:type ccp:IAO_EXT_0001527 . # no features allowed
}
filter (!bound (?feature_record)) .
?rna_record obo:BFO_0000051 ?react_xref_record .
?react_xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?react_xref_record obo:BFO_0000051 ?react_id_field .
?react_id_field rdf:type ?reactome_ice .
filter (contains (str (?reactome_ice), \"http://ccp.ucdenver.edu/kabob/ice/REACTOME_\")) .
?rna_record obo:BFO_0000051 ?location_record .
?location_record rdf:type ccp:IAO_EXT_0001521 . # cellular location
?location_record obo:BFO_0000051 ?gocc_xref_record . 
?gocc_xref_record rdf:type ccp:IAO_EXT_0001588 .
?gocc_xref_record obo:BFO_0000051 ?gocc_xref_id_field .
?gocc_xref_id_field rdf:type ?gocc_ice .
?gocc_xref_id_field rdfs:label ?gocc_id .
filter (contains (str (?gocc_ice), \"http://ccp.ucdenver.edu/kabob/ice/GO_\")) . 
bind (uri (concat (str (\"http://purl.obolibrary.org/obo/GO_\"), strafter (str (?gocc_id), str (\":\")))) AS ?obo_gocc_uri) .
?gocc_ice obo:IAO_0000219 ?gocc_bio .
filter (?gocc_bio != ?obo_gocc_uri) .
bind (uri (str (\"http://ccp.ucdenver.edu/kabob/ice/RO_0002313\")) AS ?transports) .
?transports obo:IAO_0000219 ?transports_bio .
filter (?transports_bio != obo:RO_0002313) .
bind (uri (str (\"http://ccp.ucdenver.edu/kabob/ice/RO_0002339\")) AS ?targets) .
?targets obo:IAO_0000219 ?targets_bio .
filter (?targets_bio != obo:RO_0002339) .
bind (uri (str (\"http://ccp.ucdenver.edu/kabob/ice/GO_0051179\")) AS ?localization) .
?localization obo:IAO_0000219 ?localization_bio .
filter (?localization_bio != obo:GO_0051179) .
}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
