(ns edu.ucdenver.ccp.kabob.rule
  (use edu.ucdenver.ccp.utils
       edu.ucdenver.ccp.kr.variable
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.rule)
  (require edu.ucdenver.ccp.kabob.reification-extensions))

;; this needs to be an atom so that it can be mutated?
(def ^:dynamic *gentemp-counters*)
(defn new-gentemp-counters [] {})

(defn next-name [name]
  (let [c (get *gentemp-counters* name 0)]
    (set! *gentemp-counters* (assoc *gentemp-counters* name (inc c)))
    ;;(str name "-" c)))
    (str name "" c))) ;changed from a hyphen because of AG nonconfority

(defn next-var [name]
  (variable (next-name name)))

(defn next-anon [name]
  (anon (next-name name)))


(defmacro with-reset-ids [& body]
  `(binding [*gentemp-counters* (new-gentemp-counters)]
     ~@body))


(defn kabob-rule-reader [reader]
  (with-reset-ids
    (read-eval-with-sentinel reader)))
    ;; (eval
;;  (read source nil source))))


(defn kabob-load-rules-from-classpath [path]
  (binding [*dynamic-reader-fn* kabob-rule-reader]
    (doall (load-rules-from-classpath path))))

(defn kabob-load-rules-from-file [path]
  (binding [*dynamic-reader-fn* kabob-rule-reader]
    (doall (load-rules-from-file path))))


(defn kabob-load-rules-from-directory [path]
  (binding [*dynamic-reader-fn* kabob-rule-reader]
    (doall (load-rules-from-directory path))))
