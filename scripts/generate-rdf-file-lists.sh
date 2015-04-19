#!/bin/bash

SCRIPT_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

##SOURCING DEFAULTS
source $SCRIPT_DIR/ENV.sh

# requires the DATE_STAMP environment variable to have been previously set
RDF_BASE=/RAID3/data/hudson/weekly-rdf/$DATE_STAMP
ICE_BASE=$RDF_BASE/ice
FILE_LIST_BASE=$RDF_BASE/file_lists
OWL=/RAID3/data/hudson/weekly-downloads/$DATE_STAMP/owl
export ICE_LIST="$FILE_LIST_BASE/ice-files.$DATE_STAMP.list"
export LARGE_ICE_LIST="$FILE_LIST_BASE/large-ice-files.$DATE_STAMP.list"
export OWL_LIST="$FILE_LIST_BASE/owl-files.$DATE_STAMP.list"
export SCHEMA_LIST="$FILE_LIST_BASE/schema-files.$DATE_STAMP.list"

if [ ! -d $FILE_LIST_BASE ];
then
    mkdir $FILE_LIST_BASE
fi

if [ -f $ICE_LIST ];
then
rm $ICE_LIST
fi

if [ -f $LARGE_ICE_LIST ];
then
rm $LARGE_ICE_LIST
fi

if [ -f $OWL_LIST ];
then
rm $OWL_LIST
fi

if [ -f $SCHEMA_LIST ];
then
rm $SCHEMA_LIST
fi

for file in $(find "$ICE_BASE" -type f \( -iname "*.nt.gz" ! -iname "*goa*" ! -iname "*protein2ipr*" ! -iname "*uniprot-Trembl*" \)); do echo "$file" >> "$ICE_LIST"; done
for file in $(find "$ICE_BASE" -type f \( -iname "*goa*.nt.gz" -or -iname "*protein2ipr*.nt.gz" \)); do echo "$file" >> "$LARGE_ICE_LIST"; done
for file in $(find "$ICE_BASE" -type f -name "*.schema.nt"); do echo "$file" >> "$SCHEMA_LIST"; done
for file in $(find "$OWL" -type f -name "*.owl"); do echo "$file" >> "$OWL_LIST"; done

echo "SCHEMA file to load:"
cat $SCHEMA_LIST

echo ""
echo "OWL files to load:"
cat $OWL_LIST

echo ""
echo "ICE files to load:"
cat $ICE_LIST

echo ""
echo "Large ICE files to load:"
cat $LARGE_ICE_LIST


