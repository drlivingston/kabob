
(ns edu.ucdenver.ccp.kabob.dev.cider-repl
  "In which is defined the mechanisms for starting an nREPL server and for
  manipulating the knowledge base build process."
  (:gen-class)
  (:refer-clojure :exclude [format])
  (:require [clojure.tools.nrepl.server :refer [start-server]]
            [cider.nrepl :refer (cider-nrepl-handler)]
            [mantle.io :refer [format]]
            [edu.ucdenver.ccp.kabob.build.run-rules :refer [process-forward-rules]]
            [edu.ucdenver.ccp.kabob.rule :refer [kabob-load-rules-from-directory
                                                 kabob-load-rules-from-file]]))

(defn load-rules-from-directory
  [dir]
  (let [rules (kabob-load-rules-from-directory dir)]
    (format *out* "Loaded ~a rules from ~a~%" (count rules) dir)
    rules))

(defn load-rules-from-file
  [file]
  (let [rules (kabob-load-rules-from-file file)]
    (format *out* "Loaded ~a rules from ~a~%" (count rules) file)
    rules))

(defn run-rules
  [server-url repo-name user pass output-dir rules-file]
  (process-forward-rules {:server-url server-url
                          :repo-name repo-name
                          :username user
                          :password pass
                          :output-directory output-dir}
                         (load-rules-from-file rules-file)))

(defn run-rule-set
  [server-url repo-name user pass output-dir rule-set-dir]
  (process-forward-rules {:server-url server-url
                          :repo-name repo-name
                          :username user
                          :password pass
                          :output-directory output-dir}
                         (load-rules-from-directory rule-set-dir)))

(defn -main
  [& args]
  (start-server :port 4005 :handler cider-nrepl-handler))
