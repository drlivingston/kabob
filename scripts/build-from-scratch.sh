#!/bin/bash

HUDSON_DIR=`pwd`
chmod 755 scripts/*.sh
cat scripts/ENV.sh
source scripts/ENV.sh

# Clean out the directory in which we're going to place our artefacts.
rm -rvf $KB_DATA_DIR
mkdir -p $KB_DATA_DIR

# compile/install
$MAVEN clean install

# generate lists of RDF files that will be loaded in subsequent steps
./scripts/generate-rdf-file-lists.sh

# create a new KB and load the ontologies
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/new-kb.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME \
$KB_DATA_DIR/file-lists/owl-files.$KB_NAME.list \
guess
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

./scripts/RUN_RULES_AND_LOAD.sh rules/bio_to_ice

# load the ICE schema RDF ...
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/load-list-file.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME \
$KB_DATA_DIR/file-lists/schema-files.$KB_NAME.list
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

# ... and then the ICE RDF
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/load-list-file.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME \
$KB_DATA_DIR/file-lists/ice-files.$KB_NAME.list
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/load-list-file.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME \
$KB_DATA_DIR/file-lists/large-ice-files.$KB_NAME.list
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

# remove any duplicate triples in the KB
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/remove-duplicates.sh \
$AG_BIN \
$AG_PORT \
$AG_INDICES \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

# Index optimization
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/optimize.sh \
$AG_BIN \
$AG_PORT \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

./scripts/RUN_RULES_AND_LOAD.sh rules/id_typing
./scripts/RUN_RULES_AND_LOAD.sh rules/id_mapping/skos_exact

# Create the ID sets
$MAVEN -e -f kabob-build/pom.xml \
               -Dclojure.vmargs="-d64 -Xmx24G -XX:MaxPermSize=8G -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:-UseGCOverheadLimit" \
               -Dclojure.mainClass="edu.ucdenver.ccp.kabob.build.id_sets.generate" \
               -Dclojure.args="$KB_URL $KB_NAME $KB_USER $KB_PASS $KB_DATA_DIR/id_sets/exact/ $KB_DATA_DIR/id_sets/graph_dbs/" \
               clojure:run
find $KB_DATA_DIR -type d -exec chmod 0755 {} \;

./scripts/LOAD.sh id_sets/exact
# create bioentity for each id set
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/reify
# propagate type info from ids to the bioentities
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/type
# does GorGP abstractions
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/abstraction

# Index optimization
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/optimize.sh \
$AG_BIN \
$AG_PORT \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"

# this should be in ice_to_bio now... something wrong with this rule (file
# parser generating different ICE id's than ice-to-bio does)
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/taxon

./scripts/RUN_RULES_AND_LOAD.sh rules/ice_to_bio

# These rules are probably useful for the ice_to_bio rules but they need
# ice_to_bio that connects proteins to genes.  First abstraction rules really
# only handle genes. This rule adds gene product subclasses.
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/abstraction

# These rules should eventually be migrated from temp to ice-to-bio
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/drugbank
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/pharmgkb
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/irefweb
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/goa

# Apply labels to the BIO entities that have been created by the preceding
# rules.
./scripts/RUN_RULES_AND_LOAD.sh rules/bio_labels

./scripts/DEDUP.sh
AGRAPH_CMD=$(cat <<EOF
$HUDSON_DIR/scripts/optimize.sh \
$AG_BIN \
$AG_PORT \
$KB_NAME
EOF
)
ssh agraph@localhost "$AGRAPH_CMD"
