#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

if [ $# -lt 1 ] || [ $# -gt 2 ]; then
    echo "Usage: DIR_PATTERN (FORMAT)"
    echo $@
    exit 1
fi

DIR_PAT=${1:?}
FORMAT=${2:-"ntriples"}

${SCRIPT_DIR}/stardog-specific/load-directory.sh \
  ${KB_PORT} \
  ${KB_NAME} \
  ${KB_DATA_DIR} \
  ${DIR_PAT} \
  ${FORMAT}
