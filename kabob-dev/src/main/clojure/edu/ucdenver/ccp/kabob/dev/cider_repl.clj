(ns edu.ucdenver.ccp.kabob.dev.cider-repl
  (:gen-class)
  (:require [clojure.tools.nrepl.server :as nrepl-server]
            [cider.nrepl :refer (cider-nrepl-handler)]))

(defn -main
  [& args]
  (nrepl-server/start-server :port 4005 :handler cider-nrepl-handler))
