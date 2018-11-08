`{:description "This rule finds any modified ENSEMBL dna record described in Reactome.  The rule generates the Reactome dna's ICE identifier, and places the top-level entity on the BIO side as a localized subclass of the BIO entity denoted by the ENSEMBL ICE id.  It also makes the denotational link to a new ENSEMBL BIO entity if one doesn't exist already.",
 :name "add_reactome_ensembl_dnas_to_bio_2",
 :reify ([?/modified_foo {:ln (:sha-1 "bio-side modified reactome dna" ?/dna_record), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_"}]
         [?/original_foo {:ln (:sha-1 "bio-side reactome dna" ?/dna_record), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_"}]
         [?/ensembl_bio {:ln (:sha-1 "ENSEMBL DNA" ?/accession_label), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "B_"}]
         [?/trans_restriction {:ln (:restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/target_restriction {:ln (:restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/variant_restriction {:ln (:restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/gocc_loc {:ln (:sha-1 ?/gocc_bio), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "RS_"}]
         [?/localization_sc {:ln (:sha-1 "GO_0051179" ?/target_restriction ?/trans_restriction), :ns "http://ccp.ucdenver.edu/kabob/bio/" :prefix "LOC_"}]),
 :head ((?/reactome_ice obo/IAO_0000219 ?/original_foo)
        (?/ensembl_ice obo/IAO_0000219 ?/ensembl_bio)
        (?/original_foo rdfs/subClassOf ?/ensembl_bio)
        (?/modified_foo rdfs/subClassOf ?/variant_restriction)
        (?/variant_restriction owl/onProperty ?/variant_of_bio)
        (?/variant_restriction owl/someValuesFrom ?/original_foo)
        (?/variant_restriction rdf/type owl/Restriction)
        (?/gocc_loc rdfs/subClassOf ?/gocc_bio)
        (?/trans_restriction owl/someValuesFrom ?/modified_foo)
        (?/trans_restriction rdf/type owl/Restriction)
        (?/trans_restriction owl/onProperty ?/transports_bio)
        (?/target_restriction owl/someValuesFrom ?/gocc_loc)
        (?/target_restriction rdf/type owl/Restriction)
        (?/target_restriction owl/onProperty ?/targets_bio)
        (?/localization_sc rdfs/subClassOf ?/target_restriction)
        (?/localization_sc rdfs/subClassOf ?/trans_restriction)
        (?/localization_sc rdfs/subClassOf ?/localization_bio)
        ),
 :body "#add_reactome_ensembl_dnas_to_bio_2
PREFIX franzOption_memoryLimit: <franz:85g>
PREFIX franzOption_memoryExhaustionWarningPercentage: <franz:95>
PREFIX franzOption_logQuery: <franz:yes>
PREFIX franzOption_clauseReorderer: <franz:identity>
PREFIX ccp: <http://ccp.ucdenver.edu/obo/ext/>
PREFIX obo: <http://purl.obolibrary.org/obo/>
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX kbio: <http://ccp.ucdenver.edu/kabob/bio/>
SELECT DISTINCT ?reactome_ice ?ensembl_ice ?accession_label ?transports_bio ?targets_bio ?localization_bio ?gocc_bio ?dna_record ?variant_of_bio
WHERE {
?dna_record rdf:type ccp:IAO_EXT_0001556 .  # dna record
?dna_record obo:BFO_0000051 ?entity_record .
?entity_record rdf:type ccp:IAO_EXT_0001560 .  # dna reference record
?entity_record obo:BFO_0000051 ?xref_record .
?xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?xref_record obo:BFO_0000051 ?ensembl_field .
?ensembl_field rdf:type ccp:IAO_EXT_0001523 . # Reactome ENSEMBL field
?ensembl_field rdfs:label ?accession_label .
?ensembl_field rdf:type ?ensembl_ice .
filter (contains (str (?ensembl_ice), \"http://ccp.ucdenver.edu/kabob/ice/ENSEMBL_\")) .
bind (uri (concat (str (\"http://purl.obolibrary.org/obo/ENSEMBL_\"), str (?accession_label))) AS ?obo_ensembl) .
OPTIONAL {
?ensembl_ice obo:IAO_0000219 ?ensembl_bio .
?ensembl_ice obo:IAO_0000219 ?obo_ensembl .
filter (?obo_ensembl != ?ensembl_bio) .
}
filter (!bound (?ensembl_bio)) .
# make sure there this is a DNA variant
OPTIONAL {
?dna_record obo:BFO_0000051 ?modification_feature_record .
?modification_feature_record rdf:type ccp:IAO_EXT_0001586 . #  modification features allowed
}
OPTIONAL {
?dna_record obo:BFO_0000051 ?fragment_feature_record .
?fragment_feature_record rdf:type ccp:IAO_EXT_0001587 .
}
filter (bound (?modification_feature_record) || bound (?fragment_feature_record)) .
?dna_record obo:BFO_0000051 ?react_xref_record .
?react_xref_record rdf:type ccp:IAO_EXT_0001588 . # xref field
?react_xref_record obo:BFO_0000051 ?react_id_field .
?react_id_field rdf:type ?reactome_ice .
filter (contains (str (?reactome_ice), \"http://ccp.ucdenver.edu/kabob/ice/REACTOME_\")) .
?dna_record obo:BFO_0000051 ?location_record .
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
?variant_id obo:IAO_0000219 <http://purl.obolibrary.org/obo/so#variant_of> . 
?variant_id obo:IAO_0000219 ?variant_of_bio . 
filter (contains(str(?variant_of_bio), \"kabob/bio\"))

}",
 :options {:magic-prefixes [["franzOption_logQuery" "franz:yes"] ["franzOption_clauseReorderer" "franz:identity"]]}}
