(defproject edu.ucdenver.ccp/kabob "2.0.0-SNAPSHOT"
  :description "KaBOB, the knowledgebase of biology"
  :url "https://github.com/UCDenver-ccp/kabob"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
  				       [org.clojure/core.cache "0.6.4"]
  			      	 [cheshire/cheshire "5.2.0"]
  				       [edu.ucdenver.ccp/kr "1.5.0-SNAPSHOT"]
								 [org.openrdf.sesame/sesame-util "4.1.2"]
								 [org.openrdf.sesame/sesame-query "4.1.2"]
								 [org.apache.httpcomponents/httpclient "4.5.3"]
  				       [org.clojure/core.memoize "0.5.6"]
  				       [aysylu/loom "0.5.0"]
  				       [org.clojure/data.csv "0.1.2"]
  				       [log4j/log4j "1.2.17"]
  				       [org.slf4j/slf4j-log4j12 "1.7.2"]
  				       [potemkin/potemkin "0.1.2"]
  				       [commons-codec/commons-codec "1.6"]
								 [com.complexible.stardog.sesame/stardog-sesame-core "5.0"]
								 [virtuoso/virtuoso-jdbc "4.2"]
								 [virtuoso/virtuoso-rdf4j "2.x"]]
	:repositories {"local" "file:mvn-local-repository"
								 "stardog-public" "http://maven.stardog.com"}
  ;:main ^:skip-aot my-stuff.core
  :main nil
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
						 :dev {:dependencies [[test-with-files "0.1.1"]]
									 :resource-paths ["test/resources"]}
             :main-a {:main kabob.build.run-rules
                      :jvm-opts ["-d64" "-Xmx2g"]}
             :main-b {:main kabob.build.id-sets.generate
             	      :jvm-opts ["-d64" "-Xmx24g"]}
						 :main-c {:main kabob.build.eval-sparql
											:jvm-opts ["-d64" "-Xmx2g"]}}
  :aliases {"run-rules" ["with-profile" "main-a" "run"]
            "generate-id-sets" ["with-profile" "main-b" "run"]
						"eval-sparql" ["with-profile" "main-c" "run"]})

