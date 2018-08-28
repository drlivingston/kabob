#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/ENV.sh

if [ $# -ne 1 ]
then
  echo "Usage: RULE_PAT"
  echo $@
  exit 1
fi

RULE_PAT=$1

$SCRIPT_DIR/run-rules.sh \
              $LEININGEN \
              $KB_URL \
              $KB_NAME \
              $KB_USER $KB_PASS \
              $KB_DATA_DIR \
              $RULE_PAT
