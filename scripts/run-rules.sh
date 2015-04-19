#!/bin/bash

if [ $# -ne 8 ]  #|| [ $# -gt 6 ]
then
  echo "Usage: MAVEN_BIN  KB_SERVER KB_PORT KB_NAME UNAME PASS KABOB_ROOT RULEPAT"
  echo $@
  exit 1
fi

MAVEN_BIN=$1
KB_SERVER=$2
PORT=$3
KB=$4
UNAME=$5
PASS=$6
KABOB_DATA_ROOT=$7
RULEPAT=$8

OUTPUTDIR=$KABOB_DATA_ROOT/$RULEPAT/
RULESCP=edu/ucdenver/ccp/kabob/build/$RULEPAT/

echo MAVEN_BIN: $MAVEN_BIN
echo KB_SERVER: $KB_SERVER
echo PORT: $PORT
echo KB: $KB
echo UNAME: $UNAME
echo PASS: $PASS
echo KABOB_DATA_ROOT: $KABOB_DATA_ROOT
echo OUTPUTDIR: $OUTPUTDIR
echo RULEPAT: $RULEPAT
echo RULESCP: $RULESCP

echo Running Rules

# COMPILE/INSTALL
#/data/hudson/tools/apache-maven-3.0.4/bin/mvn -U clean install
# RUN THE ID TYPING RULES
pwd
mkdir -p $KABOB_DATA_ROOT/$RULEPAT/
$MAVEN_BIN/mvn -e -f kabob-build/pom.xml -Dclojure.vmargs="-d64 -Xmx2G -XX:MaxPermSize=256m" -Dclojure.mainClass="edu.ucdenver.ccp.kabob.build.run_rules" -Dclojure.args="$KB_SERVER:$PORT $KB $UNAME $PASS $OUTPUTDIR $RULESCP" clojure:run

