#!/bin/bash

echo Sourcing Default Variables

#MAVEN_BIN=/data/hudson/apache-maven-3.1.1/bin
MAVEN_BIN=/data/hudson/apache-maven-3.2.2/bin
JAVA_HOME=/data/hudson/jdk1.7.0_45
#AG_BIN=/home/agraph/ag412/bin
#AG_BIN=/home/agraph/ag413/bin
#AG_BIN=/home/agraph/ag413.1/bin
AG_BIN=/home/agraph/ag414/bin

#DEFAULT_AG=ag414
DEFAULT_KB_SERVER=http://localhost
DEFAULT_KB_PORT=10035

#DEFAULT_KB=kabob20121201
DEFAULT_KB=kabob20140123

DEFAULT_UNAME=xx_user_xx
DEFAULT_PASS=xx_pass_xx


DATE_STAMP=20140123
DEFAULT_KABOB_DATA_ROOT=/data/kabob/$DATE_STAMP

DEFAULT_KB_INDICES="ospgi posgi spogi gspoi"
