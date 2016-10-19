#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

EXPECTED_ARGS=5

#if [ $# -ne $EXPECTED_ARGS ]
if [ $# -lt 5 ] || [ $# -gt 6 ]
then
  echo "Usage: AG_BIN AG_PORT KB_NAME KABOB_ROOT FILE (FORMAT)"
  echo $@
  exit 1
fi

AG_BIN=$1
AG_PORT=$2
KB=$3
KABOB_DATA_ROOT=$4
FILE=$KABOB_DATA_ROOT/$5

SPECIFIED_FORMAT=$6
FORMAT=${SPECIFIED_FORMAT:-"ntriples"}

##SOURCING DEFAULTS
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

echo AG_BIN: $AG_BIN
echo KB: $KB
echo AG_PORT: $AG_PORT
echo KABOB_DATA_ROOT: $KABOB_DATA_ROOT
echo SPECIFIED_FORMAT: $SPECIFIED_FORMAT
echo FORMAT: $FORMAT
echo AG_INDICES: $AG_INDICES

echo Loading file: $FILE

su agraph -c '$AG_BIN/agload -v --bulk --graph :source --with-indices "$AG_INDICES" -p $AG_PORT --duplicates delete --input $FORMAT $KB $FILE'

#  --with-indices "ospgi posgi spogi"  --encoding utf8
