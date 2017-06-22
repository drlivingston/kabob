#!/bin/bash

export LOAD_REQUEST_DIRECTORY=/virtuoso-load-requests

# retrieve the Virtuoso port being used from $LOAD_REQUEST_DIRECTORY/virtuoso.port
export PLATFORM_KB_PORT=$(cat "${LOAD_REQUEST_DIRECTORY}/virtuoso.port")

export PLATFORM_LEININGEN=/usr/local/bin/lein

# Network URL refers to the container name
export KB_INSTANCE_URL=http://virtuoso:${PLATFORM_KB_PORT}
export KB_INSTANCE_USER=dba
export KB_INSTANCE_PASS=dba
export PLATFORM_DATASOURCE_OWL_DIR=/kabob_data/ontology
export PLATFORM_DATASOURCE_ICE_DIR=/kabob_data/rdf
export KB_INSTANCE_DATA_DIR=/kabob_data/virtuoso-build

