#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

if [ $# -ne 0 ]
then
  echo "Usage: no args"
  echo $@
  exit 1
fi

echo Calling Remove Duplicates
$SCRIPT_DIR/remove-duplicates.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME

