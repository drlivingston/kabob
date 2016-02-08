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

#verify the log file
touch $LOG_FILE

echo "downloading $URL" 
date | tee -a $LOG_FILE
curl --remote-name --write-out "file: %{filename_effective} final-url: %{url_effective} size: %{size_download} time: %{time_total} final-time: " -L $URL | tee -a $LOG_FILE
date | tee -a $LOG_FILE

cd $CUR_DIR
