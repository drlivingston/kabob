#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if [ $NUM_ARGS -lt 1 ] || [ $NUM_ARGS -gt 2 ]
then
  echo "Usage: DIR_PAT (FORMAT)"
  echo $@
  exit 1
fi

DIR_PAT=$1

SPECIFIED_FORMAT=$2
FORMAT=${SPECIFIED_FORMAT:-"*.nt.gz"}

### SOURCING DEFAULTS
source $SCRIPT_DIR/ENV.sh

LOAD_DIR=$KB_DATA_DIR/$DIR_PAT

echo KB_DATA_DIR: $KB_DATA_DIR
echo SPECIFIED_FORMAT: $SPECIFIED_FORMAT
echo FORMAT: $FORMAT
echo DIR_PAT: $DIR_PAT
echo LOAD_DIR: $LOAD_DIR

$SCRIPT_DIR/load-directory-virtuoso.sh $LOAD_DIR $FORMAT
