# KaBOB
KaBOB (Knowledge Base Of Biomedicine)

This library is associated with the publication: <br />
> KaBOB: Ontology-Based Semantic Integration of Biomedical Databases <br />
> Kevin M Livingston, Michael Bada, William A Baumgartner, Lawrence E Hunter <br />
> BMC Bioinformatics (2015, to appear)

Please cite when using.

KaBOB is built on top of the [KR][] library, and numerous biomedical ontologies and data sources, which are parsed using the [datasources][] library.  KaBOB is primarily written in Clojure and is driven by Maven as well as a collection of Bash shell scripts.

The KaBOB code builds and manages a collection of RDF triples that allow querying of data using a common biomedical model based on the Open Biomedical Ontologies (OBOs). It iteratively loads triples into a triplestore and runs queries agains those triples to generate new triples, which are then loaded. (repeat)

Subdirectories:

* `scripts` the primary scripts for running KaBOB  
   to get a feel for a full load see [`build-kabob-ag.sh`](https://github.com/drlivingston/kabob/blob/master/scripts/build-kabob-ag.sh)  
   you can change your specific settings in [`ENV.sh`](https://github.com/drlivingston/kabob/blob/master/scripts/ENV.sh)
* `kabob-core` primary KaBOB library and common code
* `kabob-build` code specific for building KaBOB  
   the [rule files](https://github.com/drlivingston/kabob/tree/master/kabob-build/src/main/resources/edu/ucdenver/ccp/kabob/build/rules) are also located here 
* `kabob-dev` primary developer entry point, useful for establishing a REPL

Other components (which maybe should be in indepnedent projects, although they are built sepeartely by Maven into independent jars):
* `kabob-query` example queries and tools for common interactions
* `kabob-view` incomplete, experimental web viewer for KaBOB data
* `local-repository` a Maven setupo for plugging in Virtuoso



## Acknowledgements
open sourced by: <br />
[CCP Lab][] <br />
[University of Colorado Denver][] <br />
primary contact: [Kevin Livingston][] <br />
contributers: [Bill Baumgartner][], Mike Bada

----


[CCP Lab]: http://compbio.ucdenver.edu/Hunter_lab/CCP_website/index.html
[University of Colorado Denver]: http://www.ucdenver.edu/
[Kevin Livingston]: https://github.com/drlivingston
[Bill Baumgartner]: https://github.com/bill-baumgartner
[KR]: https://github.com/drlivingston/kr
[datasources]: https://github.com/UCDenver-ccp/datasource
