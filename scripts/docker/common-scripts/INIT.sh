#!/bin/bash

## ------------------------------------------------------------------------- ##
## Common initialization of KB build scripts
##
## Although most (and in time: all) of the KB build scripts configure
## themselves using the parameters that they are given when called, some of the
## parameters of the environment are not as easily communicated.  For example,
## many steps in this process rely on using SSH to change user identity; in
## this case the environment in which the script execution was started is lost.
## ------------------------------------------------------------------------- ##

#set -e
#set -x
#export SHELLOPTS
