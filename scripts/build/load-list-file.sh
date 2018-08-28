#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/INIT.sh

if [ $# -lt 5 ] || [ $# -gt 6 ]
then
  echo "Usage: AG_BIN AG_PORT AG_INDICES KB_NAME LIST_FILE (FORMAT)"
  echo $@
  exit 1
fi

AG_BIN=${1:?}
AG_PORT=${2:?}
AG_INDICES=${3:?}
KB_NAME=${4:?}
LIST_FILE=${5:?}
FORMAT=${6:-"ntriples"}

echo Loading list file: $LIST_FILE
echo List file count: $(wc -l $LIST_FILE)
cat $LIST_FILE

$AG_BIN/agload \
    --verbose \
    --bulk \
    --graph :source \
    --with-indices $AG_INDICES \
    --port $AG_PORT \
    --input $FORMAT \
    $KB_NAME \
    @$LIST_FILE
