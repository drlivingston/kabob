#!/bin/bash

# find the path to KaBOB rules directory
path+="kabob-build/src/main/resources/edu/ucdenver/ccp/kabob/build/rules"
echo $path

if ! [[ -e README.md ]]; then
    echo "Please run from the root of the project."
    exit 1
fi

# run python script to create a visual graph of each Clojure rule
python kabob-build/src/main/python/RuleGrapher.py -a "$path"
