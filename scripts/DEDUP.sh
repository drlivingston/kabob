#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/ENV.sh

if [ $# -ne 0 ]
then
  echo "Usage: no args"
  echo $@
  exit 1
fi

echo Calling Remove Duplicates
AGRAPH_CMD=$(cat <<EOF
$SCRIPT_DIR/remove-duplicates.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"
