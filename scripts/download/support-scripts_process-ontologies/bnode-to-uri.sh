#!/bin/bash
#
# given a .nt file as the first input argument, this script will convert each blank node
# into a fully specified URI. Note: this is only safe to use on a
# per-file basis. To combine files with fully specified blank nodes
# one must convert them back to blank nodes. See uri-to-bnode.sh

FILE=$1
echo "Transforming blank nodes to URIs: $FILE"
FILENAME=${FILE##*/}
PREFIX=$(echo ${FILENAME} | cut -f 1 -d '.')
sed "s/_:\(genid[0-9][0-9]*\)/<http:\/\/ccp.ucdenver.edu\/bnode\/${PREFIX}_\1>/g" $1 > $1.noblank.nt
