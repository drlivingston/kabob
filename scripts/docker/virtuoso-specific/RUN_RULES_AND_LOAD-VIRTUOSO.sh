#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/virtuoso-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

if [ $# -ne 1 ]; then
  echo "Usage: RULE_PATTERN"
  echo $@
  exit 1
fi

RULE_PAT=$1
echo RULE_PAT: ${RULE_PAT}

echo Running Rules
${SCRIPT_DIR}/virtuoso-specific/RULES-VIRTUOSO.sh ${RULE_PAT}

echo Loading Rule Output
${SCRIPT_DIR}/virtuoso-specific/LOAD-VIRTUOSO.sh ${RULE_PAT}
