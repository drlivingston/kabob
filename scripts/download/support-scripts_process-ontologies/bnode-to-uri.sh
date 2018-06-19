#!/bin/bash
#
# given a .nt file as the first input argument, this script will convert each blank node
# into a fully specified URI.

FILE=$1
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo "Transforming blank nodes to URIs: ${FILE}"
export JAVA_OPTS="$JAVA_OPTS -Xmx8G"
groovy ${DIR}/groovy/BNodeConverter.groovy ${FILE}
