#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker

source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh
source $SCRIPT_DIR/INIT.sh

if [ $# -lt 3 ] || [ $# -gt 4 ]; then
    echo "Usage: AG_PORT KB_NAME LIST_FILE (FORMAT)"
    echo $@
    exit 1
fi

AG_PORT=${1:?}
KB_NAME=${2:?}
LIST_FILE=${3:?}
FORMAT=${4:-"ntriples"}

# add load parameters to the list file name; note supersede=true when creating a new kb
TMP_LIST_FILE="$LIST_FILE.port_$AG_PORT.repo_$KB_NAME.format_$FORMAT.supersede.load"

mv $LIST_FILE $TMP_LIST_FILE
LIST_FILE=$TMP_LIST_FILE

echo Generating new KB: $KB_NAME
echo Loading list file: $LIST_FILE
echo List file count: $(wc -l $LIST_FILE)
cat $LIST_FILE

# moving the list file into the load-request directory will trigger a load
# delete the file if it is already in the load-request directory
LIST_FILE_NAME=${LIST_FILE##*/}
if [[ -f "$LOAD_REQUEST_DIRECTORY/$LIST_FILE_NAME" ]]
then
   rm "$LOAD_REQUEST_DIRECTORY/$LIST_FILE_NAME"
fi

# trigger the load by copying the load list file into the load-request directory
cp $LIST_FILE $LOAD_REQUEST_DIRECTORY

# The script then waits for the load to complete. A successful load is
# indicated by the creation of a file in the directory called
# $LIST_FILE.success while a load failure is indicated by the creation
# of a file called $LIST_FILE.error

# the wait-for-load script requires as input the name of the list file (without path)
LIST_FILE_NAME=${LIST_FILE##*/}
source $SCRIPT_DIR/wait-for-load.sh $LOAD_REQUEST_DIRECTORY $LIST_FILE_NAME

# a non-zero exit code indicates a load failure and the load log should be checked
 if [[ $? != 0 ]]
 then
     echo "Load failed for file: ${LIST_FILE}"
     echo "================================================"
     echo "FILES THAT SHOULD HAVE BEEN LOADED: "
     cat "${LIST_FILE}"
     echo "================================================"
     echo "LOAD LOG OUTPUT: "
     cat "${LOAD_REQUEST_DIRECTORY}${LIST_FILE_NAME}.log"
     exit 1
 fi

