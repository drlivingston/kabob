#
#!/bin/bash
#

SCRIPT_DIR=/kabob.git/scripts/docker
chmod 755 $SCRIPT_DIR/*.sh

source /config/user-env.sh
source $SCRIPT_DIR/docker-env.sh
source $SCRIPT_DIR/ENV.sh

echo "MAVEN=$MAVEN"
echo "AG_HOME=$AG_HOME"
echo "AG_PORT=$AG_PORT"
echo "KB_URL=$KB_URL"
echo "KB_USER=$KB_USER"
echo "KB_PASS=$KB_PASS"
echo "KB_NAME=$KB_NAME"
echo "DATASOURCE_OWL_DIR=$DATASOURCE_OWL_DIR"
echo "DATASOURCE_ICE_DIR=$DATASOURCE_ICE_DIR"
echo "KB_DATA_DIR=$KB_DATA_DIR"

# Clean out the directory in which we're going to place our artefacts.
rm -rvf $KB_DATA_DIR
mkdir -p $KB_DATA_DIR

# generate lists of RDF files that will be loaded in subsequent steps
$SCRIPT_DIR/generate-rdf-file-lists.sh

# create a new KB and load the ontologies
$SCRIPT_DIR/new-kb.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME \
  $KB_DATA_DIR/file-lists/owl-files.$KB_NAME.list \
  "rdfxml"

$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/bio_to_ice

# load the ICE schema RDF ...
$SCRIPT_DIR/load-list-file.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME \
  $KB_DATA_DIR/file-lists/schema-files.$KB_NAME.list

# ... and then the ICE RDF
$SCRIPT_DIR/load-list-file.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME \
  $KB_DATA_DIR/file-lists/ice-files.$KB_NAME.list

$SCRIPT_DIR/load-list-file.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME \
  $KB_DATA_DIR/file-lists/large-ice-files.$KB_NAME.list

# remove any duplicate triples in the KB
$SCRIPT_DIR/remove-duplicates.sh \
  $AG_BIN \
  $AG_PORT \
  $AG_INDICES \
  $KB_NAME

# Index optimization
$SCRIPT_DIR/optimize.sh \
  $AG_BIN \
  $AG_PORT \
  $KB_NAME

$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/id_typing
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/id_mapping/skos_exact

# Create the ID sets
$MAVEN -e -f /kabob.git/kabob-build/pom.xml \
               -Dclojure.vmargs="-d64 -Xmx24G -XX:MaxPermSize=8G -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:-UseGCOverheadLimit" \
               -Dclojure.mainClass="edu.ucdenver.ccp.kabob.build.id_sets.generate" \
               -Dclojure.args="$KB_URL $KB_NAME $KB_USER $KB_PASS $KB_DATA_DIR/id_sets/exact/ $KB_DATA_DIR/id_sets/graph_dbs/" \
               clojure:run
find $KB_DATA_DIR -type d -exec chmod 0755 {} \;

$SCRIPT_DIR/LOAD.sh id_sets/exact
# create bioentity for each id set
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/entity/reify
# propagate type info from ids to the bioentities
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/entity/type
# does GorGP abstractions
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/entity/abstraction

# Index optimization
$SCRIPT_DIR/optimize.sh \
  $AG_BIN \
  $AG_PORT \
  $KB_NAME

# this should be in ice_to_bio now... something wrong with this rule (file
# parser generating different ICE id's than ice-to-bio does)
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/taxon

$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/ice_to_bio

# These rules are probably useful for the ice_to_bio rules but they need
# ice_to_bio that connects proteins to genes.  First abstraction rules really
# only handle genes. This rule adds gene product subclasses.
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/abstraction

# These rules should eventually be migrated from temp to ice-to-bio
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/drugbank
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/pharmgkb
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/irefweb
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/temp/goa

# Apply labels to the BIO entities that have been created by the preceding
# rules.
$SCRIPT_DIR/RUN_RULES_AND_LOAD.sh rules/bio_labels

$SCRIPT_DIR/DEDUP.sh
$SCRIPT_DIR/optimize.sh \
  $AG_BIN \
  $AG_PORT \
  $KB_NAME

