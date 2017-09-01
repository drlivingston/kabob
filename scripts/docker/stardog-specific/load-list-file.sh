#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
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

# Stardog doesn't seem to be able to handle loading gzipped files
# Here we check the input $LIST_FILE for .gz files and create
# uncompressed versions, and adjust the $LIST_FILE content accordingly.
UPDATED_LIST_FILE=${LIST_FILE}.nogz

if [[ -e ${UPDATED_LIST_FILE} ]]; then
    rm ${UPDATED_LIST_FILE}
fi
touch ${UPDATED_LIST_FILE}
echo "ORIGINAL LOAD FILE: ${LIST_FILE}"
echo "NON-GZ LOAD FILE: ${UPDATED_LIST_FILE}"
while read f; do
   echo "checking for .gz: ${f}"
   if [[ ${f} == *.gz ]]
   then
      out_file=${f%.gz}
      echo "decompressing to; ${out_file}"
      gunzip -v -c ${f} > ${out_file}
      echo ${out_file} >> ${UPDATED_LIST_FILE}
   else
      echo "File is not a .gz file. Passing through untouched: ${f}"
      echo ${f} >> ${UPDATED_LIST_FILE}
   fi
done < ${LIST_FILE}


echo "CONTENTS OF UPDATED_LIST_FILE:"
cat ${UPDATED_LIST_FILE}

TMP_LIST_FILE="${UPDATED_LIST_FILE}.port_${KB_PORT}.repo_${KB_NAME}.format_${FORMAT}.load"

mv ${UPDATED_LIST_FILE} ${TMP_LIST_FILE}
UPDATED_LIST_FILE=${TMP_LIST_FILE}

echo "Loading list file (each file to be loaded as an individual bulk load into Stardog): ${UPDATED_LIST_FILE}"
echo "List file count: $(wc -l ${UPDATED_LIST_FILE})"
cat ${UPDATED_LIST_FILE}
chmod 755 ${UPDATED_LIST_FILE}

# long loads appear to cause spurious RDF parse exceptions with Stardog. In an attempt to avoid errors and
# decrease overall load sizes, each file in the updated_list_file will be treated as an individual load.

line_count=0
while read file_to_load; do

   # delete the file if it is already in the load-request directory
   LIST_FILE_NAME=${UPDATED_LIST_FILE##*/}.${line_count}.load
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
       echo "Load failed for file: ${file_to_load}"
       echo "================================================"
       echo "FILES THAT SHOULD HAVE BEEN LOADED: "
       echo "${file_to_load}"
       echo "================================================"
       echo "LOAD LOG OUTPUT: "
       cat "${LOAD_REQUEST_FILE}.log"
       exit 1
   fi

   line_count=$((line_count + 1))
done <${UPDATED_LIST_FILE}

 # TODO: If files were decommpressed so that they could be loaded by Stardog, it would be nice
 #       to clean up after the script and delete any decompressed versions of the files.
