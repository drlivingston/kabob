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

date | tee -a $LOG_FILE
wget -c -t 0 --timeout 60 --waitretry 10 -P $TARGET_DIR $URL | tee -a $LOG_FILE
e=${PIPESTATUS[0]}
date | tee -a $LOG_FILE
exit $e


