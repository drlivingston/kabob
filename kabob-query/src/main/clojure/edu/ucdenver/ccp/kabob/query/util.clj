(ns edu.ucdenver.ccp.kabob.query.util
  (use edu.ucdenver.ccp.kr.sparql))

;;; --------------------------------------------------------
;;; query bindings
;;; --------------------------------------------------------

(defmacro with-magic-ns [& body]
  `(binding [edu.ucdenver.ccp.kr.sparql/*force-prefixes*
             [["franzOption_memoryLimit" "franz:85g"]
              ["franzOption_memoryExhaustionWarningPercentage" "franz:95"]
              ["franzOption_logQuery" "franz:yes"]
              ["franzOption_clauseReorderer" "franz:identity"]]]
     ~@body))

;;; --------------------------------------------------------
;;; END
;;; --------------------------------------------------------
