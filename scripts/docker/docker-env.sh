#!/bin/bash

export LOAD_REQUEST_DIRECTORY=/ag-load-requests

# retrieve the AllegroGraph port being used from $LOAD_REQUEST_DIRECTORY/agraph.port
export PLATFORM_ALLEGROGRAPH_PORT=$(cat "$LOAD_REQUEST_DIRECTORY/agraph.port")

export PLATFORM_MAVEN=/usr/bin/mvn

# Network URL refers to the container name
export KB_INSTANCE_URL=http://agraph:$PLATFORM_ALLEGROGRAPH_PORT
export KB_INSTANCE_USER=test
export KB_INSTANCE_PASS=xyzzy
export PLATFORM_DATASOURCE_OWL_DIR=/kabob_data/ontology
export PLATFORM_DATASOURCE_ICE_DIR=/kabob_data/rdf
export KB_INSTANCE_DATA_DIR=/kabob_data/build

