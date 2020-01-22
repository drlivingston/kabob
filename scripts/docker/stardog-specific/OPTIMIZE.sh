#!/bin/bash

SCRIPT_DIR=/kabob.git/scripts/docker
source ${SCRIPT_DIR}/stardog-specific/docker-env.sh
source ${SCRIPT_DIR}/common-scripts/ENV.sh

$SCRIPT_DIR/stardog-specific/run-optimize.sh \
              ${KB_PORT} \
              ${KB_NAME}
