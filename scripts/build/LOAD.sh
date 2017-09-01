#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/ENV.sh

if [ $# -lt 1 ] || [ $# -gt 2 ]; then
    echo "Usage: DIR_PATTERN (FORMAT)"
    echo $@
    exit 1
fi

DIR_PAT=${1:?}
FORMAT=${2:-"ntriples"}

AGRAPH_CMD=$(cat <<EOF
$SCRIPT_DIR/load-directory.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME \
$KB_DATA_DIR \
$DIR_PAT \
$FORMAT
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

AGRAPH_CMD=$(cat <<EOF
$SCRIPT_DIR/remove-duplicates.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"
