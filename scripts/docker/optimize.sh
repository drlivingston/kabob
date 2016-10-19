#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh
source $SCRIPT_DIR/INIT.sh

if ! [[ $# -eq 3 ]]
then
  echo "Usage: AG_BIN AG_PORT KB_NAME"
  echo $@
  exit 1
fi

AG_BIN=${1:?}
AG_PORT=${2:?}
KB_NAME=${3:?}

echo Optimizing KB: $KB_NAME
su agraph -c "$AG_BIN/../lib/agquery --port $AG_PORT --optimize 2 $KB_NAME"
