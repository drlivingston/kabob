#!/bin/bash -e
#

# This script requires two arguments:
# 1) triple-store-specific script directory
# 2) kb name
# 3) server implementation, e.g. stardog, blazegraph, default (http), etc.

BASE_SCRIPT_DIR=$1
COMMON_SCRIPT_DIR=/kabob.git/scripts/docker/common-scripts
chmod -R 755 ${BASE_SCRIPT_DIR}
chmod -R 755 ${COMMON_SCRIPT_DIR}

# This script takes a single argument specifying the repository name to use/construct
export KB_INSTANCE_NAME=$2
export DOCKER_ENV=${BASE_SCRIPT_DIR}/docker-env.sh

SERVER_IMPL=$3

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
echo "BACKEND IMPLEMENTATION=${SERVER_IMPL}"

############ Clean out the directory in which we're going to place our artifacts.
######rm -rvf ${KB_DATA_DIR}
######mkdir -p ${KB_DATA_DIR}
#########
########## create a new database
${BASE_SCRIPT_DIR}/create-new-database.sh ${KB_NAME}
#######
########## generate lists of RDF files that will be loaded in subsequent steps
${COMMON_SCRIPT_DIR}/generate-rdf-file-lists.sh ${KB_NAME} ${DOCKER_ENV}
#######
######### Load the ontologies (note: they will have been converted from OWL to n-triples prior to loading)
${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/owl-files.${KB_NAME}.list \
  "ntriples"

######### Check that there are no classes with redundant restrictions after the ontology load
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
#
##########
############
############### ============================================================================================ #
############### =======================   PRE IDENTIFIER MERGE RULES ARE RUN BELOW   ======================= #
############### ============================================================================================ #
############
############# create ICE records for all ontology concepts
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_id_denotes_concept_non_obo_ns
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_aa_id_denotes_concept_non_obo_ns
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept_obo_ns

## check for http:/ bug before proceeding:
## grep -Rle 'http:/[^/]' rules/
## grep -Rle 'https:/[^/]' rules/

${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ab_ontology_id_denotes_concept_obo_ns
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing/step_aca_ontology_identifier_typing_by_obo_ns
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing/step_aca_ontology_identifier_typing_by_obo_ns
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing/step_acb_ontology_identifier_typing_by_obo_file
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ac_ontology_identifier_typing/step_acb_ontology_identifier_typing_by_obo_file
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_a_ontology_to_ice/step_ad_ontology_ice_record_gen
#######
############# create skos:exactMatch links between equivalent ontology identifiers
########## DEPRECATED ${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/identifier_normalization
########## DEPRECATED ${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/identifier_normalization
########## DEPRECATED ${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref
########## DEPRECATED${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/ontology_xref
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_0_pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label
########
######### Load the ICE RDF - the rules above process the ontologies only, so we have waited to load the ICE RDF until this point
${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/ice-nt-files.${KB_NAME}.list

${BASE_SCRIPT_DIR}/load-list-file.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR}/file-lists/ice-owl-files.${KB_NAME}.list \
  "rdfxml"
#
######## OPTIMIZE STORE
#${BASE_SCRIPT_DIR}/OPTIMIZE.sh
#sleep 300
##
##
#
#######
#######
############ generate other ICE RDF
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_ca_reactome_biopax2ice
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_ca_reactome_biopax2ice
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cb_reactome_biopax2ice
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cc_reactome_biopax2ice
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_c_other_ice_gen/step_cc_reactome_biopax2ice
####
########### process ICE identifiers
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_da_identifier_typing
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_db_identifier_exact_match
#
### todo: restart required here? seems to stall otherwise; maybe try optimize?
#
#
${BASE_SCRIPT_DIR}/RULES.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_dc_more_identifier_exact_match
${BASE_SCRIPT_DIR}/LOAD.sh rules/_0_pre_identifier_merge/_1_post_ice_rdf_load/step_d_ice_id_processing/step_dc_more_identifier_exact_match

#######
#########
#########
########## ============================================================================================ #
########## ============================   END PRE IDENTIFIER MERGE RULES   ============================ #
########## ============================================================================================ #
#########
#########
########### Create the ID sets (step e)
#export LEIN_ROOT=true
cd /kabob.git && { ${LEININGEN} generate-id-sets ${KB_URL} ${KB_NAME} ${KB_USER} ${KB_PASS} ${KB_DATA_DIR}/id_sets/exact/ ${KB_DATA_DIR}/id_sets/graph_dbs/ ${SERVER_IMPL} ; cd - ; }
${BASE_SCRIPT_DIR}/LOAD.sh id_sets/exact

###
########## OPTIMIZE STORE
###${BASE_SCRIPT_DIR}/OPTIMIZE.sh
###sleep 180
###
######
#######
######## ============================================================================================ #
######## =======================   POST IDENTIFIER MERGE RULES ARE RUN BELOW   ====================== #
######## ============================================================================================ #
#######
#######
##########  create bioentity for each id set
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fa_identifier_bioentity_links
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_f_bioentity_generation/step_fb_obsolete_identifier_bioentity_links
#####


###### Check for identifiers that denote multiple kabob bioentities
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/ice_bio_distinction
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/ice_bio_distinction

###
########### connect bioentities based on ontology hierarchies
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_ga_copy_owl_constructs_to_bio
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/step_gba_copy_rdfs_labels_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/step_gba_copy_rdfs_labels_to_bio
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/step_gbb_derive_missing_labels_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gb_copy_labels_to_bio/step_gbb_derive_missing_labels_to_bio

######### OPTIMIZE STORE
##${BASE_SCRIPT_DIR}/OPTIMIZE.sh
##sleep 300
#
#
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gca_links_to_nil
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gca_links_to_nil

${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcb_temp_link_ont_to_bio_concepts
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcb_temp_link_ont_to_bio_concepts

${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcc_transfer_ontology_links_to_bio
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcc_transfer_ontology_links_to_bio
#

# todo: need to have stardog use the admin user to allow kb updates/deletes that are called for in step gcd -- for now do them manually (see queries below)

#####${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcd_link_removal
##### is there anyting to load??? ${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_g_ontology_to_bio/step_gc_copy_node_links_to_bio/step_gcd_link_removal

#delete { graph ?g {?c ?p ?c}} where {
#  select ?c (owl:equivalentClass as ?p) ?g {
#    graph ?g {
#      ?c owl:equivalentClass ?c .
#    }
#  }
#}

#delete { graph ?g {?s ?p ?o}} where {
#                    select ?s (ccp:temporary_link as ?p) ?o ?g {
#                      graph ?g {
#                        ?s ccp:temporary_link ?o .
#                      }
#                    }
#                  }

###### Validate RDF syntax
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list

########### ice to bio
######### typing
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_haa
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_haa
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_hab
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_hab
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_hac
### anything to load?? ${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_gene_type/step_hac

# todo: need to have stardog use the admin user to allow kb updates/deletes that are called for in step hac -- for now do them manually (see queries below)

${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_identifier
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_parent_class
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_ha_bioentity_typing/by_parent_class

####
####
####### labeling
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hb_bioentity_labeling
####
###
###### ggp abstractions
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hca_central_dogma
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hca_central_dogma

${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/ncbi
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/ncbi
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/refseq
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/refseq
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/uniprot
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/uniprot
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/via_has_gene_template
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcb_assign_taxon/via_has_gene_template

${BASE_SCRIPT_DIR}/RULES.sh rules/validation/bioworld_validation
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/bioworld_validation

${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_generate_missing_ggp_entities/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_generate_missing_ggp_entities/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_generate_missing_ggp_entities/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcc_generate_missing_ggp_entities/step_b



## todo: remove temporary links here


${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcd_generate_gene_abstractions
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hcd_generate_gene_abstractions
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hce_link_to_gp_abstractions
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hc_ggp_abstractions/step_hce_link_to_gp_abstractions
####
######### linking
# =================================== #
# =================================== #
#          CLASS-BASED KR             #
# =================================== #
# =================================== #
### UNDER CONSTRUCTION ${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/biogrid/step_a_record_mentions_entity
### UNDER CONSTRUCTION ${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/biogrid/step_a_record_mentions_entity
##
# ====== BIOPLEX CLASS-BASED ======
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/bioplex/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/bioplex/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/bioplex/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/bioplex/step_b
###
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
#####

# ===== DRUGBANK CLASS-BASED =====
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_b/_0_drug_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_b/_0_drug_label
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_b/_1_target_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/drugbank/step_b/_1_target_label

# ----- VALIDATION -----
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list

# ===== GOA CLASS-BASED =====
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_b/_0_go_concept_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_b/_0_go_concept_label
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_b/_1_bioentity_label
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/goa/step_b/_1_bioentity_label


# ----- VALIDATION -----
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list

# ===== HP CLASS-BASED =====
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/hp/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/hp/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/hp/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/hp/step_b

# ----- VALIDATION -----
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list

# ===== IREFWEB CLASS-BASED =====
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/irefweb/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/irefweb/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/irefweb/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/irefweb/step_b

# ----- VALIDATION -----
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list


# ===== PHARMGKB CLASS-BASED =====-
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/pharmgkb/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/pharmgkb/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/pharmgkb/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/class_based_kr/pharmgkb/step_b

# ----- VALIDATION -----
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list


######### bioworld expansion
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_i_bio_expansion/class_based_kr/step_a
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_i_bio_expansion/class_based_kr/step_a
${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_i_bio_expansion/class_based_kr/step_b
${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_i_bio_expansion/class_based_kr/step_b

# ----- VALIDATION -----
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list


# todo - remove temporary links from step hd and step i

#rdfs/subClassOf ccp/temp_location
#rdfs/subClassOf ccp/temp_pharmgkb_interaction
#rdfs/subClassOf ccp/temp_irefweb_nary_interaction
#rdfs/subClassOf ccp/temp_irefweb_binary_interaction
#rdfs/subClassOf ccp/temp_human_phenotype
#rdfs/subClassOf ccp/temp_molecular_function
#rdfs/subClassOf ccp/temp_localization_process
#rdfs/subClassOf ccp/temp_biological_process
#rdfs/subClassOf ccp/temp_drugbank_interaction
#rdfs/subClassOf ccp/temp_bioplex_interaction
#rdfs/subClassOf kice/temp_ncrna_entity
#rdfs/subClassOf kice/temp_rrna_entity
#rdfs/subClassOf kice/temp_scrna_entity
#rdfs/subClassOf kice/temp_snorna_entity
#rdfs/subClassOf kice/temp_snrna_entity
#rdfs/subClassOf kice/temp_trna_entity
#ccp/temp_name ?/mf_name

#delete { graph ?g {?s rdfs:subClassOf ?o}} where {
#  select ?s (ccp:temp_location as ?o) ?g {
#    graph ?g {
#      ?s rdfs:subClassOf ccp:temp_location .
#    }
#  }
#}

#delete { graph ?g {?s ccp:temp_name ?o}} where {
#  select ?s ?o ?g {
#    graph ?g {
#      ?s ccp:temp_name ?o .
#    }
#  }
#}


######### validation rules (only rule metadata triples added)
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_z_validation/subclass_hierarchy_checks

# =================================== #
# =================================== #
#          INSTANCE-BASED KR          #
# =================================== #
# =================================== #

###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/bioplex
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/bioplex
###
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
#####
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/goa
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/goa
#####
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
##
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/hp
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/hp
#####
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
##
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/drugbank
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/drugbank
####
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
##
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/irefweb
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/irefweb
####
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
##
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/pharmgkb
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_h_ice_to_bio/step_hd_bioentity_linking/instance_based_kr/pharmgkb
####

# todo - remove temporary links from step hd


##
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
##${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
##${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
###
####
######### bioworld expansion
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_i_bio_expansion/instance_based_kr
###${BASE_SCRIPT_DIR}/LOAD.sh rules/_1_post_identifier_merge/step_i_bio_expansion/instance_based_kr
###
##
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/restriction
#${BASE_SCRIPT_DIR}/RULES.sh rules/validation/valid_owl/list
#${BASE_SCRIPT_DIR}/LOAD.sh rules/validation/valid_owl/list
##
##
######### validation rules (only rule metadata triples added)
###${BASE_SCRIPT_DIR}/RULES.sh rules/_1_post_identifier_merge/step_z_validation/subclass_hierarchy_checks
