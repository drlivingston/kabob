#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/blazegraph-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh
source ${SCRIPT_DIR}/common-scripts/INIT.sh

if [ $# -lt 3 ] || [ $# -gt 4 ]
then
  echo "Usage: KB_PORT KB_NAME LIST_FILE (FORMAT)"
  echo $@
  exit 1
fi

KB_PORT=${1:?}
KB_NAME=${2:?}
LIST_FILE=${3:?}
FORMAT=${4:-"ntriples"}

TMP_LIST_FILE="${LIST_FILE}.port_${KB_PORT}.repo_${KB_NAME}.format_${FORMAT}.load"

mv ${LIST_FILE} ${TMP_LIST_FILE}
LIST_FILE=${TMP_LIST_FILE}

echo Loading list file: ${LIST_FILE}
echo List file count: $(wc -l ${LIST_FILE})
cat ${LIST_FILE}
chmod 755 ${LIST_FILE}

# we will load each file in the file list using a separate bulk load process so that the triples
# can be put into a named graph that is the name of the file.

line_count=0
while read file_to_load; do

   # delete the file if it is already in the load-request directory
   LIST_FILE_NAME=${LIST_FILE##*/}.${line_count}.load
   LOAD_REQUEST_FILE=${LOAD_REQUEST_DIRECTORY}/${LIST_FILE_NAME}
   if [[ -f "${LOAD_REQUEST_FILE}" ]]
   then
      rm "${LOAD_REQUEST_FILE}"
   fi

   # moving the list file into the load-request directory will trigger a load
   echo "LOADING FILE: ${file_to_load}"
   echo "LOAD REQUEST FILE: ${LOAD_REQUEST_FILE}"
   echo ${file_to_load} > ${LOAD_REQUEST_FILE}

   # The script then waits for the load to complete. A successful load is
   # indicated by the creation of a file in the directory called
   # $LIST_FILE.success while a load failure is indicated by the creation
   # of a file called $LIST_FILE.error

   # the wait-for-load script requires as input the name of the list file (without path)

   source ${SCRIPT_DIR}/common-scripts/wait-for-load.sh ${LOAD_REQUEST_DIRECTORY} ${LIST_FILE_NAME}

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

   line_count=$((line_count + 1))
done <${LIST_FILE}
