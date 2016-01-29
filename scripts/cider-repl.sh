#!/bin/bash

echo starting CIDER nREPL

pwd
mvn -e -f kabob-dev/pom.xml \
    -Dclojure.vmargs="-d64 -Xmx2G" \
    -Dclojure.runwith.test=true \
    -Dclojure.mainClass="edu.ucdenver.ccp.kabob.dev.cider_repl" \
    clojure:run
