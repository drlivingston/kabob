(defproject edu.ucdenver.ccp/kabob "2.0.0-SNAPSHOT"
  :description "KaBOB, the knowledgebase of biology"
  :url "https://github.com/UCDenver-ccp/kabob"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
  				 [org.clojure/core.cache "0.6.4"]
  				 [cheshire/cheshire "5.2.0"]
  				 [edu.ucdenver.ccp/kr-core "1.4.20-SNAPSHOT"]
  				 [edu.ucdenver.ccp/kr-core "1.4.20-SNAPSHOT" :classifier "tests"]
  				 [edu.ucdenver.ccp/kr-sesame-core "1.4.20-SNAPSHOT"]
  				 [edu.ucdenver.ccp/kr-sesame-core "1.4.20-SNAPSHOT" :classifier "tests"]
  				 [org.clojure/core.memoize "0.5.6"]
  				 [aysylu/loom "0.5.0"]
  				 [org.clojure/data.csv "0.1.2"]
  				 [log4j/log4j "1.2.17"]
  				 [org.slf4j/slf4j-log4j12 "1.7.2"]
  				 [potemkin/potemkin "0.1.2"]
  				 [org.neo4j/neo4j "1.8.1"]
  				 [commons-codec/commons-codec "1.6"]]
  ;:main ^:skip-aot my-stuff.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :main-a {:main kabob.build.run_rules
                      :jvm-opts ["-d64" "-Xmx2g"]}
             :main-b {:main kabob.build.id_sets.generate
             	      :jvm-opts ["-d64" "-Xmx24g"]}}
  :aliases {"run-rules" ["with-profile" "main-a" "run"]
            "generate-id-sets" ["with-profile" "main-b" "run"]})