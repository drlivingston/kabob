#!/bin/bash

# Given
EXPECTED_ARGS=3

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
ONTOLOGY_URL=$3

mkdir -p ${ONTOLOGY_DIR}/tmp

# download the ontology files that will be used by kabob
echo "downloading $url"
scripts/download/support-scripts_process-ontologies/download-and-log.sh ${ONTOLOGY_DIR}/tmp/dload.log ${ONTOLOGY_DIR}/tmp ${ONTOLOGY_URL}

# merge all ontology imports with the ontology into a single ntriples file
find ${ONTOLOGY_DIR}/tmp -name '*.owl' ! -name '*.flattened.owl' -exec scripts/download/support-scripts_process-ontologies/flatten-ontology.sh -i {} -o {}.flattened.nt -m ${MAVEN} \;

# convert all anonymous nodes into fully qualified URIs
find ${ONTOLOGY_DIR}/tmp -name '*.nt' ! -name '*.noblank.nt' -exec scripts/download/support-scripts_process-ontologies/bnode-to-uri.sh {} \;

# copy updated ontology file to correct directory
mv ${ONTOLOGY_DIR}/tmp/*.noblank.nt ${ONTOLOGY_DIR}