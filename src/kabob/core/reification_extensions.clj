(ns kabob.core.reification-extensions
  (use kabob.core.util.hashing
       kr.core.reify
       kr.core.unify
       kr.core.forward-rule
       kr.core.variable))

;;;-------------------------------------------------------------------
;;; restriction helper
;;;-------------------------------------------------------------------

         ;; (?/r1 rdf/type owl/Restriction)
         ;; (?/r1 owl/onProperty obo/has_participant)
         ;; (?/r1 owl/someValuesFrom ?/drug)


;; (defmulti restriction-signature-part (fn [[s p o :as assertion]]
;;                                        p))

;; (defmethod restriction-signature-part 'rdf/type [[s p o :as assertion]]
;;   [o])

;; (defmethod restriction-signature-part 'owl/onProperty [[s p o :as assertion]]
;;   [owl/onProperty o])

;; (defmethod restriction-signature-part 'owl/onProperty [[s p o :as assertion]]
;;   [owl/onProperty o])


;(defn reify-sha-1 [& rest]
;  ;; (pprint "reify sha1")
;  ;; (pprint rest)
;  ;;(pprint *reify-prefix*)
;  (reify-sym (sha-1 (apply str (sort (map str rest))))))
;
;
;
;(defn restriction-signature [assertions]
;  (let [parts (map vec
;                   (remove (fn [[p o :as triple-part]]
;                             (= 'rdf/type p))
;                           (distinct
;                            (map rest assertions))))]
;        ;;(pprint parts)
;        (cons 'owl/Restriction
;              (flatten
;               (sort parts)))))
;
;
;(defn get-restriction-assertions [restriction assertions]
;  (remove (fn [[s]]
;            (not (= restriction s)))
;          assertions))
;
;
;
;;;;-------------------------------------------------------------------
;;;; reification methods
;;;;-------------------------------------------------------------------
;
;
;(defmethod reify-rule-form-fn :sha-1 [rule
;                                      [var {[fn-name & params] :ln
;                                            :as reify-opts}]]
;  (extend-reify-map reify-opts
;                    (fn [bindings]
;                      (with-reify-name-bindings reify-opts
;                        (apply reify-sha-1 (subst-bindings params bindings))))
;                    (variables params)))
;
;
;(defmethod reify-rule-form-fn :restriction [rule
;                                            [var {[fn-name & params] :ln
;                                                  :as reify-opts}]]
;  (let [head (:head rule)
;        restriction-assertions (get-restriction-assertions var head)
;        restriction-sig (restriction-signature restriction-assertions)]
;    ;;(println "restriction-sig:")
;    ;;(println restriction-sig)
;    (extend-reify-map reify-opts
;                      (fn [bindings]
;                        (with-reify-name-bindings reify-opts
;                          (apply reify-sha-1 (subst-bindings restriction-sig bindings))))
;                      (variables restriction-sig))))



;;;-------------------------------------------------------------------
;;; end
;;;-------------------------------------------------------------------
