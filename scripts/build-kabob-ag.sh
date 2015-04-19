# COMPILE/INSTALL
# overwrite ENV.sh with the server-specific file ENV-SERVER.sh
#cp scripts/ENV-SERVER.sh scripts/ENV.sh
cat scripts/ENV.sh
source scripts/ENV.sh

$MAVEN_BIN/mvn --version

#echo Skipping mvn rebuild
$MAVEN_BIN/mvn clean install

HUDSON_DIR=`pwd`
chmod 755 scripts/*.sh

# clobber the old KB, then load the OWL files, then dedup
#ssh agraph@localhost "$HUDSON_DIR/scripts/new-kb.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_KABOB_DATA_ROOT file_lists/owl-files.$DATE_STAMP.list guess"
#./scripts/DEDUP.sh
##echo "early fail for logging"
##exit 1;

# invoke the index optimizer
# ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"

#./scripts/RUN_RULES_AND_LOAD.sh rules/bio_to_ice

#### Load the ICE RDF here  (bio-to-ice rules took 2min, so we can load the ICE earlier. In this case it was loaded manually outside of this script.)

##./scripts/DEDUP.sh
# invoke the index optimizer
ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"

##echo "early fail for logging"
##exit 1;

./scripts/RUN_RULES_AND_LOAD.sh rules/id_typing
./scripts/RUN_RULES_AND_LOAD.sh rules/id_mapping/skos_exact

ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"
echo "early fail for logging"
exit 1;



$MAVEN_BIN/mvn -e -f kabob-build/pom.xml -Dclojure.vmargs="-d64 -Xmx24G -XX:MaxPermSize=8G -XX:+UseParallelGC -XX:+UseParallelOldGC -XX:-UseGCOverheadLimit" -Dclojure.mainClass="edu.ucdenver.ccp.kabob.build.id_sets.generate" -Dclojure.args="$DEFAULT_KB_SERVER:$DEFAULT_KB_PORT $DEFAULT_KB $DEFAULT_UNAME $DEFAULT_PASS $DEFAULT_KABOB_DATA_ROOT/id_sets/exact/ $DEFAULT_KABOB_DATA_ROOT/id_sets/graph_dbs/" clojure:run


./scripts/LOAD.sh id_sets/exact

# create bioentity for each id set
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/reify
# propagate type info from ids to the bioentities
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/type
# does GorGP abstractions
./scripts/RUN_RULES_AND_LOAD.sh rules/entity/abstraction



ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"

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
ssh agraph@localhost "$HUDSON_DIR/scripts/optimize.sh $AG_BIN $DEFAULT_KB_PORT $DEFAULT_KB"



