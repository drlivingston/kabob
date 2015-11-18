#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
source $SCRIPT_DIR/ENV.sh

# The location of the downloaded OWL files.
OWL_BASE=$DATASOURCE_OWL_DIR
# The location of the downloaded and triplified ICE source files.
ICE_BASE=$DATASOURCE_ICE_DIR

FILE_LIST_BASE=$KB_DATA_DIR/file-lists

export ICE_LIST="$FILE_LIST_BASE/ice-files.$KB_NAME.list"
export LARGE_ICE_LIST="$FILE_LIST_BASE/large-ice-files.$KB_NAME.list"
export OWL_LIST="$FILE_LIST_BASE/owl-files.$KB_NAME.list"
export SCHEMA_LIST="$FILE_LIST_BASE/schema-files.$KB_NAME.list"

if [[ ! -d $FILE_LIST_BASE ]]; then
    mkdir -p $FILE_LIST_BASE
fi

for list in $ICE_LIST $LARGE_ICE_LIST $OWL_LIST $SCHEMA_LIST; do
    if [[ -f $list ]]; then
        rm $list
    fi
done

for file in $(find -L "$ICE_BASE" -type f \( -iname "*.nt.gz" ! -iname "*goa*" ! -iname "*protein2ipr*" ! -iname "*uniprot-Trembl*" \)); do echo "$file" >> "$ICE_LIST"; done
for file in $(find -L "$ICE_BASE" -type f \( -iname "*goa*.nt.gz" -or -iname "*protein2ipr*.nt.gz" \)); do echo "$file" >> "$LARGE_ICE_LIST"; done
for file in $(find -L "$ICE_BASE" -type f -name "*.schema.nt"); do echo "$file" >> "$SCHEMA_LIST"; done
for file in $(find -L "$OWL_BASE" -type f -name "*.owl"); do echo "$file" >> "$OWL_LIST"; done

echo "SCHEMA file to load:"
cat $SCHEMA_LIST
echo
echo "OWL files to load:"
cat $OWL_LIST
echo
echo "ICE files to load:"
cat $ICE_LIST
echo
echo "Large ICE files to load:"
cat $LARGE_ICE_LIST
