#!/bin/bash

# find the path to KaBOB rules directory
path+="/resources/edu/ucdenver/ccp/kabob/build/rules"
echo $path

# run python script to create a visual graph of each Clojure rule
python RuleGrapher.py -a "$path"
