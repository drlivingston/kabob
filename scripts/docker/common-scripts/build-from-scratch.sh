#!/bin/bash -e
#

# This script requires two arguments:
# 1) triple-store-specific script directory
# 2) kb name
# 3) is_virtuoso (true/false)  true is Stardog currently (not Virtuoso) TODO:create flag for AG, Virtuoso, Stardog

BASE_SCRIPT_DIR=$1
COMMON_SCRIPT_DIR=/kabob.git/scripts/docker/common-scripts
chmod -R 755 ${BASE_SCRIPT_DIR}
chmod -R 755 ${COMMON_SCRIPT_DIR}

# This script takes a single argument specifying the repository name to use/construct
export KB_INSTANCE_NAME=$2
export DOCKER_ENV=${BASE_SCRIPT_DIR}/docker-env.sh

IS_VIRTUOSO=$3

source ${DOCKER_ENV}
source ${COMMON_SCRIPT_DIR}/ENV.sh

echo "LEININGEN=${LEININGEN}"
echo "KB_PORT=${KB_PORT}"
echo "KB_URL=${KB_URL}"
echo "KB_USER=${KB_USER}"
echo "KB_PASS=${KB_PASS}"
echo "KB_NAME=${KB_NAME}"
echo "DATASOURCE_OWL_DIR=${DATASOURCE_OWL_DIR}"
echo "DATASOURCE_ICE_DIR=${DATASOURCE_ICE_DIR}"
echo "KB_DATA_DIR=${KB_DATA_DIR}"

###### Clean out the directory in which we're going to place our artifacts.
rm -rvf ${KB_DATA_DIR}
mkdir -p ${KB_DATA_DIR}
###
######## create a new database
${BASE_SCRIPT_DIR}/create-new-database.sh ${KB_NAME}
####
####### generate lists of RDF files that will be loaded in subsequent steps
${COMMON_SCRIPT_DIR}/generate-rdf-file-lists.sh ${KB_NAME} ${DOCKER_ENV}
####
##### Load the ontologies (note: they will have been converted from OWL to n-triples prior to loading)
${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/owl-files.${KB_NAME}.list \
  "ntriples"
##
#####
######## ============================================================================================ #
######## =======================   PRE IDENTIFIER MERGE RULES ARE RUN BELOW   ======================= #
######## ============================================================================================ #
#####
###### create ICE records for all ontology concepts
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_ontology_id_denotes_concept_non_obo_ns
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_ontology_id_denotes_concept_non_obo_ns
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept_obo_ns
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept_obo_ns
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen
##
######## create skos:exactMatch links between equivalent ontology identifiers
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label
##
##### Load the ICE RDF - the rules above process the ontologies only, so we have waited to load the ICE RDF until this point
${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/ice-nt-files.${KB_NAME}.list

${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/ice-owl-files.${KB_NAME}.list \
  "rdfxml"
##
####### create skos:exactMatch links between equivalent ICE identifiers
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/post_ice_rdf_load/step_c_ice_id_typing
##
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_da_identifier_exact_match
${BASE_SCRIPT_DIR}/RULES.sh rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_db_more_identifier_exact_match
${BASE_SCRIPT_DIR}/LOAD.sh rules/pre_identifier_merge/post_ice_rdf_load/step_d_ice_id_exact_match/step_db_more_identifier_exact_match
##
####
####
##### ============================================================================================ #
##### ============================   END PRE IDENTIFIER MERGE RULES   ============================ #
##### ============================================================================================ #
####
####
###### Create the ID sets (step e)
export LEIN_ROOT=true
cd /kabob.git && { ${LEININGEN} generate-id-sets ${KB_URL} ${KB_NAME} ${KB_USER} ${KB_PASS} ${KB_DATA_DIR}/id_sets/exact/ ${KB_DATA_DIR}/id_sets/graph_dbs/ ${IS_VIRTUOSO} ; cd - ; }
${BASE_SCRIPT_DIR}/LOAD.sh id_sets/exact
##
##
### ============================================================================================ #
### =======================   POST IDENTIFIER MERGE RULES ARE RUN BELOW   ====================== #
### ============================================================================================ #
##
##
#######  create bioentity for each id set
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links
#
#
###### connect bioentities based on ontology hierarchies
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio


####### ice to bio
##### typing
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing
#
#
#### labeling
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling


### linking
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/taxon
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/taxon
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/central_dogma
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/central_dogma
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/goa
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/goa
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/hp
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/hp
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/drugbank
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/drugbank
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/irefweb
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/irefweb
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/pharmgkb
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_h_ice_to_bio/step_hc_bioentity_linking/pharmgkb

##### bioentity abstractions
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ia_generate_missing_genes
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ia_generate_missing_genes
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ib_generate_gene_abstractions
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ib_generate_gene_abstractions
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ic_link_to_gp_abstractions
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_i_bioentity_abstractions/step_ic_link_to_gp_abstractions


##### bioworld expansion
${BASE_SCRIPT_DIR}/RULES.sh rules/post_identifier_merge/step_j_bio_expansion
${BASE_SCRIPT_DIR}/LOAD.sh rules/post_identifier_merge/step_j_bio_expansion


