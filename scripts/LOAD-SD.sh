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
FORMAT=${SPECIFIED_FORMAT:-"ntriples"}

# ##SOURCING DEFAULTS
source $SCRIPT_DIR/ENV.sh

LOAD_DIR=$DEFAULT_KABOB_DATA_ROOT/$DIR_PAT

echo MAVEN_BIN: $MAVEN_BIN
echo SD_BIN: $SD_BIN
echo DEFAULT_KB_PORT: $DEFAULT_KB_PORT
echo DEFAULT_KB: $DEFAULT_KB
echo DEFAULT_KABOB_DATA_ROOT: $DEFAULT_KABOB_DATA_ROOT
echo SPECIFIED_FORMAT: $SPECIFIED_FORMAT
echo FORMAT: $FORMAT
echo DIR_PAT: $DIR_PAT

/usr/bin/time -v ssh agraph@localhost "$SCRIPT_DIR/load-directory-sd.sh $SD_BIN $DEFAULT_KB $LOAD_DIR $FORMAT"
