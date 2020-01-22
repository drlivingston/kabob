#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/INIT.sh

if [ $# -ne 1 ]; then
  echo "Usage: RULE_PATTERN"
  echo $@
  exit 1
fi

RULE_PAT=$1
echo RULE_PAT: $RULE_PAT

echo Running Rules
$SCRIPT_DIR/RULES.sh $RULE_PAT

echo Loading Rule Output
$SCRIPT_DIR/LOAD.sh $RULE_PAT
