#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

if [ $# -ne 1 ]
then
  echo "Usage: RULE_PAT"
  echo $@
  exit 1
fi

RULE_PAT=$1

$SCRIPT_DIR/stardog-specific/run-rules.sh \
              ${LEININGEN} \
              ${KB_URL} \
              ${KB_NAME} \
              ${KB_USER} \
              ${KB_PASS} \
              ${KB_DATA_DIR} \
              ${RULE_PAT}
