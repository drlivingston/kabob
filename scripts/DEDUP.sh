#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if [ $# -ne 0 ]
then
  echo "Usage: no args"
  echo $@
  exit 1
fi

# ##SOURCING DEFAULTS
source $SCRIPT_DIR/ENV.sh

echo MAVEN_BIN: $MAVEN_BIN
echo AG_BIN: $AG_BIN
#echo DEFAULT_AG: $DEFAULT_AG
echo DEFAULT_KB_PORT: $DEFAULT_KB_PORT
echo DEFAULT_KB: $DEFAULT_KB
echo DEFAULT_KABOB_DATA_ROOT: $DEFAULT_KABOB_DATA_ROOT

echo Calling Remove Duplicates
ssh agraph@localhost "$SCRIPT_DIR/remove-duplicates.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT"
