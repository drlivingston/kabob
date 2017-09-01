#!/bin/bash

# find the path to KaBOB rules directory (run script from root 'kabob')
path+="resources/rules"
echo $path

if ! [[ -e README.md ]]; then
    echo "Please run from the root of the project."
    exit 1
fi

# run python script to create a visual graph of each Clojure rule
python scripts/documentation/rules/RuleGrapher.py -a "$path"
