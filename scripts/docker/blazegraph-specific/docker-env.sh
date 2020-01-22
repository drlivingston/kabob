#!/bin/bash

export LOAD_REQUEST_DIRECTORY=/blazegraph-load-requests

# retrieve the port being used from $LOAD_REQUEST_DIRECTORY/agraph.port
export PLATFORM_KB_PORT=$(cat "${LOAD_REQUEST_DIRECTORY}/blazegraph.port")
export CONTAINER_NAME=$(cat "${LOAD_REQUEST_DIRECTORY}/blazegraph.container.name")

export PLATFORM_LEININGEN=/usr/local/bin/lein

# Network URL refers to the container name
export KB_INSTANCE_URL=http://${CONTAINER_NAME}:${PLATFORM_KB_PORT}
export KB_INSTANCE_USER=admin
export KB_INSTANCE_PASS=admin
export PLATFORM_DATASOURCE_OWL_DIR=/kabob_data/ontology
export PLATFORM_DATASOURCE_ICE_DIR=/kabob_data/rdf
export KB_INSTANCE_DATA_DIR=/kabob_data/blazegraph-build

