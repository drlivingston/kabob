#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

if [ $# -lt 4 ] || [ $# -gt 5 ]
then
  echo "Usage: AG_PORT KB_NAME KB_DATA_DIR RELATIVE_DIR (FORMAT)"
  echo $@
  exit 1
fi

AG_PORT=${1:?}
KB_NAME=${2:?}
KB_DATA_DIR=${3:?}
LOAD_DIR=$KB_DATA_DIR/${4:?}
FORMAT=${5:-"ntriples"}

LIST_FILE=$(mktemp)
ls -1 $LOAD_DIR/* > $LIST_FILE

echo Loading directory: $LOAD_DIR
$SCRIPT_DIR/load-list-file.sh \
    $AG_PORT \
    $KB_NAME \
    $LIST_FILE \
    $FORMAT
