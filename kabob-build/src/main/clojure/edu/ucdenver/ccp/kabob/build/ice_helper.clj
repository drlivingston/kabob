(ns edu.ucdenver.ccp.kabob.build.ice-helper)

;; NOTE: IF THIS CODE IS RESURRECTED:
;; records obo/BFO_0000051 (has_part) field values
;; this code uses field values obo/BFO_0000050 part_of records


(defn ftv [field-value template value]
  `((~field-value kiao/hasTemplate ~template)
    (~field-value iao/IAO_0000219 ~value)))

  
(defn rftv [record field-value template value]
  `((~field-value obo/BFO_0000050 ~record)
    (~field-value kiao/hasTemplate ~template)
    (~field-value iao/IAO_0000219 ~value)))

