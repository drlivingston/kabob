#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

if [ $# -ne 7 ]
then
  echo "Usage: LEININGEN KB_URL KB_NAME KB_USER KB_PASS KB_DATA_DIR RULETYPE"
  echo $@
  exit 1
fi

LEININGEN=${1:?}
KB_URL=${2:?}
KB=${3:?}
KB_USER=${4:?}
KB_PASS=${5:?}
KB_DATA_DIR=${6:?}
RULETYPE=${7:?}

OUTPUTDIR=${KB_DATA_DIR}/${RULETYPE}/
RULES_RES_DIR=resources/${RULETYPE}/

# this flag is being used to indicate a stardog usage in this branch
IS_VIRTUOSO=true

echo Running rules
mkdir -p ${OUTPUTDIR}
export LEIN_ROOT=true
cd /kabob.git && { $LEININGEN run-rules ${KB_URL} ${KB} ${KB_USER} ${KB_PASS} ${OUTPUTDIR} ${IS_VIRTUOSO} ${RULES_RES_DIR} ; cd - ; }

# Ensure that all subdirectoes implicit in the `RULETYPE` are accessible to
# later processes.
find ${KB_DATA_DIR} -type d -exec chmod 0755 {} \;
