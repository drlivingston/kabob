#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
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
time $AG_BIN/../lib/agquery --port $AG_PORT --optimize 2 $KB_NAME
