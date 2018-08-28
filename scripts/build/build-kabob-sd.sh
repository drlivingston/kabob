# COMPILE/INSTALL
# overwrite ENV.sh with the StarDog-specific ENV-SD.sh
cp scripts/ENV-SD.sh scripts/ENV.sh
cat scripts/ENV.sh
source scripts/ENV.sh

$MAVEN --version

#echo Skipping mvn rebuild
#$MAVEN clean install

HUDSON_DIR=`pwd`
chmod 755 scripts/*.sh

# clobber the old KB, then load the OWL files, then dedup
#ssh agraph@localhost "$HUDSON_DIR/scripts/new-kb.sh $AG_BIN $AG_PORT $KB_NAME $KB_DATA_DIR file_lists/owl-files.$KB_NAME.list guess"
#./scripts/DEDUP.sh
##echo "early fail for logging"
##exit 1;

# invoke the index optimizer
# ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $AG_PORT $KB_NAME"

#./scripts/RUN_RULES_AND_LOAD-SD.sh rules/bio_to_ice
echo Running Rules
./scripts/RULES.sh rules/bio_to_ice

#echo Loading Rule Output
#./scripts/LOAD-SD.sh rules/bio_to_ice

echo "early fail for logging"
exit 1;

#### Load the ICE RDF here 

##./scripts/DEDUP.sh
# invoke the index optimizer
ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $AG_PORT $KB_NAME"

##echo "early fail for logging"
##exit 1;

./scripts/RUN_RULES_AND_LOAD.sh rules/id_typing

echo "early fail for logging"
exit 1;

./scripts/RUN_RULES_AND_LOAD.sh rules/id_mapping/skos_exact

ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $AG_PORT $KB_NAME"


$MAVEN -e -f kabob-build/pom.xml -Dclojure.vmargs="-d64 -Xmx24G -XX:MaxPermSize=8G -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:-UseGCOverheadLimit" -Dclojure.mainClass="edu.ucdenver.ccp.kabob.build.id_sets.generate" -Dclojure.args="$KB_URL $KB_NAME $KB_USER $KB_PASS $KB_DATA_DIR/id_sets/exact/ $KB_DATA_DIR/id_sets/graph_dbs/" clojure:run


./scripts/LOAD.sh id_sets/exact

# create bioentity for each id set
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/reify
# propagate type info from ids to the bioentities
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/type
# does GorGP abstractions
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/abstraction



ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $AG_PORT $KB_NAME"

# this should be in ice_to_bio now... something wrong with this rule (file parser generating different ICE id's than ice-to-bio does)
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/taxon

./scripts/RUN_RULES_AND_LOAD.sh rules/ice_to_bio

#these rules are probably useful for the ice_to_bio rules
#  but they need ice_to_bio that connects proteins to genes.
# first abstraction rules really only handle genes. This rule adds gene product subclasses.
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/abstraction

# these rules should eventually be migrated from temp to ice-to-bio
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/drugbank
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/pharmgkb
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/irefweb
./scripts/RUN_RULES_AND_LOAD.sh rules/temp/goa

./scripts/DEDUP.sh
ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $AG_PORT $KB_NAME"
