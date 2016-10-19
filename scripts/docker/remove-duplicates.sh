#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh
source $SCRIPT_DIR/INIT.sh

if [[ $# -ne 4 ]]; then
  echo "Usage: AG_BIN AG_PORT AG_INDICES KB_NAME"
  echo $@
  exit 1
fi

AG_BIN=${1:?}
AG_PORT=${2:?}
AG_INDICES=${3,?}
KB_NAME=${4,?}

# Create an empty file to be passed to `agload` as the source.  The command
# requires a source, but at this we don't want actually want to load any data.
# All we want is to trigger AG's `delete-duplicate-triples` function, which is
# triggered only at the end of a load cycle.
NULL_LOAD_FILE=$(mktemp)

echo Removing duplicates
su agraph -c "$AG_BIN/agload \
    --verbose \
    --bulk \
    --with-indices $AG_INDICES \
    --port $AG_PORT \
    --input ntriples \
    --encoding utf8 \
    --duplicates delete \
    $KB_NAME \
    $NULL_LOAD_FILE"
