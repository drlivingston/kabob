#!/bin/bash
#

SCRIPT_DIR=/kabob.git/scripts/docker
chmod -R 755 ${SCRIPT_DIR}

# This script takes a single argument specifying the repository name to use/construct
export KB_INSTANCE_NAME=$1
export DOCKER_ENV=${SCRIPT_DIR}/allegrograph-specific/docker-env.sh

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

## delete duplicate triples
${SCRIPT_DIR}/allegrograph-specific/RULES.sh rules/utility/duplicates