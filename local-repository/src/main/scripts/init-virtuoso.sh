#!/bin/bash

echo run from top directory with pom

mkdir -p mvn-local-repository

wget -O ./mvn-local-repository/virt_sesame2.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/rdfproviders/sesame/26/virt_sesame2.jar
mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
                         -Dfile=./mvn-local-repository/virt_sesame2.jar -DgroupId=virtuoso \
                         -DartifactId=sesame -Dversion=2.6.0 \
                         -Dpackaging=jar -DgeneratePom=true \
                         -DlocalRepositoryPath=./mvn-local-repository
rm ./mvn-local-repository/virt_sesame2.jar

wget -O ./mvn-local-repository/virt_sesame2.jar \
     http://opldownload.s3.amazonaws.com/uda/virtuoso/rdfproviders/sesame/27/virt_sesame2.jar
mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
                         -Dfile=./mvn-local-repository/virt_sesame2.jar -DgroupId=virtuoso \
                         -DartifactId=sesame -Dversion=2.7.0 \
                         -Dpackaging=jar -DgeneratePom=true \
                         -DlocalRepositoryPath=./mvn-local-repository
rm ./mvn-local-repository/virt_sesame2.jar


wget -O ./mvn-local-repository/virtjdbc4.jar \
     http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VOSDownload/virtjdbc4.jar
mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
                         -Dfile=./mvn-local-repository/virtjdbc4.jar -DgroupId=virtuoso \
                         -DartifactId=jdbc -Dversion=4.0.0 \
                         -Dpackaging=jar -DgeneratePom=true \
                         -DlocalRepositoryPath=./mvn-local-repository
rm ./mvn-local-repository/virtjdbc4.jar

wget -O ./mvn-local-repository/virtjdbc3.jar \
     http://virtuoso.openlinksw.com/dataspace/doc/dav/wiki/Main/VOSDownload/virtjdbc3.jar
mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
                         -Dfile=./mvn-local-repository/virtjdbc3.jar -DgroupId=virtuoso \
                         -DartifactId=jdbc -Dversion=3.0.0 \
                         -Dpackaging=jar -DgeneratePom=true \
                         -DlocalRepositoryPath=./mvn-local-repository
rm ./mvn-local-repository/virtjdbc3.jar


echo done.


