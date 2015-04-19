HUDSON_DIR=`pwd`
chmod 755 scripts/*.sh
cat scripts/ENV.sh
source scripts/ENV.sh

### compile/install
$MAVEN_BIN/mvn clean install

### generate lists of RDF files that will be loaded in subsequent steps
./scripts/generate-rdf-file-lists.sh

### create a new KB and load the ontologies
#ssh agraph@localhost "$HUDSON_DIR/scripts/new-kb.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT file_lists/owl-files.$DATE_STAMP.list guess"

#./scripts/RUN_RULES_AND_LOAD.sh rules/bio_to_ice

### load the ICE schema RDF and then the ICE RDF
#ssh agraph@localhost "$HUDSON_DIR/scripts/load-list-file.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT file_lists/schema-files.$DATE_STAMP.list"
#ssh agraph@localhost "$HUDSON_DIR/scripts/load-list-file.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT file_lists/ice-files.$DATE_STAMP.list"

### remove any duplicate triples in the KB
#./scripts/DEDUP.sh

# index optimization here
ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"

### create a backup of the KB
# BACKUP HERE??

###./scripts/RUN_RULES_AND_LOAD.sh rules/id_typing

###./scripts/RUN_RULES_AND_LOAD.sh rules/id_mapping/skos_exact


###ssh agraph@localhost "$HUDSON_DIR/scripts/load-list-file.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT file_lists/large-ice-files.$DATE_STAMP.list"