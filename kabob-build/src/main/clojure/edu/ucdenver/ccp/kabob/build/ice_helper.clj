(ns edu.ucdenver.ccp.kabob.build.ice-helper)

(defn ftv [field-value template value]
  `((~field-value kiao/hasTemplate ~template)
    (~field-value iao/IAO_0000219 ~value)))

  
(defn rftv [record field-value template value]
  `((~field-value ro/part_of ~record)
    (~field-value kiao/hasTemplate ~template)
    (~field-value iao/IAO_0000219 ~value)))

