(ns kabob
  (require potemkin))
           ;;edu.ucdenver.ccp.kabob.kr.ice))

(def ns-to-mirror '(edu.ucdenver.ccp.kabob.kr.ice
                    edu.ucdenver.ccp.kabob.kr.controlled-vocabulary))

;;(potemkin.namespace/import-fn edu.ucdenver.ccp.kabob.kr.ice/rtv)

(dorun
 (map eval
      (mapcat (fn [ns-sym]
                (require ns-sym)
                (map (fn [[local-sym var]]
                       (let [sym (symbol (name ns-sym)
                                         (name local-sym))
                             import (if (.isMacro var)
                                      'potemkin/import-macro
                                      'potemkin/import-fn)]
                                      ;; 'potemkin.namespace/import-macro
                                      ;; 'potemkin.namespace/import-fn)]
                         (list import sym)))
                     (ns-publics ns-sym)))
              ns-to-mirror)))
     
