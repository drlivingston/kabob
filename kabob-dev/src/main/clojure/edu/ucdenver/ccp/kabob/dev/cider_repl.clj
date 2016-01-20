
(ns edu.ucdenver.ccp.kabob.dev.cider-repl
  "In which is defined the mechanisms for starting an nREPL server and for
  manipulating the knowledge base build process."
  (:gen-class)
  (:require [clojure.tools.nrepl.server :refer [start-server]]
            [cider.nrepl :refer (cider-nrepl-handler)]))

(defn -main
  [& args]
  (start-server :port 4005 :handler cider-nrepl-handler))
