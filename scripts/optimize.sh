#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

#either give all or give none
if [ $# -gt 3 ] || [ $# -eq 1 ] || [ $# -eq 2 ]
then
  echo "Usage: AG_BIN KB_PORT KB_NAME"
  echo $@
  exit 1
fi

CL_AG_BIN=$1
CL_PORT=$2
CL_KB=$3

echo Command line args:
echo CL_AG_BIN: $CL_AG_BIN
echo CL_KB:     $CL_KB
echo CL_PORT:   $CL_PORT

##SOURCING DEFAULTS
source $SCRIPT_DIR/ENV.sh

AG_BIN=${CL_AG_BIN:-$AG_BIN}
KB=${CL_KB:-$DEFAULT_KB}
PORT=${CL_PORT:-$DEFAULT_KB_PORT}

echo Variable values:
echo AG_BIN: $AG_BIN
echo KB: $KB
echo PORT: $PORT

echo Optimizing KB: $KB
echo start
date
$AG_BIN/../lib/agquery --port $PORT --optimize 2 $KB
echo end
date

