#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh
source $SCRIPT_DIR/INIT.sh

if [ $# -lt 5 ] || [ $# -gt 6 ]; then
    echo "Usage: AG_BIN AG_PORT AG_INDICES KB_NAME LIST_FILE (FORMAT)"
    echo $@
    exit 1
fi

AG_BIN=${1:?}
AG_PORT=${2:?}
AG_INDICES=${3,?}
KB_NAME=${4:?}
LIST_FILE=${5:?}
FORMAT=${6:-"ntriples"}

echo Generating new KB: $KB_NAME
echo Loading list file: $LIST_FILE
echo List file count: $(wc -l $LIST_FILE)
cat $LIST_FILE

$AG_BIN/agload \
    --verbose \
    --supersede \
    --bulk \
    --graph :source \
    --with-indices $AG_INDICES \
    --port $AG_PORT \
    --input $FORMAT \
    $KB_NAME \
    @$LIST_FILE
# --bulk: Faster (no transaction log processing), but potentially unsafe;
#         errors during the load process could result in a corrupt db.
# --supersede: Blow away an existing database with the same name as the one
#              being loaded.
# --graph: The default graph; `:source` indicates that the filename should be
#          used.

# TODO: Why is this being done as a separate step, and not as part of the
# initial load?  agload's `--duplicates` option would seem to suggest that this
# is possible.
$SCRIPT_DIR/remove-duplicates.sh $AG_BIN $AG_PORT $AG_INDICES $KB_NAME
