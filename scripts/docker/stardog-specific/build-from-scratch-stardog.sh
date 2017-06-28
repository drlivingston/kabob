#!/bin/bash
#

SCRIPT_DIR=/kabob.git/scripts/docker
chmod -R 755 ${SCRIPT_DIR}

# This script takes a single argument specifying the repository name to use/construct
export KB_INSTANCE_NAME=$1
export DOCKER_ENV=${SCRIPT_DIR}/stardog-specific/docker-env.sh

source ${DOCKER_ENV}
source ${SCRIPT_DIR}/common-scripts/ENV.sh

echo "LEININGEN=${LEININGEN}"
echo "KB_PORT=${KB_PORT}"
echo "KB_URL=${KB_URL}"
echo "KB_USER=${KB_USER}"
echo "KB_PASS=${KB_PASS}"
echo "KB_NAME=${KB_NAME}"
echo "DATASOURCE_OWL_DIR=${DATASOURCE_OWL_DIR}"
echo "DATASOURCE_ICE_DIR=${DATASOURCE_ICE_DIR}"
echo "KB_DATA_DIR=${KB_DATA_DIR}"

#### Clean out the directory in which we're going to place our artifacts.
#rm -rvf ${KB_DATA_DIR}
#mkdir -p ${KB_DATA_DIR}
#
#### create a new Stardog database
#${SCRIPT_DIR}/stardog-specific/create-new-database-stardog.sh ${KB_NAME}
#
#### generate lists of RDF files that will be loaded in subsequent steps
#${SCRIPT_DIR}/common-scripts/generate-rdf-file-lists.sh ${KB_NAME} ${DOCKER_ENV}
#
#### Load the ontologies (note: they will have been converted from OWL to n-triples prior to loading)
#${SCRIPT_DIR}/stardog-specific/load-list-file-stardog.sh \
#  ${KB_PORT} \
#  ${KB_NAME} \
#  ${KB_DATA_DIR}/file-lists/owl-files.${KB_NAME}.list \
#  "ntriples"
#
#### create ICE records for all ontology concepts
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_a_ontology_root_identifier_gen
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_b_ontology_id_denotes_concept
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_a_ontology_to_ice/step_c_ontology_ice_record_gen
#
#### create skos:exactMatch links between equivalent ontology identifiers
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/chebi
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/equivalent_class
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/pre_ice_rdf_load/step_b_ontology_id_exact_match/shared_label
#
#### Load the ICE RDF - the rules above process the ontologies only, so we have waited to load the ICE RDF until this point
#${SCRIPT_DIR}/stardog-specific/load-list-file-stardog.sh \
#  ${KB_PORT} \
#  ${KB_NAME} \
#  ${KB_DATA_DIR}/file-lists/ice-nt-files.${KB_NAME}.list
#
#${SCRIPT_DIR}/stardog-specific/load-list-file-stardog.sh \
#  ${KB_PORT} \
#  ${KB_NAME} \
#  ${KB_DATA_DIR}/file-lists/ice-owl-files.${KB_NAME}.list \
#  "rdfxml"

### create skos:exactMatch links between equivalent ICE identifiers
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/pre_identifier_merge/post_ice_rdf_load/step_a_ice_id_typing
${SCRIPT_DIR}/stardog-specific/RULES-STARDOG.sh rules/pre_identifier_merge/post_ice_rdf_load/step_b_ice_id_exact_match/datasource_xref


#### Create the ID sets
#export LEIN_ROOT=true
#IS_VIRTUOSO=false
#cd /kabob.git && { ${LEININGEN} generate-id-sets ${KB_URL} ${KB_NAME} ${KB_USER} ${KB_PASS} ${KB_DATA_DIR}/id_sets/exact/ ${KB_DATA_DIR}/id_sets/graph_dbs/ ${IS_VIRTUOSO} ; cd - ; }
#${SCRIPT_DIR}/stardog-specific/LOAD-STARDOG.sh id_sets/exact
#
##  create bioentity for each id set
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/post_identifier_merge/step_a_entity_generation/reify
#
## connect bioentities based on ontology hierarchies
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/post_identifier_merge/step_b_ontology_to_bio/step_a_ontology_root_identifier_gen
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/post_identifier_merge/step_b_ontology_to_bio/step_b_ontology_id_denotes_concept
#${SCRIPT_DIR}/stardog-specific/RUN_RULES_AND_LOAD-STARDOG.sh rules/post_identifier_merge/step_b_ontology_to_bio/step_c_ontology_ice_record_gen
#





# ============================================================================================ #



###########
# ******** The rules below needed to be modified to use the CCP extension ontology **********
###########

#### propagate type info from ids to the bioentities
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/entity/type
#### does GorGP abstractions
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/entity/abstraction
#
#### Index optimization -- not currently implemented
##${SCRIPT_DIR}/optimize.sh \
##  ${KB_PORT} \
##  ${KB_NAME}
#
#
#  Elizabeth's reactome rules go here somewhere
#
#
#### this should be in ice_to_bio now... something wrong with this rule (file
#### parser generating different ICE id's than ice-to-bio does)
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/taxon
#
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/ice_to_bio
#
#### These rules are probably useful for the ice_to_bio rules but they need
#### ice_to_bio that connects proteins to genes.  First abstraction rules really
#### only handle genes. This rule adds gene product subclasses.
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/abstraction
#
#### These rules should eventually be migrated from temp to ice-to-bio
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/drugbank
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/pharmgkb
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/irefweb
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/goa
#
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/bio_to_bio
#
#### Apply labels to the BIO entities that have been created by the preceding
#### rules.
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/bio_labels
#
#### the hp rule looks for labels, so it must be run after the bio_labels rule
#${SCRIPT_DIR}/RUN_RULES_AND_LOAD.sh rules/temp/hp
#
#### Index optimization -- not currently implemented
##${SCRIPT_DIR}/optimize.sh \
##  ${KB_PORT} \
##  ${KB_NAME}

