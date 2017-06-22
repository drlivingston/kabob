#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker

# This script takes a two arguments specifying (1) the repository name
# to use/construct and (2) the path to the docker-env.sh file to use
export KB_INSTANCE_NAME=$1
export DOCKER_ENV=$2

source ${DOCKER_ENV}
source ${SCRIPT_DIR}/common-scripts/ENV.sh

# The location of the downloaded OWL files.
OWL_BASE=${DATASOURCE_OWL_DIR}
# The location of the downloaded and triplified ICE source files.
ICE_BASE=${DATASOURCE_ICE_DIR}

FILE_LIST_BASE=${KB_DATA_DIR}/file-lists

export ICE_NT_LIST="${FILE_LIST_BASE}/ice-nt-files.${KB_NAME}.list"
export ICE_OWL_LIST="${FILE_LIST_BASE}/ice-owl-files.${KB_NAME}.list"
export OWL_LIST="${FILE_LIST_BASE}/owl-files.${KB_NAME}.list"

# make the output directory for the list files if it does not exists already
if [[ ! -d ${FILE_LIST_BASE} ]]; then
    mkdir -p ${FILE_LIST_BASE}
fi

for LISTFILE in ${ICE_NT_LIST} ${ICE_OWL_LIST} ${OWL_LIST} ; do
    if [[ -f ${LISTFILE} ]]; then
        rm ${LISTFILE}
    fi
    # Touch each of the list files that we're going to try to load.  In
    # practice this is only useful when testing the build process with a
    # much smaller subset of the files. Ensuring that all the list file for each category
    # of file exists before the discovery (below) is done prevents errors
    # when we try to access them later.
    touch ${LISTFILE}
done

for file in $(find -L "${ICE_BASE}" -type f \( -iname "*.nt.gz" \)); do echo "${file}" >> "${ICE_NT_LIST}"; done
for file in $(find -L "${ICE_BASE}" -type f \( -iname "*.owl" \)); do echo "${file}" >> "${ICE_OWL_LIST}"; done
for file in $(find -L "${OWL_BASE}" -type f -name "*.owl.flattened.nt.noblank.nt"); do echo "${file}" >> "${OWL_LIST}"; done

echo "OWL files to load:"
cat ${OWL_LIST}
echo
echo "ICE .nt files to load:"
cat ${ICE_NT_LIST}
echo
echo "ICE .owl files to load:"
cat ${ICE_OWL_LIST}
echo
