#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

if [ $# -ne 2 ]
then
  echo "Usage: RULE_PAT OUTPUT_DIR"
  echo $@
  exit 1
fi

### SOURCING DEFAULTS
source $SCRIPT_DIR/ENV-REMOTE.sh

RULE_PAT=$1
OUTPUT_DIR=$2

echo MAVEN: $MAVEN
echo KB_URL: $KB_URL
echo KB_NAME: $KB_NAME
echo KB_USER: $KB_USER
echo KB_PASS: $KB_PASS

echo KB_DATA_DIR: $KB_DATA_DIR

echo RULE_PAT: $RULE_PAT
echo OUTPUT_DIR: $OUTPUT_DIR

$SCRIPT_DIR/run-rules.sh $MAVEN $KB_URL $KB_NAME $KB_USER $KB_PASS $OUTPUT_DIR $RULE_PAT
