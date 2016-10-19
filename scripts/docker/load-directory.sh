#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

if [ $# -lt 6 ] || [ $# -gt 7 ]
then
  echo "Usage: AG_BIN AG_PORT AG_INDICES KB_NAME KB_DATA_DIR RELATIVE_DIR (FORMAT)"
  echo $@
  exit 1
fi

AG_BIN=${1:?}
AG_PORT=${2:?}
AG_INDICES=${3:?}
KB_NAME=${4:?}
KB_DATA_DIR=${5:?}
LOAD_DIR=$KB_DATA_DIR/${6:?}
FORMAT=${7:-"ntriples"}

LIST_FILE=$(mktemp)
ls -1 $LOAD_DIR/* > $LIST_FILE

echo Loading directory: $LOAD_DIR
$SCRIPT_DIR/load-list-file.sh \
    $AG_BIN \
    $AG_PORT \
    $AG_INDICES \
    $KB_NAME \
    $LIST_FILE \
    $FORMAT
