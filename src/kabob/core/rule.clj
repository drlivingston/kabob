(ns kabob.core.rule
  (use kr.core.utils
       kr.core.variable
       kr.core.rdf
       kr.core.rule)
  (require kabob.core.reification-extensions))

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
    ;(prn (str "===== Loading rules from directory: " path))
    (doall (load-rules-from-directory path))))
