#!/bin/bash

# FILENAME must be an absolute path
# DOWNLOAD_URL is optional

FILENAME=$1
DOWNLOAD_URL="${2:-n/a}"
METADATA_FILENAME="${FILENAME}.ready"
FILE_SIZE=$(stat -c%s "${FILENAME}")
DATE=`date +%m/%d/%Y`
a=$(stat -c %y "${FILENAME}")
LAST_MODE_DATE=$(date --date="${a}" +%m/%d/%Y)

echo "DOWNLOAD_DATE=${DATE}" > "${METADATA_FILENAME}" ;
echo "FILE_SIZE_IN_BYTES=${FILE_SIZE}" >> "${METADATA_FILENAME}" ;
echo "DOWNLOAD_URL=${DOWNLOAD_URL}" >> "${METADATA_FILENAME}" ;
echo "DOWNLOADED_FILE=${FILENAME}" >> "${METADATA_FILENAME}" ;
echo "FILE_LAST_MOD_DATE=${LAST_MODE_DATE}" >> "${METADATA_FILENAME}" ;