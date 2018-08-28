#!/bin/bash

### NOTE, THIS SCRIPT DOES NOT CURRENTLY RESULT IN INDEX OPTIMIZATION. See explanation below. Revise prior to using.

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh
source ${SCRIPT_DIR}/common-scripts/INIT.sh

if ! [[ $# -eq 2 ]]
then
  echo "Usage: KB_PORT KB_NAME"
  echo $@
  exit 1
fi

KB_PORT=${1:?}
KB_NAME=${2:?}

echo Optimizing KB: $KB_NAME
touch "$LOAD_REQUEST_DIRECTORY/port_$KB_PORT.repo_$KB_NAME.optimize"
