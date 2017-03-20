#!/bin/bash

#NOTE: due to CD'ing in script use absolute file names!!

EXPECTED_ARGS=3

if [ $# -ne $EXPECTED_ARGS ]
then
    echo "#NOTE: due to CD'ing in script use absolute file names!!"
    echo "Usage: LOG_FILE TARGET_DOWNLOAD_DIR URL"
    echo "current usage:"
    echo $@
    exit 1
fi

LOG_FILE=$1
TARGET_DIR=$2
URL=$3

CUR_DIR=`pwd`

#verify target dir and switch there
mkdir -p $TARGET_DIR
cd $TARGET_DIR

#append forwardslash to target directory if it doesn't end in a slash already
case "$TARGET_DIR" in
*/)
;;
*)
TARGET_DIR=$(echo "$TARGET_DIR/")
;;
esac

#verify the log file
touch $LOG_FILE

echo "downloading $URL" 
date | tee -a $LOG_FILE
wget -c -t 0 --timeout 60 --waitretry 10 -P $TARGET_DIR $URL | tee -a $LOG_FILE
# filename_effective does not get filled on all systems, and parsing the file name from the URL is problematic due to the occasional redirection, e.g. so.owl --> so-xp.obo.owl, so we use ls to get the most recent file downloaded in order to get the downloaded file name
#ONT_FILE_NAME=$(ls -lhrt $TARGET_DIR | tail -n 1 | tr -s " " | rev | cut -f 1 -d " " | rev)
#ONT_FILE=$TARGET_DIR$ONT_FILE_NAME
#name=$(echo $ONT_FILE_NAME | cut -f 1 -d ".")
#ext="flattened.owl"
#ONT_FILE_NAME_FLAT=$(echo ${name}_${ext})
#OUTPUT_FILE=$TARGET_DIR$ONT_FILE_NAME_FLAT
#echo -e "\nONT FILE: $ONT_FILE" | tee -a $LOG_FILE
#echo "OUTPUT FILE: $OUTPUT_FILE" | tee -a $LOG_FILE
#cd $CUR_DIR
#./scripts/download/flatten-ontology.sh -i $ONT_FILE -o $OUTPUT_FILE | tee -a $LOG_FILE
#e=$?
#date | tee -a $LOG_FILE
#exit $e


