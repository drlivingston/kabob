#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

if [ $# -lt 1 ] || [ $# -gt 2 ]; then
    echo "Usage: DIR_PATTERN (FORMAT)"
    echo $@
    exit 1
fi

DIR_PAT=${1:?}
FORMAT=${2:-"ntriples"}

$SCRIPT_DIR/load-directory.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME \
  $KB_DATA_DIR \
  $DIR_PAT \
  $FORMAT

#$SCRIPT_DIR/remove-duplicates.sh \
#  $AG_BIN \
#  $AG_PORT \
#  $AG_INDICES \
#  $KB_NAME
