(ns edu.ucdenver.ccp.kabob.kr.controlled-vocabulary
  (use edu.ucdenver.ccp.kabob.rule)
  (require [clojure.string :as string]))


(defn fv-exact-match [template value ontology-ice]
  (let [name (string/replace (string/replace (str "controled-vocab-" template "-" value "-" ontology-ice)
                                             #"\s+"
                                             "-")
                             #"/"
                             "_")]
    `{:name ~name
      :head ((?/fv skos/exactMatch ~ontology-ice))
      :body ((?/fv kiao/hasTemplate ~template)
             (?/fv obo/IAO_0000219 ~value))}))


(defmacro cv [template value ontology-ice]
  `(fv-exact-match (quote ~template) (quote ~value) (quote ~ontology-ice)))
