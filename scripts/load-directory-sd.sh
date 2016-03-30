#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

NUM_ARGS=$#
if [ $NUM_ARGS -lt 3 ] || [ $NUM_ARGS -gt 4 ]
then
  echo "Usage: SD_BIN KB_NAME LOAD_DIR (FORMAT)"
  echo $@
  exit 1
fi

SD_BIN=$1
KB=$2
LOAD_DIR=$3
SPECIFIED_FORMAT=$4
FORMAT=${SPECIFIED_FORMAT:-"ntriples"}

echo SD_BIN: $SD_BIN
echo KB: $KB
echo SPECIFIED_FORMAT: $SPECIFIED_FORMAT
echo FORMAT: $FORMAT

echo Loading directory: $LOAD_DIR

$SD_BIN/stardog-admin data add $KB -f $FORMAT $(find $LOAD_DIR -name '.nt.gz’)
