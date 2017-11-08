#!/bin/bash

# most downloads are handled by the file parser library, however the irefweb downloads sometimes terminate
# prematurely so we use wget here to download the human irefweb file
mkdir -p /kabob_data/raw/irefweb

DATE=$(date +%m/%d/%Y)

# wget the irefweb file using an automated retry-on-failure flag
cd /kabob_data/raw/irefweb && { wget -c -t 0 --timeout 60 --waitretry 10 http://irefindex.org/download/irefindex/data/archive/release_14.0/psi_mitab/MITAB2.6/9606.mitab.07042015.txt.zip ; unzip -o 9606.mitab.07042015.txt.zip ; touch -mt 1504070000 9606.mitab.04072015.txt ; cd - ; }
/kabob.git/scripts/download/create-metadata-file.sh /kabob_data/raw/irefweb/9606.mitab.04072015.txt  http://irefindex.org/download/irefindex/data/archive/release_14.0/psi_mitab/MITAB2.6/9606.mitab.07042015.txt.zip



# there are also resources that are not processed by the file parsers directly, e.g. the Reactome biopax OWL file.
# These resources are downloaded here and placed into the /kabob_data/rdf/ directory so that they are loaded
# automatically.

# wget Reactome in BioPax format
mkdir -p /kabob_data/raw/reactome
cd /kabob_data/raw/reactome && { wget -c -t 0 --timeout 60 --waitretry 10 http://www.reactome.org/download/current/biopax.zip ; unzip -o biopax.zip ; cd - ; }
/kabob.git/scripts/download/create-metadata-file.sh /kabob_data/raw/reactome/Homo_sapiens.owl  http://www.reactome.org/download/current/biopax.zip
/kabob.git/scripts/download/create-metadata-rdf.sh /kabob_data/raw/reactome/Homo_sapiens.owl REACTOME http://www.reactome.org/download/current/biopax.zip
mkdir -p /kabob_data/rdf/reactome
# copy the human reactome OWL file to the /kabob_data/rdf/reactome directory so that it will be loaded automatically
cp /kabob_data/raw/reactome/Homo_sapiens.owl /kabob_data/rdf/reactome/Homo_sapiens.owl
# copy the human reactome OWL metadata RDF file to the /kabob_data/rdf/reactome directory so that it will be loaded automatically
cp /kabob_data/raw/reactome/Homo_sapiens.owl.ready.nt /kabob_data/rdf/reactome/Homo_sapiens.owl.ready.nt
gzip /kabob_data/rdf/reactome/Homo_sapiens.owl.ready.nt
