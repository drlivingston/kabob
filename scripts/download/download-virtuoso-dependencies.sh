#!/bin/bash

if ! [[ -e README.md ]]; then
    echo "Please run from the root of the project."
    exit 1
fi

mkdir -p mvn-local-repository

wget -O ./mvn-local-repository/virt_sesame26.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/rdfproviders/sesame/26/virt_sesame2.jar
mvn deploy:deploy-file -DgroupId=virtuoso -DartifactId=sesame \
  -Dversion=2.6.0 -Dpackaging=jar -Dfile=./mvn-local-repository/virt_sesame26.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virt_sesame26.jar

wget -O ./mvn-local-repository/virt_sesame27.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/rdfproviders/sesame/27/virt_sesame2.jar
mvn deploy:deploy-file -DgroupId=virtuoso -DartifactId=sesame \
  -Dversion=2.7.0 -Dpackaging=jar -Dfile=./mvn-local-repository/virt_sesame27.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virt_sesame27.jar


wget -O ./mvn-local-repository/virtjdbc4.jar \
     http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VOSDownload/virtjdbc4.jar
mvn deploy:deploy-file -DgroupId=virtuoso -DartifactId=jdbc \
  -Dversion=4.0.0 -Dpackaging=jar -Dfile=./mvn-local-repository/virtjdbc4.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virtjdbc4.jar

wget -O ./mvn-local-repository/virtjdbc3.jar \
     http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VOSDownload/virtjdbc3.jar
mvn deploy:deploy-file -DgroupId=virtuoso -DartifactId=jdbc \
  -Dversion=3.0.0 -Dpackaging=jar -Dfile=./mvn-local-repository/virtjdbc3.jar \
  -Durl=file:mvn-local-repository
rm ./mvn-local-repository/virtjdbc3.jar


echo done.