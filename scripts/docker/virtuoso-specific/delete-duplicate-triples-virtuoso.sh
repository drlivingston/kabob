#!/bin/bash
#

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/virtuoso-specific/docker-env.sh
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

export LEIN_ROOT=true
SERVER_IMPL="virtuoso"
cd /kabob.git && { ${LEININGEN} eval-sparql ${KB_URL} ${KB_NAME} ${KB_USER} ${KB_PASS} ${SERVER_IMPL} directives/duplicates ; cd - ; }
