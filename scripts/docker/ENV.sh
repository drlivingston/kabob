#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source $SCRIPT_DIR/INIT.sh
echo Sourcing default variables

## ------------------------------------------------------------------------- ##
## Set variables for build environment and installation.                     ##
## ------------------------------------------------------------------------- ##

# We use Maven as a Java execution engine ):
MAVEN=${PLATFORM_MAVEN:?}

# The build expects to be co-located with an Allegrograph installation.
AG_HOME=${PLATFORM_ALLEGROGRAPH_HOME:?}
AG_PORT=${PLATFORM_ALLEGROGRAPH_PORT:?}

# The URL by which to connect to the knowledgebase when generating the rules.
KB_URL=${KB_INSTANCE_URL:?}
# The name used to connect to the database when generating the rules.
KB_USER=${KB_INSTANCE_USER:?}
# The password used to connect to the database when generating the rules.
KB_PASS=${KB_INSTANCE_PASS:?}
# The database or schema name by which the knowledgebase is known to the store.
KB_NAME=${KB_INSTANCE_NAME:?}

# The directories containing the data source files that must be loaded.
function assert_datasource_dir_exists {
    if ! [[ -d $1 ]]; then
        echo "Invalid $2 directory $1"
        exit 1
    fi
}
# The location of the downloaded ontologies.
DATASOURCE_OWL_DIR=${PLATFORM_DATASOURCE_OWL_DIR:?}
assert_datasource_dir_exists $DATASOURCE_OWL_DIR OWL
# The location of the downloaded data source files.
DATASOURCE_ICE_DIR=${PLATFORM_DATASOURCE_ICE_DIR:?}
assert_datasource_dir_exists $DATASOURCE_ICE_DIR ICE

# The directory into which the genereated rules will be written.
KB_DATA_DIR=${KB_INSTANCE_DATA_DIR:?}

## ------------------------------------------------------------------------- ##
## No user-adjustable variables beyond this point.                           ##
## ------------------------------------------------------------------------- ##

AG_BIN=${AG_HOME}/bin
AG_INDICES="ospgi,posgi,spogi,gspoi"
