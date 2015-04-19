#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

EXPECTED_ARGS=4

# ${KABOB_DATA_ROOT:?"Need to set KABOB_DATA_ROOT non-empty"}

if [ $# -ne $EXPECTED_ARGS ]
then
  echo "Usage: AG_BIN KB_PORT KB_NAME KABOB_ROOT"
  echo $@
  exit 1
fi

AG_BIN=$1
PORT=$2
KB=$3
KABOB_DATA_ROOT=$4

mkdir -p $KABOB_DATA_ROOT/temp
touch    $KABOB_DATA_ROOT/temp/empty.nt

source $SCRIPT_DIR/ENV.sh

echo AG_BIN: $AG_BIN
echo KB: $KB
echo PORT: $PORT
echo KABOB_DATA_ROOT: $KABOB_DATA_ROOT
echo DEFAULT_KB_INDICES: $DEFAULT_KB_INDICES

echo Removing duplicates

$AG_BIN/agload -v --bulk  --with-indices "$DEFAULT_KB_INDICES" -p $PORT --input ntriples --encoding utf8 -d delete $KB $KABOB_DATA_ROOT/temp/empty.nt
