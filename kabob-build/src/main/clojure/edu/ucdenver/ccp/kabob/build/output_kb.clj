(ns edu.ucdenver.ccp.kabob.build.output-kb
  (use edu.ucdenver.ccp.kr.kb
       edu.ucdenver.ccp.kr.rdf
       edu.ucdenver.ccp.kr.sesame.kb
       edu.ucdenver.ccp.kabob.namespace
       [clojure.java.io :only (output-stream)])
  (require edu.ucdenver.ccp.kr.sesame.writer-kb)
  (import java.util.zip.GZIPOutputStream))

;;; --------------------------------------------------------
;;; kabob writer kbs
;;; --------------------------------------------------------

;; takes something coerceable as an output stream
(defn output-kb [out]
  (let [output (output-stream out)] ;this should be redundant
    (connection
     (register-namespaces
      (edu.ucdenver.ccp.kr.sesame.writer-kb/new-sesame-writer-kb output)
      *namespaces*))))


;; takes something coerceable as an output stream
;; output-file (str base-dir (:name rule) ".nt.gz")
(defn zipped-output-kb [out]
  (let [output (output-stream out)
        zipped (GZIPOutputStream. output)]
    (connection
     (register-namespaces
      (edu.ucdenver.ccp.kr.sesame.writer-kb/new-sesame-writer-kb zipped)
      *namespaces*))))

;;; --------------------------------------------------------
;;; end
;;; --------------------------------------------------------
