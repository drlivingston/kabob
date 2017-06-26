#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh
source ${SCRIPT_DIR}/common-scripts/INIT.sh

if [ $# -lt 1 ] || [ $# -gt 1 ]
then
  echo "Usage: KB_NAME"
  echo $@
  exit 1
fi

KB_NAME=${1:?}

REQUEST_FILE="/tmp/dbCreateRequest.repo_${KB_NAME}.create"
touch $REQUEST_FILE
echo "New Stardog DB Creation Request: ${REQUEST_FILE}"

# delete the file if it is already in the load-request directory
REQUEST_FILE_NAME=${REQUEST_FILE##*/}
if [[ -f "${LOAD_REQUEST_DIRECTORY}/${REQUEST_FILE_NAME}" ]]
then
   rm "${LOAD_REQUEST_DIRECTORY}/${REQUEST_FILE_NAME}"
fi

# moving the list file into the load-request directory will trigger a load
cp ${REQUEST_FILE} ${LOAD_REQUEST_DIRECTORY}

# The script then waits for the load to complete. A successful load is
# indicated by the creation of a file in the directory called
# $REQUEST_FILE.success while a load failure is indicated by the creation
# of a file called $REQUEST_FILE.error

# the wait-for-load script requires as input the name of the list file (without path)

source ${SCRIPT_DIR}/common-scripts/wait-for-load.sh ${LOAD_REQUEST_DIRECTORY} ${REQUEST_FILE_NAME}

# a non-zero exit code indicates a load failure and the load log should be checked
 if [[ $? != 0 ]]
 then
     echo "Stardog Database creation failed: ${REQUEST_FILE}"
     echo "================================================"
     echo "================================================"
     echo "CREATION LOG OUTPUT: "
     cat "${LOAD_REQUEST_DIRECTORY}${REQUEST_FILE_NAME}.log"
     exit 1
 fi
