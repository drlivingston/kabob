#!/bin/bash

if ! [[ -e README.md ]]; then
    echo "Please run from the root of the project."
    exit 1
fi

MAVEN=$1

mkdir -p mvn-local-repository

wget -O ./mvn-local-repository/virt_rdf4j.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/7.2/rdfproviders/rdf4j/2/virt_rdf4j.jar
${MAVEN} deploy:deploy-file -DgroupId=virtuoso -DartifactId=virtuoso-rdf4j \
  -Dversion=2.x -Dpackaging=jar -Dfile=./mvn-local-repository/virt_rdf4j.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virt_rdf4j.jar

wget -O ./mvn-local-repository/virtjdbc4_2.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/7.2/jdbc/virtjdbc4_2.jar
${MAVEN} deploy:deploy-file -DgroupId=virtuoso -DartifactId=virtuoso-jdbc \
  -Dversion=4.2 -Dpackaging=jar -Dfile=./mvn-local-repository/virtjdbc4_2.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virtjdbc4_2.jar

echo done.