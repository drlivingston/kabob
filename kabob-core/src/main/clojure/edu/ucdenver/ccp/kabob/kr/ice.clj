(ns edu.ucdenver.ccp.kabob.kr.ice
  (use edu.ucdenver.ccp.kabob.rule))


(defn ftv-fn [field-value template value]
  `((~field-value kiao/hasTemplate ~template)
    (~field-value obo/IAO_0000219 ~value)))
  
(defn rftv-fn [record field-value template value]
  `(;;(~field-value obo/part_of ~record)
    (~record obo/BFO_0000051 ~field-value)
    ~@(ftv-fn field-value template value)))
    ;; (~field-value kiao/hasTemplate ~template)
    ;; (~field-value iao/IAO_0000219 ~value)))

(defn tv-fn [template value]
  (let [field-value (next-anon "fv")]
    (ftv-fn field-value template value)))
  
(defn rtv-1 [record template value]
  (let [field-value (next-anon "fv")]
    (rftv-fn record field-value template value)))

(defn rtv-fn [record & [template value & rest-tv]]
  (concat
   (rtv-1 record template value)
   (and (seq rest-tv)
        (apply rtv-fn record rest-tv))))

(defmacro ftv [field-value template value]
  `(ftv-fn (quote ~field-value) (quote ~template) (quote ~value)))
  
(defmacro rftv [record field-value template value]
  `(rftv-fn (quote ~record)
            (quote ~field-value)
            (quote ~template)
            (quote ~value)))

(defmacro tv [template value]
  `(tv-fn (quote ~template) (quote ~value)))

(defmacro rtv [record & [template value & rest-tv]]
  `(apply rtv-fn
          (quote ~record) (quote ~template) (quote ~value) (quote ~rest-tv)))
  
;; EXAMPLES:
;; >(edu.ucdenver.ccp.kabob.rule/with-reset-ids
;;    (tv 't0 'v0))

;; ((_/fv-0 kiao/hasTemplate t0)
;;  (_/fv-0 iao/IAO_0000219 v0))


;; >(edu.ucdenver.ccp.kabob.rule/with-reset-ids
;;    (rtv 'rec 't0 'v0 't1 'v1 't2 'v2))

;; ((_/fv-0 ro/part_of rec)
;;  (_/fv-0 kiao/hasTemplate t0)
;;  (_/fv-0 iao/IAO_0000219 v0)
;;  (_/fv-1 ro/part_of rec)
;;  (_/fv-1 kiao/hasTemplate t1)
;;  (_/fv-1 iao/IAO_0000219 v1)
;;  (_/fv-2 ro/part_of rec)
;;  (_/fv-2 kiao/hasTemplate t2)
;;  (_/fv-2 iao/IAO_0000219 v2))



;; >(edu.ucdenver.ccp.kabob.rule/with-reset-ids
;;    (ftv 'fv0 't0 'v0))

;; ((fv0 kiao/hasTemplate t0) (fv0 iao/IAO_0000219 v0))


;; >(edu.ucdenver.ccp.kabob.rule/with-reset-ids
;;    (rftv 'rec 'fv0 't0 'v0))

;; ((fv0 ro/part_of rec)
;;  (fv0 kiao/hasTemplate t0)
;;  (fv0 iao/IAO_0000219 v0))