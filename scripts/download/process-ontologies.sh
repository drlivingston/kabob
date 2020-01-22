#!/bin/bash

# Given
EXPECTED_ARGS=2

if [ $# -ne $EXPECTED_ARGS ]
then
    echo "#NOTE: due to CD'ing in script use absolute file names!!"
    echo "Usage: ONTOLOGY_DIR MAVEN"
    echo "current usage:"
    echo $@
    exit 1
fi

if ! [[ -e README.md ]]; then
    echo "Please run from the root of the project."
    exit 1
fi

ONTOLOGY_DIR=$1
MAVEN=$2

mkdir -p ${ONTOLOGY_DIR}

# download the ontology files that will be used by kabob
scripts/download/support-scripts_process-ontologies/download-ontologies.sh ${ONTOLOGY_DIR}/dload.log ${ONTOLOGY_DIR}

# merge all ontology imports with the ontology into a single ntriples file
find $1 -name '*.owl' ! -name '*.flattened.owl' -exec scripts/download/support-scripts_process-ontologies/flatten-ontology.sh -i {} -o {}.flattened.nt -m ${MAVEN} \;

# convert all anonymous nodes into fully qualified URIs
find $1 -name '*.nt' ! -name '*.noblank.nt' -exec scripts/download/support-scripts_process-ontologies/bnode-to-uri.sh {} \;