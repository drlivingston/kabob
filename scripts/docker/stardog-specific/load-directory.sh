#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

if [ $# -lt 4 ] || [ $# -gt 5 ]
then
  echo "Usage: KB_PORT KB_NAME KB_DATA_DIR RELATIVE_DIR (FORMAT)"
  echo $@
  exit 1
fi

KB_PORT=${1:?}
KB_NAME=${2:?}
KB_DATA_DIR=${3:?}
LOAD_DIR=${KB_DATA_DIR}/${4:?}
FORMAT=${5:-"ntriples"}

LIST_FILE=$(mktemp)
ls -1 ${LOAD_DIR}/* > $LIST_FILE

echo Loading directory: ${LOAD_DIR}
${SCRIPT_DIR}/stardog-specific/load-list-file.sh \
    ${KB_PORT} \
    ${KB_NAME} \
    ${LIST_FILE} \
    ${FORMAT}

#${SCRIPT_DIR}/stardog-specific/delete-duplicate-triples-stardog.sh